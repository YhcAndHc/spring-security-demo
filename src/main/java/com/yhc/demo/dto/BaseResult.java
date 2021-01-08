package com.yhc.demo.dto;

public class BaseResult<T> {

	String code;
	String message;
	T data;
	
	public BaseResult(String code,String message,T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BaseResult [code=" + code + ", message=" + message + ", data=" + data + "]";
	}

}
