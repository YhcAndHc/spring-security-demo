package com.yhc.demo.util;

import com.yhc.demo.plugin.Captcha;

/**
 * 生成验证码的工具类
 */
public class CaptchaUtil {

	/**
	 * 使用默认规则创建图片验证码
	 * 
	 * @return
	 */
	public static Captcha createCaptchaImage() {
		return new Captcha();
	}

	/**
	 * 指定图片验证码的长款
	 * 
	 * @param width  验证码图片的长度
	 * @param height 验证码图片的宽度
	 * @return
	 */
	public static Captcha createCaptchaImage(int width, int height) {
		return new Captcha(width, height);
	}

	/**
	 * 按指定属性生成图片验证码
	 * 
	 * @param width     验证码图片的长度
	 * @param height    验证码图片的宽度
	 * @param codeCount 验证码个数
	 * @param lineCount 干扰线条数
	 * @return
	 */
	public static Captcha createCaptchaImage(int width, int height, int codeCount, int lineCount) {
		return new Captcha(width, height, codeCount, lineCount);
	}

}
