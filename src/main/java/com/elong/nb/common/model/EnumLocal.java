package com.elong.nb.common.model;

/**
 * 语言
 */
public enum EnumLocal {
	/**
	 * 中文
	 */
	zh_CN,
	/**
	 * 英文
	 */
	en_US;

	public int getValue() {
		return this.ordinal();
	}

	public static EnumLocal forValue(int value) {
		return values()[value];
	}
}