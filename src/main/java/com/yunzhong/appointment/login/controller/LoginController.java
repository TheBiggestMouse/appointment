package com.yunzhong.appointment.login.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yunzhong.appointment.config.Const;
import com.yunzhong.appointment.config.SessionConst;
import com.yunzhong.appointment.entity.SysMenu;
import com.yunzhong.appointment.entity.SysUser;
import com.yunzhong.appointment.login.service.LoginService;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @className LoginController
 * @description 登录控制器
 * @author 石洪刚
 * @time 2017年10月2日 下午7:23:18
 */
@Controller
public class LoginController {

    private static Logger logger=Logger.getLogger(LoginController.class);
    @Resource
    private LoginService loginService;
   
    /**
     * 
     * @methodName login
     * @description 跳转登录页
     * @author 石洪刚
     * @time 2017年10月2日 下午7:23:36
     * @return
     */
    @RequestMapping("/login")
    public String login(){
    	
        return "login";
    }
    
    
    
    /**
     * 
     * @methodName login
     * @description 用户点击登录后的处理
     * @author 石洪刚
     * @time 2017年10月2日 下午7:23:51
     * @param user
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/success")
    public  String login(SysUser user, HttpServletRequest request, RedirectAttributes redirectAttributes){
    	String passwd = null;
    	
    	if(user.getUserPass()!=null){
    		
    		//加密
    		passwd = new SimpleHash("SHA-1", user.getUserPass(), user.getUserPass()+Const.SALT).toString();
    	
    	}
    	// 验证
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), passwd);
        // 获取当前的 Subject
        Subject currentUser = SecurityUtils.getSubject();
        
        try {
            // 在调用了 login 方法后, SecurityManager 会收到 AuthenticationToken, 并将其发送给已配置的 Realm 执行必须的认证检查
            // 每个 Realm 都能在必要时对提交的 AuthenticationTokens 作出反应
            // 所以这一步在调用 login(token) 方法时, 它会走到 MyRealm.doGetAuthenticationInfo() 方法中, 具体验证方式详见此方法
            logger.info(" 进行登录验证.. 验证开始");
            currentUser.login(token);
            logger.info("进行登录验证.. 验证通过");
        }catch(UnknownAccountException uae){
            logger.info("进行登录验证.. 验证未通过, 未知账户");
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "未知账户");
        }catch(IncorrectCredentialsException ice){
            logger.info("进行登录验证.. 验证未通过, 错误的凭证");
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "密码不正确");
        }catch(LockedAccountException lae){
            logger.info("对用户  进行登录验证.. 验证未通过, 账户已锁定");
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            logger.info("对用户进行登录验证.. 验证未通过, 错误次数大于 5 次, 账户已锁定");
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "用户名或密码错误次数大于 5 次, 账户已锁定");
        }catch (DisabledAccountException sae){
            logger.info("对用户进行登录验证.. 验证未通过, 帐号已经禁止登录");
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "帐号已经禁止登录");
        }catch(AuthenticationException ae){
            // 通过处理 Shiro 的运行时 AuthenticationException 就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户  进行登录验证.. 验证未通过, 堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "用户名或密码不正确");
        }
        // 验证是否登录成功
        if(currentUser.isAuthenticated()){
            // 把当前用户放入 session
            Session session = currentUser.getSession();
            SysUser tUser = loginService.getUserByName(user.getUserName());
            session.setAttribute(SessionConst.SESSION_USER,tUser);
            session.setAttribute(SessionConst.SESSION_USERNAME,tUser.getUserName());
            session.setAttribute(SessionConst.SESSION_MENULIST, this.listMenu());
            session.setAttribute(SessionConst.SESSION_PERSONAL_MENU, this.getPersonalMenu());
            return "dashboard";
        }else {
            token.clear();
            return "redirect:login";
        }
    }
    
    /**
     * 
     * @methodName getPersonalMenu
     * @description 得到个人中心菜单
     * @author 石洪刚
     * @time 2017年10月2日 下午7:24:06
     * @return
     */
    private SysMenu getPersonalMenu() {
		
		return loginService.getPersonalMenu();
	}
    
    /**
     * 
     * @methodName listMenu
     * @description 根据session中的用户信息列出菜单
     * @author 石洪刚
     * @time 2017年10月2日 下午7:24:56
     * @return
     */
	public List listMenu(){
    	Session session = SecurityUtils.getSubject().getSession();
    	//读取session中的用户信息
		SysUser user = (SysUser)session.getAttribute(SessionConst.SESSION_USER);
		
		
		return loginService.listMenuByUserId(user.getUserId());
    }
    
    /**
     * 
     * @methodName logout
     * @description 用户退出登录的方法
     * @author 石洪刚
     * @time 2017年10月2日 下午7:24:18
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="/logout")
    public String logout(RedirectAttributes redirectAttributes ){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute(Const.MESSAGE, "您已安全退出");
        return "redirect:/";
    }
}
