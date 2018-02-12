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
		private static java.util.HashMap<Integer, EnumSellChannel> mappings = new java.util.HashMap<Integer, EnumSellChannel>();
		static {
			mappings.put(2, EnumSellChannel.A);
			mappings.put(4, EnumSellChannel.B);
			mappings.put(8, EnumSellChannel.C);
			mappings.put(16, EnumSellChannel.D);
			mappings.put(32, EnumSellChannel.E);
			mappings.put(64, EnumSellChannel.F);
			mappings.put(128, EnumSellChannel.G);
			mappings.put(256, EnumSellChannel.H);
			mappings.put(512, EnumSellChannel.I);
			mappings.put(1024, EnumSellChannel.J);
			mappings.put(2048, EnumSellChannel.K);
			mappings.put(4096, EnumSellChannel.L);
			mappings.put(8192, EnumSellChannel.M);
			mappings.put(16384, EnumSellChannel.N);
			mappings.put(32768, EnumSellChannel.O);
		}

		private EnumSellChannel(int value)
		{
			intValue = value;
		}

		public int getValue()
		{
			return intValue;
		}

		public static EnumSellChannel forValue(int value)
		{
			if(mappings.containsKey(value)){
				return mappings.get(value);
			}else{
				return A;
			}
		}
	}
//#endif