package net.bluefsd.exchange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.bluefsd.comm.controller.BaseController;
import net.bluefsd.entity.Company;
import net.bluefsd.entity.Exchange;
import net.bluefsd.exchange.service.ExchangeService;

@RestController
@CrossOrigin
@RequestMapping("/exchange")
public class ExchangeController extends BaseController {
	@Autowired
	ExchangeService exchnageService;

	@RequestMapping(value = { "/add", "/create", "update" }, method = RequestMethod.POST)
	public Exchange addStockExch(Exchange exchange) {
		return exchnageService.addExchange(exchange);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<Exchange> listExchange() {
		return exchnageService.listExchange();
	}

	@RequestMapping(value = "/listcompany", method = RequestMethod.POST)
	public List<Company> listCompanyByStockExch(String exchCd) {
		return exchnageService.listCompanyByExchange(exchCd);
	}
}
