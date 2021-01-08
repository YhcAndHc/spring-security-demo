package com.yhc.demo.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServletUtil {

  public static HttpServletRequest getRequest() {
	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	HttpServletRequest request = attributes.getRequest();
	return request;
  }
  
}
