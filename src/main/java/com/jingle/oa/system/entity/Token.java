package com.jingle.oa.system.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jingle.core.base.entity.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author mowen
 * @since 2019-12-08
 */
public class Token extends BaseEntity<Token> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private String id;
	/**
	 * 用户id
	 */
	@TableField("uid")
	private String uid;
	/**
	 * token
	 */
	@TableField("token")
	private String token;
	/**
	 * 权限
	 */
	@TableField("expression")
	private String expression;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Token{" + ", id=" + id + ", uid=" + uid + ", token=" + token + ", expression=" + expression + "}";
	}
}
