package com.zc.common.core.utils;
/**
 * 开发调试日志输出工具
 * 
 * @author czb
 * @e-mail chengzhenbing@139.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2013-5-23 下午11:49:27
 * 
 */

public class DebugUtils {
	/**
	 * @description ：是否输出调试信息
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:53
	 * @version
	 * @param :
	 */
	public static Boolean DEBUGON = false;
	
	public static void println(String msg) {
		if (DEBUGON) {
			System.out.println(msg);
		}
	}

}
