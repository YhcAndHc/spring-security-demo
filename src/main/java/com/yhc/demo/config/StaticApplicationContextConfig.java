//package com.yhc.demo.config;
//
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Configuration;
//
///**
// * spring容器对象，便于动态获取bean对象
// * @param <T>
// * @author yhc
// * @date 2021-1-8
// */
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
