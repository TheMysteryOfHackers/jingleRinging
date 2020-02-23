package com.jingle.oa.system.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jingle.core.base.entity.BaseEntity;

/**
 * <p>
 * 员工表
 * </p>
 *
 * @author mowen
 * @since 2019-11-28
 */
public class User extends BaseEntity<User> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private String id;
	/**
	 * 职工名称
	 */
	@TableField("name")
	private String name;
	/**
	 * 密码
	 */
	@TableField("pwd")
	private String pwd;
	@TableField("rname")
	private String rname;
	/**
	 * 地址
	 */
	@TableField("address")
	private String address;
	/**
	 * 手机
	 */
	@TableField("mobile")
	private BigDecimal mobile;
	/**
	 * 性别 1-男、2-女
	 */
	@TableField("sex")
	private BigDecimal sex;
	@TableField("email")
	private String email;
	/**
	 * 生日
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("birth")
	private Date birth;
	/**
	 * 备注
	 */
	@TableField("remarks")
	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getMobile() {
		return mobile;
	}

	public void setMobile(BigDecimal mobile) {
		this.mobile = mobile;
	}

	public BigDecimal getSex() {
		return sex;
	}

	public void setSex(BigDecimal sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User{" + ", id=" + id + ", name=" + name + ", pwd=" + pwd + ", rname=" + rname + ", address=" + address + ", mobile=" + mobile + ", sex=" + sex + ", email=" + email + ", birth=" + birth + ", remarks=" + remarks + "}";
	}
}
