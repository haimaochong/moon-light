package com.light.moon.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.light.moon.entity.PlatformInfoEntity;
import com.light.moon.service.PlatformInfoService;
import com.light.moon.vo.ResultVO;

@Controller
@RequestMapping("/apply")
public class ApplyController {
	
	@Resource
	private PlatformInfoService PlatformInfoService;

    @RequestMapping
    public String index(ModelMap model, Long platformInfoId) {
    	PlatformInfoEntity platformInfoEntity = PlatformInfoService.findOne(platformInfoId);
    	if(null != platformInfoEntity) {
    		model.put("platformInfo", platformInfoEntity);
    	} else {
    		model.put("errorMsg", "平台不存在，请刷新重试");
    	}
        return "apply/apply";
    }
    
    @RequestMapping(value = "/queryPlatformInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO queryPlatformInfo(Long platformInfoId) {

		PlatformInfoEntity formInfoEntity = PlatformInfoService.findOne(platformInfoId);
		if (null == formInfoEntity) {
			return ResultVO.err("平台不存在，请刷新重试！");
		}

		return ResultVO.suc(formInfoEntity);
	}

}
