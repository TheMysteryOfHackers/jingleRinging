package com.jingle.oa.device.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jingle.oa.device.entity.Devicepurchaserecords;

/**
 * <p>
 * 商品购买记录 服务类
 * </p>
 *
 * @author mowen
 * @since 2020-01-29
 */
public interface IDevicepurchaserecordsService extends IService<Devicepurchaserecords> {

	IPage pageVo(Page page, QueryWrapper<Devicepurchaserecords> queryWrapper);

}
