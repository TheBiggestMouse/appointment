package com.yunzhong.appointment.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yunzhong.appointment.entity.Patient;
import com.yunzhong.appointment.entity.Province;
import com.yunzhong.appointment.entity.SysUser;

/**
* @Title: SysRegisterService.java
* @Package com.yunzhong.appointment.system.service
* @Description: TODO(用户注册的模型层)
* @author 郎国峰
* @date 2017年10月26日 下午8:58:52
* @version V1.0
 */
public interface SysRegisterService {
	/** 
	 * @Description: TODO(查询所有的省记录)  
	 * @author 郎国峰
	 * @date 2017年10月26日 下午9:39:26 
	 */
	List<Province> listProvince();
	/**
	 * 
	 * @方法名: getUser   
	 * @描述: 根据前台传入的电话号码查询一条SysUser数据,用来做ajax前台验证用户名手机号是否重复
	 * @作者: 郎国峰
	 * @时间: 2017年10月27日 下午6:09:36
	 * @param tel 前台传入的电话号码  也是用户名
	 * @return 返回一条SysUser数据
	 */
	SysUser getUser(String tel);
	/**
	 * 
	 * @方法名: registerUser   
	 * @描述: 根据前台的注册信息注册用户,添加患者,添加用户角色表
	 * @作者: 郎国峰
	 * @时间: 2017年10月28日06:12:18
	 * @param patientTel 手机号 用户名  
	 * @param patientName 患者姓名
	 * @param patientSex 患者性别
	 * @param verificationCode 验证码
	 * @param patientUid 身份证
	 * @param patientBirth 出生日期
	 * @param password 密码
	 * @param provinceId 省id
	 * @param provinceName 省name
	 * @param cityId 市id
	 * @param cityName 市name
	 * @param areaId 区id
	 * @param areaName 区name
	 * @param nickName 昵称
	 * @param request
	 * @return
	 */
	void registerUser( String patientTel, String patientName,
			String patientSex, String verificationCode, String patientUid,
			String patientBirth, String password, String provinceId,
			String provinceName, String cityId, String cityName, String areaId,
			String areaName, HttpServletRequest request, String nickName);
	/**
	 * 
	 * @方法名: telVerify   
	 * @描述: 验证身份证号在数据库是否唯一
	 * @作者: 郎国峰
	 * @时间: 2017年10月30日08:45:55
	 * @param patientUid 传入需要验证的身份证号
	 * @return 返回是否重复   1 重复  0 不重复                                                                           
	 */
	Patient getPatient(String patientUid);
	
	
}
