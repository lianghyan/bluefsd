package net.bluefsd.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bfyearday")
@org.hibernate.annotations.Proxy(lazy = false)
public class BFYearDay extends BFInterval {

}
