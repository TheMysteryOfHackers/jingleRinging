package com.jingle.oa.device.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jingle.core.base.entity.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 设备维修表
 * </p>
 *
 * @author mowen
 * @since 2020-02-03
 */
public class Devicerepair extends BaseEntity<Devicerepair> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    /**
     * 设备id
     */
    @TableField("did")
    private String did;
    /**
     * 报修人
     */
    @TableField("damager")
    private String damager;
    /**
     * 报修时间
     */
    @TableField("damagedate")
    private String damagedate;
    /**
     * 报修备注
     */
    @TableField("damageremarks")
    private String damageremarks;
    /**
     * 维修时间
     */
    @TableField("repairdate")
    private String repairdate;
    /**
     * 维修人
     */
    @TableField("repairer")
    private String repairer;
    /**
     * 维修备注
     */
    @TableField("repairremarks")
    private String repairremarks;
    /**
     * 状态 1-报修、2-维修
     */
    @TableField("status")
    private BigDecimal status;
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
     * 得到 报修人
     */
    public String getDamager() {
        return damager;
    }

	/**
     * 设置 报修人
     */
    public void setDamager(String damager) {
        this.damager = damager;
    }

	/**
     * 得到 报修时间
     */
    public String getDamagedate() {
        return damagedate;
    }

	/**
     * 设置 报修时间
     */
    public void setDamagedate(String damagedate) {
        this.damagedate = damagedate;
    }

	/**
     * 得到 报修备注
     */
    public String getDamageremarks() {
        return damageremarks;
    }

	/**
     * 设置 报修备注
     */
    public void setDamageremarks(String damageremarks) {
        this.damageremarks = damageremarks;
    }

	/**
     * 得到 维修时间
     */
    public String getRepairdate() {
        return repairdate;
    }

	/**
     * 设置 维修时间
     */
    public void setRepairdate(String repairdate) {
        this.repairdate = repairdate;
    }

	/**
     * 得到 维修人
     */
    public String getRepairer() {
        return repairer;
    }

	/**
     * 设置 维修人
     */
    public void setRepairer(String repairer) {
        this.repairer = repairer;
    }

	/**
     * 得到 维修备注
     */
    public String getRepairremarks() {
        return repairremarks;
    }

	/**
     * 设置 维修备注
     */
    public void setRepairremarks(String repairremarks) {
        this.repairremarks = repairremarks;
    }

	/**
     * 得到 状态 1-报修、2-维修
     */
    public BigDecimal getStatus() {
        return status;
    }

	/**
     * 设置 状态 1-报修、2-维修
     */
    public void setStatus(BigDecimal status) {
        this.status = status;
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
        return "Devicerepair{" +
        ", id=" + id +
        ", did=" + did +
        ", damager=" + damager +
        ", damagedate=" + damagedate +
        ", damageremarks=" + damageremarks +
        ", repairdate=" + repairdate +
        ", repairer=" + repairer +
        ", repairremarks=" + repairremarks +
        ", status=" + status +
        ", crer=" + crer +
        ", mder=" + mder +
        "}";
    }
}
