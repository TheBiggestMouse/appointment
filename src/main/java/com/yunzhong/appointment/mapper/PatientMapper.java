package com.yunzhong.appointment.mapper;



import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yunzhong.appointment.entity.Patient;
import com.yunzhong.appointment.entity.SysUser;

public interface PatientMapper {
    int deleteByPrimaryKey(String patientId);

    int insert(Patient record);

    int insertSelective(Patient record);

    Patient selectByPrimaryKey(String patientId);

    int updateByPrimaryKeySelective(Patient record);

    int updateByPrimaryKey(Patient record);
    /**
     * 
     * @methodName updateUserInfo
     * @description 修改患者中的用户信息
     * @author 石洪刚
     * @time 2017年10月5日 下午7:22:37
     * @param user
     */
    @Update("UPDATE patient SET user_name = #{userName} where user_id=#{userId}")
	void updateUserInfo(SysUser user);
    /**
     * 
     * @methodName updateUserInfoNullByUserId
     * @description 设置用户信息为NULL
     * @author 石洪刚
     * @time 2017年10月5日 下午7:37:46
     * @param id
     */
    @Update("UPDATE patient SET user_name = NULL,user_id = NULL where user_id=#{id}")
	void updateUserInfoNullByUserId(String id);
    /***
     * 
    * @Title: getQuery 
    * @Description: 根据ID查询患者信息 
    * @param @param userId
    * @param @return    设定文件 
    * @return Patient    返回类型 
    * @throws
     */
    @Select("SELECT patient_id,user_id,user_name,patient_name,patient_tel,patient_sex,patient_uid, "+
   " patient_birth,province_id,province_name,city_id,city_name,area_id,area_name,patient_time,standby, "+
   " patient_pic from patient where user_id=#{userId} " )
    @ResultMap("BaseResultMap")
	Patient getQuery(String userId);

	 /**
     * 
    * @Title: getCityId 
    * @Description: 根据患者查询省ID
    * @param @param id
    * @param @return    设定文件 
    * @return Patient    返回类型 
    * @throws
     */
    @Select("SELECT province_id, user_id FROM patient where user_id=#{id}")
    @ResultMap("BaseResultMap")
	Patient getProvinceId(String id);
    @Select("SELECT city_id, user_id FROM patient where user_id=#{id}")
    @ResultMap("BaseResultMap")
	Patient getCityId(String id);
 

    /**
	 * 
	 * @方法名: telVerify   
	 * @描述: 验证身份证号在数据库是否唯一
	 * @作者: 郎国峰
	 * @时间: 2017年10月30日08:45:55
	 * @param patientUid 传入需要验证的身份证号
	 * @return 返回是否重复   1 重复  0 不重复                                                                           
	 */
    @Select("select patient_id from patient where patient_uid = #{patientUid}")
    @ResultMap("BaseResultMap")
	Patient getPatient(String patientUid);
    //修改头像
    int updatetou (Patient record);
	

    
}