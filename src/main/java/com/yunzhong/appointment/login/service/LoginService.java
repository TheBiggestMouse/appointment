package com.yunzhong.appointment.login.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunzhong.appointment.entity.SysMenu;
import com.yunzhong.appointment.entity.SysUser;
/**
 * 
 * @className LoginService
 * @description 登录模型层接口
 * @author 石洪刚
 * @time 2017年10月2日 下午7:25:29
 */
public interface LoginService {

	/**
	 * 
	 * @methodName getUserByName
	 * @description 根据用户名得到用户对象
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:25:39
	 * @param username
	 * @return
	 */
	SysUser getUserByName(String username);

	/**
	 * 
	 * @methodName getRoleName
	 * @description 根据角色主键得到角色名称
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:25:49
	 * @param roleId
	 * @return
	 */
	String getRoleName(String roleId);

	/**
	 * 
	 * @methodName listRoleNamesByUserId
	 * @description 根据用户主键得到用户所有角色名称
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:26:02
	 * @param userId
	 * @return
	 */
	Set<String> listRoleNamesByUserId(String userId);

	/**
	 * 
	 * @methodName listPermissionsByUserId
	 * @description 根据用户主键得到用户所有合法请求
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:26:13
	 * @param userId
	 * @return
	 */
	Set<String> listPermissionsByUserId(String userId);

	/**
	 * 
	 * @methodName listMenuByUserId
	 * @description 根据用户主键查询用户菜单
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:26:22
	 * @param userId
	 * @return
	 */
	List listMenuByUserId(String userId);

	/**
	 * 
	 * @methodName getLastUrl
	 * @description 得到上次的请求路径
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:26:34
	 * @param request
	 * @return
	 */
	String getLastUrl(HttpServletRequest request);

	/**
	 * 
	 * @methodName getPersonalMenu
	 * @description 得到个人中心菜单
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:26:45
	 * @return
	 */
	SysMenu getPersonalMenu();

}