package com.jingle.oa.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingle.core.util.CommonUtil;
import com.jingle.oa.device.entity.Device;
import com.jingle.oa.device.entity.Devicerepair;
import com.jingle.oa.device.mapper.DevicerepairMapper;
import com.jingle.oa.device.service.IDeviceService;
import com.jingle.oa.device.service.IDevicerepairService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备维修表 服务实现类
 * </p>
 *
 * @author mowen
 * @since 2020-02-03
 */
@Service
public class DevicerepairServiceImpl extends ServiceImpl<DevicerepairMapper, Devicerepair> implements IDevicerepairService {
    @Autowired
    private IDeviceService iDeviceService;

    @Override
    public boolean saveDevicerepair(Devicerepair devicerepair) {
        UpdateWrapper<Device> updateWrapper = new UpdateWrapper<>();
        //状态 1-入库、2-出库中、3-出库、4-领用、5-报修
        updateWrapper.set("status", 5).eq("id", devicerepair.getDid());
        if (iDeviceService.update(updateWrapper)) {
            return this.save(devicerepair);
        }
        return false;
    }

    @Override
    public IPage pageVo(Page page, QueryWrapper<Devicerepair> queryWrapper) {
        return baseMapper.selectPageVo(page ,queryWrapper);
    }

    @Override
    public boolean updateComplete(String id,String did) {

        if (!CommonUtil.isBlank(did)) {
            //更改维修好的设备状态
            UpdateWrapper<Device> deviceUpdateWrapper = new UpdateWrapper<>();
            //状态 1-入库、2-出库中、3-出库、4-领用、5-报修
            deviceUpdateWrapper.set("status",1).eq("id",did);
            if ( iDeviceService.update(deviceUpdateWrapper)) {
                UpdateWrapper<Devicerepair> devicerepairUpdateWrapper = new UpdateWrapper<>();
                //状态 1-报修、2-维修、3-维修完成
                devicerepairUpdateWrapper.set("status",3).eq("id",id);
                return this.update(devicerepairUpdateWrapper);
            }
        }
        return false;
    }


}
