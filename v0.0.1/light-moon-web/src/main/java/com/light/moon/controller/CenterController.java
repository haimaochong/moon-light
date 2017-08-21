package com.light.moon.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.light.moon.dto.UserDto;
import com.light.moon.entity.InvestEntity;
import com.light.moon.entity.UserInfoEntity;
import com.light.moon.searcher.DynamicSpecifications;
import com.light.moon.searcher.WebSearchFilter;
import com.light.moon.service.InvestService;
import com.light.moon.service.UserInfoService;
import com.light.moon.utils.GridUtils;
import com.light.moon.utils.ThreadLocalInfo;
import com.light.moon.vo.ResultVO;

@Controller
@RequestMapping("/center")
public class CenterController {

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private InvestService investService;

	@RequestMapping
	public String index() {
		return "center/index";
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

		return ResultVO.suc(userInfoEntity);
	}

	@RequestMapping(value = "/queryInvestList", method = RequestMethod.POST)
	@ResponseBody
	public String queryInvestList(Integer pageIndex, Integer pageSize) {

		UserDto userDto = ThreadLocalInfo.getInstance().getUser();
		if (null == userDto) {
			return null;
		}

		Pageable pageable = GridUtils.buildPageable(pageIndex, pageSize, new Sort(Direction.DESC, "id"));
		Specification<InvestEntity> filter = DynamicSpecifications.bySearchFilter(InvestEntity.class,
				toFilter(userDto.getUserId()));

		Page<InvestEntity> page = investService.findAll(filter, pageable);

		return GridUtils.toJson(page);
	}

	private List<WebSearchFilter> toFilter(Long userId) {
		List<WebSearchFilter> filters = Lists.newArrayList();
		GridUtils.addSearchFilterNotNull(filters, "user.id", WebSearchFilter.Operator.EQ, userId);
		return filters;
	}

}
