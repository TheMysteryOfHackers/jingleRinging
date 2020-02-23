package com.jingle.oa.system.service;

import com.jingle.oa.system.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 职工-部门表 服务类
 * </p>
 *
 * @author mowen
 * @since 2019-12-06
 */
public interface IUserRoleService extends IService<UserRole> {

	boolean updateRole(String id, String[] rid);

}
