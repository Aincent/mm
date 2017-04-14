package com.pay.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class PageData<E> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8601072796551873058L;
	private int pageCount;
	private List<E> list;		// 列表
	
	public PageData(){
		
	}

	public PageData(int pageCount,List<E> list){
		this.pageCount = pageCount;
		this.list = list;
	}
	
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public JSONObject toJSON(){
		JSONObject json = new JSONObject();
		json.put("pageCount", pageCount);
		if(list==null)
			json.put("list", new ArrayList<E>());
		else
			json.put("list", list);
		return json;
	}
	
	public String toJSONString(){
		return toJSON().toJSONString();
	}
	
}
