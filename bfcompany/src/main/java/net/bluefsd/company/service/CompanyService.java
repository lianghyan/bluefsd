package net.bluefsd.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bluefsd.company.dao.CompanyRepository;
import net.bluefsd.company.dao.IPORepository;
import net.bluefsd.company.dao.StockRepository;
import net.bluefsd.dao.StockPriceRepository;
import net.bluefsd.entity.Company;
import net.bluefsd.entity.StockPrice;
import net.bluefsd.model.CompanyDetails;
import net.bluefsd.model.StockPriceDetails;
import net.bluefsd.util.FSDConstant;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	IPORepository ipoRepository;

	@Autowired
	StockPriceRepository stockPriceRepository;

	@Autowired
	StockRepository stockRepository;

	public Company addOrUpdateCompany(Company com) {
		return companyRepository.save(com);
	}

	public Company findCompanyByCd(String companyCd) {
		return companyRepository.findCompanyByCd(companyCd);
	}

	public List<Company> findMatchedCompany(String input) {
		return companyRepository.findMatchedCompany(input);
	}

	public List getCompanyStockPrice() {
		return null;
	}

	public List findCompanyIPODetails(String companyCd) {
		return null;
	}

	public CompanyDetails findCompanyDetail(CompanyDetails compDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	public CompanyDetails findCompanyDetail(Company compDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<StockPriceDetails> findPrice(String companyCd, String from, String to, String periodType) {
		// type: week, month, year, ,
		String stock_cd = "";
		if (FSDConstant.PERIOD_WEEK.equalsIgnoreCase(periodType)) {
			List<StockPrice> list = stockPriceRepository.findWeekByStockCd(stock_cd, from, to);
		}
		if (FSDConstant.PERIOD_MONTH.equalsIgnoreCase(periodType)) {
			List<StockPrice> list = stockPriceRepository.findMonthByStockCd(stock_cd, from, to);
		}
		if (FSDConstant.PERIOD_YEAR.equalsIgnoreCase(periodType)) {

		}

		return null;
	}
}
