package com.jingle.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成流水号的工具类
 * 
 * @author mowen
 *
 */
public class CodeUtil {
	private static SimpleDateFormat year2 = new SimpleDateFormat("yy");
	private static SimpleDateFormat year4 = new SimpleDateFormat("yyyy");

	/**
	 * 	生成流水号
 	 */
	public static String getDeviceCode(Long code) {
		StringBuilder codeStr = new StringBuilder("S");
		//添加年份的两位
		codeStr.append(year2.format(new Date()));
		//添加流水code
		codeStr.append(CommonUtil.evalr(code, 4));
		return codeStr.toString();
	}

	/**
	 * 	生成单据号
	 */
	public static String getDevicereceiveCode(Long code) {
		StringBuilder codeStr = new StringBuilder("DJS");
		//添加年份的两位
		codeStr.append(year2.format(new Date()));
		//添加流水code
		codeStr.append(CommonUtil.evalr(code, 4));
		return codeStr.toString();
	}
}
