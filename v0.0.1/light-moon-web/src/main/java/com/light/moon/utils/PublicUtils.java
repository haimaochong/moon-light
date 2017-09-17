package com.light.moon.utils;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 常用工具
 * 
 * @author lihh
 * 
 */
public class PublicUtils {

	private static Logger logger = LoggerFactory.getLogger(PublicUtils.class);

	private PublicUtils() {

	}

	public static Object CopyBeanToBean(Object src, Object dest) {
		if (src == null) {
			return dest;
		}
		
		try {
			Method[] method1 = src.getClass().getMethods();
			
			Method[] method2 = dest.getClass().getMethods();
			for (int i = 0; i < method1.length; i++) {
				String methodName1 = method1[i].getName();
				String methodFix1 = methodName1.substring(methodName1.startsWith("is") ? 2 : 3, methodName1.length());
				if ((methodName1.startsWith("get")) || (methodName1.startsWith("is"))) {
					for (int j = 0; j < method2.length; j++) {
						String methodName2 = method2[j].getName();
						String methodFix2 = methodName2.substring(3, methodName2.length());
						if ((methodName2.startsWith("set")) && (methodFix2.equals(methodFix1))) {
							Object[] objs1 = new Object[0];
							Object[] objs2 = new Object[1];
							objs2[0] = method1[i].invoke(src, objs1);
							method2[j].invoke(dest, objs2);
						}
					}
				}
			}
		}catch (Exception e) {
			logger.error("数据转换发生异常！", e);
		}
		
		return dest;
	}

}
