/**   
 * @(#)HowSwitchUtil.java	2017年7月25日	下午4:17:34	   
 *     
 * Copyrights (C) 2017艺龙旅行网保留所有权利
 */
package com.elong.nb.common.util;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elong.hotswitch.client.HotSwitchConfigHelper;
import com.elong.hotswitch.client.Entity.RequestParamEntity;
import com.elong.hotswitch.client.Exception.HotSwitchClientException;

/**
 * HotSwitch工具类
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2017年7月25日 下午4:17:34   suht     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		suht  
 * @version		1.0  
 * @since		JDK1.7
 */
public class HowSwitchUtil {

	private static Logger logger = LoggerFactory.getLogger(HowSwitchUtil.class);

	private static HotSwitchConfigHelper hotSwitchConfigHelper = null;

	static {
		Properties properties = CommonsUtil.loadProperties("conf/custom/env/hotswitch.properties");
		String appName = properties.getProperty("AppName");
		String version = properties.getProperty("Version");
		String requestUrl = properties.getProperty("RequestUrl");
		String timeOut = properties.getProperty("TimeOut");
		String clientConfigPath = properties.getProperty("ClientConfigPath");
		if (StringUtils.isEmpty(appName) || StringUtils.isEmpty(version) || StringUtils.isEmpty(requestUrl) || StringUtils.isEmpty(timeOut)) {
			throw new IllegalStateException(
					"It has no element['AppName','Version','RequestUrl','TimeOut'] at least in hotswitch.properties");
		}
		int timeOutInt = 0;
		try {
			timeOutInt = Integer.valueOf(timeOut);
		} catch (NumberFormatException e1) {
			throw new IllegalStateException("the element['timeOut'] must be an Integer type in hotswitch.properties");
		}

		RequestParamEntity requestParamEntity = new RequestParamEntity();
		requestParamEntity.setAppName(appName);
		requestParamEntity.setVersion(version);
		requestParamEntity.setRequestUrl(requestUrl);
		requestParamEntity.setTimeOut(timeOutInt);
		requestParamEntity.setConfigFileBasePath(clientConfigPath);
		try {
			hotSwitchConfigHelper = new HotSwitchConfigHelper(requestParamEntity);
		} catch (HotSwitchClientException e) {
			logger.error("config center init fail.", e);
		}
	}

	/** 
	 * 获取配置信息, 不存在或报错时返回null
	 *
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static <T> T getValue(String key, Class<T> clazz) {
		T value = null;
		try {
			value = hotSwitchConfigHelper.GetConfigValue(key, clazz);
		} catch (HotSwitchClientException e) {
			logger.error("config center get value error,key = " + key, e);
		}
		return value;
	}

	/** 
	 * 获取配置信息, 不存在或报错时返回默认值
	 *
	 * @param key
	 * @param clazz
	 * @param defaultValue
	 * @return
	 */
	public static <T> T getValue(String key, Class<T> clazz, T defaultValue) {
		T value = defaultValue;
		try {
			value = hotSwitchConfigHelper.GetConfigValue(key, clazz);
		} catch (HotSwitchClientException e) {
			logger.error("config center get value error,key = " + key, e);
		}
		return value;
	}
	
	public static void main(String[] args){
		System.out.println(getValue("rp.from", Integer.class));
	}

}
