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
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT e FROM Exchange e")
	public List<Exchange> listExchange();

	@Transactional(readOnly = true)
	@Query(value = "SELECT c FROM Company c, Stock s where s.exchCd= :exchCd and c.companyCd=s.companyCd")
	public List<Company> listCompanyByExchange(@Param("exchCd") String exchCd);
}
