package com.yunzhong.appointment.appointment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunzhong.appointment.entity.Appointmentorder;
import com.yunzhong.appointment.entity.Department;
import com.yunzhong.appointment.entity.Departmenttype;
import com.yunzhong.appointment.entity.DoctorIllness;
import com.yunzhong.appointment.entity.Illness;
import com.yunzhong.appointment.entity.Illnessposition;
import com.yunzhong.appointment.entity.Person;
import com.yunzhong.appointment.appointment.service.AppointmentService;
import com.yunzhong.appointment.mapper.AppointmentorderMapper;
import com.yunzhong.appointment.mapper.DepartmentMapper;
import com.yunzhong.appointment.mapper.DepartmenttypeMapper;
import com.yunzhong.appointment.mapper.DoctorIllnessMapper;
import com.yunzhong.appointment.mapper.IllnessMapper;
import com.yunzhong.appointment.mapper.IllnesspositionMapper;
import com.yunzhong.appointment.mapper.PersonMapper;
import com.yunzhong.appointment.mapper.SysMenuMapper;
@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private AppointmentorderMapper appointmentorderMapper;
	@Autowired
	private DepartmenttypeMapper departmenttypeMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private PersonMapper personMapper;
	@Autowired
	private IllnesspositionMapper illnesspositionMapper;
	@Autowired
	private IllnessMapper illnessMapper;
	@Autowired
	private DoctorIllnessMapper doctorIllnessMapper;
	/**
	 * 查询7日内 十大热门科室
	 * 康磊
	 * 2017年10月31日 19:53:01
	 */
	@Override
	public List<Appointmentorder> queryWelcomeDepartment() {
		List<Appointmentorder> list = appointmentorderMapper.selectWelcomeDepartment();
		return list ;
	}
	/**
	 * 查询所有科室类别
	 * 康磊
	 * 2017年10月31日 19:53:47
	 */
	@Override
	public List<Departmenttype> queryAllDepartmenttype() {
		List<Departmenttype>list = departmenttypeMapper.qureyAll();
			for (Departmenttype dptt : list) {
				List<Department> dpList = new ArrayList<Department>();
				dpList  = departmentMapper.queryAll(dptt.getDplId());
				dptt.setDpList(dpList);
			}
		return list;
	}
	/**
	 * 根据科室name 查询人员(医生)
	 * 康磊
	 * 2017年10月31日 22:07:32
	 */
	@Override
	public List<Person> queryPersonBydpName(String dpName) {
		List<Person> list = personMapper.queryPersonBydpName(dpName);
		
		return list;
	}
	
	/**
	 * 查询所有疾病类别
	 * 康磊
	 * 2017年10月31日 19:53:47
	 */
	@Override
	public List<Illnessposition> queryAllIllnessposition() {
		List<Illnessposition>list = illnesspositionMapper.qureyAll();
		for (Illnessposition illpt : list) {
			List<Illness>  illList = new ArrayList<Illness>();
			illList=illnessMapper.queryAllByIlltpId(illpt.getIlltpId());
			illpt.setIllList(illList);
		}
		return list;
	}
	/**
	 * 根据疾病名 查 医生
	 */
	@Override
	public List<Person> queryPersonByIllName(String illName) {
		// 疾病名 查疾病id
		Illness illId = illnessMapper.selectIllnessByIllName(illName);
		//疾病id 查   医生id
		if(illId!=null){
			//根据根据疾病id 得到 医生
		List<DoctorIllness> dtillList = doctorIllnessMapper.selectDoctorIdByIllID(illId.getIllId());
		List<Person> list = new ArrayList<Person>();
		for (DoctorIllness dtill : dtillList) {
			Person person =personMapper.selectPersonById(dtill.getDoctorId());
			list.add(person);
		}
		return list;
		}else{
			return null;}
	}
	
/*	public void asd(String illName){
		//根据疾病名 得到 相应的疾病 
		List<Illness> illList = illnessMapper.selectIllnessByIllName(illName);
		
		Map<Illness,List<Person>> map = new HashMap<Illness, List<Person>>() ; 
			for (Illness ill : illList) {
				// 疾病id 查医生
				List<DoctorIllness> dtillList = doctorIllnessMapper.selectDoctorIdByIllID(ill.getIllId());
				//装医生的list
				List<Person> pslist = new ArrayList<Person>();
				for (DoctorIllness dtill : dtillList) {
					Person person =personMapper.selectPersonById(dtill.getDoctorId());
					pslist.add(person);
				}
			map.put(ill, pslist);
			}
	}*/
	/**
	 * 根据疾病名查疾病
	 */
	@Override
	public Illness selectIllnessByIllName(String illName) {
		Illness illId = illnessMapper.selectIllnessByIllName(illName);
		return illId;
	}
	
	

}
