package net.bluefsd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_price")
@org.hibernate.annotations.Proxy(lazy = false)
public class StockPrice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "company_cd")
	private String companyCd;
	
	@Column(name = "stock_exch_cd")
	private String stockEcxchCd;
	
	@Column(name = "price")
	private String price;
	
	@Column(name = "cur_date")
	private String Date;
	
	@Column(name = "cur_time")
	private String Time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public String getStockEcxchCd() {
		return stockEcxchCd;
	}

	public void setStockEcxchCd(String stockEcxchCd) {
		this.stockEcxchCd = stockEcxchCd;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}
	
	
}
