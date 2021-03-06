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
import net.bluefsd.entity.IPOPlan;
import net.bluefsd.model.CompanyDetail;

@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanyController extends BaseController {
	@Autowired
	CompanyService companyService;

	@RequestMapping(value = { "/update", "add" }, method = RequestMethod.POST)
	public Map updateCompany(@RequestBody CompanyDetail compDetails) {
		try {
			Company comp = companyService.updateCompanyDetail(compDetails);
			return composeReturnMap("data", comp.getCompanyCd(), "Save data successfully!", "No data updated!");

		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);

		}
	}

	@RequestMapping(value = { "/companybycd" }, method = RequestMethod.POST)
	public Map findCompanyDetail(@RequestParam String companyCd) {
		try {
			CompanyDetail data = companyService.findCompanyDetail(companyCd);
			return composeReturnMap("data", data, "Find data successfully!", "No data found!");
		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);

		}
	}

	@RequestMapping(value = { "/listcompany" }, method = RequestMethod.POST)
	public Map listCompanyDetail() {
		try {
			List<CompanyDetail> cdList = companyService.listCompanyDetail();
			return composeReturnMap("dataList", cdList, "Find data successfully!", "No data found!");

		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);

		}
	}

	@RequestMapping(value = { "/searchcompanyname" }, method = RequestMethod.POST)
	public Map listCompanyNames(@RequestParam String searchStr) {
		try {
			List<String> cdList = companyService.listCompanyNames(searchStr);
			return composeReturnMap("dataList", cdList, "Find data successfully!", "No data found!");

		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);

		}
	}

	@RequestMapping(value = { "/listcompanyname" }, method = RequestMethod.POST)
	public Map listCompanyNames() {
		try {
			List<String[]> cdList = companyService.listCompanyNames();
			return composeReturnMap("dataList", cdList, "Find data successfully!", "No data found!");

		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);

		}
	}

	@RequestMapping(value = { "/companycdnames" }, method = RequestMethod.POST)
	public Map findCompanyCdNames() {
		try {
			List<String[]> cdList = companyService.findCompanyCdNames();
			return composeReturnMap("dataList", cdList, "Find data successfully!", "No data found!");

		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);

		}
	}
	
	@RequestMapping(value = { "/matchcompany" }, method = RequestMethod.POST)
	public Map findMatchedCompanyDetail(@RequestParam String searchStr) {
		try {
			List<CompanyDetail> cdList = companyService.findMatchedCompanyDetail(searchStr);
			return composeReturnMap("dataList", cdList, "Find data successfully!", "No data found!");

		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);

		}
	}

}
