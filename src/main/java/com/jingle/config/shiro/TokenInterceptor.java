package com.jingle.config.shiro;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jingle.core.util.CommonUtil;
import com.jingle.core.util.Constant;
import com.jingle.core.util.JwtTokenUtil;
import com.jingle.core.vo.ResponseBean;
import com.jingle.oa.system.entity.Token;
import com.jingle.oa.system.service.ITokenService;
import com.jingle.oa.system.service.IUserService;

@Component
public class TokenInterceptor implements HandlerInterceptor {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private ITokenService iTokenService;
	@Autowired
	private IUserService iUserService;

	private static final Logger log = LoggerFactory.getLogger(TokenInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
			return true;
		}
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		response.addHeader("Access-Control-Max-Age", "1800");// 30 min
		String token = getToken(request);
		log.info(token + "来了");
		if (!CommonUtil.isBlank(token) && !CommonUtil.isEquals("null", token)) {
			// 请求带token参数了
			Date timeoutDate = jwtTokenUtil.getTokenTimeOut(token);
			if (!CommonUtil.isBlank(timeoutDate)) {
				// 尝试从数据库查询token
				QueryWrapper<Token> queryWrapper = new QueryWrapper();
				queryWrapper.eq("token", token);
				Token tokenObj = iTokenService.getOne(queryWrapper);
				if (CommonUtil.isBlank(tokenObj)) {
					// token已经注销了
					log.warn(request.getRequestURI() + "，token已经注销了。");
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					ResponseBean responseBean = new ResponseBean(Constant.RESPONSE_CODE_NOLOGIN, "没有登录");
					out.write(JSON.toJSONString(responseBean));
					out.flush();
					out.close();
					return false;
				}
				// 超时时间
				long timeOutMils = timeoutDate.getTime();
				// 续约时间
				long flushMils = timeOutMils - (15 * 60 * 1000);
				// long flushMils = timeOutMils - (5* 1000);
				// 当前时间
				long currntMils = CommonUtil.getDate().getTime();

				if (currntMils > flushMils) {
					// 续约
					log.warn(request.getRequestURI() + "，token续约。");
					// 拿到用户id
					String uid = jwtTokenUtil.getUserId(token);
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					// 登录成功后，要删除该用户已有token
					QueryWrapper<Token> queryWrapper2 = new QueryWrapper();
					queryWrapper2.eq("uid", uid);
					iTokenService.remove(queryWrapper2);
					// 生成新token
					String newToken = jwtTokenUtil.generalToken(uid, JSON.toJSONString(iUserService.getById(uid)));
					// 保存新token
					Token newTokenObj = new Token();
					newTokenObj.setToken(newToken);
					newTokenObj.setUid(uid);
					iTokenService.save(newTokenObj);
					ResponseBean responseBean = new ResponseBean(Constant.RESPONSE_CODE_REFRESH, "token续约", newToken);
					out.write(JSON.toJSONString(responseBean));
					out.flush();
					out.close();
					return false;
				}

				// 验证通过
				return true;
			} else {
				// token失效，token 错误了
				log.warn(request.getRequestURI() + "，token失效或者错误了。");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				ResponseBean responseBean = new ResponseBean(Constant.RESPONSE_CODE_NOLOGIN, "没有登录");
				out.write(JSON.toJSONString(responseBean));
				out.flush();
				out.close();
				return false;
			}
		}
		log.warn(request.getRequestURI() + "，没有带token。");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ResponseBean responseBean = new ResponseBean(Constant.RESPONSE_CODE_NOLOGIN, "没有登录");
		out.write(JSON.toJSONString(responseBean));
		out.flush();
		out.close();
		return false;
	}

	public String getToken(HttpServletRequest request) {
		String token = null;
		if (!CommonUtil.isBlank(request.getHeader(Constant.REQUEST_TOKEN_HEADER))) {
			token = request.getHeader(Constant.REQUEST_TOKEN_HEADER);
		} else {
			token = request.getParameter(Constant.REQUEST_TOKEN_HEADER);
		}

		return token;

	}

}
