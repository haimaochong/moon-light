package com.light.moon.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.light.moon.context.ThreadLocalInfo;
import com.light.moon.context.UserContext;
import com.light.moon.dto.RegistUserDto;
import com.light.moon.entity.UserInfoEntity;
import com.light.moon.service.UserInfoService;
import com.light.moon.vo.ResultVO;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserInfoService userInfoService;

	private UserContext userContext = UserContext.getInstance();

	private ThreadLocalInfo threadLocalInfo = ThreadLocalInfo.getInstance();

	@RequestMapping(value = "regist", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO regist(RegistUserDto user) {
		ResultVO validResult = validRegist(user);
		if (validResult.getCode() != 0) {
			return validResult;
		}

		String errorMsg = userInfoService.registUser(user);
		if (StringUtils.isBlank(errorMsg)) {
			return ResultVO.ok();
		} else {
			return ResultVO.err(errorMsg);
		}
	}

	private ResultVO validRegist(RegistUserDto user) {
		if (StringUtils.isBlank(user.getLoginName())) {
			return ResultVO.err("手机号码不可为空");
		}

		if (StringUtils.isBlank(user.getPassword())) {
			return ResultVO.err("登录密码不可为空");
		}

		if (!user.getPassword().equals(user.getConfirmPassword())) {
			return ResultVO.err("两次密码不一致，请重新输入");
		}

		return ResultVO.ok();
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO login(String loginName, String password) {
		UserInfoEntity user = userInfoService.queryUser(loginName, password);

		if (null != user) {
			userInfoService.login(user);
			return ResultVO.ok();
		} else {
			return ResultVO.err("用户名或密码错误，请重新输入");
		}
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO logout() {
		userContext.removeLoginUser(threadLocalInfo.getSessionId());
		return ResultVO.ok();
	}

	@RequestMapping(value = "checkLogin", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO checkLogin() {
		Boolean isLogin = userContext.checkLogin(threadLocalInfo.getSessionId());
		return ResultVO.suc(isLogin);
	}

}
