package com.yhc.demo.aop;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yhc.demo.exception.ServiceException;
import com.yhc.demo.util.ServletUtil;

/**
 * 独立的日志打印切面
 * 
 * @author yhc
 * @date 2021-1-8
 */
@Configuration
@Aspect
public class WebLogAop {

	private static final Logger log = LoggerFactory.getLogger(WebLogAop.class);

	private static ObjectMapper logMapper = new ObjectMapper();

	/** 以 controller 包下定义的所有请求为切入点 */
	@Pointcut("execution(public * com.yhc.demo.controller.*.*(..))")
	public void webLog() {
	}

	/**
	 * 在切点之前织入
	 * 
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		// 开始打印请求日志
		HttpServletRequest request = ServletUtil.getRequest();

		// 打印请求相关参数
		log.info("### <<<------------------------- Log Start ------------------------->>>");
		// 打印请求 url
		log.info("### URL            : {}", request.getRequestURL().toString());
		// 打印 Http method
		log.info("### HTTP Method    : {}", request.getMethod());
		// 打印调用 controller 的全路径以及执行方法
		log.info("### Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName());
		// 打印请求的 IP
		log.info("### IP             : {}", request.getRemoteAddr());
		// 打印请求入参
//		log.info("### Request Args   : {}", JacksonUtil.marshallToString(joinPoint.getArgs()));
		log.info("### Request Args   : ");
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			log.info("###    name:{}---value:{}", name, request.getParameter(name));
		}
	}

	/**
	 * 在切点之后织入
	 * 
	 * @throws Throwable
	 */
	@After("webLog()")
	public void doAfter() {
		log.info("### <<<------------------------- Log End ------------------------->>>");
	}

	/**
	 * 环绕
	 * 
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("webLog()")
	public Object doAround(ProceedingJoinPoint proceedingJoinPoint)
			throws MethodArgumentNotValidException, ServiceException, Throwable {
		Object result = proceedingJoinPoint.proceed();

		// 勿修改result对象，可能会导致请求重定向
		String resultStr;
		try {
			resultStr = logMapper.writeValueAsString(result);
		} catch (Exception e) {
			// 兼容返回为URL字符串
			resultStr = result.toString();
		}
		// 打印出参
		log.info("### Response Args  : {}", resultStr);
		return result;
	}
}
