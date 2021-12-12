<%--
  Created by IntelliJ IDEA.
  User: 86137
  Date: 2021/11/20
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            var submit=$("#submit");
            submit.click(function () {
                var bookId=$("#bookId").val();
                var bookName=$("#bookName").val();
                var price=$("#price").val();
                $.ajax({
                    url:'/book?method=addBook',
                    data:{bookId:bookId,bookName:bookName,price:price},
                    type:'post',
                    datatype:'text',
                    success:function (data) {
                        alert("success");
                    }
                })
            });
            }
        )
    </script>
    <title>增加商品界面</title>
</head>
<body>
    <div>
        <a href="adminPage.jsp">管理员首页</a>
    </div>
    <div>
        <table>
<%--            <tr>
                <td class="title">书号:</td>
                <td class="input">
                    <input type="text" name="bookId" id="bookId"/>
                </td>
            </tr>--%>
            <tr>
                <td class="title">书名:</td>
                <td class="input">
                    <input type="text" name="bookName" id="bookName"/>
                </td>
            </tr>
            <tr>
                <td class="title">价格:</td>
                <td class="input">
                    <input type="text" name="price" id="price"/>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input class="btn" type="button" id="submit" value="提交"/>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>
