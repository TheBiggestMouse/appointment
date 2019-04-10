package com.yunzhong.appointment.config;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;


import java.util.*;

/**
 * 
 * @className MyShiroConfigurer
 * @description shiro配置类
 * @author 石洪刚
 * @time 2017年10月2日 下午7:20:33
 */
@Configuration
public class MyShiroConfigurer {
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个 ShiroFilterFactoryBean 配置是或报错的，因为在
     * 初始化 ShiroFilterFactoryBean 的时候需要注入：SecurityManager
     *
     Filter Chain 定义说明
     1、一个 URL 可以配置多个 Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如 perms，roles
     *
     */
    @Bean
    public EhCacheManager getEhCacheManager(){
    	EhCacheManager ehcacheManager = new EhCacheManager();
    	ehcacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
    	return ehcacheManager;
    }
    /**
     * realm
     * */
    @Bean(name = "myShiroRealm")
    public MyRealm myShiroRealm(EhCacheManager ehCacheManager){
        MyRealm realm = new MyRealm();
        realm.setCacheManager(ehCacheManager);
        return realm;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(MyRealm realm){
    	DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置 realm
        securityManager.setRealm(realm);
        securityManager.setCacheManager(getEhCacheManager());
        return securityManager;
    }

    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }
    
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找 Web 工程根目录下的 "/login.jsp" 页面
        factoryBean.setLoginUrl("/success");
        // 登录成功后要跳转的连接
        //factoryBean.setSuccessUrl("/");
        factoryBean.setUnauthorizedUrl("redirect:/");
        loadShiroFilterChain(factoryBean);
        return factoryBean;
    }
    

    /**
     * 
     * @methodName loadShiroFilterChain
     * @description 加载 ShiroFilter 权限控制规则
     * @author 石洪刚
     * @time 2017年10月2日 下午7:20:56
     * @param factoryBean
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean factoryBean) {
    	/** 下面这些规则配置最好配置到配置文件中 */
        Map<String, String> filterChainMap = new LinkedHashMap<String, String>();
        /** authc：该过滤器下的页面必须验证后才能访问，它是 Shiro 内置的一个拦截器
         * org.apache.shiro.web.filter.authc.FormAuthenticationFilter */
        // anon：它对应的过滤器里面是空的, 什么都没做, 可以理解为不拦截
        //authc: 所有 url 都必须认证通过才可以访问; anon: 所有 url 都都可以匿名访问
        filterChainMap.put("/error", "anon");
        filterChainMap.put("/success", "anon");
        filterChainMap.put("/login", "anon");
        filterChainMap.put("/register", "anon");
        filterChainMap.put("/registerUser", "anon");
        filterChainMap.put("/listCity", "anon");
        filterChainMap.put("/telVerify", "anon");
        filterChainMap.put("/patientUidVerify", "anon");
        filterChainMap.put("/listArea", "anon");
        filterChainMap.put("/", "anon");
        filterChainMap.put("/sys/showMenu", "anon");
        filterChainMap.put("/static/**", "anon");
        filterChainMap.put("/druid/**", "anon");
        filterChainMap.put("/base/**", "anon");
        filterChainMap.put("/**", "authc");

        factoryBean.setFilterChainDefinitionMap(filterChainMap);
    }

    /*1.LifecycleBeanPostProcessor，这是个 DestructionAwareBeanPostProcessor 的子类，负责 org.apache.shiro.util.Initializable 类型 bean 的生命周期的，初始化和销毁。主要是 AuthorizingRealm 类的子类，以及 EhCacheManager 类。
    2.HashedCredentialsMatcher，这个类是为了对密码进行编码的，防止密码在数据库里明码保存，当然在登陆认证的生活，这个类也负责对 form 里输入的密码进行编码。
    3.ShiroRealm，这是个自定义的认证类，继承自 AuthorizingRealm，负责用户的认证和权限的处理，可以参考 JdbcRealm 的实现。
    4.EhCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，然后每次用户请求时，放入用户的 session 中，如果不设置这个 bean，每个请求都会查询一次数据库。
    5.SecurityManager，权限管理，这个类组合了登陆，登出，权限，session 的处理，是个比较重要的类。
    6.ShiroFilterFactoryBean，是个 factorybean，为了生成 ShiroFilter。它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
    7.DefaultAdvisorAutoProxyCreator，Spring 的一个 bean，由 Advisor 决定对哪些类的方法进行 AOP 代理。
    8.AuthorizationAttributeSourceAdvisor，shiro 里实现的 Advisor 类，内部使用 AopAllianceAnnotationsAuthorizingMethodInterceptor 来拦截用以下注解的方法。*/
}
