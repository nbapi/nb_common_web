package com.elong.nb.common.model;

/** 
会员级别

*/
public enum EnumMemberLevel
{
	/** 
	 普通会员
	*/
	Normal(1),
	/** 
	 VIP会员
	*/
	VIP(2),
	/** 
	 龙萃会员
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
