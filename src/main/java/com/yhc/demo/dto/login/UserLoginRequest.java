package com.yhc.demo.dto.login;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class UserLoginRequest implements Serializable {

	private static final long serialVersionUID = -5183740203720829513L;

	@NotBlank
	String userName;

	@NotBlank // 此处密码建议非对称加密传输
	String userPwd;

	@NotBlank
	String captcha;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}
