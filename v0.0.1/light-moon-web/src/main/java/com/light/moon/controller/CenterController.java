package com.light.moon.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.light.moon.dto.UserDto;
import com.light.moon.entity.OrderEntity;
import com.light.moon.entity.UserInfoEntity;
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

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private OrderService investService;

	@RequestMapping
	public String index(ModelMap model, HttpServletRequest request) {
		String page = request.getParameter("page");
		if(StringUtils.isNotBlank(page)) {
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

		Pageable pageable = GridUtils.buildPageable(pageIndex, 15, new Sort(Direction.ASC, "status").and(new Sort(Direction.DESC, "id")));
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

}
