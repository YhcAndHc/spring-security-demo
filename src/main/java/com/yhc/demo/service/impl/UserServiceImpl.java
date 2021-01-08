package com.yhc.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yhc.demo.consts.ResponseEnum;
import com.yhc.demo.consts.SystemValue;
import com.yhc.demo.dao.UserMapper;
import com.yhc.demo.dao.UserRoleRelationMapper;
import com.yhc.demo.dto.login.UserLoginResponse;
import com.yhc.demo.entity.UserEntity;
import com.yhc.demo.exception.ServiceException;
import com.yhc.demo.plugin.JwtService;
import com.yhc.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserRoleRelationMapper userRoleMapper;

	@Autowired
	JwtService jwtService;
	
	@Override
	public UserLoginResponse login(String userName, String userPwd) {

		// 判断账密是否正确
		UserEntity user = userMapper.selectByName(userName);
		PasswordEncoder pe = new BCryptPasswordEncoder();
		if (user == null || !pe.matches(userPwd, user.getUserPwd())) {
			throw new ServiceException(ResponseEnum.EXCEPTION_LOGIN_ERROR);
		}

		// 查询用户的角色
		String roleName = userRoleMapper.selectRoleNameByUserId(user.getId());

//		String tksKey = SystemValue.JWT_CACHE_KEY_PREFIX + req.getUserName();
		String tks = SystemValue.JWT_HEADER_VALUE_PREFIX + jwtService.generateToken(userName);
//		LocalRedis.set(tksKey, tksValue, expireTime); // jwt是无状态的，所以此处无须保存
		UserLoginResponse resp = new UserLoginResponse(user.getUserName(), roleName, tks);

		return resp;
	}

}
