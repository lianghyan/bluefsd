package net.bluefsd.stockexch.service;

import java.util.List;

import net.bluefsd.entity.Company;
import net.bluefsd.entity.StockExchange;
import net.bluefsd.stockexch.dao.StockExchRepository;

public class StockExchService {
	StockExchRepository stockExchRepository;

	public void addStockExch(StockExchange stockExch) {
		stockExchRepository.save(stockExch);
	}

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
