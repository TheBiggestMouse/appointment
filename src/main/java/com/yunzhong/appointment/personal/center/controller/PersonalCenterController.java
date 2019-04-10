package com.yunzhong.appointment.personal.center.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yunzhong.appointment.config.SessionConst;
import com.yunzhong.appointment.entity.Area;
import com.yunzhong.appointment.entity.City;
import com.yunzhong.appointment.entity.Patient;
import com.yunzhong.appointment.entity.Person;
import com.yunzhong.appointment.entity.Province;
import com.yunzhong.appointment.entity.SysUser;
import com.yunzhong.appointment.person.service.IPersonService;
import com.yunzhong.appointment.personal.center.service.ICityService;
import com.yunzhong.appointment.personal.center.service.IPersonalCenterService;
import com.yunzhong.appointment.personal.center.service.IProvinceService;

/**
 * 
* @ClassName: PersonalCenterController 
* @Description: 个人中心控制层
* @author 赵星宇
* @date 2017年10月26日 下午1:34:12 
*
 */
@Controller
@Scope("prototype")
@RequestMapping("personal")
public class PersonalCenterController {

	@Autowired
	private IPersonService  personService;
    @Autowired
    private IPersonalCenterService  PersonalCenterService;
    @Autowired
    private IProvinceService ProvinceService;
    @Autowired
    private ICityService CityService;
  
   /**
   * @Title: query 
   * @Description: 跳转到个人中心页面
   * @author 赵星宇
   */
    @RequestMapping("editPage")
    public String query(ModelMap map,ModelMap mm){
    	//登陆的患者才有个人中心 信息存在LoginController Session中
    	Subject currentUser = SecurityUtils.getSubject();
    	SysUser	user = (SysUser)currentUser.getSession().getAttribute(SessionConst.SESSION_USER);
    	//查询roleName是不是患者
    	String roleName=PersonalCenterService.getRoleName(user.getUserId());
    	//获取角色名称
    	System.out.println("roleName:"+roleName);
    	//如果roleName里面的角色名称是患者
    	if(roleName.equals("patient")){
    		//根据id查询患者信息
    		Patient	Patient =PersonalCenterService.getQuery(user.getUserId());
    		//查看患者ID
    		System.out.println("ID+++++"+user.getUserId());
    		//查看患者信息
    		System.out.println("患者!!++++"+Patient );
    		//获取患者手机号
    		String telphone = Patient .getPatientTel();
    		//截取
    		String telnum = telphone.substring(0,telphone.length()-(telphone.substring(3)).length())+"****"+telphone.substring(6);
    		System.out.println(telnum);
    		mm.put("telnum", telnum);
    		//获取患者身份证号
    		String patientUid = Patient .getPatientUid();
    		//截取
    		String uid = patientUid.substring(0,patientUid.length()-(patientUid.substring(3)).length())+"****"+patientUid.substring(7);
    		System.out.println("身份证"+uid);
    		mm.put("uid", uid);
    		//存入患者信息
    		map.put("patient", Patient );
    		//查询省信息用于回显
    		List<Province>provinceList=ProvinceService.getProvince();
    		map.put("provinceList", provinceList);
    		System.out.println("省信息"+provinceList);
    		//获取当前用户省id根据省ID查询市信息
    		String id = user.getUserId();
    		
    		Patient ProvinceId = PersonalCenterService.getProvinceId(id);
    		System.out.println("省IDID++"+ProvinceId);
    		//根据用户省ID查询市信息存入
    		String provinceId = ProvinceId.getProvinceId();
    		System.out.println("21314"+provinceId);
    		List<City> cityList = CityService.getquery(provinceId); 
    		System.out.println("cityList+_"+cityList);    	
    		map.put("cityList", cityList);
    		//根据用户市ID查询区信息存入
    		Patient CityId = PersonalCenterService.getCityId(id);
    		System.out.println("市区+"+CityId);
    		String cityId=CityId.getCityId();
    		List<Area> areaList = CityService.getArea(cityId);
    		System.out.println("888888"+areaList);
    		map.put("areaList", areaList);
    		//----修改
    		Patient	pat =PersonalCenterService.getQuery(user.getUserId());
    		map.put("pat", pat);
    	

  		

    		return "personcenterinfo/patient_message";
    	}
    
    	
    	/**
    	 * 查询人员的数据
    	 */
    	Person	person= personService.getQuery(user.getUserId());
    	System.out.println("人员ID+"+user.getUserId());
    	map.put("person", person);
    	System.out.println("人员:++"+person);
		return "personcenterinfo/patient_message";
    	
		}
    /**
     * 
    * @Title: edit 
    * @Description: 跳转到个人信息页面
    * @param @param map
    * @param @param id
    * @param @return    设定文件 	
    * @return String    返回类型 
    * @throws
     */
    
    @RequestMapping("personalquery")
    public String query(ModelMap map){
    	
    	Subject currentUser = SecurityUtils.getSubject();
    	SysUser	user = (SysUser)currentUser.getSession().getAttribute(SessionConst.SESSION_USER);
    	
    	//查询roleName是不是患者
    	String roleName=PersonalCenterService.getRoleName(user.getUserId());
    	//获取角色名称
    	System.out.println("roleName:"+roleName);
    	//如果roleName里面的角色名称是患者
    	if(roleName.equals("patient")){
    	
    		Patient	patient=PersonalCenterService.getQuery(user.getUserId());
    		String name = patient.getPatientName();
    		System.out.println("name");
    		map.put("name", name);
    		map.put("patient",patient);
    		
    
    		
    		return "personcenterinfo/patient_personinfo";
    	
    	}
    	//如果不是患者
    	return "personcenterinfo/admin_personinfo";
    	
    }
    @RequestMapping("avatarsave")
    public String saveFile(@RequestParam("pic") MultipartFile picFile){
	   	Subject currentUser = SecurityUtils.getSubject();
    	SysUser	user = (SysUser)currentUser.getSession().getAttribute(SessionConst.SESSION_USER);
    	Patient patient = PersonalCenterService.getQuery(user.getUserId());
    	
    	PersonalCenterService.save(patient,picFile);

    	
    	
    	System.out.println("1"+picFile);
		return "redirect:avatarquery";
    	
    }
    
    
    @RequestMapping("avatarquery")
    public String queryFile(ModelMap map){
    	Subject currentUser = SecurityUtils.getSubject();
    	SysUser	user = (SysUser)currentUser.getSession().getAttribute(SessionConst.SESSION_USER);
    	//查询roleName是不是患者
    	Patient patient = PersonalCenterService.getQuery(user.getUserId());
    	map.put("patient", patient);
    	System.out.println("765432"+patient);
    	
    	
    	
		return "personcenterinfo/patient_avatar";
    	
    }
    /***
     * 
    * @Title: editsave 
    * @Description: 修改保存 需要省市区连动 
    * @param @param map
    * @param @param picFile
    * @param @param request
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
   @RequestMapping("/editsave")
   public String editsave(ModelMap map,Patient pat){
	   //接不到页面穿的值
	  System.out.println("修改"+pat);
   	Subject currentUser = SecurityUtils.getSubject();
   	SysUser	user = (SysUser)currentUser.getSession().getAttribute(SessionConst.SESSION_USER);
  	String roleName=PersonalCenterService.getRoleName(user.getUserId());
  	//查询患者信息

	//如果roleName里面的角色名称是患者
	if(roleName.equals("patient")){
		PersonalCenterService.edit(pat);		
		return "redirect:editPage";
	
	}
   	
	//如果不是患者走下方法
	System.out.println("123456");
	return "null";

//    	
    }
    
    
}
