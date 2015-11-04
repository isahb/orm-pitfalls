package com.javacents.ormpitfalls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.javacents.ormpitfalls.config.PersistenceConfig;
import com.javacents.ormpitfalls.dao.CityDao;
import com.javacents.ormpitfalls.model.InitialData;

@Component
public class App {
	static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				PersistenceConfig.class)) {
			InitialData.initializeTestData(context);
			
			CityDao cityDao = context.getBean(CityDao.class);
			logger.trace("ORM Pitfall nr. 1: Naive query about to start...");
			cityDao.getAllNaive();
			
			logger.trace("Improved query with fetches about to start...");
			cityDao.getAllWithFetches();
			
			logger.trace("ORM Pitfall nr. 2 if country is set to LAZY in City");
			cityDao.doSomethingWithCitiesAndTheirCountry();
			
			logger.trace("ORM Pitfall nr. 3 lazy cities in Country(default)");
			cityDao.doSomethingWithCountriesAndTheirCities();
			
			logger.trace("ORM Pitfall nr. 3 lazy cities with fetch on first query");
			cityDao.doSomethingWithCountriesAndTheirCitiesWithFetch();
		}
	}
}
