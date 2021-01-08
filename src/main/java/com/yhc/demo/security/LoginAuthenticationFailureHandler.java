package com.yhc.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.yhc.demo.consts.ResponseEnum;
import com.yhc.demo.util.ResultUtil;

/**
 * 登录认证失败处理
 * 
 * @author yhc
 * @date 2021-1-8
 */
@Configuration
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		ResultUtil.failAsStream(ResponseEnum.EXCEPTION_LOGIN_ERROR, request, response);
	}

}
