package net.bluefsd.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bluefsd.company.dao.CompanyRepository;
import net.bluefsd.entity.Company;
import net.bluefsd.model.CompanyDetails;

@Service
public class CompanyService {
	@Autowired
	CompanyRepository companyRepository;

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
}
