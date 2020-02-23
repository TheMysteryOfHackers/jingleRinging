package com.jingle.oa.device.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jingle.oa.device.entity.Devicetype;

import java.util.List;

/**
 * <p>
 * 设备类型 Mapper 接口
 * </p>
 *
 * @author mowen
 * @since 2019-12-10
 */
public interface DevicetypeMapper extends BaseMapper<Devicetype> {

    List<Devicetype> selectHaveDeviceOfType();
}
