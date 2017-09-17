package com.light.base.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/file")
public class FileController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${applyModelUrl}")
	private String applyModelUrl;

	@RequestMapping(value = "/downloadApplyModel", method = RequestMethod.POST)
	public void downloadApplyModel(HttpServletResponse response) {
		File file = new File(applyModelUrl);
		if (!file.exists() || !file.isFile()) {
			logger.error("订单提交模板不存在！");
			return;
		}

	}

}
