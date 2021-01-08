package com.yhc.demo.plugin;

import java.time.LocalDateTime;

/**
 * redis的缓存数据结构
 */
public class Element<T> {

	private T value;

	private LocalDateTime expireDate;

	public Element(T value, LocalDateTime expireDate) {
		this.value = value;
		this.expireDate = expireDate;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public LocalDateTime getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDateTime expireDate) {
		this.expireDate = expireDate;
	}

}
