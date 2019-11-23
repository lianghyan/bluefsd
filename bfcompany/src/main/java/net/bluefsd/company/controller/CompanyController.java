package net.bluefsd.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.bluefsd.comm.controller.BaseController;
import net.bluefsd.company.service.CompanyService;
import net.bluefsd.entity.Company;
import net.bluefsd.entity.StockPrice;
import net.bluefsd.model.CompanyDetails;

@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanyController extends BaseController {
	@Autowired
	CompanyService companyService;

	@RequestMapping(value = { "/add", "/create" }, method = RequestMethod.POST)
	public Company addCompany(Company newComp) throws Exception {
		Company comp = companyService.findCompanyByCd(newComp.getCompanyCd());
		if (comp != null) {
			throw new Exception("Company code already exist!");
		}
		return companyService.addOrUpdateCompany(newComp);
	}

	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public CompanyDetails updateCompany(CompanyDetails compDetails) {
		return companyService.findCompanyDetail(compDetails);
	}

	public CompanyDetails findCompanyDetail(Company compDetails) {
		return companyService.findCompanyDetail(compDetails);
	}

	public List<StockPrice> findPrice(String companyCd, String from, String to, String type) {
		return null;
	}
}
