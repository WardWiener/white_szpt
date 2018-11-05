<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>

</head>

<body class="m-ui-layer-body">

	<div class="fj1">
		<button id="uploadBtn" class="btn btn-sm btn-info">
			<span class="glyphicon glyphicon-open mar-right"></span>上传
		</button>
	</div>
	<div class="m-ui-layer-box" style="width: 400px;">
		

		<div class="row" style="margin-top: 20px">
			<div class="col-xs-3">
				<div style="width: 100px; margin-top: 20px; ">
				<label class="control-label">资料类型：</label>
				</div>
			</div>
			<div class="col-xs-6">
					<div style="width: 200px; margin-top: 20px; ">
					<select id="zllxSel" class="select2-noSearch" style="width: 200px;">
					</select>
					</div>
			</div>
		</div>

		 <div class="row" style="margin-top: 20px">
			<div class="col-xs-3">
				<label class="control-label">资料上传：</label>
			</div>
			<div class="col-xs-9">
				<div class="input-group">
					<div class="col-xs-12 upload-control" id="container">
					</div>
					
				</div>
			</div>
			 
		</div> 
	</div>
	<script type="text/javascript"
		src="<%=context%>/script/zazl/zaUploadLayer.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>

</body>
<style>
.diyTable-page {
	text-align: right;
}

.laypage_main a, .laypage_main span {
	color: #fff;
}

.laypageskin_default a {
	border: 0px;
	background-color: transparent;
}
</style>
</html>