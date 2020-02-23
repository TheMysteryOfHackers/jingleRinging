package com.jingle.oa.device.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jingle.core.base.entity.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 设备报废
 * </p>
 *
 * @author mowen
 * @since 2020-02-01
 */
public class Devicescrap extends BaseEntity<Devicescrap> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    /**
     * 设备id
     */
    @TableField("did")
    private String did;
    /**
     * 报废人
     */
    @TableField("scraper")
    private String scraper;
    /**
     * 报废日期
     */
    @TableField("scraperdate")
    private String scraperdate;
    /**
     * 报废备注
     */
    @TableField("scrapremarks")
    private String scrapremarks;
    /**
     * 状态 1-申请、2-准许、3-拒绝
     */
    @TableField("status")
    private BigDecimal status;
    /**
     * 审批人
     */
    @TableField("approver")
    private String approver;
    /**
     * 审批意见
     */
    @TableField("approvalremarks")
    private String approvalremarks;
    /**
     * 审批日期
     */
    @TableField("approvaldate")
    private String approvaldate;
    /**
     * 创建人
     */
    @TableField("crer")
    private String crer;
    /**
     * 修改人
     */
    @TableField("mder")
    private String mder;


	/**
     * 得到 
     */
    public String getId() {
        return id;
    }

	/**
     * 设置 
     */
    public void setId(String id) {
        this.id = id;
    }

	/**
     * 得到 设备id
     */
    public String getDid() {
        return did;
    }

	/**
     * 设置 设备id
     */
    public void setDid(String did) {
        this.did = did;
    }

	/**
     * 得到 报废人
     */
    public String getScraper() {
        return scraper;
    }

	/**
     * 设置 报废人
     */
    public void setScraper(String scraper) {
        this.scraper = scraper;
    }

	/**
     * 得到 报废日期
     */
    public String getScraperdate() {
        return scraperdate;
    }

	/**
     * 设置 报废日期
     */
    public void setScraperdate(String scraperdate) {
        this.scraperdate = scraperdate;
    }

	/**
     * 得到 报废备注
     */
    public String getScrapremarks() {
        return scrapremarks;
    }

	/**
     * 设置 报废备注
     */
    public void setScrapremarks(String scrapremarks) {
        this.scrapremarks = scrapremarks;
    }

	/**
     * 得到 状态 1-申请、2-准许、3-拒绝
     */
    public BigDecimal getStatus() {
        return status;
    }

	/**
     * 设置 状态 1-申请、2-准许、3-拒绝
     */
    public void setStatus(BigDecimal status) {
        this.status = status;
    }

	/**
     * 得到 审批人
     */
    public String getApprover() {
        return approver;
    }

	/**
     * 设置 审批人
     */
    public void setApprover(String approver) {
        this.approver = approver;
    }

	/**
     * 得到 审批意见
     */
    public String getApprovalremarks() {
        return approvalremarks;
    }

	/**
     * 设置 审批意见
     */
    public void setApprovalremarks(String approvalremarks) {
        this.approvalremarks = approvalremarks;
    }

	/**
     * 得到 审批日期
     */
    public String getApprovaldate() {
        return approvaldate;
    }

	/**
     * 设置 审批日期
     */
    public void setApprovaldate(String approvaldate) {
        this.approvaldate = approvaldate;
    }

	/**
     * 得到 创建人
     */
    public String getCrer() {
        return crer;
    }

	/**
     * 设置 创建人
     */
    public void setCrer(String crer) {
        this.crer = crer;
    }

	/**
     * 得到 修改人
     */
    public String getMder() {
        return mder;
    }

	/**
     * 设置 修改人
     */
    public void setMder(String mder) {
        this.mder = mder;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Devicescrap{" +
        ", id=" + id +
        ", did=" + did +
        ", scraper=" + scraper +
        ", scraperdate=" + scraperdate +
        ", scrapremarks=" + scrapremarks +
        ", status=" + status +
        ", approver=" + approver +
        ", approvalremarks=" + approvalremarks +
        ", approvaldate=" + approvaldate +
        ", crer=" + crer +
        ", mder=" + mder +
        "}";
    }
}
