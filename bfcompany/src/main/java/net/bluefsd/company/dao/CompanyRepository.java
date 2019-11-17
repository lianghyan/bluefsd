package net.bluefsd.company.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.Company;

@Repository(value = "companyRepository")
public interface CompanyRepository extends JpaRepository<Company, String> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT u FROM Company u")
	public List<Company> listCompany();

	@Transactional(readOnly = true)
	@Query(value = "SELECT u FROM Company u WHERE companyName like:input or companyCd like:input")
	public List<Company> findMatchedCompany(@Param("input") String input);

	@Transactional(readOnly = true)
	@Query(value = "SELECT u FROM Company u WHERE companyCd=:companyCd")
	public Company findCompanyByCd(@Param("companyCd") String companyCd);
}
