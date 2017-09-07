package com.light.moon.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.light.moon.context.ThreadLocalInfo;
import com.light.moon.dto.UserDto;
import com.light.moon.exception.ServiceException;
import com.light.moon.service.BatchApplyService;
import com.light.moon.vo.ResultVO;

@Controller
@RequestMapping("/file")
public class FileController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String applyModelUrl = "d://test/applyModel.xls";

	@Resource
	private BatchApplyService batchApplyService;

	@RequestMapping(value = "/downloadApplyModel", method = RequestMethod.GET)
	public void downloadApplyModel(HttpServletResponse response) {
		File file = new File(applyModelUrl);
		if (!file.exists() || !file.isFile()) {
			logger.error("订单提交模板不存在！");
			return;
		}

		batchApplyService.download(response, file);
	}

	@RequestMapping(value = "/queryUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO uploadApplyBook(@RequestParam MultipartFile file) {
		UserDto user = ThreadLocalInfo.getInstance().getUser();
		if (null != user) {
			return ResultVO.err("未登录用户不允许该操作！");
		}
		
		if(null == file) {
			return ResultVO.err("文件异常！");
		}

		try {
			batchApplyService.dealBatchApplyFile(user, file);
			return ResultVO.ok();
		} catch (ServiceException e) {
			logger.error("处理批量提交订单发生异常！", e);
			return ResultVO.err(e.getMessage());
		} catch (Exception e) {
			logger.error("处理批量提交订单发生异常！", e);
			return ResultVO.err("系统异常！");
		}
	}

}
