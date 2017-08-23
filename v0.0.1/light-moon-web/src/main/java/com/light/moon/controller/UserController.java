package com.light.moon.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.light.moon.context.ThreadLocalInfo;
import com.light.moon.context.UserContext;
import com.light.moon.dto.UserDto;
import com.light.moon.entity.UserInfoEntity;
import com.light.moon.enumCode.OperatorType;
import com.light.moon.service.OperatorLogService;
import com.light.moon.service.UserInfoService;
import com.light.moon.vo.ResultVO;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private OperatorLogService operatorLogService;

	private UserContext userContext = UserContext.getInstance();

	private ThreadLocalInfo threadLocalInfo = ThreadLocalInfo.getInstance();

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO login(String loginName, String password) {
		UserInfoEntity user = userInfoService.queryUser(loginName, password);

		if (null != user) {
			UserDto userDto = new UserDto();
			userDto.setUserId(user.getId());
			userDto.setLoginName(user.getLoginName());
			userDto.setUserName(user.getUserName());
			userDto.setLastOperateTime(new Date());
			userContext.addLoginUser(threadLocalInfo.getSessionId(), userDto);

			operatorLogService.addOperatorLog(user.getLoginName(), OperatorType.USER_LOGIN, "用户登录");
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
