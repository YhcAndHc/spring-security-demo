//package com.yhc.demo.config;
//
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class StaticApplicationContextConfig<T> implements ApplicationContextAware {
//
//	private static ApplicationContext applicationContext;
//
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		StaticApplicationContextConfig.applicationContext = applicationContext;
//	}
//
//	public static <T> T getBean(Class<T> clazz) {
//		return applicationContext != null ? applicationContext.getBean(clazz) : null;
//	}
//}
