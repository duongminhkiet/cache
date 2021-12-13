package com.zmk.cache1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zmk.cache1.entity.Country;
import com.zmk.cache1.itfx.CodeNameCountry;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long>{
	Country findByCodeAndName(String code, String name);
	
	@Query(value = "SELECT  code, name FROM Country WHERE code like :code% and name like :name%", nativeQuery = true)
	List<CodeNameCountry> getListCodeNameCountry(String code, String name);
}
