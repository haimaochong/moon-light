package com.light.moon.controller;

import java.util.List;

import javax.annotation.Resource;

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
import com.light.moon.dto.QueryPlatformParams;
import com.light.moon.entity.PlatFormInfoEntity;
import com.light.moon.enumCode.ReturnMoneyType;
import com.light.moon.searcher.DynamicSpecifications;
import com.light.moon.searcher.WebSearchFilter;
import com.light.moon.service.PlatformInfoService;
import com.light.moon.utils.GridUtils;
import com.light.moon.vo.ResultVO;

@Controller
@RequestMapping("/index")
public class IndexController {

	@Resource
	private PlatformInfoService platformInfoService;

	@RequestMapping
	public String index(ModelMap model) {
		Iterable<PlatFormInfoEntity> iterator = platformInfoService.findAll(new Sort(Direction.DESC, "id"));

		model.put("platformList", Lists.newLinkedList(iterator));
		model.put("returnMoneyType", ReturnMoneyType.values());
		return "index/index";
	}

	@RequestMapping(value = "/queryPlatformList", method = RequestMethod.POST)
	@ResponseBody
	public String queryPlatformList(QueryPlatformParams params) {

		Pageable pageable = GridUtils.buildPageable(params.getPageIndex(), params.getPageSize(),
				new Sort(Direction.DESC, params.getOrderBy()));
		Specification<PlatFormInfoEntity> filter = DynamicSpecifications.bySearchFilter(PlatFormInfoEntity.class, toFilter(params));
		
		Page<PlatFormInfoEntity> page = platformInfoService.findAll(filter, pageable);

		return GridUtils.toJson(page);
	}
	
	private List<WebSearchFilter> toFilter(QueryPlatformParams params) {
		List<WebSearchFilter> filters = Lists.newArrayList();
		
		GridUtils.addSearchFilterNotNull(filters, "id", WebSearchFilter.Operator.EQ, params.getPlatformId());
		GridUtils.addSearchFilterNotNull(filters, "isInvestCycle", WebSearchFilter.Operator.EQ, params.getIsInvestCycle());
		
		return filters;
	}

	@RequestMapping(value = "/queryPlatformInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO queryPlatformInfo(Long platformInfoId) {

		PlatFormInfoEntity formInfoEntity = platformInfoService.findOne(platformInfoId);
		if (null == formInfoEntity) {
			return ResultVO.err("平台不存在，请刷新重试！");
		}

		return ResultVO.suc(formInfoEntity);
	}

}
