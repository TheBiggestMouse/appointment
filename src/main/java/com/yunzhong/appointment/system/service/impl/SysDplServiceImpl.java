package com.yunzhong.appointment.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunzhong.appointment.entity.SysRole;
import com.yunzhong.appointment.mapper.DepartmenttypeMapper;
import com.yunzhong.appointment.mapper.SysRoleMapper;
import com.yunzhong.appointment.mapper.SysRoleMenuMapper;
import com.yunzhong.appointment.mapper.SysUserRoleMapper;
import com.yunzhong.appointment.system.service.SysDplService;
import com.yunzhong.appointment.util.PageData;
/**
 * 
* @ClassName: SysDplServiceImpl 
* @Description: 部门实现类
* @author 于琦 
* @date 2017年10月27日 下午2:03:13 
*
 */
@Service
public class SysDplServiceImpl implements SysDplService  {
	
	@Autowired
	private DepartmenttypeMapper mapper;

	@Override
	public List listDpl(PageData pd) {
		pd.setPageData(pd);
		return mapper.listdpl(pd);
	}

	@Override
	public Object getDplById(String id) {
		
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Object getDplByDplName(String dplname) {
		
		return mapper.selectByName(dplname);
	}

	@Override
	public void removeDpl(String[] ids) {
		return;
		
	}

}
