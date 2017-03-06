package com.elong.nb.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

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
		RestResponse restResponse = new RestResponse(null);
		restResponse.setCode(ErrorCode.Common_UnkownException+ ex.getMessage());
		ModelAndView exceptionView = new ModelAndView("apierror");
		errorJson = gson.toJson(restResponse);
		exceptionView.addObject("errorResponse", errorJson);
		return exceptionView;
	}
}