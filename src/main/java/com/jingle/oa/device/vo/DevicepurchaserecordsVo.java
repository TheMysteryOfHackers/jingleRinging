package com.jingle.oa.device.vo;

import com.jingle.oa.device.entity.Devicepurchaserecords;

/**
 * <p>
 * 商品购买记录
 * </p>
 *
 * @author mowen
 * @since 2020-01-29
 */
public class DevicepurchaserecordsVo extends Devicepurchaserecords {
	private String dtname;
	private String uname;

	public String getDtname() {
		return dtname;
	}

	public void setDtname(String dtname) {
		this.dtname = dtname;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Override
	public String toString() {
		return "DevicepurchaserecordsVo [dtname=" + dtname + ", uname=" + uname + "]";
	}

}
