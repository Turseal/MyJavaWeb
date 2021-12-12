<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<link rel="icon" href="images/search.gif" type="img/x-ico" />
<link href="css/MyLogin.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
<script language="javascript" type="text/javascript">
	function check(){
		var val=document.getElementById("username").value;
		if(val.length==0)
		{
			alert("用户名不能为空");
			return false;
		}
	}
</script>
<c:if test="${requestScope.loginState== 'fail'}">
	<script language="javascript" type="text/javascript">
		alert("用户名密码错误");
	</script>
</c:if>


<body>
	<div id="top"></div>
	<div id="main">
		<img src="images/MyLogin.png" id="main_bg"/>
		<div id="login_block">
			<div>
				<h1>登录界面</h1>
			</div>
			<form action="/login" method="post" id="loginForm">
				<table border="0">
					<tr>
						<td class="title">用户名:</td>
						<td class="input">
							<input type="text" name="username" id="username" class="login_input"/>
						</td>
					</tr>
					<tr>
						<td class="title">密码:</td>
						<td class="input">
							<input type="password" name="password" id="password" class="login_input"/>
						</td>
					</tr>
					<tr>
						<td class="title">用户类型:</td>
						<td class="input">
							<input type="radio" name="type" value="user" checked="checked"/>&nbsp;&nbsp;用户&nbsp;&nbsp;
							<input type="radio" name="type" value="admin"/>&nbsp;&nbsp;管理员
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="btn" type="submit" value="登录" onclick="check()"/>
							<div class="btn" id="reset">重&nbsp;&nbsp;置</div>
							<a class="btn" href="register.jsp">注册</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="footer">
		<div class="foot_content">
			CopyRight &copy; 2021 SCUTWXH 前途有限公司
		</div>
		<div class="foot_content">
			版权所有 WXH
		</div>
	</div>
</body>
</html>