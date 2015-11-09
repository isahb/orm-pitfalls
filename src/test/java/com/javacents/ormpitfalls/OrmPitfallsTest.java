package com.javacents.ormpitfalls;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.javacents.ormpitfalls.config.PersistenceConfig;
import com.javacents.ormpitfalls.dao.CityDao;
import com.javacents.ormpitfalls.dao.CountryDao;

@RunWith(SpringTestClassRunner.class)
// ApplicationContext will be loaded from PersistenceConfig class
@ContextConfiguration(classes = PersistenceConfig.class, loader = AnnotationConfigContextLoader.class)
public class OrmPitfallsTest implements TestListener {
	static Logger logger = LoggerFactory.getLogger(OrmPitfallsTest.class);

	@Autowired
	private CountryDao countryDao;
	@Autowired
	private CityDao cityDao;

	@Override
	public void beforeClass() {
		logNote("Populating database with some countries and cities...");
		countryDao.add(InitialData.COUNTRIES);
	}

	@Override
	public void afterClass() {
		logNote("Cleaning up database from test data...");
		countryDao.remove(InitialData.COUNTRIES);
	}

	@Test
	public void test_pitfall1_with_non_lazy_ref() {
		logNote("ORM Pitfall nr. 1: non-lazy country in city...");
		cityDao.getAllNaive();
	}

	@Test
	public void test_pitfall1_improved_with_fetch() {
		logNote("Pitfall nr. 1 workaround with country fetch on first query...");
		cityDao.getAllWithFetches();
	}

	@Test
	public void test_pitfall2_with_lazy_ref() {
		logNote("ORM Pitfall nr. 2 if country is set to LAZY in City...");
		cityDao.doSomethingWithCitiesAndTheirCountry();
	}

	@Test
	public void test_pitfall2_with_lazy_collections() {
		logNote("ORM Pitfall nr. 3 lazy cities in Country(default fetch mode)...");
		cityDao.doSomethingWithCountriesAndTheirCities();
	}

	@Test
	public void test_pitfall2_improved_with_fetch() {
		logNote("ORM Pitfall nr. 3 workaround with fetch of cities on first query...");
		cityDao.doSomethingWithCountriesAndTheirCitiesWithFetch();
	}

	private void logNote(String note) {
		logger.trace("----------------------------------------------------------------------");
		logger.trace(note);
		logger.trace("----------------------------------------------------------------------");
	}
}
