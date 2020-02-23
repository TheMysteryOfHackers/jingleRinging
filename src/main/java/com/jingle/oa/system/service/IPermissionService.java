package com.jingle.oa.system.service;

import com.jingle.oa.system.entity.Permission;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author mowen
 * @since 2019-12-05
 */
public interface IPermissionService extends IService<Permission> {

	List<String> listCodeByUid(String uid);

	List<Permission> listByUid(String uid);

}
