package com.jingle.oa.device.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingle.oa.device.entity.Devicescrap;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jingle.oa.device.vo.DevicescrapVo;

/**
 * <p>
 * 设备报废 服务类
 * </p>
 *
 * @author mowen
 * @since 2020-02-01
 */
public interface IDevicescrapService extends IService<Devicescrap> {

    boolean saveScrap(Devicescrap devicescrap);

    DevicescrapVo getVoById(String id);

    boolean approvalById(Devicescrap devicescrap);

    IPage pageVo(Page page, QueryWrapper queryWrapper);
}
