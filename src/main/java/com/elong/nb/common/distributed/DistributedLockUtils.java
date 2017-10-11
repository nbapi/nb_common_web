/**   
 * @(#)DistributedLockUtils.java	2017年9月29日	下午2:33:53	   
 *     
 * Copyrights (C) 2017艺龙旅行网保留所有权利
 */
package com.elong.nb.common.distributed;

/**
 * 分布式锁工具类
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2017年9月29日 下午2:33:53   user     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		zhangyang.zhu  
 * @version		1.0  
 * @since		JDK1.7
 */
public class DistributedLockUtils {
	
	public static boolean lock(IDistributedLock distributedLock){
			return distributedLock.lock();
	}
	
	public static void unLock(IDistributedLock distributedLock){
				distributedLock.unLock();
	}
}
