package com.light.moon.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.light.base.vo.ResultVO;
import com.light.moon.entity.PlatformInfoEntity;
import com.light.moon.service.PlatformInfoService;

@Controller
@RequestMapping("/apply")
public class ApplyController {
	
	@Resource
	private PlatformInfoService platformInfoService;

    @RequestMapping(value = "/queryPlatformInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO queryPlatformInfo(Long platformInfoId) {

		PlatformInfoEntity formInfoEntity = platformInfoService.findOne(platformInfoId);
		if (null == formInfoEntity) {
			return ResultVO.err("平台不存在，请刷新重试！");
		}

		return ResultVO.suc(formInfoEntity);
	}

}
