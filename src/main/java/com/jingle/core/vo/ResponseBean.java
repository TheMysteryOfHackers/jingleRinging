package com.jingle.core.vo;

import java.io.Serializable;

public class ResponseBean implements Serializable {

	private Integer code;
	private String msg;
	private Object data;

	public ResponseBean(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ResponseBean(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	private ResponseBean(Builder builder) {
		this.code = builder.code;
		this.msg = builder.msg;
		this.data = builder.data;
	}

	public static class Builder {
		private Integer code;
		private String msg;
		private Object data;

		public Builder code(Integer code) {
			this.code = code;
			return this;
		}

		public Builder msg(String msg) {
			this.msg = msg;
			return this;
		}

		public Builder data(Object data) {
			this.data = data;
			return this;
		}

		public ResponseBean build() {
			return new ResponseBean(this);
		}
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
