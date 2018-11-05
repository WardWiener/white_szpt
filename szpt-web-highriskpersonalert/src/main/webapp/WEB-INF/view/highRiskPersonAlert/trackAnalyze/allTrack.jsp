<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/highRiskPersonAlert/common/constant.jsp"%>

</head>
<body id="validform" class="validform m-ui-layer-body">

<div class="m-ui-layer-box" style="width:500px;">

<div class="m-ui-layer-content ">

			<table id="table"
				class="table table-bordered table-hover m-ui-table-whole"
				cellspacing="0" width="100%">
				<thead></thead>
				<tbody>
				</tbody>
			</table>

</div>
</div>

</body>
<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/trackAnalyze/allTrack.js"></script>

</html>