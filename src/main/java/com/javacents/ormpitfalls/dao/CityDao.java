package com.javacents.ormpitfalls.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.javacents.ormpitfalls.model.City;

@Repository
public class CityDao extends BaseDao<City, String> {
	public CityDao() {
		super(City.class);
	}

	@SuppressWarnings("unchecked")
	public List<City> getAllNaive() {
		String jpql = "FROM " + City.class.getName();
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<City> getAllWithFetches() {
		Query query = entityManager.createQuery(new StringBuilder("FROM ").append(City.class.getName())
				.append(" ct JOIN FETCH ct.country").toString());
		return query.getResultList();
	}
}
