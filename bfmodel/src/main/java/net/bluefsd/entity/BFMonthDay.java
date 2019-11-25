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
public class BFMonthDay  extends BFInterval {

}
