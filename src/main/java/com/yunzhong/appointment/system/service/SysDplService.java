package com.yunzhong.appointment.system.service;

import java.util.List;
import com.yunzhong.appointment.util.PageData;

public interface SysDplService<SysDpl> {

	List<SysDpl> listDpl(PageData pd);

	SysDpl getDplById(String id);

	void removeDpl(String[] ids);

	SysDpl getDplByDplName(String dplname);

	

}
