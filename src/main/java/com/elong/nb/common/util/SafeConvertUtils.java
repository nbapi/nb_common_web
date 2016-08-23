/**   
 * @(#)SafeConvertUtils.java	2016年8月23日	下午3:55:18	   
 *     
 * Copyrights (C) 2016艺龙旅行网保留所有权利
 */
package com.elong.nb.common.util;

import java.math.BigDecimal;

/**
 * 系统类型安全转换处理类
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2016年8月23日 下午3:55:18   suht     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		suht  
 * @version		1.0  
 * @since		JDK1.7
 */
public class SafeConvertUtils {

	/** 
	 * 将价格向上转化为整数
	 *
	 * @param price
	 * @param convertType (0=不处理,1=Round,2=Ceil)
	 * @return
	 */
	public static double toIntegerPrice(Double price, int convertType) {
		switch (convertType) {
		case 0:
			;
			break;
		case 1:
			BigDecimal b = new BigDecimal(price);
			price = b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
			break;
		case 2:
			price = Math.ceil(price);
			break;
		default:
			break;
		}
		return price;
	}

}
