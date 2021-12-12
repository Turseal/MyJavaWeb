<%--
  Created by IntelliJ IDEA.
  User: 86137
  Date: 2021/11/20
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>付款页面</title>
</head>
<body>
    <div>这里有一个付款二维码</div>
    <div>
        总计：${requestScope.totalPrice}元
    </div>
    <a href="/book?method=success">点击付款</a>
    <a href="/book?method=findMyCart">回到购物车</a>
</body>
</html>
