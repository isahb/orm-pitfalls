package com.javacents.ormpitfalls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.javacents.ormpitfalls.model.City;
import com.javacents.ormpitfalls.model.Country;

public class InitialData {

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

			Set<City> skCities = new LinkedHashSet<>(Arrays.asList(skCity1, skCity2));
			sk.getCities().addAll(skCities);
			add(sk);

			Country kosovo = new Country("Kosovo", "KS");
			City ksCity1 = new City("Gjilan", kosovo);
			ksCity1.setId(UUID.randomUUID().toString());
			City ksCity2 = new City("Prishtine", kosovo);
			ksCity2.setId(UUID.randomUUID().toString());
			Set<City> kosovoCities = new LinkedHashSet<>(Arrays.asList(ksCity1, ksCity2));
			kosovo.getCities().addAll(kosovoCities);
			add(kosovo);

			Country austria = new Country("Austria", "AT");
			City atCity1 = new City("Wien", austria);
			atCity1.setId(UUID.randomUUID().toString());
			City atCity2 = new City("Salzburg", austria);
			atCity2.setId(UUID.randomUUID().toString());

			Set<City> austriaCities = new LinkedHashSet<>(Arrays.asList(atCity1, atCity2));
			austria.getCities().addAll(austriaCities);
			add(austria);
		}
	};
}
