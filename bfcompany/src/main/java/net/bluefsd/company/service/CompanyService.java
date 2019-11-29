package net.bluefsd.company.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bluefsd.company.dao.CompanyRepository;
import net.bluefsd.company.dao.IPORepository;
import net.bluefsd.entity.Company;
import net.bluefsd.model.CompanyDetail;

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

	public List findCompanyIPODetails(String companyCd) {
		return null;
	}

	public void updateCompanyDetail(CompanyDetail cd) {
		Company cp = new Company();
		cp.setBrief(cd.getBrief());
		cp.setCeoName(cd.getCeoName());
		cp.setCompanyCd(cd.getCompanyCd());
		cp.setDirector(cd.getDirector());
		cp.setExchCd(cd.getExchCd());
		cp.setSectorCd(cd.getSectorCd());
		companyRepository.save(cp);
	}

	public CompanyDetail findCompanyDetail(String companyCd) throws Exception {
		List<Object[]> list = companyRepository.findCompanyDetailByCd(companyCd);

		if (list == null || list.size() <= 0) {
			throw new Exception("Can't find any company!");
		} else {
			Object[] obj = list.get(0);
			CompanyDetail cd = composeCompanyDetail(obj);
			return cd;
		}
	}

	public List<CompanyDetail> listCompanyDetail() throws Exception {
		List<CompanyDetail> cdList = new ArrayList<>();
		List<Object[]> list = companyRepository.listCompanyDetail();
		if (list == null || list.size() <= 0) {
			throw new Exception("Can't find any company!");
		} else {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = list.get(i);
				CompanyDetail cd = composeCompanyDetail(obj);
				cdList.add(cd);
			}
		}
		return cdList;
	}

	public List<CompanyDetail> findCompanyDetailByName(String companyName) throws Exception {
		List<CompanyDetail> cdList = new ArrayList<>();
		List<Object[]> list = companyRepository.findCompanyDetailByName(companyName);
		if (list == null || list.size() <= 0) {
			throw new Exception("Can't find any company!");
		} else {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = list.get(i);
				CompanyDetail cd = composeCompanyDetail(obj);
				cdList.add(cd);
			}
		}
		return cdList;
	}

	private CompanyDetail composeCompanyDetail(Object[] obj) {
		// select c.companyCd, c.companyName,c.ceoName, c.exchCd, e.exchName,
		// c.director, c.brief,
		// c.sectorCd, s.sectorName
		CompanyDetail cd = new CompanyDetail();
		int index = 0;
		cd.setCompanyCd((String) obj[index++]);
		cd.setCompanyName((String) obj[index++]);
		cd.setCeoName((String) obj[index++]);
		cd.setExchCd((String) obj[index++]);
		cd.setExchName((String) obj[index++]);
		cd.setDirector((String) obj[index++]);
		cd.setBrief((String) obj[index++]);
		cd.setSectorCd((String) obj[index++]);
		cd.setSectorName((String) obj[index++]);
		cd.setStockCd((String) obj[index++]);

		return cd;
	}

	public List<String> listCompanyName(String searchStr) {
		List<String> list = companyRepository.findMatchedCompanyName(searchStr);
		return list;
	}
 

}
