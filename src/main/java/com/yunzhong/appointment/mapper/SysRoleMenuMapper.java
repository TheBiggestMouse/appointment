package com.yunzhong.appointment.mapper;

import org.apache.ibatis.annotations.Delete;

import com.yunzhong.appointment.entity.SysRoleMenu;

public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(String rmId);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(String rmId);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);
    /**
     * 根据角色id删除
    * @Title: removeOneRoleMenu
    * @author 康磊 
    * @Description: TODO(根据角色id删除)
    * @param id
    * void
    * @throws
    * @date 2017年10月27日 上午11:18:21
     */
    @Delete("delete from sys_role_menu where role_id = #{id}")
	void removeOneRoleMenu(String id);
    /**
     * 根据菜单 id 删除一条数据
    * @Title: deleteByMenuId
    * @author 康磊 
    * @param id
    * void
    * @throws
    * @date 2017年10月30日 上午11:58:52
     */
    @Delete("delete from sys_role_menu where menu_id = #{id}")
	void deleteByMenuId(String id);
    

	
}