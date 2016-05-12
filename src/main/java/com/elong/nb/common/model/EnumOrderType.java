package com.elong.nb.common.model;

public enum EnumOrderType
{
	OrderFrom(0),
	ProxyId(1);

	private int intValue;
	private static java.util.HashMap<Integer, EnumOrderType> mappings;
	private synchronized static java.util.HashMap<Integer, EnumOrderType> getMappings()
	{
		if (mappings == null)
		{
			mappings = new java.util.HashMap<Integer, EnumOrderType>();
		}
		return mappings;
	}

	private EnumOrderType(int value)
	{
		intValue = value;
		EnumOrderType.getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static EnumOrderType forValue(int value)
	{
		return getMappings().get(value);
	}
}
