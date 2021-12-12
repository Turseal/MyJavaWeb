<%--
  Created by IntelliJ IDEA.
  User: 86137
  Date: 2021/11/20
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>管理页面</title>
</head>
<body>
    <div>
        尊敬的管理员： ${sessionScope.admin.username} 欢迎来到管理界面 <a href="/logout">登录注销</a>
    </div>
    <div>
        管理员操作
    </div>
    <div>
        <a href="admin?method=findDelete&page=1">删除/修改商品</a>
    </div>
    <div>
        <a href="adminAdd.jsp">增加商品</a>
    </div>
    <div>
        <a href="/admin?method=adminSalesSituation">商品销售情况</a>
    </div>
    <div>
        <a href="/admin?method=PurchaseRecord">用户购买记录</a>
    </div>
    <div>
        <a href="/admin?method=BrowseRecord">用户浏览记录</a>
    </div>


</body>
</html>
