<%--
  Created by IntelliJ IDEA.
  User: 86137
  Date: 2021/11/17
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>注册</title>
</head>
<link rel="icon" href="images/search.gif" type="img/x-ico" />
<link href="css/MyLogin.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    $(function () {
        //得到“注册”按钮
        var register=$("#register");
        //点击“注册”按钮执行函数
        register.click(function(){
            // var username=$("#username").text();
            // var password=$("#password").text();
            // var re_password=$("#re_password").text();
            if($("#username").val()=='') {
                alert("用户名不能为空")
                return 0
            }
            if($("#password").val()=='') {
                alert("密码不能为空")
                return 0
            }
            $.ajax({
                //注册servlet的注释信息
                url:'/register',
                //用户的注册信息
                data:{username:$("#username").val(),password:$("#password").val(),re_password:$("#re_password").val(),email:$("#email").val()},
                type:'post',
                datatype:'text',
                success:function (data) {
                    if(data==="1"){
                        var username=$("#username");
                        alert("用户名已占用");

                    }
                    else if(data==="2"){
                        var re_password=$("#re_password");
                        alert("密码不一致");
                    }
                    else {
                        alert("success");
                    }
                }
            })
        })
    })
</script>
<body>
<div id="top"></div>
<div id="main">
    <img src="images/MyLogin.png" id="main_bg"/>
    <div id="login_block">
        <table border="0">
            <div>
                <h1>注册界面</h1>
            </div>
            <tr>
                <td class="title">用户名:</td>
                <td class="input">
                    <input type="text" name="username" id="username" class="login_input"/>
                </td>
            </tr>
            <tr>
                <td class="title">密码:</td>
                <td class="input">
                    <input type="password" name="password" id="password" class="login_input"/>
                </td>
            </tr>
            <tr>
                <td class="title">确认密码:</td>
                <td class="input">
                    <input type="password" name="re_password" id="re_password" class="login_input"/>
                </td>
            </tr>
            <tr>
                <td class="title">邮箱:</td>
                <td class="input">
                    <input type="text" name="email" id="email" class="login_input"/>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input class="btn" type="button" id="register" value="注册"/>
                    <a class="btn" href="login.jsp">返回</a>
                </td>
            </tr>

        </table>
    </div>
</div>
<div id="footer">
    <div class="foot_content">
        CopyRight &copy; 2008 www.**********.com 西安市*****有限公司
    </div>
    <div class="foot_content">
        版权所有 Library V1.0
    </div>
</div>
</body>
</html>