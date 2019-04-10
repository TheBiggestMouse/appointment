package com.yunzhong.appointment.system.service.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yunzhong.appointment.config.Const;
import com.yunzhong.appointment.entity.Patient;
import com.yunzhong.appointment.entity.Province;
import com.yunzhong.appointment.entity.SysUser;
import com.yunzhong.appointment.entity.SysUserRole;
import com.yunzhong.appointment.mapper.PatientMapper;
import com.yunzhong.appointment.mapper.ProvinceMapper;
import com.yunzhong.appointment.mapper.SysUserMapper;
import com.yunzhong.appointment.mapper.SysUserRoleMapper;
import com.yunzhong.appointment.system.service.SysRegisterService;
import com.yunzhong.appointment.util.FileUtils;
/**
 * 
* @Title: SysRegisterServiceImpl.java
* @Package com.yunzhong.appointment.system.service.impl
* @Description: TODO(注册页面的模型层实现)
* @author 郎国峰
* @date 2017年10月27日 上午8:56:29
* @version V1.0
 */
@Service
public class SysRegisterServiceImpl implements SysRegisterService {
	/**
	 * 省 mapper
	 */
	@Autowired
	private ProvinceMapper provinceMapper;
	/**
	 * 用户 mapper
	 */
	@Autowired
	private SysUserMapper userMapper;
	/**
	 * 患者 mapper
	 */
	@Autowired
	private PatientMapper patientMapper;
	/**
	 * 用户角色 mapper
	 */
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	

	@Override
	public List<Province> listProvince() {
		// TODO Auto-generated method stub
		return provinceMapper.listProvince();
	}

	@Override
	public SysUser getUser(String tel) {
		// TODO Auto-generated method stub
		return userMapper.getUser(tel);
	}

	@Override
	public void registerUser( String patientTel,
			String patientName, String patientSex, String verificationCode,
			String patientUid, String patientBirth, String password,
			String provinceId, String provinceName, String cityId,
			String cityName, String areaId, String areaName,
			HttpServletRequest request, String nickName) {
		// TODO Auto-generated method stub
		//添加一条用户信息
		SysUser user = new SysUser();//创建用户实体
		String userId = UUID.randomUUID().toString();//生成用户id
		user.setUserId(userId);//添加用户主键
		user.setUserName(patientTel);//添加用户名
		String pass = new SimpleHash("SHA-1",password,password+Const.SALT).toString();
		user.setUserPass(pass);//添加密码
		user.setUserNickname(nickName);//添加昵称
		user.setUserState("A");//状态设置为A
		userMapper.insert(user);//调用mapper,存储数据库
		//添加一条患者信息
		Patient patient = new Patient();//创建患者实体
		patient.setPatientId(UUID.randomUUID().toString());//设置患者主键
		patient.setUserId(userId);//添加用户id
		patient.setUserName(patientTel);//添加用户名
		patient.setPatientName(patientName);//添加患者名
		patient.setPatientTel(patientTel);//添加手机号
		patient.setPatientSex(patientSex);//添加患者性别
		patient.setPatientUid(patientUid);//添加身份证
		try {//添加出生日期
			patient.setPatientBirth(new SimpleDateFormat("yyyy-mm-dd").parse(patientBirth));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		patient.setProvinceId(provinceId);//添加居住地省id
		patient.setProvinceName(provinceName);//添加居住地省name
		patient.setCityId(cityId);//添加居住地市id
		patient.setCityName(cityName);//添加居住地市name
		patient.setAreaId(areaId);//添加居住地区id
		patient.setAreaName(areaName);//添加居住地区name
		patientMapper.insert(patient);//调用mapper,存储数据库
		//添加一条用户角色信息
		SysUserRole userRole = new SysUserRole();//创建用户角色实体
		userRole.setUrId(UUID.randomUUID().toString());//添加用户角色主键
		userRole.setUserId(userId);//添加用户id
		userRole.setRoleId("8a150f63-2d29-4c15-bf2f-8b6e5bfe589b");//添加角色id   角色id为患者id
		sysUserRoleMapper.insert(userRole);//调用mapper,存储数据库
	}

	@Override
	public Patient getPatient(String patientUid) {
		// TODO Auto-generated method stub
		return patientMapper.getPatient(patientUid);
	}
	


}
