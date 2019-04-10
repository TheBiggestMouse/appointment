package com.yunzhong.appointment.mapper;



import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.yunzhong.appointment.entity.Illnessposition;

public interface IllnesspositionMapper {
    int deleteByPrimaryKey(String illtpId);

    int insert(Illnessposition record);

    int insertSelective(Illnessposition record);

    Illnessposition selectByPrimaryKey(String illtpId);

    int updateByPrimaryKeySelective(Illnessposition record);

    int updateByPrimaryKey(Illnessposition record);
    /**
     * 查询所有疾病
    * @Title: qureyAll
    * @author 康磊 
    * @return
    * List<Illnessposition>
    * @throws
    * @date 2017年11月1日 下午3:07:01
     */
    @Select("SELECT illtp_id, illtp_name FROM illnessposition")
	@ResultMap("BaseResultMap")
	List<Illnessposition> qureyAll();
    
}