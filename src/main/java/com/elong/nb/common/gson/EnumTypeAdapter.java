package com.elong.nb.common.gson;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.groovy.util.Finalizable;
import org.hibernate.engine.Mapping;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

/**
 * 多所有请求的枚举类型进行映射 适配枚举类型String与value两种情况 由GsonHttpMessageConverter注册
 * DefaultDateTypeAdapter仿
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("rawtypes")
public class EnumTypeAdapter implements JsonDeserializer<Enum> {

	private static Logger LocalMsg = LogManager.getLogger(EnumTypeAdapter.class);
	
	@SuppressWarnings("unchecked")
	public Enum deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		if (!(json instanceof JsonPrimitive)) {
			throw new JsonParseException("The date should be a string value");
		}
		String value = json.getAsString();
		boolean isNumber = NumberUtils.isNumber(value);

		if (!isNumber) {
			// 普通的枚举处理,
			return EnumUtils.getEnum((Class) typeOfT, json.getAsString());
		}

		// 不是字符串的情况
		Integer enumValue = Integer.valueOf(value);
		try {
			return (Enum) MethodUtils.invokeStaticMethod((Class) typeOfT, "forValue", enumValue);
		} catch (Exception e) {
			LocalMsg.error("Error deserialize type:" + typeOfT + " and value:" + enumValue, e);
			return null;
		}
	}
	
}
