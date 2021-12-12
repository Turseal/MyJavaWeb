<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 86137
  Date: 2021/11/21
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户购买记录查询页面</title>
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


        <c:forEach items="${purchaseRecordList}" var="purchaseRecord">
            <tr>
                <td>${purchaseRecord.user.username}</td>
                <td>${purchaseRecord.book.id}</td>
                <td>${purchaseRecord.book.name}</td>
                <td>${purchaseRecord.book.price}</td>
                <td>${purchaseRecord.date}</td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
