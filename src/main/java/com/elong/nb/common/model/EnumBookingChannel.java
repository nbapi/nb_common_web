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
	private static java.util.HashMap<Integer, EnumBookingChannel> mappings = new java.util.HashMap<Integer, EnumBookingChannel>();
	static {
		mappings.put(2, EnumBookingChannel.OnLine);
		mappings.put(4, EnumBookingChannel.OffLine);
		mappings.put(8, EnumBookingChannel.Point);
		mappings.put(16, EnumBookingChannel.Mobile);
		mappings.put(32, EnumBookingChannel.SEM);
		mappings.put(64, EnumBookingChannel.API);
	}

	private EnumBookingChannel(int value)
	{
		intValue = value;
	}

	public int getValue()
	{
		return intValue;
	}

	public static EnumBookingChannel forValue(int value)
	{
		if(mappings.containsKey(value)){
			return mappings.get(value);
		}else{
			return OnLine;
		}
	}
}
