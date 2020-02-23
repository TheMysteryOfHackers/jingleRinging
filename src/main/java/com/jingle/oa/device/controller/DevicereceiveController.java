package com.jingle.oa.device.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingle.core.base.controller.BaseController;
import com.jingle.core.util.CodeUtil;
import com.jingle.core.util.CommonUtil;
import com.jingle.core.util.Constant;
import com.jingle.oa.device.entity.Devicereceive;
import com.jingle.oa.device.entity.Devicetype;
import com.jingle.oa.device.service.IDeviceService;
import com.jingle.oa.device.service.IDevicereceiveService;
import com.jingle.oa.device.service.IDevicetypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 设备领用控制器
 * </p>
 *
 * @author mowen
 * @since 2019-12-05
 */
@RestController
@RequestMapping("/device/devicereceive")
public class DevicereceiveController extends BaseController {

    @Autowired
    private IDevicereceiveService iDevicereceiveService;

    @Autowired
    private IDevicetypeService iDevicetypeService;

    @Autowired
    private IDeviceService iDeviceService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获得设备领用列表
     *
     * @param receipt
     * @param recipients
     * @param status
     * @param page
     * @return
     */
    @RequiresPermissions("00050015")
    @GetMapping("/list")
    public Object list(String receipt, String recipients,String status , Page page) {
        // 列表内容
        QueryWrapper<Devicereceive> queryWrapper = new QueryWrapper<Devicereceive>().orderByDesc("crtm");
        if (!CommonUtil.isBlank(receipt)) {
            queryWrapper.like("receipt", receipt);
        }
        if (!CommonUtil.isBlank(recipients)) {
            queryWrapper.like("recipients", recipients);
        }
        if (!CommonUtil.isBlank(status)) {
            queryWrapper.eq("status", status);
        }
        IPage iPage = iDevicereceiveService.page(page, queryWrapper);
        return responseSuccess(iPage);
    }

    /**
     * 转跳到新增
     *
     * @return
     */
    @RequiresPermissions("000500150000")
    @GetMapping("/toInsert")
    public Object toInsert() {
        //														获得有设备的类型
        List<Devicetype> devicetypes = iDevicetypeService.getHaveDeviceOfType();
        if (CommonUtil.isBlank(devicetypes)) {
            responseFail("无可领用设备");
        }
        return responseSuccess(devicetypes);
    }


    /**
     * 新增
     *
     * @param devicereceive 设备领用对象
     * @return
     */
    @RequiresPermissions("000500150000")
    @PostMapping("/insert")
    public Object insert(String dtid, Devicereceive devicereceive) {


//设置领用时间
        devicereceive.setReceivedate(CommonUtil.date6());
        //设置状态 状态 1-领用、2-归还
        devicereceive.setStatus(new BigDecimal("1"));
        //设置单据号
        //重redis获取单据值
        Long codeLong = redisTemplate.opsForValue().increment(Constant.DEVICERECEIVE_CODE_REDIS);
        //生成单据号
        String receipt = CodeUtil.getDevicereceiveCode(codeLong);
        devicereceive.setReceipt(receipt);
        if (iDevicereceiveService.saveReceipt(dtid, devicereceive)) {
            return responseSuccess(receipt);
        } else {
            return responseFail();
        }
    }

    /**
     * 转跳到更新
     *
     * @param id 编辑的id
     * @return
     */
    @RequiresPermissions("000500150005")
    @GetMapping("/toRestore/{id}")
    public Object toRestore(@PathVariable("id") String id) {
        return responseSuccess(iDevicereceiveService.getById(id));
    }

    /**
     * 更新
     *
     * @param Devicereceive 设备领用对象
     * @return
     */
    @RequiresPermissions("000500150005")
    @PutMapping("/restore")
    public Object restore(Devicereceive Devicereceive) {
        //设置归还日期
        Devicereceive.setReturndate(CommonUtil.date6());
        //状态 1-领用、2-归还
        Devicereceive.setStatus(new BigDecimal("2"));
        if (iDevicereceiveService.updateRestoreById(Devicereceive)) {
            return responseSuccess();
        } else {
            return responseFail();
        }

    }

    /**
     * 删除
     *
     * @param id 删除的id
     * @return
     */
    @RequiresPermissions("000500150010")
    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable("id") String id) {
        if (iDevicereceiveService.removeById(id)) {
            return responseSuccess();
        } else {
            return responseFail();
        }
    }
}
