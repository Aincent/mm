package com.pay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pay.entity.ChannelRefresh;
import com.pay.entity.ChannelSet;

public interface ChannelRefreshMapper {
	public List<ChannelRefresh> getChannelRefreshList(@Param("startNum")int startNum,
			@Param("pageSize")int pageSize);
	
	public int getChannelRefreshListCount();
	
	public List<ChannelRefresh> getChannelRefresh(@Param("channelName")String name);
	
	public int getChannelRefreshExist(@Param("channelName")String name);
	
	public int insertChannelRefresh(@Param("channelRefresh")ChannelRefresh channelRefresh);
	
	public int updateChannelRefresh(@Param("channelRefresh")ChannelRefresh channelRefresh);
	
	public int deleteChannelRefresh(@Param("channelName")String name);
}
