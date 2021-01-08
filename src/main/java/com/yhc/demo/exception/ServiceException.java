package com.yhc.demo.exception;

import com.yhc.demo.consts.ResponseEnum;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 8524001273041399186L;

	private String errorCode;
	private String errorMessage;

	public ServiceException(String errorCode, String errorMessage) {
		super(errorCode + "-" + errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public ServiceException(ResponseEnum response) {
		super(response.getCode() + "-" + response.getMessage());
		this.errorCode = response.getCode();
		this.errorMessage = response.getMessage();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
