package com.elong.nb.common.gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.elong.nb.common.biglog.Constants;
import com.elong.nb.common.model.RestRequest;
import com.elong.nb.common.model.RestResponse;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;

public class GsonUtil {

	@SuppressWarnings("rawtypes")
	public static <T> RestRequest<T> toReq(HttpServletRequest request,
			Class<T> clazz, Map<Class, TypeAdapter> adapters)
			throws IOException {
		String json = IOUtils.toString(request.getInputStream(), "utf-8");

		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ra.setAttribute(Constants.ELONG_REQUEST_JSON, json == null ? "" : json,
				ServletRequestAttributes.SCOPE_REQUEST);

		GsonBuilder gsonBuilder = new GsonBuilder();
		// 添加枚举适配器
		gsonBuilder.registerTypeHierarchyAdapter(Enum.class,
				new EnumTypeAdapter());
		gsonBuilder.registerTypeAdapter(Date.class, new DateTypeAdapter());
		if (adapters != null && !adapters.isEmpty()) {
			for (Entry<Class, TypeAdapter> e : adapters.entrySet()) {
				gsonBuilder.registerTypeAdapter(e.getKey(), e.getValue());
			}
		}

		Type objectType = type(RestRequest.class, clazz);
		RestRequest req = gsonBuilder.create().fromJson(json, objectType);

		if (req != null && req.getGuid() != null && req.getGuid().length() > 0)
			ra.setAttribute(Constants.ELONG_REQUEST_REQUESTGUID, req.getGuid(),
					ServletRequestAttributes.SCOPE_REQUEST);

		return req;
	}
	
	@SuppressWarnings("rawtypes")
	public static <T> RestRequest<T> toReqAdapter(HttpServletRequest request,
			Class<T> clazz, Map<Class, TypeAdapter> adapters)
			throws IOException {
		String json = IOUtils.toString(request.getInputStream(), "utf-8");
		GsonBuilder gsonBuilder = new GsonBuilder();
		// 添加枚举适配器，兼容大小写，暂时只用于成单接口，会影响性能
		gsonBuilder.registerTypeHierarchyAdapter(Enum.class,
				new EnumTypeOrderAdapter());
		gsonBuilder.registerTypeAdapter(Date.class, new DateTypeAdapter());
		if (adapters != null && !adapters.isEmpty()) {
			for (Entry<Class, TypeAdapter> e : adapters.entrySet()) {
				gsonBuilder.registerTypeAdapter(e.getKey(), e.getValue());
			}
		}
		Type objectType = type(RestRequest.class, clazz);
		RestRequest req = gsonBuilder.create().fromJson(json, objectType);
		
		try {
			//隐藏信用卡相关信息
			Pattern pattern=Pattern.compile("Number\":[a-zA-Z0-9]+,");
			Matcher matcher=pattern.matcher(json);
			if(matcher.find()){
				json=matcher.replaceAll("Number\":\"###\",");
			}
		} catch (Exception e) {
		}
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ra.setAttribute(Constants.ELONG_REQUEST_JSON, json == null ? "" : json,
				ServletRequestAttributes.SCOPE_REQUEST);

		if (req != null && req.getGuid() != null && req.getGuid().length() > 0)
			ra.setAttribute(Constants.ELONG_REQUEST_REQUESTGUID, req.getGuid(),
					ServletRequestAttributes.SCOPE_REQUEST);

		return req;
	}
	
	@SuppressWarnings("rawtypes")
	public static <T> RestResponse<T> toResponse(String resultJson,
			Type typeObj, Map<Class, TypeAdapter> adapters)
			throws IOException {
		String json = resultJson;

		//RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		//ra.setAttribute(Constants.ELONG_REQUEST_JSON, json == null ? "" : json,
		//		ServletResponseAttributes.SCOPE_REQUEST);

		GsonBuilder gsonBuilder = new GsonBuilder();
		// 添加枚举适配器
		gsonBuilder.registerTypeHierarchyAdapter(Enum.class,
				new EnumTypeAdapter());
		gsonBuilder.registerTypeAdapter(Date.class, new DateTypeAdapter());
		if (adapters != null && !adapters.isEmpty()) {
			for (Entry<Class, TypeAdapter> e : adapters.entrySet()) {
				gsonBuilder.registerTypeAdapter(e.getKey(), e.getValue());
			}
		}

		//Type objectType = type(RestResponse.class, clazz);
		RestResponse res = gsonBuilder.create().fromJson(json, typeObj);

		//if (res != null && res.getGuid() != null && res.getGuid().length() > 0)
		//	ra.setAttribute(Constants.ELONG_REQUEST_REQUESTGUID, req.getGuid(),
		//			ServletRequestAttributes.SCOPE_REQUEST);

		return res;
	}

	static ParameterizedType type(final Class raw, final Type... args) {
		return new ParameterizedType() {
			public Type getRawType() {
				return raw;
			}

			public Type[] getActualTypeArguments() {
				return args;
			}

			public Type getOwnerType() {
				return null;
			}
		};
	}

	@SuppressWarnings("rawtypes")
	public static String toJson(RestResponse resp, double version) {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ra.setAttribute(Constants.ELONG_RESPONSE_CODE, resp == null||resp.getCode()==null ? "" : resp.getCode().split("\\|")[0],
				ServletRequestAttributes.SCOPE_REQUEST);
		// 增加版本对应的输出设置
		GsonBuilder gsonBuilder = new GsonBuilder().disableHtmlEscaping();
		gsonBuilder.registerTypeAdapter(Date.class, new DateTypeAdapter());
		if (version > 0)
			gsonBuilder.setVersion(version);
		String json = gsonBuilder.create().toJson(resp, RestResponse.class);
		return json;
	}
	
	@SuppressWarnings("rawtypes")
	public static String toJson(RestRequest req, double version) {
		// 增加版本对应的输出设置
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new DateTypeAdapter());
		if (version > 0)
			gsonBuilder.setVersion(version);
		String json = gsonBuilder.create().toJson(req, RestRequest.class);
		return json;
	}
	
	@SuppressWarnings("rawtypes")
	public static String toJsonResponse(RestResponse resp, double version) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new DateTypeAdapter());
		if (version > 0)
			gsonBuilder.setVersion(version);
		String json = gsonBuilder.create().toJson(resp, RestResponse.class);
		return json;
	}
	@SuppressWarnings("rawtypes")
	public static<T> String toJsonT(T req) {
		// 增加版本对应的输出设置
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new DateTypeAdapter());
		String json = gsonBuilder.create().toJson(req, RestRequest.class);
		return json;
	}
}
