package com.jingle.oa.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingle.core.util.CommonUtil;
import com.jingle.oa.system.entity.UserRole;
import com.jingle.oa.system.mapper.UserRoleMapper;
import com.jingle.oa.system.service.IUserRoleService;

/**
 * <p>
 * 职工-部门表 服务实现类
 * </p>
 *
 * @author mowen
 * @since 2019-12-06
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

	@Override
	public boolean updateRole(String id, String[] rid) {
		// 删除该用户岗位
		QueryWrapper<UserRole> wrapper = new QueryWrapper<UserRole>().eq("uid", id);
		this.baseMapper.delete(wrapper);
		if (!CommonUtil.isBlank(rid)) {
			List<UserRole> userRoles = new ArrayList();
			for (String ridStr : rid) {
				UserRole userRole = new UserRole();
				userRole.setUid(id);
				userRole.setRid(ridStr);
				userRoles.add(userRole);
			}
			return this.saveBatch(userRoles);
		} else {
			return true;
		}

	}

}
