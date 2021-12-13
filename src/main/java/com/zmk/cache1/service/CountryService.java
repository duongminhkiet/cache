package com.zmk.cache1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zmk.cache1.entity.Country;
import com.zmk.cache1.helper.GlobalVariable;
import com.zmk.cache1.itfx.CodeNameCountry;
import com.zmk.cache1.repo.CountryRepository;

@Service
public class CountryService {
    private void delayX() {
        try {
            long time = 5000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
	@Autowired
	CountryRepository countryRepository;
//	@Cacheable(value = "country", key = "{ #country.id, #country.code, #country.name }")
	@Cacheable(value = GlobalVariable.CACHE_ENTITY_COUNTRY, key="#country.id + #country.code + #country.name", cacheManager = "cacheManagerMemX")
    public Country getCountryByObj(Country country) {
		System.out.println("============>>>> GO IN FUNCTION getCountryByObj");
		delayX();
        return countryRepository.findByCodeAndName(country.getCode(), country.getName());
    }
	@Cacheable(value = GlobalVariable.CACHE_ENTITY_COUNTRY2, key="#country.id + #country.code + #country.name", cacheManager = "cacheManagerMemX")
    public Country getCountryByObj2(Country country) {
		System.out.println("============>>>> GO IN FUNCTION getCountryByObj2");
		delayX();
        return countryRepository.findByCodeAndName(country.getCode(), country.getName());
    }
	
	@Cacheable(value = GlobalVariable.CACHE_ENTITY_COUNTRY, key = "#id")
    public Country getCountryById(Long id) {
		System.out.println("============>>>> GO IN FUNCTION getCountryById");
		delayX();
        return countryRepository.findById(id).get();
    }
	
	///
	//Get 
	@Cacheable(value = GlobalVariable.CACHE_ENTITY_COUNTRY, key = "#id", cacheManager = "cacheManagerMemX")
    public Country getCountryByIdTestCache(Long id) {
		System.out.println("============>>>> GO IN FUNCTION getCountryById");
		delayX();
        return countryRepository.findById(id).get();
    }
	//Clear
	@CacheEvict(value = GlobalVariable.CACHE_ENTITY_COUNTRY, key = "#id", cacheManager = "cacheManagerMemX")
    public void getCountryByIdTestCacheClear(Long id) {
		System.out.println("============>>>> Cleared Cache By Id: "+id);
//		delayX();
//        return countryRepository.findById(id).get();
    }
	
	///
	
	
	
	@Cacheable(value = GlobalVariable.CACHE_ENTITY_COUNTRY, key = "#id"+"A")
    public Country getCountryById_plus(Long id) {
		System.out.println("============>>>> GO IN FUNCTION getCountryById");
		delayX();
        return countryRepository.findById(id).get();
    }
	@Cacheable(value = GlobalVariable.CACHE_ENTITY_COUNTRY2, key = "#all", sync = true)
    public List<Country> getAllCountries(String all) {
		if(all.equalsIgnoreCase("ALL")) {
			System.out.println("============>>>> GO IN FUNCTION getAllCountries");
			delayX();
	        return countryRepository.findAll();
		}
		return null;
    }
	@Cacheable(value = GlobalVariable.CACHE_ENTITY_COUNTRY, key = "#code + #name")
	public List<CodeNameCountry> getListCodeName(String code, String name){
		return countryRepository.getListCodeNameCountry(code, name);
	}
	@CacheEvict(GlobalVariable.CACHE_ENTITY_COUNTRY)
    public void clearCacheById(Long id) {
		System.out.println("============>>>> GO IN FUNCTION clearCacheById");
		countryRepository.deleteById(id);
    }
    @CacheEvict(value = GlobalVariable.CACHE_ENTITY_COUNTRY, allEntries = true)
    public void clearAllCache() {
    	System.out.println("============>>>> GO IN FUNCTION clearAllCache");
    	countryRepository.deleteAll();
    }
    @CachePut(value = GlobalVariable.CACHE_ENTITY_COUNTRY)
    public Country updateCacheCountryById(Long id) {
    	System.out.println("============>>>> GO IN FUNCTION updateCacheCountryById");
    	delayX();
    	Country c1 = new Country();
    	c1.setId(id);
    	c1.setCode("Code updated"+id);
    	c1.setName("Name Updated"+id);
    	countryRepository.save(c1);
        return c1;
    }
    public Country updateCacheCountryByIdNoCache(Long id) {
    	System.out.println("============>>>> GO IN FUNCTION updateCacheCountryById");
    	delayX();
    	Country c1 = new Country();
    	c1.setId(id);
    	c1.setCode("Code updated - No CACHE"+id);
    	c1.setName("Name Updated - No CACHE"+id);
    	countryRepository.save(c1);
        return c1;
    }
}
