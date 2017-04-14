<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			<div style="margin-top: 5px;">渠道维护列表</div>
		</div>
		<form>
			<div class="p">
				<div class="left">
					<div class="label">渠道添加：</div>
				</div>
				<div class="right">
					渠道名字：<input name="channelName" id="channelName" />  
					交易数量：<input name="channelCount" id="channelCount" />
					交易金额：<input name="channelAmount" id="channelAmount" />
					子交易数量：<input name="channelChilCount" id="channelChilCount"/>
					 <input class="commit"
						type="button" onclick="update();" value="提交" /> 
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
					<th>渠道名称</th>
					<th>数量</th>
					<th>金额</th>
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
		<td>{$T.Row.name}</td>
     	<td>{$T.Row.count}</td>
     	<td>{$T.Row.amount}</td>
     	<td>{$T.Row.createtimeStr}</td>
     </tr>
     {#/for}
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
		url : "channelInfo/list",
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
	$("#listTbody").setTemplateElement("Template-listTbody").processTemplate(data);
}

function update(){
	var channelname = $("#channelName").val();
	var channelcount = $("#channelCount").val();
	var channelamount = $("#channelAmount").val();
	var channelchilcount = $("#channelChilCount").val();
	if(channelname == ""){
		alert("请输入渠道名称");
		return false;
	}
	if(channelcount == ""){
		alert("请输入扣量笔数");
		return false;
	}
	if(channelamount == ""){
		alert("请输入最小扣量金额");
		return false;
	}
	if(channelchilcount == ""){
		alert("请输入子扣量笔数");
		return false;
	}
	
	$.ajax({
		type: "POST",
		url: "channelInfo/commitdata",
		data: {'channelName': channelname,'channelCount':channelcount,'channelAmount':channelamount,'channelChilCount':channelchilcount},
		dataType: "json",
		success: function(data){
			if(data>0){
				alert("更新成功!");
			}else if (data==-1){
			    alert("渠道名错误！");	
			}
			else{
				alert("更新出错!");
				return;
			}
		    pageBar.onLoad({param:"pageSize=20"});
		   }
		});
}
</script>
</html>