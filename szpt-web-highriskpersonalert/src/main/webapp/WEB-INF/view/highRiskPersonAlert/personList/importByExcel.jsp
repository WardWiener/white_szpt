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

	<div class="m-ui-layer-box" style="width: 400px;">
		<div class="row row-mar mar-top">
			<div class="col-xs-3 m-label-right">
				数据文件(*.xls):
			</div>
			<div class="col-xs-9">
				<div class="upload-control" id="container"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/personList/importByExcel.js"></script>

</html>