<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<%@include file="/custom/arcgis/arcgis-import.jsp"%>
	<div id="c-center">
		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-xsfxts.jsp"%>
		</div>
		<div id="c-center-right">
			<div id="c-center-right-content">
				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">刑事案件分析</a></li>
					<li><a href="#">两抢一盗案件智能串并案分析</a></li>
					<li><a href="#">案件串并案评分模板</a></li>
				</ol>
				<!--悬浮操作层-->
				<div class="fixed-a">
					<div class="m-ui-title1">
						<h1>案件串并案评分模板</h1>
						<div class="m-ui-caozuobox">
							<button id="newBut" class="btn btn-primary">新增</button>
							<button id="copyBut" class="btn btn-primary">
								<span class="glyphicon glyphicon-copy"></span>复制新增
							</button>
							<button id="modifyBut" class="btn btn-success">修改</button>
							<button id="deleteBut" class="btn btn-danger">删除</button>
							<button id="enabledBut" class="btn btn-primary">启用</button>
							<button id="disableBut" class="btn btn-primary">停用</button>
						</div>
					</div>
				</div>
				<!--悬浮操作层-->
				<div class="m-ui-table">
					<table id="cgtbTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
						
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript"
		src="<%=context%>/scripts/score/scoreTemplateList.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
	
</body>

</html>