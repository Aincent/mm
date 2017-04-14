package com.pay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pay.entity.OnlineInfo;

public interface OnlineInfoMapper {
	
	public int saveOnlineInfo(OnlineInfo onlineinfo);
	
	public int getOnlineInfoExist(@Param("id")String id);
	
	public List<OnlineInfo> getOnlineInfo(@Param("id")String id);
	
	public int updateOnlineInfo(@Param("id")String id,@Param("text1")String text1,@Param("text2")String text2,@Param("text3")String text3);
}
