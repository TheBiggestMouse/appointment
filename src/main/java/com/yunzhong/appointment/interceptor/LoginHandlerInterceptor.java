package com.yunzhong.appointment.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @className LoginHandlerInterceptor
 * @description 权限验证拦截器
 * @author 石洪刚
 * @time 2017年10月2日 下午7:22:57
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//若果得到的请求路径为/error，代表shiro验证没通过，那么交给shiro处理，springMVC拦截器不处理
		String path = request.getServletPath();
		if(!"/error".equals(path)){
			try {
				//SecurityUtils.getSubject().checkPermission(path);
			} catch (Exception e) {
				// TODO: handle exception
				response.sendRedirect(request.getContextPath()+"/login");
			}
		}
		return true;
	}
	
}
