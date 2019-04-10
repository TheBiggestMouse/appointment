package com.yunzhong.appointment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.yunzhong.appointment.entity.Departmenttype;
import com.yunzhong.appointment.util.PageData;

public interface DepartmenttypeMapper {
    int deleteByPrimaryKey(String[] ids);

    int insert(Departmenttype record);

    int insertSelective(Departmenttype record);

    Departmenttype selectByPrimaryKey(String dplId);

    int updateByPrimaryKeySelective(Departmenttype record);

    int updateByPrimaryKey(Departmenttype record);
/**
 * 
* @Title: listdpl 
* @Description:根据部门查询部门信息
* @param @param pd
* @param @return    设定文件 
* @return List    返回类型 
* @throws
 */
    @Select("select dpl_name from sys_dpl where dpl_id=#{dplId}")
	List listdpl(PageData pd);
	/**
	 * 
	* @Title: selectByName 
	* @Description: 查询部门名字
	* @param @param dplname
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws
	 */

	Object selectByName(String dplname);
	/**
	 * 
	* @Title: removeDpl 
	* @Description: 清除
	* @param @param ids
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws
	 */
	Object removeDpl(String[] ids);
	/**
	 * 查询所有
	* @Title: qureyAll
	* @author 康磊 
	* @return
	* List<Departmenttype>
	* @throws
	* @date 2017年10月31日 下午8:07:38
	 */
	@Select("SELECT dpl_id, dpl_name FROM departmenttype")
	@ResultMap("BaseResultMap")
	List<Departmenttype> qureyAll();
    
}