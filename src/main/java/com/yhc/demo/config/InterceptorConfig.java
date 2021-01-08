package com.yhc.demo.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yhc.demo.interceptor.ValidateCaptchaInterceptor;

/**
 * springboot拦截器配置
 * 
 * @author yhc
 * @date 2021-1-8
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Resource
	ValidateCaptchaInterceptor validateCaptchaInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(validateCaptchaInterceptor);
	}

}
