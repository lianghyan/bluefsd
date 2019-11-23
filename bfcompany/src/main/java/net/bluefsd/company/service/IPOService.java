package net.bluefsd.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bluefsd.company.dao.IPORepository;
import net.bluefsd.entity.IPOPlan;

@Service(value = "ipoService")
public class IPOService {

	@Autowired
	IPORepository ipoRepository;

	public IPOPlan add(IPOPlan ipoPlan) {
		return ipoRepository.save(ipoPlan);
	}
}
