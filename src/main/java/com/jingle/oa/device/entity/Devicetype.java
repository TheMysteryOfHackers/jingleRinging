package com.jingle.oa.device.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jingle.core.base.entity.BaseEntity;

/**
 * <p>
 * 设备类型
 * </p>
 *
 * @author mowen
 * @since 2019-12-10
 */
public class Devicetype extends BaseEntity<Devicetype> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    /**
     * 名字
     */
    @TableField("name")
    private String name;
    /**
     * 品牌
     */
    @TableField("brand")
    private String brand;
    /**
     * 使用年限
     */
    @TableField("useyear")
    private BigDecimal useyear;
    /**
     * 国际编号
     */
    @TableField("intlcode")
    private String intlcode;
    /**
     * 原值
     */
    @TableField("original")
    private BigDecimal original;
    /**
     * 净残值率
     */
    @TableField("residualrate")
    private BigDecimal residualrate;
    /**
     * 型号
     */
    @TableField("model")
    private String model;
    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getUseyear() {
        return useyear;
    }

    public void setUseyear(BigDecimal useyear) {
        this.useyear = useyear;
    }

    public String getIntlcode() {
        return intlcode;
    }

    public void setIntlcode(String intlcode) {
        this.intlcode = intlcode;
    }

    public BigDecimal getOriginal() {
        return original;
    }

    public void setOriginal(BigDecimal original) {
        this.original = original;
    }

    public BigDecimal getResidualrate() {
        return residualrate;
    }

    public void setResidualrate(BigDecimal residualrate) {
        this.residualrate = residualrate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        return "Devicetype{" +
        ", id=" + id +
        ", name=" + name +
        ", brand=" + brand +
        ", useyear=" + useyear +
        ", intlcode=" + intlcode +
        ", original=" + original +
        ", residualrate=" + residualrate +
        ", model=" + model +
        ", remarks=" + remarks +
        ", crer=" + crer +
        ", mder=" + mder +
        "}";
    }
}
