package com.yhc.demo.service;

import com.yhc.demo.dto.login.UserLoginResponse;

public interface UserService {

	/**
	 * 自定义登录接口处理，该demo未使用
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	UserLoginResponse login(String userName, String userPwd);

}
