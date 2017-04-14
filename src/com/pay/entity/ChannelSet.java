package com.pay.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChannelSet {
	private String name;
	private int count;
	private int amount;
	private int chilcount;
	private Date createtime;
	private Date updatetime;
	private String createtimeStr;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getCount(){
		return count;
	}
	
	public void setCount(int count){
		this.count=count;
	}
	
	public int getAmount(){
		return amount;
	}
	
	public void setAmount(int amount){
		this.amount=amount;
	}
	public int getChilcount() {
		return chilcount;
	}

	public void setChilcount(int chilcount) {
		this.chilcount = chilcount;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getCreatetimeStr() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createtime);
	}

	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}
}
