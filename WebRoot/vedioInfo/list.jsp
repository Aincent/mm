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
			<div style="margin-top: 5px;">信息列表</div>
		</div>
		<form>
			<div class="p">
				<div class="left">
					<div class="label">选择视频页面：</div>
				</div>
				<div class="right" >
				<tr>
				<td><select name=vtype id="vtype">
					<option value="1">首页Banner</option>
					<option value="2">首页列表</option>
					<option value="0">热播列表</option>
				</select></td>
				</tr>
					<input class="commit" type="button" onclick="search();"
						value="确定" /> 
				</div>
			</div>
			<div class="p">
			<div class="left">
					<div class="label">添加内容：</div>
				</div>
				<div class="right">
					 <input class="commit" type="button"
						onclick="turnPage('vedioInfo/detail.jsp');" value="添加信息" />
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
					<th>名字</th>
					<th>视频时长</th>
					<th>视频类型</th>
					<th>是否是VIP</th>
					<th>位置</th>
					<th>操作</th>
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
		<td>{$T.Row.title}</td>
     	<td>{$T.Row.times}</td>
     	<td>{$T.Row.type}</td>
     	<td>{#if $T.Row.vip==0}VIP{#/if}
	     	{#if $T.Row.vip==1}非VIP{#/if}</td>
     	<td>{$T.Row.postion}</td>
     	<td><a onclick="updateInfo('{$T.Row.title}','{$T.Row.times}','{$T.Row.cover}','{$T.Row.type}','{$T.Row.vedio}','{$T.Row.newvedio}','{$T.Row.vtype}','{$T.Row.postion}','{$T.Row.id}');">修改信息</a>|
     	<a onclick="deleteInfo('{$T.Row.id}')">删除</a></td>
     </tr>
     {#/for}
</textarea>
</body>
<script type="text/javascript">
var pageBar;
$(function(){
	var vtype = $('#vtype').val();
	pageBar = $("#pageBar").myPagination({
	currPage : 1,
	pageSize : 10,
	ajax : {
		on : true,
		url : "vedio/list",
		dataType : 'json',
		param : "pageSize=20&vtype="+vtype,
		callback : appListAjaxCallBack,
		ajaxStart: function() {
        	ZENG.msgbox.show(" 正在加载中，请稍后...", 6, 10000);
    	},
    	ajaxStop: function() {
           setTimeout(function() {
            ZENG.msgbox.hide();
            }, 300);
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
function search(){
	var param = "pageSize=20";
	var vtype = $("#vtype").val();
	param+="&vtype="+vtype;
	pageBar.onLoad({param:param});
}
function updateInfo(title,times,imgurl,type,vedio,newvedio,vtype,postion,id){
	var detailJson = {'title':title,'times':times,'imgurl':imgurl,
			'type':type,'vedio':vedio,'newvedio':newvedio,'vtype':vtype,'postion':postion,'id':id}
	turnPagePost('vedioInfo/detail.jsp',detailJson);
}
function deleteInfo(id){
	$.ajax({
		type: "POST",
		url: "vedioInfo/delete",
		data: {id: id},
		dataType: "json",
		success: function(data){
			if(data>0){
				alert("删除成功!");
			}else{
				alert("删除出错!");
				return;
			}
		    pageBar.onLoad({param:"pageSize=20"});
		   }
		});
}
</script>
</html>