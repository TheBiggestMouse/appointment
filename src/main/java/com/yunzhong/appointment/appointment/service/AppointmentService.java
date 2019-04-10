package com.yunzhong.appointment.appointment.service;

import java.util.List;

import com.yunzhong.appointment.entity.Appointmentorder;
import com.yunzhong.appointment.entity.Department;
import com.yunzhong.appointment.entity.Departmenttype;
import com.yunzhong.appointment.entity.DoctorIllness;
import com.yunzhong.appointment.entity.Illness;
import com.yunzhong.appointment.entity.Illnessposition;
import com.yunzhong.appointment.entity.Person;

public interface AppointmentService {
	/**
	 *查询7  日内 十大热门科室
	* @Title: queryWelcomeDepartment
	* @author 康磊 
	* @return
	* List<Appointmentorder>
	* @throws
	* @date 2017年10月31日 下午7:50:44
	 */
	List<Appointmentorder> queryWelcomeDepartment();
	/**
	 * 查询所有科室类别
	* @Title: queryAllDepartmenttype
	* @author 康磊 
	* @return
	* List<Departmenttype>
	* @throws
	* @date 2017年10月31日 下午7:51:36
	 */
	List<Departmenttype> queryAllDepartmenttype();
	/**
	 * 根据科室name 查询人员(医生)
	* @Title: queryPersonBydpName
	* @author 康磊 
	* @param dpName
	* @return
	* List<Person>
	* @throws
	* @date 2017年10月31日 下午9:52:32
	 */
	List<Person> queryPersonBydpName(String dpName);
	/**
	 * 查询所有疾病类别  
	* @Title: queryAllIllnessposition
	* @author 康磊 
	* @return
	* List<Illnessposition>
	* @throws
	* @date 2017年11月1日 下午3:02:23
	 */
	List<Illnessposition> queryAllIllnessposition();
	/**
	 * 根据疾病name 查询人员(医生)
	* @Title: queryPersonBydpName
	* @author 康磊 
	* @param dpName
	* @return
	* List<Person>
	* @throws
	* @date 2017年11月1日 15:32:34
	 */
	List<Person> queryPersonByIllName(String illName);
	/**
	 * 根据疾病名查疾病
	* @Title: selectDoctorIdByIllID
	* @author 康磊 
	* @return
	* List<DoctorIllness>
	* @throws
	* @date 2017年11月2日 上午11:54:43
	 */
	Illness selectIllnessByIllName(String illName);
	

}
