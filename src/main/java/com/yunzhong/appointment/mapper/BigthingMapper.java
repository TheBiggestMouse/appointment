package com.yunzhong.appointment.mapper;

import com.yunzhong.appointment.entity.Bigthing;

public interface BigthingMapper {
    int deleteByPrimaryKey(String bigthingId);

    int insert(Bigthing record);

    int insertSelective(Bigthing record);

    Bigthing selectByPrimaryKey(String bigthingId);

    int updateByPrimaryKeySelective(Bigthing record);

    int updateByPrimaryKey(Bigthing record);
}