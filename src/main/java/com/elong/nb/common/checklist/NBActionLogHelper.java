package com.elong.nb.common.checklist;

import java.sql.Time;
import java.util.Calendar;

import com.elong.springmvc_enhance.common.LogHandler;
import com.elong.springmvc_enhance.entities.ActionLogEntity;
import com.elong.springmvc_enhance.utilities.ExceptionHelper;
import com.elong.springmvc_enhance.utilities.IdentityUtil;
import com.elong.springmvc_enhance.utilities.LogHelper;
import com.elong.springmvc_enhance.utilities.ServerInfoHelper;

public class NBActionLogHelper {

	/**
	 * 
	 * 记录系统操作的统计日志
	 * 
	 * @param traceId
	 *            当前方法的traceId,为了将请求的调用链串起来，请在方法开始时获取，
	 *            ThreadLocalForTraceIdUtil.getCurrenMethodtTraceId(methodName)
	 * @param success
	 *            是否系统异常
	 * @param methodName
	 *            方法名称
	 * @param classFullName
	 *            类的全称
	 * @param e
	 *            异常类
	 * @param excuteTimeSpan
	 *            方法的执行时间
	 * @param responseCode
	 *            业务方返回码
	 * @param message
	 *            异常信息
	 * @param result
	 *            方法返回值
	 * @param parameters
	 *            方法入参
	 * @param channel
	 *            分销商用户名
	 * @param nbtype
	 * 			  日志在nb服务化体系中产生的位置
	 */
	public static void businessLog(String traceId, Boolean success,
			String methodName, String classFullName, Exception e,
			float excuteTimeSpan, Integer responseCode, String message,
			String result, String parameters, String channel, EnumNBLogType nbtype) {
		ActionLogEntity actionLogEntity = new ActionLogEntity();
		if (traceId == null) return;
		
		try {
			actionLogEntity.setUniqueID(traceId);
			if (success) {
				actionLogEntity.setLogType(0);
			} else {
				actionLogEntity.setLogType(1);
			}
			String hostname = ServerInfoHelper.getServerName(); // 获得本机名称

			actionLogEntity.setMethodName(methodName);
			actionLogEntity.setAppServerIP(hostname);
			if (e == null) {
				actionLogEntity.setExceptionMessage(null);
			} else {
				actionLogEntity.setExceptionMessage(limitStr(e.getMessage(),
						8000));
			}
			actionLogEntity.setDetail(limitStr(ExceptionHelper.getStackInfo(e),
					8000));
			actionLogEntity.setChannel(channel);
			actionLogEntity.setExcuteTimeSpan(excuteTimeSpan);
			actionLogEntity.setLogTime(new Time(System.currentTimeMillis()));
			actionLogEntity.setProductLine(IdentityUtil.getProductLine());
			actionLogEntity.setServiceName(classFullName);
			actionLogEntity.setResponseCode(responseCode);
			actionLogEntity.setExtend1(message);
			actionLogEntity.setExtend2(nbtype.toString());
			actionLogEntity.setParameters(limitStr(parameters, 10000));
			actionLogEntity.setResult(limitStr(result,10000));
			actionLogEntity.setCreateTime(Calendar.getInstance().getTime());

		} catch (Exception exception) {
			LogHandler.spring_mvc_enhance.error(
					"act=businessLog error message=" + exception.getMessage(), exception);
		} finally {
			LogHelper.actionLogEntityInfo(actionLogEntity.toString());
		}
	}

	private static String limitStr(String str, int length) {
		if (str == null || str.length() <= length) {
			return str;
		}
		str = str.substring(0, length);
		return str;
	}

}
