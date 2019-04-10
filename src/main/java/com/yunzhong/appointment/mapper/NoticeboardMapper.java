package com.yunzhong.appointment.mapper;

import com.yunzhong.appointment.entity.Noticeboard;

public interface NoticeboardMapper {
    int deleteByPrimaryKey(String noticeId);

    int insert(Noticeboard record);

    int insertSelective(Noticeboard record);

    Noticeboard selectByPrimaryKey(String noticeId);

    int updateByPrimaryKeySelective(Noticeboard record);

    int updateByPrimaryKey(Noticeboard record);
}