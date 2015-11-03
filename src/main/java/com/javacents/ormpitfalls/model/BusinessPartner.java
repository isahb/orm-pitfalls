package com.javacents.ormpitfalls.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class BusinessPartner {
	@Id
	@Column(length = 36)
	private String id;
	@Column(name = "name", length = 128, nullable = false)
	private String name;
	@Column(name = "fisc_no", length = 64, nullable = false)
	private String fiscNo;
	@Column(name = "city_id", length = 36, nullable = false)
	private City city;

	public BusinessPartner(String name, String fiscNo, City city) {
		super();
		this.name = name;
		this.fiscNo = fiscNo;
		this.city = city;
	}

	public String getId() {
		return id;
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
}
