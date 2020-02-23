package com.jingle.oa.device.vo;

import com.jingle.oa.device.entity.Devicerepair;

/**
 * @author mowen
 * @create 2020/2/3-17:00
 */
public class DevicerepairVo extends Devicerepair {
    /**
     * 报修人
     */
    private String udamager;
    /**
     * 维修人
     */
    private String urepairer;
    /**
     * 流水号
     */
    private String code;

    /**
     * 报修人
     */
    public String getUdamager() {
        return this.udamager;
    }

    /**
     * 报修人
     */
    public void setUdamager(String udamager) {
        this.udamager = udamager;
    }

    /**
     * 维修人
     */
    public String getUrepairer() {
        return this.urepairer;
    }

    /**
     * 维修人
     */
    public void setUrepairer(String urepairer) {
        this.urepairer = urepairer;
    }

    /**
     * 流水号
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 流水号
     */
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DevicerepairVo{" +
                "udamager='" + udamager + '\'' +
                ", urepairer='" + urepairer + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
