package com.elong.nb.common.model;

/** 
预订渠道

*/
public enum EnumBookingChannel
{
	/** 
	 线上
	 
	*/
	OnLine(2),

	/** 
	 线下
	 
	*/
	OffLine(4),

	/** 
	 积分广场
	 
	*/
	Point(8),

	/** 
	 手机
	 
	*/
	Mobile(16),

	/** 
	 SEM特殊渠道
	 
	*/
	SEM(32),

	/** 
	 API分销渠道
	 
	*/
	API(64);

	private int intValue;
	private static java.util.HashMap<Integer, EnumBookingChannel> mappings;
	private synchronized static java.util.HashMap<Integer, EnumBookingChannel> getMappings()
	{
		if (mappings == null)
		{
			mappings = new java.util.HashMap<Integer, EnumBookingChannel>();
		}
		return mappings;
	}

	private EnumBookingChannel(int value)
	{
		intValue = value;
		EnumBookingChannel.getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static EnumBookingChannel forValue(int value)
	{
		if(getMappings().containsKey(value)){
			return getMappings().get(value);
		}else{
			return OnLine;
		}
	}
}
