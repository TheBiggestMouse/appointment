package com.yunzhong.appointment.person.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yunzhong.appointment.entity.Department;
import com.yunzhong.appointment.entity.Departmenttype;
import com.yunzhong.appointment.entity.Person;
import com.yunzhong.appointment.entity.Scheduling;
import com.yunzhong.appointment.entity.Visitsettime;
import com.yunzhong.appointment.util.PageData;

/**
 * 
 * 标题: IPersonService.java
 * 路径: com.yunzhong.appointment.person.service
 * 描述: 人员信息模型层接口
 * 作者: 郎国峰
 * 时间: 2017年11月10日 下午1:55:42
 * 版本: @version V1.0
 */
public interface IPersonService {
	/**
	 * @param pd 
	 * @方法名: listScheduling   
	 * @描述: 查询所有排班信息
	 * @作者: 郎国峰
	 * @时间: 2017年11月10日 下午2:01:41
	 * @return
	 */
	ArrayList<Scheduling> listScheduling(PageData pd);
	/**
	 * 
	 * @方法名: getQuery   
	 * @描述: 查询人员
	 * @作者: 赵星宇
	 * @时间: 2017年11月10日 下午3:25:19
	 * @param userId
	 * @return
	 */
	Person getQuery(String userId);
	/**
	 * @方法名: listDepartmenttype   
	 * @描述: 查询医生科室类别
	 * @作者: 郎国峰
	 * @时间: 2017年11月10日 下午5:47:35
	 */
	List<Departmenttype> listDepartmenttype();
	/**
	 * @方法名: listDepartmentDplId   
	 * @描述: 根据科室类型查询科室
	 * @作者: 郎国峰
	 * @时间: 2017年11月10日 下午6:46:04
	 * @param id
	 * @return
	 */
	List<Department> listDepartmentDplId(String id);
	/**
	 * @方法名: queryDoctorName   
	 * @描述: 根据科室id,动态查询医生信息
	 * @作者: 郎国峰
	 * @时间: 2017年11月10日 下午7:14:29
	 * @param id
	 * @return
	 */
	List<Person> queryDoctorName(String id);
	/**
	 * @方法名: docscheSave   
	 * @描述: 手动排班表添加保存
	 * @作者: 郎国峰
	 * @时间: 2017年11月10日 下午9:52:17
	 * @param scheduling
	 */
	void docscheSave(Scheduling scheduling);
	/**
	 * @方法名: docscheEdit   
	 * @描述: 手动排班修改保存
	 * @作者: 郎国峰
	 * @时间: 2017年11月10日 下午9:52:50
	 * @param scheduling
	 */
	void docscheEdit(Scheduling scheduling);
	/**
	 * @方法名: getSchedulingById   
	 * @描述: 根据id,查询一条手动排班信息
	 * @作者: 郎国峰
	 * @时间: 2017年11月10日 下午9:59:52
	 * @param id
	 * @return
	 */
	Scheduling getSchedulingById(String id);
	/**
	 * @方法名: listAutoDocsche   
	 * @描述: 查询自动排班表
	 * @作者: 郎国峰
	 * @时间: 2017年11月11日 上午12:40:41
	 * @return
	 */
	List<Map<String, String[]>> listAutoDocsche();
 
}
