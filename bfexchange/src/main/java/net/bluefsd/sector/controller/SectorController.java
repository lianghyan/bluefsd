package net.bluefsd.sector.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.bluefsd.comm.controller.BaseController;
import net.bluefsd.entity.Company;
import net.bluefsd.entity.Sector;
import net.bluefsd.entity.StockPrice;
import net.bluefsd.sector.service.SectorService;

@RestController
@CrossOrigin
@RequestMapping("/sector")
public class SectorController extends BaseController {
	@Autowired
	SectorService sectorService;

	public List<Sector> listSector() {
		return sectorService.listSector();
	}

	public List<Company> listCompanyBySector(String sectorCd) {
		return sectorService.listCompanyBySectior(sectorCd);
	}

	@RequestMapping(value = { "/listSectorNames" }, method = RequestMethod.POST)
	public Map listSectorNames() {
		List<String[]> dataList = sectorService.listSectorNames();
		return composeReturnMap("dataList", dataList, "Find data successfully!", "No data found!");

	}

	public List<StockPrice> findSectorPrice(Timestamp from, Timestamp to, int periodicity, String sectorCd) {
		return sectorService.findSectorPrice(from, to, periodicity, sectorCd);
	}
}
