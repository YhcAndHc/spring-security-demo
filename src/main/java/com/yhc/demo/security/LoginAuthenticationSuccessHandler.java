package com.yhc.demo.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.yhc.demo.consts.SystemValue;
import com.yhc.demo.dto.login.UserLoginResponse;
import com.yhc.demo.plugin.JwtService;
import com.yhc.demo.util.ResultUtil;

@Configuration
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	JwtService jwtService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		User principal = (User) authentication.getPrincipal();
		String username = principal.getUsername();

		Collection<GrantedAuthority> authorities = principal.getAuthorities();

		// 因每个用户只对应一种角色，所以此处直接用role[]
		String[] roles = authorities.stream().map(auth -> auth.getAuthority()).toArray(String[]::new);

		String tks = SystemValue.JWT_HEADER_VALUE_PREFIX + jwtService.generateToken(username);
		UserLoginResponse resp = new UserLoginResponse(username, roles[0], tks);
		ResultUtil.okAsStream(resp, request, response);

	}

}
