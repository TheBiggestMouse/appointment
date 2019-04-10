package com.yunzhong.appointment.personal.center.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunzhong.appointment.entity.Province;
import com.yunzhong.appointment.mapper.ProvinceMapper;
import com.yunzhong.appointment.personal.center.service.IProvinceService;
/**
 * 
* @ClassName: ProvinceServiceServiceImpl 
* @Description: 查询所有省
* @author 赵星宇
* @date 2017年10月28日 上午12:36:38 
*
 */
@Service
public class ProvinceServiceServiceImpl implements IProvinceService{
	@Autowired
	private ProvinceMapper provinceMapper;
	
	@Override
	public List<Province> getProvince() {
		
		return provinceMapper.getProvince();
	}

}
