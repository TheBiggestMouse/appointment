package com.yunzhong.appointment.mapper;



import com.yunzhong.appointment.entity.Professional;

public interface ProfessionalMapper {
    int deleteByPrimaryKey(String professionalId);

    int insert(Professional record);

    int insertSelective(Professional record);

    Professional selectByPrimaryKey(String professionalId);

    int updateByPrimaryKeySelective(Professional record);

    int updateByPrimaryKey(Professional record);

    
}