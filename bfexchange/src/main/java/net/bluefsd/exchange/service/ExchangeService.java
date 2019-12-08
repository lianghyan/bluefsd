package net.bluefsd.exchange.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bluefsd.entity.Exchange;
import net.bluefsd.exchange.dao.ExchangeRepository;
import net.bluefsd.model.CompanyDetail;
import net.bluefsd.util.CompanyUtil;

@Service(value = "exchangeService")
public class ExchangeService {
	@Autowired
	ExchangeRepository exchangeRepository;

	public Exchange addExchange(Exchange exchange) {
		return exchangeRepository.save(exchange);
	}

	public List<Exchange> listExchange() {
		return exchangeRepository.listExchange();
	}

	public List<CompanyDetail> findCompanyDetailByCd(String exchangeCd) {
		List<Object[]> list = exchangeRepository.findCompanyDetailByCd(exchangeCd);
		return CompanyUtil.composeCompanyDetail(list);
	}

	public Exchange findExchangeByCd(String exchCd) {
		return exchangeRepository.findById(exchCd).get();
	}

	public List<Object[]> ListExchangeCdName() {
		return exchangeRepository.ListExchangeCdName();
	}

}
