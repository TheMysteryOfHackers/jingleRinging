package com.jingle.oa.device.vo;

import com.jingle.oa.device.entity.Devicescrap;

public class DevicescrapVo extends Devicescrap {
    private String uname;
    private String dcode;
    private String dtname;
    private String dtmodel;
    private String dtbrand;

    public String getUname() {
        return this.uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDcode() {
        return this.dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    public String getDtname() {
        return this.dtname;
    }

    public void setDtname(String dtname) {
        this.dtname = dtname;
    }

    public String getDtmodel() {
        return this.dtmodel;
    }

    public void setDtmodel(String dtmodel) {
        this.dtmodel = dtmodel;
    }

    public String getDtbrand() {
        return this.dtbrand;
    }

    public void setDtbrand(String dtbrand) {
        this.dtbrand = dtbrand;
    }

    @Override
    public String toString() {
        return "DevicescrapVo{" +
                "uname='" + uname + '\'' +
                ", dcode='" + dcode + '\'' +
                ", dtname='" + dtname + '\'' +
                ", dtmodel='" + dtmodel + '\'' +
                ", dtbrand='" + dtbrand + '\'' +
                '}';
    }
}
