package com.elong.nb.common.model;

import com.elong.nb.common.util.GenericsUtils;

public class RestResponse<T> {
	private String Code = "0";
	private T Result;
	private String Guid;

	@SuppressWarnings("unchecked")
	public RestResponse(String guid) {
		Class<T> entityClass = GenericsUtils
				.getSuperClassGenricType(getClass());
		try {
			Result = entityClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			Result = null;
		}
		this.Guid = guid;
	}

	public String getGuid() {
		return Guid;
	}

	public void setGuid(String guid) {
		Guid = guid;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public T getResult() {
		return Result;
	}

	public void setResult(T result) {
		Result = result;
	}
}
