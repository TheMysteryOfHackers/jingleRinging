package com.jingle.oa.device.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingle.oa.device.entity.Devicescrap;
import com.jingle.oa.device.vo.DevicescrapVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 设备报废 Mapper 接口
 * </p>
 *
 * @author mowen
 * @since 2020-02-01
 */
public interface DevicescrapMapper extends BaseMapper<Devicescrap> {

    DevicescrapVo selectVoById(String id);

    IPage selectPageVo(Page page, @Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}
