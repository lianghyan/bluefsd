package net.bluefsd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company_exch_stock")
@org.hibernate.annotations.Proxy(lazy = false)
public class CompanyExchStock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_cd")
	private String companyCd;

	@Column(name = "stock_cd")
	private String stockCd;

	@Column(name = "stock_exch_cd")
	private String stockExchCd;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public String getStockCd() {
		return stockCd;
	}

	public void setStockCd(String stockCd) {
		this.stockCd = stockCd;
	}

	public String getStockExchCd() {
		return stockExchCd;
	}

	public void setStockExchCd(String stockExchCd) {
		this.stockExchCd = stockExchCd;
	}

}
