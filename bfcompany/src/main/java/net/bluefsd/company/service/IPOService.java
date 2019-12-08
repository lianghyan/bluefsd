package net.bluefsd.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bluefsd.comm.dao.StockRepository;
import net.bluefsd.company.dao.CompanyRepository;
import net.bluefsd.company.dao.IPORepository;
import net.bluefsd.entity.Company;
import net.bluefsd.entity.IPOPlan;
import net.bluefsd.entity.Stock;

@Service(value = "ipoService")
public class IPOService {

	@Autowired
	IPORepository ipoRepository;

	@Autowired
	StockRepository stockRepository;

	@Autowired
	CompanyRepository companyRepository;

	public IPOPlan add(IPOPlan ipoPlan) throws Exception {
		Company com = companyRepository.findById(ipoPlan.getCompanyCd()).get();
		String sectorCd = com.getSectorCd();

		Stock exSt = stockRepository.findById(ipoPlan.getStockCd()).get();
		if (exSt != null && !exSt.getCompanyCd().equals(ipoPlan.getCompanyCd())) {
			throw new Exception("Stock Cd already exists for " + exSt.getCompanyCd() + "!");
		}
		stockRepository.deleteByCompanyCd(com.getCompanyCd());

		Stock st = new Stock();
		st.setCompanyCd(ipoPlan.getCompanyCd());
		st.setExchCd(ipoPlan.getExchCd());
		st.setSectorCd(sectorCd);
		st.setStockCd(ipoPlan.getStockCd());
		stockRepository.save(st);
		return ipoRepository.save(ipoPlan);
	}

	public IPOPlan findIpoPlan(String companyCd) {
		return ipoRepository.findIPOByCompanyCd(companyCd);
	}
}
