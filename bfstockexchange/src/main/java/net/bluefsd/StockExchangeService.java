package net.bluefsd;

import java.util.List;

import org.springframework.data.repository.query.Param;

import net.bluefsd.dao.StockExchangeRepository;
import net.bluefsd.entity.Company;
import net.bluefsd.entity.StockExchange;

public class StockExchangeService {
	StockExchangeRepository stockExchRepository;

	public List<StockExchange> listStockExchange() {
		return stockExchRepository.listStockExchange();
	}

	public void addStockExchange(StockExchange stockExch) {
		stockExchRepository.saveAndFlush(stockExch);
	}

	public List<Company> listCompanyByStockExch(String stockExchCd) {
		return stockExchRepository.listCompanyByStockExch(stockExchCd);
	}

}
