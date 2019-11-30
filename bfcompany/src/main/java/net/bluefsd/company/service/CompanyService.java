package net.bluefsd.company.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bluefsd.company.dao.CompanyRepository;
import net.bluefsd.company.dao.IPORepository;
import net.bluefsd.entity.Company;
import net.bluefsd.model.CompanyDetail;
import net.bluefsd.util.CompanyUtil;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	IPORepository ipoRepository;

	public Company addOrUpdateCompany(Company com) {
		return companyRepository.save(com);
	}

	public Company findCompanyByCd(String companyCd) {
		return companyRepository.findCompanyByCd(companyCd);
	}

	public Company updateCompanyDetail(CompanyDetail cd) {
		Company cp = new Company();
		cp.setBrief(cd.getBrief());
		cp.setCeoName(cd.getCeoName());
		cp.setCompanyCd(cd.getCompanyCd());
		cp.setDirector(cd.getDirector());
		cp.setExchCd(cd.getExchCd());
		cp.setSectorCd(cd.getSectorCd());
		return companyRepository.save(cp);
	}

	public CompanyDetail findCompanyDetail(String companyCd) throws Exception {
		List<Object[]> list = companyRepository.findCompanyDetailByCd(companyCd);

		if (list == null || list.size() <= 0) {
			throw new Exception("Can't find any company!");
		} else {
			Object[] obj = list.get(0);
			CompanyDetail cd = CompanyUtil.composeCompanyDetail(obj);
			return cd;
		}
	}

	public List<CompanyDetail> listCompanyDetail() throws Exception {
		List<CompanyDetail> cdList = new ArrayList<>();
		List<Object[]> list = companyRepository.listCompanyDetail();
		if (list == null || list.size() <= 0) {
			throw new Exception("Can't find any company!");
		} else {
			cdList=CompanyUtil.composeCompanyDetail(list);
		}
		return cdList;
	}

	public List<CompanyDetail> findMatchedCompanyDetail(String searchStr) throws Exception {
		List<CompanyDetail> cdList = new ArrayList<>();
		List<Object[]> list = companyRepository.findMatchedCompanyDetail(searchStr);
		if (list == null || list.size() <= 0) {
			throw new Exception("Can't find any company!");
		} else {
			cdList=CompanyUtil.composeCompanyDetail(list);
		}
		return cdList;
	}

	public List<String> listCompanyNames(String searchStr) {
		List<String> list = companyRepository.findMatchedCompanyName(searchStr);
		return list;
	}

	public List<String[]> listCompanyNames() {
		List<String[]> list = companyRepository.findCompanyNames();
		return list;

	}

}
