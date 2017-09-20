package com.light.moon.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.light.moon.context.CacheContext;
import com.light.moon.entity.UserInfoEntity;
import com.light.moon.exception.ServiceException;
import com.light.moon.message.Sms;
import com.light.moon.service.UserInfoService;
import com.light.moon.vo.ResultVO;

@Controller
@RequestMapping(value = { "/message" })
public class MessageController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final int TIME_RANGE = 10 * 60; // 校验最大发送量的时间范围

	private static final int TIME_RANGE_MAX_NUM = 5; // 校验最大发送量的次数范围

	@Resource
	private Sms sms;
	
	@Resource
	private UserInfoService userInfoService;

	@RequestMapping(value = "/sendValidCode", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO queryPlatformList(HttpServletRequest request, Long phoneNum, String type) {
		String remoteAddr = request.getRemoteAddr();
		try {
			if (!CacheContext.validQueueCacheable(remoteAddr, TIME_RANGE, TIME_RANGE_MAX_NUM)) {
				return ResultVO.err("操作过于频繁，请稍后再试");
			}

			if (!"regist".equals(type) && !"updatePwd".equals(type)) {
				return ResultVO.err("参数错误，请刷新重试");
			}

			if ("regist".equals(type)) {
				UserInfoEntity oldUser = userInfoService.queryUser(phoneNum.toString());
				if(null != oldUser) {
					return ResultVO.err("该手机号码已注册");
				}
				sms.sendRegistAuthCode(phoneNum);
			} else if ("updatePwd".equals(type)) {
				sms.sendResetPwdAuthCode(phoneNum);
			}

			CacheContext.addQueueKey(remoteAddr, TIME_RANGE, TIME_RANGE_MAX_NUM);
		} catch (ServiceException e) {
			logger.error("发送短信失败！", e);
			return ResultVO.err("短信发送失败！");
		}

		return ResultVO.ok();
	}

}
