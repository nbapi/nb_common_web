/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.elong.nb.common.gson;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * Adapter for Date. Although this class appears stateless, it is not.
 * DateFormat captures its time zone and locale when it is created, which gives
 * this class state. DateFormat isn't thread safe either, so this class has to
 * synchronize its read and write methods.
 */
public final class DateTypeAdapter extends TypeAdapter<Date> {

	private static Logger LocalMsg = LogManager
			.getLogger(DateTypeAdapter.class);

	public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
		@SuppressWarnings("unchecked")
		// we use a runtime check to make sure the 'T's equal
		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
			return typeToken.getRawType() == Date.class ? (TypeAdapter<T>) new DateTypeAdapter()
					: null;
		}
	};

	@Override
	public Date read(JsonReader in) throws IOException {
		if (in.peek() == JsonToken.NULL) {
			in.nextNull();
			return null;
		}
		return deserializeToDate(in.nextString());
	}

	/**
	 * elongapi使用的支持的击中日期格式，主要处理request传来的日期字符串
	 * 
	 * @param json
	 * @return
	 */
	private synchronized Date deserializeToDate(String json) {
		Date jsonDate;
		json = json.trim();
		if (isWithMicSecends(json)) json = json.substring(0, 19);
		try {
			jsonDate = DateUtils.parseDate(json, new String[] {
					"MM/dd/yyyy",
					"yyyy.MM.dd",
					"yyyy-MM-dd",
					"yyyy-MM-dd HH:mm",
					"yyyy-MM-dd HH:mm:ss",
					"yyyy/MM/dd HH:mm:ss",
					"MM/dd/yy HH:mm",
					//"MM/dd/yyyy HH:mm:ss",
					"yyyy-MM-dd HH:mm:ss.SSS",
					"yyyy-MM-dd'+'HH:mm:ss",
					"yyyy-MM-dd'T'HH:mm:ss",
					"yyyy-MM-dd'T'HH:mm:ss.SSS",
					"yyyy-MM-dd'T'HH:mm:ss'+08:00'",
					"yyyy-MM-dd'T'HH:mm:ss.SSS'+08:00'",
					"yyyy.MM.dd'T'HH:mm:ss'+08:00'",
					"yyyy.MM.dd'T'HH:mm:ss' +08:00'",
					"yyyy.MM.dd'T'HH:mm"
					});
		} catch (ParseException e) {
			LocalMsg.error(json + " This msg json can't be parse!!!");
			throw new JsonSyntaxException(json, e);
		}
		return jsonDate;
	}

	/**
	 * 是否是带微妙的形式   2016-07-19T16:05:00.2024835 
	 * @param date
	 * @return
	 */
	private static boolean isWithMicSecends(String date){
		if (date.length() == 27){
			if (date.charAt(19) == '.'){
				boolean isAllDig = true;
				for (int i=20; i<27; i++){
					char c = date.charAt(i);
					if (c > '9' || c < '0') {
						isAllDig = false;
						break;
					}
				}
				if (isAllDig) return true;
			}
		}
		return false;
	}
	
	/**
	 * json输出的日期字符串
	 */
	@Override
	public synchronized void write(JsonWriter out, Date value)
			throws IOException {
		if (value == null) {
			out.nullValue();
			return;
		}
		SimpleDateFormat sFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss'+08:00'");
		out.value(sFormat.format(value));
	}
}
