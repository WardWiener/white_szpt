<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
</head>
<body class="m-ui-layer-body">
	<div class="text-center" style="padding: 20px" >
		<button id="btn1" divId="writ1" class="btn btn-danger">第一联</button>
	</div>
	<input type="hidden" id="writId"
		value=<%=request.getParameter("writId")%>>
	<div style="padding: 20px;" >
		<div id="writ1" style="width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="a1"></span></h3>
			<br/>
			<h3 align="center">起诉意见书</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="a2"></span><span class="valCell" valName="a3"></span><span class="valCell" valName="a4"></span>字[<span class="valCell" valName="a5"></span>]<span class="valCell" valName="a6"></span>号</p>
			<br/>
			<p><span class="valCell" valName="a7"></span></p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;此致</p>
			<br/>
			<p><span class="valCell" valName="a8"></span>人民检察院</p>
			<br/><br/>
			<p style="text-align: right;">(公安局印)</p>
			<p style="text-align: right;"> <span class="valCell" valName="a9"></span></p>
			<br/>
			<p>附：1.本案卷宗<span class="valCell" valName="a10"></span>卷<span class="valCell" valName="a11"></span>页</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.随案移交物品<span class="valCell" valName="a13"></span>件</p>
		</div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/showWrit/qsyjs.js"></script>
</html>