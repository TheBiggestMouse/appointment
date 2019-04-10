package com.yunzhong.appointment.mapper;

import org.apache.ibatis.annotations.Select;

import com.yunzhong.appointment.entity.Const;

public interface ConstMapper {
    int insert(Const record);

    int insertSelective(Const record);
    
    /**
     * @description 得到基础服务费
     * @author 石洪刚
     * @time 2017年9月12日16:21:16
     * @return
     */
    @Select("select const_val from const where const_id = 'base_fee'")
	String getBaseFee();
}