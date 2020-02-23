package com.jingle.oa.device.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jingle.oa.device.entity.Device;

import java.util.List;

/**
 * <p>
 * 设备明细 服务类
 * </p>
 *
 * @author mowen
 * @since 2019-12-11
 */
public interface IDeviceService extends IService<Device> {

	IPage pageVo(IPage page, QueryWrapper<Device> queryWrapper);

    List listVo(QueryWrapper<Device> queryWrapper);
}
