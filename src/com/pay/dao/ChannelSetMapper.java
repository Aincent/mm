package com.pay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pay.entity.ChannelSet;


public interface ChannelSetMapper {
	/**
	 * 分页信息
	 * @param startNum
	 * @param pageSize
	 * @return
	 */
	public List<ChannelSet> getChannelSetList(@Param("startNum")int startNum,
			@Param("pageSize")int pageSize,@Param("userChannel")String userChannel,@Param("userType")String userType);
	/**
	 * 数量
	 * @return
	 */
	public int getChannelSetListCount(@Param("userChannel")String userChannel,@Param("userType")String userType);
	
	public int getChannelSetCount(@Param("channelName")String name);
	/**
	 * 添加信息
	 * @param vedioInfo
	 * @return
	 */
	public int insertChannelSet(@Param("channelSet")ChannelSet channelSet);
	/**
	 * 更新信息
	 * @param vedioInfo
	 * @return
	 */
	public int updateChannelSet(@Param("channelSet")ChannelSet channelSet);
	/**
	 * 删除信息
	 * @param id
	 * @return
	 */
	public int deleteChannelSet(@Param("channelName")String name);
	
	public int getChannelExist(@Param("channelName")String name,@Param("userChannel")String userChannel,@Param("userType")String userType);
}
