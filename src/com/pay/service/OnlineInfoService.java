package com.pay.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.dao.OnlineInfoMapper;
import com.pay.entity.OnlineInfo;

@Service
public class OnlineInfoService {
	
	@Resource
	private OnlineInfoMapper onlineInfoDao;
	
	public int saveOnlineInfo(OnlineInfo onlineinfo){
		// TODO Auto-generated method stub
		
		return onlineInfoDao.saveOnlineInfo(onlineinfo);
	}
	
	public OnlineInfo getOnlineInfo(String id) {
		// TODO Auto-generated method stub
		if (onlineInfoDao.getOnlineInfoExist(id)>0)
		{
			List<OnlineInfo> list=onlineInfoDao.getOnlineInfo(id);
			return list.get(0);
		}
		else
		{
			return null;
		}
	}
	
	public int updateOnlineInfo(String id,String text1,String text2,String text3){
		// TODO Auto-generated method stub
		
		return onlineInfoDao.updateOnlineInfo(id,text1,text2,text3);
	}
}
