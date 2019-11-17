package net.bluefsd.model;

import java.util.List;

import javax.persistence.Column;

import net.bluefsd.entity.BoardDirector;
import net.bluefsd.entity.Company;
import net.bluefsd.entity.Stock;

public class CompanyDetails {
	private Long id = -1L;
	private String companyCd;
	private String companyName;
	private String ceoName;
	private String brief;

	Stock stockExch;
	List<String> directors;
	List<String> sectors;

}
