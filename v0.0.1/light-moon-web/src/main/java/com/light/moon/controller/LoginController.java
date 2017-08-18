package com.light.moon.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.light.moon.dto.UserDto;
import com.light.moon.service.UserInfoService;
import com.light.moon.utils.ThreadLocalInfo;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Resource
	private UserInfoService userInfoService;

	@RequestMapping
	public String index(ModelMap model, String refererUri) {
		UserDto user = ThreadLocalInfo.getInstance().getUser();
		if(null == user) {
			model.addAttribute("errorMsg", "用户名或密码错误，请重新输入！");
		} else {
			model.addAttribute("refererUri", refererUri);
		}
		return "/login";
	}

}
