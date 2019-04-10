package com.yunzhong.appointment.system.service;

import java.util.List;

import com.yunzhong.appointment.entity.SysMenu;

/**
 * 
 * @className SysMenuService
 * @description 菜单模型层接口
 * @author 石洪刚
 * @time 2017年10月2日 下午7:37:23
 */
public interface SysMenuService {

	/**
	 * 
	 * @methodName listAllMenu
	 * @description 得到所有菜单信息
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:41:18
	 * @return
	 */
	List<SysMenu> listAllMenu();
	/**
	 * 
	 * @methodName removeMenu
	 * @description 根据主键删除菜单
	 * @author 石洪刚
	 * @time 2017年10月2日 下午8:19:51
	 * @param id
	 */
	void removeMenu(String[] id);
	/**
	 * 
	 * @methodName getMenuById
	 * @description 根据菜单主键得到菜单，包括父菜单名称
	 * @author 石洪刚
	 * @time 2017年10月2日 下午10:35:15
	 * @param id
	 * @return
	 */
	SysMenu getMenuWithParNameById(String id);
	/**
	 * 
	 * @methodName saveOrUpdateMenu
	 * @description 增加或修改菜单
	 * @author 石洪刚
	 * @time 2017年10月2日 下午10:55:37
	 * @param menu
	 */
	void saveOrUpdateMenu(SysMenu menu);
	/**
     * 
     * @methodName queryMenuById
     * @description 根据菜单id查出需要增加子菜单的菜单
     * @author 于琦
     * @time 2017年10月27日上午10:39:00
     * @param id
     */
    SysMenu queryMenuById(String id);
    
    /**
     * 
     * @methodName save
     * @description 保存菜单信息
     * @author 于琦
     * @time 2017年10月27日上午11:16:49
     * @param menu
     */
    void save(SysMenu menu);
    
    /**
     * 
     * @methodName update
     * @description 修改菜单信息
     * @author 于琦
     * @time 2017年10月27日上午11:16:49
     * @param menu
     */
    void update(SysMenu menu);
    
    /**
     * 
     * @methodName getMenuByMenuName
     * @description 通过菜单名查询菜单
     * @author 于琦
     * @time 2017年10月27日下午1:52:58
     * @param menuName
     */
    SysMenu getMenuByMenuName(String menuName);
    
    /**
     * 
     * @methodName getMenuByUrl
     * @description 根据url查询菜单
     * @author 于琦
     * @time 2017年10月27日下午1:48:48
     * @param url
     */
    SysMenu getMenuByUrl(String url);
    /**
     * 根据父菜单id获得父菜单名称
    * @Title: getParNameByParMenuId
    * @author 康磊 
    * @param parMenuId
    * @return
    * SysMenu
    * @throws
    * @date 2017年10月30日 上午11:18:36
     */
	SysMenu getParNameByParMenuId(String parMenuId);

	/**
	 * 
	 * @methodName saveOrUpdateMenu
	 * @description 增加或修改菜单
	 * @author 康磊
	 * @time 2017年10月30日 11:17:34
	 * @param menu
	 */
	void saveOrUpdatemenu(SysMenu menu);
}
