package com.elong.nb.common.util;

import org.apache.commons.lang3.StringUtils;

import com.elong.nb.common.model.ErrorCode;
import com.elong.nb.common.model.RestRequest;

public class ValidateUtil {

	public static String validateRestRequest(RestRequest<?> req) {
		StringBuffer sb = new StringBuffer();
		if (req.getVersion() == null || req.getVersion() <= 0d)
			sb.append(ErrorCode.Common_VersionInvalid);
		if (req.getRequest() == null)
			sb.append(ErrorCode.Common_DataRequired);
		if (req.getProxyInfo() == null)
			sb.append(ErrorCode.Common_ProxyInfoInvalid);
		if (StringUtils.isBlank(req.getGuid()))
			sb.append(ErrorCode.Common_GUIDRequired);
		return sb.toString();
	}
}
