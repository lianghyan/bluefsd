package net.bluefsd.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bfquarterday")
@org.hibernate.annotations.Proxy(lazy = false)
public class BFQuarterDay extends BFInterval {
	
}
