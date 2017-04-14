<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="javascript" src="js/My97DatePicker/WdatePicker.js"></script>
</head>
 
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
			<div style="margin-top: 5px;">收入查询</div>
		</div>
		<form>
			<div class="p">
				<div class="left">
					<div class="label">选择条件：</div>
				</div>
				<div class="right">
					<form>
						开始时间：<input type="text" name="startTime" id="startTime" readonly="readonly"/>
						结束时间: <input type="text" name="endTime" id="endTime" readonly="readonly"/> 
						<input class="commit" type="button" onclick="search();" value="查询" />
					</form>
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
					<th>渠道</th>
					<th>收入</th>
				</tr>
			</thead>
			<tbody id="listTbody">
			</tbody>
		</table>
	</div>
	<textarea id="Template-listTbody" style="display: none;">
   {#foreach $T.list as Row}
	<tr>
     	<td>{$T.Row.additionalinfo}</td>
     	<td>{$T.Row.money}</td>
     </tr>
     {#/for}
     <tr>
		<th>合计</th>
		<th>{$T.total}</th>
     </tr>
</textarea>
</body>

<script type="text/javascript">
function showDetail(channelid){
	var detailJson = {'channelid':channelid}
	turnPagePost('statistics/income_detail.jsp', detailJson);
}
$(function(){
	 $("input[name=startTime]").click(function(){
	    WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyyMMdd',alwaysUseStartDate:true});
	 });
	 $("input[name=endTime]").click(function(){
	   	WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyyMMdd',alwaysUseStartDate:true});
	 });
	 $("input[name=startTime]").val(GetCurrentTime(0));
	 $("input[name=endTime]").val(GetCurrentTime(0));
});
var pageBar;
$(function(){
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var price = $("#price").val();
	var orderchannel = $("#orderchannel").val();
	pageBar = $("#pageBar").myPagination({
	currPage : 1,
	pageSize : 10,
	ajax : {
		on : true,
		url : "order/list",
		dataType : 'json',
		param : "pageSize=20&startTime="+startTime+"&endTime="+endTime,
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
	var money = 0;
	$.each(data.list,function(i,e){
		money +=e.money;
	});
	data.total = money;
	$("#listTbody").setTemplateElement("Template-listTbody").processTemplate(data);
}
function search(){
	var param = "pageSize=20";
	var startTime = $("#startTime").val();
	param+="&startTime="+startTime;
	var endTime = $("#endTime").val();
	param+="&endTime="+endTime;
	pageBar.onLoad({param:param});
}
function deleteInfo(id){
	$.ajax({
		type: "POST",
		url: "appkeys/delete",
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
function GetCurrentTime(flag) {
    var currentTime = "";
    var myDate = new Date();
    var year = myDate.getFullYear();
    var month = parseInt(myDate.getMonth().toString()) + 1; //month是从0开始计数的，因此要 + 1
    if (month < 10) {
        month = "0" + month.toString();
    }
    var date = myDate.getDate();
    if (date < 10) {
        date = "0" + date.toString();
    }
    var hour = myDate.getHours();
    if (hour < 10) {
        hour = "0" + hour.toString();
    }
    var minute = myDate.getMinutes();
    if (minute < 10) {
        minute = "0" + minute.toString();
    }
    var second = myDate.getSeconds();
    if (second < 10) {
        second = "0" + second.toString();
    }
    if(flag == "0")
    {
        currentTime = year.toString() + month.toString() + date.toString() ; //返回时间的数字组合
    }
    else if(flag == "1")
    {
        currentTime = year.toString() + "年" + month.toString() + "月" + date.toString() + "日 "; //以时间格式返回
    }
    return currentTime;
}
</script>
 <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>
</html>