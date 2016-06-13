package com.elong.nb.common.gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import com.elong.nb.common.model.RestRequest;
import com.elong.nb.common.model.RestResponse;
import com.google.gson.GsonBuilder;

public class GsonUtil {

	@SuppressWarnings("rawtypes")
	public static <T> RestRequest<T> toReq(HttpServletRequest request, Class<T> clazz) throws IOException {
		String json = IOUtils.toString(request.getInputStream(), "utf-8");
		GsonBuilder gsonBuilder = new GsonBuilder();
		// 添加枚举适配器
		gsonBuilder.registerTypeHierarchyAdapter(Enum.class, new EnumTypeAdapter());
		gsonBuilder.registerTypeAdapter(Date.class, new DateTypeAdapter());
		Type objectType = type(RestRequest.class, clazz);
		RestRequest req = gsonBuilder.create().fromJson(json, objectType);
		return req;
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
		// 增加版本对应的输出设置
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new DateTypeAdapter());
		gsonBuilder.setVersion(version);
		String json = gsonBuilder.create().toJson(resp, RestResponse.class);
		return json;
	}
}
