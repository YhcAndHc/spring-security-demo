package com.yhc.demo.service;

import com.yhc.demo.dto.login.UserLoginResponse;

public interface UserService {

	UserLoginResponse login(String userName, String userPwd);

}
