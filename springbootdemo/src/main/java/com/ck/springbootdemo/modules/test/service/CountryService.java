package com.ck.springbootdemo.modules.test.service;

import com.ck.springbootdemo.modules.test.entity.Country;

public interface CountryService {
	Country getCountryByCountryId(int countryId);
	Country getCountryByCountryName(String countryName);
}
