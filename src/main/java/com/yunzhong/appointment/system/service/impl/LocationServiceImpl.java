package com.yunzhong.appointment.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunzhong.appointment.entity.Area;
import com.yunzhong.appointment.entity.City;
import com.yunzhong.appointment.mapper.AreaMapper;
import com.yunzhong.appointment.mapper.CityMapper;
import com.yunzhong.appointment.system.service.LocationService;
/**
 * 
* @Title: LocationServiceImpl.java
* @Package com.yunzhong.appointment.system.service.impl
* @Description: TODO(实现省市区联动模型)
* @author 郎国峰
* @date 2017年10月27日 上午10:38:02
* @version V1.0
 */
@Service
public class LocationServiceImpl implements LocationService {
	/**
	 * 城市   mapper
	 */
	@Autowired
	private CityMapper cityMapper;
	/**
	 * 区县  mapper
	 */
	@Autowired
	private AreaMapper areaMapper;
	
	@Override
	public List<City> listCity(String provinceId) {
		// TODO Auto-generated method stub
		return cityMapper.listCityByProvinceId(provinceId);
	}

	@Override
	public List<Area> listArea(String cityId) {
		// TODO Auto-generated method stub
		return areaMapper.listAreaByCityId(cityId);
	}

}
