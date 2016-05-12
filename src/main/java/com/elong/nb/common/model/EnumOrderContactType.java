package com.elong.nb.common.model;

public enum EnumOrderContactType
{
	NoNeed(0),
	Customer(1),
	Proxy(2);

	private int intValue;
	private static java.util.HashMap<Integer, EnumOrderContactType> mappings;
	private synchronized static java.util.HashMap<Integer, EnumOrderContactType> getMappings()
	{
		if (mappings == null)
		{
			mappings = new java.util.HashMap<Integer, EnumOrderContactType>();
		}
		return mappings;
	}

	private EnumOrderContactType(int value)
	{
		intValue = value;
		EnumOrderContactType.getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static EnumOrderContactType forValue(int value)
	{
		return getMappings().get(value);
	}
}