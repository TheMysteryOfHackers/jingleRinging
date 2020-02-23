package com.jingle.oa.device.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingle.core.base.controller.BaseController;
import com.jingle.core.util.AlipayUtil;
import com.jingle.core.util.CommonUtil;
import com.jingle.oa.device.entity.Devicepurchaserecords;
import com.jingle.oa.device.service.IDevicepurchaserecordsService;

/**
 * <p>
 * 交易记录
 * </p>
 *
 * @author mowen
 * @since 2019-12-05
 */
@RestController
@RequestMapping("/device/devicepurchaserecords")
public class DevicepurchaserecordsController extends BaseController {
	@Autowired
	private IDevicepurchaserecordsService iDevicepurchaserecordsService;

	/**
	 * 查询交易记录列表
	 * @param uname 名字
	 * @param gmtPayment 交易时间
	 * @param page 分页对象
	 * @return
	 */
	// @RequiresPermissions("")
	@GetMapping("/list")
	public Object list(String uname, String gmtPayment, Page page) {
		// 列表内容
		QueryWrapper<Devicepurchaserecords> queryWrapper = new QueryWrapper<Devicepurchaserecords>().orderByDesc("dpr.crtm");
		if (!CommonUtil.isBlank(uname)) {
			queryWrapper.like("u.name", uname);
		}
		if (!CommonUtil.isBlank(gmtPayment)) {
			queryWrapper.like("gmt_payment", gmtPayment);
		}
		IPage iPage = iDevicepurchaserecordsService.pageVo(page, queryWrapper);
		return responseSuccess(iPage);
	}

	/**
	 * 退款
	 * @param id 交易记录的id
	 * @return
	 */
	@DeleteMapping("/refund/{id}")
	public Object refund(@PathVariable("id") String id) {
		Devicepurchaserecords devicepurchaserecord = iDevicepurchaserecordsService.getById(id);
		//商户订单号
		String outTradeNo = devicepurchaserecord.getOutTradeNo();
		//支付宝交易号
		String tradeNo = devicepurchaserecord.getTradeNo();
		//退款的金额
		String refundAmount=devicepurchaserecord.getBuyerPayAmount().toString();
		//退款的原因说明
		String refundReason = "正常退款";
		// 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
		String out_request_no=devicepurchaserecord.getOutTradeNo();
		// 发起退款
		String result = AlipayUtil.aliRefund(outTradeNo, tradeNo, refundAmount, refundReason, out_request_no);
		// 解析json字符串
		JSONObject parseObject = JSON.parseObject(result);
		// 解析json的alipay_trade_refund_response对象，获得核心参数对象
		JSONObject alipay_response = parseObject.getJSONObject("alipay_trade_refund_response");
		System.out.println(alipay_response);
		// 获取code的值判断是否退款成功
		if (alipay_response.getInteger("code") == 10000) {
			System.out.println("-------------------退款成功----------------------------------");
			if (iDevicepurchaserecordsService.removeById(id)) {
				return responseSuccess("退款成功，已退款回" + alipay_response.getString("buyer_logon_id") + ",请注意查收！");
			} else {
				return responseFail(alipay_response.getString("sub_msg"));
			}
		} else {
			return responseFail(alipay_response.getString("sub_msg"));
		}

	}
}
