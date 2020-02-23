package com.jingle.oa.system.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.jingle.core.vo.ZtreeBean;
import com.jingle.oa.system.entity.Department;
import com.jingle.oa.system.entity.Permission;
import com.jingle.oa.system.entity.Role;
import com.jingle.oa.system.service.IDepartmentService;
import com.jingle.oa.system.service.IPermissionService;
import com.jingle.oa.system.service.IRolePermissionService;
import com.jingle.oa.system.service.IRoleService;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author mowen
 * @since 2019-12-05
 */
@RestController
@RequestMapping("/system/role")
public class RoleController extends BaseController {
	@Autowired
	private IRoleService iRoleService;
	@Autowired
	private IDepartmentService iDepartmentService;
	@Autowired
	private IRolePermissionService iRolePermissionService;
	@Autowired
	private IPermissionService iPermissionService;

	/**
	 * 获取角色列表
	 * @return
	 */
	@RequiresPermissions("00000010")
	@GetMapping("/list")
	public Object list() {
		QueryWrapper<Role> wrapper = new QueryWrapper<Role>().orderByAsc("level", "sort");
		System.out.println(iRoleService.list(wrapper));
		return responseSuccess(iRoleService.list(wrapper));
	}

	/**
	 * 转跳到新增页面
	 * @return
	 */
	@RequiresPermissions("000000100000")
	@GetMapping("/toInsert")
	public Object toInsert() {
		// 查找所有部门
		QueryWrapper<Department> wrapper = new QueryWrapper<Department>().orderByAsc("level", "sort");
		List<Department> departments = iDepartmentService.list(wrapper);
		List<ZtreeBean> ztreeBeans = new ArrayList();
		// 转换ztreeBean
		if (!CommonUtil.isBlank(departments)) {
			for (Department department : departments) {
				ZtreeBean ztreeBean = new ZtreeBean();
				ztreeBean.setId(department.getId());
				ztreeBean.setPId(department.getPid());
				ztreeBean.setName(department.getName());
				ztreeBean.setOpen(true);
				ztreeBeans.add(ztreeBean);
			}
		}
		return responseSuccess(ztreeBeans);
	}

	/**
	 * 新增
	 * @param role 角色对象
	 * @return
	 */
	@RequiresPermissions("000000100000")
	@PostMapping("/insert")
	public Object insert(Role role) {

		QueryWrapper<Role> wrapper = new QueryWrapper<Role>().eq("name", role.getName());
		if (!CommonUtil.isBlank(iRoleService.list(wrapper))) {
			return responseFail("名字已存在。");
		}

		if (CommonUtil.isEquals(role.getLevel(), 1)) {
			role.setPid("0");
		}
		if (iRoleService.save(role)) {
			return responseSuccess();
		} else {
			return responseFail();
		}
	}

	/**
	 * 转跳到更新页面
	 * @param id 编辑的id
	 * @return
	 */
	@RequiresPermissions("000000100005")
	@GetMapping("/toUpdate/{id}")
	public Object toUpdate(@PathVariable("id") String id) {
		// 查找所有部门
		QueryWrapper<Department> wrapper = new QueryWrapper<Department>().orderByAsc("level", "sort");
		List<Department> departments = iDepartmentService.list(wrapper);
		List<ZtreeBean> ztreeBeans = new ArrayList();
		// 转换ztreeBean
		if (!CommonUtil.isBlank(departments)) {
			for (Department department : departments) {
				ZtreeBean ztreeBean = new ZtreeBean();
				ztreeBean.setId(department.getId());
				ztreeBean.setPId(department.getPid());
				ztreeBean.setName(department.getName());
				ztreeBean.setOpen(true);
				ztreeBeans.add(ztreeBean);
			}
		}
		Map map = new HashMap();
		Role post = iRoleService.getById(id);
		map.put("ztreeBeans", ztreeBeans);
		map.put("record", post);
		map.put("department", iDepartmentService.getById(post.getDid()));
		return responseSuccess(map);
	}

	/**
	 * 更新
	 * @param role 更新的角色对象
	 * @return
	 */
	@RequiresPermissions("000000100005")
	@PutMapping("/update")
	public Object update(Role role) {
		if (iRoleService.updateById(role)) {
			return responseSuccess();
		} else {
			return responseFail();
		}

	}

	/**
	 * 删除
	 * @param id 删除的id
	 * @return
	 */
	@RequiresPermissions("000000100015")
	@DeleteMapping("/delete/{id}")
	public Object delete(@PathVariable("id") String[] id) {
		if (iRoleService.removeByIds(Arrays.asList(id))) {
			return responseSuccess();
		} else {
			return responseFail();
		}
	}

	/**
	 * 转跳到更新权限
	 * @param id 编辑的id
	 * @return
	 */
	@RequiresPermissions("000000100010")
	@GetMapping("/toUpdatePermission/{id}")
	public Object toUpdatePermission(@PathVariable("id") String id) {
		// 查找该岗位的权限
		List<String> pids = iRolePermissionService.listPidByRid(id);
		QueryWrapper<Permission> wrapper = new QueryWrapper<Permission>().orderByAsc("level", "sort");
		List<Permission> permissions = iPermissionService.list(wrapper);
		// 把查询回来的实体转成ZtreeBean
		List<ZtreeBean> ztreeBeans = new ArrayList();
		if (!CommonUtil.isBlank(permissions)) {
			for (Permission p : permissions) {
				ZtreeBean ztreeBean = new ZtreeBean();
				ztreeBean.setId(p.getId() + "");
				// ztreeBean.setIsParent(CommonUtil.isBlank(p.getPid()));
				ztreeBean.setName(p.getName());
				ztreeBean.setPId(p.getPid());
				ztreeBean.setOpen(true);
				// 匹配该权限是否已经选中
				if (!CommonUtil.isBlank(pids)) {
					ztreeBean.setChecked(pids.contains(p.getId()));
				}
				ztreeBeans.add(ztreeBean);
			}
		}
		return responseSuccess(ztreeBeans);
	}

	/**
	 * 更新权限
	 * @param rid 角色id
	 * @param pid 权限id，可以是数组
	 * @return
	 */
	@RequiresPermissions("000000100010")
	@PutMapping("/updatePermission")
	public Object updatePermission(String rid, String[] pid) {
		iRolePermissionService.updatePermission(rid, pid);
		return responseSuccess();
	}
}
