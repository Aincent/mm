package com.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class BasicController {

	protected final void writeJSON(HttpServletResponse response,JSONObject json) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(json.toJSONString());
		pw.flush();
		pw.close();
	}
	
	protected final void writeJSONStr(HttpServletResponse response,String jsonStr) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(jsonStr);
		pw.flush();
		pw.close();
	}
	
	protected final void writeStr(HttpServletResponse response,String str) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(str);
		pw.flush();
		pw.close();
	}
	
	protected final void writeInt(HttpServletResponse response,int i) throws IOException{
		PrintWriter pw = response.getWriter();
		pw.print(i);
		pw.flush();
		pw.close();
	}
	
	protected final void writeDouble(HttpServletResponse response,double b) throws IOException{
		PrintWriter pw = response.getWriter();
		pw.print(b);
		pw.flush();
		pw.close();
	}
	
	protected final void writeDouble(HttpServletResponse response,float f) throws IOException{
		PrintWriter pw = response.getWriter();
		pw.print(f);
		pw.flush();
		pw.close();
	}
	
	protected final void writeDouble(HttpServletResponse response,short s) throws IOException{
		PrintWriter pw = response.getWriter();
		pw.print(s);
		pw.flush();
		pw.close();
	}
	
	protected final void writeChar(HttpServletResponse response,char c) throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(c);
		pw.flush();
		pw.close();
	}
}
