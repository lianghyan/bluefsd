package net.bluefsd.company.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bluefsd.dao.StockPriceRepository;
import net.bluefsd.dao.StockRepository;
import net.bluefsd.entity.Stock;
import net.bluefsd.entity.StockPrice;
import net.bluefsd.model.StockPriceDetail;
import net.bluefsd.util.FSDConstant;

@Service(value = "stockPriceService")
public class StockPriceService {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	StockPriceRepository stockPriceRepository;

	@Autowired
	StockRepository stockRepository;

	public Stock add(Stock stock) {
		return stockRepository.save(stock);
	}

	public Stock findStock(String companyCd, String exchangeCd) {
		List<Stock> list = stockRepository.findByCompanyExch(companyCd, exchangeCd);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public Map<String, List<StockPriceDetail>> findPriceDetails(String[] stockCds, String from, String to)
			throws ParseException {

		List<StockPriceDetail> week = findPriceDetails(stockCds, from, to, FSDConstant.INTERVAL_WEEK);
		List<StockPriceDetail> month = findPriceDetails(stockCds, from, to, FSDConstant.INTERVAL_MONTH);
		List<StockPriceDetail> quarter = findPriceDetails(stockCds, from, to, FSDConstant.INTERVAL_QUARTER);
		List<StockPriceDetail> year = findPriceDetails(stockCds, from, to, FSDConstant.INTERVAL_YEAR);

		HashMap<String, List<StockPriceDetail>> map = new HashMap<>();
		map.put("weekList", week);
		map.put("monthList", month);
		map.put("quarterList", quarter);
		map.put("yearList", year);
		return map;
	}

	private List<StockPriceDetail> findPriceDetails(String[] stockCds, String from, String to, String intervalType)
			throws ParseException {
		List<StockPriceDetail> detailList = new ArrayList<>();
		if (stockCds != null) {
			for (int i = 0; i < stockCds.length; i++) {
				String stockCd = stockCds[i];
				StockPriceDetail detail = findPrice(stockCd, from, to, intervalType);
				detailList.add(detail);
			}
		}
		return detailList;
	}

	private StockPriceDetail findPrice(String stockCd, String from, String to, String intervalType)
			throws ParseException {
		// type: week, month, year, ,
		// Stock stock = stockRepository.findById(stockCd).get();
		// detail.setCompanyCd(stock.getCompanyCd());
		StockPriceDetail detail = new StockPriceDetail();
		detail.setStockCd(stockCd);

		Date oFrom = dateFormat.parse(from);
		Date oTo = dateFormat.parse(to);
		if (FSDConstant.INTERVAL_WEEK.equalsIgnoreCase(intervalType)) {
			detail.setPeriodType(FSDConstant.INTERVAL_WEEK);
			List<StockPrice> priceList = stockPriceRepository.findWeekByStockCd(stockCd, oFrom, oTo);
			return composePriceDetails(priceList, detail);
		}
		if (FSDConstant.INTERVAL_MONTH.equalsIgnoreCase(intervalType)) {
			detail.setPeriodType(FSDConstant.INTERVAL_MONTH);
			List<StockPrice> priceList = stockPriceRepository.findMonthByStockCd(stockCd, oFrom, oTo);
			return composePriceDetails(priceList, detail);
		}
		if (FSDConstant.INTERVAL_QUARTER.equalsIgnoreCase(intervalType)) {
			detail.setPeriodType(FSDConstant.INTERVAL_QUARTER);
			List<StockPrice> priceList = stockPriceRepository.findQuarterByStockCd(stockCd, oFrom, oTo);
			return composePriceDetails(priceList, detail);
		}
		if (FSDConstant.INTERVAL_YEAR.equalsIgnoreCase(intervalType)) {
			detail.setPeriodType(FSDConstant.INTERVAL_YEAR);
			List<StockPrice> priceList = stockPriceRepository.findYearByStockCd(stockCd, oFrom, oTo);
			return composePriceDetails(priceList, detail);
		}

		return null;
	}

	private StockPriceDetail composePriceDetails(List<StockPrice> priceList, StockPriceDetail detail)
			throws ParseException {
		String[] dates = new String[priceList.size()];
		double[] values = new double[priceList.size()];
		for (int i = 0; i < priceList.size(); i++) {
			StockPrice sp = priceList.get(i);
			String dateTime = sp.getCurTime();
			Date oDateTime = simpleFormat.parse(dateTime);
			String date = dateFormat.format(oDateTime);
			dates[i] = date;
			values[i] = sp.getPrice();
		}
		detail.setDates(dates);
		detail.setValues(values);
		return detail;
	}

	public Map<String, StockPriceDetail> findPriceDetail(String stockCd) throws ParseException {
		StockPriceDetail week = findPriceDetail(stockCd, FSDConstant.INTERVAL_WEEK);
		StockPriceDetail month = findPriceDetail(stockCd, FSDConstant.INTERVAL_MONTH);
		StockPriceDetail quarter = findPriceDetail(stockCd, FSDConstant.INTERVAL_QUARTER);
		StockPriceDetail year = findPriceDetail(stockCd, FSDConstant.INTERVAL_YEAR);

		HashMap<String, StockPriceDetail> map = new HashMap<>();
		map.put("weekList", week);
		map.put("monthList", month);
		map.put("quarterList", quarter);
		map.put("yearList", year);
		return map;

	}

	private StockPriceDetail findPriceDetail(String stockCd, String intervalType) throws ParseException {
		StockPriceDetail detail = new StockPriceDetail();
		detail.setStockCd(stockCd);
		if (FSDConstant.INTERVAL_WEEK.equalsIgnoreCase(intervalType)) {
			detail.setPeriodType(FSDConstant.INTERVAL_WEEK);
			List<StockPrice> priceList = stockPriceRepository.findWeekByStockCd(stockCd);
			return composePriceDetails(priceList, detail);
		}
		if (FSDConstant.INTERVAL_MONTH.equalsIgnoreCase(intervalType)) {
			detail.setPeriodType(FSDConstant.INTERVAL_MONTH);
			List<StockPrice> priceList = stockPriceRepository.findMonthByStockCd(stockCd);
			return composePriceDetails(priceList, detail);
		}
		if (FSDConstant.INTERVAL_QUARTER.equalsIgnoreCase(intervalType)) {
			detail.setPeriodType(FSDConstant.INTERVAL_QUARTER);
			List<StockPrice> priceList = stockPriceRepository.findQuarterByStockCd(stockCd);
			return composePriceDetails(priceList, detail);
		}
		if (FSDConstant.INTERVAL_YEAR.equalsIgnoreCase(intervalType)) {
			detail.setPeriodType(FSDConstant.INTERVAL_YEAR);
			List<StockPrice> priceList = stockPriceRepository.findYearByStockCd(stockCd);
			return composePriceDetails(priceList, detail);
		}

		return null;
	}
}
