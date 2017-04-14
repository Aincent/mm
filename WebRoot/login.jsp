<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language="javascript" src="js/jquery-1.11.1.min.js"></script>
	<style>
	#login{
		width:500px;
		height:200px;
		position:absolute;
		top:50%;
		left:50%;
		margin-left:-125px;
		margin-top:-100px;
	}
	#login p{
		margin:0 0 5px 0;
		height:30px;
		padding:0px;
	}
	#login input{
		maring:0;
		padding:0;
		vertical-align:middle;
	}
	#login label{
		display:block;
		float:left;
	}
	#captcha{
		margin:0;
		padding:0;
		width:60px;
		height:20px;
		align:right;
	}
	.error{
		color:red;
		margin-left:10px;
		font-size:12px;
	}
	</style>
	<script>
	function refreshCap(){
		$("#captcha").attr("src","captcha?r="+Math.random());
	}

	$(function(){
		$("input[name=username]").blur(function(){
			$(this).next(".error").remove();
			var username = $(this).val();
			if(username==""){
				$(this).after("<span class='error'>用户名不能为空</span>");
			}
		}).keyup(function(){
	        $(this).triggerHandler("blur");
	    }).focus(function(){
	        $(this).triggerHandler("blur");
	    }); 
		
		$("input[name=password]").blur(function(){
			$(this).next(".error").remove();
			var password = $(this).val();
			if(password==""){
				$(this).after("<span class='error'>密码不能为空</span>");
			}
		}).keyup(function(){
	        $(this).triggerHandler("blur");
	    }).focus(function(){
	        $(this).triggerHandler("blur");
	    });
		
		$("input[name=captcha]").blur(function(){
			$("#captcha").next(".error").remove();
			var captcha = $(this).val();
			if(captcha==""){
				$("#captcha").after("<span class='error'>验证码不能为空</span>");
			}
		}).keyup(function(){
	        $(this).triggerHandler("blur");
	    }).focus(function(){
	        $(this).triggerHandler("blur");
	    });
	    
	    function verify(){
	    	$("input[name=username]").triggerHandler("blur");
			$("input[name=password]").triggerHandler("blur");
			$("input[name=captcha]").triggerHandler("blur");
			var length = $(".error").length;
			if(length!=0)
	    		return false;
	    	return true;
	    }
	    
	    $("#commit").click(function(){
			if(!verify())
				return;
			var username = $("input[name=username]").val();
			var password = $("input[name=password]").val();
			var captcha = $("input[name=captcha]").val(); 
			$.get("user/login",
				{username:username,
				password:password,
				captcha:captcha},
				function(data){
					if(data==0)
						window.location="index.jsp";
					else if(data==1)
						$("input[name=username]").after("<span class='error'>用户名或密码错误</span>");
					else if(data==2)
						$("#captcha").after("<span class='error'>验证码错误</span>");
				}
			); 
	    });
	});
	
	</script>
  </head>
  
  <body>
    <div id="wrap">
    	<div id="login">
    		<p><label>用户名：</label><input type="text" name="username" /></p>
    		<p><label>密&nbsp;码：</label><input type="password" name="password" /></p>
    		<p><button id="commit" onclick="login();">登录</button></p>
    	</div>
    </div>
  </body>
</html>
