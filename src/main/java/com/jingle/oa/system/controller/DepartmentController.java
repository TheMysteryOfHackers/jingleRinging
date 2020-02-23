package com.jingle.oa.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jingle.core.base.controller.BaseController;
import com.jingle.core.util.CommonUtil;
import com.jingle.oa.system.entity.Department;
import com.jingle.oa.system.service.IDepartmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author mowen
 * @since 2019-12-05
 */
@RestController
@RequestMapping("/system/department")
public class DepartmentController extends BaseController {
	@Autowired
	private IDepartmentService iDepartmentService;

	/**
	 * 获得部门列表
	 * @return
	 */
	@GetMapping("/list")
	@RequiresPermissions("00000005")
	public Object list() {
		QueryWrapper<Department> wrapper = new QueryWrapper<Department>().orderByAsc("level", "sort");
		return responseSuccess(iDepartmentService.list(wrapper));
	}

	/**
	 * 新增
	 * @param department 部门对象
	 * @return
	 */
	@RequiresPermissions(value = { "000000050000" })
	@PostMapping("/insert")
	public Object insert(Department department) {

		QueryWrapper<Department> wrapper = new QueryWrapper<Department>().eq("name", department.getName());
		if (!CommonUtil.isBlank(iDepartmentService.list(wrapper))) {
			return responseFail("名字已存在。");
		}

		if (CommonUtil.isEquals(department.getLevel(), 1)) {
			department.setPid("0");
		}
		if (iDepartmentService.save(department)) {
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
	@RequiresPermissions(value = { "000000050005" })
	@GetMapping("/toUpdate/{id}")
	public Object toUpdate(@PathVariable("id") String id) {
		return responseSuccess(iDepartmentService.getById(id));
	}

	/**
	 * 更新
	 * @param department 更新的部门对象
	 * @return
	 */
	@RequiresPermissions(value = { "000000050005" })
	@PutMapping("/update")
	public Object update(Department department) {
		if (iDepartmentService.updateById(department)) {
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
	@RequiresPermissions(value = { "000000050010" })
	@DeleteMapping("/delete/{id}")
	public Object delete(@PathVariable("id") String[] id) {
		if (iDepartmentService.removeByIds(Arrays.asList(id))) {
			return responseSuccess();
		} else {
			return responseFail();
		}
	}
}
