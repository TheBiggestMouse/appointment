package com.yunzhong.appointment.mapper;



import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.yunzhong.appointment.entity.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String dpId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String dpId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    /**
     * 根据科室类别id 查询科室
    * @Title: queryAll
    * @author 康磊 
    * @return
    * List<Department>
    * @throws
    * @date 2017年10月31日 下午8:12:32
     */
    @Select("SELECT  dp_id,  dpl_id,  dp_name FROM	department where dpl_id=#{dplId}")
    @ResultMap("BaseResultMap")
    List<Department> queryAll(String dplId);
 

	
   
}