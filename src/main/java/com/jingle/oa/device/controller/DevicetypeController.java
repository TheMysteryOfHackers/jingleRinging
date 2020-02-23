package com.jingle.oa.device.controller;

import java.util.Arrays;

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
import com.jingle.oa.device.entity.Devicetype;
import com.jingle.oa.device.service.IDevicetypeService;

/**
 * <p>
 * 设备类型控制器
 * </p>
 *
 * @author mowen
 * @since 2019-12-05
 */
@RestController
@RequestMapping("/device/devicetype")
public class DevicetypeController extends BaseController {
	@Autowired
	private IDevicetypeService iDevicetypeService;

	/**
	 * 获得设备类型列表
	 * @param name
	 * @param brand
	 * @param page
	 * @return
	 */
	@GetMapping("/list")
	@RequiresPermissions("00050000")
	public Object list(String name, String brand, Page page) {
		// 列表内容
		QueryWrapper<Devicetype> queryWrapper = new QueryWrapper<Devicetype>().orderByDesc("crtm");
		if (!CommonUtil.isBlank(name)) {
			queryWrapper.like("name", name);
		}
		if (!CommonUtil.isBlank(brand)) {
			queryWrapper.like("brand", brand);
		}
		IPage iPage = iDevicetypeService.page(page, queryWrapper);
		return responseSuccess(iPage);
	}

	/**
	 * 新增
	 * @param devicetype 设备类型对象
	 * @return
	 */
	@RequiresPermissions(value = { "000500000000" })
	@PostMapping("/insert")
	public Object insert(Devicetype devicetype) {

		QueryWrapper<Devicetype> wrapper = new QueryWrapper<Devicetype>().eq("name", devicetype.getName());
		if (!CommonUtil.isBlank(iDevicetypeService.list(wrapper))) {
			return responseFail("名字已存在。");
		}

		if (iDevicetypeService.save(devicetype)) {
			return responseSuccess();
		} else {
			return responseFail();
		}
	}

	/**
	 * 转跳到更新
	 * @param id 编辑的id
	 * @return
	 */
	@RequiresPermissions(value = { "000500000005" })
	@GetMapping("/toUpdate/{id}")
	public Object toUpdate(@PathVariable("id") String id) {
		return responseSuccess(iDevicetypeService.getById(id));
	}

	/**
	 * 更新
	 * @param devicetype 设备类型对象
	 * @return
	 */
	@RequiresPermissions(value = { "000500000005" })
	@PutMapping("/update")
	public Object update(Devicetype devicetype) {
		if (iDevicetypeService.updateById(devicetype)) {
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
	@RequiresPermissions(value = { "000500000010" })
	@DeleteMapping("/delete/{id}")
	public Object delete(@PathVariable("id") String[] id) {
		if (iDevicetypeService.removeByIds(Arrays.asList(id))) {
			return responseSuccess();
		} else {
			return responseFail();
		}
	}
}
