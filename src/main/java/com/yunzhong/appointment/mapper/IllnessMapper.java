package com.yunzhong.appointment.mapper;



import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.yunzhong.appointment.entity.Illness;

public interface IllnessMapper {
    int deleteByPrimaryKey(String illId);

    int insert(Illness record);

    int insertSelective(Illness record);

    Illness selectByPrimaryKey(String illId);

    int updateByPrimaryKeySelective(Illness record);

    int updateByPrimaryKey(Illness record);
    /**
     * 根据疾病类别id 查询疾病
    * @Title: queryAllByIlltpId
    * @author 康磊 
    * @param illtpId
    * @return
    * List<Illness>
    * @throws
    * @date 2017年11月1日 下午3:15:48
     */
    @Select("SELECT ill_id,  illtp_id, ill_name, ill_info FROM illness where illtp_id = #{illtpId}")
    @ResultMap("BaseResultMap")
	List<Illness> queryAllByIlltpId(String illtpId);
    /**
     * 根据疾病名 查 疾病
    * @Title: selectIllnessByIllName
    * @author 康磊 
    * @param illName
    * @return
    * Illness
    * @throws
    * @date 2017年11月1日 下午4:11:33
     */
    @Select("SELECT ill_id,  illtp_id, ill_name, ill_info FROM illness where ill_name like concat(concat('%',#{ill_name}),'%')")
    @ResultMap("BaseResultMap")
	Illness selectIllnessByIllName(String illName);

	
}