package com.yhc.demo.consts;

public enum RoleEnum {

	ROOT("ROLE_ROOT"), ADMIN("ROLE_ADMIN"), VIP("ROLE_VIP"), USER("ROLE_USER"), VISITOR("ROLE_VISITOR"),;

	String message;

	RoleEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
