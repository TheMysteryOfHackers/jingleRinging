package com.jingle.oa.system.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jingle.core.base.entity.BaseEntity;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author mowen
 * @since 2019-12-05
 */
public class Role extends BaseEntity<Role> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private String id;
	/**
	 * 部门id
	 */
	@TableField("did")
	private String did;
	/**
	 * 父id
	 */
	@TableField("pid")
	private String pid;
	/**
	 * 名称
	 */
	@TableField("name")
	private String name;
	/**
	 * 备注
	 */
	@TableField("remarks")
	private String remarks;
	/**
	 * 等级
	 */
	@TableField("level")
	private BigDecimal level;
	/**
	 * 排序
	 */
	@TableField("sort")
	private BigDecimal sort;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getLevel() {
		return level;
	}

	public void setLevel(BigDecimal level) {
		this.level = level;
	}

	public BigDecimal getSort() {
		return sort;
	}

	public void setSort(BigDecimal sort) {
		this.sort = sort;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Role{" + ", id=" + id + ", did=" + did + ", pid=" + pid + ", name=" + name + ", remarks=" + remarks + ",  level=" + level + ", sort=" + sort + "}";
	}
}
