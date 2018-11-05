<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>    
<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<link  href="<%=context%>/custom/szpt/style/layer.css" rel="stylesheet" type="text/css" />
<link  href="<%=context%>/custom/szpt/style/login.css" rel="stylesheet" type="text/css" />
<script>

var queryString = '<%=request.getQueryString()%>' ;
var sign = $.util.parseUrlQueryStr(queryString) ;
if(sign.session=="0"){
	window.top.location.href = context + "/index.jsp" ;
}

var sessionId = '<%=session.getId()%>' ;
$(document).ready(function() {	

});
</script>
</head>
<body>

<div class="wrapper">
<div class="bottom-bg"></div>
<h1 class="logo-name"><span>贵阳市经开分局警务实战平台</span></h1>


<div class="login-box">
<div class="login-box-bg"></div>
<h2>用户登录</h2>
<div id="myvf" class="content logform">
<input  type="text" name="userName" id="userName" placeholder="用户名" class="put1 form-val" />
<div class="tips-out"><p class="tips alt">请输入用户名！</p></div>

<input  type="password" name="password" id="password" class="put2 form-val" />
<div class="tips-out"><p class="tips alt">请输入密码！</p></div>

<button type="submit" id="login-button">登录</button>

</div>
<div class="welcomebox">
<div class="we">
<h3>欢迎您，登录成功！<!--请在2秒的过渡中预加载首页数据避免空白--></h3>
<p>2&nbsp;&nbsp;秒后进入平台首页..</p>
</div>
<div class="btnbox"><button class="exit">退出登录</button></div>
</div>
</div>


<div class="bg-animation"><div class="zhen"><div class="light"></div></div></div>  
                                 
</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
<script type="text/javascript" src="<%=context%>/scripts/szpt/login/login.js"></script>
</body>


</html>