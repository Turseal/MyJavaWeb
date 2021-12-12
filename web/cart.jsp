<%--
  Created by IntelliJ IDEA.
  User: 86137
  Date: 2021/11/19
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>购物车</title>
</head>
<body>
    <div>
        <a href="/book?page=1">商品首页</a> <a href="/book?method=payment">商品结算</a>
    </div>
    <table class="table">
        <tr>
            <td>编号</td>
            <td>图书名称</td>
            <td>定价(元)</td>
            <td>操作</td>
        </tr>

        <c:forEach items="${cartlist}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>
                    <a href="/book?method=cartDelete&bookId=${book.id}">删除</a>
                </td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
