package com.yunzhong.appointment.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yunzhong.appointment.config.SessionConst;
import com.yunzhong.appointment.entity.SysMenu;
import com.yunzhong.appointment.entity.SysUser;
import com.yunzhong.appointment.system.service.SysMenuService;

/**
 * 
 * @className SysMenuController
 * @description 对菜单进行管理与维护
 * @author 石洪刚
 * @time 2017年10月2日 下午7:32:15
 */
@Controller
@RequestMapping("sys")
public class SysMenuController {
	
	@Autowired
	private SysMenuService menuService;
	/**
	 * 
	 * @methodName showMenu
	 * @description 用于点击一级菜单进行跳转
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:32:41
	 * @param id
	 * @return
	 */
	@RequestMapping("showMenu")
	public String showMenu(String id){
		List<SysMenu> menuList = (List<SysMenu>) SecurityUtils.getSubject().getSession().getAttribute(SessionConst.SESSION_MENULIST);
		SysMenu sm = null;
		if(menuList!=null){
			for (SysMenu sysMenu : menuList) {
				if(id.equals(sysMenu.getMenuId())){
					sm = sysMenu;
					break;
				}
			}
		}
		//如果没查到，则设置要显示的菜单为个人中心
		if(sm==null){
			sm = (SysMenu) SecurityUtils.getSubject().getSession().getAttribute(SessionConst.SESSION_PERSONAL_MENU);
		}
		SecurityUtils.getSubject().getSession().setAttribute(SessionConst.SESSION_MENU, sm);
		return "redirect:"+sm.getMenuUrl();
	}

	/**
	 * 查询所有菜单用于 菜单维护
	* @Title: queryMenu
	* @author 康磊 
	* @param mm
	* @return
	* String
	* @throws
	* @date 2017年10月31日 上午10:45:44
	 */
	@RequestMapping("queryMenu")
	public String queryMenu(ModelMap mm){
		List<SysMenu> menuList = menuService.listAllMenu();
		mm.put("menuList", menuList);
		return "system/menu/menutree";
	}
	
	/**
	 * 菜单维护 添加 修改 跳页
	* @Title: pageMenu
	* @author 康磊 
	* @param id
	* @param parMenuId
	* @param mm
	* @return
	* String
	* @throws
	* @date 2017年10月30日 上午11:10:26
	 */
	@RequestMapping("pageMenu")
	public String pageMenu(@Param("id")String id,@Param("parMenuId")String parMenuId,ModelMap mm){
		if(null!=id){
			SysMenu menu = menuService.getMenuWithParNameById(id);
			mm.put("menu1", menu);
		}
		SysMenu parMenuName=menuService.getParNameByParMenuId(parMenuId);
		mm.put("parMenuName", parMenuName);
		return "system/menu/pagemenu";
	}
	/**
	 * 添加  修改  保存
	* @Title: saveOrUpdateMenu
	* @author 康磊 
	* @param menu
	* @param redirectAttributes
	* @return
	* String
	* @throws
	* @date 2017年10月30日 上午11:24:51
	 */
	@RequestMapping("saveOrUpdateMenu")
	public String saveOrUpdateMenu(SysMenu menu,RedirectAttributes redirectAttributes){
		menuService.saveOrUpdatemenu(menu);
		redirectAttributes.addFlashAttribute("firstObj",menu);
		return "redirect:queryMenu";
	}
	/**
	 * 删除 
	* @Title: removeMenu
	* @author 康磊 
	* @param id
	* @return
	* String
	* @throws
	* @date 2017年10月30日 下午12:00:14
	 */
	@RequestMapping("removeMenu")
	public String removeMenu(String[] id){
		menuService.removeMenu(id);
		return "redirect:queryMenu";
	}
}

