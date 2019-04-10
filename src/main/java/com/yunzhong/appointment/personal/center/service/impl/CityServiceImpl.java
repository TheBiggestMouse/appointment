package com.yunzhong.appointment.personal.center.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunzhong.appointment.entity.Area;
import com.yunzhong.appointment.entity.City;
import com.yunzhong.appointment.mapper.AreaMapper;
import com.yunzhong.appointment.mapper.CityMapper;
import com.yunzhong.appointment.personal.center.service.ICityService;
/***
 * 
* @ClassName: CityServiceImpl 
* @Description: 用于市的回显
* @author 赵星宇
* @date 2017年10月28日 上午9:40:59 
*
 */
@Service
public class CityServiceImpl  implements ICityService {
	
	@Autowired
	private CityMapper mapper;
	
	@Autowired
	private AreaMapper areaMapper;
	@Override
	public List<City> getquery(String provinceId) {
		
		return mapper.getQuery(provinceId);
	}

	@Override
	public List<Area> getArea(String cityId) {
		
		return areaMapper.getArea(cityId);
	}

	


}
