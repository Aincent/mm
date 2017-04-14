<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String channel = (String)session.getAttribute("CHANNEL");
if (channel==null){
	channel="NULL";
}

String usertype = (String)session.getAttribute("USERTYPE");
if (usertype==null){
	usertype="1";
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
#wrap-menu{
	width:164px;
	padding:3px 3px 0 3px;
	background-color:#D2EAF1;
	height:100%;
	float:left;
}
#wrap-menu .subject{
	width:162px;
	padding:1px;
	background-color:#637F86;
	border-bottom:3px solid #D2EAF1;
}
#wrap-menu .subject .title{
	height:22px;
	background-color:#EEF7FA;
	font-family: "宋体";
	font-size: 12px;
	font-weight: bold;
	color: #1B3441;
	margin-bottom:1px;
}
#wrap-menu .subject .list{
	background-color: #FFFFFF;
	padding-left:5px;
	padding-right:5px;
	padding-top:5px;
}
#wrap-menu .subject .list input[type=button]{
	background-color: #1B5FA9;
	font-family: "宋体";
	font-size: 12px;
	line-height: normal;
	font-weight: normal;
	color: #FFFFFF;
	height: 20px;
	border-top-width: 2px;
	border-right-width: 2px;
	border-bottom-width: 2px;
	border-left-width: 2px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #3888E0;
	border-right-color: #0E3561;
	border-bottom-color: #0E3561;
	border-left-color: #3888E0;
	width:134px;
	margin-bottom:5px;
}
</style>
</head>
<body>
<div id="wrap-menu">
	<div class="subject">
		<%if (channel.equals("admin")) {%>
		<div class="title">
			<img class="switch" src="images/table_2_1_3.gif" width="9px" height="9px" style="float:left;margin:6px 5px 7px 6px;" />
			<span style="float:left;margin:4px 5px 6px 0;">功能列表</span>
			<img src="images/table_1_3.gif" width="32" height="22" style="float:right;"/>
		</div>
		<div class="list">
			<img src="images/help_96_32.gif" width="13" height="13" style="cursor:help;float:right;margin-top:3px;"/>
			<input type="button" value="视频编辑" onclick="turnPage('vedioInfo/list.jsp');"/>
		</div>
		<div class="list">
			<img src="images/help_96_32.gif" width="13" height="13" style="cursor:help;float:right;margin-top:3px;"/>
			<input type="button" value="收入查询" onclick="turnPage('statistics/income.jsp');"/>
		</div>
		<%} %>
		<div class="title">
			<img class="switch" src="images/table_2_1_3.gif" width="9px" height="9px" style="float:left;margin:6px 5px 7px 6px;" />
			<span style="float:left;margin:4px 5px 6px 0;">数据统计</span>
			<img src="images/table_1_3.gif" width="32" height="22" style="float:right;"/>
		</div>
		
		<div class="list">
			<img src="images/help_96_32.gif" width="13" height="13" style="cursor:help;float:right;margin-top:3px;"/>
			<input type="button" value="明细查询" onclick="turnPage('query/list.jsp');"/>
		</div>
		<%if (channel.equals("admin")) {%>
		<div class="list">
			<img src="images/help_96_32.gif" width="13" height="13" style="cursor:help;float:right;margin-top:3px;"/>
			<input type="button" value="渠道维护" onclick="turnPage('setting/list.jsp');"/>
		</div>
		<div class="list">
			<img src="images/help_96_32.gif" width="13" height="13" style="cursor:help;float:right;margin-top:3px;"/>
			<input type="button" value="渠道刷新" onclick="turnPage('setting/refreshList.jsp');"/>
		</div>
		<%} %>
	</div>
</div>
</body>
</html>