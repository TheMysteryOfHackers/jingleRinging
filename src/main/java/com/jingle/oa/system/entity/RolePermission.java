package com.jingle.oa.system.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 岗位权限表
 * </p>
 *
 * @author mowen
 * @since 2019-12-06
 */
@TableName("role_permission")
public class RolePermission extends Model<RolePermission> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private String id;
	/**
	 * 岗位id
	 */
	@TableField("rid")
	private String rid;
	/**
	 * 权限id
	 */
	@TableField("pid")
	private String pid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "RolePermission{" + ", id=" + id + ", rid=" + rid + ", pid=" + pid + "}";
	}
}
