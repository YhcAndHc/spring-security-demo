package com.yhc.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {

	private static final Logger log = LoggerFactory.getLogger(JacksonUtil.class);

	public static ObjectMapper mapper = new ObjectMapper();

	public static <T> String marshallToString(T _T) {
		try {
			return mapper.writeValueAsString(_T);
		} catch (Exception e) {
			log.error("# MarshallToString error", e);
		}
		return null;
	}

	public static <T> T jsonToObject(String json, Class<T> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (Exception e) {
			log.error("# JsonToObject error", e);
		}
		return null;
	}

	public static <T> T jsonToObject(String json, TypeReference<T> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (Exception e) {
			log.error("# JsonToObject error", e);
		}
		return null;
	}

}
