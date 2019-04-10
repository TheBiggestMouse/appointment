package com.yunzhong.appointment.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunzhong.appointment.entity.SysMenu;
import com.yunzhong.appointment.mapper.SysMenuMapper;
import com.yunzhong.appointment.mapper.SysRoleMenuMapper;
import com.yunzhong.appointment.system.service.SysMenuService;

/**
 * 
 * @className SysMenuServiceImpl
 * @description 菜单模型层实现类
 * @author 石洪刚
 * @time 2017年10月2日 下午7:38:02
 */
@Service
public class SysMenuServiceImpl implements SysMenuService{

	@Autowired
	private SysMenuMapper menuMapper;
	@Autowired
	private SysRoleMenuMapper rolemenuMapper;
	@Override
	public List<SysMenu> listAllMenu() {
		//得到所有一级菜单
		List<SysMenu> topList = menuMapper.listTopMenu();
		List<SysMenu> rvList = new ArrayList<SysMenu>();
		for (SysMenu sysMenu : topList) {
			SysMenu rv = this.recursiveMenuByTopMenu(sysMenu);
			rvList.add(rv);
		}
		return rvList;
	}
	/**
	 * 
	 * @methodName recursiveMenuByTopMenu
	 * @description 递归得到一个菜单下的所有子菜单
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:47:28
	 * @param menu
	 * @return
	 */
	private SysMenu recursiveMenuByTopMenu(SysMenu menu) {
		List<SysMenu> childMenus = menuMapper.listMenuByPar(menu);
		for (SysMenu sysMenu : childMenus) {
			SysMenu m = recursiveMenuByTopMenu(sysMenu);
			menu.getChildrenMenuList().add(m);
		}
		return menu;
	}
	
	/*@Override
	public void removeMenu(String id) {
		SysMenu m = menuMapper.selectByPrimaryKey(id);
		SysMenu menu = this.recursiveMenuByTopMenu(m);
		//把所有菜单按顺序放在list中
		List<SysMenu> rmList = new ArrayList<SysMenu>();
		List<SysMenu> list = menu.getChildrenMenuList();
		while(list.size()>0){
			rmList.addAll(list);
			List<SysMenu> temList = new ArrayList<SysMenu>();
			for (int j = 0; j < list.size(); j++) {
				SysMenu temAdds = list.get(j);
				temList.addAll(temAdds.getChildrenMenuList());
			}
			list = temList;
		}
		//删除所有子集菜单
		for (int j = rmList.size()-1; j >= 0; j--) {
			menuMapper.deleteByPrimaryKey(rmList.get(j).getMenuId());
		}
		//删除菜单
		menuMapper.deleteByPrimaryKey(id);
	}*/
	@Override
	public SysMenu getMenuWithParNameById(String id) {
		return menuMapper.getMenuWithParNameById(id);
	}
	@Override
	public void saveOrUpdateMenu(SysMenu menu) {
		if("".equals(menu.getMenuId())){
			menu.setMenuId(UUID.randomUUID().toString());
			menu.setMenuState("A");
			menuMapper.insertSelective(menu);
		}else{
			menuMapper.updateByPrimaryKeySelective(menu);
		}
	}
	/**
     * 
     * @methodName queryMenuById
     * @description 根据菜单id查出需要增加子菜单的菜单
     * @author 于琦
     * @time 2017年10月27日上午10:39:00
     * @param id
     */
    @Override
    public SysMenu queryMenuById(String id) {
        return menuMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 
     * @methodName save
     * @description 保存菜单信息
     * @author 于琦
     * @time 2017年10月27日上午11:16:49
     * @param menu
     */
    @Override
    public void save(SysMenu menu) {
        menuMapper.insertSelective(menu);
    }
    
    /**
     * 
     * @methodName update
     * @description 修改菜单信息
     * @author 于琦
     * @time 2017年10月27日上午11:16:49
     * @param menu
     */
    @Override
    public void update(SysMenu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }
    
    /**
     * 
     * @methodName getMenuByMenuName
     * @description 通过菜单名查询菜单
     * @author 于琦
     * @time 2017年10月27日下午1:52:58
     * @param menuName
     */
    @Override
    public SysMenu getMenuByMenuName(String menuName) {
        return menuMapper.getMenuByMenuName(menuName);
    }
    
    /**
     * 
     * @methodName getMenuByUrl
     * @description 根据url查询菜单
     * @author 于琦
     * @time 2017年10月27日下午1:48:48
     * @param url
     */
    @Override
    public SysMenu getMenuByUrl(String url) {
        return menuMapper.getMenuByUrl(url);
    }
    /**
     * 根据传入父菜单id获得父菜单名称
     * 作者 康磊
     * 2017年10月30日 11:05:12
     */
    @Override
	public SysMenu getParNameByParMenuId(String parMenuId) {
		return menuMapper.getParNameByParMenuId(parMenuId);
	}
	/**
	 * 增加保存
	 * 康磊
	 */
    @Override
	public void saveOrUpdatemenu(SysMenu menu) {
		if("".equals(menu.getMenuId())){
			menu.setMenuId(UUID.randomUUID().toString());
			String parMenuName = menu.getParMenuName();
			String parMenuId=menuMapper.getparMenuIdByparMenuName(parMenuName);
			menu.setSysMenuId(parMenuId);
			menuMapper.insertSelective(menu);
		}else{
			String parMenuName = menu.getParMenuName();
			String parMenuId=menuMapper.getparMenuIdByparMenuName(parMenuName);
			menu.setSysMenuId(parMenuId);
			menuMapper.updateByPrimaryKeySelective(menu);
		}
	} 
    /**
     * 删除
     * 作者 康磊
     */
    @Override
	public void removeMenu(String[] ids) {
		for (String id : ids) {
		SysMenu m = menuMapper.selectByPrimaryKey(id);
		SysMenu menu = this.recursiveMenuByTopMenu(m);
		//把所有菜单按顺序放在list中
		//删除中间表信息
		//根据id删除role-menu表信息
		rolemenuMapper.deleteByMenuId(id);
		
		List<SysMenu> rmList = new ArrayList<SysMenu>();
		List<SysMenu> list = menu.getChildrenMenuList();
		while(list.size()>0){
			rmList.addAll(list);
			List<SysMenu> temList = new ArrayList<SysMenu>();
			for (int j = 0; j < list.size(); j++) {
				SysMenu temAdds = list.get(j);
				temList.addAll(temAdds.getChildrenMenuList());
			}
			list = temList;
		}
		//删除所有子集菜单
		for (int j = rmList.size()-1; j >= 0; j--) {
			menuMapper.deleteByPrimaryKey(rmList.get(j).getMenuId());
		}
		//删除菜单
		menuMapper.deleteByPrimaryKey(id);
		}
	}
}
