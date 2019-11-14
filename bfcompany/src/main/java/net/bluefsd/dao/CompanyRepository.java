package net.bluefsd.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.Company;

@Repository(value = "companyRepository")
public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT c FROM Company")
	public List<Company> listCompany();

	@Transactional(readOnly = true)
	@Query(value = "SELECT c FROM Company u WHERE companyName=:companyName")
	public Company getCompanyByName(@Param("companyName") String companyName);
}
