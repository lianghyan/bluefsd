package net.bluefsd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_exchange")
@org.hibernate.annotations.Proxy(lazy = false)
public class StockExchange {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "stock_exch_name")
	private String stockExchName;

	@Column(name = "stock_exch_cd")
	private String stockExchCd;

	@Column(name = "brief")
	private String brief;

	@Column(name = "contact_addr")
	private String contactAddr;

	@Column(name = "remarks")
	private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockExchName() {
		return stockExchName;
	}

	public void setStockExchName(String stockExchName) {
		this.stockExchName = stockExchName;
	}

	public String getStockExchCd() {
		return stockExchCd;
	}

	public void setStockExchCd(String stockExchCd) {
		this.stockExchCd = stockExchCd;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContactAddr() {
		return contactAddr;
	}

	public void setContactAddr(String contactAddr) {
		this.contactAddr = contactAddr;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
