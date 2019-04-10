package com.yunzhong.appointment.person.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.yunzhong.appointment.entity.Department;
import com.yunzhong.appointment.entity.Departmenttype;
import com.yunzhong.appointment.entity.Person;
import com.yunzhong.appointment.entity.Scheduling;
import com.yunzhong.appointment.person.service.IPersonService;
import com.yunzhong.appointment.util.PageData;

/**
* 标题: PersonController.java
* 路径: com.yunzhong.appointment.person.controller
* 描述: 人员信息控制器
* 作者: 郎国峰
* 时间: 2017年11月7日 下午4:23:27
* 版本: @version V1.0
 */
@Controller
@RequestMapping("/pp")
public class PersonController {
	/**
	 * 作者: 郎国峰
	 * 描述: 人员模型层对象
	 */
	@Autowired
	private IPersonService personService;
	
	/**
	 * @方法名: personMenu   
	 * @描述: 跳转到人员信息页面
	 * @作者: 郎国峰
	 * @时间: 2017年11月7日 下午4:24:20
	 * @return
	 */
	@RequestMapping("/pp")
	public String personMenu(){
		return "person/personMenu";
	}
	/**
	 * @方法名: docsche   
	 * @描述: 排班控制器,默认是查询排班表
	 * @作者: 郎国峰
	 * @时间: 2017年11月8日 上午8:02:09
	 * @return
	 */
	@RequestMapping("/docsche")
	public String docsche(ModelMap modelMap , HttpServletRequest request){
		//声明pageDate 用来接参
		PageData pd = new PageData(request);
		//查询所有排班信息
		ArrayList<Scheduling> listScheduling = personService.listScheduling(pd);
		//分页
		PageInfo page = new PageInfo(listScheduling);
		modelMap.put("page", page);
		modelMap.put("pd", pd);
		return "person/docsche";
	}
	
	/**
	 * @方法名: docschePage   
	 * @描述: 跳转医生排班添加修改页面控制器
	 * @作者: 郎国峰
	 * @时间: 2017年11月9日 下午11:09:26
	 * @param id
	 * @return
	 */
	@RequestMapping("/docschePage")
	public String docschePage(String id , ModelMap modelMap){
		//调用模型层查询医生科室信息
		List<Departmenttype> listDepartmenttype = personService.listDepartmenttype();
		if(id!=null && id != ""){//如果id不是空,根据id,查询一条手动排班信息
			Scheduling scheduling = personService.getSchedulingById(id);
			modelMap.put("scheduling", scheduling);
		}
		modelMap.put("listDepartmenttype", listDepartmenttype);
		return "person/docschePage";
	}
	/**
	 * @方法名: docscheSave   
	 * @描述: 医生手动排班添加或修改保存控制器
	 * @作者: 郎国峰
	 * @时间: 2017年11月10日 下午9:37:57
	 * @param scheduling
	 * @return
	 */
	@RequestMapping("/docscheSave")
	public String docscheSave(Scheduling scheduling){
		if(scheduling.getSchedulingId()!=null && scheduling.getSchedulingId()!="" ){//否则是修改保存
			personService.docscheEdit(scheduling);
		}else{//id不是空,就是增加保存
			personService.docscheSave(scheduling);
		}
		return "redirect:docsche";
	}
	/**
	 * @方法名: autoDocsche   
	 * @描述: 自动排班查询控制器
	 * @作者: 郎国峰
	 * @时间: 2017年11月9日 下午11:59:22
	 * @return
	 */
	@RequestMapping("/autoDocsche")
	public String autoDocsche(ModelMap modelMap,HttpServletRequest request){
		
		List<Map<String, String[]>> listAutoDocsche = personService.listAutoDocsche();
		modelMap.put("listAutoDocsche", listAutoDocsche);
		return "person/autoDocsche";
	}
	
	/**
	 * @方法名: autoDocschePage   
	 * @描述: 跳转到自动排班添加修改页控制器
	 * @作者: 郎国峰
	 * @时间: 2017年11月10日08:16:00
	 * @param id 传入的自动排班id
	 * @param data 传入的自动排班的星期几
	 * @return
	 */
	@RequestMapping("/autoDocschePage")
	public String autoDocschePage(String id,String date){
		
		return "person/autoDocschePage";
	}
	
	/**
	 * @方法名: listDepartmentDplId   
	 * @描述: 根据科室类型id,查询科室
	 * @作者: 郎国峰
	 * @时间: 2017年11月10日 下午6:44:11
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listDepartmentDplId")
	public List<Department> listDepartmentDplId(String id){
		List<Department> listDepartmentDplId = personService.listDepartmentDplId(id);
		return listDepartmentDplId;
	}
	/**
	 * @方法名: queryDoctorName   
	 * @描述: 根据科室id动态查询医生
	 * @作者: 郎国峰
	 * @时间: 2017年11月10日 下午7:12:11
	 */
	@ResponseBody
	@RequestMapping("/queryDoctorName")
	public List<Person> queryDoctorName(String id , ModelMap modelMap){
		List<Person> listDoctor = personService.queryDoctorName(id);
		return listDoctor;
	}
	
	
	
}
