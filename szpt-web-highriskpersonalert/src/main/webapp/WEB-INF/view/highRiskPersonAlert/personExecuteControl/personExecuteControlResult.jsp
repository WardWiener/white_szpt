<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/highRiskPersonAlert/common/constant.jsp"%>
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/highRiskPersonAlert/common/arcgisMapCommon.js"></script>
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
<body id="validform" class="validform m-ui-layer-body">

	<div class="m-ui-layer-box" style="width: 800px;">

		<div class="m-ui-layer-content">
			<p class="row-mar">
				布控单 <strong id="num" name="num"></strong> 累计共捕获 <span
					class="color-red1" id="resultNum" name="resultNum"></span> 条信息
			</p>


			<div class="alert alert-info">
				<div class="row row-mar">
					<div class="col-xs-2 m-label-left">身份证号：</div>
					<div class="col-xs-3" id="idCardNo" name="idCardNo"></div>
					<div class="col-xs-1 m-label-left">姓名：</div>
					<div class="col-xs-2" id="personName" name="personName"></div>
					<div class="col-xs-1 m-label-left">性别：</div>
					<div class="col-xs-2" id="sexName" name="sexName"></div>
				</div>
				<div class="row row-mar">
					<div class="col-xs-2 m-label-left">手机号：</div>
					<div class="col-xs-6" id="phone" name="phone"></div>
					<div class="col-xs-1 m-label-left">民族：</div>
					<div class="col-xs-2" id="ethnicGroup" name="ethnicGroup"></div>
				</div>
				<div class="row row-mar">
					<div class="col-xs-2 m-label-left">户籍地址：</div>
					<div class="col-xs-4" id="residenceAddress" name="residenceAddress"></div>
				</div>
				<div class="row row-mar">
					<div class="col-xs-2 m-label-left">现住地址：</div>
					<div class="col-xs-4" id="localAddressDetail"
						name="localAddressDetail"></div>
				</div>
				<div class="row row-mar">
					<div class="col-xs-2 m-label-left">布控时间：</div>
					<div class="col-xs-8" id="time" name="time"></div>
				</div>
				<div class="row row-mar">
					<div class="col-xs-2 m-label-left">布控原因：</div>
					<div class="col-xs-4" id="note" name="note"></div>
				</div>
			</div>
			<table id="table"
				class="table table-bordered table-hover m-ui-table-whole"
				cellspacing="0" width="100%">
			</table>


			<div id="locusMapConten" style="height: 400px;"></div>

		</div>
		<!--内容end-->
	</div>

</body>
<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/personExecuteControl/personExecuteControlResult.js"></script>

</html>