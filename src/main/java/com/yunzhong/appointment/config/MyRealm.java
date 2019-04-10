package com.yunzhong.appointment.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.yunzhong.appointment.entity.SysUser;
import com.yunzhong.appointment.login.service.LoginService;

import javax.annotation.Resource;

/**
 * 
 * @className MyRealm
 * @description shiro用于验证及权限处理
 * @author 石洪刚
 * @time 2017年10月2日 下午7:19:25
 */
public class MyRealm extends AuthorizingRealm {
    @Resource
    private LoginService loginService;
    
    /**
	 * 
	 * @methodName doGetAuthorizationInfo
	 * @description 权限处理
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:14:41
	 * @param url
	 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser) super.getAvailablePrincipal(principals);
        if (user != null){
            // 权限信息对象 info，用来存放查出的用户的所有的角色及权限
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 用户的角色集合
            info.setRoles(loginService.listRoleNamesByUserId(user.getUserId()));
            // 用户的角色对应的所有权限
            //info.addStringPermission(loginService.getRoleName(user.getRoleId()));
            info.setStringPermissions(loginService.listPermissionsByUserId(user.getUserId()));
            return info;
        }
        // 返回 null 将会导致用户访问任何被拦截的请求时都会自动跳转到 unauthorizedUrl 指定的地址
        return null;
    }

    /**
	 * 
	 * @methodName doGetAuthenticationInfo
	 * @description 身份验证
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:14:41
	 * @param url
	 */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        SysUser user=loginService.getUserByName(userToken.getUsername());
        if (user != null){
            // 若存在，将此用户存放到登录认证 info 中，无需自己做密码对比，Shiro 会为我们进行密码对比校验
            return new SimpleAuthenticationInfo(user, user.getUserPass(), getName());
        }
        return null;
    }
}
