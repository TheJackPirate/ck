package com.ck.springbootdemo.modules.test.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ck.springbootdemo.modules.common.vo.Result;
import com.ck.springbootdemo.modules.common.vo.Result.ResultStatus;
import com.ck.springbootdemo.modules.common.vo.SearchVo;
import com.ck.springbootdemo.modules.test.dao.CityDao;
import com.ck.springbootdemo.modules.test.entity.City;
import com.ck.springbootdemo.modules.test.service.CityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class CityServiceImpl implements CityService{

	@Autowired
	private CityDao cityDao;
	
	@Override
	public List<City> getCitiesByCountryId(int countryId) {
		return Optional.ofNullable(cityDao.getCitiesByCountryId(countryId))
						.orElse(Collections.emptyList());
	}

	@Override
	public List<City> getCitiesByCountryId2(int countryId) {
		return Optional.ofNullable(cityDao.getCitiesByCountryId2(countryId))
				.orElse(Collections.emptyList());
	}

	@Override
	public City getCityByName(String cityName, String localCityName) {
		// TODO Auto-generated method stub
		return cityDao.getCityByName(cityName, localCityName);
	}

	@Override
	public PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId) {
		PageHelper.startPage(currentPage, pageSize);
		return new PageInfo<City>(
				Optional.ofNullable(cityDao.getCitiesByCountryId(countryId))
				.orElse(Collections.emptyList()));
	}

	@Override
	public PageInfo<City> getCitiesBySearchVo(SearchVo searchVo) {
		searchVo.initSearchVo();
		PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
		return new PageInfo<City>(
				Optional.ofNullable(cityDao.getCitiesBySearchVo(searchVo))
				.orElse(Collections.emptyList()));
	}

	@Override
	public Result<City> insertCity(City city) {
		cityDao.insertCity(city);
		return new Result<City>(ResultStatus.SUCCESS.status, "", city);
	}

	@Override
	@Transactional
	public Result<City> updateCity(City city) {
		cityDao.updateCity(city);
		return new Result<City>(ResultStatus.SUCCESS.status, "", city);
	}

	@Override
	public Result<City> deleteCityByCityId(int cityId) {
		cityDao.deleteCityByCityId(cityId);
		return new Result<City>(ResultStatus.SUCCESS.status, "");
	}

}
