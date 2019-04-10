package com.yunzhong.appointment.mapper;

import com.yunzhong.appointment.entity.Hostipal;

public interface HostipalMapper {
    int deleteByPrimaryKey(String hostipalId);

    int insert(Hostipal record);

    int insertSelective(Hostipal record);

    Hostipal selectByPrimaryKey(String hostipalId);

    int updateByPrimaryKeySelective(Hostipal record);

    int updateByPrimaryKey(Hostipal record);
}