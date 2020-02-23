package com.jingle.oa.system.controller;

import java.util.ArrayList;
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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingle.core.base.controller.BaseController;
import com.jingle.core.util.CommonUtil;
import com.jingle.core.util.Constant;
import com.jingle.core.util.CryptoUtils;
import com.jingle.core.vo.ZtreeBean;
import com.jingle.oa.system.entity.Role;
import com.jingle.oa.system.entity.User;
import com.jingle.oa.system.service.IRoleService;
import com.jingle.oa.system.service.IUserRoleService;
import com.jingle.oa.system.service.IUserService;

/**
 * <p>
 * 员工表 前端控制器
 * </p>
 *
 * @author mowen
 * @since 2019-01-14
 */
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {
	@Autowired
	private IUserService iUserService;
	@Autowired
	private IRoleService iRoleService;
	@Autowired
	private IUserRoleService iUserRoleService;

	/**
	 * 获得用户列表
	 * @param rname 名字
	 * @param email 邮箱
	 * @param mobile 手机号
	 * @param page 分页对象
	 * @return
	 */
	@RequiresPermissions("00000000")
	@GetMapping("/list")
	public Object list(String rname, String email, String mobile, Page page) {
		// 列表内容
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>().orderByDesc("crtm");
		if (!CommonUtil.isBlank(rname)) {
			queryWrapper.like("rname", rname);
		}
		if (!CommonUtil.isBlank(email)) {
			queryWrapper.like("email", email);
		}
		if (!CommonUtil.isBlank(mobile)) {
			queryWrapper.like("mobile", mobile);
		}
		IPage iPage = iUserService.page(page, queryWrapper);
		return responseSuccess(iPage);
	}

	/**
	 * 新增
	 * @param User 用户对象
	 * @return
	 */
	@RequiresPermissions("000000000015")
	@PostMapping("/insert")
	public Object insert(User User) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("name", User.getName());
		if (!CommonUtil.isBlank(iUserService.getOne(queryWrapper))) {
			return responseFail("登陆名字已存在。。");
		}
		// 默认密码
		User.setPwd(CryptoUtils.encodeMD5(Constant.SYSTEM_DEFAUL_PWD));
		if (iUserService.save(User)) {
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
	@RequiresPermissions("000000000005")
	@GetMapping("/toUpdate/{id}")
	public Object toUpdate(@PathVariable("id") String id) {
		return responseSuccess(iUserService.getById(id));
	}

	/**
	 * 更新
	 * @param User 用户对象
	 * @return
	 */
	@RequiresPermissions("000000000005")
	@PutMapping("/update")
	public Object update(User User) {
		if (iUserService.updateById(User)) {
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
	@RequiresPermissions("000000000010")
	@DeleteMapping("/delete/{id}")
	public Object delete(@PathVariable("id") String[] id) {
		if (iUserService.removeByIds(Arrays.asList(id))) {
			return responseSuccess();
		} else {
			return responseFail();
		}
	}

	/**
	 * 转跳到更新角色
	 * @return
	 */
	@RequiresPermissions("000000000025")
	@GetMapping("/toUpdateRole")
	public Object toUpdateRole() {
		// 查找所有部门
		QueryWrapper<Role> wrapper = new QueryWrapper<Role>().orderByAsc("level", "sort");
		List<Role> roles = iRoleService.list(wrapper);
		List<ZtreeBean> ztreeBeans = new ArrayList();
		// 转换ztreeBean
		if (!CommonUtil.isBlank(roles)) {
			for (Role role : roles) {
				ZtreeBean ztreeBean = new ZtreeBean();
				ztreeBean.setId(role.getId());
				ztreeBean.setPId(role.getPid());
				ztreeBean.setName(role.getName());
				ztreeBean.setOpen(true);
				ztreeBeans.add(ztreeBean);
			}
		}
		return responseSuccess(ztreeBeans);
	}

	/**
	 * 更新角色
	 * 
	 * @param id
	 * @param rid
	 * @return
	 */
	@RequiresPermissions("000000000025")
	@PutMapping("/updateRole")
	public Object updateRole(String id, String[] rid) {
		if (iUserRoleService.updateRole(id, rid)) {
			return responseSuccess();
		} else {
			return responseFail();
		}
	}
}
