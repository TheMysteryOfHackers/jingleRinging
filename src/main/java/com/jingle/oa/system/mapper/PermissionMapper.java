package com.jingle.oa.system.mapper;

import com.jingle.oa.system.entity.Permission;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author mowen
 * @since 2019-12-05
 */
public interface PermissionMapper extends BaseMapper<Permission> {

	List<String> selectCodeByUid(String uid);

	List<Permission> selectByUid(String uid);

}
