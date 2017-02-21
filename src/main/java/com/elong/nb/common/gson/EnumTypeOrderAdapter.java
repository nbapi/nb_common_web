/**   
 * @(#)EnumTypeOrderAdapter.java	2017年2月21日	上午11:08:14	   
 *     
 * Copyrights (C) 2017艺龙旅行网保留所有权利
 */
package com.elong.nb.common.gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2017年2月21日 上午11:08:14   user     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		zhangyang.zhu  
 * @version		1.0  
 * @since		JDK1.7
 */
@SuppressWarnings("rawtypes")
public class EnumTypeOrderAdapter implements JsonDeserializer<Enum>{
	
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
			Map<String, ?>map=EnumUtils.getEnumMap((Class)typeOfT);
			if(map!=null&&map.keySet()!=null&&map.keySet().size()>0){
				for (String key : map.keySet()) {
					if(value.toLowerCase().equals(key.toLowerCase())){
							value=key;
							break;
						}
					}
				}
			return EnumUtils.getEnum((Class) typeOfT,value);
			//return EnumUtils.getEnum((Class) typeOfT, json.getAsString());
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
