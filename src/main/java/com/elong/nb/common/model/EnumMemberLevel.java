package com.elong.nb.common.model;

/** 
会员级别 1普通会员  2贵宾  3龙翠  4钻石龙翠

*/
public enum EnumMemberLevel
{
	/** 
	 普通会员
	*/
	Normal(1),
	/** 
	 贵宾
	*/
	VIP(2),
	/** 
	 龙萃会员
	*/
	LONGCUI(3),
	/** 
	 钻石龙翠
	*/
	SVIP(4);
	private int intValue;
	private static java.util.HashMap<Integer, EnumMemberLevel> mappings;
	private synchronized static java.util.HashMap<Integer, EnumMemberLevel> getMappings()
	{
		if (mappings == null)
		{
			mappings = new java.util.HashMap<Integer, EnumMemberLevel>();
		}
		return mappings;
	}

	private EnumMemberLevel(int value)
	{
		intValue = value;
		EnumMemberLevel.getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static EnumMemberLevel forValue(int value)
	{
		return getMappings().get(value);
	}
}
