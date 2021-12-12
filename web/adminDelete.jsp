<%--
  Created by IntelliJ IDEA.
  User: 86137
  Date: 2021/11/20
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品删改界面</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<%--    <script type="text/javascript">--%>
<%--        $(function () {--%>
<%--                var submit=$("#submit");--%>
<%--                submit.click(function () {--%>
<%--                    var bookId=$("#bookId").val();--%>
<%--                    var bookName=$("#bookName").val();--%>
<%--                    var price=$("#price").val();--%>
<%--                    $.ajax({--%>
<%--                        url:'/book?method=modifyBook',--%>
<%--                        data:{bookId:bookId,bookName:bookName,price:price},--%>
<%--                        type:'get',--%>
<%--                        datatype:'text',--%>
<%--                        success:function (data) {--%>
<%--                            alert(bookName+"信息修改成功");--%>
<%--                        }--%>
<%--                    })--%>
<%--                });--%>
<%--            }--%>
<%--        )--%>
<%--    </script>--%>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#next").click(function(){
                var pages = parseInt($("#pages").html());
                var page = parseInt($("#currentPage").html());
                if(page == pages){
                    return;
                }
                page++;
                location.href = "/admin?page="+page;
            })

            $("#previous").click(function () {
                var page = parseInt($("#currentPage").html());
                if(page == 1){
                    return;
                }
                page--;
                location.href = "/admin?page="+page;
            })

            $("#first").click(function () {
                location.href = "/admin?page=1";
            })

            $("#last").click(function(){
                var pages = parseInt($("#pages").html());
                location.href = "/admin?page="+pages;
            })
        })
    </script>
</head>
<body>
    <div>
        <a href="adminPage.jsp">管理员首页</a>
    </div>
    <table class="table" cellspacing="0">
        <tr>
            <td>编号</td>
            <td>图书名称</td>
            <td>定价</td>
            <td>商品修改</td>
            <td>商品清除</td>
        </tr>

        <c:forEach items="${list}" var="book">
            <tr>
                <form action="/book?method=modifyBook" method="post">
                    <td>
                        <input type="text" name="bookId" value="${book.id}" id="bookId" readonly/>
                    </td>
                    <td>
                        <input type="text"  name="bookName" value="${book.name}" id="bookName"/>
                    </td>
                    <td>
                        <input type="text" name="price" value="${book.price}" id="price"/>
                    </td>
                    <td>
                        <input class="btn" type="submit" id="submit" value="商品修改"/>
                    </td>
                    <td>
                        <a href="/book?method=bookDelete&bookId=${book.id}">商品清除</a>
                    </td>
                </form>
            </tr>
        </c:forEach>

    </table>

    <hr class="hr"/>
    <div id="pageControl">
        <div >每页<font id="dataPrePage">${dataPrePage }</font>条数据</div>
        <input type="button"  id="first" value="首页"></input>
        <input type="button"  id="previous" value="上一页"></input>
        <font id="currentPage">${currentPage }</font>/<font id="pages">${pages }</font>
        <input type="button"  id="next" value="下一页"></input>
        <input type="button"  id="last" value="最后一页"></input>
    </div>
</body>
</html>
