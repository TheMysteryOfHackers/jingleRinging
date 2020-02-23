
package com.jingle.config.shiro;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jingle.core.util.CommonUtil;
import com.jingle.core.util.Constant;
import com.jingle.core.util.JwtTokenUtil;
import com.jingle.core.vo.ResponseBean;
import com.jingle.oa.system.entity.Token;
import com.jingle.oa.system.entity.User;
import com.jingle.oa.system.service.IPermissionService;
import com.jingle.oa.system.service.ITokenService;
import com.jingle.oa.system.service.IUserService;

@Component
public class TokenFilter extends AccessControlFilter {

	@Autowired
	private ITokenService iTokenService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private IUserService iUserService;
	@Autowired
	private IPermissionService iPermissionService;

	private Logger log = LoggerFactory.getLogger(TokenFilter.class);

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		if (httpServletRequest.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(httpServletRequest.getMethod())) {
			return true;
		}
		httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		httpServletResponse.addHeader("Access-Control-Allow-Headers", "Content-Type");
		httpServletResponse.addHeader("Access-Control-Max-Age", "1800");// 30 min
		// 白名单
		if (httpServletRequest.getRequestURI().lastIndexOf("/login") > -1) {
			return true;
		}
		if (httpServletRequest.getRequestURI().lastIndexOf("/notify") > -1) {
			return true;
		}

		// 获取token
		String token = jwtTokenUtil.getToken(httpServletRequest);
		// token不为空
		if (!CommonUtil.isBlank(token) && !CommonUtil.isEquals("null", token)) {
			// 校验token
			String uid = jwtTokenUtil.getUserId(token);

			Date timeOut = jwtTokenUtil.getTokenTimeOut(token);
			System.out.println(CommonUtil.date6(timeOut));
			if (!CommonUtil.isBlank(uid)) {
				QueryWrapper<Token> wrapper = new QueryWrapper<Token>().eq("token", token);
				Token tokenObject = iTokenService.getOne(wrapper);
				if (!CommonUtil.isBlank(tokenObject)) {
					// 是否需要刷新
					if (jwtTokenUtil.isFlush(token)) {
						request.setAttribute("filterFlag", "refreshToekn");
						return false;
					} else {
						// 交给Realm类处理
						// 委托 realm 进行登录认证
						JwtToken jwtToken = new JwtToken(token);
						// 所以这个地方最终还是调用JwtRealm进行的认证
						getSubject(request, response).login(jwtToken);
						request.setAttribute(Constant.REQUEST_TOKEN_KEY, tokenObject);
						request.setAttribute(Constant.REQUEST_USERID_KEY, tokenObject.getUid());
						return true;
					}
				} else {
					request.setAttribute("filterFlag", "otherLogin");
					return false;
				}
			} else {
				// 校验失败
				request.setAttribute("filterFlag", "tokenBad");
				return false;
			}
		} else {
			request.setAttribute("filterFlag", "noToken");
			return false;
		}
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setContentType("application/json;charset=UTF-8");
		PrintWriter out = httpServletResponse.getWriter();
		String filterFlag = (String) httpServletRequest.getAttribute("filterFlag");
		String token = jwtTokenUtil.getToken(httpServletRequest);
		if (CommonUtil.isEquals(filterFlag, "noToken")) {
			log.info("{}该url被拦截。", httpServletRequest.getRequestURI());
			ResponseBean responseBean = new ResponseBean(Constant.RESPONSE_CODE_NOLOGIN, Constant.RESPONSE_MSG_NOLOGIN, null);
			out.write(JSON.toJSONString(responseBean));
		} else if (CommonUtil.isEquals(filterFlag, "tokenBad")) {
			log.info("[{}],坏掉的token。", token);
			ResponseBean responseBean = new ResponseBean(Constant.RESPONSE_CODE_BADTOKEN, "坏掉的token。", null);
			out.write(JSON.toJSONString(responseBean));
			return false;
		} else if (CommonUtil.isEquals(filterFlag, "refreshToekn")) {
			// 校验token
			String uid = jwtTokenUtil.getUserId(token);
			User user = iUserService.getById(uid);
			String tokenStr = jwtTokenUtil.generalToken(user.getId(), JSON.toJSONString(user));
			Token newTokenObj = new Token();
			newTokenObj.setUid(user.getId());
			newTokenObj.setToken(tokenStr);
			List<String> codes = iPermissionService.listCodeByUid(uid);
			newTokenObj.setExpression(JSON.toJSONString(codes));
			iTokenService.saveNewToken(newTokenObj);
			httpServletRequest.getHeader(Constant.REQUEST_TOKEN_HEADER);
			ResponseBean responseBean = new ResponseBean(Constant.RESPONSE_CODE_REFRESH, Constant.RESPONSE_MSG_REFRESH, tokenStr);
			out.write(JSON.toJSONString(responseBean));
			log.info("[{}],被替换为[{}]。", token, tokenStr);
		} else if (CommonUtil.isEquals(filterFlag, "otherLogin")) {
			log.info("[{}],token与数据库不匹配，可能在别的地方登陆。", token);
			ResponseBean responseBean = new ResponseBean(Constant.RESPONSE_CODE_BADTOKEN, "可能在别的地方登陆。", null);
			out.write(JSON.toJSONString(responseBean));

		}
		out.flush();
		out.close();
		return false;
	}

}
