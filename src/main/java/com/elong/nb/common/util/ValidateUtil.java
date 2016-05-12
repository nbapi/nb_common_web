package com.elong.nb.common.util;

import org.apache.commons.lang3.StringUtils;

import com.elong.nb.common.model.RestRequest;

public class ValidateUtil {

	private static final String Version = "Version不能为空";
	private static final String Local = "Local不能为空";
	private static final String ProxyAccount = "企业账户信息不能为空";
	private static final String Request = "H000016|Data参数必须填写";
	private static final String Guid = "H000017|GUID参数必须填写";

	public static String validateRestRequest(RestRequest req) {
		StringBuffer sb = new StringBuffer();
		if (req.getVersion() == null || req.getVersion() <= 0d)
			sb.append(Version);
		if (req.getLocal() == null)
			sb.append(Local);
		if (req.getRequest() == null)
			sb.append(Request);
		if (req.getProxyInfo() == null)
			sb.append(ProxyAccount);
		if (StringUtils.isBlank(req.getGuid()))
			sb.append(Guid);
		return sb.toString();
	}
}
