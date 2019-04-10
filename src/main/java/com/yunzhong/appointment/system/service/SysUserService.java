package com.yunzhong.appointment.system.service;

import java.util.List;

import com.yunzhong.appointment.entity.SysUser;
import com.yunzhong.appointment.util.PageData;
import com.yunzhong.appointment.util.SelectOptions;

/**
 * 
 * @className SysUserService
 * @description 用户模型层接口
 * @author 石洪刚
 * @time 2017年10月2日 下午11:31:09
 */
public interface SysUserService {
	/**
	 * 
	 * @methodName listUser
	 * @description 得到用户数据
	 * @author 石洪刚
	 * @time 2017年10月2日 下午11:44:18
	 * @param pd
	 * @return
	 */
	List<SysUser> listUser(PageData pd);
	/**
	 * 
	 * @methodName getUserById
	 * @description 根据主键得到一个用户对象
	 * @author 石洪刚
	 * @time 2017年10月4日 上午8:58:31
	 * @param id
	 * @return
	 */
	SysUser getUserById(String id);
	/**
	 * 
	 * @methodName getUserByUserName
	 * @description 根据登录名得到一个用户的对象
	 * @author 石洪刚
	 * @time 2017年10月5日 上午8:12:00
	 * @param userName
	 * @return
	 */
	SysUser getUserByUserName(String userName);
	/**
	 * 
	 * @methodName saveOrUpdateUser
	 * @description 增加或修改用户
	 * @author 石洪刚
	 * @time 2017年10月5日 上午9:53:27
	 * @param user
	 */
	void saveOrUpdateUser(SysUser user);
	/**
	 * 
	 * @methodName removeUser
	 * @description 删除用户数据
	 * @author 石洪刚
	 * @time 2017年10月5日 下午7:27:45
	 * @param ids
	 */
	void removeUser(String[] ids);
	/**
	 * 
	 * @methodName listRoleByUserId
	 * @description 根据用户主键获得用户的权限规则
	 * @author 石洪刚
	 * @time 2017年10月6日 上午8:25:02
	 * @param id
	 * @return
	 */
	List<SelectOptions> listRoleByUserId(String id);
	/**
	 * 
	 * @methodName updateAuthUser
	 * @description 修改用户角色权限
	 * @author 石洪刚
	 * @time 2017年10月6日 下午2:28:33
	 * @param roleIds
	 * @param userId
	 */
	void updateAuthUser(String roleIds, String userId);

}
