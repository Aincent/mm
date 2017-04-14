package com.pay.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.dao.OrderMapper;
import com.pay.entity.OrderInfo;
import com.pay.util.PageData;
import com.pay.util.PageUtil;

@Service
public class OrderService {
	
	@Autowired
	private OrderMapper orderMapper;	
	
	public int saveOrder(Map<String, String> map){
		return orderMapper.saveOrder(map);
	}
	
	public int saveOrderJP(Map<String, String> map)
	{
		return orderMapper.saveOrderJP(map);
	}
	
	public int saveOrdergongdapeng(Map<String, String> map) {
		// TODO Auto-generated method stub
		return orderMapper.saveOrdergongdapeng(map);
	}

	
	public int saveOrderhzyl(Map<String, String> map) {
		// TODO Auto-generated method stub
		return orderMapper.saveOrderhzyl(map);
	}
	
	public int saveOrderJuBao(Map<String, String> map){
		return orderMapper.saveOrderJuBao(map);
	}
	
	public int saveNewOrderJP(Map<String, String> map) {
		// TODO Auto-generated method stub
		return orderMapper.saveNewOrderJP(map);
	}
	
	
	public int saveOrderWeiQianBao(Map<String, String> map) {
		// TODO Auto-generated method stub
		return orderMapper.saveOrderWeiQianBao(map);
	}
	
	public int saveOrderShunShouYun(Map<String, String> map) {
		// TODO Auto-generated method stub
		return orderMapper.saveOrderShunShouYun(map);
	}
	
	public int getOrderByTransID(String transID){
		if(orderMapper.getOrderInfoExist(transID) > 0)
		{
			return -1;
		}
		
		return 0;
	}
	
	public int getOrderByMemberID(String memberID){
		if(orderMapper.getOrderInfoExistByMemberID(memberID) > 0)
		{
			return -1;
		}
		
		return 0;
	}
	
	
	public List<Map<String, Object>> getOrderList(Map<String, Object> map) {
		return orderMapper.getOrderInfo(map);
	}
	
	public PageData<OrderInfo> getOrderInfoList(int pageNum, int pageSize, String channelname,String startTime,String endTime,String userChannel,String orderState,String userType,String bigChannel,String channelType,String payType){
		System.out.println("getOrderInfoList orderState "+orderState+" !!!!");
		PageData<OrderInfo> pageData = new PageData<OrderInfo>();
		int count = orderMapper.getOrderInfoListCount(channelname,startTime,endTime,userChannel,orderState,userType,bigChannel,channelType,payType);
		if(count == 0){
			return pageData;
		}
		int startNum = PageUtil.getStart(pageNum, pageSize);
		List<OrderInfo> list = orderMapper.getOrderInfoList(startNum, pageSize, channelname,startTime,endTime,userChannel,orderState,userType,bigChannel,channelType,payType);
		int pageCount = PageUtil.getTotalPage(pageSize, count);
		pageData.setList(list);
		pageData.setPageCount(pageCount);
		return pageData;
	}
}
