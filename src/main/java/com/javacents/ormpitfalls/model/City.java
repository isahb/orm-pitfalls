package com.javacents.ormpitfalls.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.javacents.ormpitfalls.dao.PO;

@Entity(name = "City")
public class City extends PO {
	@Column(name = "name", nullable = false, length = 64)
	private String name;
	@JoinColumn(name = "country_id", nullable = false)
	@ManyToOne
	private Country country;

	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private Set<BusinessPartner> businessPartners = new LinkedHashSet<>();

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

	public Set<BusinessPartner> getBusinessPartners() {
		return businessPartners;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", country=" + country + "]";
	}
}
