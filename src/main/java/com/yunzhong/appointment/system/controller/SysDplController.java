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
import com.yunzhong.appointment.system.service.SysDplService;
import com.yunzhong.appointment.util.PageData;
/**
 * 
* @ClassName: SysDplController 
* @Description: 部门控制器 
* @author 于琦 
* @date 2017年10月27日 下午1:38:26 
* 
* @param <SysDpl>
 */
@RequestMapping("sys")
@Controller
public class SysDplController<SysDpl> {
	private static final String dplName = null;
	@Autowired
	private SysDplService dplService;
	
	   /**
			 * 
			 * @methodName listDel
			 * @description 得到用户数据
			 * @author 于琦
			 * @time 2017年10月27日 下午1:38:26 
			 * @param request
			 * @param mm
			 * @return
			 */

	@RequestMapping("listDpl")
	public String listDpl(HttpServletRequest request,ModelMap mm){
		 //接收前台数据
        PageData pd = new PageData(request);
        //查询数据
        List<SysDpl>varList = dplService.listDpl(pd);
        //得到分页数据
        PageInfo page = new PageInfo(varList);
        mm.put("page", page);
        mm.put("pd", pd);
        return "system/dpl/list_dpl";
	}
	/**
	 * 
	* @Title: pageDpl 
	* @Description: 跳转部门的增加或修改页
	* @param @param mm
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	   @RequestMapping("pageDpl")
	    public String pageDpl(ModelMap mm, String id){
	        if (null!=id) {
	            SysDpl dpl =  (SysDpl) dplService.getDplById(id);
	            mm.put("dpl", dpl);
	        }
	        return "system/dpl/page_dpl";     
	    }
	    /**
	     * @methodName saveOrUpdateDpl
	     * @description 根据id修改或添加
	     * @author 于琦 
	     * @param dpl
	     * @param redirectAttributes
	     * @return
	     */
	    
	   
	   
	   @RequestMapping("saveOrUpdateDpl")
	    public String  saveOrUpdateDpl(SysDpl dpl,RedirectAttributes redirectAttributes){
	       // dplService.saveOrUpdateDpl(dpl);
	        //刚增加或修改的数据要回传到页面显示在第一条
	        redirectAttributes.addFlashAttribute("firstObj",dpl);
	        return "redirect:query.action";
	    }
	    /**
	     * @methodName removeDpl
	     * @description 删除
	     * @author 于琦
	     * @param ids
	     */	   
	   @RequestMapping("/removeDpl")
       public String removeDpl(String[] ids){
	        dplService.removeDpl(ids);
	        return "redirect:query.action";
       }
	   /**
	     * 
	     * @description 
	     * @author 于琦
	      * @time 2017年10月27日 下午1:38:26
	     * @param
	     */
	    @RequestMapping("checkDplName")
	    @ResponseBody
	    public boolean checkRoleName(String roleName){
	        SysDpl dpl = (SysDpl) dplService.getDplByDplName(dplName);
	        return dpl==null?true:false;
	    }

	    
	}

