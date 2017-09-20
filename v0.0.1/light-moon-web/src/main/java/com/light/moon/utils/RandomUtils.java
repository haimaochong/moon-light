package com.light.moon.utils;

import java.util.Random;

/**
 * 随机数常用工具
 * 
 * @author lihh
 * 
 */
public class RandomUtils {

	private static final String[] numeral = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	private static final String[] letter = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
			"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	private RandomUtils() {
	}

	public static String getRandomNum(int digit) {
		if (digit < 1) {
			return "";
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < digit; i++) {
			int index = new Random().nextInt(10);
			result.append(numeral[index]);
		}
		return result.toString();
	}

	public static String getRandomId(int digit) {
		if (digit < 1) {
			return "";
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < digit; i++) {
			int index = new Random().nextInt(62);
			if (index < 10) {
				result.append(numeral[index]);
			} else {
				result.append(letter[index - 10]);
			}
		}
		return result.toString();
	}

}
