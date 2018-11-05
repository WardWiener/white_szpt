<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>      
<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<%@include file="/WEB-INF/base/skin/utilPart.jsp"%>

<script>
var queryString = '<%=request.getQueryString()%>' ;
var sign = $.util.parseUrlQueryStr(queryString) ;
$(document).ready(function() {	
	var url = context + "/jsp/auth/denied.jsp" ;
	if(sign.sign=="denied"){
		//window.top.location.href = url ;
	}
});
</script>
</head>

<body class="m-ui-layer-body">
<h1 style="color:#000;">权限不足</h1>

<%@include file="/WEB-INF/base/skin/footPart.jsp"%>

</body>


</html>