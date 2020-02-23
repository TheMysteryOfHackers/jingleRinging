package com.jingle.oa.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingle.core.util.CommonUtil;
import com.jingle.oa.system.entity.RolePermission;
import com.jingle.oa.system.mapper.RolePermissionMapper;
import com.jingle.oa.system.service.IRolePermissionService;

/**
 * <p>
 * 岗位权限表 服务实现类
 * </p>
 *
 * @author mowen
 * @since 2019-12-06
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

	@Override
	public List<String> listPidByRid(String id) {

		return this.baseMapper.selectPidByRid(id);
	}

	@Override
	public boolean updatePermission(String rid, String[] pid) {
		// 删除所有角色权限
		QueryWrapper<RolePermission> wrapper = new QueryWrapper<RolePermission>().eq("rid", rid);
		baseMapper.delete(wrapper);
		if (!CommonUtil.isBlank(pid)) {
			List<RolePermission> rolePermissions = new ArrayList();
			for (String pidStr : pid) {
				RolePermission rolePermission = new RolePermission();
				rolePermission.setPid(pidStr);
				rolePermission.setRid(rid);
				// rolePermissionService.insert(rolePermission);
				rolePermissions.add(rolePermission);
			}
			return this.saveBatch(rolePermissions);
		} else {
			return true;

		}
	}

}
