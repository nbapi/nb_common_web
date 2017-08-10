package com.elong.nb.common.model;

import java.util.Map;

public class NbapiHttpRequest {

	/** 
	 * 请求url
	 *
	 * String NbapiHttpRequest.java url
	 */
	private String url;

	/** 
	 * 请求参数-json方式	
	 *
	 * String NbapiHttpRequest.java paramStr
	 */
	private String paramStr;

	/** 
	 * 请求参数-form方式
	 *
	 * Map<String,Object> NbapiHttpRequest.java paramsMap
	 */
	private Map<String, Object> paramsMap;

	/** 
	 * (可选，默认2000ms)连接超时ms	
	 *
	 * int NbapiHttpRequest.java connectTimeout
	 */
	private int connectTimeout;

	/** 
	 * (可选，默认30000ms)读取超时ms	
	 *
	 * int NbapiHttpRequest.java socketTimeout
	 */
	private int socketTimeout;

	/** 
	 * (可选，默认2000ms)获取连接超时ms	
	 *
	 * int NbapiHttpRequest.java connectionRequestTimeout
	 */
	private int connectionRequestTimeout;

	/**   
	 * 得到url的值   
	 *   
	 * @return url的值
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置url的值
	 *   
	 * @param url 被设置的值
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**   
	 * 得到paramStr的值   
	 *   
	 * @return paramStr的值
	 */
	public String getParamStr() {
		return paramStr;
	}

	/**
	 * 设置paramStr的值
	 *   
	 * @param paramStr 被设置的值
	 */
	public void setParamStr(String paramStr) {
		this.paramStr = paramStr;
	}

	/**   
	 * 得到paramsMap的值   
	 *   
	 * @return paramsMap的值
	 */
	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	/**
	 * 设置paramsMap的值
	 *   
	 * @param paramsMap 被设置的值
	 */
	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}

	/**   
	 * 得到connectTimeout的值   
	 *   
	 * @return connectTimeout的值
	 */
	public int getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * 设置connectTimeout的值
	 *   
	 * @param connectTimeout 被设置的值
	 */
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/**   
	 * 得到socketTimeout的值   
	 *   
	 * @return socketTimeout的值
	 */
	public int getSocketTimeout() {
		return socketTimeout;
	}

	/**
	 * 设置socketTimeout的值
	 *   
	 * @param socketTimeout 被设置的值
	 */
	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	/**   
	 * 得到connectionRequestTimeout的值   
	 *   
	 * @return connectionRequestTimeout的值
	 */
	public int getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}

	/**
	 * 设置connectionRequestTimeout的值
	 *   
	 * @param connectionRequestTimeout 被设置的值
	 */
	public void setConnectionRequestTimeout(int connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}

}
