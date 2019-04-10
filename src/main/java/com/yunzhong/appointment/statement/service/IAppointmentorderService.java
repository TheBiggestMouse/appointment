package com.yunzhong.appointment.statement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.yunzhong.appointment.entity.Appointmentorder;
import com.yunzhong.appointment.entity.Province;

public interface IAppointmentorderService {

	List<Appointmentorder> yearApp();

	List<Appointmentorder> yearIllnessApp(String getYear);

	List<Appointmentorder> appointmentt(String getYear);

	List<Appointmentorder> queryYeartimes();



	List<Appointmentorder> queryProvinceCount(String string);

	List<Province> allProvince();
	/**
	 * ajax  按年度查询 科室 预约量
	* @Title: ajaxDeptQuery
	* @author 康磊 
	* @return
	* HashMap<String,String>
	* @throws
	* @date 2017年11月3日 上午10:09:25
	 */
	HashMap<String,ArrayList<String>> ajaxDeptQuery(String year);



}
