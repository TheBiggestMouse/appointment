package com.yunzhong.appointment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yunzhong.appointment.interceptor.LoginHandlerInterceptor;

/**
 * 
 * @className MyWebAppConfigurer
 * @description web配置类，配置springMVC自定义拦截器
 * @author 石洪刚
 * @time 2017年10月2日 下午7:21:22
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter{

	/**
     * 
     * @methodName addInterceptors
     * @description 配置拦截器过滤规则
     * @author 石洪刚
     * @time 2017年10月2日 下午7:20:56
     * @param factoryBean
     */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//添加自定义拦截器
		LoginHandlerInterceptor li = new LoginHandlerInterceptor();
//		registry.addInterceptor(li)
//			.addPathPatterns("/**")
//			.excludePathPatterns("/login")
//			.excludePathPatterns("/logout")
//			.excludePathPatterns("/success")
//			.excludePathPatterns("/")
//			.excludePathPatterns("/appointment/dept")
//			.excludePathPatterns("/appointment/illness")
//			.excludePathPatterns("/pubinfo/newinfo")
//			.excludePathPatterns("/menu/showMenu");
		super.addInterceptors(registry);
	}

}
