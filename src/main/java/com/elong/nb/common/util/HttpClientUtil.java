/**   
 * @(#)HttpClientUtil.java	2017年8月8日	下午4:16:59	   
 *     
 * Copyrights (C) 2017艺龙旅行网保留所有权利
 */
package com.elong.nb.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.elong.nb.common.model.NbapiHttpRequest;

/**
 * HttpClient工具类
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2017年8月8日 下午4:16:59   suht     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		suht  
 * @version		1.0  
 * @since		JDK1.7
 */
public class HttpClientUtil {

	private static final Log logger = LogFactory.getLog(HttpClientUtil.class);

	private static final RequestConfig defaultRequestConfig = generateDefaultRequestConfig();

	private static final CloseableHttpClient httpClient = generateHttpClient();

	private static final ResponseHandler<String> responseHandler = new CustomResponseHandler();

	/** 
	 * post-json方式
	 *
	 * @param nbapiHttpRequest
	 * @return
	 */
	public static String httpJsonPost(NbapiHttpRequest nbapiHttpRequest) {
		String url = nbapiHttpRequest.getUrl();
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalArgumentException("uri = " + url + ",error = " + e.getMessage());
		}
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setEntity(new StringEntity(nbapiHttpRequest.getParamStr(), "UTF-8"));
		resetRequestConfig(httpPost, nbapiHttpRequest);
		return httpRequest(httpPost);
	}

	/** 
	 * post-form方式 
	 *
	 * @param nbapiHttpRequest
	 * @return
	 */
	public static String httpFormPost(NbapiHttpRequest nbapiHttpRequest) {
		String url = nbapiHttpRequest.getUrl();
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalArgumentException("url = " + url + ",error = " + e.getMessage());
		}
		HttpPost httpPost = new HttpPost(uri);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Map<String, Object> paramsMap = nbapiHttpRequest.getParamsMap();
		if (paramsMap != null && paramsMap.size() > 0) {
			for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
			}
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException("httpPost UnsupportedEncodingException = " + e.getMessage());
		}
		resetRequestConfig(httpPost, nbapiHttpRequest);
		return httpRequest(httpPost);
	}

	/** 
	 * get方法
	 *
	 * @param nbapiHttpRequest
	 * @return
	 */
	public static String httpGet(NbapiHttpRequest nbapiHttpRequest) {
		String url = nbapiHttpRequest.getUrl();
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalArgumentException("url = " + url + ",error = " + e.getMessage());
		}
		HttpGet httpGet = new HttpGet(uri);
		resetRequestConfig(httpGet, nbapiHttpRequest);
		return httpRequest(httpGet);
	}

	/***************************************************private method below*****************************************/
	private static String httpRequest(HttpRequestBase httpRequestBase) {
		long startTime = System.currentTimeMillis();
		logger.info("RequestLine = " + httpRequestBase.getRequestLine());
		String responseBody = null;
		try {
			responseBody = httpClient.execute(httpRequestBase, responseHandler);
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException("httpPost ClientProtocolException = " + e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException("httpPost IOException = " + e.getMessage());
		}
		logger.info("use time = " + (System.currentTimeMillis() - startTime) + "ms");
		return responseBody;
	}

	/** 
	 * 默认请求配置 
	 *
	 * @return
	 */
	private static RequestConfig generateDefaultRequestConfig() {
		return RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(30000).setConnectionRequestTimeout(2000)
				.setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
	}

	/** 
	 * 连接池 
	 *
	 * @return
	 */
	private static CloseableHttpClient generateHttpClient() {
		Registry<ConnectionSocketFactory> socketFactory = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.INSTANCE).build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactory);
		SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).setSoKeepAlive(true).build();
		connManager.setDefaultSocketConfig(socketConfig);
		ConnectionConfig connectionConfig = ConnectionConfig.custom().setCharset(Consts.UTF_8)
				.setMalformedInputAction(CodingErrorAction.IGNORE).setUnmappableInputAction(CodingErrorAction.IGNORE).build();
		connManager.setDefaultConnectionConfig(connectionConfig);
		connManager.setMaxTotal(65535);
		connManager.setDefaultMaxPerRoute(65535);
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connManager)
				.setDefaultRequestConfig(defaultRequestConfig).build();
		return httpClient;
	}

	/** 
	 *  重置请求超时配置 
	 *
	 * @param httpRequestBase
	 */
	private static void resetRequestConfig(HttpRequestBase httpRequestBase, NbapiHttpRequest nbapiHttpRequest) {
		RequestConfig.Builder builder = RequestConfig.copy(defaultRequestConfig);
		int connectTimeout = nbapiHttpRequest.getConnectTimeout();
		if (connectTimeout > 0) {
			builder = builder.setConnectTimeout(connectTimeout);
		}
		int socketTimeout = nbapiHttpRequest.getSocketTimeout();
		if (socketTimeout > 0) {
			builder = builder.setSocketTimeout(socketTimeout);
		}
		int connectionRequestTimeout = nbapiHttpRequest.getConnectionRequestTimeout();
		if (connectionRequestTimeout > 0) {
			builder = builder.setConnectionRequestTimeout(connectionRequestTimeout);
		}
		RequestConfig requestConfig = builder.build();
		httpRequestBase.setConfig(requestConfig);
	}

	/**
	 * 自定义ResponseHandler 
	 *
	 * <p>
	 * 修改历史:											<br>  
	 * 修改日期    		修改人员   	版本	 		修改内容<br>  
	 * -------------------------------------------------<br>  
	 * 2017年8月8日 下午4:35:58   suht     1.0    	初始化创建<br>
	 * </p> 
	 *
	 * @author		suht  
	 * @version		1.0  
	 * @since		JDK1.7
	 */
	private static class CustomResponseHandler implements ResponseHandler<String> {
		@Override
		public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
			StatusLine statusLine = response.getStatusLine();
			logger.info("StatusLine = " + statusLine);
			int status = statusLine == null ? 0 : statusLine.getStatusCode();
			HttpEntity entity = response.getEntity();
			if (status >= 200 && status < 300) {
				return entity != null ? EntityUtils.toString(entity) : StringUtils.EMPTY;
			} else {
				EntityUtils.consume(entity);
				throw new ClientProtocolException("Unexpected response status: " + status);
			}
		}
	}

	public static void main(String[] args) {
		NbapiHttpRequest nbapiHttpRequest = new NbapiHttpRequest();
		String reqUrl = "http://10.39.34.25:8965/api/Hotel/GetLastId";
		String reqData = "{\"hotelId\":12}";
		nbapiHttpRequest.setUrl(reqUrl);
		nbapiHttpRequest.setParamStr(reqData);
		String responseBody = HttpClientUtil.httpJsonPost(nbapiHttpRequest);
		System.out.println(responseBody);
	}
}
