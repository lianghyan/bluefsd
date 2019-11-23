package net.bluefsd.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = { "/add", "/create" }, method = RequestMethod.POST)
	public IPOPlan add(IPOPlan ipoPlan) {
		return ipoService.add(ipoPlan);
	}
}
