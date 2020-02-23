package com.jingle.core.base.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.jingle.core.util.CommonUtil;
import com.jingle.core.util.Constant;
import com.jingle.core.vo.ResponseBean;

public class BaseController {

	public Logger log = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 得到request对象
	 */
	protected HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		return request;
	}

	/**
	 * 得到token
	 */
	protected String getToken() {
		HttpServletRequest request = getRequest();
		if (!CommonUtil.isBlank(request.getHeader("jwttoken"))) {
			return request.getHeader("jwttoken");
		} else {
			return request.getParameter("jwttoken");
		}

	}

	/**
	 * 全局异常捕捉
	 *
	 * @param e
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ExceptionHandler(Exception.class)
	public Object exception(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
		e.printStackTrace();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ResponseBean responseBean = new ResponseBean(Constant.RESPONSE_CODE_EXCEPTION, Constant.RESPONSE_MSG_EXCEPTION, null);
		out.write(JSON.toJSONString(responseBean));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 没有权限异常捕捉
	 *
	 * @param e
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public Object UnauthorizedException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
		e.printStackTrace();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ResponseBean responseBean = new ResponseBean(Constant.RESPONSE_CODE_NOAUTH, Constant.RESPONSE_MSG_NOAUTH, null);
		out.write(JSON.toJSONString(responseBean));
		out.flush();
		out.close();
		return null;
	}

	public static boolean isAjax(HttpServletRequest request) {
		if (request != null && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")))
			return true;
		return false;
	}

	/**
	 * 获取访问者IP
	 * <p>
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * <p>
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)， 如果还不存在则调用Request
	 * .getRemoteAddr()。
	 *
	 * @return
	 */
	protected String getAddr() throws Exception {
		HttpServletRequest request = getRequest();

		String ip = request.getHeader("X-Real-IP");
		if (!CommonUtil.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!CommonUtil.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

	/**
	 * 响应成功
	 *
	 * @return
	 */
	protected Object responseSuccess() {
		return new ResponseBean(Constant.RESPONSE_CODE_SUCCESS, Constant.RESPONSE_MSG_SUCCESS, null);
	}

	/**
	 * 响应成功
	 *
	 * @param msg
	 *            响应信息
	 * @return
	 */
	protected Object responseSuccess(String msg) {
		return new ResponseBean(Constant.RESPONSE_CODE_SUCCESS, msg, null);
	}

	/**
	 * 响应成功
	 *
	 * @param data
	 *            响应内容
	 * @return
	 */
	protected Object responseSuccess(Object data) {
		return new ResponseBean(Constant.RESPONSE_CODE_SUCCESS, Constant.RESPONSE_MSG_SUCCESS, data);
	}

	/**
	 * 响应成功
	 *
	 * @param msg
	 *            响应信息
	 * @param data
	 *            响应内容
	 * @return
	 */
	protected Object responseSuccess(String msg, Object data) {
		return new ResponseBean(Constant.RESPONSE_CODE_SUCCESS, msg, data);
	}

	/**
	 * 响应失败
	 *
	 * @return
	 */
	protected Object responseFail() {
		return new ResponseBean(Constant.RESPONSE_CODE_FAIL, Constant.RESPONSE_MSG_FAIL, null);
	}

	/**
	 * 响应失败
	 *
	 * @param msg
	 *            响应信息
	 * @return
	 */
	protected Object responseFail(String msg) {
		return new ResponseBean(Constant.RESPONSE_CODE_FAIL, msg, null);
	}

	/**
	 * 响应失败
	 *
	 * @param data
	 *            响应内容
	 * @return
	 */
	protected Object responseFail(Object data) {
		return new ResponseBean(Constant.RESPONSE_CODE_FAIL, Constant.RESPONSE_MSG_FAIL, data);
	}

	/**
	 * 响应失败
	 *
	 * @param msg
	 *            响应信息
	 * @param data
	 *            响应内容
	 * @return
	 */
	protected Object responseFail(String msg, Object data) {
		return new ResponseBean(Constant.RESPONSE_CODE_FAIL, msg, data);
	}

	/**
	 * 响应报错
	 *
	 * @param msg
	 *            响应信息
	 * @return
	 */
	protected Object responseException(String msg) {
		return new ResponseBean(Constant.RESPONSE_CODE_EXCEPTION, msg, null);
	}

	/**
	 * 响应报错
	 *
	 * @param data
	 *            响应内容
	 * @return
	 */
	protected Object responseException(Object data) {
		return new ResponseBean(Constant.RESPONSE_CODE_EXCEPTION, Constant.RESPONSE_MSG_EXCEPTION, data);
	}

	/**
	 * 响应报错
	 *
	 * @param msg
	 *            响应信息
	 * @param data
	 *            响应内容
	 * @return
	 */
	protected Object responseException(String msg, Object data) {
		return new ResponseBean(Constant.RESPONSE_CODE_EXCEPTION, msg, data);
	}
}
