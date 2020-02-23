package com.jingle.oa.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingle.oa.system.entity.Permission;
import com.jingle.oa.system.mapper.PermissionMapper;
import com.jingle.oa.system.service.IPermissionService;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author mowen
 * @since 2019-12-05
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

	@Override
	public List<String> listCodeByUid(String uid) {

		return baseMapper.selectCodeByUid(uid);
	}

	@Override
	public List<Permission> listByUid(String uid) {
		return baseMapper.selectByUid(uid);
	}

}
