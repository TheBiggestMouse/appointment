package com.yunzhong.appointment.personal.center.service.impl;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yunzhong.appointment.config.SessionConst;
import com.yunzhong.appointment.entity.Patient;
import com.yunzhong.appointment.entity.SysUser;
import com.yunzhong.appointment.mapper.PatientMapper;
import com.yunzhong.appointment.mapper.SysRoleMapper;
import com.yunzhong.appointment.personal.center.service.IPersonalCenterService;
import com.yunzhong.appointment.util.FileUtils;
/**
 * 
* @ClassName: 个人中心模型层
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 赵星宇
* @date 2017年10月27日 下午2:03:34 
*
 */
@Service
public class PersonalCenterServiceImpl implements IPersonalCenterService {

	@Autowired
	private  SysRoleMapper roleMapper;
	@Autowired 
	private PatientMapper patientMapper;
	/**
	 * 查询角色名称 用来判断是否是患者
	 */
	@Override
	public String getRoleName(String userId) {
		
		return roleMapper.getRoleName(userId);
	}
	@Override
	public Patient getQuery(String userId) {
	
		return patientMapper.getQuery(userId);
	
}
	/***
	 * 查询省id做回显
	 */
	@Override
	public Patient getProvinceId(String userProvinceId) {
		
		return patientMapper.getProvinceId(userProvinceId);
	}
	@Override
	public Patient getCityId(String id) {
		
		return patientMapper.getCityId(id);
	}
	@Override
	public void edit(Patient patient) {
		patientMapper.updateByPrimaryKey(patient);
		
	}

	@Override
	public void save(Patient patient, MultipartFile picFile) {
		
		String path = FileUtils.saveFile(picFile);
		patient.setPatientPic(path);
		System.out.println("2323323+"+path);
		System.out.println("2323323+"+patient);
		Patient ls = new Patient() ;
		ls.setPatientId(patient.getPatientId());
		ls.setPatientPic(path);
		patientMapper.updatetou(ls);
	}




	
	

	

}
