package com.yunzhong.appointment.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.yunzhong.appointment.entity.SysMenu;
import com.yunzhong.appointment.util.SelectOptions;

public interface SysMenuMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
    /**
     * @description 根据用户主键查询用户拥有的合法请求
     * @author 石洪刚
     * @time 2017年8月10日11:36:42
     * @param userId
     * @return
     */
    @Select("select sm.MENU_URL from sys_menu sm "+
			"  left join sys_role_menu srm on sm.MENU_ID=srm.MENU_ID "+
			"  left join sys_role sr on srm.ROLE_ID=sr.ROLE_ID "+
			"  left join sys_user_role sur on sr.ROLE_ID=sur.ROLE_ID "+
			"  where sur.USER_ID=#{userId}")
    @ResultType(Set.class)
	Set<String> listPermissionsByUserId(String userId);
    
    /**
     * @description 根据父菜单查询下级子菜单
     * @author 石洪刚
     * @time 2017年8月10日13:51:29
     * @param menu
     * @return
     */
    @Select("select menu_id, sys_menu_id, menu_name, menu_url, menu_islink, menu_sequ, menu_state, menu_info from sys_menu where sys_menu_id=#{menuId} order by menu_sequ")
    @ResultMap("BaseResultMap")
	List<SysMenu> listMenuByPar(SysMenu menu);
    
    /**
     * @description 根据用户主键查询一级菜单
     * @author 石洪刚
     * @time 2017年8月10日19:36:04
     * @param userId
     * @return
     */
    @Select("select sm.menu_id, sm.sys_menu_id, sm.menu_name, sm.menu_url, sm.menu_islink, sm.menu_sequ, sm.menu_state, sm.menu_info from sys_menu sm "+
			"  left join sys_role_menu srm on sm.MENU_ID=srm.MENU_ID "+
			"  left join sys_role sr on srm.ROLE_ID=sr.ROLE_ID "+
			"  left join sys_user_role sur on sr.ROLE_ID=sur.ROLE_ID "+
			"  where sur.USER_ID=#{userId} and sm.menu_islink=1"
			+ " order by sm.menu_sequ")
    @ResultMap("BaseResultMap")
	List<SysMenu> listTopMenuByUserId(String userId);
    /**
     * @description 查询游客一级菜单
     * @author 石洪刚
     * @time 2017年8月29日20:36:32
     * @return
     */
    @Select("select sm.menu_id, sm.sys_menu_id, sm.menu_name, sm.menu_url, sm.menu_islink, sm.menu_sequ, sm.menu_state, sm.menu_info from sys_menu sm "+
			"  left join sys_role_menu srm on sm.MENU_ID=srm.MENU_ID "+
			"  left join sys_role sr on srm.ROLE_ID=sr.ROLE_ID "+
			"  left join sys_user_role sur on sr.ROLE_ID=sur.ROLE_ID "+
			"  left join sys_user su on sur.USER_ID=su.USER_ID "+
			"  where su.USER_NAME='GUEST' and sm.menu_islink=1"
			+ " order by sm.menu_sequ")
    @ResultMap("BaseResultMap")
	List<SysMenu> listGuestTopMenu();
	/**
	 * @description 根据上级菜单主键及用户查出该用户所属的下级菜单
	 * @author 石洪刚
	 * @time 2017年8月29日20:22:26
	 * @param m
	 * @return
	 */
	@Select("SELECT menu_id, sys_menu_id, menu_name, menu_url, menu_islink, menu_sequ, menu_state, menu_info FROM sys_menu WHERE sys_menu_id = #{menuId} and menu_id in (SELECT menu_id FROM sys_role_menu WHERE role_id in (SELECT role_id FROM sys_user_role WHERE user_id = #{userId})) order by menu_sequ")
	@ResultMap("BaseResultMap")
	List<SysMenu> listMenuByParIdAndUserId(Map m);
	/**
	 * @description 得到游客特定菜单所有下级菜单
	 * @author 石洪刚
	 * @time 2017年8月29日20:43:18
	 * @param menu
	 * @return
	 */
	@Select("SELECT menu_id, sys_menu_id, menu_name, menu_url, menu_islink, menu_sequ, menu_state, menu_info FROM sys_menu WHERE sys_menu_id = #{menuId} and menu_id in (SELECT menu_id FROM sys_role_menu WHERE role_id in (SELECT role_id FROM sys_user_role WHERE user_id = (select user_id from sys_user where user_name='GUEST'))) order by menu_sequ")
	@ResultMap("BaseResultMap")
	List<SysMenu> listGuestMenuByParId(SysMenu menu);
	
	/**
	 * @description 查询个人中心菜单
	 * @author 石洪刚
	 * @time 2017年8月31日10:03:46
	 * @return
	 */
	@Select("select menu_id, sys_menu_id, menu_name, menu_url, menu_islink, menu_sequ, menu_state, menu_info from sys_menu where menu_name='个人中心'")
	@ResultMap("BaseResultMap")
	SysMenu getPersonalMenu();
	
	/**
	 * 
	 * @methodName listTopMenu
	 * @description 得到所有一级菜单
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:44:12
	 * @return
	 */
	 @Select("select sm.menu_id, sm.sys_menu_id, sm.menu_name, sm.menu_url, sm.menu_islink, sm.menu_sequ, sm.menu_state, sm.menu_info from sys_menu sm  where sm.menu_islink=1 order by sm.menu_sequ")
	 @ResultMap("BaseResultMap")
	List<SysMenu> listTopMenu();
	/**
	 * 
	 * @methodName getMenuWithParNameById
	 * @description 根据菜单主键得到菜单信息，及父菜单名称
	 * @author 石洪刚
	 * @time 2017年10月2日 下午10:43:45
	 * @param id
	 * @return
	 */
	 @Select("select menu.menu_id,menu.menu_info,menu.menu_islink,menu.menu_name,menu.menu_sequ,menu.menu_url,menu.sys_menu_id,parmenu.menu_name par_name from sys_menu menu "
	 		+ " left join sys_menu parmenu on menu.sys_menu_id=parmenu.menu_id "
	 		+ " where menu.menu_id=#{id}")
	 @ResultMap("BaseResultMap")
	SysMenu getMenuWithParNameById(String id);

	 /**
	  *根据角色主键查询关联的 菜单主键
	 * @Title: listMenuByUserId
	 * @author 康磊 
	 * @param id
	 * @return
	 * List<String>
	 * @throws
	 * @date 2017年10月27日 下午6:24:08
	  */
	@Select("select sm.menu_id from sys_menu sm left join sys_role_menu srm on sm.menu_id = srm.menu_id where srm.role_id=#{id}")
	@ResultType(String.class)
	List<String> listMenuByUserId(String id);
	/**
	 *得到菜单
	* @Title: listMenu
	* @author 康磊 
	* @return
	* List<SysMenu>
	* @throws
	* @date 2017年10月27日 下午6:25:46
	 */
	@Select("select menu_id,menu_name from sys_menu")
    @ResultMap("BaseResultMap")
	List<SysMenu> listMenu();
	 
	/**
	 * 
	 * @methodName queryAllMenuByTopWithOptions
	 * @description 获得一级菜单
	 * @author 于琦
	 * @time 2017年10月26日下午9:26:24
	 * @param
	 */
    List<SelectOptions> queryAllMenuByTopWithOptions();
    
    /**
     * 
     * @methodName queryMenuByOneWithOptions
     * @description 获得下级菜单
     * @author 于琦
     * @time 2017年10月26日下午10:19:08
     * @param sp
     */
    List<SelectOptions> queryMenuByOneWithOptions(SelectOptions sp);
    
    /**
     * 
     * @methodName getMenuByMenuName
     * @description 通过菜单名查询菜单
     * @author 于琦
     * @time 2017年10月27日下午1:52:58
     * @param menuName
     */
    @Select("select menu_id from sys_menu where menu_name=#{menuName}")
    @ResultMap("BaseResultMap")
    SysMenu getMenuByMenuName(String menuName);
    
    /**
     * 
     * @methodName getMenuByUrl
     * @description 根据url查询菜单
     * @author 于琦
     * @time 2017年10月27日下午1:48:48
     * @param url
     */
    @Select("select menu_id from sys_menu where menu_url=#{url}")
    @ResultMap("BaseResultMap")
    SysMenu getMenuByUrl(String url);
    /**
     * 根据传入父菜单id获得父菜单名称
    * @Title: getParNameByParMenuId
    * @author 康磊 
    * @param parMenuId
    * @return
    * SysMenu
    * @throws
    * @date 2017年10月30日 上午11:04:16
     */
    @Select("select menu_name,menu_islink from sys_menu where menu_id=#{parMenuId}")
	@ResultMap("BaseResultMap")
	SysMenu getParNameByParMenuId(String parMenuId);
    /**
     * 根据传入父菜单名称获得父菜单ID
    * @Title: getparMenuIdByparMenuName
    * @author 康磊
    * @param parMenuName
    * @return
    * String
    * @throws
    * @date 2017年10月30日 上午11:23:39
     */
    @Select("select menu_id from sys_menu where menu_name=#{parMenuName}")
	String getparMenuIdByparMenuName(String parMenuName);
}