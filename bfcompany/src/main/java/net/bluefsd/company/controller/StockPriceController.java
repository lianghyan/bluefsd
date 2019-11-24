package net.bluefsd.company.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.bluefsd.comm.controller.BaseController;
import net.bluefsd.company.service.StockPriceService;
import net.bluefsd.entity.Stock;
import net.bluefsd.model.StockPriceDetails;

@RestController
@CrossOrigin
@RequestMapping("/stock")
public class StockPriceController extends BaseController {
	@Autowired
	StockPriceService stockPriceService;

	@RequestMapping(value = { "/pricedetails" }, method = RequestMethod.POST)
	public List<StockPriceDetails> findPriceDetails(String[] stockCds, String from, String to, String periodType) {
		try {
			return stockPriceService.findPriceDetails(stockCds, from, to, periodType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
	public Map add(Stock stock) {
		stockPriceService.add(stock);
		return composeReturnMap();
	}
}
