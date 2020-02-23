package com.jingle.oa.device.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingle.core.base.controller.BaseController;
import com.jingle.core.util.CommonUtil;
import com.jingle.core.util.JwtTokenUtil;
import com.jingle.oa.device.entity.Device;
import com.jingle.oa.device.entity.Devicerepair;
import com.jingle.oa.device.entity.Devicetype;
import com.jingle.oa.device.service.IDeviceService;
import com.jingle.oa.device.service.IDevicerepairService;
import com.jingle.oa.device.service.IDevicetypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;


/**
 * 设备维修表 前端控制器
 *
 * @author mowen
 * @since 2020-02-03
 */
@RestController
@RequestMapping("/device/devicerepair")
public class DevicerepairController extends BaseController {

    @Autowired
    private IDevicerepairService iDevicerepairService;
    @Autowired
    private IDevicetypeService iDevicetypeService;
    @Autowired
    private IDeviceService iDeviceService;
@Autowired
    private JwtTokenUtil jwtTokenUtil;
    /**
     * 获取设备维修表列表
     *
     * @return
     */
    @RequiresPermissions("00050020")
    @GetMapping("/list")
    public Object list(String code,String status,String damager,String repairer,Page page) {
        // 列表内容
        QueryWrapper<Devicerepair> queryWrapper = new QueryWrapper<Devicerepair>().orderByDesc("crtm");
        if (!CommonUtil.isBlank(code)) {
            queryWrapper.like("code",code);
        }
        if (!CommonUtil.isBlank(status)) {
            queryWrapper.eq("drp.status",status);
        }
        if (!CommonUtil.isBlank(damager)) {
            queryWrapper.like("u.name",damager);
        }
        if (!CommonUtil.isBlank(repairer)) {
            queryWrapper.like("u2.name",repairer);
        }
        IPage iPage = iDevicerepairService.pageVo(page, queryWrapper);
        return responseSuccess(iPage);
    }

    /**
     * 转跳到新增页面
     *
     * @return
     */
    @RequiresPermissions("000500200000")
    @GetMapping("/toInsert")
    public Object toInsert() {
        QueryWrapper<Devicetype> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("id","name");
        return responseSuccess(iDevicetypeService.list(queryWrapper));
    }

    /**
     * 获取相关类型的设备
     *
     * @param dtid 类型的id
     * @return
     */
    @GetMapping("/getTypeDevice/{dtid}")
    public Object getTypeDevice(@PathVariable("dtid") String dtid) {
        QueryWrapper<Device> queryWrapper=new QueryWrapper<>();
        //状态 1-入库、2-出库中、3-出库、4-领用、5-报修
        queryWrapper.select("id","code").eq("dtid",dtid).eq("status",1);
        return responseSuccess(iDeviceService.list(queryWrapper));
    }

    /**
     * 新增
     *
     * @param devicerepair 设备维修表对象
     * @return
     */
    @RequiresPermissions("000500200000")
    @PostMapping("/insert")
    public Object insert(Devicerepair devicerepair) {
        //设置报修人
        devicerepair.setDamager(jwtTokenUtil.getUserId(getToken()));
        //设置报修时间
        devicerepair.setDamagedate(CommonUtil.date6());
        //设置状态 状态 1-报修、2-维修
        devicerepair.setStatus(new BigDecimal("1"));
        if (iDevicerepairService.saveDevicerepair(devicerepair)) {
            return responseSuccess();
        } else {
            return responseFail();
        }

    }

    /**
     * 接收维修
     *
     * @param devicerepair 更新的设备维修表对象
     * @return
     */
    @RequiresPermissions("000500200005")
    @PutMapping("/maintain")
    public Object maintain(Devicerepair devicerepair) {
        //设置维修人
        devicerepair.setRepairer(jwtTokenUtil.getUserId(getToken()));
        //设置维修时间
        devicerepair.setRepairdate(CommonUtil.date6());
        if (iDevicerepairService.updateById(devicerepair)) {
            return responseSuccess();
        } else {
            return responseFail();
        }
    }



    /**
     * 维修完成
     *
     * @param id 更新的设备维修表id
     * @return
     */
    @RequiresPermissions("000500200005")
    @PutMapping("/complete")
    public Object complete(String id,String did) {

        if (iDevicerepairService.updateComplete(id,did)) {
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
    @RequiresPermissions("000500200010")
    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable("id") String[] id) {
        if (iDevicerepairService.removeByIds(Arrays.asList(id))) {
            return responseSuccess();
        } else {
            return responseFail();
        }
    }


}

