package net.bluefsd.company.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.bluefsd.comm.controller.BaseController;
import net.bluefsd.company.service.IPOService;
import net.bluefsd.entity.IPOPlan;

@RestController
@CrossOrigin
@RequestMapping("/ipo")
public class IPOController extends BaseController {

	@Autowired
	IPOService ipoService;

	@RequestMapping(value = { "/add", "/create", "/update" }, method = RequestMethod.POST)
	public Map add(@RequestBody IPOPlan ipoPlan) {
		IPOPlan data = ipoService.add(ipoPlan);
		return composeReturnMap("data", data, "Save IPO plan successfully!","Fail to save IPO plan!"); 
	}

	@RequestMapping(value = { "/ipo" }, method = RequestMethod.POST)
	public Map find(@RequestParam String companyCd) {
		IPOPlan data = ipoService.findIpoPlan(companyCd);
		return composeReturnMap("data", data, "Find IPO plan for "+ companyCd +"successfully!","Cant find IPO plan for " + companyCd); 
	}
}
