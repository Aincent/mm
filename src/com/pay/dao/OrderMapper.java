package com.pay.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.entity.OrderInfo;

public interface OrderMapper {
	
	public int saveOrder(Map<String, String> map);
	public int saveOrderJP(Map<String, String> map);
	public int saveOrdergongdapeng(Map<String, String> map);
	public int saveOrderhzyl(Map<String, String> map);
	public int saveOrderJuBao(Map<String, String> map);
	public int saveNewOrderJP(Map<String, String> map);
	public int saveOrderWeiQianBao(Map<String, String> map);
	public int saveOrderShunShouYun(Map<String, String> map);
	
	public int getOrderInfoExist(@Param("transid")String transid);
	public int getOrderInfoExistByMemberID(@Param("memberid")String memberID);
	
	public List<Map<String,Object>> getOrderInfo(Map<String, Object> map);
	
	public List<OrderInfo> getOrderInfoList(@Param("startNum") int startNum, @Param("pageSize") int pageSize,
			@Param("channelname") String channelname,@Param("startTime") String startTime,@Param("endTime") String endTime
			,@Param("userChannel") String userChannel,@Param("orderState") String orderState,@Param("userType") String userType,
			@Param("bigChannel") String bigChannel,@Param("channelType") String channelType,@Param("payType") String payType);
	
	public int getOrderInfoListCount(@Param("channelname") String channelname,@Param("startTime") String startTime,@Param("endTime") String endTime
			,@Param("userChannel") String userChannel,@Param("orderState") String orderState,@Param("userType") String userType,
			@Param("bigChannel") String bigChannel,@Param("channelType") String channelType,@Param("payType") String payType) ;

}
