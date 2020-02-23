package com.jingle.oa.system.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 职工-部门表
 * </p>
 *
 * @author mowen
 * @since 2019-12-06
 */
@TableName("user_role")
public class UserRole extends Model<UserRole> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private String id;
	/**
	 * 员工id
	 */
	@TableField("uid")
	private String uid;
	/**
	 * 岗位id
	 */
	@TableField("rid")
	private String rid;

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

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserRole{" + ", id=" + id + ", uid=" + uid + ", rid=" + rid + "}";
	}
}
