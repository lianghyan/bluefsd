package net.bluefsd.exchange.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.Company;
import net.bluefsd.entity.Exchange;

@Repository(value = "exchangeRepository")
public interface ExchangeRepository extends JpaRepository<Exchange, String> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT e FROM Exchange e")
	public List<Exchange> listExchange();

	@Transactional(readOnly = true)
	@Query(value = "select c.companyCd, c.companyName, c.ceoName, c.exchCd, e.exchName, c.director, c.brief, c.sectorCd, s.sectorName , st.stockCd "
			+ "from Company c, Sector s, Exchange e , Stock st where c.exchCd=:exchCd and c.sectorCd=s.sectorCd and c.exchCd=e.exchCd and st.companyCd=c.companyCd")
	public List<Object[]> findCompanyDetailByCd(@Param("exchCd") String exchCd); 
}
