package net.bluefsd.company.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.bluefsd.comm.controller.BaseController;
import net.bluefsd.company.service.CompanyService;
import net.bluefsd.entity.Company;
import net.bluefsd.model.CompanyDetail;

@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanyController extends BaseController {
	@Autowired
	CompanyService companyService;

	@RequestMapping(value = { "/add", "/create" }, method = RequestMethod.POST)
	public Company addCompany(@RequestBody Company newComp) throws Exception {
		Company comp = companyService.findCompanyByCd(newComp.getCompanyCd());
		if (comp != null) {
			throw new Exception("Company code already exist!");
		}
		return companyService.addOrUpdateCompany(newComp);
	}

	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public Map updateCompany(@RequestBody CompanyDetail compDetails) {
		try {
			companyService.updateCompanyDetail(compDetails);
			return composeReturnMap("Company is updated successfully!");
		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);

		}
	}

	@RequestMapping(value = { "/companybycd" }, method = RequestMethod.POST)
	public Map findCompanyDetail(@RequestParam String companyCd) {
		try {
			CompanyDetail cd = companyService.findCompanyDetail(companyCd);
			Map map = composeReturnMap("Company[" + companyCd + "] is found successfully!");
			map.put("data", cd);
			return map;

		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);

		}
	}
	


	@RequestMapping(value = { "/listcompany" }, method = RequestMethod.POST)
	public Map listCompanyDetail() {
		try {
			List<CompanyDetail> cdList = companyService.listCompanyDetail();
			Map map = composeReturnMap();
			map.put("dataList", cdList);
			return map;

		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);

		}
	}
	
	@RequestMapping(value = { "/listcompanyname" }, method = RequestMethod.POST)
	public Map listCompanyName(@RequestParam  String searchStr) {
		try {
			List<String> cdList = companyService.listCompanyName(searchStr);
			Map map = composeReturnMap();
			map.put("dataList", cdList);
			return map;

		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);

		}
	}
	
	@RequestMapping(value = { "/companybyname" }, method = RequestMethod.POST)
	public Map findCompanyDetailByName(@RequestParam  String companyName) {
		try {
			List<CompanyDetail> cdList = companyService.findCompanyDetailByName(companyName);
			Map map = composeReturnMap();
			map.put("dataList", cdList);
			return map;

		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);

		}
	} 
}
