package com.pay.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.pay.entity.OrderInfo;
import com.pay.service.OrderService;
import com.pay.util.PageData;
import com.pay.util.SessionKey;

@Controller
@RequestMapping("/orderInfo")
public class OrderInfoController extends BasicController {
	@Autowired
	private OrderService orderInfoService;

	@RequestMapping("/list")
	public void findList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String channelname = request.getParameter("channelname");
		String bigchannel = request.getParameter("bigchannel");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String orderstate = request.getParameter("orderstate");
		String userChannel=(String) request.getSession().getAttribute(SessionKey.CHANNEL);
		String userType=(String) request.getSession().getAttribute(SessionKey.USERTYPE);
		String channelType = (String)request.getSession().getAttribute(SessionKey.CHANNELTYPE);
		String payType = (String) request.getSession().getAttribute(SessionKey.PAYTYTPE);

		System.out.println("page"+page+"pageSize"+pageSize);
		System.out.println("startTime"+startTime+"endTime"+endTime);
		if (orderstate!=null){
			orderstate=orderstate.trim();
		}
		System.out.println("orderstate"+orderstate);
		PageData<OrderInfo> pageData = orderInfoService.getOrderInfoList(page, pageSize, channelname,startTime,endTime,userChannel,orderstate,userType,bigchannel,channelType,payType);
		writeJSON(response, pageData.toJSON());
	}


}
