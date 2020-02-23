package com.jingle.oa.device.vo;

import com.jingle.oa.device.entity.Device;

public class DeviceVo extends Device {
	private String ucreator;
	private String dtname;
	private String dtbrand;
	private String dtmodel;

	public String getUcreator() {
		return ucreator;
	}

	public void setUcreator(String ucreator) {
		this.ucreator = ucreator;
	}

	public String getDtname() {
		return dtname;
	}

	public void setDtname(String dtname) {
		this.dtname = dtname;
	}

	public String getDtbrand() {
		return dtbrand;
	}

	public void setDtbrand(String dtbrand) {
		this.dtbrand = dtbrand;
	}

	public String getDtmodel() {
		return dtmodel;
	}

	public void setDtmodel(String dtmodel) {
		this.dtmodel = dtmodel;
	}

	@Override
	public String toString() {
		return "DeviceVo [ucreator=" + ucreator + ", dtname=" + dtname + ", dtbrand=" + dtbrand + ", dtmodel=" + dtmodel + "]";
	}

}
