<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../web/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../js/jquery-1.12.0.min.js"></script>
<script src="../web/bootstrap/js/bootstrap.min.js"></script>
<script src="../js/bootstrap-paginator.js"></script>
<script src="../js/jquery.json.js"></script>
<script src="../js/userList.js"></script>
<title>Canwhn's Studio</title>
</head>
<body>
	<input id="web_url" type="hidden"  value="<%=basePath %>">
	<!-- 
	<div class="row">
	  <div class="col-md-1">.col-md-1</div>
	  <div class="col-md-1">.col-md-1</div>
	  <div class="col-md-1">.col-md-1</div>
	  <div class="col-md-1">.col-md-1</div>
	  <div class="col-md-1">.col-md-1</div>
	  <div class="col-md-1">.col-md-1</div>
	  <div class="col-md-1">.col-md-1</div>
	  <div class="col-md-1">.col-md-1</div>
	  <div class="col-md-1">.col-md-1</div>
	  <div class="col-md-1">.col-md-1</div>
	  <div class="col-md-1">.col-md-1</div>
	  <div class="col-md-1">.col-md-1</div>
	</div>
	<div class="row">
	  <div class="col-md-8">.col-md-8</div>
	  <div class="col-md-4">.col-md-4</div>
	</div>
	<div class="row">
	  <div class="col-md-4">.col-md-4</div>
	  <div class="col-md-4">.col-md-4</div>
	  <div class="col-md-4">.col-md-4</div>
	</div>
	<div class="row">
	  <div class="col-md-6">.col-md-6</div>
	  <div class="col-md-6">.col-md-6</div>
	</div>
	<div class="row">
	  <div class="col-md-12">
	  	<table class="table">
	      <caption>Optional table caption.</caption>
	      <thead>
	        <tr>
	          <th>#</th>
	          <th>First Name</th>
	          <th>Last Name</th>
	          <th>Username</th>
	        </tr>
	      </thead>
	      <tbody>
	        <tr>
	          <th scope="row">1</th>
	          <td>Mark</td>
	          <td>Otto</td>
	          <td>@mdo</td>
	        </tr>
	        <tr>
	          <th scope="row">2</th>
	          <td>Jacob</td>
	          <td>Thornton</td>
	          <td>@fat</td>
	        </tr>
	        <tr>
	          <th scope="row">3</th>
	          <td>Larry</td>
	          <td>the Bird</td>
	          <td>@twitter</td>
	        </tr>
	      </tbody>
	    </table>	
	  </div>
	</div>
	-->
	<div class="row" id='myForm'>
		<form id="userForm">
		姓名：<input type="text" name="userName" value="1">
		年龄：<input type="text" name="age" value="2">
		<input type="button" value="添加" onclick="addUser()">
	</form>
	</div>
	<div class="row">
	  <div class="col-md-12" id="userTable">
	  		<div id="list"></div>
        	<ul id="example" style="float:right"></ul>
	  </div>
	</div>
</body>
</html>