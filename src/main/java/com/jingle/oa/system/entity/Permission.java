package com.jingle.oa.system.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jingle.core.base.entity.BaseEntity;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author mowen
 * @since 2019-12-05
 */
public class Permission extends BaseEntity<Permission> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private String id;
	/**
	 * 父id
	 */
	@TableField("pid")
	private String pid;
	/**
	 * 类型 1-显示菜单、2-跳转菜单、3-功能按钮
	 */
	@TableField("type")
	private BigDecimal type;
	/**
	 * 名字
	 */
	@TableField("name")
	private String name;
	/**
	 * 权限编码
	 */
	@TableField("code")
	private String code;
	/**
	 * url
	 */
	@TableField("url")
	private String url;
	/**
	 * 备注
	 */
	@TableField("remarks")
	private String remarks;
	/**
	 * 权限表达式（系统标识）
	 */
	@TableField("expression")
	private String expression;
	/**
	 * 排序
	 */
	@TableField("sort")
	private BigDecimal sort;
	/**
	 * 等级
	 */
	@TableField("level")
	private BigDecimal level;
	/**
	 * 图标
	 */
	@TableField("icon")
	private String icon;

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

	public BigDecimal getType() {
		return type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public BigDecimal getSort() {
		return sort;
	}

	public void setSort(BigDecimal sort) {
		this.sort = sort;
	}

	public BigDecimal getLevel() {
		return level;
	}

	public void setLevel(BigDecimal level) {
		this.level = level;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Permission{" + ", id=" + id + ", pid=" + pid + ", type=" + type + ", name=" + name + ", code=" + code + ", url=" + url + ", remarks=" + remarks + ", expression=" + expression + ", sort=" + sort + ", level=" + level + ", icon=" + icon + "}";
	}
}
