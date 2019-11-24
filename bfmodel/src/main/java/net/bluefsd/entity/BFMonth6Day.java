package net.bluefsd.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bfmont6hday")
@org.hibernate.annotations.Proxy(lazy = false)
public class BFMonth6Day extends BFPeriodDay {
	
}
