package com.jingle.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

//这个就类似UsernamePasswordToken
public class JwtToken implements AuthenticationToken {

	private String token;

	public JwtToken(String token) {
		this.token = token;
	}

	@Override // 类似是用户名
	public Object getPrincipal() {
		return token;
	}

	@Override // 类似密码
	public Object getCredentials() {
		return token;
	}
	// 返回的都是jwt
}
