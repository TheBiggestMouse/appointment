package com.yunzhong.appointment.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.yunzhong.appointment.entity.SysRole;
import com.yunzhong.appointment.util.PageData;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
   
    /**
     * @description 根据角色名称查询角色信息
     * @author 石洪刚
     * @time 2017年8月10日11:11:23
     * @param roleId
     * @return
     */
    @Select("select role_name from sys_role where role_id=#{roleId}")
	String getRoleNameById(String roleId);
    /**
     * @description 根据用户主键查询用户所有角色名称
     * @author 石洪刚
     * @time 2017年8月10日11:12:27
     * @param userId
     * @return
     */
    @Select("SELECT role_name FROM sys_role WHERE role_id IN (SELECT role_id FROM sys_user_role WHERE user_id = #{userId})")
    @ResultType(Set.class)
	Set<String> listRoleNamesByUserId(String userId);
 
    /**
     * 
     * @methodName listRoleByUserId
     * @description 根据用户主键得到用户关联的角色主键
     * @author 石洪刚
     * @time 2017年10月6日 上午11:21:20
     * @param id
     * @return
     */
    @Select("select sr.role_id from sys_role sr left join sys_user_role sur on sr.role_id = sur.role_id where sur.user_id=#{id}")
	@ResultType(String.class)
    List<String> listRoleByUserId(String id);
   /**
    * 查询  角色
   * @Title: listrole
   * @author 康磊 
   * @param pd
   * @return
   * List<SysRole>
   * @throws
   * @date 2017年10月27日 上午11:21:14
    */
	List<SysRole> listrole(PageData pd);
	/**
     * 
     * @methodName listRole
     * @description 得到角色数据
     * @author 石洪刚
     * @time 2017年10月6日 上午11:44:06
     * @return
     */
    @Select("select role_id,role_name from sys_role")
    @ResultMap("BaseResultMap")
	List<SysRole> listRole();

    /**
     * 根据roleName查询 用于验证角色名称是否重复
    * @Title: selectByRoleName
    * @author 康磊 
    * @param roleName
    * @return
    * String
    * @throws
    * @date 2017年10月29日 下午11:07:03
     */
    @Select("select role_name from sys_role where role_name=#{roleName}")
	String selectByRoleName(String roleName);

    /**
     * 
    * @Title: selectByRoleName
    * @author 康磊 
    * @param roleName
    * @return
    * String
    * @throws
    * @date 2017年10月31日 13:07:45
     */
    @Select("SELECT role_name from sys_role r inner join sys_user_role sur on sur.role_id=r.role_id inner join sys_user u on u.user_id=sur.user_id where u.user_id=#{userId}")
	String getRoleName(String userId);

    
}