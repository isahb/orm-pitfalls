package com.javacents.ormpitfalls.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class City {
	@Id
	@Column(length = 36)
	private String id;
	@Column(name = "name", nullable = false, length = 64)
	private String name;
	@Column(name = "country_id", length = 36, nullable = false)
	private Country country;

	public City(String name, Country country) {
		super();
		this.name = name;
		this.country = country;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Country getCountry() {
		return country;
	}
}
