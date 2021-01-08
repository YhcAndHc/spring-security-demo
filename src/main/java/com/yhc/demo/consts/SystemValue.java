package com.yhc.demo.consts;

public interface SystemValue {

	String APPLICATION_NAME = "spring-security-demo";
	String APPLICATION_NAME_SHORTHAND = "SSD";

	// 登录态token的key
	// 请求携带的jwt的key
	String JWT_HEADER_KEY = "Authorization";
	// 请求携带的jwt的value的前缀
	String JWT_HEADER_VALUE_PREFIX = "Basic:tks-";
	// 本地缓存jwt的key的前缀
	String JWT_CACHE_KEY_PREFIX = "Basic:tks-";

	// 符号
	String SYMBOL_BAR = "-";
	String SYMBOL_COMMA = ",";

	String LOGIN_CAPTCHA_KEY = APPLICATION_NAME_SHORTHAND + "-captcha-";

}
