package com.pay.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.entity.ChannelRefresh;
import com.pay.entity.ChannelSet;
import com.pay.util.PageData;
import com.pay.util.PageUtil;
import com.pay.dao.ChannelRefreshMapper;

@Service
public class ChannelRefreshService {
	@Resource
	private ChannelRefreshMapper channelRefreshDao;

	public PageData<ChannelRefresh> getChannelRefreshList(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageData<ChannelRefresh> pageData = new PageData<ChannelRefresh>();
		int count = channelRefreshDao.getChannelRefreshListCount();
		if(count == 0){
			return pageData;
		}
		int startNum = PageUtil.getStart(pageNum, pageSize);
		List<ChannelRefresh> list = channelRefreshDao.getChannelRefreshList(startNum, pageSize);
		int pageCount = PageUtil.getTotalPage(pageSize, count);
		pageData.setList(list);
		pageData.setPageCount(pageCount);
		return pageData;
		
	}
	
	public ChannelRefresh getChannelRefresh(String channelName) {
		// TODO Auto-generated method stub
		if (channelRefreshDao.getChannelRefreshExist(channelName)>0)
		{
			List<ChannelRefresh> list=channelRefreshDao.getChannelRefresh(channelName);
			return list.get(0);
		}
		else
		{
			return null;
		}
		
		
	}
	
	public int insertChannelRefresh(ChannelRefresh channelRefresh) {
		// TODO Auto-generated method stub
		int count = channelRefreshDao.getChannelRefreshExist(channelRefresh.getName());
		if (count>0)
		{
			 return channelRefreshDao.updateChannelRefresh(channelRefresh);
			
		}
		return channelRefreshDao.insertChannelRefresh(channelRefresh);
	}
	
	public int updateChannelRefresh(ChannelRefresh channelRefresh) {
		return channelRefreshDao.updateChannelRefresh(channelRefresh);
	}
	
	public int deleteChannelRefresh(String name) {
		// TODO Auto-generated method stub
		return channelRefreshDao.deleteChannelRefresh(name);
	}
}
