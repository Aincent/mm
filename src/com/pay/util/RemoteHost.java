package com.pay.util;

import javax.servlet.http.HttpServletRequest;

public class RemoteHost {
	/**
	 * 获取请求的真实IP
	 * @param request
	 * @return
	 */
	public static String getRemoteHost1(HttpServletRequest request){
	    return  request.getRemoteHost();
	}
	/**
	 * 获取请求的真实IP
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

}
