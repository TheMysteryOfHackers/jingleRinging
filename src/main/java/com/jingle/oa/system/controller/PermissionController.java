package com.jingle.oa.system.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jingle.core.base.controller.BaseController;
import com.jingle.core.util.CommonUtil;
import com.jingle.oa.system.entity.Permission;
import com.jingle.oa.system.service.IPermissionService;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author mowen
 * @since 2019-12-05
 */
@RestController
@RequestMapping("/system/permission")
public class PermissionController extends BaseController {
	@Autowired
	private IPermissionService iPermissionService;

	/**
	 * 获取权限列表
	 * @return
	 */
	@RequiresPermissions("00000015")
	@GetMapping("/list")
	public Object list() {
		QueryWrapper<Permission> wrapper = new QueryWrapper<Permission>().orderByAsc("level", "sort");
		List<Permission> permissions = iPermissionService.list(wrapper);
		return responseSuccess(permissions);
	}

	/**
	 * 新增
	 * @param permission 权限对象
	 * @return
	 */
	@RequiresPermissions("000000150000")
	@PostMapping("/insert")
	public Object insert(Permission permission) {
		// 校验编码重复
		QueryWrapper<Permission> wrapper = new QueryWrapper<Permission>().eq("code", permission.getCode());
		List list = iPermissionService.list(wrapper);
		if (!CommonUtil.isBlank(list)) {
			return responseFail("编码重复");
		}
		if (CommonUtil.isEquals(permission.getLevel(), 1)) {
			permission.setPid("0");
		}
		iPermissionService.save(permission);
		return responseSuccess();
	}

	/**
	 * 转跳到更新页面
	 * @param id 编辑的id
	 * @return
	 */
	@RequiresPermissions("000000150005")
	@GetMapping("/toUpdate/{id}")
	public Object toUpdate(@PathVariable("id") String id) {
		return responseSuccess(iPermissionService.getById(id));
	}

	/**
	 * 更新
	 * @param permission 更新的权限对象
	 * @return
	 */
	@RequiresPermissions("000000150005")
	@PutMapping("/update")
	public Object update(Permission permission) {
		iPermissionService.updateById(permission);
		return responseSuccess();
	}

	/**
	 * 删除
	 * @param id 删除的id
	 * @return
	 */
	@RequiresPermissions("000000150010")
	@DeleteMapping("/delete/{id}")
	public Object delete(@PathVariable("id") String[] id) {
		iPermissionService.removeByIds(Arrays.asList(id));
		return responseSuccess();
	}
}
