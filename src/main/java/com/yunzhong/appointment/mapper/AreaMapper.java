package com.yunzhong.appointment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.yunzhong.appointment.entity.Area;

public interface AreaMapper {
    int deleteByPrimaryKey(String areaId);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(String areaId);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
   /**
    * 
    * @方法名: listAreaByCityId   
    * @描述: 传入城市id,根据城市id查询区县信息
    * @作者: 郎国峰
    * @时间: 2017年10月27日 下午2:11:58
    * @param cityId 传入的城市id
    * @return 返回区县信息的list
    */
    @ResultMap("BaseResultMap")
    @Select("select area_id, area_name, city_id "+
			" from area "+
			" where city_id = #{cityId}")
	List<Area> listAreaByCityId(String cityId);
    /***
     * 
     * 
    * @Title: getArea 
    * @Description: 根据用户市id查询区
    * @param @param cityId
    * @param @return    设定文件 
    * @return List<Area>    返回类型 
    * @throws
     */
    @Select("select area_id, area_name, city_id "+
			" from area "+
			" where city_id = #{cityId}")
    @ResultMap("BaseResultMap")
    List<Area> getArea(String cityId);
}