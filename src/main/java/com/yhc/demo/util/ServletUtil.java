package com.yhc.demo.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 实时获取请求对象
 * 
 * @author yhc
 * @date 2021-1-8
 */
public class ServletUtil {

  public static HttpServletRequest getRequest() {
	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	HttpServletRequest request = attributes.getRequest();
	return request;
  }
  
}
