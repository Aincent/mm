package com.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pay.entity.VedioInfo;
import com.pay.service.VedioInfoService;
import com.pay.util.ApkOSSUpload;
import com.pay.util.PageData;
import com.pay.util.TimeUtil;
import com.pay.util.UUIDUtil;

@Controller
@RequestMapping("/vedio")
public class VedioController extends BasicController{
	
	@Autowired
	private VedioInfoService vedioInfoService;
	
	@RequestMapping("/list")
	public void videoList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		System.out.println("list");
		int type = Integer.parseInt(request.getParameter("vtype"));
		int page = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		PageData<VedioInfo> pageData = vedioInfoService.getVedioInfo_1(type,page, pageSize);
		System.out.println(pageData.toJSONString());
		writeJSONStr(response, pageData.toJSONString());
	}
	
	@RequestMapping("/commitdata")
	public void commitdata(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("commitdata");
		String title = request.getParameter("title");
		String times = request.getParameter("times");
		String cover = request.getParameter("imgurl");
		String type = request.getParameter("type");
		String vedio = request.getParameter("vedio");
		String newvedio = request.getParameter("newvedio");
		String vtype =request.getParameter("vtype");
		String postion = request.getParameter("postion");
		int id = Integer.parseInt(request.getParameter("id"));
		VedioInfo vedioInfo = new VedioInfo();
		vedioInfo.setTitle(title);
		vedioInfo.setTimes(times);
		vedioInfo.setCover(cover);
		vedioInfo.setType(type);
		vedioInfo.setVedio(vedio);
		vedioInfo.setNewvedio(newvedio);
		vedioInfo.setVtype(vtype);
		vedioInfo.setPostion(postion);
		vedioInfo.setId(id);
		int flag = 0;
		if (id > 0) {
			//重置位置
//			vedioInfoService.resettingPostion(id, imgtype, postition);
			flag = vedioInfoService.updateVedioInfo(vedioInfo);
		} else {
			flag = vedioInfoService.insertVedioInfo(vedioInfo);
		}
		writeInt(response, flag);
	}
	
	
	@RequestMapping("/uploadimg")
	public void uploadimg(@RequestParam MultipartFile[] myfiles, HttpServletResponse response)
			throws IOException, FileUploadException {
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		MultipartFile imgFile = null;
		for (MultipartFile myfile : myfiles) {
			if (!myfile.isEmpty()) {
				imgFile = myfile;
			}
		}
		if (imgFile == null) {
			out.print("1`请选择文件后上传");
			out.flush();
			return;
		}
		try {
			String date = TimeUtil.getNowDay();
			// 上传图片
			String oldName = imgFile.getOriginalFilename();
			String exten = oldName.substring(oldName.lastIndexOf("."));
			String newName = "img_" + UUIDUtil.getUUID() + exten;
			String url = ApkOSSUpload.upAliYun(imgFile.getInputStream(), newName, "images/"+date+"/");
//			if (!FtpUtil.storeFile(ftpClient, newName, imgFile.getInputStream(), FtpConfig.videoImgPath + date + "/")) {
//				out.print("1`文件上传失败，请重试！！");
//				out.flush();
//				return;
//			}
//			out.print("#_#_S" + FtpConfig.resorceUrl + "/" + FtpConfig.videoImgPath + date + "/" + newName + "#_#_E");
			out.print("#_#_S" + url + "#_#_E");
			out.flush();
		} finally {
		}
	}
}
