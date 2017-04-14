package com.pay.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChannelRefresh {
	private String name;
	private String type;
	private String owner;
	private String big;
	private String status;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getBig() {
		return big;
	}

	public void setBig(String big) {
		this.big = big;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
