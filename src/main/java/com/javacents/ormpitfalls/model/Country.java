package com.javacents.ormpitfalls.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Country")
public class Country {
	@Id
	private String name;
	@Column(name = "code", length = 2, nullable = false)
	private String code;

	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	private Set<City> cities = new LinkedHashSet<>();
	
	public Country() {
		super();
	}

	public Country(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public Country(String name, String code, Set<City> cities) {
		super();
		this.name = name;
		this.code = code;
		this.cities = cities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<City> getCities() {
		return cities;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", code=" + code + "]";
	}
}
