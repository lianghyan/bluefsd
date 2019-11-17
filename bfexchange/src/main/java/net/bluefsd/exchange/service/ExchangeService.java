package net.bluefsd.exchange.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bluefsd.entity.Company;
import net.bluefsd.entity.Exchange;
import net.bluefsd.exchange.dao.ExchangeRepository;

@Service(value="exchangeService")
public class ExchangeService {
	@Autowired
	ExchangeRepository exchangeRepository;

	public Exchange addExchange(Exchange exchange) {
		return exchangeRepository.save(exchange);
	}

	public List<Exchange> listExchange() {
		return exchangeRepository.listExchange();
	}

	public List<Company> listCompanyByExchange(String exchangeCd) {
		return exchangeRepository.listCompanyByExchange(exchangeCd);
	}

}
