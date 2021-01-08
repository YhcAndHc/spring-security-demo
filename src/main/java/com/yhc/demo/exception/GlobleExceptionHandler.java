package com.yhc.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.yhc.demo.dto.BaseResult;
import com.yhc.demo.util.ResultUtil;

@ControllerAdvice(basePackages="com.yhc.demo")
public class GlobleExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobleExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public BaseResult<?> exceptionHandle(Exception e) {
		log.error("# {}", e.getMessage(), e);
		return ResultUtil.error();
	}

	@ExceptionHandler(ServiceException.class)
	public BaseResult<?> exceptionHandle(ServiceException se) {
		log.warn("# {}", se.getMessage(), se);
		return ResultUtil.fail(se);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public BaseResult<?> exceptionHandle(MethodArgumentNotValidException me) {
		log.warn("# {}", me.getMessage());
		return ResultUtil.fail(me.getMessage());
	}

}
