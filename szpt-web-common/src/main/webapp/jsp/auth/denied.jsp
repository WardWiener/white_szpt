<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=8,IE=Edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>实战平台</title>
</head>

<style type="text/css">
    body{background: #f4f9ff; font-family: Microsoft Yahei;}
    .box404{width: 670px; margin:200px auto 0 auto; text-align: center;}
    .box404 button{ background:none; border:1px solid #40a8e8;color:#249ce6; padding: 8px 25px; font-size: 16px; border-radius: 2px;font-family: Microsoft Yahei;}
    .box404 button:hover{ background:#40a8e8; border-color:#249ce6;color:#fff;}
    .box404 p{margin-bottom: 30px; font-size: 16px;}
</style>

<body>
<div class="box404">
    <img src="<%=request.getContextPath()%>/images/bg-sorry.png">
    <p class="text">抱歉，您没有对应业务的使用权限！
        您可以联系分局管理员，帮助您授权。</p>
</div>
</body>


</html>