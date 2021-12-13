package com.zmk.cache1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zmk.cache1.entity.Country;
import com.zmk.cache1.helper.FileUtilSOS;
import com.zmk.cache1.helper.GlobalVariable;
import com.zmk.cache1.redis.TestRedisData;
import com.zmk.cache1.repo.CountryRepository;

@SpringBootApplication
public class CacheTest1Application implements CommandLineRunner{

	@Autowired
	CountryRepository countryRepository;
	@Autowired
	TestRedisData testRedisData;
	public static void main(String[] args) {
		FileUtilSOS.readSOS();
		SpringApplication.run(CacheTest1Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		//countryRepository.deleteAll();
		List<Country> list = countryRepository.findAll();
		if( list== null ||list.size() ==0 ) {
			createCountryDataTest();
		}
		
		//testRedisData.testData();
		
		
	}
	private void createCountryDataTest() {
		List<Country> list = new ArrayList<>();
		for(int i = 1; i<= 100; i++) {
			Country country = new Country();
			country.setCode("code"+i);
			country.setName("name"+i);
			list.add(country);
		}
		countryRepository.saveAll(list);
	}
}
