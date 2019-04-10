package com.yunzhong.appointment.mapper;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.yunzhong.appointment.entity.Appointmentorder;

public interface AppointmentorderMapper {
    int deleteByPrimaryKey(String appointmentId);

    int insert(Appointmentorder record);
    
    int insertSelective(Appointmentorder record);

    Appointmentorder selectByPrimaryKey(String appointmentId);

    int updateByPrimaryKeySelective(Appointmentorder record);

    int updateByPrimaryKey(Appointmentorder record);

    /**
     * 查询 热门科室
    * @Title: selectWelcomeDepartment
    * @author 康磊 
    * @return
    * List<Appointmentorder>
    * @throws
    * @date 2017年11月1日 下午3:07:28
     */
    @Select("select l.department_name, l.department_id  "+
    	" from ( "+
    		" 	select  count(*)  , department_name ,department_id "+
    		" 	from appointmentorder  "+
    		" 	where   TO_DAYS(NOW()) - TO_DAYS(appointment_date) < 7 "+
    		" 	GROUP BY department_name ORDER BY count(*) desc "+
    		" ) l ,(select @i:=0) as it") 
    @ResultMap("BaseResultMap")
	List<Appointmentorder> selectWelcomeDepartment();
    


    /**
     * 
    * @Title: patientsite 
    * @Description: 查询预约订单的地区
    * @param @return    设定文件 
    * @throws  赵星宇
     */
	@Select("select nm ,COUNT(lin.nm) "+
			 "  from ( "+
			 "		select p.province_name as nm "+
			 "		from appointmentorder a left join patient p on a.patient_id = p.patient_id "+
			 "		where  date_format(a.appointment_date,'%Y')='#{year}' "+
			 "		) lin "+
			 "    group by lin.nm ")
	@ResultMap("BaseResultMap")
	HashMap<String, ArrayList<HashMap<String, String>>> patientsite(String year);

	@Select("select aaaaa.qqqqq nm,IFNULL(aaaaa.xxxxx,0) countmnn "+
		"	from ( "+
		"			select province_name  qqqqq  , linzhong.countmn xxxxx "+
		"			from province linpro left join ( "+
		"					select p.province_name nm, count(p.province_name) countmn  "+
		"					from appointmentorder a,patient p ,province c where  a.patient_id = p.patient_id and p.province_id=c.province_id "+
		"					and  date_format(a.appointment_date,'%Y')=#{year} and p.province_name is not null "+
		"					group by a.patient_id  "+
		"					) linzhong on linpro.province_name = linzhong.nm "+
		"			) aaaaa ")
	@ResultMap("BaseResultMap")
	List<Appointmentorder> queryProvinceCount(String string);
	//查询日期
	@Select("select appointment_time from appointmentorder GROUP BY YEAR(appointment_time)")
	@ResultMap("BaseResultMap")
	List<Appointmentorder> yearApp();
	/**
	 * @description 通过年份获取年度疾病预约统计
	 * @author 赵星宇
	 * @time 2017年11月2日22:03:46
	 * @param getYear
	 * @return
	 */
	@Select("select DATE_FORMAT(appointment_time,'%Y-%m-%d') appointment_time, "+
			" illness_id,illness_name,count(illness_id) standby "+
			" from appointmentorder "+
			" WHERE YEAR(appointment_time) = #{getYear} and  illness_name is not null GROUP BY illness_id")
	@ResultMap("BaseResultMap")
	List<Appointmentorder> yearIllnessApp(String getYear);
	
	//根据年份查询  预约订单的
	@Select("select DATE_FORMAT(appointment_time,'%Y-%m-%d') appointment_time,  "+
			 "	appointment_id,illness_name,count(appointment_id) standby  "+
			 " from appointmentorder  "+
			 " WHERE YEAR(appointment_time) = '2017' and  appointment_id is not null GROUP BY appointment_id")
	@ResultMap("BaseResultMap")
	List<Appointmentorder> appointmentt(String getYear);

		//查预约订单

	@Select("select count(*) yycs,YEAR(appointment_date)nian from appointmentorder "+
	" GROUP BY  YEAR(appointment_date)")
	@ResultMap("BaseResultMap")
	List<Appointmentorder> queryYeartimes();


	/**
	 * @方法名: listDoctimesData   
	 * @描述: 根据传入的年份,查询医生被预约次数
	 * @作者: 郎国峰
	 * @时间: 2017年11月2日 下午6:45:47
	 * @param year 传入的年份
	 * @return
	 */
	@Select("select  doctor_name , count(*) docTimescount  from appointmentorder where date_format(appointment_date,'%Y') = #{year} group by doctor_name")
	@ResultMap("BaseResultMap")
	List<Appointmentorder> listDoctimesDataByYear(String year);

	/**
	 * @方法名: listDoctimesData   
	 * @描述: 查询医生被预约次数
	 * @作者: 郎国峰
	 * @时间: 2017年11月2日 下午9:03:14
	 * @return
	 */
	@Select("select  doctor_id , count(*)  docTimescount  from appointmentorder  group by doctor_id ")
	@ResultMap("BaseResultMap")
	List<Appointmentorder> listDoctimesData();
	
	@Select("select department_name, Count(appointment_id)countmnn from appointmentorder where date_format(appointment_date,'%Y') = #{year} GROUP BY department_name ORDER BY countmnn  ")
	@ResultMap("BaseResultMap")
	List<Appointmentorder> ajaxDeptQuery(String year);
}