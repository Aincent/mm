package com.pay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class UrlConnect {
	public static String doConnection(String xmlInfo,String URL,int timeout,String charsetName){
		return doConnection(xmlInfo, URL, timeout, charsetName, 0);
	}
	public static String doConnection(String xmlInfo,String URL,int timeout,String charsetName,int tryTimes){
		return null;
	}
	
	public static String doConnectionCharset(String xmlInfo,String URL,int timeout,String charsetName,int tryTimes) throws UnsupportedEncodingException{
		return null;
	}

	
	 public static String doPost(String reqUrl, Map parameters,
	            String recvEncoding)
	    {
	        HttpURLConnection url_con = null;
	        String responseContent = null;
	        try
	        {
	            StringBuffer params = new StringBuffer();
	            for (Iterator iter = parameters.entrySet().iterator(); iter
	                    .hasNext();)
	            {
	                Entry element = (Entry) iter.next();
	                params.append(element.getKey().toString());
	                params.append("=");
	                params.append(URLEncoder.encode(element.getValue().toString(),"utf-8"));
	                params.append("&");
	            }

	            if (params.length() > 0)
	            {
	                params = params.deleteCharAt(params.length() - 1);
	            }

	            URL url = new URL(reqUrl);
	            url_con = (HttpURLConnection) url.openConnection();
	            url_con.setRequestMethod("POST");
	            // url_con.setConnectTimeout(5000);//（单位：毫秒）jdk
	            // 1.5换成这个,连接超时
	            // url_con.setReadTimeout(5000);//（单位：毫秒）jdk 1.5换成这个,读操作超时
	            url_con.setDoOutput(true);
	            byte[] b = params.toString().getBytes();
	            url_con.getOutputStream().write(b, 0, b.length);
	            url_con.getOutputStream().flush();
	            url_con.getOutputStream().close();

	            InputStream in = url_con.getInputStream();
	            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
	                    recvEncoding));
	            String tempLine = rd.readLine();
	            StringBuffer tempStr = new StringBuffer();
	            String crlf=System.getProperty("line.separator");
	            while (tempLine != null)
	            {
	                tempStr.append(tempLine);
	                tempStr.append(crlf);
	                tempLine = rd.readLine();
	            }
	            responseContent = tempStr.toString();
	            rd.close();
	            in.close();
	        }
	        catch (IOException e)
	        {
	            System.out.println("网络故障"+e);
	        }
	        finally
	        {
	            if (url_con != null)
	            {
	                url_con.disconnect();
	            }
	        }
	        return responseContent;
	    }
}
