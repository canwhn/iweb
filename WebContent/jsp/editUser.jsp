<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>编辑用户</title>
    <script src="<%=basePath %>/js/jquery-1.12.0.min.js"></script>
    <script src="<%=basePath %>/js/jquery.json.js"></script>
	<script type="text/javascript">
	$.fn.serializeObject = function() {
		 var o = {};
		 var a = this.serializeArray();
		 $.each(a, function() {
		 if (o[this.name] !== undefined) {
		 if (!o[this.name].push) {
		 o[this.name] = [o[this.name]];
		}
		 o[this.name].push(this.value || '');
		 } else {
		 o[this.name] = this.value || '';
		}
		});
		 return o;
		};
		
	function updateUser(){
		var form = $("#userForm");
		debugger;
		form.attr("action", "<%=path%>/user/updateUser");
		form.submit();
	}
	function updateUser_bak(){
		//debugger;		
		//var user = $.toJSON($('#userForm').serializeObject());
	    //alert(user);
	    var msg = $('#userForm').serializeObject();
	    debugger;
	    alert(msg);

	    $.ajax({
	        type : 'POST',
	        contentType : 'application/json',
	        url : "<%=basePath%>user/updateUser",
	        data :msg,
	        dataType : 'json',
	        success : function(result){
	        }
		    ,
		    error : function(XMLHttpRequest, textStatus, errorThrown) {  
                alert("error");  
            } 
	        })
	}
</script>

  </head>
  
  <body>
    <h1>添加用户</h1>
	<form action="" name="userForm" id="userForm">
		<input type="hidden" name="id" value="${user.id }"/>
		姓名：<input type="text" name="userName" value="${user.userName }"/>
		年龄：<input type="text" name="age" value="${user.age }"/>
		<input type="button" value="编辑" onclick="updateUser()"/>
	</form>
  </body>
  
</html>
