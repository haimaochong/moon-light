package com.light.moon.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;
import com.light.moon.context.ThreadLocalInfo;
import com.light.moon.dto.UserDto;
import com.light.moon.service.BatchApplyService;
import com.light.moon.service.ExportService;
import com.light.moon.vo.ResultVO;

@Controller
@RequestMapping("/file")
public class FileController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${applyModelUrl}")
	private String applyModelUrl;

	@Resource
	private BatchApplyService batchApplyService;

	@Resource
	private ExportService exportService;

	@RequestMapping(value = "/downloadApplyModel", method = RequestMethod.POST)
	public void downloadApplyModel(HttpServletResponse response) {
		File file = new File(applyModelUrl);
		if (!file.exists() || !file.isFile()) {
			logger.error("订单提交模板不存在！");
			return;
		}

		batchApplyService.download(response, file);
	}

	@RequestMapping(value = "/uploadApplyBook", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO uploadApplyBook(@RequestParam(value = "file", required = false) MultipartFile file) {
		UserDto user = ThreadLocalInfo.getInstance().getUser();
		if (null != user) {
			return ResultVO.err("未登录用户不允许该操作！");
		}

		if (null == file) {
			return ResultVO.err("文件异常！");
		}

		Map<String, String> result = Maps.newHashMap();
		result.put("fileName", file.getOriginalFilename());
		result.put("fileKey", "SDAWEDQWDAS23SDa23aSD.xls");

		return ResultVO.suc(result);
	}

	@RequestMapping(value = "/downloadOrderList", method = RequestMethod.POST)
	public void downloadOrderList(HttpServletResponse response) {
		UserDto user = ThreadLocalInfo.getInstance().getUser();
		if (null == user) {
			logger.error("用户未登录！");
			return;
		}

		OutputStream os = null;

		try {
			response.reset();
			response.setContentType("application/binary;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-disposition",
					"attachment; filename=" + URLEncoder.encode("【安心返利】交单记录.xls", "UTF-8"));

			os = response.getOutputStream();
			HSSFWorkbook workbook = exportService.createUserOrderExcel(user.getUserId());
			workbook.write(os);
			os.flush();
		} catch (IOException e) {
			logger.error("下载订单发生异常！", e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("IO关闭失败！", e);
				}
			}
		}

		return;
	}

}
