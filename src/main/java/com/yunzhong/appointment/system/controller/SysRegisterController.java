package com.yunzhong.appointment.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;





import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yunzhong.appointment.entity.Patient;
import com.yunzhong.appointment.entity.Province;
import com.yunzhong.appointment.entity.SysUser;
import com.yunzhong.appointment.system.service.SysRegisterService;

/**
 * 
* @Title: SysRegisterController.java
* @Package com.yunzhong.appointment.system.controller
* @Description: TODO(注册页面的控制器)
* @author 郎国峰
* @date 2017年10月26日 下午7:03:39
* @version V1.0
 */
@Controller
public class SysRegisterController {
	/**
	 * 注册页面的模型层接口
	 */
	@Autowired
	private SysRegisterService service;
	/**
	 * 
	 * @Title: register   
	 * @Description: TODO(跳转到注册页)  
	 * @author 郎国峰
	 * @date 2017年10月26日 下午9:37:23 
	 * @param: @return      
	 * @return: String      
	 */
	@RequestMapping("/register")
	public String register(ModelMap modelMap){
		List<Province> provinceList = service.listProvince(); //调用模型层查询所有省信息
		modelMap.put("provinceList", provinceList); //将省信息用modelMap传给页面
		return "register";
	}
	/**
	 * 
	 * @方法名: telVerify   
	 * @描述: 验证手机号在数据库是否唯一
	 * @作者: 郎国峰
	 * @时间: 2017年10月27日 下午4:34:59
	 * @param tel 传入需要验证的手机号
	 * @return 返回是否重复   1 重复  0 不重复                                                                         
	 */
	@RequestMapping("/telVerify")
	@ResponseBody
	public String telVerify(String tel){
		SysUser user = null; //声明一个SysUser 变量
		user = service.getUser(tel); //根据tel 查询SysUser
		if (user==null) { //如果user是null,表示电话可用,否则表示电话不可用
			return "0";
		}else{
			return "1";
		}
	}
	/**
	 * 
	 * @方法名: registerUser   
	 * @描述: 根据前台的注册信息注册用户,添加患者,添加用户角色表
	 * @作者: 郎国峰
	 * @时间: 2017年10月28日 上午6:07:23
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
	@RequestMapping("/registerUser")
	public String registerUser(String patientTel, String patientName, String patientSex, String verificationCode,
			String patientUid, String patientBirth, String password, String provinceId, String provinceName, String cityId, 
			String cityName, String areaId, String areaName,  HttpServletRequest request,String nickName){
		service.registerUser(patientTel,  patientName,  patientSex,  verificationCode,
				 patientUid,  patientBirth,  password,  provinceId,  provinceName,  cityId, 
				 cityName,  areaId,  areaName,   request, nickName);
//		System.out.println("\t patientName:"+patientName+"\t patientTel:"+patientTel+"\t patientSex:"+patientSex+"\t verificationCode:"+verificationCode+
//				"\t patientUid:"+patientUid+"\t patientBirth:"+patientBirth+"\t password:"+password+"\t provinceId:"+provinceId+
//				"\t provinceName:"+provinceName+"\t cityId:"+cityId+"\t cityName:"+cityName+"\t areaId:"+areaId+"\t areaName:"+areaName);
		return "redirect:/login";
	}
	/**
	 * 
	 * @方法名: telVerify   
	 * @描述: 验证身份证号在数据库是否唯一
	 * @作者: 郎国峰
	 * @时间: 2017年10月30日08:43:11
	 * @return 返回是否重复   1 重复  0 不重复                                                                           
	 * @param patientUid 传入需要验证的身份证号
	 */
	@RequestMapping("/patientUidVerify")
	@ResponseBody
	public String patientUidVerify(String patientUid){
		System.out.println("身份验证======================入参:"+patientUid);
		Patient patient = null; //声明一个SysUser 变量
		patient = service.getPatient(patientUid); //根据patientUid 查询Patient
		System.out.println("=======================patient: "+patient);
		
		if (patient==null) { //如果patient是null,表示身份证号可用,否则表示身份证号不可用
			return "0";
		}else{
			return "1";
		}
	}
	
	
	
	
}
