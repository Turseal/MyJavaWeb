<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 86137
  Date: 2021/11/21
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品销售情况</title>
</head>
<body>
<div>
    <a href="adminPage.jsp">管理员首页</a>
</div>
<table class="table" >
    <tr>
        <td>编号&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>图书名称</td>
        <td>定价&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>销量&nbsp;&nbsp;&nbsp;&nbsp;</td>
    </tr>


    <c:forEach items="${saleList}" var="book">

        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.price}</td>
            <td>${book.sales}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
