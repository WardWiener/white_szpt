<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台–重点人关注</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/highRiskPersonAlert/common/arcgisMapCommon.js"></script>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/highRiskPersonAlert/common/constant.jsp"%>
<!-- acrgis地图弹窗样式 -->
<style type="text/css">
.esriPopup .sizer {
	position: relative;
	width: 338px;
	z-index: 1;
}

.esriPopup .contentPane {
	position: relative;
	max-height: 500px;
	overflow: auto;
	padding: 0px;
	background-color: #F7F7F7;
	color: #333333;
}
</style>
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
					<li class="active"><a href="#">高危人预警</a></li>
				</ol>
				<div class="m-ui-title1">
					<h1>高危人预警</h1>
				</div>
				<div class="row">
						<div class="m-ui-table" style="margin-right: 10px">
							<table id="alertTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
								
							</table>
						</div>
				</div>
				<div class="row">
				<div id="personArrentionMapConten" style="height:600px; text-align:center"></div>
				
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/personAttention/personAttention.js"></script>
</html>