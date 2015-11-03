package com.javacents.ormpitfalls.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.javacents.ormpitfalls.dao.PO;

@Entity(name = "BUSINESS_PARTNER")
public class BusinessPartner extends PO {
	@Column(name = "name", length = 128, nullable = false)
	private String name;
	@Column(name = "fisc_no", length = 64, nullable = false)
	private String fiscNo;
	@JoinColumn(name = "city_id", nullable = false)
	@ManyToOne
	private City city;

	public BusinessPartner() {
		super();
	}

	public BusinessPartner(String name, String fiscNo, City city) {
		super();
		this.name = name;
		this.fiscNo = fiscNo;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public String getFiscNo() {
		return fiscNo;
	}

	public City getCity() {
		return city;
	}

	@Override
	public String toString() {
		return "BusinessPartner [name=" + name + ", fiscNo=" + fiscNo + ", city=" + city + "]";
	}
}
