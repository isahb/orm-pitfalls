package com.javacents.ormpitfalls.dao;

import org.springframework.stereotype.Repository;

import com.javacents.ormpitfalls.model.Country;

@Repository
public class CountryDao extends BaseDao<Country, String> {
	public CountryDao() {
		super(Country.class);
	}
}
