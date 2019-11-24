package net.bluefsd.company.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bluefsd.dao.StockPriceRepository;
import net.bluefsd.dao.StockRepository;
import net.bluefsd.entity.Stock;
import net.bluefsd.entity.StockPrice;
import net.bluefsd.model.StockPriceDetails;
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

	public List<StockPriceDetails> findPriceDetails(String[] stockCds, String from, String to, String periodType)
			throws ParseException {
		List<StockPriceDetails> detailList = new ArrayList<>();
		if (stockCds != null) {
			for (int i = 0; i < stockCds.length; i++) {
				String stockCd = stockCds[i];
				StockPriceDetails detail = findPrice(stockCd, from, to, periodType);
				detailList.add(detail);
			}
		}
		return detailList;
	}

	public StockPriceDetails findPrice(String stockCd, String from, String to, String periodType)
			throws ParseException {
		// type: week, month, year, ,
		Stock stock = stockRepository.findById(stockCd).get();
		StockPriceDetails detail = new StockPriceDetails();
		detail.setCompanyCd(stock.getCompanyCd());
		detail.setStockCd(stock.getStockCd());

		Date oFrom = dateFormat.parse(from);
		Date oTo = dateFormat.parse(to);
		if (FSDConstant.PERIOD_WEEK.equalsIgnoreCase(periodType)) {
			detail.setPeriodType(FSDConstant.PERIOD_WEEK);
			List<StockPrice> priceList = stockPriceRepository.findWeekByStockCd(stockCd, oFrom, oTo);
			return composePriceDetails(priceList, detail);
		}
		if (FSDConstant.PERIOD_MONTH.equalsIgnoreCase(periodType)) {
			detail.setPeriodType(FSDConstant.PERIOD_MONTH);
			List<StockPrice> priceList = stockPriceRepository.findMonthByStockCd(stockCd, oFrom, oTo);
			return composePriceDetails(priceList, detail);
		}
		if (FSDConstant.PERIOD_MONTH6.equalsIgnoreCase(periodType)) {
			detail.setPeriodType(FSDConstant.PERIOD_MONTH6);
			List<StockPrice> priceList = stockPriceRepository.findMonth6ByStockCd(stockCd, oFrom, oTo);
			return composePriceDetails(priceList, detail);
		}
		if (FSDConstant.PERIOD_YEAR.equalsIgnoreCase(periodType)) {
			detail.setPeriodType(FSDConstant.PERIOD_YEAR);
			List<StockPrice> priceList = stockPriceRepository.findYearByStockCd(stockCd, oFrom, oTo);
			return composePriceDetails(priceList, detail);
		}

		return null;
	}

	private StockPriceDetails composePriceDetails(List<StockPrice> priceList, StockPriceDetails detail)
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
}
