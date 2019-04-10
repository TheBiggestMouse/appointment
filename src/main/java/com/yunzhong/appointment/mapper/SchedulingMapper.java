package com.yunzhong.appointment.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.yunzhong.appointment.entity.Scheduling;
import com.yunzhong.appointment.util.PageData;

public interface SchedulingMapper {
    int deleteByPrimaryKey(String schedulingId);

    int insert(Scheduling record);

    int insertSelective(Scheduling record);

    Scheduling selectByPrimaryKey(String schedulingId);

    int updateByPrimaryKeySelective(Scheduling record);

    int updateByPrimaryKey(Scheduling record);
    /**
     * 
    * @Title: queryAllFutureInfo 
    * @Description: 查询医生所有信息 
    * @param @param doctorId
    * @param @return    设定文件 
    * @return List<Scheduling>    返回类型 
    * @throws
     */
	List<Scheduling> queryAllFutureInfo(String doctorId);
	/**
	 * @方法名: listScheduling   
	 * @描述: 查询所有手动排班信息
	 * @作者: 郎国峰
	 * @时间: 2017年11月10日 下午2:13:35
	 * @param pd 条件查询参数
	 * @return
	 */
	ArrayList<Scheduling> listScheduling(PageData pd);
	/**
	 * @方法名: getSchedulingById   
	 * @描述: 根据id,查询一条手动排班信息
	 * @作者: 郎国峰
	 * @时间: 2017年11月10日 下午10:04:27
	 * @param id
	 * @return
	 */
	@ResultMap("BaseResultMap")
	@Select("  SELECT scheduling.scheduling_id,scheduling.doctor_id,scheduling.scheduling_date,scheduling.time1,scheduling.time2,scheduling.time3, "+
	" 	   scheduling.time4,scheduling.time5,scheduling.time6,scheduling.count1,scheduling.count2,scheduling.count3,scheduling.count4, "+
	" 	   scheduling.count5,scheduling.count6,scheduling.standby,person.pp_name,person.department_name "+
	" FROM   scheduling left join person on scheduling.doctor_id = person.pp_id "+
	" where scheduling.scheduling_id = #{id} ")
	Scheduling getSchedulingById(String id);
    
   
}