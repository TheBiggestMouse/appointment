package com.yunzhong.appointment.system.service;

import java.util.List;

import com.yunzhong.appointment.entity.Area;
import com.yunzhong.appointment.entity.City;

/**
 * 
* @Title: LocationService.java
* @Package com.yunzhong.appointment.system.service
* @Description: TODO(省市区联动的模型层,用于页面ajax控制器的地址联动查询)
* @author 郎国峰
* @date 2017年10月27日 上午10:36:51
* @version V1.0
 */
public interface LocationService {
	/**
	 * 
	 * @Title: listCity   
	 * @Description: TODO(根据省id查询城市)  
	 * @author 郎国峰
	 * @date 2017年10月27日 上午10:46:04 
	 * @param:  provinceId 省id    
	 * @return: List<City> 返回所有城市
	 */
	List<City> listCity(String provinceId);
	/**
	 * 
	 * @Title: listArea   
	 * @Description: TODO((根据市id查询区县)  )  
	 * @author 郎国峰
	 * @date 2017年10月27日 下午2:06:22
	 * @param cityId 传入的城市id
	 * @return 以json格式返回所有的区县
	 */
	List<Area> listArea(String cityId);

}
