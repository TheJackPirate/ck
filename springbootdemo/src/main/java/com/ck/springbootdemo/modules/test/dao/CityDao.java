package com.ck.springbootdemo.modules.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ck.springbootdemo.modules.common.vo.SearchVo;
import com.ck.springbootdemo.modules.test.entity.City;

@Mapper
public interface CityDao {

	@Select("select * from m_city where country_Id = #{countryId}")
	List<City> getCitiesByCountryId2(int countryId);

	List<City> getCitiesByCountryId(int countryId);

	@Select("select * from m_city where city_name = #{cityName} or local_city_name=#{localCityName}")
	City getCityByName(String cityName, String localCityName);


	@Select("<script>" + 
	        "select * from m_city "
	        + "<where> "
	        + "<if test='keyWord != \"\" and keyWord != null'>"
	        + "and (city_name like '%${keyWord}%' or "
	        + " local_city_name like '%${keyWord}%') "
	        + "</if>"
	        + "</where>"
	        + "<choose>"
	        + "<when test='orderBy != \"\" and orderBy != null'>"
	        + "order by ${orderBy} ${sort}"
	        + "</when>"
	        + "<otherwise>"
	        + "order by city_id desc"
	        + "</otherwise>"
	        + "</choose>"
	        + "</script>")
	List<City> getCitiesBySearchVo(SearchVo searchVo);

}
