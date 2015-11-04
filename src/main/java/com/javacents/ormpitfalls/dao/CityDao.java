package com.javacents.ormpitfalls.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javacents.ormpitfalls.model.City;
import com.javacents.ormpitfalls.model.Country;

@Repository
public class CityDao extends BaseDao<City, String> {
	public CityDao() {
		super(City.class);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<City> getAllNaive() {
		String jpql = "FROM " + City.class.getName();
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<City> getAllWithFetches() {
		Query query = entityManager.createQuery(new StringBuilder("FROM ").append(City.class.getName())
				.append(" ct JOIN FETCH ct.country").toString());
		return query.getResultList();
	}
	
	/**
	 * Make country reference LAZY in City entity to test behavior of this method
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void doSomethingWithCitiesAndTheirCountry() {
		String jpql = "FROM " + City.class.getName();
		Query query = entityManager.createQuery(jpql);
		List<City> cities = query.getResultList(); //1 query to list cities
		cities.forEach(city -> {
			String countryName = city.getCountry().getName();//city.getCountry() triggers query to fetch country, n queries.
			// do something
		});
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void doSomethingWithCountriesAndTheirCities() {
		String jpql = "FROM " + Country.class.getName();
		Query query = entityManager.createQuery(jpql);
		List<Country> countries = query.getResultList(); // 1 query to list cities
		countries.forEach(country -> {
			country.getCities().toString(); //country.getCities() triggers a query to fetch cities of that country, n queries
			// do something
		});
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void doSomethingWithCountriesAndTheirCitiesWithFetch() {
		String jpql = "FROM " + Country.class.getName() + " c JOIN FETCH c.cities";
		Query query = entityManager.createQuery(jpql);
		List<Country> countries = query.getResultList(); // 1 query to list cities
		countries.forEach(country -> {
			country.getCities().toString(); //country.getCities() triggers a query to fetch cities of that country, n queries
			// do something
		});
	}
	
}
