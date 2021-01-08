package com.yhc.demo.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

	@Value("${spring.application.name}")
	String applicationName;

	@RequestMapping("/health")
	public Map<String, Object> health() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("time", LocalDateTime.now());
		result.put("appName", applicationName);
		result.put("status", 200);
		return result;
	}
	

}
