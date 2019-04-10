package com.yunzhong.appointment.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className DruidConfiguration
 * @description 阿里巴巴连接池的配置信息
 * @author 石洪刚
 * @time 2017年10月2日 下午7:16:28
 */
@Configuration
public class DruidConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);
	
	/**
	 * 
	 * @methodName statViewServlet
	 * @description 注册一个StatViewServlet
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:16:42
	 * @return
	 */
	@Bean
	public ServletRegistrationBean statViewServlet() {
		logger.info("init Druid Servlet Configuration ");
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		// IP白名单
		//servletRegistrationBean.addInitParameter("allow","127.0.0.1");
		// IP黑名单(共同存在时，deny优先于allow)如果满足deny的话提示:Sorry, you are not permitted to view this page.
		//servletRegistrationBean.addInitParameter("deny", "192.168.2.100");
		// 控制台管理用户
		servletRegistrationBean.addInitParameter("loginUsername", "yunzhongadmin");
		servletRegistrationBean.addInitParameter("loginPassword", "yunzhongadmin");
		// 是否能够重置数据 禁用HTML页面上的“Reset All”功能
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}
	/**
	 * 
	 * @methodName statFilter
	 * @description 注册一个：filterRegistrationBean
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:16:54
	 * @return
	 */
	@Bean
    public FilterRegistrationBean statFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
	
	/**
	 * 
	 * @className DataSourceProperties
	 * @description 数据源配置信息
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:17:08
	 */
    @ConfigurationProperties(prefix = Const.DB_PREFIX)
	class DataSourceProperties{
    	private String url;
        private String username;
        private String password;
        private String driverClassName;
        private int initialSize;
        private int minIdle;
        private int maxActive;
        private int maxWait;
        private int timeBetweenEvictionRunsMillis;
        private int minEvictableIdleTimeMillis;
        private String validationQuery;
        private boolean testWhileIdle;
        private boolean testOnBorrow;
        private boolean testOnReturn;
        private boolean poolPreparedStatements;
        private int maxPoolPreparedStatementPerConnectionSize;
        private String filters;
        private String connectionProperties;
        
        @Bean     //声明其为Bean实例
        @Primary  //在同样的DataSource中，首先使用被标注的DataSource
        public DataSource dataSource() {
        	DruidDataSource datasource = new DruidDataSource();
            datasource.setUrl(url);
            datasource.setUsername(username);
            datasource.setPassword(password);
            datasource.setDriverClassName(driverClassName);

            //configuration
            datasource.setInitialSize(initialSize);
            datasource.setMinIdle(minIdle);
            datasource.setMaxActive(maxActive);
            datasource.setMaxWait(maxWait);
            datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            datasource.setValidationQuery(validationQuery);
            datasource.setTestWhileIdle(testWhileIdle);
            datasource.setTestOnBorrow(testOnBorrow);
            datasource.setTestOnReturn(testOnReturn);
            datasource.setPoolPreparedStatements(poolPreparedStatements);
            datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
            try {
                datasource.setFilters(filters);
            } catch (SQLException e) {
                System.err.println("druid configuration initialization filter: " + e);
            }
            datasource.setConnectionProperties(connectionProperties);
            return datasource;
        }

      	@Bean
      	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
      	    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
      	    sqlSessionFactoryBean.setDataSource(dataSource());
      	    //分页插件
      	    PageHelper pageHelper = new PageHelper();
      	    Properties props = new Properties();
              props.setProperty("reasonable", "true");
              props.setProperty("supportMethodsArguments", "true");
              props.setProperty("returnPageInfo", "check");
              props.setProperty("params", "count=countSql");
              pageHelper.setProperties(props);
              //增加分页插件
      	    sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper}); 
      	    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
      	    //加载映射文件
      	    sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
      	    return sqlSessionFactoryBean.getObject();
      	}
    	

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getDriverClassName() {
			return driverClassName;
		}

		public void setDriverClassName(String driverClassName) {
			this.driverClassName = driverClassName;
		}

		public int getInitialSize() {
			return initialSize;
		}

		public void setInitialSize(int initialSize) {
			this.initialSize = initialSize;
		}

		public int getMinIdle() {
			return minIdle;
		}

		public void setMinIdle(int minIdle) {
			this.minIdle = minIdle;
		}

		public int getMaxActive() {
			return maxActive;
		}

		public void setMaxActive(int maxActive) {
			this.maxActive = maxActive;
		}

		public int getMaxWait() {
			return maxWait;
		}

		public void setMaxWait(int maxWait) {
			this.maxWait = maxWait;
		}

		public int getTimeBetweenEvictionRunsMillis() {
			return timeBetweenEvictionRunsMillis;
		}

		public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
			this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
		}

		public int getMinEvictableIdleTimeMillis() {
			return minEvictableIdleTimeMillis;
		}

		public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
			this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
		}

		public String getValidationQuery() {
			return validationQuery;
		}

		public void setValidationQuery(String validationQuery) {
			this.validationQuery = validationQuery;
		}

		public boolean isTestWhileIdle() {
			return testWhileIdle;
		}

		public void setTestWhileIdle(boolean testWhileIdle) {
			this.testWhileIdle = testWhileIdle;
		}

		public boolean isTestOnBorrow() {
			return testOnBorrow;
		}

		public void setTestOnBorrow(boolean testOnBorrow) {
			this.testOnBorrow = testOnBorrow;
		}

		public boolean isTestOnReturn() {
			return testOnReturn;
		}

		public void setTestOnReturn(boolean testOnReturn) {
			this.testOnReturn = testOnReturn;
		}

		public boolean isPoolPreparedStatements() {
			return poolPreparedStatements;
		}

		public void setPoolPreparedStatements(boolean poolPreparedStatements) {
			this.poolPreparedStatements = poolPreparedStatements;
		}

		public int getMaxPoolPreparedStatementPerConnectionSize() {
			return maxPoolPreparedStatementPerConnectionSize;
		}

		public void setMaxPoolPreparedStatementPerConnectionSize(
				int maxPoolPreparedStatementPerConnectionSize) {
			this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
		}

		public String getFilters() {
			return filters;
		}

		public void setFilters(String filters) {
			this.filters = filters;
		}

		public String getConnectionProperties() {
			return connectionProperties;
		}

		public void setConnectionProperties(String connectionProperties) {
			this.connectionProperties = connectionProperties;
		}
	}
    
 
}
