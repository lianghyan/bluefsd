package net.bluefsd.company.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.bluefsd.comm.controller.BaseController;
import net.bluefsd.comm.service.StockPriceService;
import net.bluefsd.entity.Stock;
import net.bluefsd.model.PriceDetail;

@RestController
@CrossOrigin
@RequestMapping("/stock")
public class StockPriceController extends BaseController {
	@Autowired
	StockPriceService stockPriceService;

	@RequestMapping(value = { "/listpricedetail" }, method = RequestMethod.POST)
	public Map ListStockPrice(String[] stockCds, String fromDate, String toDate) {
		try {
			Map<String, List<PriceDetail>> detailMap = stockPriceService.listStockPrice(stockCds, fromDate, toDate);
			return composeReturnMap("data", detailMap, "find data successfully!", "No data found!");

		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);
		}
	}

	@RequestMapping(value = { "/pricedetail" }, method = RequestMethod.POST)
	public Map findPriceDetail(String stockCd) {
		try {
			Map<String, PriceDetail> detailMap = stockPriceService.findStockPrice(stockCd);
			return composeReturnMap("data", detailMap, "find data successfully!", "No data found!");

		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);
		}
	}

	@RequestMapping(value = { "/listsectorprice" }, method = RequestMethod.POST)
	public Map ListSectorPrice(String[] sectorCds, String fromDate, String toDate) {
		try {
			Map<String, List<PriceDetail>> detailMap = stockPriceService.listSectorPrice(sectorCds, fromDate, toDate);
			return composeReturnMap("data", detailMap, "find data successfully!", "No data found!");
		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);
		}
	}

	@RequestMapping(value = { "/sectorprice" }, method = RequestMethod.POST)
	public Map findSecotrPrice(String sectorCd) {
		try {
			Map<String, PriceDetail> detailMap = stockPriceService.findSecotrPrice(sectorCd);
			return composeReturnMap("data", detailMap, "find data successfully!", "No data found!");
		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);
		}
	}

	@RequestMapping(value = { "/stocksectorprice" }, method = RequestMethod.POST)
	public Map findStockSectorPrice(String stockCd, String fromDate, String toDate) {
		try {
			Map<String, List<PriceDetail>> detailMap = stockPriceService.findStockSectorPrice(stockCd, fromDate, toDate);
			return composeReturnMap("data", detailMap, "find data successfully!", "No data found!");
		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);
		}
	}

	@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
	public Map add(Stock stock) {
		Stock ret = stockPriceService.add(stock);
		return composeReturnMap("data", ret, "Save data successfully!", "No data updated!");
	}
}
