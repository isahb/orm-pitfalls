package com.javacents.ormpitfalls.dao;

import com.javacents.ormpitfalls.model.City;

public class CityDao extends BaseDao<City, String> {
	public CityDao() {
		super(City.class);
	}
}
