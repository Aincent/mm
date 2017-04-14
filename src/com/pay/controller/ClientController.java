package com.pay.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pay.entity.VedioInfo;
import com.pay.service.VedioInfoService;

@Controller
@RequestMapping("/client")
public class ClientController extends BasicController{
	
	@Autowired
	private VedioInfoService vedioInfoService;
	
	@RequestMapping("/mainVideoList")
	public void mainVideoList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<VedioInfo> list = vedioInfoService.getVedioInfo(2, 0, 0);
		List<VedioInfo> bannerList = vedioInfoService.getVedioInfo(1, 0, 0);
		JSONArray data = new JSONArray();
		JSONObject mianfeishikan = new JSONObject();
		mianfeishikan.put("cname", "精彩推荐");
		mianfeishikan.put("data", list.subList(0, 6));
		JSONObject rihanshiping = new JSONObject();
		rihanshiping.put("cname", "日韩视频");
		rihanshiping.put("data", list.subList(6, 12));
		JSONObject luanlunshunv = new JSONObject();
		luanlunshunv.put("cname", "伦理熟女");
		luanlunshunv.put("data", list.subList(12, 18));
		JSONObject zhifushisheng = new JSONObject();
		zhifushisheng.put("cname", "制服师生");
		zhifushisheng.put("data", list.subList(18, 90));
		data.add(mianfeishikan);
		data.add(rihanshiping);
		data.add(luanlunshunv);
		data.add(zhifushisheng);
		JSONObject jo = new JSONObject();
		jo.put("code", "200");
		jo.put("data", data);
		jo.put("banner", bannerList);
		writeJSONStr(response, jo.toJSONString());
	}
	@RequestMapping("/videoList")
	public void videoList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		List<VedioInfo> list = vedioInfoService.getVedioInfo(0,pageNum, pageSize);
		JSONObject jo = new JSONObject();
		jo.put("code", "200");
		jo.put("data", list);
		writeJSONStr(response, jo.toJSONString());
	}
	@RequestMapping("/initInfo")
	public void initInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject jo = new JSONObject();
		jo.put("qq", "2146879162");
		jo.put("monthfee", 3); //49
		jo.put("permanentfee",2); //69
		jo.put("exitfee", 1);  //29
		jo.put("returnUrl", "http://120.76.103.51:8091/mm/order/jubaoCallBack");
		//startPageImg  http://120.76.103.51:8091/mm/order/orderCallBack
		writeJSONStr(response, jo.toJSONString());
	}
	
	@RequestMapping("/updateInfo")
	public void updateInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject jo = new JSONObject();
		jo.put("md5", "2146879162");
		jo.put("url", ""); //49
		writeJSONStr(response, jo.toJSONString());
	}
	
}
