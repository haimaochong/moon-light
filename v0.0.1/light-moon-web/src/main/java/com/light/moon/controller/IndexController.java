package com.light.moon.controller;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.light.moon.entity.PlatFormInfoEntity;
import com.light.moon.enumCode.ReturnMoneyType;
import com.light.moon.service.PlatformInfoService;
import com.light.moon.utils.GridUtils;

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
		return "/index";
	}

	@RequestMapping(value = "/queryPlatformList", method = RequestMethod.POST)
	@ResponseBody
	public String queryPlatformList() {
		Pageable pageable = GridUtils.buildPageable(0, 20, new Sort(Direction.DESC, "id"));
		Page<PlatFormInfoEntity> page = platformInfoService.findAll(pageable);

		return GridUtils.toJson(page);
	}

}
