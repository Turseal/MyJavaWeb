<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/index.css" type="text/css" rel="stylesheet">
	 
  	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
  	<script type="text/javascript">
		$(function(){
			$("#next").click(function(){
				//分页功能的核心，维护一个page数据，以此为参数向下查询，得到分页信息
				var pages = parseInt($("#pages").html());
				var page = parseInt($("#currentPage").html());
				if(page == pages){
					//如果已经是最后一页，不对page进行改变
					return;
				}
				//如果还不是，则+1
				page++;
				//复用代码，BookServlet中的商品信息浏览模块，通过page控制展示信息
				location.href = "/book?page="+page;
			})

			$("#previous").click(function () {
				var page = parseInt($("#currentPage").html());
				if(page == 1){
					return;
				}
				page--;
				location.href = "/book?page="+page;
			})

			$("#first").click(function () {
				location.href = "/book?page=1";
			})

			$("#last").click(function(){
				var pages = parseInt($("#pages").html());
				location.href = "/book?page="+pages;
			})
		})
	</script>
  </head>
  
  <body>
  <%@ include file="top.jsp" %>

  	<div id="main">
		<div class="navigation">
				当前位置:&nbsp;&nbsp;<a href="/book?page=1">首页</a>
				<div id="readerBlock">欢迎回来&nbsp;:<a href="/book?method=findMyCart">${sessionScope.user.username}</a>&nbsp;<a href="/logout">注销</a></div>
		</div>
		<%
			String searchWord=(String) request.getAttribute("searchWord");
			if (searchWord==null){
				searchWord="请输入图书名称";
			}
			request.setAttribute("searchWord",searchWord);
		%>

		<div>
			<form action="/book?method=search" method="post">
				<span>搜索框:</span>
				<input type="text" name="search_name" id="search_name" value="${requestScope.searchWord}"/>
				<input class="btn" type="submit"  value="搜索"/>
			</form>
		</div>
		<div class="img_block">
			<img src="images/main_booksort.gif" class="img" />
		</div>
		
		<table class="table" cellspacing="0">
			<tr>
				<td>编号</td>
				<td>图书名称</td>
				<td>价格</td>
				<td>操作</td>
				<td>商品详情</td>

			</tr>

			<c:forEach items="${list}" var="book">
				<tr>
					<td>${book.id}</td>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>
						<a href="/book?method=addCart&bookid=${book.id}">加入购物车</a>
					</td>
					<td>
						<a href="/book?method=bookInfo&bookid=${book.id}&currentPage=${currentPage}">商品详情</a>
					</td>
				</tr>
			</c:forEach>
			
		</table>
		<hr class="hr"/>
		<div id="pageControl">
			<div class="pageControl_item">每页<font id="dataPrePage">${dataPrePage }</font>条数据</div>
			<div class="pageControl_item" id="last">最后一页</div>
			<div class="pageControl_item" id="next">下一页</div>
			<div class="pageControl_item"><font id="currentPage">${currentPage }</font>/<font id="pages">${pages }</font></div>
			<div class="pageControl_item" id="previous">上一页</div>
			<div class="pageControl_item" id="first">首页</div>
		</div>
		
	</div>

  </body>
</html>
