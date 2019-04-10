package com.yunzhong.appointment.system.service;

import java.util.List;

import com.yunzhong.appointment.entity.SysRole;
import com.yunzhong.appointment.util.PageData;
import com.yunzhong.appointment.util.SelectOptions;

public interface SysRoleService {
	/**
	* @Title: listRole 
	* @author 康磊 
	* @Description: TODO(角色查询) 
	* @param @param pd
	* @param @return    
	* @return List<SysRole>  
	* @throws 
	* @date 2017年10月27日 上午11:09:11
	 */
	List<SysRole> listRole(PageData pd);
	/**
	* @Title: getroleById 
	* @author 康磊 
	* @Description: TODO(根据主键查询一条数据) 
	* @param @param id
	* @param @return    
	* @return SysRole  
	* @throws 
	* @date 2017年10月27日 上午11:12:48
	 */
	SysRole getroleById(String id);
	/**
	* @Title: saveOrUpdateRole
	* @author 康磊 
	* @Description: TODO(添加修改保存)
	* @param role
	* void
	* @throws
	* @date 2017年10月27日 上午11:14:16
	 */
	void saveOrUpdateRole(SysRole role);
	/**
	 * 
	* @Title: removeOneRole
	* @author 康磊 
	* @Description: TODO(删除一条或多条数据)
	* @param ids
	* void
	* @throws
	* @date 2017年10月27日 上午11:14:37
	 */
	void removeOneRole(String[] ids);
	/**
	 * 根据角色主键 获得  菜单 工具类 对象list      list中 菜单工具类 分为 可以菜单与 不可用菜单
	* @Title: listMenuByRoleId
	* @author 康磊 
	* @param id
	* @return
	* List<SelectOptions>
	* @throws
	* @date 2017年10月27日 下午5:06:40
	 */
	List<SelectOptions> listMenuByRoleId(String id);
	/**
	 * 角色授权菜单修改保存
	* @Title: updateAuthRole
	* @author 康磊 
	* @param menuIds
	* @param roleId
	* void
	* @throws
	* @date 2017年10月27日 下午7:00:18
	 */
	void updateAuthRole(String menuIds, String roleId);
	/**
	 * 根据roleName查询 
	* @Title: verifyName
	* @author 康磊 
	* @param roleName
	* void
	 * @return 
	* @throws
	* @date 2017年10月29日 下午9:44:17
	 */
	String verifyName(String roleName);
	
	
	void roleMenu(String roleId, String menuIds);
	

}
