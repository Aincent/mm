<%@ page language="java" pageEncoding="UTF-8"%>
<%
String title = request.getParameter("title");
String times = request.getParameter("times");
String imgurl = request.getParameter("imgurl");
String type = request.getParameter("type");
String vedio = request.getParameter("vedio");
String newvedio = request.getParameter("newvedio");
String vtype = request.getParameter("vtype");
String postion = request.getParameter("postion");
String id = request.getParameter("id");
%>
<!-- 此处不能简写为<script type="text/javascript" src=".."/> -->

<script type="text/javascript">
function ajaxFileUpload(id){
    //开始上传文件时显示一个图片,文件上传完成将图片隐藏
    //$("#loading").ajaxStart(function(){$(this).show();}).ajaxComplete(function(){$(this).hide();});
    //执行上传文件操作的函数
    $.ajaxFileUpload({
        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
        url:'vedio/uploadimg',
        secureuri:false,                       //是否启用安全提交,默认为false
        fileElementId:id+'myBlogImage',           //文件选择框的id属性
        fileSize:2048000,                       //文件大小限制（字节）2MB
        dataType:'text',                       //服务器返回的格式,可以是json或xml等
        success:function(data, status){			//服务器响应成功时的处理函数
            if(data.indexOf("#_#_S")>0){
        		var x = data.indexOf("#_#_S");
        		var y = data.indexOf("#_#_E");
        		var upUrl= data.substring(x + 5, y);
        		//alert(upUrl);
                $("img[id='"+id+"uploadImage']").attr("src", upUrl);
               // var rid = randomString(10);
               // $('#result').append('<img onclick=\'removeimg("'+rid+'");\' id="'+rid+'" style="width: 120px;height: 120px;" src="'+upUrl+'">');
               	//var imgurls = $('#imgurl').val();
                //imgurls += upUrl + "#_#";
                $('#'+id+'imgurl').val(upUrl);
            }else{
                alert('图片上传失败，请重试！！1');
            }
        },
        error:function(data, status, e){ //服务器响应失败时的处理函数
            alert('图片上传失败，请重试！！2');
        }
    });
}
</script>

<form>
	<table>
		<thead>
			<tr>
				<th>添加信息</th>
			</tr>
		</thead>
		<tr>
			<td align="right">名字：</td>
			<td><input type="text" name="title" id="title" /></td>
		</tr>
		<tr>
			<td align="right">视频时长：</td>
			<td><input type="text" name="times" id="times" value="" /></td>
		</tr>
		<tr>
			<td align="right">上传封面图片：</td>
			<td>
				<img id="coveruploadImage" src="images/zanwu.jpg" style="width: 75px;height: 75px;">
				<input type="file" id="covermyBlogImage" name="myfiles" accept="image/*"/>
				<input type="button" value="上传图片" onclick="ajaxFileUpload('cover')"/>
			</td>
		</tr>
		<tr>
			<td align="right">封面图片地址：</td>
			<td>
				<input type="text" name="imgurl" value="" id="coverimgurl" style="width: 500px">
				<input type="hidden" name="id" value="0" id="id">
			</td>
		</tr>
		<tr>
			<td align="right">视频类型：</td>
			<td><input type="text" name="type" id="type" value="" ></td>
		</tr>
		<tr>
			<td align="right">视频地址：</td>
			<td><input type="text" name="vedio" id="vedio" value="" /></td>
		</tr>
		<tr>
			<td align="right">VIP地址：</td>
			<td><input type="text" name="newvedio" id="newvedio" value="" /></td>
		</tr>
		<tr>
			<td align="right">视频位置：</td>
			<td><select name="vtype" id="vtype">
					<option value="1">首页Banner</option>
					<option value="2">首页列表</option>
					<option value="0">热播列表</option>
			</select></td>
		</tr>
		<tr>
			<td align="right">位置：</td>
			<td><input type="text" name="postion" id="postion" value="99999999" /></td>
		</tr>
	</table>
	<div class="right">
		<input class="commit" type="button" onclick="commitdata();" value="提交" />
	</div>
</form>

<script type="text/javascript">
	function commitdata(){
		var title = $('#title').val();
		var times = $('#times').val();
		var imgurl = $('#coverimgurl').val();
		var type = $('#type').val();
		var vedio = $('#vedio').val();
		var vtype = $('#vtype').val();
		var postion = $('#postion').val();
		var id = $('#id').val();
		var newvedio = $('#newvedio').val();
		$.ajax({
			type: "POST",
			url: "vedio/commitdata",
			data: { "title": title, "times": times, "imgurl": imgurl,
				"type": type, "vedio": vedio, "vtype": vtype, "postion": postion, "id": id,"newvedio": newvedio},
			dataType: "json",
			success: function(data){
				if(data>0){
					alert("编辑成功!");
					turnPage('vedioInfo/list.jsp');
				}else{
					alert("编辑数据出错!");
					return;
				}
			}
		});
	}
	function editInfoInit(id){
		if((id!=null) && (id!="null") && (id!="")){
			var title = '<%= title%>';
			var times = '<%= times%>';
			var imgurl = '<%= imgurl%>';
			var type = '<%= type%>';
			var vedio = '<%= vedio%>';
			var newvedio = '<%= newvedio%>';
			var vtype = '<%= vtype%>';
			var postion = '<%= postion%>';
			var id = <%= id%>;
			$('#title').val(title);
			$('#times').val(times);
			$('#coverimgurl').val(imgurl);
			$('#type').val(type);
			$('#vedio').val(vedio);
			$('#vtype').val(vtype);
			$('#postion').val(postion);
			$('#id').val(id);
			$('#newvedio').val(newvedio);
			showImg('coveruploadImage',imgurl);
		}
	}
	editInfoInit("<%=id%>");
	function showImg(id, url) {
		$('#'+id).attr('src',url);
	}
	function removeimg(id) {
		var imgurl = $('#' + id).attr('src');
		var imgurls = $('#imgurl').val().replace(imgurl + "#_#", "");
		$('#imgurl').val(imgurls);
		$('#' + id).remove();
	}
</script>


<!--
AjaxFileUpload简介
官网:http://phpletter.com/Our-Projects/AjaxFileUpload/
简介:jQuery插件AjaxFileUpload能够实现无刷新上传文件,并且简单易用,它的使用人数很多,非常值得推荐
注意:引入js的顺序(它依赖于jQuery)和页面中并无表单(只是在按钮点击的时候触发ajaxFileUpload()方法)
常见错误及解决方案如下
1)SyntaxError: missing ; before statement
  --检查URL路径是否可以访问
2)SyntaxError: syntax error
  --检查处理提交操作的JSP文件是否存在语法错误
3)SyntaxError: invalid property id
  --检查属性ID是否存在
4)SyntaxError: missing } in XML expression
  --检查文件域名称是否一致或不存在
5)其它自定义错误
  --可使用变量$error直接打印的方法检查各参数是否正确,比起上面这些无效的错误提示还是方便很多
 -->