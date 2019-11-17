package net.bluefsd.company.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.StockPrice;

@Repository(value = "stockPriceRepository")
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {
	@Transactional(readOnly = true)
	@Query(value = "DELETE FROM StockPrice u where stockCd=:stockCd")
	public void deleteByStockCd(@Param("stockCd") String stockCd);

	@Transactional(readOnly = true)
	@Query(value = "SELECT s FROM StockPrice s where stockCd=:stockCd")
	public List<String> findByStockCd(@Param("stockCd") String stockCd);

}
