package com.light.base.utils;

import java.io.File;

/**
 * 文件常用工具
 * 
 * @author lihh
 * 
 */
public class FileUtils {

	private FileUtils() {
	}

	public static String formatFileUri(String uri) {
		return replaceAll(replaceAll(uri, "\\", "/"), "/", File.separator);
	}

	private static String replaceAll(String str, String src, String dest) {
		if (!str.contains(src)) {
			return str;
		} else {
			str = str.replace(src, dest);
			return replaceAll(str, src, dest);
		}
	}

	public static String getFileFix(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	}

}
