package com.yunzhong.appointment.personal.center.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yunzhong.appointment.entity.Patient;

public interface IPersonalCenterService {



	String getRoleName(String userId);

	Patient getQuery(String userId);

	Patient getProvinceId(String userProvinceId);


	Patient getCityId(String id);

	void edit(Patient patient);


	void save(Patient patient, MultipartFile picFile);






	



}
