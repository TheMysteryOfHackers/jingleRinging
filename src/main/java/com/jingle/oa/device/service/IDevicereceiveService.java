package com.jingle.oa.device.service;

import com.jingle.oa.device.entity.Devicereceive;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 设备领用表 服务类
 * </p>
 *
 * @author mowen
 * @since 2020-02-03
 */
public interface IDevicereceiveService extends IService<Devicereceive> {

    boolean saveReceipt(String dtid,Devicereceive devicereceive);


    boolean updateRestoreById(Devicereceive devicereceive);
}
