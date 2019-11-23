package net.bluefsd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bfmonthday")
@org.hibernate.annotations.Proxy(lazy = false)
public class BFMonthDay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "first_day" ,length=30)
	public String firstDay;

	@Column(name = "last_day",length=30)
	public String lastDay;

	public String getFirstDay() {
		return firstDay;
	}

	public void setFirstDay(String firstDay) {
		this.firstDay = firstDay;
	}

	public String getLastDay() {
		return lastDay;
	}

	public void setLastDay(String lastDay) {
		this.lastDay = lastDay;
	}
}
