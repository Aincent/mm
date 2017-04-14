package com.pay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pay.entity.VedioInfo;

public interface VedioInfoMapper {
	
	public List<VedioInfo> getVedioInfo(@Param("vtype")int vtype, @Param("startNum")int startNum, @Param("pageSize")int pageSize);
	
	public List<VedioInfo> getVedioInfo_1(@Param("vtype")int vtype, @Param("startNum")int startNum, @Param("pageSize")int pageSize);
	
	public int getVedioInfoCount(@Param("vtype")int vtype);

	public int updateVedioInfo(VedioInfo vedioInfo);

	public int insertVedioInfo(VedioInfo vedioInfo);
	
}
