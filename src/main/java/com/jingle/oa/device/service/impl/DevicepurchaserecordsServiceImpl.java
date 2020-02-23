package com.jingle.oa.device.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingle.oa.device.entity.Devicepurchaserecords;
import com.jingle.oa.device.mapper.DevicepurchaserecordsMapper;
import com.jingle.oa.device.service.IDevicepurchaserecordsService;

/**
 * <p>
 * 商品购买记录 服务实现类
 * </p>
 *
 * @author mowen
 * @since 2020-01-29
 */
@Service
public class DevicepurchaserecordsServiceImpl extends ServiceImpl<DevicepurchaserecordsMapper, Devicepurchaserecords> implements IDevicepurchaserecordsService {

	@Override
	public IPage pageVo(Page page, QueryWrapper<Devicepurchaserecords> queryWrapper) {
		// TODO Auto-generated method stub
		return baseMapper.selectPageVo(page, queryWrapper);
	}

}
