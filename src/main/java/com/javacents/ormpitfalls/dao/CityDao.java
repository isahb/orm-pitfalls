package com.javacents.ormpitfalls.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javacents.ormpitfalls.model.City;
import com.javacents.ormpitfalls.model.Country;

@Repository
public class CityDao extends BaseDao<City, String> {
	public CityDao() {
		super(City.class);
	}

	/**
	 * Pitfall nr. 1 with non-lazy references
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<City> getAllNaive() {
		String jpql = "FROM " + City.class.getName();
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	/**
	 * Alleviation to avoid pitfall nr. 1 by fetching country
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<City> getAllWithFetches() {
		Query query = entityManager.createQuery(
				new StringBuilder("FROM ").append(City.class.getName()).append(" ct JOIN FETCH ct.country").toString());
		return query.getResultList();
	}

	/**
	 * Pitfall nr. 2 with LAZY references. Make country reference LAZY in City
	 * entity to test behavior of this method
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void doSomethingWithCitiesAndTheirCountry() {
		String jpql = "FROM " + City.class.getName();
		Query query = entityManager.createQuery(jpql);
		List<City> cities = query.getResultList(); // 1 query to list cities
		cities.forEach(city -> {
			String countryName = city.getCountry().getName();// city.getCountry() triggers query to fetch country, n queries.
			// do something
		});
	}

	/**
	 * Pitfall nr. 3 with LAZY collections
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void doSomethingWithCountriesAndTheirCities() {
		String jpql = "FROM " + Country.class.getName();
		Query query = entityManager.createQuery(jpql);
		List<Country> countries = query.getResultList(); // 1 query to list cities
		countries.forEach(country -> {
			country.getCities().toString(); // country.getCities() triggers a query to fetch cities of that country, n queries
			// do something
		});
	}

	/**
	 * Avoiding pitfall nr. 3 by fetching the LAZY collection right away
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void doSomethingWithCountriesAndTheirCitiesWithFetch() {
		String jpql = "FROM " + Country.class.getName() + " c JOIN FETCH c.cities";
		Query query = entityManager.createQuery(jpql);
		List<Country> countries = query.getResultList(); // 1 query to list
															// cities
		countries.forEach(country -> {
			country.getCities().toString(); // country.getCities() triggers a
											// query to fetch cities of that
											// country, n queries
			// do something
		});
	}

}
