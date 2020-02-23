package com.jingle.oa.device.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jingle.core.base.entity.BaseEntity;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * <p>
 * 设备明细
 * </p>
 *
 * @author mowen
 * @since 2019-12-11
 */
public class Device extends BaseEntity<Device> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    /**
     * 设备类型id
     */
    @TableField("dtid")
    private String dtid;
    /**
     * 设备流水号
     */
    @TableField("code")
    private String code;
    /**
     * 净值
     */
	@Excel(name = "净值", orderNum = "1")
    @TableField("residual")
    private BigDecimal residual;
    /**
     * 购买价钱
     */
	@Excel(name = "购买价钱", orderNum = "2")
    @TableField("price")
    private BigDecimal price;
    /**
     * 状态 1-入库、2-出库中、3-出库、4-领用、5-报修
     */
    @TableField("status")
    private BigDecimal status;
    /**
     * 生产日期
     */
	@Excel(name = "生产日期", orderNum = "3")
    @TableField("proddate")
    private String proddate;
    /**
     * 登记人
     */
    @TableField("creator")
    private String creator;
    /**
     * 登记时间
     */
    @TableField("createtime")
    private String createtime;
    /**
     * 购买人
     */
    @TableField("buyer")
    private String buyer;
    /**
     * 购买日期
     */
    @TableField("buydate")
    private String buydate;
    /**
     * 序列号
     */
	@Excel(name = "序列号", orderNum = "4")
    @TableField("sno")
    private String sno;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDtid() {
        return dtid;
    }

    public void setDtid(String dtid) {
        this.dtid = dtid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getResidual() {
        return residual;
    }

    public void setResidual(BigDecimal residual) {
        this.residual = residual;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public String getProddate() {
        return proddate;
    }

    public void setProddate(String proddate) {
        this.proddate = proddate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuydate() {
        return buydate;
    }

    public void setBuydate(String buydate) {
        this.buydate = buydate;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getCrer() {
        return crer;
    }

    public void setCrer(String crer) {
        this.crer = crer;
    }

    public String getMder() {
        return mder;
    }

    public void setMder(String mder) {
        this.mder = mder;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Device{" +
        ", id=" + id +
        ", dtid=" + dtid +
        ", code=" + code +
        ", residual=" + residual +
        ", price=" + price +
        ", status=" + status +
        ", proddate=" + proddate +
        ", creator=" + creator +
        ", createtime=" + createtime +
        ", buyer=" + buyer +
        ", buydate=" + buydate +
        ", sno=" + sno +
        ", crer=" + crer +
        ", mder=" + mder +
        "}";
    }
}
