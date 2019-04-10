package com.yunzhong.appointment.mapper;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yunzhong.appointment.entity.Person;

public interface PersonMapper {
    int deleteByPrimaryKey(String ppId);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(String ppId);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
   
	/**
	 * 
	 * @methodName setUserIdNullByUserId
	 * @description 设置用户主键为NULL
	 * @author 石洪刚
	 * @time 2017年10月5日 下午7:35:48
	 * @param id
	 */
	@Update("update person set user_id = NULL where user_id = #{id}")
	void updateUserIdNullByUserId(String id);
	/**
	 * 
	* @Title: getQuery 
	* @Description: 根据ID查询人员信息
	* @param @param userId
	* @param @return    设定文件 
	* @return Person    返回类型 
	* @throws
	 */
	@Select("SELECT person.pp_id, person.user_id, person.pp_name, person.pp_info, person.doctor_domain, "+
  " person.pp_state, person.pp_time, person.department_id, person.department_name, "+
" person.professional_name, person.professional_id, person.doctor_pay,  person.pp_pic, person.standby FROM "+
" person  WHERE user_id=#{userId} ")
	Person getQuery(String userId);
	/**
	 * 根据科室name 查询人员(医生)
	* @Title: queryPersonBydpName
	* @author 康磊 
	* @param dpName
	* @return
	* List<Person>
	* @throws
	* @date 2017年10月31日 下午10:07:12
	 */
	@Select("SELECT  pp_id,  user_id,  pp_name,  pp_info,  doctor_domain,  pp_state, pp_time,  department_id, "+ 
			" department_name,  professional_id,  professional_name,  doctor_pay,  pp_pic,  standby "+ 
			" FROM person WHERE  department_name like  CONCAT(CONCAT('%', #{dpName}), '%')")
	@ResultMap("BaseResultMap")
	List<Person> queryPersonBydpName(String dpName);
	/**
	 * 根据id  查询     
	 *     
	* @Title: selectPersonById
	* @author 康磊 
	* @param doctorId
	* @return
	* Person
	* @throws
	* @date 2017年11月1日 下午4:23:38
	 */
	@Select("SELECT  pp_id,  user_id,  pp_name,  pp_info,  doctor_domain,  pp_state, pp_time,  department_id, "+ 
			" department_name,  professional_id,  professional_name,  doctor_pay,  pp_pic,  standby "+ 
			" FROM person WHERE  pp_id = #{doctorId}")
	@ResultMap("BaseResultMap")
	Person selectPersonById(String doctorId);
	/**
	 * @方法名: listOutCallCount   
	 * @描述: 查询医生排班出诊次数
	 * @作者: 郎国峰
	 * @时间: 2017年11月3日 上午6:13:43
	 * @return
	 */
	@Select("select p.pp_id  , sum(IFNULL(count1,0)+IFNULL(count2,0)+IFNULL(count3,0)+IFNULL(count4,0)+IFNULL(count5,0)+IFNULL(count6,0)) outcallcount "
		 + " from  scheduling  s left join person p on s.doctor_id = p.pp_id GROUP BY p.pp_id")
	@ResultMap("BaseResultMap")
	List<Person> listOutCallCount();
	/**
	 * @方法名: queryAllDoctor   
	 * @描述: 查询医院所有医生id,和姓名
	 * @作者: 郎国峰
	 * @时间: 2017年11月3日 上午7:50:08
	 * @return
	 */
	@Select("select p.pp_id , p.pp_name  "
			+ "from person p left join sys_user_role ur on p.user_id = ur.user_id  "
			+ "left join sys_role r on ur.role_id = r.role_id  "
			+ "where r.role_name = 'doctor' ")
	@ResultMap("BaseResultMap")
	List<Person> listAllDoctor();
	/**
	 * @方法名: queryDoctorNameByDeptId   
	 * @描述: 根据科室id,查询医生姓名和医生id
	 * @作者: 郎国峰
	 * @时间: 2017年11月10日 下午7:17:11
	 * @param id
	 * @return
	 */
	@Select("SELECT pp_id,pp_name FROM person where department_id = #{id}")
	@ResultMap("BaseResultMap")
	List<Person> queryDoctorNameByDeptId(String id);

}