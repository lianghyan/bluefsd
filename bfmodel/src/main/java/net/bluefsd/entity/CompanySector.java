package net.bluefsd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company_sector")
@org.hibernate.annotations.Proxy(lazy = false)
public class CompanySector {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "sector_cd", length=20)
	private String sectorCd;

	@Column(name = "company_cd", length=20)
	private String companyCd;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSectorCd() {
		return sectorCd;
	}

	public void setSectorCd(String sectorCd) {
		this.sectorCd = sectorCd;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

}
