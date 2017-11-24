/**   
 * @(#)LockedOwnerInfo.java	2017年10月19日	上午10:08:29	   
 *     
 * Copyrights (C) 2017艺龙旅行网保留所有权利
 */
package com.elong.nb.common.distributed;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.elong.nb.common.util.LocalHost;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2017年10月19日 上午10:08:29   user     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		zhangyang.zhu  
 * @version		1.0  
 * @since		JDK1.7
 */
public class LockedOwnerInfo {
	private static final Log logger = LogFactory.getLog(LockedOwnerInfo.class);
	private final static String mac=LocalHost.getMACAddress();;
	private final static long jvmPID=LocalHost.getJVMPid();
	private long threadID;
	public long getThreadID() {
		return threadID;
	}
	public void setThreadID(long threadID) {
		this.threadID = threadID;
	}

	
	public  String getMac() {
		return mac;
	}
	public  long getJvmpid() {
		return jvmPID;
	}
	
	/**   
	 * (构造函数说明)  
	 *      
	 */
	public LockedOwnerInfo() {
		this.threadID=Thread.currentThread().getId();
	}
	
	public static LockedOwnerInfo fromString(String info){
		try {
			logger.info("lockedOwnerInfo:"+info);
			return JSON.parseObject(info, LockedOwnerInfo.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String toString(LockedOwnerInfo lockedOwnerInfo){
		return JSON.toJSONString(lockedOwnerInfo);
	}
	
	public static boolean isSameOwner(LockedOwnerInfo redisLockedOwnerInfo){
		boolean b=false;
		if(mac.equals(redisLockedOwnerInfo.getMac())&&
				jvmPID==redisLockedOwnerInfo.getJvmpid()&&
				Thread.currentThread().getId()==redisLockedOwnerInfo.getThreadID()){
			b=true;
		}
		return b;
	}
	
	
}
