package com.yunzhong.appointment.system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageInfo;
import com.yunzhong.appointment.entity.SysRole;
import com.yunzhong.appointment.entity.SysUser;
import com.yunzhong.appointment.system.service.SysRoleService;
import com.yunzhong.appointment.util.PageData;
import com.yunzhong.appointment.util.SelectOptions;
/**
* @ClassName: SysRoleController 
* @Description: 角色控制器
* @author 康磊 
* @date 2017年10月27日 上午10:11:30 
*
 */
@RequestMapping("role")
@Controller
public class SysRoleController {
	@Autowired
	private SysRoleService service;
	/**
	* @Title: query 
	* @author 康磊 
	* @Description: 角色查询页面查询所有
	* @param @param model
	* @param @param request
	* @param @return    
	* @return String  
	* @throws
	 */
	@RequestMapping("query")
	public String query(ModelMap model,HttpServletRequest request){
		PageData pd = new PageData(request);
		List<SysRole> roleList=service.listRole(pd);
		PageInfo page = new PageInfo(roleList);
		model.put("pd", pd);
		model.put("page", page);
		return "system/role/listrole";
	}
	/**
	* @Title: pageRole 
	* @author 康磊 
	* @Description: 添加修改跳转页面 
	* @param @param id
	* @param @param model
	* @param @return    
	* @return String  
	* @throws
	 */
	@RequestMapping("pageRole")
	public String pageRole(String id,ModelMap model){
		if(null!=id){
			SysRole role = service.getroleById(id);
			model.put("role",role );
		}
		return "system/role/pagerole";
	}
	/**
	* @Title: saveOrUpdateRole 
	* @author 康磊 
	* @Description:添加修改保存
	* @param @param role
	* @param @param redirectAttributes
	* @param @return    
	* @return String  
	* @throws 
	* @date 2017年10月27日 上午11:06:45
	 */
	@RequestMapping("saveOrUpdateRole")
	public String saveOrUpdateRole(SysRole role,RedirectAttributes redirectAttributes){
		service.saveOrUpdateRole(role);
		//刚增加或修改的数据要回传到页面显示在第一条
		redirectAttributes.addFlashAttribute("firstObj",role);
		return "redirect:query";	
	}
	/**
	 * 
	* @Title: removeOneRole 
	* @author 康磊 
	* @Description: 删除一条数据 与删除多条数据
	* @param @param ids
	* @param @return    
	* @return String  
	* @throws 
	* @date 2017年10月27日 上午11:07:07
	 */
	@RequestMapping("removeOneRole")
	public String removeOneRole(String[] ids){
		service.removeOneRole(ids);
		return "redirect:query";
	}
	
	/**
	 *  角色菜单授权    有两种页面 
	* @Title: authUser
	* @author 康磊 
	* @param id
	* @param mm
	* @return
	* String
	* @throws
	* @date 2017年10月27日 下午4:19:37
	 */
	@RequestMapping("authRole")
	public String authUser(String id, ModelMap mm){
		SysRole role = service.getroleById(id);
		List<SelectOptions> menuList = service.listMenuByRoleId(id);
		mm.put("menuList", menuList);
		mm.put("role", role);
		//有两种页面
		//路径有两个 "system/role/rrole_menu"  与 "system/role/role_menu"
		return "system/role/rrole_menu";
	}
	
	
	/**
	 * 角色授权菜单 修改保存
	* @Title: updateAuthRole
	* @author 康磊 
	* @param menuIds
	* @param roleId
	* @return
	* String
	* @throws
	* @date 2017年10月27日 下午6:59:50
	 */
	@RequestMapping("updateAuthRole")
	public String updateAuthRole(String menuIds, String roleId){
		service.updateAuthRole(menuIds,roleId);
		return "redirect:query";
	}
	/**
	 *验证角色名称 是否重复
	* @Title: verifyName
	* @author 康磊 
	* @param roleName
	* @param response
	* @throws IOException
	* void
	* @throws
	* @date 2017年10月29日 下午11:05:47
	 */
	@RequestMapping("verifyName")
	public void verifyName(String roleName,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		String str = service.verifyName(roleName);
		if (str!=null) {
			out.print(str);
		}
	}
	/**
	 * 角色授权 树状图方法
	* @Title: roleMenu
	* @author 康磊 
	* @param roleId
	* @param menuIds
	* @return
	* String
	* @throws
	* @date 2017年11月3日 下午3:19:01
	 */
	@RequestMapping("roleMenu")
	public String roleMenu(String roleId,String menuIds){
		service.roleMenu(roleId,menuIds);
		return "redirect:query";
	}
}
