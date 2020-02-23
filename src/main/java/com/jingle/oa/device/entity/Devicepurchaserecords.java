package com.jingle.oa.device.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jingle.core.base.entity.BaseEntity;

/**
 * <p>
 * 商品购买记录
 * </p>
 *
 * @author mowen
 * @since 2020-01-29
 */
public class Devicepurchaserecords extends BaseEntity<Devicepurchaserecords> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    /**
     * 购买的商品类型id
     */
    @TableField("dtid")
    private String dtid;
    /**
     * 支付宝交易号
     */
    @TableField("trade_no")
    private String tradeNo;
    /**
     * 商户订单号
     */
    @TableField("out_trade_no")
    private String outTradeNo;

    /**
     * 付款金额
     */
    @TableField("buyer_pay_amount")
    private BigDecimal buyerPayAmount;
    /**
     * 订单标题
     */
    @TableField("subject")
    private String subject;
    /**
     * 商品描述
     */
    @TableField("body")
    private String body;
    /**
     * 交易付款时间
     */
    @TableField("gmt_payment")
    private Date gmtPayment;
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
     * 得到 购买的商品类型id
     */
    public String getDtid() {
        return dtid;
    }

	/**
     * 设置 购买的商品类型id
     */
    public void setDtid(String dtid) {
        this.dtid = dtid;
    }

	/**
     * 得到 支付宝交易号
     */
    public String getTradeNo() {
        return tradeNo;
    }

	/**
     * 设置 支付宝交易号
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

	/**
     * 得到 商户订单号
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

	/**
     * 设置 商户订单号
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }


	/**
     * 得到 付款金额
     */
    public BigDecimal getBuyerPayAmount() {
        return buyerPayAmount;
    }

	/**
     * 设置 付款金额
     */
    public void setBuyerPayAmount(BigDecimal buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }

	/**
     * 得到 订单标题
     */
    public String getSubject() {
        return subject;
    }

	/**
     * 设置 订单标题
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

	/**
     * 得到 商品描述
     */
    public String getBody() {
        return body;
    }

	/**
     * 设置 商品描述
     */
    public void setBody(String body) {
        this.body = body;
    }

	/**
     * 得到 交易付款时间
     */
    public Date getGmtPayment() {
        return gmtPayment;
    }

	/**
     * 设置 交易付款时间
     */
    public void setGmtPayment(Date gmtPayment) {
        this.gmtPayment = gmtPayment;
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
        return "Devicepurchaserecords{" +
        ", id=" + id +
        ", dtid=" + dtid +
        ", tradeNo=" + tradeNo +
        ", outTradeNo=" + outTradeNo +
        ", buyerPayAmount=" + buyerPayAmount +
        ", subject=" + subject +
        ", body=" + body +
        ", gmtPayment=" + gmtPayment +
        ", crer=" + crer +
        ", mder=" + mder +
        "}";
    }
}
