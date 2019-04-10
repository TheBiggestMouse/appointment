package com.yunzhong.appointment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.yunzhong.appointment.entity.City;

public interface CityMapper {
    int deleteByPrimaryKey(String id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
    /**
     * 
     * @Title: listCityByProvinceId   
     * @Description: TODO(根据省id查询所有的市)  
     * @author 郎国峰
     * @date 2017年10月27日 上午10:55:50      
     * @return: List<City>
     */
    @Select("select city_id, city_name, province_id  "+
			"from city  "+
			"where province_id = #{provinceId}")
    @ResultMap("BaseResultMap")
	List<City> listCityByProvinceId(String provinceId);
    /**
     * 
    * @Title: getCity 
    * @Description: 查询市的回显
    * @param @return    设定文件 
    * @return List<City>    返回类型 
    * @throws
     */
    @Select("SELECT city_id, city_name, province_id FROM city")
    @ResultMap("BaseResultMap")
	List<City> getCity();
    /**
     * 
    * @Title: getQuery 
    * @Description: 根据省id查询市信息
    * @param @param provinceId
    * @param @return    设定文件 
    * @return List<City>    返回类型 
    * @throws
     */
    @Select("SELECT city_id,province_id,city_name FROM city WHERE province_id=#{provinceId} ")
    @ResultMap("BaseResultMap")
	List<City> getQuery(String provinceId);
 
}