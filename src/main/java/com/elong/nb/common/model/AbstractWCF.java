/**   
 * @(#)AbstractWCF.java	2017年8月10日	下午7:26:32	   
 *     
 * Copyrights (C) 2017艺龙旅行网保留所有权利
 */
package com.elong.nb.common.model;

/**
 * 添加AbstractWCF类，用于记录调用wcf接口日志
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2017年8月10日 下午7:26:32   user     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		zhangyang.zhu  
 * @version		1.0  
 * @param <T1>
 * @since		JDK1.7
 */
public abstract class AbstractWCF<T1,T2>{
	public  String classFullName;
	public  String methodName;
	
	public abstract T2 invoke(T1 request);
}
