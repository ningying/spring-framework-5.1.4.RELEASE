package com.haowu.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Copyright (C), 2015-2020, 上海好屋网信息技术有限公司
 * FileName: AppConfig
 * Author:   ningying
 * Date:     2019/6/13
 * Description: TODO
 * Version 1.0
 */
@Configuration
public class AppConfig {
//	@Value("#{jdbcProperties.url}")
//	private String jdbcUrl;
//	@Value("#{jdbcProperties.username}")
//	private String username;
//	@Value("#{jdbcProperties.password}")
//	private String password;
//
//	@Bean
//	public FooService fooService(){
//		return new FoolServiceImpl(foolRepository());
//	}
//
//	@Bean
//	public FooRepository fooRepository(){
//		return new HibernateFooRepository(sessionFactory());
//	}
//
//	@Bean
//	public SessionFactory sessionFactory(){
//		//wire up a session factory
//		AnnotationSessionFactoryBean asFactoryBean = new AnnotationSessionFactoryBean();
//		as.setDataSource(dataSource);
//		//additional config
//		return asFactoryBean.getObject();
//	}
//
//	@Bean
//	public DataSource dataSource(){
//		return new DriverManagerDataSource(jdbcUrl, username, password);
//	}
	public static void main(String[] args){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.refresh();


	}
}
