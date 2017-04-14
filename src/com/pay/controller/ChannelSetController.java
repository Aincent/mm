package com.pay.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pay.entity.ChannelSet;
import com.pay.service.ChannelSetService;
import com.pay.util.PageData;
import com.pay.util.SessionKey;


@Controller
@RequestMapping("/channelInfo")
public class ChannelSetController extends BasicController {
	@Autowired
	private ChannelSetService channelSetService;

	@RequestMapping("/list")
	public void findList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		String userChannel=(String) request.getSession().getAttribute(SessionKey.CHANNEL);
		String userType=(String) request.getSession().getAttribute(SessionKey.USERTYPE);
		System.out.println("userChannel"+userChannel+" userType"+userType);
		PageData<ChannelSet> pageData = channelSetService.getChannelSetList(page, pageSize,userChannel,userType);
		writeJSON(response, pageData.toJSON());
	}

	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("channelName");
		int flag = channelSetService.deleteChannelSet(name);
		writeInt(response, flag);
	}

	@RequestMapping("/commitdata")
	public void commitdata(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("channelName");
		int count = Integer.parseInt(request.getParameter("channelCount"));
		int amount = Integer.parseInt(request.getParameter("channelAmount"));
		int chilcount = Integer.parseInt(request.getParameter("channelChilCount"));
		
		String userChannel=(String) request.getSession().getAttribute(SessionKey.CHANNEL);
		String userType=(String) request.getSession().getAttribute(SessionKey.USERTYPE);
		
		ChannelSet channelSet = new ChannelSet();
		channelSet.setName(name);
		channelSet.setCount(count);
		channelSet.setAmount(amount);
		channelSet.setChilcount(chilcount);

		System.out.println(name);
		System.out.println(count);
		System.out.println(amount);
		int flag = channelSetService.insertChannelSet(channelSet,userChannel,userType);
		writeInt(response, flag);
	}

	
}
