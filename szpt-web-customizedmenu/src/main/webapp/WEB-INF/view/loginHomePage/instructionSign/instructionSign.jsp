<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/instruction/common/constant.jsp"%>
<script type="text/javascript" src="<%=context%>/custom/arcgis/multiBaseMap.js"></script>
</head>
<body class="m-ui-layer-body">
	<div class="m-ui-layer-box" style="width:500px;">
    <p class="alert alert-info" style="padding: 10px 10px"><a id="content" href="#">指令内容针对XXX案件嫌疑人张三开展研判</a></p>
    <p class="text-right mar-right" style="color: #444">指令发送单位: <span id="createPeopleDepar">案管/情指中心</span> <span id="createTime" class="mar-left">09:41</span></p>
    
    <!--内容end-->
</div>

	<script type="text/javascript" src="<%=context%>/scripts/loginHomePage/instructionSign/instructionSign.js"></script>
</body>
</html>