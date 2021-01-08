package com.yhc.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yhc.demo.dao.RoleMapper;
import com.yhc.demo.dao.UserMapper;
import com.yhc.demo.dao.UserRoleRelationMapper;
import com.yhc.demo.entity.UserEntity;

/**
 * 实现springsecurity的默认用户校验接口
 * 
 * @author yhc
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	RoleMapper roleMapper;

	@Autowired
	UserRoleRelationMapper userRoleMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// 判断用户是否存在
		UserEntity user = userMapper.selectByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("user{" + username + "}does not exist");
		}

		// 查询用户权限
		String roleName = userRoleMapper.selectRoleNameByUserId(user.getId());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(roleName));
		return new User(user.getUserName(), user.getUserPwd(), authorities);
	}

}
