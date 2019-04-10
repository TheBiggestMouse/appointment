package com.yunzhong.appointment.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageInfo;
import com.yunzhong.appointment.entity.SysUser;
import com.yunzhong.appointment.system.service.SysUserService;
import com.yunzhong.appointment.util.PageData;
import com.yunzhong.appointment.util.SelectOptions;

/**
 * 
 * @className SysUserController
 * @description 用户控制器
 * @author 石洪刚
 * @time 2017年10月2日 下午11:30:37
 */
@RequestMapping("sys")
@Controller
public class SysUserController {

	@Autowired
	private SysUserService userService;
	/**
	 * 
	 * @methodName listUser
	 * @description 得到用户数据
	 * @author 石洪刚
	 * @time 2017年10月2日 下午11:43:55
	 * @param request
	 * @param mm
	 * @return
	 */
	@RequestMapping("listUser")
	public String listUser(HttpServletRequest request,ModelMap mm){
		PageData pd = new PageData(request);
		List<SysUser> userList = userService.listUser(pd);
		PageInfo page = new PageInfo(userList);
		mm.put("pd", pd);
		mm.put("page", page);
		return "system/user/listuser";
	}
	/**
	 * 
	 * @methodName pageUser
	 * @description 跳转用户的增加或修改页
	 * @author 石洪刚
	 * @time 2017年10月4日 上午8:57:15
	 * @param id
	 * @return
	 */
	@RequestMapping("pageUser")
	public String pageUser(String id,ModelMap mm){
		if(null!=id){
			SysUser user = userService.getUserById(id);
			mm.put("user", user);
		}
		return "system/user/pageuser";
	}
	/**
	 * 
	 * @methodName checkUserName
	 * @description 验证登录名是否与数据库中现有数据重复
	 * @author 石洪刚
	 * @time 2017年10月5日 上午8:11:18
	 * @param userName
	 * @return
	 */
	@RequestMapping("checkUserName")
	@ResponseBody
	public boolean checkUserName(String userName){
		SysUser user = userService.getUserByUserName(userName);
		return user==null?true:false;
	}
	
	/**
	 * 
	 * @methodName saveOrUpdateUser
	 * @description 增加或修改用户信息
	 * @author 石洪刚
	 * @time 2017年10月5日 上午9:54:47
	 * @param user
	 * @return
	 */
	@RequestMapping("saveOrUpdateUser")
	public String saveOrUpdateUser(SysUser user,RedirectAttributes redirectAttributes){
		userService.saveOrUpdateUser(user);
		//刚增加或修改的数据要回传到页面显示在第一条
		redirectAttributes.addFlashAttribute("firstObj",user);
		return "redirect:listUser";
	}
	/**
	 * 
	 * @methodName removeUser
	 * @description 删除用户数据
	 * @author 石洪刚
	 * @time 2017年10月5日 下午7:27:26
	 * @param ids
	 * @return
	 */
	@RequestMapping("removeUser")
	public String removeUser(String[] ids){
		userService.removeUser(ids);
		return "redirect:listUser";
	}
	/**
	 * 
	 * @methodName authUser
	 * @description 对用户进行授权
	 * @author 石洪刚
	 * @time 2017年10月6日 上午8:18:01
	 * @param id
	 * @return
	 */
	@RequestMapping("authUser")
	public String authUser(String id, ModelMap mm){
		SysUser user = userService.getUserById(id);
		List<SelectOptions> roleList = userService.listRoleByUserId(id);
		mm.put("roleList", roleList);
		mm.put("user", user);
		return "system/user/user_role";
	}
	/**
	 * 
	 * @methodName updateAuthUser
	 * @description 修改用户权限
	 * @author 石洪刚
	 * @time 2017年10月6日 下午1:57:13
	 * @param roleIds
	 * @param userId
	 * @return
	 */
	@RequestMapping("updateAuthUser")
	public String updateAuthUser(String roleIds, String userId){
		userService.updateAuthUser(roleIds,userId);
		return "redirect:listUser";
	}
}

