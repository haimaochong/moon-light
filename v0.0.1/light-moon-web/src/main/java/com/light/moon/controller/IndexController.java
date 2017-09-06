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
import com.light.moon.entity.NoticeEntity;
import com.light.moon.entity.PlatformInfoEntity;
import com.light.moon.searcher.DynamicSpecifications;
import com.light.moon.searcher.WebSearchFilter;
import com.light.moon.service.NoticeService;
import com.light.moon.service.PlatformInfoService;
import com.light.moon.utils.GridUtils;

@Controller
@RequestMapping(value = { "/", "/index" })
public class IndexController {

	@Resource
	private PlatformInfoService platformInfoService;

	@Resource
	private NoticeService noticeService;

	@RequestMapping
	public String index(ModelMap model) {
		List<NoticeEntity> noticeList = noticeService.queryNewNotice();
		model.put("noticeList", noticeList);
		return "index/index";
	}

	@RequestMapping(value = "/queryPlatformList", method = RequestMethod.POST)
	@ResponseBody
	public String queryPlatformList(String platformName, Integer pageIndex) {

		Pageable pageable = GridUtils.buildPageable(pageIndex, 15,
				new Sort(Direction.DESC, "investNum").and(new Sort(Direction.ASC, "id")));
		Specification<PlatformInfoEntity> filter = DynamicSpecifications.bySearchFilter(PlatformInfoEntity.class,
				toFilter(platformName));

		Page<PlatformInfoEntity> page = platformInfoService.findAll(filter, pageable);

		return GridUtils.toJson(page);
	}

	private List<WebSearchFilter> toFilter(String platformName) {
		List<WebSearchFilter> filters = Lists.newArrayList();
		GridUtils.addSearchFilterNotNull(filters, "name", WebSearchFilter.Operator.LIKE, platformName);
		return filters;
	}

}
