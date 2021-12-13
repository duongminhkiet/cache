package com.zmk.cache1.redis;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.zmk.cache1.entity.Country;
import com.zmk.cache1.service.CountryService;

@Component
public class TestRedisData {
	@Autowired
    private RedisTemplate template;
	
	@Autowired 
	CountryService countryService;
	
	public void testData() {
//        template.opsForValue().set("hi","hello world");
		template.opsForValue().set("hi", "HELLO", Duration.ofSeconds(20));
        System.out.println("Value of key hi: "+template.opsForValue().get("hi"));
        
        try {
            Country c = countryService.getCountryById(4L);
            if(c != null) {
            	template.opsForValue().set(c.getCode(),c, Duration.ofSeconds(20));
            	System.out.println("Value of key c: "+template.opsForValue().get(c.getCode()).toString());
            }
		} catch (Exception e) {
			System.out.println(""+e.getMessage());
		}
        try {
            List<Country> listc = countryService.getAllCountries("ALL");
            if(listc != null) {
            	template.opsForValue().set("listc",listc, Duration.ofSeconds(20));
            	List<Country> listc2 = (List<Country>)template.opsForValue().get("listc");
            	System.out.println("Value of key c: "+listc2.size());
            	for(Country country : listc2) {
            		System.out.println(country.toString()+"\n");
            	}
            }
		} catch (Exception e) {
			System.out.println(""+e.getMessage());
		}
	}
}
