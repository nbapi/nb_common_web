package com.elong.nb.common.biglog;

import java.util.UUID;

import org.apache.commons.lang3.ClassUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class LogAop {

	private static Logger logger = LogManager.getLogger("biglog");

	// ------------------------controller begin---------------------------
	/**
	 * 之前
	 * 
	 * @param point
	 * @throws Throwable
	 */
	@Before("execution(public * com.elong.nb.controller..*..*(..))")
	public void handlerLogBefore(JoinPoint point) {
		RequestAttributes request = RequestContextHolder.getRequestAttributes();
		request.setAttribute(Constants.ELONG_REQUEST_STARTTIME, System.currentTimeMillis(), ServletRequestAttributes.SCOPE_REQUEST);
		request.setAttribute(Constants.ELONG_REQUEST_TRACEID, UUID.randomUUID().toString(), ServletRequestAttributes.SCOPE_REQUEST);
	}

	/**
	 * 之后
	 * 
	 * @param point
	 * @throws Throwable
	 */
	@AfterReturning(pointcut = "execution(public * com.elong.nb.controller..*..*(..))", returning = "returnValue")
	public void handlerLogAfter(JoinPoint point, Object returnValue) {
		RequestAttributes request = RequestContextHolder.getRequestAttributes();
		String handlerMethodName = ClassUtils.getShortClassName(point.getSignature().getDeclaringTypeName()) + "."
				+ point.getSignature().getName();
		long start = (Long) request.getAttribute(Constants.ELONG_REQUEST_STARTTIME, ServletRequestAttributes.SCOPE_REQUEST);
		String useTime = String.valueOf(System.currentTimeMillis() - start);

		BigLog log = new BigLog();
		log.setAppName("controller");
		log.setTraceId((String) (request.getAttribute(Constants.ELONG_REQUEST_TRACEID, ServletRequestAttributes.SCOPE_REQUEST)));
		log.setSpan("1.1");
		log.setServiceName(handlerMethodName);
		log.setElapsedTime(useTime);
		ResponseEntity<byte[]> resp = (ResponseEntity<byte[]>) returnValue;
		log.setResponseBody(new String(resp.getBody()));
		log.setUserLogType("111");
		logger.info(log.toString());
	}
	
	@AfterThrowing(pointcut = "execution(public * com.elong.nb.controller..*..*(..))", throwing = "throwing")
	public void handlerLogThrowing(JoinPoint point, Object throwing) {
		RequestAttributes request = RequestContextHolder.getRequestAttributes();
		String handlerMethodName = ClassUtils.getShortClassName(point.getSignature().getDeclaringTypeName()) + "."
				+ point.getSignature().getName();
		long start = (Long) request.getAttribute(Constants.ELONG_REQUEST_STARTTIME, ServletRequestAttributes.SCOPE_REQUEST);
		String useTime = String.valueOf(System.currentTimeMillis() - start);

		BigLog log = new BigLog();
		log.setAppName("controller");
		log.setTraceId((String) (request.getAttribute(Constants.ELONG_REQUEST_TRACEID, ServletRequestAttributes.SCOPE_REQUEST)));
		log.setSpan("1.1");
		log.setServiceName(handlerMethodName);
		log.setElapsedTime(useTime);
		Throwable t = (Throwable) throwing;
		log.setResponseBody(t.getMessage());
		log.setException(t);
		log.setBusinessErrorCode("1");
		log.setUserLogType("111");
		logger.info(log.toString());
	}

	// ----------------------------controller end-------------------------------
}
