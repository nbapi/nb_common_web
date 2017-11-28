package com.elong.nb.common.util;

import com.elong.nb.common.checklist.EnumNBLogType;
import com.elong.nb.common.checklist.NBActionLogHelper;


public class LogUtils {
	/**
	 * 随机获取startTime
	 * @return
	 */
	public static long getStartTime(){
		long startTime = 0;
		if(RandomJudgeUtil.judge(1)){
			startTime = System.currentTimeMillis();
		}
		return startTime;
	}
	
	/**
	 * 随机写日志
	 * @param success
	 * @param methodName
	 * @param className
	 * @param startTime
	 * @param e
	 */
	public static void writeLog(boolean success, String methodName, String className, long startTime, Exception e){
		if(startTime > 0){
			if(success){
				NBActionLogHelper.businessLog(null, true, methodName, className, null, (System.currentTimeMillis() - startTime), 0, null, null,
						null, null, EnumNBLogType.DAO);
			}else{
				NBActionLogHelper.businessLog(null, false, methodName,className, e, (System.currentTimeMillis() - startTime), -1, e==null? null:e.getMessage(), null,
						null, null, EnumNBLogType.DAO);
			}
		}
	}
	
	
	/**
	 * 随机写日志
	 * @param success
	 * @param methodName
	 * @param className
	 * @param param
	 * @param startTime
	 * @param e
	 */
	public static void writeLog(boolean success, String methodName, String className, String param, long startTime, Exception e){
		if(startTime > 0){
			if(success){
				NBActionLogHelper.businessLog(null, true, methodName, className, null, (System.currentTimeMillis() - startTime), 0, null, null,
						param, null, EnumNBLogType.DAO);
			}else{
				NBActionLogHelper.businessLog(null, false, methodName,className, e, (System.currentTimeMillis() - startTime), -1, e==null? null:e.getMessage(), null,
						param, null, EnumNBLogType.DAO);
			}
		}
	}
	
}
