package com.yhc.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.yhc.demo.consts.ResponseEnum;
import com.yhc.demo.consts.SystemValue;
import com.yhc.demo.util.ResultUtil;

/**
 * 当未登录或者token失效访问接口时，自定义的返回结果
 */
@Component
public class RestAuthenticationFailureHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

	private static final Logger log = LoggerFactory.getLogger(RestAuthenticationFailureHandler.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {

		String token = request.getHeader(SystemValue.JWT_HEADER_KEY);
		String uri = request.getRequestURI();
		log.warn("# user auth, token[{}] is invalid, request uri:{}", token, uri);

		ResultUtil.failAsStream(ResponseEnum.EXCEPTION_NO_LOGIN, request, response);
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		String token = request.getHeader(SystemValue.JWT_HEADER_KEY);
		String uri = request.getRequestURI();
		log.warn("# user access, token[{}] does not have permission to visit the url[{}]", token, uri);

		ResultUtil.failAsStream(ResponseEnum.EXCEPTION_AUTH, request, response);
	}

}