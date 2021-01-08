package com.yhc.demo.dto.login;

public class UserLoginResponse {

	// 用户名
	String userName;
	// 角色id(前端根据角色显示页面)
	String roleName;
	// 用户登录态token
	String tks;

	public UserLoginResponse() {
	}

	public UserLoginResponse(String userName, String roleName, String tks) {
		this.userName = userName;
		this.roleName = roleName;
		this.tks = tks;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getTks() {
		return tks;
	}

	public void setTks(String tks) {
		this.tks = tks;
	}

}
