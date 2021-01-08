package com.yhc.demo.util;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.util.Assert;

/**
 * 
 * @Des 时间格式化工具类
 *
 */
public class DateTimeUtil {

	private final static String DATETIME_FOAMAT_NORMAT = "yyyy-MM-dd HH:mm:ss";
	private final static String DATE_FOAMAT_NORMAT = "yyyy-MM-dd";

	/** 传入long时间戳 ，转换成格式化的String类型时间(yyyy-MM-dd HH:mm:ss) */
	public String TimeOfLongToStr(long time) {
		Assert.notNull(time, "time is null");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATETIME_FOAMAT_NORMAT);
		return dtf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));

	}

	/** 传入String类型格式化时间(yyyy-MM-dd HH:mm:ss)，转换成Long类型的时间戳 */
	public long TimeOfStrToLong(String strTime) {
		Assert.notNull(strTime, "time is null");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATETIME_FOAMAT_NORMAT);
		LocalDateTime parse = LocalDateTime.parse(strTime, dtf);
		return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

	}

	/** 通过LocalDateTime获取当前格式化时间，格式：yyyy-MM-dd HH:mm:ss */
	public static String getDateTimeStrNow() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATETIME_FOAMAT_NORMAT));
	}

	/** 通过LocalDateTime获取当前格式化时间，格式：yyyy-MM-dd */
	public static String getDateStrNow() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FOAMAT_NORMAT));
	}

	/** 获取当前时间N秒后的时间戳，格式：yyyy-MM-dd HH:mm:ss */
	public static LocalDateTime afterDateTime(long seconds) {
		return LocalDateTime.now().plusSeconds(seconds);
	}

	/** 计算两个时间相差秒数(ldt2-ldt1) */
	public static long diff(LocalDateTime ldt1, LocalDateTime ldt2) {
		Duration duration = Duration.between(ldt1, ldt2);
		long diffSeconds = duration.toMillis() / 1000;
		return diffSeconds;
	}

}
