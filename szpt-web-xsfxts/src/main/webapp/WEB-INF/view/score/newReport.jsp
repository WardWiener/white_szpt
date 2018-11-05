<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
</head>
<body class="m-ui-layer-body">

	<div class="m-ui-layer-box">
		<div class="m-ui-title2">
			<h2>生成串并案报告</h2>
		</div>

		<!--word编辑器-->
		<div class="paper-toolbar">
			<img src="../images/word-edit.png">
		</div>
		<div class="paper-box">
			<div class="paper" style="width: 1000px;"></div>
		</div>
		<!--word编辑器end-->


		<!--内容end-->
		<div id="newBut" class="m-ui-commitbox">
			<button id="saveBut" class="btn btn-primary  btn-lg">保存</button>
			<button id="cancelBut" class="btn btn-default  btn-lg">取消</button>
		</div>
		<div id="lookBut" class="m-ui-commitbox" style="display:none;">
			<button id="exportWordBut" class="btn btn-primary btn-lg">导出word</button>
			<button id="informBut" class="btn btn-danger  btn-lg">通报</button> 
		</div>
	</div>


</body>
<script type="text/javascript"
	src="<%=context%>/scripts/score/newReport.js"></script>
</html>