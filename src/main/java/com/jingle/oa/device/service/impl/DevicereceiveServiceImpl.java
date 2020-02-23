package com.jingle.oa.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingle.core.util.CommonUtil;
import com.jingle.oa.device.entity.Device;
import com.jingle.oa.device.entity.Devicereceive;
import com.jingle.oa.device.mapper.DevicereceiveMapper;
import com.jingle.oa.device.service.IDeviceService;
import com.jingle.oa.device.service.IDevicereceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备领用表 服务实现类
 * </p>
 *
 * @author mowen
 * @since 2020-02-03
 */
@Service
public class DevicereceiveServiceImpl extends ServiceImpl<DevicereceiveMapper, Devicereceive> implements IDevicereceiveService {
    @Autowired
    private IDeviceService iDeviceService;

    public boolean saveReceipt(String dtid, Devicereceive devicereceive) {
        if (!CommonUtil.isBlank(dtid)) {
            QueryWrapper<Device> wrapper = new QueryWrapper<>();
            //查询设备表的id，设备类型等于用户选择的，选最早登记的设备，只需要一个
            wrapper.select("id").eq("dtid", dtid).eq("status", 1).orderByAsc("crtm").last("limit 1");
            Device device = iDeviceService.getOne(wrapper);
            devicereceive.setDid(device.getId());

            UpdateWrapper<Device> updateWrapper = new UpdateWrapper<>();
            //状态 1-入库、2-出库中、3-出库、4-领用、5-报修
            updateWrapper.set("status", 4).eq("id", device.getId());
            if (iDeviceService.update(updateWrapper)) {
                return this.save(devicereceive);
            }
        }

        return false;
    }



    @Override
    public boolean updateRestoreById(Devicereceive devicereceive) {
        UpdateWrapper<Device> updateWrapper = new UpdateWrapper<>();
        //状态 1-入库、2-出库中、3-出库、4-领用、5-报修
        updateWrapper.set("status", 1).eq("id", devicereceive.getDid());
        if (iDeviceService.update(updateWrapper)){
            return this.updateById(devicereceive);
        }
        return false;
    }
}
