package com.yhc.demo.security;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.yhc.demo.consts.RoleEnum;

@Configuration
public class MyAccessDecisionManager implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		// 此处无须判空，因在自定义metadatasource里，默认匿名用户为ROOT_VISITOR
//		if (authentication == null) {
//			throw new AccessDeniedException("当前访问没有权限");
//		}

		for (ConfigAttribute configAttribute : configAttributes) {

			String needRole = configAttribute.getAttribute();
			if (StringUtils.equalsIgnoreCase(RoleEnum.VISITOR.getMessage(), needRole)) {
				return;
//				if (authentication instanceof AnonymousAuthenticationToken) {
//					throw new BadCredentialsException("用户未登录");
//				} else {
//					return;
//				}
			}

			// 当前用户所具有的权限
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority authority : authorities) {
				if (StringUtils.equalsIgnoreCase(authority.getAuthority(), needRole)) {
					return;
				}
			}
		}
		throw new AccessDeniedException("无访问权限");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
