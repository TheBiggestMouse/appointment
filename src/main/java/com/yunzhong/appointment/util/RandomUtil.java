package com.yunzhong.appointment.util;

import java.util.UUID;

/**
 * 
 * @className RandomUtil
 * @description 随机相关工具类
 * @author 石洪刚
 * @time 2017年10月12日 下午3:04:32
 */
public class RandomUtil {
	/**
	 * 
	 * @methodName getUUIDStr32
	 * @description 得到32位随机字符串
	 * @author 石洪刚
	 * @time 2017年10月12日 下午3:07:48
	 * @return
	 */
	public static String getUUIDStr32(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
