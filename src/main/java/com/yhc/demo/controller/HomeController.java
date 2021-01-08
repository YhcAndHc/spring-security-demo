package com.yhc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页
 * 
 * @author yhc
 * @date 2021-1-8
 */
@Controller
public class HomeController {

	@GetMapping("/home")
	public String home() {
		return "home";
	}

}
