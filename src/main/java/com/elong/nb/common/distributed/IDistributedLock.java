/**   
 * @(#)IDistributedLock.java	2017年9月29日	下午2:54:53	   
 *     
 * Copyrights (C) 2017艺龙旅行网保留所有权利
 */
package com.elong.nb.common.distributed;

/**
 * 分布式锁接口
 * 方便redis、zk、db继承该接口进行扩展
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2017年9月29日 下午2:54:53   user     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		zhangyang.zhu  
 * @version		1.0  
 * @since		JDK1.7
 */
public interface IDistributedLock {
	public  boolean lock();
	public boolean unLock();
}
