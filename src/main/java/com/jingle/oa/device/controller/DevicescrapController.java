package com.jingle.oa.device.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingle.core.base.controller.BaseController;
import com.jingle.core.util.CommonUtil;
import com.jingle.core.util.JwtTokenUtil;
import com.jingle.oa.device.entity.Device;
import com.jingle.oa.device.entity.Devicescrap;
import com.jingle.oa.device.service.IDeviceService;
import com.jingle.oa.device.service.IDevicescrapService;
import com.jingle.oa.device.vo.DeviceVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 设备报废控制器
 * </p>
 *
 * @author mowen
 * @since 2019-12-05
 */
@RestController
@RequestMapping("/device/devicescrap")
public class DevicescrapController extends BaseController {
    @Autowired
    private IDevicescrapService iDevicescrapService;

    @Autowired
    private IDeviceService iDeviceService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 获得设备报废列表
     *
     * @param brand
     * @param scraperdate
     * @param status
     * @param page
     * @return
     */
    @RequiresPermissions("00050010")
    @GetMapping("/list")
    public Object list( String brand,String scraperdate,String status, Page page) {
        // 列表内容
        QueryWrapper<Devicescrap> queryWrapper = new QueryWrapper<Devicescrap>().orderByDesc("crtm");
        if (!CommonUtil.isBlank(brand)) {
            queryWrapper.like("brand",brand);
        }
        if (!CommonUtil.isBlank(scraperdate)) {
            queryWrapper.like("scraperdate",scraperdate);
        }
        if (!CommonUtil.isBlank(status)) {
            queryWrapper.eq("status",status);
        }
        IPage iPage = iDevicescrapService.pageVo(page, queryWrapper);
        return responseSuccess(iPage);
    }

    /**
     * 转跳到新增
     *
     * @return
     */
    @RequiresPermissions("000500100000")
    @GetMapping("/toInsert")
    public Object toInsert() {
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        //查找处于入库状态的设备
        queryWrapper.eq("d.status", "1");
        List<DeviceVo> deviceList = iDeviceService.listVo(queryWrapper);
        return responseSuccess(deviceList);
    }

    /**
     * 新增
     *
     * @param devicescrap 设备报废对象
     * @return
     */
    @RequiresPermissions("000500100000")
    @PostMapping("/insert")
    public Object insert(Devicescrap devicescrap) {
        QueryWrapper<Devicescrap> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("did", devicescrap.getDid());
        List<Devicescrap> devicescrapList = iDevicescrapService.list(queryWrapper);
        if (!CommonUtil.isBlank(devicescrapList)) {
            return responseFail("重复申请");
        }
        //设置报废人
        devicescrap.setScraper(jwtTokenUtil.getUserId(getToken()));
        //设置报废时间
        devicescrap.setScraperdate(CommonUtil.date6());
        //状态 1-申请、2-准许、3-拒绝
        devicescrap.setStatus(new BigDecimal(1));

        if (iDevicescrapService.saveScrap(devicescrap)) {
            return responseSuccess();
        } else {
            return responseFail();
        }
    }

    /**
     * 转跳到审批
     *
     * @param id 编辑的id
     * @return
     */
    @RequiresPermissions("000500100005")
    @GetMapping("/toApproval/{id}")
    public Object toApproval(@PathVariable("id") String id) {
        return responseSuccess(iDevicescrapService.getVoById(id));
    }

    /**
     * 审批
     *
     * @param devicescrap 设备报废对象
     * @return
     */
    @RequiresPermissions("000500100005")
    @PutMapping("/approval")
    public Object approval(Devicescrap devicescrap) {
        //设置审批时间
        devicescrap.setApprovaldate(CommonUtil.date6());
        //设置审批人
        devicescrap.setApprover(jwtTokenUtil.getUserId(getToken()));
        if (iDevicescrapService.approvalById(devicescrap)) {
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
    @RequiresPermissions("000500100010")
    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable("id") String[] id) {
        if (iDevicescrapService.removeByIds(Arrays.asList(id))) {
            return responseSuccess();
        } else {
            return responseFail();
        }
    }
}
