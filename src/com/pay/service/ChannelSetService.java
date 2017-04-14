package com.pay.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.entity.ChannelSet;
import com.pay.util.PageData;
import com.pay.util.PageUtil;
import com.pay.dao.ChannelSetMapper;

@Service
public class ChannelSetService {
	@Resource
	private ChannelSetMapper channelSetDao;
	/**
	 * 获取数据列表
	 * 
	 * @param map
	 * @return
	 */
	public PageData<ChannelSet> getChannelSetList(int pageNum, int pageSize,String userChannel,String userType) {
		PageData<ChannelSet> pageData = new PageData<ChannelSet>();
		System.out.println("userChannel"+userChannel+" userType"+userType);
		int count = channelSetDao.getChannelSetListCount(userChannel,userType);
		if(count == 0){
			return pageData;
		}
		int startNum = PageUtil.getStart(pageNum, pageSize);
		List<ChannelSet> list = channelSetDao.getChannelSetList(startNum, pageSize,userChannel,userType);
		int pageCount = PageUtil.getTotalPage(pageSize, count);
		pageData.setList(list);
		pageData.setPageCount(pageCount);
		return pageData;
	}

	/**
	 * 添加信息
	 * 
	 * @param ChannelSet
	 * @return
	 */
	public int insertChannelSet(ChannelSet channelSet,String userChannel,String userType) {
		int flag = channelSetDao.getChannelExist(channelSet.getName(),userChannel,userType);
		if (flag==0){
			return -1;
		}
		
		int count = channelSetDao.getChannelSetCount(channelSet.getName());
		if (count==0)
		{
			return channelSetDao.insertChannelSet(channelSet);
		}
		else
		{
			return channelSetDao.updateChannelSet(channelSet);
		}
			
	}

	/**
	 * 更新信息
	 * 
	 * @param ChannelSet
	 * @return
	 */
	public int updateChannelSet(ChannelSet channelSet) {
		return channelSetDao.updateChannelSet(channelSet);
	}

	/**
	 * 删除信息
	 * 
	 * @param id
	 * @return
	 */
	public int deleteChannelSet(String name) {
		return channelSetDao.deleteChannelSet(name);
	}
}
