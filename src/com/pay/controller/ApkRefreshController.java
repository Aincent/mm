package com.pay.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pay.entity.ChannelRefresh;
import com.pay.entity.ChannelSet;
import com.pay.service.ChannelRefreshService;
import com.pay.util.PageData;




@Controller
@RequestMapping("/apkRefreshInfo")
public class ApkRefreshController extends BasicController {
	@Autowired
	private ChannelRefreshService channelRefreshService;

//	@RequestMapping("/refresh")
//	public void refreshApk(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String channelName = request.getParameter("channel");
//		if (channelName==null || channelName.equals("")){
//			List<ChannelRefresh> channelList=channelRefreshService.getChannelRefreshList();
//			for(ChannelRefresh tmp:channelList)  
//	        {  
//	            //System.out.println(tmp);  
//				ApkBuild.build(tmp);
//	        }  
//		}
//		writeInt(response, 1);
//
//	}
	
	@RequestMapping("/list")
	public void findList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		PageData<ChannelRefresh> pageData = channelRefreshService.getChannelRefreshList(page, pageSize);
		writeJSON(response, pageData.toJSON());
	}

	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("channelName");
		int flag = channelRefreshService.deleteChannelRefresh(name);
		writeInt(response, flag);
	}

	@RequestMapping("/commitdata")
	public void commitdata(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("channelName");
		String type = request.getParameter("channelType");
		String owner = request.getParameter("owner");
		String big = request.getParameter("bigChannel");

		ChannelRefresh channelRefresh = new ChannelRefresh();
		channelRefresh.setName(name);
		channelRefresh.setType(type);
		channelRefresh.setOwner(owner);
		channelRefresh.setBig(big);
		channelRefresh.setStatus("1");

		int flag = channelRefreshService.insertChannelRefresh(channelRefresh);
		writeInt(response, flag);
	}


//	public void refreshSchedule()throws Exception {
//		try{
//			List<ChannelRefresh> channelList=channelRefreshService.getChannelRefreshList();
//			for(ChannelRefresh tmp:channelList)  
//	        {  
//				ApkBuild.build(tmp);
//	        }  
//		}catch (Exception e) {  
//            e.printStackTrace();  
//        } 
//	}

	
}
