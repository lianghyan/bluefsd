package net.bluefsd.exchange.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.bluefsd.comm.controller.BaseController;
import net.bluefsd.entity.Exchange;
import net.bluefsd.exchange.service.ExchangeService;
import net.bluefsd.model.CompanyDetail;

@RestController
@CrossOrigin
@RequestMapping("/exchange")
public class ExchangeController extends BaseController {
	@Autowired
	ExchangeService exchnageService;

	@RequestMapping(value = { "/add", "/create", "update" }, method = RequestMethod.POST)
	public Map addStockExch(@RequestBody Exchange exchange) {
		Exchange data = exchnageService.addExchange(exchange);
		return composeReturnMap("data", data, "Add stock exchange successfully!", "Fail to save stock exchange!");
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map listExchange() {
		List<Exchange> dataList = exchnageService.listExchange();
		return composeReturnMap("dataList", dataList, "Find exchange successfully!", "Fail to find exchange!");
	}

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public Map findExchangeByCd(@RequestParam String exchCd) {
		Exchange data = exchnageService.findExchangeByCd(exchCd);
		return composeReturnMap("data", data, "Find exchange successfully!", "Fail to find exchange!");

	}

	@RequestMapping(value = "/listcompany", method = RequestMethod.POST)
	public Map listCompanyByStockExch(String exchCd) {
		List<CompanyDetail> dataList = exchnageService.findCompanyDetailByCd(exchCd);
		return composeReturnMap("dataList", dataList, "Find company successfully!", "Fail to find company!");

	}
}
