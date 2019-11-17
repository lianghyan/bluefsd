package net.bluefsd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
@org.hibernate.annotations.Proxy(lazy = false)
public class Company {
	
	@Id
	@Column(name = "company_cd", unique=true, nullable=false, length=20)
	private String companyCd;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "ceo_name")
	private String ceoName;

	@Column(name = "brief")
	private String brief;

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCeoName() {
		return ceoName;
	}

	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

}
