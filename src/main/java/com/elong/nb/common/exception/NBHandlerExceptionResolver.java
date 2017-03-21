package com.elong.nb.common.exception;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.elong.nb.common.checklist.Constants;
import com.elong.nb.common.model.ErrorCode;
import com.elong.nb.common.model.RestResponse;
import com.google.gson.Gson;

/**
 * 全局异常处理
 * 
 * @author bin.song
 *
 */
public class NBHandlerExceptionResolver implements HandlerExceptionResolver {
	private Gson gson=new Gson();
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		String errorJson = "";
		response.setHeader("Content-type", "application/json;charset=UTF-8");
		RequestAttributes requestAttr = RequestContextHolder.getRequestAttributes();
		Object guid = requestAttr.getAttribute(Constants.ELONG_REQUEST_REQUESTGUID,
				ServletRequestAttributes.SCOPE_REQUEST);
		if (guid == null)
			guid = UUID.randomUUID();
		RestResponse restResponse = new RestResponse(guid.toString());
		restResponse.setCode(ErrorCode.Common_UnkownException+ ex.getMessage());
		ModelAndView exceptionView = new ModelAndView("apierror");
		errorJson = gson.toJson(restResponse);
		exceptionView.addObject("errorResponse", errorJson);
		return exceptionView;
	}
}