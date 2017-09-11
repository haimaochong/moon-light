package com.light.moon.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.light.moon.entity.ImageEntity;
import com.light.moon.service.ImageService;
import com.light.moon.utils.FileUtils;

@Controller
@RequestMapping("/image")
public class ImageController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${fileBaseDir}")
	private String fileBaseDir;

	@Value("${noPicUri}")
	private String noPicUri;

	@Resource
	private ImageService imageService;

	@RequestMapping(value = "/read/{imageId}")
	public void index(@PathVariable Long imageId, HttpServletResponse response) {
		try {
			if (null == imageId) {
				logger.error("imageId不可为空！");
				readNoPic(response);
				return;
			}

			ImageEntity imageEntity = imageService.findOne(imageId);
			if (null == imageEntity) {
				logger.error("imageId对应的图片在数据库找不到！imageId：" + imageId);
				readNoPic(response);
				return;
			}

			String fileUri = FileUtils.formatFileUri(fileBaseDir + imageEntity.getUri());
			File file = new File(fileUri);
			if (!file.exists() || !file.isFile()) {
				logger.error("图片找不到或者不是文件，图片路径：" + fileUri);
				readNoPic(response);
				return;
			}

			BufferedImage bi = ImageIO.read(file);
			ImageIO.write(bi, FileUtils.getFileFix(file.getName()), response.getOutputStream());
		} catch (IOException e) {
			logger.error("读取图片发生错误！", e);
			try {
				readNoPic(response);
			} catch (IOException e1) {
			}
		}
	}

	private void readNoPic(HttpServletResponse response) throws IOException {
		String fileUri = FileUtils.formatFileUri(fileBaseDir + noPicUri);
		File file = new File(fileUri);
		BufferedImage bi = ImageIO.read(file);
		ImageIO.write(bi, "gif", response.getOutputStream());
	}

}
