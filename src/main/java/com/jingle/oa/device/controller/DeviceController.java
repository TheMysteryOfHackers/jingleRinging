package com.jingle.oa.device.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingle.config.alipay.AlipayConfig;
import com.jingle.core.base.controller.BaseController;
import com.jingle.core.util.*;
import com.jingle.core.vo.ResponseBean;
import com.jingle.oa.device.entity.Device;
import com.jingle.oa.device.entity.Devicepurchaserecords;
import com.jingle.oa.device.entity.Devicetype;
import com.jingle.oa.device.service.IDeviceService;
import com.jingle.oa.device.service.IDevicepurchaserecordsService;
import com.jingle.oa.device.service.IDevicetypeService;
import com.jingle.oa.system.entity.User;
import com.jingle.oa.system.service.IUserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * <p>
 * 设备明细
 * </p>
 *
 * @author mowen
 * @since 2019-12-05
 */
@RestController
@RequestMapping("/device/device")
public class DeviceController extends BaseController {
    @Autowired
    private IDeviceService iDeviceService;
    @Autowired
    private IDevicetypeService iDevicetypeService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IDevicepurchaserecordsService iDevicepurchaserecordsService;

    /**
     * 获得设备列表
     *
     * @param model 型号
     * @param page  分页对象
     * @return
     */
    @RequiresPermissions("00050005")
    @GetMapping("/list")
    public ResponseBean list(String model, String status, Page page) {
        // 列表内容
        QueryWrapper<Device> queryWrapper = new QueryWrapper<Device>().orderByDesc("d.crtm");
        if (!CommonUtil.isBlank(model)) {
            queryWrapper.like("dt.model", model);
        }
        if (!CommonUtil.isBlank(status)) {
            queryWrapper.eq("d.status", status);
        }

        IPage iPage = iDeviceService.pageVo(page, queryWrapper);
        return (ResponseBean) responseSuccess(iPage);
    }

    /**
     * 转跳到新增页面时查询
     *
     * @return
     */
    @RequiresPermissions("000500050000")
    @GetMapping("/toInsert")
    public Object toInsert() {
        List<Devicetype> devicetypes = iDevicetypeService.list();
        List<User> users = iUserService.list();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("devicetypes", devicetypes);
        data.put("users", users);
        return responseSuccess(data);
    }

    /**
     * 新增
     *
     * @param device 设备对象
     * @return
     */
    @RequiresPermissions("000500050000")
    @PostMapping("/insert")
    public Object insert(Device device) {

        long codeLong = redisTemplate.opsForValue().increment(Constant.DEVICE_CODE_REDIS);
        String codeStr = CodeUtil.getDeviceCode(codeLong);
        device.setCode(codeStr);
        // 设置登记人
        String token = getToken();
        device.setCreator(jwtTokenUtil.getUserId(token));
        // 设置登记时间
        device.setCreatetime(CommonUtil.date6());
        // 状态 1-入库、2-出库中、3-出库、4-领用、5-报修
        device.setStatus(new BigDecimal(1));
        if (iDeviceService.save(device)) {
            return responseSuccess();
        } else {
            return responseFail();
        }
    }

    /**
     * 转跳到更新页面时查询
     *
     * @param id 要更改的id
     * @return
     */
    @RequiresPermissions("000500050005")
    @GetMapping("/toUpdate/{id}")
    public Object toUpdate(@PathVariable("id") String id) {
        List<Devicetype> devicetypes = iDevicetypeService.list();
        List<User> users = iUserService.list();
        Device device = iDeviceService.getById(id);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("devicetypes", devicetypes);
        data.put("users", users);
        data.put("device", device);
        return responseSuccess(data);
    }

    /**
     * 更新
     *
     * @param device 设备对象
     * @return
     */
    @RequiresPermissions("000500050005")
    @PutMapping("/update")
    public Object update(Device device) {
        // 设置登记人
        String token = getToken();
        User user = iUserService.getById(jwtTokenUtil.getUserId(token));
        device.setMder(user.getName());
        // 设置登记时间
        device.setMdtm(CommonUtil.getDate());
        if (iDeviceService.updateById(device)) {
            return responseSuccess();
        } else {
            return responseFail();
        }

    }

    /**
     * 删除
     *
     * @param id 要删除的id，可以是数组
     * @return
     */
    @RequiresPermissions("000500050010")
    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable("id") String[] id) {
        if (iDeviceService.removeByIds(Arrays.asList(id))) {
            return responseSuccess();
        } else {
            return responseFail();
        }
    }

    /**
     * 转跳上传页面
     *
     * @return
     */
    @GetMapping("/toUpload")
    public Object toUpload() {
        List<Devicetype> devicetypes = iDevicetypeService.list();
        List<User> users = iUserService.list();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("devicetypes", devicetypes);
        data.put("users", users);
        return responseSuccess(data);
    }

    /**
     * 上传
     *
     * @param file    上传的文件
     * @param dtid    设备类型
     * @param buyer   购买人
     * @param buydate 时间
     * @return
     * @throws IOException
     * @throws Exception
     */
    @PostMapping("/upload")
    public Object upload(MultipartFile file, String dtid, String buyer, String buydate) throws IOException, Exception {
        // 导入参数
        ImportParams params = new ImportParams();
        // 设置标题行
        params.setTitleRows(0);
        // 设置开始行
        params.setHeadRows(1);
        // 导入返回集合
        List<Device> list = ExcelImportUtil.importExcel(file.getInputStream(), Device.class, params);
        // 集合是否为空
        if (!CommonUtil.isBlank(list)) {
            // 遍历集合
            for (Device device : list) {
                // 判断流水号是否重复
                if (iDeviceService.getOne(new QueryWrapper<Device>().eq("code", device.getCode())) != null) {
                    return responseFail(device.getCode() + "流水号重复");
                }

                long codeLong = redisTemplate.opsForValue().increment(Constant.DEVICE_CODE_REDIS);
                String codeStr = CodeUtil.getDeviceCode(codeLong);
                device.setCode(codeStr);
                // 设备类型
                device.setDtid(dtid);
                // 购买人
                device.setBuyer(buyer);
                // 购买时间
                device.setBuydate(buydate);
                // 登记人
                device.setCreator(jwtTokenUtil.getUserId(getToken()));
                // 登记时间
                device.setCreatetime(CommonUtil.date6());
                // 状态 1.入库 2.出库中 3.出库 4.领用 5.报修
                device.setStatus(new BigDecimal(1));
            }
            if (iDeviceService.saveBatch(list)) {
                return responseSuccess();
            } else {
                return responseFail();
            }
        }

        return responseSuccess();
    }

    /**
     * 导出数据
     */
    @GetMapping("/export")
    public Object export(HttpServletResponse response) throws IOException {
        System.out.println("11165646464645");
        List<Device> devices = iDeviceService.list(new QueryWrapper<Device>().orderByDesc("crtm"));
        ExportParams params = new ExportParams();
        params.setSheetName("设备明细表");
        params.setType(ExcelType.XSSF);
        params.setFreezeCol(2);
        Workbook workbook = (XSSFWorkbook) ExcelExportUtil.exportExcel(params, Device.class, devices);
        if (!CommonUtil.isBlank(workbook)) {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("设备明细表.xlsx", "UTF-8"));
            workbook.write(response.getOutputStream());
        }
        return responseSuccess();
    }

    /**
     * 支付宝发起支付
     *
     * @param request
     * @param response
     * @param dtid     购买的设备类型
     * @param num      数量
     * @param remarks  备注
     * @return 返回重支付宝接口获得的二维码跳转页面
     * @throws AlipayApiException
     * @throws UnsupportedEncodingException
     */
    @PostMapping("/buy")
    public Object buy(HttpServletRequest request, HttpServletResponse response, String dtid, BigDecimal num, String remarks) throws AlipayApiException, UnsupportedEncodingException {
        response.setCharacterEncoding("UTF-8");

        QueryWrapper<Devicetype> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", dtid);
        Devicetype devicetype = iDevicetypeService.getOne(queryWrapper);

        BigDecimal amount = devicetype.getOriginal().multiply(num);
        String subject = "买入设备";
        String notice = "购买" + devicetype.getName();

        String json = "{" + "\"dtid\":\"" + dtid + "\"," + "\"num\":\"" + num + "\"," + "\"userID\":\"" + jwtTokenUtil.getUserId(getToken()) + "\"" + "}";

        String passback_params = URLEncoder.encode(json, "UTF-8");
        String result = "";

        result = AlipayUtil.sponsorPay(amount.toString(), subject, notice, passback_params);
        return responseSuccess(result);
    }

    /**
     * 支付宝异步通知
     *
     * @param response
     * @param request
     * @throws IOException
     * @throws AlipayApiException
     */
    @PostMapping("/notify")
    public void notify_yes(HttpServletResponse response, HttpServletRequest request) throws IOException, AlipayApiException {
        log.debug("**************支付宝异步通知到达***************");
        PrintWriter out = response.getWriter();
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); // 调用SDK验证签名

        if (signVerified) {// 验证成功
            // 商户订单号
            // String out_trade_no = new
            // String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 支付宝交易号
            // String trade_no = new
            // String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            if (trade_status.equals("TRADE_FINISHED")) {
                log.info("交易完成");
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                log.info("交易成功");
                // 获取回调参数
                String passback_params = params.get("passback_params");
                String dtid = null;
                JSONObject jsonObject = null;
                // 根据回调参数添加商品
                if (!CommonUtil.isBlank(passback_params)) {
                    jsonObject = JSON.parseObject(URLDecoder.decode(passback_params, "UTF-8"));
                    // 获取购买的设备类型id
                    dtid = jsonObject.getString("dtid");
                    // 设备类型id不为空
                    if (!CommonUtil.isBlank(dtid)) {

                        Integer num = jsonObject.getInteger("num");
                        // 购买数量不为空
                        if (!CommonUtil.isBlank(num)) {
                            Devicetype devicetype = iDevicetypeService.getById(dtid);
                            // 批量保存使用
                            List<Device> list = new ArrayList<Device>();
                            for (int i = 0; i < num; i++) {
                                Device device = new Device();
                                // 购买价格
                                device.setPrice(devicetype.getOriginal());
                                // 设置流水号
                                long codeLong = redisTemplate.opsForValue().increment(Constant.DEVICE_CODE_REDIS);
                                String codeStr = CodeUtil.getDeviceCode(codeLong);
                                device.setCode(codeStr);
                                // 设备类型
                                device.setDtid(dtid);
                                // 购买人
                                device.setBuyer(jsonObject.getString("userID"));
                                // 购买时间
                                device.setBuydate(CommonUtil.date6());
                                // 登记人
                                device.setCreator(jsonObject.getString("userID"));
                                // 登记时间
                                device.setCreatetime(CommonUtil.date6());
                                // 状态 1.入库 2.出库中 3.出库 4.领用 5.报修
                                device.setStatus(new BigDecimal(1));
                                list.add(device);
                            }
                            // 批量保存
                            iDeviceService.saveBatch(list);
                        }
                    }
                }

                // 添加购买记录
                Devicepurchaserecords devtmp = new Devicepurchaserecords();
                // 设置购买类型的id
                devtmp.setDtid(dtid);
                // 设置支付宝交易号
                devtmp.setTradeNo(params.get("trade_no"));
                // 设置商户订单号
                devtmp.setOutTradeNo(params.get("out_trade_no"));
                // 设置付款金额
                devtmp.setBuyerPayAmount(new BigDecimal(params.get("buyer_pay_amount")));
                // 设置订单标题
                devtmp.setSubject(params.get("subject"));
                // 设置描述
                devtmp.setBody(params.get("body"));
                // 设置交易时间
                devtmp.setGmtPayment(CommonUtil.getDate(params.get("gmt_payment"), "ISO6"));
                // 设置创建人
                devtmp.setCrer(jsonObject.getString("userID"));
                iDevicepurchaserecordsService.save(devtmp);
            }

            log.info("***************验签成功******************");
        } else {// 验证失败
            log.info("***************验签失败******************");
        }

    }

}
