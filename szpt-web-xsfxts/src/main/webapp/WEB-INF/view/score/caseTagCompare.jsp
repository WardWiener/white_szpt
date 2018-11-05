<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript"
	src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>

</head>
<script type="text/javascript">
	var caseCodes = ${resultMap.caseCodes};
</script>
<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
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
				</ol>

				<div class="m-ui-title1">
					<h1>
						<button id="back" class="btn btn-primary" style="margin-right: 20px;">
							<span class="glyphicon glyphicon-menu-left"></span>返回
						</button>
						比对结果
					</h1>
					<div class="m-ui-caozuobox">
						<button id="showHideBut" state="hide" class="btn btn-primary">隐藏不同项</button>
						<button id="highlightBut" class="btn btn-warning">高亮显示相同项</button>
						<button id="caseAnalysisBut" class="btn btn-danger">串并案分析</button>
						<button id="exportExcelBut" class="btn btn-primary">EXCEL导出</button>
						<button id='refreshBut' class="btn btn-primary">
							<span class="glyphicon glyphicon-refresh mar-right"></span>刷新
						</button>
					</div>
				</div>
				<div style="overflow-x: auto; overflow-y: hidden; margin-top: 30px; width: 100%;">
					<!-- <div style="width: 2000px; overflow: hidden;"> -->
						<table id="ctcTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%" style="margin: 0;">
							
						</table>
					<!-- </div> -->
				</div>
				<p style="margin-top: 10px;">
					隐藏项说明：<span id="hideItemInfo" class="color-yellow1">无</span>
				</p>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=context%>/scripts/score/caseTagCompare.js">
	</script>
</body>
</html>