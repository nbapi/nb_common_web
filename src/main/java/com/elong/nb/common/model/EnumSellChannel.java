package com.elong.nb.common.model;

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
//#if !ForXSD
	/** 
	 分销渠道
	 
	*/
	public enum EnumSellChannel
	{
		A(2),
		B(4),
		C(8),
		D(16),
		E(32),
		F(64),
		G(128),
		H(256),
		I(512),
		J(1024),
		K(2048),
		L(4096),
		M(8192),
		N(16384),
		O(32768);

		private int intValue;
		private static java.util.HashMap<Integer, EnumSellChannel> mappings;
		private synchronized static java.util.HashMap<Integer, EnumSellChannel> getMappings()
		{
			if (mappings == null)
			{
				mappings = new java.util.HashMap<Integer, EnumSellChannel>();
			}
			return mappings;
		}

		private EnumSellChannel(int value)
		{
			intValue = value;
			EnumSellChannel.getMappings().put(value, this);
		}

		public int getValue()
		{
			return intValue;
		}

		public static EnumSellChannel forValue(int value)
		{
			return getMappings().get(value);
		}
	}
//#endif