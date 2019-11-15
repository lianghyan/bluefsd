package net.bluefsd.model;

import java.util.List;

import javax.persistence.Column;

import net.bluefsd.entity.BoardDirector;
import net.bluefsd.entity.Company;
import net.bluefsd.entity.CompanyExchStock;

public class CompanyDetails {
	private Long id = -1L;
	private String companyCd;
	private String companyName;
	private String ceoName;
	private String brief;

	CompanyExchStock stockExch;
	List<String> directors;
	List<String> sectors;

}
