package com.jingle.oa.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingle.core.util.CommonUtil;
import com.jingle.oa.device.entity.Device;
import com.jingle.oa.device.entity.Devicescrap;
import com.jingle.oa.device.mapper.DevicescrapMapper;
import com.jingle.oa.device.service.IDeviceService;
import com.jingle.oa.device.service.IDevicescrapService;
import com.jingle.oa.device.vo.DevicescrapVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备报废 服务实现类
 * </p>
 *
 * @author mowen
 * @since 2020-02-01
 */
@Service
public class DevicescrapServiceImpl extends ServiceImpl<DevicescrapMapper, Devicescrap> implements IDevicescrapService {
    @Autowired
    private IDeviceService iDeviceService;

    @Override
    public boolean saveScrap(Devicescrap devicescrap) {
        UpdateWrapper<Device> updateWrapper = new UpdateWrapper<>();
        //状态 1-入库、2-出库中、3-出库、4-领用、5-报修
        updateWrapper.set("status", "2");
        updateWrapper.eq("id", devicescrap.getDid());
        //更改设备的状态
        if (iDeviceService.update(updateWrapper)) {
            //新增报废申请
            return this.save(devicescrap);
        }
        return false;
    }

    @Override
    public DevicescrapVo getVoById(String id) {
        return baseMapper.selectVoById(id);
    }

    @Override
    public boolean approvalById(Devicescrap devicescrap) {
        UpdateWrapper<Device> updateWrapper=new UpdateWrapper<>();

        //状态 1-申请、2-准许、3-拒绝
        if (CommonUtil.isEquals(devicescrap.getStatus(),2)) {
            //状态 1-入库、2-出库中、3-出库、4-领用、5-报修
            updateWrapper.set("status",3);
        }

        if (CommonUtil.isEquals(devicescrap.getStatus(),3)) {
            //状态 1-入库、2-出库中、3-出库、4-领用、5-报修
            updateWrapper.set("status",1);
        }
        updateWrapper.eq("id",devicescrap.getDid());
        if ( iDeviceService.update(updateWrapper)) {
            return this.updateById(devicescrap)  ;
        }

        return false;
    }

    @Override
    public IPage pageVo(Page page, QueryWrapper queryWrapper) {
        return baseMapper.selectPageVo(page,queryWrapper);
    }


}
