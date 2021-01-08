package com.yhc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringSecurityDemoApplication {

	public static ConfigurableApplicationContext ac;

	public static void main(String[] args) {
		ac = SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}

}
