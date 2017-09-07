package com.light.moon.service;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.light.moon.dto.UserDto;

/**
 * 批量提交订单服务接口
 * 
 * @author lihh
 * 
 */
public interface BatchApplyService {

	public void download(HttpServletResponse response, File file);

	public void dealBatchApplyFile(UserDto user, MultipartFile file);

}
