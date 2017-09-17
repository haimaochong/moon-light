package com.light.moon.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * 导出服务接口
 * 
 * @author lihh
 * 
 */
public interface ExportService {

	public HSSFWorkbook createUserOrderExcel(Long userId);
	
}
