package com.yunzhong.appointment.statement.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunzhong.appointment.entity.Appointmentorder;
import com.yunzhong.appointment.entity.Province;
import com.yunzhong.appointment.mapper.AppointmentorderMapper;
import com.yunzhong.appointment.mapper.ProvinceMapper;
import com.yunzhong.appointment.statement.service.IAppointmentorderService;



/**
 * 
* @ClassName: AppointmentorderServiceImpl 
* @Description: TODO(报表信息模型层实现类) 
* @author 康磊 
* @date 2017年11月3日 上午10:14:39 
*
 */

@Service
public class AppointmentorderServiceImpl implements IAppointmentorderService {
	@Autowired
	private AppointmentorderMapper aMapper;
	
	@Autowired
	private ProvinceMapper pMapper;
	
	/***
	 *	查询年预约各个地区人数 
	 *	2017年11月3日10:19:03
	 *	赵星宇
	 */
	@Override
	public List<Appointmentorder> queryProvinceCount(String string) {
		
		return aMapper.queryProvinceCount(string);
	}
	
	/**
	 * 查询所有省份
	 */
	@Override
	public List<Province> allProvince() {
		
		return pMapper.allProvince();
	}
	
	/**
	 * 查询年疾病统计
	 */
	@Override
	public List<Appointmentorder> yearIllnessApp(String getYear) {
	
		return aMapper.yearIllnessApp(getYear);
	}
	
	/**
	 * 查询年疾病
	 */
	@Override
	public List<Appointmentorder> yearApp() {
		
		return aMapper.yearApp();
	}

	@Override
	public List<Appointmentorder> appointmentt(String getYear) {
		
		return aMapper.appointmentt(getYear);
	}

	//查询预约订单
	@Override
	public List<Appointmentorder> queryYeartimes() {
		
		return aMapper.queryYeartimes();
	}

	/** 
	 * 康磊
	 * 2017年11月3日 10:28:07
	 */
	@Override
	public HashMap<String,ArrayList<String>> ajaxDeptQuery(String year) {
		List<Appointmentorder> list = aMapper.ajaxDeptQuery(year);
		ArrayList<String> listName = new ArrayList<String>();
		ArrayList<String> listCount = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Appointmentorder apto = list.get(i);
			String name = apto.getDepartmentName();
			String count = apto.getCountmnn();
			listName.add(name);
			listCount.add(count);
		}
		HashMap<String,ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		map.put("listName", listName);
		map.put("listCount", listCount);
		return map;
	}



	


}
