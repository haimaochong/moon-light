package com.light.moon.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.light.moon.dto.UserDto;
import com.light.moon.service.BatchApplyService;
import com.light.moon.utils.ExcelUtil;
import com.light.moon.utils.ExcelUtil.ExcelType;

/**
 * 批量提交订单服务实现类
 * 
 * @author lihh
 * 
 */
@Service
public class BatchApplyServiceImpl implements BatchApplyService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void download(HttpServletResponse response, File file) {
		ServletOutputStream outputStream = null;
		BufferedInputStream br = null;

		try {
			response.reset();
			response.setContentType("application/binary;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-disposition",
					"attachment; filename=" + URLEncoder.encode("【安心返利】订单提交模板.xls", "UTF-8"));

			br = new BufferedInputStream(new FileInputStream(file));
			byte[] buf = new byte[1024];
			int len = 0;
			outputStream = response.getOutputStream();
			while ((len = br.read(buf)) > 0) {
				outputStream.write(buf, 0, len);
			}
			outputStream.flush();
		} catch (Exception e) {
			logger.error("模板下载发生异常", e);
		} finally {
			try {
				if (null != br) {
					br.close();
				}
				if (null != outputStream) {
					outputStream.close();
				}
			} catch (IOException e) {
				logger.error("IO流关闭发生异常", e);
			}
		}
	}
	
	@Override
	public void dealBatchApplyFile(UserDto user, MultipartFile file) {
		ArrayList<ArrayList<Object>> reader = null;
		InputStream is = null;
		int lineNum = 1;
		
		try{
			is = file.getInputStream();
			reader = ExcelUtil.readAllRows(is, ExcelType.XLS, false);
			
			// 跳过表头
			for(int index=1; index<reader.size(); index++) {
				ArrayList<Object> row = reader.get(index);
				if(row != null) {
					StringBuilder all = new StringBuilder();
					for(int i=0; i<row.size(); i++) {
						all.append(row.get(i));
					}
					if(StringUtils.isBlank(all)) {
						logger.info("第{}行为空行，过滤", lineNum++);
						continue;
					}
					
					for(int i=0; i<row.size(); i++) {
						// 校验并设置值
						buildApplyItem(null, row, i);
					}
					
					// add
				}
			}
			
			// save
			
		} catch (Exception e) {
			
		}
	}
	
	private void buildApplyItem(Object item, ArrayList<Object> rowData, int colIndex) {
		switch (colIndex) {
		case 0:
			break;
		default:
			break;
		}
	}
	
}
