<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台–WIFI围栏监控点</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/placemonitor/common/constant.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>

	<div id="c-center">
		<%@include file="/WEB-INF/base/skin/leftPart-placemonitor.jsp"%>
		<div id="c-center-right">
			<div id="c-center-right-content">

				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">场所监控分析</a></li>
					<li class="active"><a href="#">WIFI围栏监控点</a></li>
				</ol>

				<div class="m-ui-searchbox">

					<div class="row row-mar">
						<div class="col-xs-1" style="width: 100px;">
							<label class="control-label">名称：</label>
						</div>
						<div class="col-xs-1">
							<input id="nameInput" type="text" class="form-control input-sm">
						</div>
						<div class="col-xs-1" style="width: 100px;">
							<label class="control-label">编码：</label>
						</div>
						<div class="col-xs-1">
							<input type="text" id="codeInput" class="form-control input-sm">
						</div>
						<div class="col-xs-1" style="width: 100px;">
							<label class="control-label">地址：</label>
						</div>
						<div class="col-xs-2">
							<input type="text" id="addressInput" class="form-control input-sm">
						</div>
						<div class="col-xs-2" style="width: 200px;">
							<label class="control-label"><input type="checkbox" id="isLocaCheck" class="icheckbox"> 本区域 (经开分局)&nbsp;&nbsp;&nbsp;</label>
						</div>
						<div class="col-xs-2">
							<button id="searchBtn" class="btn btn-primary btn-sm" style="margin-left: 15px">查询</button>
							<button id="reset" class="btn btn-default btn-sm">重置</button>
						</div>
					</div>
				</div>

				<div class="m-ui-title1">
					<h1>监控点</h1>
				</div>
				<div class="m-ui-table">
					<table id="tableId" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0">
						
					</table>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>

	<!--c-center-right-content-block结束-->
</body>

<script type="text/javascript"
	src="<%=context%>/scripts/placemonitor/deployControl/deployControlPreserve.js"></script>