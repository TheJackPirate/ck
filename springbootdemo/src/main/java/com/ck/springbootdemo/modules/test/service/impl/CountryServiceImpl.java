package com.ck.springbootdemo.modules.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.springbootdemo.modules.test.dao.CountryDao;
import com.ck.springbootdemo.modules.test.entity.Country;
import com.ck.springbootdemo.modules.test.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService{

	@Autowired
	CountryDao countryDao;
	
	@Override
	public Country getCountryByCountryId(int countryId) {
		return countryDao.getCountryByCountryId(countryId);
	}

	@Override
	public Country getCountryByCountryName(String countryName) {
		return countryDao.getCountryByCountryName(countryName);
	}

}
