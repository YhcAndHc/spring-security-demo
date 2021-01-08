package com.yhc.demo.util;

import cn.hutool.core.lang.UUID;

public class UuidUtil {

	/**
	 * 获取不带"-"的uuid
	 * 
	 * @return
	 */
	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
