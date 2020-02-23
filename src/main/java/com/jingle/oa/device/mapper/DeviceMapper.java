package com.jingle.oa.device.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.jingle.oa.device.entity.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 设备明细 Mapper 接口
 * </p>
 *
 * @author mowen
 * @since 2019-12-11
 */
public interface DeviceMapper extends BaseMapper<Device> {


    IPage selectPageVo(IPage page, @Param(Constants.WRAPPER) QueryWrapper<Device> queryWrapper);

    List selectVo(@Param(Constants.WRAPPER) QueryWrapper<Device> queryWrapper);
}
