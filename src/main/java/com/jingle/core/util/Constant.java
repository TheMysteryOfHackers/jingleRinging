package com.jingle.core.util;

import org.springframework.stereotype.Component;

/**
 * 常量类
 * 
 * @Author mowen
 * @Version 1.0, 2018年9月23日
 * @See
 * @Since com.jkoss.common.util
 * @Description: TODO
 */
@Component
public class Constant {
	/**
	 * 设备流水值
	 */
	public static final String DEVICE_CODE_REDIS = "devicecode";

	/**
	 * 设备单据值
	 */
	public static final String DEVICERECEIVE_CODE_REDIS = "devicereceivecode";

	/**
	 * 系统属性key
	 */
	public static final String APPLICATION_ATTRIBUTE_KEY = "attribute";
	/**
	 * 默认密码
	 */
	public static final String SYSTEM_DEFAUL_PWD = "OSSJK2017";

	/**
	 * 请求header token key
	 */
	public static final String REQUEST_TOKEN_HEADER = "jwttoken";

	/**
	 * userid
	 */
	public static final String REQUEST_USERID_KEY = "userid";
	/**
	 * token
	 */
	public static final String REQUEST_TOKEN_KEY = "token";
	/**
	 * 成功返回码
	 */
	public static final int RESPONSE_CODE_SUCCESS = 1000;
	/**
	 * 请求失败返回码
	 */
	public static final int RESPONSE_CODE_FAIL = 1001;
	/**
	 * 请求抛出异常返回码
	 */
	public static final int RESPONSE_CODE_EXCEPTION = 1002;
	/**
	 * 未登陆状态返回码
	 */
	public static final int RESPONSE_CODE_NOLOGIN = 1003;
	/**
	 * 无操作权限返回码
	 */
	public static final int RESPONSE_CODE_NOAUTH = 1004;
	/**
	 * userid可能在别的地方登录
	 */
	public static final int RESPONSE_CODE_BADTOKEN = 1005;
	/**
	 * 刷新Token
	 */
	public static final int RESPONSE_CODE_REFRESH = 1006;
	/**
	 * 成功返回信息
	 */
	public static final String RESPONSE_MSG_SUCCESS = "操作成功";
	/**
	 * 成功返回信息
	 */
	public static final String RESPONSE_MSG_FAIL = "操作失败";
	/**
	 * 请求抛出异常返回信息
	 */
	public static final String RESPONSE_MSG_EXCEPTION = "后台异常，请联系管理员";
	/**
	 * 未登陆状态返回信息
	 */
	public static final String RESPONSE_MSG_NOLOGIN = "no login!";
	/**
	 * 无操作权限返回信息
	 */
	public static final String RESPONSE_MSG_NOAUTH = "no auth!";
	/**
	 * 刷新Token返回信息
	 */
	public static final String RESPONSE_MSG_REFRESH = "refresh token!";

}
