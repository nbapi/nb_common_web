package com.elong.nb.common.exception;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
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
	/**
	 * 全局异常记录logger
	 */
	private static Logger logger = Logger.getLogger("globalExceptionLogger");

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		String accept = request.getHeader("Content-Type");
		String errorJson = "";
		ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
		ex.printStackTrace(new java.io.PrintWriter(buf, true));
		String expMessage = buf.toString();
		try {
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RestResponse restResponse = new RestResponse(errorJson);
		restResponse.setCode(ErrorCode.Common_UnkownException+ ex.getMessage()+expMessage);
		ModelAndView exceptionView = new ModelAndView("apierror");
		errorJson = gson.toJson(restResponse);
		exceptionView.addObject("errorResponse", errorJson);
		return exceptionView;
	}
}