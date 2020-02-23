package com.jingle.oa.device.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingle.oa.device.entity.Devicerepair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 设备维修表 Mapper 接口
 * </p>
 *
 * @author mowen
 * @since 2020-02-03
 */
public interface DevicerepairMapper extends BaseMapper<Devicerepair> {

    IPage selectPageVo(Page page,@Param(Constants.WRAPPER) QueryWrapper<Devicerepair> queryWrapper);
}
