package net.bluefsd.entity;

import java.sql.Timestamp;

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

	@Column(name = "stock_cd", length=20)
	private String stockCd;
	
	@Column(name = "price")
	private String price;
	
	@Column(name = "cur_time")
	private Timestamp  curTime;
	
 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getStockCd() {
		return stockCd;
	}

	public void setStockCd(String stockCd) {
		this.stockCd = stockCd;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Timestamp getCurTime() {
		return curTime;
	}

	public void setCurTime(Timestamp curTime) {
		this.curTime = curTime;
	}
}
