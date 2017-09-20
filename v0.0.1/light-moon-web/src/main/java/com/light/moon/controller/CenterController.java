package com.light.moon.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.light.moon.context.ThreadLocalInfo;
import com.light.moon.context.UserContext;
import com.light.moon.dto.UserDto;
import com.light.moon.dto.UserInfoDto;
import com.light.moon.entity.OrderEntity;
import com.light.moon.entity.UserInfoEntity;
import com.light.moon.exception.ServiceException;
import com.light.moon.message.Sms;
import com.light.moon.searcher.DynamicSpecifications;
import com.light.moon.searcher.WebSearchFilter;
import com.light.moon.service.OrderService;
import com.light.moon.service.UserInfoService;
import com.light.moon.utils.GridUtils;
import com.light.moon.utils.PublicUtils;
import com.light.moon.vo.ResultVO;
import com.light.moon.vo.UserInfoVo;

@Controller
@RequestMapping("/center")
public class CenterController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private OrderService investService;

	@Resource
	private Sms sms;

	@RequestMapping
	public String index(ModelMap model, HttpServletRequest request) {
		String page = request.getParameter("page");
		if (StringUtils.isNotBlank(page)) {
			model.addAttribute("page", page);
		}
		return "center/center";
	}

	@RequestMapping(value = "/queryUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO queryUserInfo() {

		UserDto userDto = ThreadLocalInfo.getInstance().getUser();
		if (null == userDto) {
			return ResultVO.err("未登录用户！");
		}

		UserInfoEntity userInfoEntity = userInfoService.findOne(userDto.getUserId());
		if (null == userInfoEntity) {
			return ResultVO.err("用户不存在！");
		}

		UserInfoVo userInfoVo = new UserInfoVo();
		PublicUtils.CopyBeanToBean(userInfoEntity, userInfoVo);

		return ResultVO.suc(userInfoVo);
	}

	@RequestMapping(value = "/queryOrderList", method = RequestMethod.POST)
	@ResponseBody
	public String queryOrderList(Integer pageIndex) {

		UserDto userDto = ThreadLocalInfo.getInstance().getUser();
		if (null == userDto) {
			return null;
		}

		Pageable pageable = GridUtils.buildPageable(pageIndex, 15,
				new Sort(Direction.ASC, "status").and(new Sort(Direction.DESC, "id")));
		Specification<OrderEntity> filter = DynamicSpecifications.bySearchFilter(OrderEntity.class,
				toFilter(userDto.getUserId()));

		Page<OrderEntity> page = investService.findAll(filter, pageable);

		return GridUtils.toJson(page);
	}

	private List<WebSearchFilter> toFilter(Long userId) {
		List<WebSearchFilter> filters = Lists.newArrayList();
		GridUtils.addSearchFilterNotNull(filters, "user.id", WebSearchFilter.Operator.EQ, userId);
		return filters;
	}

	@RequestMapping(value = "/saveNormalInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO saveNormalInfo(UserInfoDto userInfo) {
		UserDto userDto = ThreadLocalInfo.getInstance().getUser();
		if (null == userDto) {
			return ResultVO.err("登录信息超时");
		}

		if (null == userInfo) {
			return ResultVO.err("参数错误，请刷新重新输入");
		}

		try {
			userInfoService.saveNormalInfo(userDto.getUserId(), userInfo);
			return ResultVO.ok();
		} catch (ServiceException e) {
			return ResultVO.err(e.getMessage());
		} catch (Exception e) {
			return ResultVO.err("系统发生异常");
		}
	}

	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO updatePassword(String newPwd, String validCode) {
		UserDto userDto = ThreadLocalInfo.getInstance().getUser();
		if (null == userDto) {
			return ResultVO.err("登录信息超时");
		}

		if (StringUtils.isBlank(newPwd) || null == validCode) {
			return ResultVO.err("参数错误，请刷新重新输入");
		}

		try {
			sms.validResetPwdAuthCode(Long.valueOf(userDto.getLoginName()), validCode);
			userInfoService.resetPwd(userDto.getUserId(), newPwd);

			UserContext.getInstance().removeLoginUser(ThreadLocalInfo.getInstance().getSessionId());
			return ResultVO.ok();
		} catch (ServiceException e) {
			return ResultVO.err(e.getMessage());
		} catch (Exception e) {
			logger.error("重置密码短信发送失败！", e);
			return ResultVO.err("系统发生异常！");
		}

	}
	
	@RequestMapping(value = "/saveAccountInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO saveAccountInfo(UserInfoDto userInfo) {
		UserDto userDto = ThreadLocalInfo.getInstance().getUser();
		if (null == userDto) {
			return ResultVO.err("登录信息超时");
		}

		if (null == userInfo) {
			return ResultVO.err("参数错误，请刷新重新输入");
		}

		try {
			userInfoService.saveAccountInfo(userDto.getUserId(), userInfo);
			return ResultVO.ok();
		} catch (ServiceException e) {
			return ResultVO.err(e.getMessage());
		} catch (Exception e) {
			return ResultVO.err("系统发生异常");
		}
	}

}
