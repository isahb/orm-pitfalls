package com.javacents.ormpitfalls.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.javacents.ormpitfalls.dao.PO;

@Entity(name = "City")
public class City extends PO {
	@Column(name = "name", nullable = false, length = 64)
	private String name;
	@JoinColumn(name = "country_id", nullable = false)
	@ManyToOne
	private Country country;

	public City() {
		super();
	}

	public City(String name, Country country) {
		super();
		this.name = name;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public Country getCountry() {
		return country;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", country=" + country + "]";
	}
}
