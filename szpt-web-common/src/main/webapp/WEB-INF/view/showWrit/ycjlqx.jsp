<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
</head>
<body class="m-ui-layer-body">
	<input type="hidden" id="writId"
				value=<%=request.getParameter("writId")%>>
	<div style="padding: 20px;" >
		<div id="writ1" style="width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="a1"></span></h3>
			<br/>
			<h3 align="center">延长拘留期限通知书</h3>
			<br/>
			<h3 align="center">(存  根)</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="a2"></span><span class="valCell" valName="a3"></span><span class="valCell" valName="a4"></span>字[<span class="valCell" valName="a5"></span>]<span class="valCell" valName="a6"></span>号</p>
			<br/>
			<p>案件名称   <span class="valCell" valName="a7"></span></p>
			<br/>
			<p>案件编号   <span class="valCell" valName="a8"></span></p>
			<br/>
			<p>犯罪嫌疑人   <span class="valCell" valName="a9"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a10"></span></p>
			<br/>
			<p>出生日期   <span class="valCell" valName="a11"></span></p>
			<br/>
			<p>羁押处所   <span class="valCell" valName="a12"></span></p>
			<br/>
			<p>执行拘留时间   <span class="valCell" valName="a13"></span></p>
			<br/>
			<p>延长拘留期限   <span class="valCell" valName="a14"></span></p>
			<br/>
			<p>延长拘留期限原因   <span class="valCell" valName="a15"></span></p>
			<br/>
			<p>批准人   <span class="valCell" valName="a16"></span></p>
			<br/>
			<p>批准时间   <span class="valCell" valName="a17"></span></p>
			<br/>
			<p>办案人   <span class="valCell" valName="a18"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a19"></span></p>
			<br/>
			<p>办案单位   <span class="valCell" valName="a20"></span></p>
			<br/>
			<p>填发时间   <span class="valCell" valName="a21"></span></p>
			<br/>
			<p>填发人   <span class="valCell" valName="a22"></span></p>
		</div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/showWrit/ycjlqx.js"></script>
</html>