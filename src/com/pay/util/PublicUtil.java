package com.pay.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSONObject;

public class PublicUtil {
	
	/**
	 * 替换 String 类型  null "null" "NULL" 
	 * @param source
	 * @return
	 */
	public static String replaceNull(String source) {
		if (source == null || source.equalsIgnoreCase("null")) {
			return "";
		} else {
			return source;
		}
	}
	/**
	 * 替换 Object 类型  null to ""
	 * @param source
	 * @return
	 */
	public static String replaceNull2String(Object obj) {
		String str = "";
		if(obj != null){
			str = PublicUtil.replaceNull(obj.toString());
		}
		return str;
	}
	
	/**
	 * 去手机号码86
	 * @param src
	 * @return
	 */
	public static String getPhone(String src) {
		src = replaceNull(src);
		if (src.startsWith("+86")) {
			return src.substring(3);
		} else if (src.startsWith("86")) {
			return src.substring(2);
		} else if (src.startsWith("%2B86")) {
			return src.substring(5);
		} else {
			return src;
		}
	}
	public synchronized static String getOrderNo() {
		String seqid = Integer.toString(SequenceGenerator.nextSequence());
		if (seqid.length() == 1)
			seqid = "00" + seqid;
		else if (seqid.length() == 2)
			seqid = "0" + seqid;
		int random  = new Random().nextInt(100);
		String tmp = "";
		if(random<10){
			tmp = "0" + random;
		}else{
			tmp = random + "";
		}
		return new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()) + seqid+tmp;
	}
	/** 
     * @description 实现将一个List转化为Map<Key,List>的样式 
     * @param 某个对象的List 
     * @return Map<Key,List> 
     */  
    public Map<String, Map<String,Object>> list4Map(List<Map<String,Object>> list,String k1,String k2,String k3) {  
  
        Map<String, Map<String,Object>> map = new HashMap<String, Map<String,Object>>();  
        
        if ((list != null) && (list.size() != 0)) {  
            for (Map<String,Object> m : list) {  
            	String key1 = m.get(k1).toString();
            	String key2 = m.get(k2).toString();
            	String key3 = m.get(k3).toString();
            	m.remove(k1);m.remove(k2);m.remove(k3);
                map.put(key1+key2+key3, m);
            }  
        }  
        return map;  
    }
	/** 
     * @description 实现将一个List转化为Map<Key,List>的样式 
     * @param 某个对象的List 
     * @return Map<Key,List> 
     */  
    public Map<String, Map<String,Object>> list4Map(List<Map<String,Object>> list,String k1,String k2) {  
  
        Map<String, Map<String,Object>> map = new HashMap<String, Map<String,Object>>();  
        
        if ((list != null) && (list.size() != 0)) {  
            for (Map<String,Object> m : list) {  
            	String key1 = m.get(k1).toString();
            	String key2 = m.get(k2).toString();
            	m.remove(k1);m.remove(k2);
                map.put(key1+key2, m); 
            }  
        }  
        return map;  
    }
    /** 
     * @description 实现将一个List转化为Map<Key,List>的样式 
     * @param 某个对象的List 
     * @return Map<Key,List> 
     */  
    public Map<String, Map<String,Object>> list4Map(List<Map<String,Object>> list,String k) {  
  
        Map<String, Map<String,Object>> map = new HashMap<String, Map<String,Object>>();  
        
        if ((list != null) && (list.size() != 0)) {  
            for (Map<String,Object> m : list) {  
            	String key = m.get(k).toString();
            	m.remove(k);
                map.put(key, m); 
            }  
        }  
        return map;  
    }
    public static String  getErrorMsg(int errorCode,String errorInfo){
    	JSONObject json = new JSONObject();
    	json.put("code", errorCode);
    	json.put("msg", errorInfo);
		return json.toString();
    }
    
    public static String exception(Throwable t){
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
	    String exc = baos.toString();
	    //截取前350 个字节
	    if(exc.length() > 700){
	    	exc = exc.substring(0, 700);
	    }
	    return "异常时间：["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"]"+exc;
	}
    
    public static String exceptionAll(Throwable t){
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
	    String exc = baos.toString();
	    return "异常时间：["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"]"+exc;
	}
    /**
     * 
     * @return
     */
	public static void main(String[] args) throws UnsupportedEncodingException {
		int random  = new Random().nextInt(2);
		System.out.println(random);
		
	}
	
	public static String tt(String a){
		System.out.println("beforesleep："+a);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("behindsleep："+a);
		return a;
	}

}
