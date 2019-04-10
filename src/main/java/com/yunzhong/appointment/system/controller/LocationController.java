package com.yunzhong.appointment.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunzhong.appointment.entity.Area;
import com.yunzhong.appointment.entity.City;
import com.yunzhong.appointment.system.service.LocationService;

/**
* @Title: LocationController.java
* @Package com.yunzhong.appointment.system.controller
* @Description: TODO(省市区联动的控制器,用于页面ajax控制器的地址联动查询)
* @author 郎国峰
* @date 2017年10月27日 上午10:33:46
* @version V1.0
 */
@Controller
public class LocationController {
	/**
	 * 省市区联动模型层
	 */
	@Autowired
	private LocationService service;
	/**
	 * 
	 * @Title: listCity   
	 * @Description: TODO(根据省id查询城市)  
	 * @author 郎国峰
	 * @date 2017年10月27日 上午10:46:40 
	 * @param: @param modelMap
	 * @param: @param provinceId 省id    
	 * @return: List<City> 查询城市的list
	 */
	@RequestMapping("/listCity")
	@ResponseBody
	public List<City> listCity(ModelMap modelMap, String provinceId){
		List<City> listCity = service.listCity(provinceId); //调用listCity根据省id 查询城市
		return listCity;
	}
	/**
	 * 
	 * @Title: listArea   
	 * @Description: TODO(根据市id查询区县)  
	 * @author 郎国峰
	 * @date 2017年10月27日 下午2:05:07
	 * @param modelMap
	 * @param cityId 传入的城市id
	 * @return 以json格式返回所有的区县
	 */
	@RequestMapping("/listArea")
	@ResponseBody
	public List<Area> listArea(ModelMap modelMap, String cityId){
		List<Area> listArea = service.listArea(cityId); //调用listArea根据省id 查询城市
		return listArea;
	}
}
