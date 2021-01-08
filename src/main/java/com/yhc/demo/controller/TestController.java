package com.yhc.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	private final static Logger log = LoggerFactory.getLogger(TestController.class);
	
	@GetMapping("/test/user")
	public String userTest() {
		log.info("# welcome user");
		return "welcome user";
	}

	@GetMapping("/test/admin")
	public String adminTest() {
		log.info("# welcome admin");
		return "welcome admin";
	}

	@GetMapping("/test/root")
	public String rootTest() {
		log.info("# welcome root");
		return "welcome root";
	}

}
