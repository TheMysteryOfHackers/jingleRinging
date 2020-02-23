package com.jingle.oa.device.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jingle.core.base.entity.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 设备领用表
 * </p>
 *
 * @author mowen
 * @since 2020-02-03
 */
public class Devicereceive extends BaseEntity<Devicereceive> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    /**
     * 设备id
     */
    @TableField("did")
    private String did;
    /**
     * 单据号
     */
    @TableField("receipt")
    private String receipt;
    /**
     * 领用人
     */
    @TableField("recipients")
    private String recipients;
    /**
     * 领取时间
     */
    @TableField("receivedate")
    private String receivedate;
    /**
     * 归还日期
     */
    @TableField("returndate")
    private String returndate;
    /**
     * 状态 1-领用、2-归还
     */
    @TableField("status")
    private BigDecimal status;
    /**
     * 领用备注
     */
    @TableField("receiveremarks")
    private String receiveremarks;
    /**
     * 归还备注
     */
    @TableField("returnremarks")
    private String returnremarks;
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
     * 得到 单据号
     */
    public String getReceipt() {
        return receipt;
    }

	/**
     * 设置 单据号
     */
    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

	/**
     * 得到 领用人
     */
    public String getRecipients() {
        return recipients;
    }

	/**
     * 设置 领用人
     */
    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

	/**
     * 得到 领取时间
     */
    public String getReceivedate() {
        return receivedate;
    }

	/**
     * 设置 领取时间
     */
    public void setReceivedate(String receivedate) {
        this.receivedate = receivedate;
    }

	/**
     * 得到 归还日期
     */
    public String getReturndate() {
        return returndate;
    }

	/**
     * 设置 归还日期
     */
    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

	/**
     * 得到 状态 1-领用、2-归还
     */
    public BigDecimal getStatus() {
        return status;
    }

	/**
     * 设置 状态 1-领用、2-归还
     */
    public void setStatus(BigDecimal status) {
        this.status = status;
    }

	/**
     * 得到 领用备注
     */
    public String getReceiveremarks() {
        return receiveremarks;
    }

	/**
     * 设置 领用备注
     */
    public void setReceiveremarks(String receiveremarks) {
        this.receiveremarks = receiveremarks;
    }

	/**
     * 得到 归还备注
     */
    public String getReturnremarks() {
        return returnremarks;
    }

	/**
     * 设置 归还备注
     */
    public void setReturnremarks(String returnremarks) {
        this.returnremarks = returnremarks;
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
        return "Devicereceive{" +
        ", id=" + id +
        ", did=" + did +
        ", receipt=" + receipt +
        ", recipients=" + recipients +
        ", receivedate=" + receivedate +
        ", returndate=" + returndate +
        ", status=" + status +
        ", receiveremarks=" + receiveremarks +
        ", returnremarks=" + returnremarks +
        ", crer=" + crer +
        ", mder=" + mder +
        "}";
    }
}
