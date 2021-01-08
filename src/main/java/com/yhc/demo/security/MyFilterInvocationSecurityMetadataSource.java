package com.yhc.demo.security;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import com.yhc.demo.consts.RoleEnum;
import com.yhc.demo.dao.ResourceRoleRelationMapper;
import com.yhc.demo.dao.RoleMapper;
import com.yhc.demo.entity.vo.UrlRolenameVo;

@Configuration
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	private static final Logger log = LoggerFactory.getLogger(MyFilterInvocationSecurityMetadataSource.class);

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Autowired
	ResourceRoleRelationMapper resourceMapper;

	@Autowired
	RoleMapper roleMapper;

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequest().getServletPath();
//		log.info("# [path access auth] , url[{}]", requestUrl);

		// 此处建议做全局缓存，项目启动时就加载，这样每次请求时无须重新查询数据库,然后配置一个刷新接口或定时刷新
		List<UrlRolenameVo> urlList = resourceMapper.selectAll();
		String[] roles = urlList.stream().filter(entity -> antPathMatcher.match(entity.getUrl(), requestUrl))
				.map(entity -> entity.getRoleName()).toArray(String[]::new);
		if (roles != null && roles.length > 0) {
			return SecurityConfig.createList(roles);
		}

		// 未配置的路径，均视为可以无须登录访问
		log.warn("# [path access auth] , url[{}] has not been configured", requestUrl);
		return SecurityConfig.createList(RoleEnum.VISITOR.getMessage());
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
