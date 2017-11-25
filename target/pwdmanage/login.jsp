<%--
  Created by IntelliJ IDEA.
  User: 80603
  Date: 2017/9/12
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>密码管理系统 - 登录</title>
    <meta name="keywords" content="perfect-ssm">
    <meta name="description" content="perfect-ssm">

    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min14ed.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min93e3.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/resources/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/style.min862f.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/login.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>
            <h1 class="logo-name">FUCK THE COURSE</h1>
        </div>
        <h3>欢迎使用 密码管理系统</h3>
        <form class="m-t" role="form" id="adminlogin" method="post"
              name="adminlogin" onsubmit="return false" action="##">
            <div class="form-group">
                <input type="email" class="form-control" placeholder="用户名"
                       name="userName" id="userName" required="" onkeydown="if(event.keyCode==13) login()">
            </div>
            <div class="form-group">
                <input type="password" class="form-control"
                       placeholder="密码" name="pwd" id="pwd" required="" onkeydown="if(event.keyCode==13) login()">
            </div>
            <button type="button" class="btn btn-primary block full-width m-b" onclick="javascript:login();">登 录
            </button>
        </form>
    </div>
</div>

</body>

</html>
