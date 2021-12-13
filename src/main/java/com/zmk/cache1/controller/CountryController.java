package com.zmk.cache1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zmk.cache1.entity.Country;
import com.zmk.cache1.itfx.CodeNameCountry;
import com.zmk.cache1.repo.CountryRepository;
import com.zmk.cache1.service.CountryService;

@RestController
@RequestMapping("country")
public class CountryController {
	@Autowired
	CountryRepository countryRepository;
	@Autowired
	CountryService countryService;
	
    @GetMapping(value = "/getCountry/object", produces = "application/json")
    public Country getCountry(@RequestBody Country country) {
    	try {
    		return countryService.getCountryByObj(country);
		} catch (Exception e) {
			System.out.println(""+e.getMessage());
		}
    	return null;
//    	return countryRepository.findById(id).get();
    }
    @GetMapping(value = "/getCountry/object2", produces = "application/json")
    public Country getCountry2(@RequestBody Country country) {
    	try {
    		return countryService.getCountryByObj2(country);
		} catch (Exception e) {
			System.out.println(""+e.getMessage());
		}
    	return null;
//    	return countryRepository.findById(id).get();
    }
    @GetMapping(value = "/getCountry/{id}", produces = "application/json")
    public Country getCountry(@PathVariable Long id) {
    	try {
    		return countryService.getCountryById(id);
		} catch (Exception e) {
			System.out.println(""+e.getMessage());
		}
    	return null;
//    	return countryRepository.findById(id).get();
    }
    @GetMapping(value = "/getCountryA/{id}", produces = "application/json")
    public Country getCountry_plus(@PathVariable Long id) {
    	try {
    		return countryService.getCountryById_plus(id);
		} catch (Exception e) {
			System.out.println(""+e.getMessage());
		}
    	return null;
//    	return countryRepository.findById(id).get();
    }
    
    @GetMapping(value = "/getCountryTestCache/{id}/{clear}", produces = "application/json")
    public Country getCountryTestCache(@PathVariable Long id,@PathVariable Boolean clear) {
    	try {
    		if(clear) {
    			countryService.getCountryByIdTestCacheClear(id);
    			return countryService.getCountryByIdTestCache(id);
    		}else {
    			return countryService.getCountryByIdTestCache(id);
    		}
    		
		} catch (Exception e) {
			System.out.println(""+e.getMessage());
		}
    	return null;
//    	return countryRepository.findById(id).get();
    }
    
    @GetMapping(value = "/getAll/{all}", produces = "application/json")
    public List<Country> getAllCountries(@PathVariable String all) {
    	return countryService.getAllCountries(all);
    }
    @GetMapping(value = "/deleteById/{id}", produces = "application/json")
    public String deleteCountryById(@PathVariable Long id) {
    	 countryService.clearCacheById(id);
    	 return "DONE";
    }
    @GetMapping(value = "/deleteAll", produces = "application/json")
    public String deleteAllCountries(@PathVariable Long id) {
    	 countryService.clearCacheById(id);
    	 return "DONE";
    }
    @GetMapping(value = "/updateCountryById/{id}", produces = "application/json")
    public String updateCountryById(@PathVariable Long id) {
    	 countryService.updateCacheCountryById(id);
    	 return "DONE";
    }
    @GetMapping(value = "/updateCountryByIdNoCache/{id}", produces = "application/json")
    public String updateCountryByIdNoCache(@PathVariable Long id) {
    	 countryService.updateCacheCountryByIdNoCache(id);
    	 return "DONE";
    }
    @GetMapping(value = "/getListCountry/{code}/{name}", produces = "application/json")
    public List<CodeNameCountry> getCountry(@PathVariable String code, @PathVariable String name) {
    	try {
    		return countryService.getListCodeName(code, name);
		} catch (Exception e) {
			System.out.println(""+e.getMessage());
		}
    	return null;
//    	return countryRepository.findById(id).get();
    }
}
