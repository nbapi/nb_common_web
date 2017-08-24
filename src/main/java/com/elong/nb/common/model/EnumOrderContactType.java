package com.elong.nb.common.model;

public enum EnumOrderContactType
{
	NoNeed(0),
	Customer(1),
	Proxy(2);

	private int intValue;
	private static java.util.HashMap<Integer, EnumOrderContactType> mappings = new java.util.HashMap<Integer, EnumOrderContactType>();
	static{
		mappings.put(0, EnumOrderContactType.NoNeed);
		mappings.put(1, EnumOrderContactType.Customer);
		mappings.put(2, EnumOrderContactType.Proxy);
	}

	private EnumOrderContactType(int value)
	{
		intValue = value;
	}

	public int getValue()
	{
		return intValue;
	}

	public static EnumOrderContactType forValue(int value)
	{
		return mappings.get(value);
	}
}