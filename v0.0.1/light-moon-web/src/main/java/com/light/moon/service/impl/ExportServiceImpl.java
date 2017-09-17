package com.light.moon.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.stereotype.Service;

import com.light.moon.dao.OrderDao;
import com.light.moon.entity.OrderEntity;
import com.light.moon.service.ExportService;

/**
 * 导出服务实现类
 * 
 * @author lihh
 * 
 */
@Service
public class ExportServiceImpl implements ExportService {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Resource
	private OrderDao orderDao;

	@Override
	public HSSFWorkbook createUserOrderExcel(Long userId) {
		HSSFWorkbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("交单记录");
		// 冻结第一行
		sheet.createFreezePane(0, 1, 0, 1);

		// 创建字体，并设置字体样式
		Font headFont = wb.createFont();
		headFont.setFontName("宋体");
		headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// 创建单元格样式，并设置居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(headFont);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle style1 = wb.createCellStyle();
		style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle style2 = wb.createCellStyle();
		style2.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		int rowIndex = 0;
		int cellIndex = 0;

		Row row = sheet.createRow(rowIndex++);

		Cell cell = row.createCell(cellIndex++);
		sheet.setColumnWidth(cellIndex - 1, 20 * 256);
		cell.setCellStyle(style);
		cell.setCellValue("平台名称");

		cell = row.createCell(cellIndex++);
		sheet.setColumnWidth(cellIndex - 1, 14 * 256);
		cell.setCellStyle(style);
		cell.setCellValue("注册手机");

		cell = row.createCell(cellIndex++);
		sheet.setColumnWidth(cellIndex - 1, 14 * 256);
		cell.setCellStyle(style);
		cell.setCellValue("用户名");

		cell = row.createCell(cellIndex++);
		sheet.setColumnWidth(cellIndex - 1, 14 * 256);
		cell.setCellStyle(style);
		cell.setCellValue("投资金额");

		cell = row.createCell(cellIndex++);
		sheet.setColumnWidth(cellIndex - 1, 14 * 256);
		cell.setCellStyle(style);
		cell.setCellValue("投资期限");

		cell = row.createCell(cellIndex++);
		sheet.setColumnWidth(cellIndex - 1, 17 * 256);
		cell.setCellStyle(style);
		cell.setCellValue("投资日期");

		cell = row.createCell(cellIndex++);
		sheet.setColumnWidth(cellIndex - 1, 20 * 256);
		cell.setCellStyle(style);
		cell.setCellValue("交单时间");

		cell = row.createCell(cellIndex++);
		sheet.setColumnWidth(cellIndex - 1, 10 * 256);
		cell.setCellStyle(style);
		cell.setCellValue("状态");

		cell = row.createCell(cellIndex++);
		sheet.setColumnWidth(cellIndex - 1, 13 * 256);
		cell.setCellStyle(style);
		cell.setCellValue("收款类型");

		cell = row.createCell(cellIndex++);
		sheet.setColumnWidth(cellIndex - 1, 23 * 256);
		cell.setCellStyle(style);
		cell.setCellValue("收款账户");

		cell = row.createCell(cellIndex++);
		sheet.setColumnWidth(cellIndex - 1, 30 * 256);
		cell.setCellStyle(style);
		cell.setCellValue("备注");

		List<OrderEntity> orderList = orderDao.queryByUserId(userId);
		if (!CollectionUtils.isEmpty(orderList)) {
			for (OrderEntity order : orderList) {
				row = sheet.createRow(rowIndex++);
				cellIndex = 0;

				cell = row.createCell(cellIndex++);
				cell.setCellStyle(style1);
				cell.setCellValue(order.getPlatForm().getName());

				cell = row.createCell(cellIndex++);
				cell.setCellStyle(style1);
				cell.setCellValue(order.getApplyTelephone());

				cell = row.createCell(cellIndex++);
				cell.setCellStyle(style1);
				cell.setCellValue(order.getApplyUserName());

				cell = row.createCell(cellIndex++);
				cell.setCellStyle(style1);
				cell.setCellValue(order.getAmount().setScale(2).toString());

				cell = row.createCell(cellIndex++);
				cell.setCellStyle(style1);
				cell.setCellValue(order.getTimeRange());

				cell = row.createCell(cellIndex++);
				cell.setCellStyle(style1);
				cell.setCellValue(dateFormat.format(order.getInvestTime()));

				cell = row.createCell(cellIndex++);
				cell.setCellStyle(style1);
				cell.setCellValue(timeFormat.format(order.getCreateTime()));

				cell = row.createCell(cellIndex++);
				cell.setCellStyle(style1);
				cell.setCellValue(order.getStatus().getText());

				cell = row.createCell(cellIndex++);
				cell.setCellStyle(style1);
				cell.setCellValue(order.getPayType().getText());

				cell = row.createCell(cellIndex++);
				cell.setCellStyle(style1);
				cell.setCellValue(order.getAccount());

				cell = row.createCell(cellIndex++);
				cell.setCellStyle(style2);
				cell.setCellValue(order.getNote());
			}
		}

		return wb;
	}

}
