package com.yunzhong.appointment.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.yunzhong.appointment.entity.Province;

public interface ProvinceMapper {
    int deleteByPrimaryKey(String provinceId);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(String provinceId);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);
    /**
     * @Title: listProvince   
     * @Description: TODO(查询所有的省信息)  
     * @author 郎国峰
     * @date 2017年10月27日 上午8:02:34 
     */
    List<Province> listProvince();
    /**
     * 查询省
    * @Title: getProvince 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @return    设定文件 
    * @return List<Province>    返回类型 
    * @throws
     */
    @Select(" SELECT province_id,province_name FROM province ")
    @ResultMap("BaseResultMap")
	List<Province> getProvince();
    /***
     * 
    * @Title: allProvince 
    * @Description: 查询所有的省
    * @param @return    设定文件 
    * @return List<Province>    返回类型 
    * @throws 赵星宇
     */
    @Select("select province_name from province")
    @ResultMap("BaseResultMap")
	List<Province> allProvince();
    
    
}