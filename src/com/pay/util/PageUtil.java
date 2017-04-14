package com.pay.util;

public class PageUtil {

	public static int getStart(int pageNumber,int pageSize){
		return (pageNumber-1)*pageSize;
	}
	
	public static int getTotalPage(int pageSize,int totalRecords){
		return (int)Math.ceil((double)totalRecords/(double)pageSize);
	}
}
