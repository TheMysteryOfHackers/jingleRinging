package com.jingle.core.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class JwtTokenUtil {

	/**
	 * jwtSecret
	 */
	@Value("${jwt.secret}")
	private String jwtSecret;
	/**
	 * 时效
	 */
	@Value("${jwt.duration}")
	private Long duration;

	@Value("${jwt.flush}")
	private Long flush;

	private Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

	public SecretKey generalKey() {
		String stringKey = jwtSecret;
		byte[] encodedKey = Base64.decodeBase64(stringKey);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}

	public String generalToken(String id, String subject) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey key = generalKey();
		// header Map
		Map<String, Object> header = new HashMap();
		header.put("alg", "HS256");
		header.put("typ", "JWT");
		JwtBuilder builder = Jwts.builder().setHeader(header).setId(UuidUtil.getUUID()).setIssuedAt(now).setSubject(subject).claim("id", id).signWith(signatureAlgorithm, key);
		if (duration >= 0) {
			long expMillis = nowMillis + duration;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		return builder.compact();
	}

	private Map getClaimsMap(String token) {
		if (CommonUtil.isBlank(token)) {
			return null;
		}
		try {
			Map<String, Object> jwtClaims = Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token).getBody();
			return jwtClaims;
		} catch (ExpiredJwtException e) {
			log.error("{}已过期", token);
		} catch (Exception e) {
			log.error("{}", e);
		}
		return null;
	}

	/**
	 * 获取token过期时间
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public Date getTokenTimeOut(String token) {
		// 解释token
		Map claims = getClaimsMap(token);
		if (CommonUtil.isBlank(claims)) {
			return null;
		}
		Object expObj = claims.get("exp");
		String exp = expObj.toString();
		// exp转换时间
		Long timestamp = Long.parseLong(exp) * 1000;
		Date expDate = new Date(timestamp);
		return expDate;
	}

	private String getSubject(String token) {
		Map claims = getClaimsMap(token);
		if (CommonUtil.isBlank(claims)) {
			return null;
		}
		return (String) claims.get("sub");
	}

	/**
	 * 获取当前用户id
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getUserId(String token) {
		Map claims = getClaimsMap(token);
		if (CommonUtil.isBlank(claims)) {
			return null;
		}
		return (String) claims.get("id");
	}

	public String getJwtSecret() {
		return jwtSecret;
	}

	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	// 获取token
	public String getToken(HttpServletRequest request) {
		String token = request.getHeader(Constant.REQUEST_TOKEN_HEADER);
		if (CommonUtil.isBlank(token)) {
			token = request.getParameter(Constant.REQUEST_TOKEN_HEADER);
		}
		return token;
	}

	// 获取token
	public boolean isFlush(String token) {

		if ((getTokenTimeOut(token).getTime() - CommonUtil.getDate().getTime()) <= flush) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		jwtTokenUtil.jwtSecret = "ossjk";
		jwtTokenUtil.duration = (long) 600000;
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyYTExNzhkMTRiYjQ0NmZhOTNlZDllYjNhN2NkNjIzMSIsImlhdCI6MTU3NTAxNzM1Mywic3ViIjoiYWJjZGVmIiwiaWQiOiI4ODgiLCJleHAiOjE1NzUwMTc5NTN9.FLL633gRteVUmui9OcTE1CKcjNujyEdDkGyKtzwUZFw";
		System.out.println(token);
		System.out.println(jwtTokenUtil.getTokenTimeOut(token));
		System.out.println(jwtTokenUtil.getUserId(token));
		System.out.println(jwtTokenUtil.getSubject(token));

	}

}
