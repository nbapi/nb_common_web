package com.elong.nb.common.model;

public enum EnumOrderType
{
	OrderFrom(0),
	ProxyId(1);

	private int intValue;
	private static java.util.HashMap<Integer, EnumOrderType> mappings = new java.util.HashMap<Integer, EnumOrderType>();
	static {
		mappings.put(0, EnumOrderType.OrderFrom);
		mappings.put(1, EnumOrderType.ProxyId);
	}

	private EnumOrderType(int value)
	{
		intValue = value;
	}

	public int getValue()
	{
		return intValue;
	}

	public static EnumOrderType forValue(int value)
	{
		return mappings.get(value);
	}
}
