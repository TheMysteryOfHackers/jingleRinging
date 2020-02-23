package com.jingle.oa.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jingle.core.base.controller.BaseController;
import com.jingle.core.util.CommonUtil;
import com.jingle.core.util.Constant;
import com.jingle.core.util.CryptoUtils;
import com.jingle.core.util.JwtTokenUtil;
import com.jingle.oa.system.entity.Permission;
import com.jingle.oa.system.entity.Token;
import com.jingle.oa.system.entity.User;
import com.jingle.oa.system.service.IPermissionService;
import com.jingle.oa.system.service.ITokenService;
import com.jingle.oa.system.service.IUserService;

/**
 * 主页控制器
 */
@RestController
public class IndexController extends BaseController {

	@Autowired
	private IUserService iUserService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private ITokenService iTokenService;
	@Autowired
	private IPermissionService iPermissionService;

	/**
	 * 登录
	 * @param name 名字
	 * @param pwd 密码
	 * @param session
	 * @return
	 */
	@PostMapping(value = "/login")
	public Object login(String name, String pwd, HttpSession session) {
		QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("name", name);
		User user = iUserService.getOne(wrapper);
		if (CommonUtil.isBlank(user)) {
			// 没有好到用户
			return responseFail("帐号不存在");
		}
		if (!CommonUtil.isEquals(user.getPwd(), CryptoUtils.encodeMD5(pwd))) {
			// 密码错误
			return responseFail("密码错误");
		}
		// 分配token
		Token token = new Token();
		token.setUid(user.getId());
		String tokenStr = jwtTokenUtil.generalToken(user.getId(), JSON.toJSONString(user));
		token.setToken(tokenStr);
		List<String> codes = iPermissionService.listCodeByUid(user.getId());
		token.setExpression(JSON.toJSONString(codes));
		iTokenService.saveNewToken(token);
		return responseSuccess(Constant.RESPONSE_MSG_SUCCESS, tokenStr);
	}

	/**
	 * 登出
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/logout")
	public Object logout(HttpServletRequest request) {
		// 注销，把该账号的token 从数据库删除
		String token = request.getHeader(Constant.REQUEST_TOKEN_HEADER);
		String uid = jwtTokenUtil.getUserId(token);
		QueryWrapper<Token> wrapper = new QueryWrapper<Token>().eq("uid", uid);
		iTokenService.remove(wrapper);
		return responseSuccess();
	}

	/**
	 * 更新密码
	 * @param id id
	 * @param pwd 新密码
	 * @return
	 */
	@PutMapping(value = "/updatePwd")
	public Object updatePwd(String id, String pwd) {
		User user = new User();
		user.setId(id);
		user.setPwd(CryptoUtils.encodeMD5(pwd));
		if (iUserService.updateById(user)) {
			return responseSuccess("修改密码成功。");
		} else {
			return responseFail();
		}
	}

	/**
	 * 获得信息
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/getInfo")
	public Object getInfo(HttpServletRequest request) {
		String token = request.getHeader(Constant.REQUEST_TOKEN_HEADER);
		String uid = jwtTokenUtil.getUserId(token);
		User user = iUserService.getById(uid);
		// 查询权限
		List<Permission> permissions = iPermissionService.listByUid(uid);
		Map data = new HashMap();
		data.put("user", user);
		data.put("permission", permissions);
		return responseSuccess(data);
	}

}
