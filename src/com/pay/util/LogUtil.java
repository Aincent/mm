package com.pay.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 用来处理所有日志
 * @author moli
 *
 */
public class LogUtil {
	private final static Logger errorlogger = Logger.getLogger("errorlogger");
	private final static Logger logger = Logger.getLogger(LogUtil.class);
	public static void requestLog(HttpServletRequest request){
		requestLog(request, logger);
	}
	/**
	 * 记录请求日志
	 * @param request
	 */
	public static void requestLog(HttpServletRequest request, Logger logger){
		String ip = getRemoteHost(request);
		StringBuffer sb = new StringBuffer(200);
		sb.append(ip).append("|").append(request.getRequestURI()).append("?");
		Map<String, String[]> parameterMap = request.getParameterMap();
		for(Entry<String, String[]> entry : parameterMap.entrySet()){
			String parameterName = entry.getKey();
			String[] parameterValue = entry.getValue();
			for(String s : parameterValue){
				sb.append(parameterName).append("=").append(s).append("&");
			}
		}
		logger.error(sb.toString());
	}
	/**
	 * 记录保存数据库失败的数据
	 * @param dateStr
	 * @param logger
	 */
	public static void dataStrLog(String dateStr, Logger logger){
		logger.error(dateStr);
	}
	/**
	 * 记录所有异常日志
	 * @param t
	 */
	public static void exceptionLog(Throwable t){
		errorlogger.error(exceptionToString(t));
	}
	
	/**
	 * 获取请求IP
	 * @param request
	 * @return
	 */
	public static String getRemoteHost(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}
		request.getRemoteHost();
		String ipstr = ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
		ipstr = getIp(ipstr);
		return ipstr;
	}
	private static String getIp(String pString) {
		String regexString = ".*(\\d{3}(\\.\\d{1,3}){3}).*";
		String regexString2 = ".*(\\d{2}(\\.\\d{1,3}){3}).*";
		String IPString = pString.replaceAll(regexString, "$1");
		if (IPString.indexOf(".") > 3)
			IPString = pString.replaceAll(regexString2, "$1");
		return  IPString;
	}
	public static String exceptionToString(Throwable t){
		if(t == null)
			return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try{
			t.printStackTrace(new PrintStream(baos));
		}finally{
			try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//获取错误字符串
		return baos.toString();
	}

}
