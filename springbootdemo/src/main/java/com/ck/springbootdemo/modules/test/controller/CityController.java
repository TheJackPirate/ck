package com.ck.springbootdemo.modules.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ck.springbootdemo.modules.common.vo.Result;
import com.ck.springbootdemo.modules.common.vo.SearchVo;
import com.ck.springbootdemo.modules.test.entity.City;
import com.ck.springbootdemo.modules.test.service.CityService;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api")
public class CityController {
	@Autowired
	private CityService cityService;
	
	/**
	 * 127.0.0.1/api/cities/522 查询中国所有城市
	 * @param countryId
	 * @return
	 */
	@RequestMapping("/cities/{countryId}")
	public List<City> getCitiesByCountryId(@PathVariable int countryId) {
//		return cityService.getCitiesByCountryId(countryId);
		return cityService.getCitiesByCountryId2(countryId);
	}
	
	/**
	 * 127.0.0.1/api/city?cityName=Shanghai&localCityName=1111
	 * @param cityName
	 * @param localCityName
	 * @return
	 */
	@RequestMapping("/city")
	public City getCityByName(@RequestParam(required = false) String cityName, @RequestParam(required = false) String localCityName) {
		
		return cityService.getCityByName(cityName, localCityName);
	}
	
	/**
	 * 127.0.0.1/api/cities?currentPage=1&pageSize=5&countryId=522
	 * @param currentPage
	 * @param pageSize
	 * @param countryId
	 * @return
	 */
	@RequestMapping("/cities")
	public PageInfo<City> getCitiesByPage(
			@RequestParam int currentPage, 
			@RequestParam int pageSize, 
			@RequestParam int countryId) {
		return cityService.getCitiesByPage(currentPage, pageSize, countryId);
	}
	
	/**
	 * 127.0.0.1/api/cities----post
	 * @param searchVo
	 * @return
	 */
	@PostMapping(value = "/cities", consumes = "application/json")
	public PageInfo<City> getCitiesBySearchVo(@RequestBody SearchVo searchVo) {
		return cityService.getCitiesBySearchVo(searchVo);
	}
	
	/**
	 * 127.0.0.1/api/city----post
	 * @param city
	 * @return
	 */
	@PostMapping(value = "/city", consumes = "application/json")
	public Result<City> insertCity(@RequestBody City city) {
		return cityService.insertCity(city);
	}
	
	/**
	 * 127.0.0.1/api/city----put
	 * @param city
	 * @return
	 */
	@PutMapping(value = "/city", consumes = "application/x-www-form-urlencoded")
	public Result<City> updateCity(@ModelAttribute City city) {
		return cityService.updateCity(city);
	}
	
	/**
	 * 127.0.0.1/api/city/2258----delete
	 * @param cityId
	 * @return
	 */
	@DeleteMapping("/city/{cityId}")
	public Result<City> deleteCityByCityId(@PathVariable int cityId) {
		return cityService.deleteCityByCityId(cityId);
	}
}
