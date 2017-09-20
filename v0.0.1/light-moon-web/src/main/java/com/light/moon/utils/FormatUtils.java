package com.light.moon.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * 格式化常用工具
 * 
 * @author lihh
 * 
 */
public class FormatUtils {

	private FormatUtils() {
	}

	public static String formatMoney(BigDecimal money, int maximumFractionDigits) {
		if(null == money) {
			money = BigDecimal.ZERO;
		}
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMaximumFractionDigits(maximumFractionDigits);
		String numberString = numberFormat.format(money);
		return numberString;
	}

	public static String formatMoney(BigDecimal money) {
		return formatMoney(money, 2);
	}

}
