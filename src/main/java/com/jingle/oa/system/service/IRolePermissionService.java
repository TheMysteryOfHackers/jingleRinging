package com.jingle.oa.system.service;

import com.jingle.oa.system.entity.RolePermission;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 岗位权限表 服务类
 * </p>
 *
 * @author mowen
 * @since 2019-12-06
 */
public interface IRolePermissionService extends IService<RolePermission> {

	List<String> listPidByRid(String id);

	boolean updatePermission(String rid, String[] pid);

	 

}
