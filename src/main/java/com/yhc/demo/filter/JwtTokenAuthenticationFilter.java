package com.yhc.demo.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yhc.demo.consts.SystemValue;
import com.yhc.demo.plugin.JwtService;

/**
 * JWT认证授权过滤器
 * 
 * @author yhc
 *
 */
@Configuration
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
	private static final Logger log = LoggerFactory.getLogger(JwtTokenAuthenticationFilter.class);

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtService jwtTokenUtil;

//	private RequestHeaderRequestMatcher headerMatcher = new RequestHeaderRequestMatcher(SystemValue.JWT_HEADER_KEY);

	@Value("#{'${jwt.filter.uri.list}'.split(',')}")
	List<String> jwtFilterUriList;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		// 匿名访问，不校验jwt，后面权限校验也会拦截掉
//		if (headerMatcher.matches(request)) {
//			chain.doFilter(request, response);
//			return;
//		}

		// 实际场景应放置在header，此处偷懒直接放在url后面了
//		String authHeader = request.getHeader(tokenKey);
		// 登录和忘记密码不校验
		String authHeader = request.getParameter(SystemValue.JWT_HEADER_KEY);
		if (StringUtils.isNotBlank(authHeader) && authHeader.startsWith(SystemValue.JWT_HEADER_VALUE_PREFIX)) {
			String authToken = authHeader.substring(SystemValue.JWT_HEADER_VALUE_PREFIX.length());

			String username = jwtTokenUtil.getIssuer(authToken);
			log.info("# valid jwt by user:{}", username);

			if (StringUtils.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		chain.doFilter(request, response);
//		ResultUtil.failAsStream(ResponseEnum.EXCEPTION_NO_LOGIN, request, response);
//		throw new ServiceException(ResponseEnum.EXCEPTION_NO_LOGIN);

	}
}
