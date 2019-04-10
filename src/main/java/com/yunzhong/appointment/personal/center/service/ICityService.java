package com.yunzhong.appointment.personal.center.service;

import java.util.List;

import com.yunzhong.appointment.entity.Area;
import com.yunzhong.appointment.entity.City;

public interface ICityService {

	List<City> getquery(String provinceId);

	List<Area> getArea(String cityId);

	



}
