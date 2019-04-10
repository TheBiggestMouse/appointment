package com.yunzhong.appointment.person.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunzhong.appointment.entity.Department;
import com.yunzhong.appointment.entity.Departmenttype;
import com.yunzhong.appointment.entity.Person;
import com.yunzhong.appointment.entity.Scheduling;
import com.yunzhong.appointment.entity.Visitsettime;
import com.yunzhong.appointment.mapper.DepartmentMapper;
import com.yunzhong.appointment.mapper.DepartmenttypeMapper;
import com.yunzhong.appointment.mapper.PersonMapper;
import com.yunzhong.appointment.mapper.SchedulingMapper;
import com.yunzhong.appointment.mapper.VisitsettimeMapper;
import com.yunzhong.appointment.person.service.IPersonService;
import com.yunzhong.appointment.util.PageData;
/**
 * 标题: PersonServiceImpl.java
 * 路径: com.yunzhong.appointment.person.service.impl
 * 描述: 人员信息模型层实现类
 * 作者: 郎国峰
 * 时间: 2017年11月10日 下午1:58:43
 * 版本: @version V1.0
 */
@Service
public class PersonServiceImpl implements IPersonService {
	/**
	 * 作者: 郎国峰
	 * 时间: 2017年11月10日14:05:40
	 * 描述: 手动排班表mapper
	 */
	@Autowired
	private SchedulingMapper schedulingMapper;
	/**
	 * 作者: 赵星宇
	 * 时间: 2017年11月10日15:27:01
	 * 描述: 人员mapper
	 */
	@Autowired
	private PersonMapper personMapper;
	/**
	 * 作者: 郎国峰
	 * 时间: 2017年11月10日17:50:11
	 * 描述: 医生科室类别mapper
	 */
	@Autowired
	private DepartmenttypeMapper departmenttypeMapper;
	/**
	 * 作者: 郎国峰
	 * 时间: 2017年11月10日18:51:29
	 * 描述: 医生科室mapper
	 */
	@Autowired
	private DepartmentMapper departmentMapper;
	/**
	 * 作者: 郎国峰
	 * 时间: 2017年11月11日00:44:30
	 * 描述: 自动排班mapper
	 */
	@Autowired
	private VisitsettimeMapper visitsettimeMapper;
	
	@Override
	public Person getQuery(String userId) {
		
		return personMapper.getQuery(userId);
	}
	
	@Override
	public ArrayList<Scheduling> listScheduling(PageData pd) {
		// TODO Auto-generated method stub
		ArrayList<Scheduling> listScheduling = schedulingMapper.listScheduling(pd);
		return listScheduling;
	}

	@Override
	public List<Departmenttype> listDepartmenttype() {
		// TODO Auto-generated method stub
		return departmenttypeMapper.qureyAll();
	}

	@Override
	public List<Department> listDepartmentDplId(String id) {
		// TODO Auto-generated method stub
		return departmentMapper.queryAll(id);
	}

	@Override
	public List<Person> queryDoctorName(String id) {
		// TODO Auto-generated method stub
		List<Person> listPerson = personMapper.queryDoctorNameByDeptId(id);
		return listPerson;
	}

	@Override
	public void docscheSave(Scheduling scheduling) {
		// TODO Auto-generated method stub
		scheduling.setSchedulingId(UUID.randomUUID().toString());
		schedulingMapper.insertSelective(scheduling);
	}

	@Override
	public void docscheEdit(Scheduling scheduling) {
		// TODO Auto-generated method stub
		schedulingMapper.updateByPrimaryKey(scheduling);
	}

	@Override
	public Scheduling getSchedulingById(String id) {
		// TODO Auto-generated method stub
		return schedulingMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Map<String, String[]>> listAutoDocsche() {
		// TODO Auto-generated method stub
		//查询自动排班人员
		List<Visitsettime> list = visitsettimeMapper.queryAll();
		System.out.println("=================list===="+list+"=======================");
		//进行格式转换 , 声明一个list套map套list
		List<Map<String, String[]>> listAutoDocsche = new ArrayList<Map<String,String[]>>();
		//遍历list得到所需元素,存储到目标容器listAutoDocsche
		for (int i = 0; i < list.size(); i++) {
			//声明一个map用来存储一条数据
			HashMap<String, String[]> map = new HashMap<String, String[]>();
			//得到一条数据
			Visitsettime vis = list.get(i);
			//将这条数据的id以数组的形式放入map中
			map.put("id",vis.getVisitstId().split("@@"));
			//将这条数据的人名以数组的形式放入map中
			map.put("ppName",vis.getPpName().split("@@"));
			//将这条数据的科室名以数组的形式放入map中
			map.put("departmentName",vis.getDepartmentName().split("@@"));
			//-------------------------------------------------出诊时间段和次数的字符串处理开始----------------------------------------------------------
			//得到星期一的出诊时间段和次数字符串
			String visitMoncount = vis.getVisitMoncount();//1-1,2-2,3-3
			//声明一个七个长度的数组用来存储出诊时间段和次数
			String[] moncount = {"0","0","0","0","0","0","0"};
			//判断星期一的出诊时间是否为null,
			if(visitMoncount!=null){//不为null的情况下就行处理,
				//以逗号切分visitWedcount,得到一个新的数组
				String[] newWedcount = visitMoncount.split(",");
				//遍历这个数组得到每一个出诊时间段
				for (int j = 0; j < newWedcount.length; j++) {
					String str = newWedcount[j];//得到具体时间段和此时间段的出诊次数字符串
					int wedcountTime = new Integer( str.split("-")[0]);//得到出诊的时间段
					String wedcountstr = str.split("-")[1];//得到此事件段的具体出诊次数
					//将这个时间段的出诊次数存储到wedcount的对应位置
					moncount[wedcountTime-1] = wedcountstr;
				}
			}
			
			//得到星期二的出诊时间段和次数字符串
			String visitTuescount = vis.getVisitTuescount();//1-1,2-2,3-3
			//声明一个七个长度的数组用来存储出诊时间段和次数
			String[] tuescount = {"0","0","0","0","0","0","0"};
			//判断星期一的出诊时间是否为null,
			if(visitTuescount!=null){//不为null的情况下就行处理,
				//以逗号切分visitWedcount,得到一个新的数组
				String[] newTuescount = visitTuescount.split(",");
				//遍历这个数组得到每一个出诊时间段
				for (int j = 0; j < newTuescount.length; j++) {
					String str = newTuescount[j];//得到具体时间段和此时间段的出诊次数字符串
					int tuescountTime = new Integer( str.split("-")[0]);//得到出诊的时间段
					String countstr = str.split("-")[1];//得到此事件段的具体出诊次数
					//将这个时间段的出诊次数存储到wedcount的对应位置
					moncount[tuescountTime-1] = countstr;
				}
			}
			
			//得到星期三的出诊时间段和次数字符串
			String visitWedcount = vis.getVisitWedcount();//1-1,2-2,3-3
			//声明一个七个长度的数组用来存储出诊时间段和次数
			String[] wedcount = {"0","0","0","0","0","0","0"};
			//判断星期一的出诊时间是否为null,
			if(visitWedcount!=null){//不为null的情况下就行处理,
				//以逗号切分visitWedcount,得到一个新的数组
				String[] newcount = visitWedcount.split(",");
				//遍历这个数组得到每一个出诊时间段
				for (int j = 0; j < newcount.length; j++) {
					String str = newcount[j];//得到具体时间段和此时间段的出诊次数字符串
					int time = new Integer( str.split("-")[0]);//得到出诊的时间段
					String countstr = str.split("-")[1];//得到此事件段的具体出诊次数
					//将这个时间段的出诊次数存储到wedcount的对应位置
					wedcount[time-1] = countstr;
				}
			}
			
			//得到星期四的出诊时间段和次数字符串
			String visitThurcount = vis.getVisitThurcount();//1-1,2-2,3-3
			//声明一个七个长度的数组用来存储出诊时间段和次数
			String[] thurcount = {"0","0","0","0","0","0","0"};
			//判断星期一的出诊时间是否为null,
			if(visitThurcount!=null){//不为null的情况下就行处理,
				//以逗号切分visitWedcount,得到一个新的数组
				String[] newcount = visitThurcount.split(",");
				//遍历这个数组得到每一个出诊时间段
				for (int j = 0; j < newcount.length; j++) {
					String str = newcount[j];//得到具体时间段和此时间段的出诊次数字符串
					int time = new Integer( str.split("-")[0]);//得到出诊的时间段
					String countstr = str.split("-")[1];//得到此事件段的具体出诊次数
					//将这个时间段的出诊次数存储到wedcount的对应位置
					thurcount[time-1] = countstr;
				}
			}
			
			//得到星期五的出诊时间段和次数字符串
			String visitFricount = vis.getVisitFricount();//1-1,2-2,3-3
			//声明一个七个长度的数组用来存储出诊时间段和次数
			String[] fricount = {"0","0","0","0","0","0","0"};
			//判断星期一的出诊时间是否为null,
			if(visitFricount!=null){//不为null的情况下就行处理,
				//以逗号切分visitWedcount,得到一个新的数组
				String[] newcount = visitFricount.split(",");
				//遍历这个数组得到每一个出诊时间段
				for (int j = 0; j < newcount.length; j++) {
					String str = newcount[j];//得到具体时间段和此时间段的出诊次数字符串
					int time = new Integer( str.split("-")[0]);//得到出诊的时间段
					String countstr = str.split("-")[1];//得到此事件段的具体出诊次数
					//将这个时间段的出诊次数存储到wedcount的对应位置
					fricount[time-1] = countstr;
				}
			}
			
			//得到星期六的出诊时间段和次数字符串
			String visitSatcount = vis.getVisitSatcount();//1-1,2-2,3-3
			//声明一个七个长度的数组用来存储出诊时间段和次数
			String[] satcount = {"0","0","0","0","0","0","0"};
			//判断星期一的出诊时间是否为null,
			if(visitSatcount!=null){//不为null的情况下就行处理,
				//以逗号切分visitWedcount,得到一个新的数组
				String[] newcount = visitSatcount.split(",");
				//遍历这个数组得到每一个出诊时间段
				for (int j = 0; j < newcount.length; j++) {
					String str = newcount[j];//得到具体时间段和此时间段的出诊次数字符串
					int time = new Integer( str.split("-")[0]);//得到出诊的时间段
					String countstr = str.split("-")[1];//得到此事件段的具体出诊次数
					//将这个时间段的出诊次数存储到wedcount的对应位置
					satcount[time-1] = countstr;
				}
			}
			
			//得到星期日的出诊时间段和次数字符串
			String visitSuncount = vis.getVisitSuncount();//1-1,2-2,3-3
			//声明一个七个长度的数组用来存储出诊时间段和次数
			String[] suncount = {"0","0","0","0","0","0","0"};
			//判断星期一的出诊时间是否为null,
			if(visitSuncount!=null){//不为null的情况下就行处理,
				//以逗号切分visitWedcount,得到一个新的数组
				String[] newcount = visitSuncount.split(",");
				//遍历这个数组得到每一个出诊时间段
				for (int j = 0; j < newcount.length; j++) {
					String str = newcount[j];//得到具体时间段和此时间段的出诊次数字符串
					int time = new Integer( str.split("-")[0]);//得到出诊的时间段
					String countstr = str.split("-")[1];//得到此事件段的具体出诊次数
					//将这个时间段的出诊次数存储到wedcount的对应位置
					suncount[time-1] = countstr;
				}
			}
			
			//-------------------------------------------------出诊时间段和次数的字符串处理结束----------------------------------------------------------
			//将各个时间段的出诊次数放到map中
			map.put("count1", moncount);
			map.put("count2", tuescount);
			map.put("count3", wedcount);
			map.put("count4", thurcount);
			map.put("count5", fricount);
			map.put("count6", satcount);
			map.put("count7", suncount);
			//将这条数据放入list中
			listAutoDocsche.add(map);
		}
		System.out.println("===================listAutoDocsche=="+listAutoDocsche+"=======================");
		return listAutoDocsche;
	}

}
