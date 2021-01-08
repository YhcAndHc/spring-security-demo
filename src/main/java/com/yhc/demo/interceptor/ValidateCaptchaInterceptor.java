package com.yhc.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yhc.demo.consts.ResponseEnum;
import com.yhc.demo.consts.SystemValue;
import com.yhc.demo.util.ResultUtil;

/**
 * 登录验证码校验器
 * 
 */
@Configuration
public class ValidateCaptchaInterceptor extends HandlerInterceptorAdapter {

	private static final Logger log = LoggerFactory.getLogger(ValidateCaptchaInterceptor.class);

	@Value("${login.uri}")
	String loginUri;

	// 此处可以重写UsernamePasswordAuthenticationFilter（这个过滤器默认只校验账号和密码），这里拦截器实现起来更简单和直观
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 判断是否为登录接口
		if (StringUtils.equalsIgnoreCase(request.getMethod(), "POST") && StringUtils.equals(request.getServletPath(), loginUri)) {
			String captchaRequest = request.getParameter("captcha");
			String captchaCache = (String) request.getSession().getAttribute(SystemValue.LOGIN_CAPTCHA_KEY);
			if (!StringUtils.equals(captchaRequest, captchaCache)) {
				log.warn("# session code:{}, input code:{}", captchaCache, captchaRequest);
//				throw new ServiceException(ResponseEnum.EXCEPTION_CAPTCHA_ERROR);
				ResultUtil.failAsStream(ResponseEnum.EXCEPTION_CAPTCHA_ERROR, request, response);
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}

}
