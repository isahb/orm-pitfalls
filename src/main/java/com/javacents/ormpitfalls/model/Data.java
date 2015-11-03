package com.javacents.ormpitfalls.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.javacents.ormpitfalls.dao.CountryDao;

public class Data {
    public static final List<Country> COUNTRIES = new ArrayList<Country>() {
	/**
	* 
	*/
	private static final long serialVersionUID = -2176905740174063781L;

	{
	    Country sk = new Country("Korea, South", "KR");
	    City skCity1 = new City("Gangneung", sk);
	    skCity1.setId(UUID.randomUUID().toString());
	    City skCity2 = new City("Seoul", sk);
	    skCity2.setId(UUID.randomUUID().toString());

	    BusinessPartner skBp1City1 = new BusinessPartner("SK Business Partner 1", "1000000", skCity1);
	    skBp1City1.setId(UUID.randomUUID().toString());
	    BusinessPartner skBp2City1 = new BusinessPartner("SK Business Partner 2", "1000001", skCity1);
	    skBp2City1.setId(UUID.randomUUID().toString());
	    BusinessPartner skBp3City1 = new BusinessPartner("SK Business Partner 3", "1000002", skCity1);
	    skBp3City1.setId(UUID.randomUUID().toString());
	    BusinessPartner skBp4City2 = new BusinessPartner("SK Business Partner 4", "1000003", skCity2);
	    skBp4City2.setId(UUID.randomUUID().toString());
	    skCity1.getBusinessPartners().addAll(Arrays.asList(skBp1City1, skBp2City1, skBp3City1));
	    skCity2.getBusinessPartners().add(skBp4City2);
	    Set<City> skCities = new LinkedHashSet<>(Arrays.asList(skCity1, skCity2));
	    sk.getCities().addAll(skCities);
	    add(sk);

	    Country kosovo = new Country("Kosovo", "KS");
	    City ksCity1 = new City("Gjilan", kosovo);
	    ksCity1.setId(UUID.randomUUID().toString());
	    City ksCity2 = new City("Prishtine", kosovo);
	    ksCity2.setId(UUID.randomUUID().toString());

	    BusinessPartner ksBp1City1 = new BusinessPartner("KS Business Partner 1", "2000000", skCity1);
	    ksBp1City1.setId(UUID.randomUUID().toString());
	    BusinessPartner ksBp2City1 = new BusinessPartner("KS Business Partner 2", "2000001", skCity1);
	    ksBp2City1.setId(UUID.randomUUID().toString());
	    BusinessPartner ksBp3City2 = new BusinessPartner("KS Business Partner 3", "2000002", skCity2);
	    ksBp3City2.setId(UUID.randomUUID().toString());
	    BusinessPartner ksBp4City2 = new BusinessPartner("KS Business Partner 4", "2000003", skCity2);
	    ksBp4City2.setId(UUID.randomUUID().toString());
	    ksCity1.getBusinessPartners().addAll(Arrays.asList(ksBp1City1, ksBp2City1));
	    ksCity2.getBusinessPartners().addAll(Arrays.asList(ksBp3City2, ksBp4City2));
	    Set<City> kosovoCities = new LinkedHashSet<>(Arrays.asList(ksCity1, ksCity2));
	    kosovo.getCities().addAll(kosovoCities);
	    add(kosovo);
	}
    };
    
    
    public static void initializeTestData(AnnotationConfigApplicationContext context) {
		CountryDao countryDao = context.getBean(CountryDao.class);
		for (Country country : COUNTRIES) {
			if (countryDao.find(country.getName()) == null) {
				countryDao.add(country);
			} 
		}
	}
}
