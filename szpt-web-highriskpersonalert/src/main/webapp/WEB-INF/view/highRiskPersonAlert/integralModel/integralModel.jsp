<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title>实战平台-人员积分设置</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<div id="c-center">
		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-highRiskPersonAlert.jsp"%>
		</div>
		<div id="c-center-right">

			<div id="c-center-right-content">
				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">高危人群分析</a></li>
					<li><a href="#">人员积分模型设置</a></li>
				</ol>
				<div class="m-ui-title1">
					<h1>人员积分模型设置</h1>
					<div class="m-ui-caozuobox">
						<button class="btn btn-primary" id="addModel">新增积分模型</button>
						<button class="btn btn-success" id="updateModel">修改</button>
						<button class="btn btn-danger" id="deleteModel">删除</button>
						<button class="btn btn-success" id="enabled">启用</button>
						<button class="btn btn-danger" id="disable">停用</button>
					</div>
				</div>
				<table id="integralModelTable"
					class="table table-bordered table-hover m-ui-table-whole"
					cellspacing="0" width="100%">
					 
				</table>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/integralModel/integralModel.js"></script>
</html>
