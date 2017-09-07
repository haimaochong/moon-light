package com.light.moon.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * excel操作工具类
 * 
 * @author lihh
 *
 */
public class ExcelUtil {
	private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

	/**
	 * excel文件类型
	 * 
	 * @author lihh
	 *
	 */
	public enum ExcelType {
		XLS(0, ".xls文件格式"), XLSX(1, ".xlsx文件格式");
		private int code;
		private String name;

		private ExcelType(int code, String name) {
			this.code = code;
			this.name = name;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	/**
	 * 获取excel操作工作薄
	 * 
	 * @author lihh
	 * @param is
	 * @param type
	 *            指定excel类型读取,可为null
	 * @return
	 * @throws Exception
	 */
	public static Workbook workbookFactory(InputStream is, ExcelType type) throws Exception {
		Workbook workbook = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] bf = new byte[512];
		int index = -1;
		while ((index = is.read(bf)) != -1)
			output.write(bf, 0, index);
		output.close();
		byte[] outputByteData = output.toByteArray();
		if (type != null) {
			switch (type) {
			case XLS:
				workbook = new HSSFWorkbook(new ByteArrayInputStream(outputByteData));
				break;
			default:
				workbook = new XSSFWorkbook(new ByteArrayInputStream(outputByteData));
				break;
			}
		} else {
			try {
				workbook = new XSSFWorkbook(new ByteArrayInputStream(outputByteData));
			} catch (Exception ex) {
				workbook = new HSSFWorkbook(new ByteArrayInputStream(outputByteData));
			}
		}

		return workbook;
	}

	/**
	 * 根据输入流，文件格式读取数据
	 * 
	 * @author lihh
	 * @param is
	 * @param type
	 *            传null将匹配xls、xlsx两类格式文件
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<ArrayList<Object>> readAllRows(InputStream is, ExcelType type, boolean isCreateNullRow)
			throws Exception {
		Workbook wb = workbookFactory(is, type);
		ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();

		for (int i = 0; i < wb.getNumberOfSheets(); i++) {// 获取每个Sheet表
			Sheet sheet = wb.getSheetAt(i);
			rowList.addAll(readRows(sheet, isCreateNullRow));
		}

		return rowList;
	}

	/**
	 * 根据工作薄读取数据
	 * 
	 * @author lihh
	 * @param wb
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<ArrayList<Object>> readAllRowsByWorkbook(Workbook wb) throws Exception {
		ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {// 获取每个Sheet表
			Sheet sheet = wb.getSheetAt(i);
			rowList.addAll(readRows(sheet, true));
		}

		return rowList;
	}

	/**
	 * 读取sheet的所有数据行
	 * 
	 * @author lihh
	 * @param sheet
	 * @return
	 */
	public static ArrayList<ArrayList<Object>> readRows(Sheet sheet, boolean isCreateNullRow) {
		int endRowIndex = sheet.getLastRowNum();
		return readRows(sheet, 0, endRowIndex, isCreateNullRow);
	}

	/**
	 * 读取sheet
	 * 
	 * @author lihh
	 * @param sheet
	 * @param startRowIndex
	 *            开始下标
	 * @param endRowIndex
	 *            结束下标
	 * @return
	 */
	public static ArrayList<ArrayList<Object>> readRows(Sheet sheet, int startRowIndex, int endRowIndex,
			boolean isCreateNullRow) {
		ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
		int columnCnt = sheet.getRow(0).getLastCellNum();// 获取标题栏数

		for (int i = startRowIndex; i <= (startRowIndex + endRowIndex); i++) {
			Row row = sheet.getRow(i);
			if (row == null) {// 断层数据
				if (false == isCreateNullRow) {
					continue;
				}
				row = sheet.createRow(i);
			}

			ArrayList<Object> cellList = new ArrayList<Object>();
			for (int j = 0; j < columnCnt; j++) {
				cellList.add(readCell(row.getCell(j)));
			}
			rowList.add(cellList);
		}
		return rowList;
	}

	/**
	 * 获取必填项的下标
	 * 
	 * @author lihh
	 * @param headRow
	 *            标题栏
	 * @param requiredSymbol
	 *            必填项的匹配标记
	 * @return
	 */
	public static List<Integer> getRequiredColumnIndex(ArrayList<Object> headRow, String requiredSymbol) {
		List<Integer> requiredIndexs = Lists.newArrayList();
		requiredSymbol = requiredSymbol == null ? "*" : requiredSymbol;
		int size = headRow.size();
		for (int i = 0; i < size; i++) {
			String value = headRow.get(i).toString();
			if (StringUtils.isNotBlank(value) && value.indexOf(requiredSymbol) == 0) {
				requiredIndexs.add(i);
			}
		}
		return requiredIndexs;
	}

	/**
	 * 从Excel读Cell
	 *
	 * @param cell
	 * @return
	 */
	private static Object readCell(Cell cell) {
		if (cell == null) {
			return "";
		}

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			String str = cell.getRichStringCellValue().getString();
			return str == null ? "" : str.trim();

		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue();
			} else {
				Double value = cell.getNumericCellValue();
				if (null == value) {
					return "";
				}
				return subZeroAndDot(BigDecimal.valueOf(value).toPlainString());
			}

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		case Cell.CELL_TYPE_FORMULA:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue();
			} else {
				return cell.getCellFormula();
			}

		case Cell.CELL_TYPE_BLANK:
			return "";

		default:
			logger.error("读取excel单元格数据异常：" + cell.getCellType());
			return "";
		}

	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * 
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s) {
		if (s != null && s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}

}