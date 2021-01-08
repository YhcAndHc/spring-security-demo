package com.yhc.demo.plugin;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.yhc.demo.util.DateTimeUtil;

/**
 * 仅供参考，该demo暂未使用，因jwt是无状态的，尽量不适用redis等缓存
 * 
 * @Des 简易缓存，生产环境请使用Redis
 *
 */
public class LocalRedis {

	private static Map<String, Element<?>> map = new ConcurrentHashMap<>();

	/**
	 * 
	 * @param key   缓存key
	 * @param value 缓存value
	 */
	public static <T> void set(String key, T value) {
		map.put(key, new Element<>(value, null));
	}

	/**
	 * 
	 * @param key        缓存key
	 * @param value      缓存value
	 * @param expireTime 缓存过期时间（秒）
	 */
	public static <T> void set(String key, T value, int expireTime) {
		LocalDateTime expireDate = DateTimeUtil.afterDateTime(expireTime);
		map.put(key, new Element<>(value, expireDate));
	}

	/**
	 * 
	 * @param key 缓存key
	 * @return value 缓存value
	 */
	public static String getString(String key) {
		if (map.containsKey(key)) {
			LocalDateTime expireDate = map.get(key).getExpireDate();
			if (expireDate == null || expireDate.isAfter(LocalDateTime.now())) {
				String value = map.get(key).getValue().toString();
				return value;
			} else {
				map.remove(key);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param key   缓存key
	 * @param clazz 缓存value类型
	 * @return value 缓存value
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(String key, Class<T> clazz) {
		if (map.containsKey(key)) {
			LocalDateTime expireDate = map.get(key).getExpireDate();
			if (expireDate == null || expireDate.isAfter(LocalDateTime.now())) {
				try {
					return (T) map.get(key).getValue();
				} catch (Exception e) {
					return null;
				}
			} else {
				map.remove(key);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param key
	 */
	public static void remove(String key) {
		if (map.containsKey(key)) {
			map.remove(key);
		}
	}

	// for test
	public static void main(String[] args) throws InterruptedException {
		LocalRedis.set("a", "a");
		System.out.println(LocalRedis.getString("a"));
		LocalRedis.set("a", "b");
		System.out.println(LocalRedis.getString("a"));

		LocalRedis.set("a", "c", 3);
//		Thread.sleep(5000);
		System.out.println(LocalRedis.getString("a"));
	}

}
