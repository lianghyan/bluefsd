package net.bluefsd.sector.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import net.bluefsd.entity.Company;
import net.bluefsd.entity.Sector;
import net.bluefsd.entity.StockPrice;
import net.bluefsd.sector.dao.SectorRepository;

@Service(value = "sectorService")
public class SectorService {
	SectorRepository sectorRepository;

	// getList of Companies in a Sector
	public List<Company> listCompanyBySectior(String sectorCd) {
		return sectorRepository.listCompanyBySector(sectorCd);
	}

	// getSectorPrice Sector ID, From Period, To period, periodicity
	public List<StockPrice> findSectorPrice(Timestamp from, Timestamp to, int periodicity, String sectorCd) {
		return sectorRepository.listSectorPrice(from, to, sectorCd);
	}

	public List<Sector> listSector() {
		return sectorRepository.findAll();
	}
}
