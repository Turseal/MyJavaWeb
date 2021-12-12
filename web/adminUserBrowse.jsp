<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 86137
  Date: 2021/11/21
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户浏览记录</title>
</head>
<body>
<div>
    <a href="adminPage.jsp">管理员首页</a>
</div>
<table class="table" cellspacing="0">
    <tr>
        <td>用户名&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>书籍编号&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>书名&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>定价&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>日期&nbsp;&nbsp;&nbsp;&nbsp;</td>
    </tr>


    <c:forEach items="${browseRecordList}" var="browseRecord">
        <tr>
            <td>${browseRecord.user.username}</td>
            <td>${browseRecord.book.id}</td>
            <td>${browseRecord.book.name}</td>
            <td>${browseRecord.book.price}</td>
            <td>${browseRecord.date}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
