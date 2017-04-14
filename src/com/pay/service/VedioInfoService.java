package com.pay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.dao.VedioInfoMapper;
import com.pay.entity.VedioInfo;
import com.pay.util.PageData;
import com.pay.util.PageUtil;

@Service
public class VedioInfoService {
	
	@Autowired
	private VedioInfoMapper vedioInfoMapper;
	
	public List<VedioInfo> getVedioInfo(int vtype, int pageNum, int pageSize){
		int startNum = PageUtil.getStart(pageNum, pageSize);
		return vedioInfoMapper.getVedioInfo(vtype, startNum, pageSize);
	}
	public PageData<VedioInfo> getVedioInfo_1(int vtype, int pageNum, int pageSize){
		PageData<VedioInfo> pageData = new PageData<VedioInfo>();
		int count = vedioInfoMapper.getVedioInfoCount(vtype);
		if(count == 0){
			
		}
		int startNum = PageUtil.getStart(pageNum, pageSize);
		int pageCount = PageUtil.getTotalPage(pageSize, count);
		List<VedioInfo> list = vedioInfoMapper.getVedioInfo_1(vtype, startNum, pageSize);
		pageData.setList(list);
		pageData.setPageCount(pageCount);
		return pageData;
	}
	public void resettingPostion(int id, int imgtype, int postition) {
		// TODO Auto-generated method stub
		
	}
	public int updateVedioInfo(VedioInfo vedioInfo) {
		return vedioInfoMapper.updateVedioInfo(vedioInfo);
	}
	public int insertVedioInfo(VedioInfo vedioInfo) {
		// TODO Auto-generated method stub
		return vedioInfoMapper.insertVedioInfo(vedioInfo);
	}
}
