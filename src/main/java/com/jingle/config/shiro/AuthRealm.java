package com.jingle.config.shiro;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.jingle.core.util.Constant;
import com.jingle.oa.system.entity.Token;

public class AuthRealm extends AuthorizingRealm {

	/*
	 * 多重写一个support 标识这个Realm是专门用来验证JwtToken 不负责验证其他的token（UsernamePasswordToken）
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		// 这个token就是从过滤器中传入的jwtToken
		return token instanceof JwtToken;
	}

	// 认证.登录
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		String jwt = (String) token.getPrincipal();
		if (jwt == null) {
			throw new NullPointerException("jwtToken 不允许为空");
		}
		return new SimpleAuthenticationInfo(jwt, jwt, "JwtRealm");
	}

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Token token = (Token) request.getAttribute(Constant.REQUEST_TOKEN_KEY);
		List<String> per = JSON.parseArray(token.getExpression(), String.class);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addStringPermissions(per);
		return authorizationInfo;
	}

}
