package com.javacents.ormpitfalls.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Country {
	@Id
	private String name;
	@Column(name = "code", length = 2, nullable = false)
	private String code;

	public Country(String name, String code) {
		super();
		this.name = name;
		this.code = code;
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
}
