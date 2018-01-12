package com.elong.nb.common.model;

public enum EnumOrderType
{
	OrderFrom(0),
	ProxyId(1),
	UniqueSearchByOrderFrom(2);

	private int intValue;
	private static java.util.HashMap<Integer, EnumOrderType> mappings = new java.util.HashMap<Integer, EnumOrderType>();
	static {
		mappings.put(0, EnumOrderType.OrderFrom);
		mappings.put(1, EnumOrderType.ProxyId);
		mappings.put(2, EnumOrderType.UniqueSearchByOrderFrom);
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
