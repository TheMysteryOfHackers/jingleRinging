package com.jingle.oa.system.mapper;

import com.jingle.oa.system.entity.RolePermission;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 岗位权限表 Mapper 接口
 * </p>
 *
 * @author mowen
 * @since 2019-12-06
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

	List<String> selectPidByRid(String id);

}
