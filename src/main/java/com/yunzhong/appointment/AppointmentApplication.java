package com.yunzhong.appointment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * 
 * @className AppointmentApplication
 * @description 项目启动类
 * @author 石洪刚
 * @time 2017年10月1日 上午9:14:03
 */
@SpringBootApplication
@MapperScan("com.yunzhong.appointment.mapper")   //扫描Mapper接口
@EnableTransactionManagement	//启用事务注解
@EnableScheduling  //启用定时器注解
public class AppointmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppointmentApplication.class, args);
	}
}
