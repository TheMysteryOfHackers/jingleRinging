package com.jingle.oa.device.service.impl;

import com.jingle.oa.device.entity.Devicetype;
import com.jingle.oa.device.mapper.DevicetypeMapper;
import com.jingle.oa.device.service.IDevicetypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 设备类型 服务实现类
 * </p>
 *
 * @author mowen
 * @since 2019-12-10
 */
@Service
public class DevicetypeServiceImpl extends ServiceImpl<DevicetypeMapper, Devicetype> implements IDevicetypeService {

    @Override
    public List<Devicetype> getHaveDeviceOfType() {
        return baseMapper.selectHaveDeviceOfType();
    }
}
