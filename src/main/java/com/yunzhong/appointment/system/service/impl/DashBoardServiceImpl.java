package com.yunzhong.appointment.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunzhong.appointment.config.SessionConst;
import com.yunzhong.appointment.entity.SysMenu;
import com.yunzhong.appointment.mapper.SysMenuMapper;
import com.yunzhong.appointment.system.service.DashBoardService;

/**
 * @description 首页模型层
 * @author 石洪刚
 * @time 2017年8月29日17:02:29
 */
@Service
public class DashBoardServiceImpl implements DashBoardService{

	@Autowired
	private SysMenuMapper menuMapper;
	/**
	 * @description 设置游客菜单
	 * @author 石洪刚
	 * @time 2017年8月29日20:09:53
	 */
	public void setGuestMenu() {
		//如果session中没有菜单信息并且用户没登录
		Subject sub = SecurityUtils.getSubject();
		if(sub.getSession().getAttribute(SessionConst.SESSION_MENULIST)==null && !sub.isAuthenticated()){
			List<SysMenu> menuList = menuMapper.listGuestTopMenu();
			for (int i = 0; i < menuList.size(); i++) {
				menuList.set(i, this.recursiveMenuByPar(menuList.get(i)));
			}
			sub.getSession().setAttribute(SessionConst.SESSION_MENULIST, menuList);
		}
	}
	/**
	 * @description 根据上级菜单得到游客所有下级菜单
	 * @author 石洪刚
	 * @time 2017年8月29日20:46:11
	 * @param menu
	 * @return
	 */
	
	private SysMenu recursiveMenuByPar(SysMenu menu) {
		List<SysMenu> childMenus = menuMapper.listGuestMenuByParId(menu);
		List<SysMenu> childrenMenuList = new ArrayList<SysMenu>();
		for (SysMenu sysMenu : childMenus) {
			SysMenu m = recursiveMenuByPar(sysMenu);
			childrenMenuList.add(m);
		}
		menu.setChildrenMenuList(childrenMenuList);
		return menu;
	}
	
}
