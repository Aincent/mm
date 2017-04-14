<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    
    <title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/global.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/myPagination/page.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/msgbox/msgbox.css" />
	<script language="javascript" src="<%=basePath %>js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/ajaxfileupload.js"></script>
	<script language="javascript" src="<%=basePath %>js/jtemplates.js"></script>
	<script language="javascript" src="<%=basePath %>js/myPagination/jquery.myPagination6.0.js"></script>
	<script language="javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
	<script language="javascript" src="<%=basePath %>js/msgbox/msgbox.js"></script>
	<script src="js/highcharts.js"></script>
<style>
body{
	padding:0;
	margin:0;
	font-size:12px;
}
#wrap{
	margin:1px 1px 1px 1px;
}
#wrap-main{
	clear:both;
	background-color:#F6F6F6;
	padding:3px;
	border:1px solid #82BECE;
}
#wrap-content{
	height:100%; 
 	margin-left:173px;
  	background-color:#D2EAF1;
  	padding:3px;
}
</style>
  </head>
  
  <body>
  	<div id="wrap">
  		<div id="wrap-head"></div>
  		<div id="wrap-main">
  			<%@ include file="menu.jsp" %>
  			<div id="wrap-content">
  			<%@ include file="welcome.jsp" %>
  			</div>
  			<div style="clear:both"></div>
  		</div>
  		<%@ include file="foot.jsp" %>
  	</div>
  </body>
<script type="text/javascript">
function turnPage(url){
	$("#wrap-content").load(url);
}
function turnPagePost(url,detailJson){
	$("#wrap-content").load(url, detailJson);
}
function randomString(len) {
	len = len || 32;
	var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
	var maxPos = $chars.length;
	var pwd = '';
	for (i = 0; i < len; i++) {
		pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
	}
	return pwd;
}
</script>
</html>
