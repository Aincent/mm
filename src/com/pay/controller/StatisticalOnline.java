package com.pay.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pay.entity.OnlineInfo;
import com.pay.service.OnlineInfoService;


@Controller
@RequestMapping("/online")
public class StatisticalOnline extends BasicController {
	
	@Autowired
	private OnlineInfoService onlineInfoService;
	
	@RequestMapping("/commitdata")
	public void commitdata(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("commitdata");
		String id = request.getParameter("onlyid");
		String deviceonlyid = request.getParameter("_deviceOnlyId");
		String devicetype = request.getParameter("_deviceType");
		String packagename = request.getParameter("_packageName");
		String imei = request.getParameter("_imei");
		String devicemanufacturer = request.getParameter("_deviceManufacturer");
		String deviceid = request.getParameter("_deviceId");
		String osversion = request.getParameter("_osVersion");
		String imsi = request.getParameter("_imsi");
		String appversioncode = request.getParameter("_appVersionCode");
		String appversionname = request.getParameter("_appVersionName");
		String ourversion = request.getParameter("_ourVersion");
		String umengchannel = request.getParameter("UMENG_CHANNEL");
		String text1 = request.getParameter("text1");
		String text2 = request.getParameter("text2");
		String text3 = request.getParameter("text3");
		
		OnlineInfo info = new OnlineInfo();
		info.setId(id);
		info.setDeviceonlyid(deviceonlyid);
		info.setDeviceid(deviceid);
		info.setDevicetype(devicetype);
		info.setPackagename(packagename);
		info.setImei(imei);
		info.setDevicemanufacturer(devicemanufacturer);
		info.setDeviceid(deviceid);
		info.setOsversion(osversion);
		info.setImsi(imsi);
		info.setAppversioncode(appversioncode);
		info.setAppversionname(appversionname);
		info.setOurversion(ourversion);
		info.setUmengchannel(umengchannel);
		info.setText1(text1);
		info.setText2(text2);
		info.setText3(text3);
		
		int flag = 0;
		OnlineInfo oldinfo = onlineInfoService.getOnlineInfo(id);
		if(oldinfo  != null)
		{
			flag = onlineInfoService.updateOnlineInfo(id,text1,text2,text3);
		}
		else
		{
			flag = onlineInfoService.saveOnlineInfo(info);
		}
		
		writeInt(response, flag);
	}
}
