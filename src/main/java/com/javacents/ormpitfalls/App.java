package com.javacents.ormpitfalls;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.javacents.ormpitfalls.dao.BusinessPartnerDao;
import com.javacents.ormpitfalls.model.Data;

import config.PersistenceConfig;

@Component
public class App {
	static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				PersistenceConfig.class)) {
			logger.info("All beans in context {}", Arrays.toString(context.getBeanDefinitionNames()));
			Data.initializeTestData(context);
			
			BusinessPartnerDao bpDao = context.getBean(BusinessPartnerDao.class);
			logger.trace("Naive query about to start...");
			bpDao.getAllNaive();
			logger.trace("Naive query end");
			
			logger.trace("Improved query with fetches about to start...");
			bpDao.getAllWithFetches();
			logger.trace("Improved query end");
		}
	}

}
