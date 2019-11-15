package net.bluefsd.stockexch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.Company;
import net.bluefsd.entity.StockExchange;

@Repository(value = "stockExchRepository")
public interface StockExchRepository extends JpaRepository<StockExchange, Long> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT c FROM StockExchange")
	public List<StockExchange> listStockExchange();

	@Transactional(readOnly = true)
	@Query(value = "SELECT c FROM StockExchange")
	public List<Company> listCompanyByStockExch(@Param("stockExchCd") String stockExchCd);
}
