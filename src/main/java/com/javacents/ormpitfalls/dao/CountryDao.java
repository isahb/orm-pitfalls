package com.javacents.ormpitfalls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javacents.ormpitfalls.model.Country;

@Repository
public class CountryDao extends BaseDao<Country, String> {
	public CountryDao() {
		super(Country.class);
	}

	@Transactional(readOnly = false)
	public void add(List<Country> countries) {
		countries.forEach(country -> {
			add(country);
		});
	}

	@Transactional(readOnly = false)
	public void remove(List<Country> countries) {
		countries.forEach(country -> {
			delete(merge(country));
		});
	}
}
