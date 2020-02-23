package com.jingle.oa.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingle.oa.device.entity.Devicetype;

import java.util.List;

/**
 * <p>
 * 设备类型 服务类
 * </p>
 *
 * @author mowen
 * @since 2019-12-10
 */
public interface IDevicetypeService extends IService<Devicetype> {

    List<Devicetype> getHaveDeviceOfType();
}
