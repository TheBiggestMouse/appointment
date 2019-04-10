package com.yunzhong.appointment.mapper;



import java.util.List;

import com.yunzhong.appointment.entity.Visitsettime;

public interface VisitsettimeMapper {
    int deleteByPrimaryKey(String visitstId);

    int insert(Visitsettime record);

    int insertSelective(Visitsettime record);

    Visitsettime selectByPrimaryKey(String visitstId);

    int updateByPrimaryKeySelective(Visitsettime record);

    int updateByPrimaryKey(Visitsettime record);
    /**
     * @方法名: queryAll   
     * @描述: 查询所有的自动排班
     * @作者: 郎国峰
     * @时间: 2017年11月11日 上午12:45:45
     * @return
     */
	List<Visitsettime> queryAll();

    
}