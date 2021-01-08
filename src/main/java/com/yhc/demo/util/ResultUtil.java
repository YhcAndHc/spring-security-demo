package com.yhc.demo.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yhc.demo.consts.ResponseEnum;
import com.yhc.demo.consts.SystemValue;
import com.yhc.demo.dto.BaseResult;
import com.yhc.demo.exception.ServiceException;

public class ResultUtil {

	private static final String FAIL_CODE_PREFIX = SystemValue.APPLICATION_NAME + SystemValue.SYMBOL_BAR;

	public static <T> BaseResult<?> ok() {
		return new BaseResult<>(ResponseEnum.OK.getCode(), ResponseEnum.OK.getMessage(), null);
	}

	public static <T> BaseResult<T> ok(T data) {
		return new BaseResult<>(ResponseEnum.OK.getCode(), ResponseEnum.OK.getMessage(), data);
	}

	public static <T> BaseResult<?> fail(String errorMessage) {
		return new BaseResult<>(FAIL_CODE_PREFIX + ResponseEnum.EXCEPTION_CUSTOMIZE.getCode(), errorMessage, null);
	}

	public static <T> BaseResult<?> fail(ResponseEnum resp) {
		return new BaseResult<>(FAIL_CODE_PREFIX + resp.getCode(), resp.getMessage(), null);
	}

	public static <T> BaseResult<?> fail(ServiceException se) {
		return new BaseResult<>(FAIL_CODE_PREFIX + se.getErrorCode(), se.getErrorMessage(), null);
	}

	public static <T> BaseResult<?> fail(ResponseEnum resp, T data) {
		return new BaseResult<>(FAIL_CODE_PREFIX + resp.getCode(), resp.getMessage(), data);
	}

	public static <T> BaseResult<?> error() {
		return new BaseResult<>(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getMessage(), null);
	}

	public static <T> void okAsStream(T data, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("application/json;charset=UTF-8;");
		PrintWriter out = response.getWriter();
		out.println(JacksonUtil.marshallToString(ok(data)));
		out.flush();
		out.close();
	}

	public static void failAsStream(ResponseEnum resp, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("application/json;charset=UTF-8;");
		PrintWriter out = response.getWriter();
		out.println(JacksonUtil.marshallToString(fail(resp)));
		out.flush();
		out.close();
	}

}
