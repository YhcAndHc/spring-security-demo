package com.yhc.demo.consts;

public enum ResponseEnum {

	// @formatter:off
	OK("000000", "请求成功"), 
	ERROR("999999", "系统异常"),
	
	EXCEPTION_CUSTOMIZE("400000", "自定义错误信息"),

	EXCEPTION_GET_CAPTCHA("400101", "获取验证码失败"),
	
	EXCEPTION_CAPTCHA_ERROR("400102", "验证码错误"),
	
	EXCEPTION_LOGIN_ERROR("400103", "账号或密码错误"),
	
	EXCEPTION_AUTH("400104", "权限不足"),
	
	EXCEPTION_NO_LOGIN("400105", "用户未登录，请先登录"),

	;
	// @formatter:on

	String code;
	String message;

	ResponseEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
