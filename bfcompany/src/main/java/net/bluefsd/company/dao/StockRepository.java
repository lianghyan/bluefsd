package net.bluefsd.company.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.Stock;

@Repository(value = "stockRepository")
public interface StockRepository extends JpaRepository<Stock, String> {
	@Transactional(readOnly = true)
	@Query(value = "DELETE FROM Stock u where companyCd=:companyCd")
	public void deleteByCompanyCd(@Param("companyCd") String companyCd);

	@Transactional(readOnly = true)
	@Query(value = "SELECT s FROM Stock s where companyCd=:companyCd")
	public List<String> findByCompanyCd(@Param("companyCd") String companyCd);

}
