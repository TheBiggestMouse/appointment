package com.yunzhong.appointment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.yunzhong.appointment.entity.SysUser;
import com.yunzhong.appointment.util.PageData;

public interface SysUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    /**
     * @description 根据用户名和密码查询用户对象
     * @author 石洪刚
     * @time 2017年8月10日11:28:36
     * @param user1
     * @return
     */
    @Select("select * from sys_user where user_name = #{userName} and user_pass=#{userPass}")
    @ResultMap("BaseResultMap")
	SysUser getUserByNameAndPass(SysUser user1);
    
    /**
     * @description 根据用户名查询用户对象
     * @author 石洪刚
     * @time 2017年8月10日11:29:17
     * @param username
     * @return
     */
    @Select("select user_id, user_name, user_pass, user_nickname, user_state, user_info from sys_user where user_name = #{userName}")
    @ResultMap("BaseResultMap")
	SysUser getUserByName(String username);
    
    /**
	 * @description 查询未关联用户
	 * @author 石洪刚
	 * @time 2017年9月12日10:47:45
	 * @return
	 */
    @Select("SELECT su.user_id,su.user_name FROM sys_user su "+
    		" LEFT JOIN person p on su.user_id = p.user_id "+
    		" LEFT JOIN patient pat on su.user_id = pat.user_id "+
    		"WHERE "+
    		" p.user_id is null and pat.user_id is null and su.user_name <> 'GUEST'")
    @ResultMap("BaseResultMap")
	List<SysUser> listUserNotLinked();
    
    /**
	 * @description 根据医生主键查询被关联用户
	 * @param id
	 * @return
	 */
    @Select("select user_id,user_name from sys_user where user_id = (select user_id from person where pp_id = #{id})")
    @ResultMap("BaseResultMap")
	SysUser getLinkedUserByPpId(String id);
    /**
     * 
     * @methodName listUser
     * @description 查询用户数据
     * @author 石洪刚
     * @time 2017年10月2日 下午11:46:14
     * @param pd
     */
    List<SysUser> listUser(PageData pd);
    /**
     * 
     * @methodName getUSerByUserName
     * @description 根据登录名得到一个用户对象
     * @author 石洪刚
     * @time 2017年10月5日 上午8:12:57
     * @param userName
     * @return
     */
    @Select("select user_id from sys_user where user_name = #{userName}")
    @ResultMap("BaseResultMap")
	SysUser getUSerByUserName(String userName);
    /**
     * 
     * @方法名: getUser   
     * @描述: 根据前台传入的电话号码,查询一条SysUser数据
     * @作者: 郎国峰
     * @时间: 2017年10月27日 下午6:12:39
     * @param tel 前台传入的电话号码   也是用户名
     * @return 返回的user数据
     */
    @Select("select user_id from sys_user where user_name = #{tel}")
    @ResultMap("BaseResultMap")
	SysUser getUser(String tel);
   
}