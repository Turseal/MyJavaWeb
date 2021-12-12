<%@ page import="com.wxh.entity.Book" %><%--
  Created by IntelliJ IDEA.
  User: 86137
  Date: 2021/11/19
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>书籍详情页</title>
</head>
<body>
    <div>
        <a href="/book?page=${requestScope.currentPage}">返回首页</a>
    </div>
    <%
        String Bpath="/bookImage/";
        Book book=(Book)request.getAttribute("book");
        if(book.getId().equals(1) || book.getId().equals(2)){
            //（只准备了两张封面）用book的id作为图片的名称
            Bpath+=book.getId()+".png";
        }
        else{
            Bpath+="book"+".png";
        }
        //将计算得到的图片路径放入request中
        request.setAttribute("Bpath",Bpath);
    %>
    <div>
        <img src=${Bpath}>
    </div>
    <div>
        <table>
            <tr>
                <th>书名</th>
                <th>价格</th>
                <th>操作</th>
            </tr>
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td><a href="/book?method=addCart&bookid=${book.id}">加入购物车</a></td>
            </tr>
        </table>
    </div>
</body>
</html>
