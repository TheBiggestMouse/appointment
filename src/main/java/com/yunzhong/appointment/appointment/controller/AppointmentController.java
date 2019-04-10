package com.yunzhong.appointment.appointment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunzhong.appointment.appointment.service.AppointmentService;
import com.yunzhong.appointment.entity.Appointmentorder;
import com.yunzhong.appointment.entity.Department;
import com.yunzhong.appointment.entity.Departmenttype;
import com.yunzhong.appointment.entity.DoctorIllness;
import com.yunzhong.appointment.entity.Illness;
import com.yunzhong.appointment.entity.Illnessposition;
import com.yunzhong.appointment.entity.Person;
import com.yunzhong.appointment.system.service.SysMenuService;
/**
 * 
* @ClassName: AppointmentController 
* @Description: 预约控制器
* @author 康磊 
* @date 2017年10月31日 下午1:47:53 
*
 */
@Controller
@RequestMapping("appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	/**
	 * 按科室挂号 查询热门科室 查询科室
	* @Title: showMenu
	* @author 康磊 
	* @param mm
	* @return
	* String
	* @throws
	* @date 2017年10月31日 下午7:49:28
	 */
	@RequestMapping("listDept")
	public String showMenu(ModelMap mm){
		List<Appointmentorder> listWelcome = appointmentService.queryWelcomeDepartment();
		List<Departmenttype> listPDept = appointmentService.queryAllDepartmenttype();
		mm.put("listWelcome", listWelcome);
		mm.put("listPDept", listPDept);
		return"appointment/guahao_department";
	}
	/**
	 * 按科室挂号   根据科室name查询人员(医生)
	* @Title: queryPersonBydpName
	* @author 康磊 
	* @param mm
	* @param dpName
	* @return
	* String
	* @throws
	* @date 2017年10月31日 下午10:09:05
	 */
	@RequestMapping("queryPerson")
	public String queryPersonBydpName(ModelMap mm,String dpName){
		List<Person>listPerson = appointmentService.queryPersonBydpName(dpName);
		mm.put("listPerson", listPerson);
		mm.put("dpName", dpName);
		return "appointment/yuyue_keshi";
	}
	/**
	 * 按疾病挂号 热门疾病  查询疾病
	* @Title: showMenu
	* @author 康磊 
	* @param mm
	* @return
	* String
	* @throws
	* @date 2017年11月1日 12:59:39
	 */
	@RequestMapping("listIllness")
	public String listIllness(ModelMap mm){
		List<Illnessposition> listIlltp = appointmentService.queryAllIllnessposition();
		mm.put("listIlltp", listIlltp);
		return"appointment/guahao_illness";
	}
	/**
	 * 按疾病挂号  点击疾病 跳转治疗该疾病医生
	* @Title: queryPersonBydpName
	* @author 康磊 
	* @param mm
	* @param dpName
	* @return
	* String
	* @throws
	* @date 2017年11月1日 下午3:28:51
	 */
	@RequestMapping("queryPersonByIllName")
	public String queryPersonByIllName(ModelMap mm,String illName){
		List<Person>listPerson = appointmentService.queryPersonByIllName(illName);
		Illness illness =appointmentService.selectIllnessByIllName(illName);
		System.out.println(illness);
		mm.put("illness", illness);
		mm.put("listPerson", listPerson);
		return "appointment/yuyue_illness";
	}
	
}
