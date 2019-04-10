package com.yunzhong.appointment.mapper;


import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.yunzhong.appointment.entity.DoctorIllness;

public interface DoctorIllnessMapper {
    int deleteByPrimaryKey(String docillId);

    int insert(DoctorIllness record);

    int insertSelective(DoctorIllness record);

    DoctorIllness selectByPrimaryKey(String docillId);

    int updateByPrimaryKeySelective(DoctorIllness record);

    int updateByPrimaryKey(DoctorIllness record);
    /**
     * 疾病id 查医生id
    * @Title: selectDoctorIdByIllID
    * @author 康磊 
    * @param illId
    * @return
    * List<DoctorIllness>
    * @throws
    * @date 2017年11月1日 下午4:15:08
     */
    @Select("SELECT docill_id, ill_id, doctor_id FROM doctor_illness where ill_id= #{illId}")
  	@ResultMap("BaseResultMap")
	List<DoctorIllness> selectDoctorIdByIllID(String illId);
    
}