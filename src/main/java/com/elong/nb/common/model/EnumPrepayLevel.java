package com.elong.nb.common.model;

/**
 * 佣金过滤－预付等级
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2016年12月20日 下午1:28:40   suht     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		suht  
 * @version		1.0  
 * @since		JDK1.7
 */
public enum EnumPrepayLevel {

	/** 
	 * 低
	 *
	 * EnumAgencyLevel EnumAgencyLevel.java LOW
	 */
	LOW(3),

	/** 
	 * 中
	 *
	 * EnumAgencyLevel EnumAgencyLevel.java MIDDLE
	 */
	MIDDLE(2),

	/** 
	 * 高
	 *
	 * EnumAgencyLevel EnumAgencyLevel.java HIGH
	 */
	HIGH(1),

	/** 
	 * 不限
	 *
	 * EnumAgencyLevel EnumAgencyLevel.java NOLIMIT
	 */
	NOLIMIT(0);

	/** 
	 * 枚举值
	 *
	 * int EnumAgencyLevel.java intValue
	 */
	private int intValue;

	/** 
	 * 存放枚举map
	 *
	 * java.util.HashMap<Integer,EnumAgencyLevel> EnumAgencyLevel.java mappings
	 */
	private static java.util.HashMap<Integer, EnumPrepayLevel> mappings = new java.util.HashMap<Integer, EnumPrepayLevel>();

	static{
		mappings.put(3, EnumPrepayLevel.LOW);
		mappings.put(2, EnumPrepayLevel.MIDDLE);
		mappings.put(1, EnumPrepayLevel.HIGH);
		mappings.put(0, EnumPrepayLevel.NOLIMIT);
	}

	/**   
	 * 构造函数  
	 *   
	 * @param value   
	 */
	private EnumPrepayLevel(int value) {
		intValue = value;
	}

	/** 
	 * 获取枚举值
	 *
	 * @return
	 */
	public int getValue() {
		return intValue;
	}

	/** 
	 * 获取枚举
	 *
	 * @param value
	 * @return
	 */
	public static EnumPrepayLevel forValue(int value) {
		return mappings.get(value);
	}

}
