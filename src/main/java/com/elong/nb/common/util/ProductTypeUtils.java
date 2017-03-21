package com.elong.nb.common.util;

import java.util.regex.Pattern;

public class ProductTypeUtils {
	public static final Pattern pattern = Pattern.compile("(\\d点)|(\\d:\\d)");// 匹配n点或n:n

	public static boolean isHourPayRoom(String name) {
		// 钟点、小时、半日房
		// \d点、8:0
		if (name.contains("小时") || name.contains("钟点") || name.contains("半日房")
				|| pattern.matcher(name).find()) {
			return true;
		} else {
			return false;
		}
	}
}
