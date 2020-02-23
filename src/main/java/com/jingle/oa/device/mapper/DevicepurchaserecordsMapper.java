package com.jingle.oa.device.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingle.oa.device.entity.Devicepurchaserecords;

/**
 * <p>
 * 商品购买记录 Mapper 接口
 * </p>
 *
 * @author mowen
 * @since 2020-01-29
 */
public interface DevicepurchaserecordsMapper extends BaseMapper<Devicepurchaserecords> {

	IPage selectPageVo(Page page, @Param(Constants.WRAPPER) QueryWrapper<Devicepurchaserecords> queryWrapper);

}
