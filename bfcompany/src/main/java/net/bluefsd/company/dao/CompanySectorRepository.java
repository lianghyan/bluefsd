package net.bluefsd.company.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.CompanySector;

@Repository(value = "companySectorRepository")
public interface CompanySectorRepository extends JpaRepository<CompanySector, Long> {
	@Transactional(readOnly = true)
	@Query(value = "DELETE FROM CompanySector u where companyCd=:companyCd")
	public void deleteByCompanyCd(@Param("companyCd") String companyCd);

	@Transactional(readOnly = true)
	@Query(value = "SELECT s.sectorName FROM CompanySector c, Sector s where c.companyCd=s.companyCd and companyCd=:companyCd")
	public List<String> findByCompanyCd(@Param("companyCd") String companyCd);

}
