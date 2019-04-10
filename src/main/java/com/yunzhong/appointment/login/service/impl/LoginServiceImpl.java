package com.yunzhong.appointment.login.service.impl;

import com.yunzhong.appointment.entity.SysMenu;
import com.yunzhong.appointment.entity.SysUser;
import com.yunzhong.appointment.login.service.LoginService;
import com.yunzhong.appointment.mapper.SysMenuMapper;
import com.yunzhong.appointment.mapper.SysRoleMapper;
import com.yunzhong.appointment.mapper.SysUserMapper;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @className LoginServiceImpl
 * @description 登录模型层
 * @author 石洪刚
 * @time 2017年10月2日 下午7:26:59
 */
@Service
public class LoginServiceImpl implements LoginService{
    @Resource
    private SysUserMapper userMapper;
    @Resource
    private SysRoleMapper roleMapper;
    @Resource
    private SysMenuMapper menuMapper;

    /**
     * @description 根据用户名得到用户对象
     * @author 石洪刚
     * @time 2017年8月10日11:23:16
     * @param username
     * @return
     */
    public SysUser getUserByName(String username){
        return userMapper.getUserByName(username);
    }
    
    /**
     * @description 根据角色主键得到角色名称
     * @author 石洪刚
     * @time 2017年8月10日11:25:36
     * @param roleId
     * @return
     */
    public String getRoleName(String roleId) {
       return roleMapper.getRoleNameById(roleId);
    }

    /**
     * @description 根据用户主键得到用户所有角色名称
     * @author 石洪刚
     * @time 2017年8月10日11:26:34
     * @param userId
     * @return
     */
	public Set<String> listRoleNamesByUserId(String userId) {
		return roleMapper.listRoleNamesByUserId(userId);
	}

	/**
	 * @description 根据用户主键得到用户所有合法请求
	 * @author 石洪刚
	 * @time 2017年8月10日11:27:30
	 * @param userId
	 * @return
	 */
	public Set<String> listPermissionsByUserId(String userId) {
		return menuMapper.listPermissionsByUserId(userId);
	}
	
	
	/**
	 * @description 根据用户主键查询用户菜单
	 * @author 石洪刚
	 * @time 2017年8月10日13:43:59
	 * @param userId
	 * @return
	 */
	public List listMenuByUserId(String userId) {
		List<SysMenu> topMenuList = menuMapper.listTopMenuByUserId(userId);
		for (int i = 0; i < topMenuList.size(); i++) {
			topMenuList.set(i, this.recursiveMenuByPar(topMenuList.get(i),userId));
		}
		return topMenuList;
	}
	
	/**
	 * @description 根据父菜单查询所有子菜单
	 * @author 石洪刚
	 * @time 2017年8月10日13:49:29
	 * @param menu
	 * @return
	 */
	private SysMenu recursiveMenuByPar(SysMenu menu,String userId) {
		Map mp = new HashMap();
		mp.put("menuId", menu.getMenuId());
		mp.put("userId", userId);
		List<SysMenu> childMenus = menuMapper.listMenuByParIdAndUserId(mp);
		List<SysMenu> childrenMenuList = new ArrayList<SysMenu>();
		for (SysMenu sysMenu : childMenus) {
			SysMenu m = recursiveMenuByPar(sysMenu,userId);
			childrenMenuList.add(m);
		}
		menu.setChildrenMenuList(childrenMenuList);
		return menu;
	}
	/**
	 * @description 得到上次的请求路径
	 * @author 石洪刚
	 * @time 2017年8月29日16:21:54
	 * @param request
	 * @return
	 */
	public String getLastUrl(HttpServletRequest request) {
		if(WebUtils.getSavedRequest(request)!=null){
			return WebUtils.getSavedRequest(request).getRequestUrl();
		}
		return null;
	}
	/**
	 * @description 得到个人中心菜单
	 * @author 石洪刚
	 * @time 2017年8月31日10:02:20
	 * @return
	 */
	public SysMenu getPersonalMenu() {
		SysMenu menu = menuMapper.getPersonalMenu();
		List<SysMenu> childMenuList = menuMapper.listMenuByPar(menu);
		if(SecurityUtils.getSubject().hasRole("patient")){
			menu.setChildrenMenuList(childMenuList);
		}else{
			//如果不是患者角色，则在菜单中排除未付款订单及付款订单
			List<SysMenu> temList = new ArrayList<SysMenu>();
			for (int i = 0; i < childMenuList.size(); i++) {
				if(!"未付款订单".equals(childMenuList.get(i).getMenuName())&&!"订单详情".equals(childMenuList.get(i).getMenuName())){
					temList.add(childMenuList.get(i));
				}
			}
			menu.setChildrenMenuList(temList);
		}
		return menu;
	}
}
