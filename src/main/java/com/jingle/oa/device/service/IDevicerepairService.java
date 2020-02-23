package com.jingle.oa.device.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingle.oa.device.entity.Devicerepair;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 设备维修表 服务类
 * </p>
 *
 * @author mowen
 * @since 2020-02-03
 */
public interface IDevicerepairService extends IService<Devicerepair> {

    boolean saveDevicerepair(Devicerepair devicerepair);

    IPage pageVo(Page page, QueryWrapper<Devicerepair> queryWrapper);


    boolean updateComplete(String id,String did);
}
