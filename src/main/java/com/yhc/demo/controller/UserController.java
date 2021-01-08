package com.yhc.demo.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhc.demo.consts.ResponseEnum;
import com.yhc.demo.consts.SystemValue;
import com.yhc.demo.dto.BaseResult;
import com.yhc.demo.dto.login.UserLoginResponse;
import com.yhc.demo.exception.ServiceException;
import com.yhc.demo.plugin.Captcha;
import com.yhc.demo.service.UserService;
import com.yhc.demo.util.CaptchaUtil;
import com.yhc.demo.util.ResultUtil;

/**
 * 用户相关服务
 * 
 * @author yhc
 * @date 2021-1-8
 */
@Controller
public class UserController {

	private final static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String login() {
		log.info("# login");
		return "login";
	}

//	@PostMapping(value = "/login")
//	@ResponseBody
	public BaseResult<UserLoginResponse> login(HttpServletRequest request,
			@RequestParam("userName") @NotBlank String userName, @RequestParam("userPwd") @NotBlank String userPwd,
			@RequestParam("captcha") @NotBlank String captcha) {

		String captchaCache = (String) request.getSession().getAttribute(SystemValue.LOGIN_CAPTCHA_KEY);
		if (!StringUtils.equals(captcha, captchaCache)) {
			log.warn("# session code:{}, input code:{}", captchaCache, captcha);
			throw new ServiceException(ResponseEnum.EXCEPTION_CAPTCHA_ERROR);
		}
		UserLoginResponse loginResp = userService.login(userName, userPwd);
		return ResultUtil.ok(loginResp);
	}

	@GetMapping("/captcha.jpg")
	@ResponseBody
	public void getCodeImage(HttpServletRequest request, HttpServletResponse response) {
		try {
			Captcha captcha = CaptchaUtil.createCaptchaImage(160, 30);
			BufferedImage bi = captcha.getBuffImg();
			ImageIO.write(bi, "JPEG", response.getOutputStream());

			request.getSession().setAttribute(SystemValue.LOGIN_CAPTCHA_KEY, captcha.getCode());
		} catch (Exception e) {
		}
	}

}
