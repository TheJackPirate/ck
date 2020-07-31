package com.ck.springbootdemo.modules.test.service;

import java.util.List;

import com.ck.springbootdemo.modules.common.vo.Result;
import com.ck.springbootdemo.modules.common.vo.SearchVo;
import com.ck.springbootdemo.modules.test.entity.City;
import com.github.pagehelper.PageInfo;

public interface CityService {
	List<City> getCitiesByCountryId(int countryId);
	
	List<City> getCitiesByCountryId2(int countryId);
	
	City getCityByName(String cityName, String localCityName);
	
	PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId);
	
	PageInfo<City> getCitiesBySearchVo(SearchVo searchVo);
	
	Result<City> insertCity(City city);
}
