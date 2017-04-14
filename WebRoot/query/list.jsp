<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<style type="text/css">
/* box */
.box {
	position: absolute;
	width: 600px;
	left: 50%;
	height: auto;
	z-index: 100;
	background-color: #fff;
	border: 1px #ddd solid;
	padding: 1px;
}

.box h2 {
	height: 25px;
	font-size: 14px;
	background-color: #637F86;
	position: relative;
	padding-left: 10px;
	line-height: 25px;
	color: #fff;
}

.box h2 a {
	position: absolute;
	right: 5px;
	font-size: 12px;
	color: #fff;
}

.box .list {
	padding: 10px;
}

.box .list li {
	height: 24px;
	line-height: 24px;
}

.box .list li span {
	margin: 0 5px 0 0;
	font-family: "宋体";
	font-size: 12px;
	font-weight: 400;
	color: #ddd;
}

.showbtn {
	font: bold 24px '微软雅黑';
}

#bg {
	background-color: #666;
	position: absolute;
	z-index: 99;
	left: 0;
	top: 0;
	display: none;
	width: 100%;
	height: 100%;
	opacity: 0.5;
	filter: alpha(opacity = 50);
	-moz-opacity: 0.5;
}
</style>
</head>
<body>
	<div class="searchForm">
		<div class="title">
			<img src="images/table_2_12s_3.gif" width="9" height="9"
				style="float: left; margin: 7px 6px 9px 6px;">
			<div style="float: right;">
				<a href="login.jsp">退出</a>
			</div>
			<div style="margin-top: 5px;">付费信息列表</div>
		</div>
		<form>
			<div class="p">
				<div class="left"><div class="label">数据时间：</div></div>
				<div class="right">
					开始时间：<input type="text" name="startTime"  readonly="readonly"/>
					结束时间: <input type="text" name="endTime"  readonly="readonly"/>
					<%if (channel.equals("admin") || (usertype.equals("2")) ) {%>
						支付状态：<select name="orderstate" id="orderstate"><option value="0">All</option><option value="1">1</option><option value="2">2</option>
						<%if (channel.equals("admin") ) {%>
						<option value="3">3</option>
						<%} %>
						</select>

					<%}
					else if((usertype.equals("5"))) {
					%>
						<input type="hidden" name="orderstate" id="orderstate" value="3">
					<%} else {%>
						<input type="hidden" name="orderstate" id="orderstate" value="1">
				   <%} %>
				</div>
				
			</div>
			<div class="p">
				<div class="left">
					<div class="label">搜索：</div>
				</div>
				<div class="right">
					渠道名字：<input name="channelname" id="channelname" />  
					大渠道：<input name="bigchannel" id="bigchannel" />   
				</div>
			</div>
			<div class="p">
				<div class="left">
					<div class="label">搜索：</div>
					
				</div>
				<div class="right">
					每页行数 ：<input name="pagesize" id="pagesize" value="20" />
					 <input class="commit"
						type="button" onclick="search();" value="开始查询" /> 
				</div>
			</div>
		</form>
	</div>
	<div class="table">
		<img src="images/table_2_12s_3.gif" width="9" height="9"
			style="float: left; margin: 7px 6px 9px 6px;">
		<div class="title">信息列表</div>
		<table>
			<thead>
				<tr>
					<th>OrderId</th>
					<th>类型</th>
					<th>是否支付</th>
					<th>金额</th>
					<th>渠道</th>
					<th>商务</th>
					<th>大渠道</th>
					<th>时间</th>
				</tr>
			</thead>
			<tbody id="listTbody">
			</tbody>
		</table>
		<div id="pageBar"></div>
	</div>
	<textarea id="Template-listTbody" style="display: none;">
   {#foreach $T.list as Row}
	<tr>
		<td>{$T.Row.orderId}</td>
     	<td>
     		{#if $T.Row.orderChannel==1}支付宝{#/if}
     		{#if $T.Row.orderChannel==2}微信{#/if}
     	</td>
     	<td>{$T.Row.orderState}</td>
     	<td>{$T.Row.price}</td>
     	<td>{$T.Row.userName}</td>
     	<td>{$T.Row.owner}</td>
     	<td>{$T.Row.big}</td>
     	<td>{$T.Row.createtime}</td>
     </tr>
     {#/for}
     <tr>
		<td>合计  {$T.totalRow}条</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
     	<td>{$T.totalPrice}</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
     </tr>
</textarea>
</body>
<script>
$(function(){
    $("input[name=startTime]").click(function(){
    	WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});
    });
    $("input[name=endTime]").click(function(){
    	WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});
    });
});

var pageBar;
$(function(){
	pageBar = $("#pageBar").myPagination({
	currPage : 1,
	pageSize : 20,
	ajax : {
		on : true,
		url : "orderInfo/list",
		dataType : 'json',
		param : "pageSize=20",
		callback : appListAjaxCallBack,
		ajaxStart: function() {
        	ZENG.msgbox.show(" 正在加载中，请稍后...", 6, 10000);
    	},
    	ajaxStop: function() {
           setTimeout(function() {
            ZENG.msgbox.hide();
            }, 500);
        },
	},
	panel: {
            tipInfo_on: true,
            tipInfo: '&nbsp;&nbsp;跳{input}/{sumPage}页&nbsp;',
            tipInfo_css: {
              width: '23px',
              height: "16px",
              border: "2px solid #f0f0f0",
              padding: "0 0 0 5px",
              margin: "0 5px 0 5px",
              color: "#48b9ef"
            }
    }
	});
});
function appListAjaxCallBack(data) {
	var totalPrice=0;
	var rownum=0;
	$.each(data.list,function(i,e){
		rownum+=1;
		totalPrice+= Number(e.price);

	});
	data.totalPrice=totalPrice.toFixed(2);
	data.totalRow=rownum;
	$("#listTbody").setTemplateElement("Template-listTbody").processTemplate(data);
}
function search(){
	var param = "";
	var pagesize = $("#pagesize").val();
	param+="&pageSize="+pagesize;
	var channelname = $("#channelname").val();
	param+="&channelname="+channelname;
	var bigchannel = $("#bigchannel").val();
	param+="&bigchannel="+bigchannel;
	var startTime = $("input[name=startTime]").val();
	param+="&startTime="+startTime;
	var endTime = $("input[name=endTime]").val();
	param+="&endTime="+endTime;
	var orderState = $("#orderstate").val();
	param+="&orderstate="+orderState;
	pageBar.onLoad({param:param});
}

</script>
</html>