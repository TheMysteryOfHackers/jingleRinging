package com.jingle.oa.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingle.oa.device.entity.Device;
import com.jingle.oa.device.mapper.DeviceMapper;
import com.jingle.oa.device.service.IDeviceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 设备明细 服务实现类
 * </p>
 *
 * @author mowen
 * @since 2019-12-11
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

	@Override
	public IPage pageVo(IPage page, QueryWrapper<Device> queryWrapper) {
		// TODO Auto-generated method stub
		return baseMapper.selectPageVo(page, queryWrapper);
	}



	@Override
	public List listVo(QueryWrapper queryWrapper) {
		return baseMapper.selectVo(queryWrapper);
	}

}
