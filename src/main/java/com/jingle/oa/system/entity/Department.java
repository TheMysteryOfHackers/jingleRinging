package com.jingle.oa.system.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jingle.core.base.entity.BaseEntity;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author mowen
 * @since 2019-12-05
 */
public class Department extends BaseEntity<Department> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private String id;
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
	 * 等级
	 */
	@TableField("level")
	private BigDecimal level;
	/**
	 * 排序
	 */
	@TableField("sort")
	private BigDecimal sort;
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
		return "Department{" + ", id=" + id + ", pid=" + pid + ", name=" + name + ", level=" + level + ", sort=" + sort + ", remarks=" + remarks + "}";
	}
}
