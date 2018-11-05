<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>

</head>

<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>


	<div id="c-center">

		<div class="fixed-a">
			<%@include file="/WEB-INF/custom/szpt/skin/left-ptjc.jsp"%>
		</div>
		<input id="paraId" type="hidden"
			value="<%=request.getParameter("id")%>">
		<div id="c-center-right">
			<div id="c-center-right-content">

				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">平台基础</a></li>
					<li><a href="#">刑事警情常量设置</a></li>
				</ol>


				<div class="m-ui-title1">
					<h1>刑事警情常量</h1>
				</div>
				<div class="row mar-top">
					<div  class="col-xs-6">
						<div id="ri" style="margin-right: 20px;">
							<div  class="m-ui-title4">
								<h2>日警情常量</h2>
							</div>
							<div class="m-ui-table">
								<table id="day" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
								</table>
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div style="margin-left: 20px;">
							<div class="m-ui-title4">
								<h2>周警情常量</h2>
							</div>
							<div class="m-ui-table">
								<table id="week" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
								</table>
							</div>
						</div>
					</div>

				</div>

				<div class="row mar-top">
					<div class="col-xs-6">
						<div style="margin-right: 20px;">
							<div class="m-ui-title4">
								<h2>月警情常量</h2>
							</div>
							<div class="m-ui-table">
								<table id="month" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
								</table>
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div style="margin-left: 20px;">
							<div class="m-ui-title4">
								<h2>年警情常量</h2>
							</div>
							<div class="m-ui-table">
								<table id="year" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
								</table>
							</div>
						</div>
					</div>

				</div>
				<div class="m-ui-commitbox">
					<button id="saveBtn" class="btn btn-primary  btn-lg">保存</button>
					<button id="refresh" class="btn btn-default  btn-lg">重置</button>
				</div>


				<!--结束-->


			</div>
		</div>

	</div>
	<script type="text/javascript"
		src="<%=context%>/scripts/gzgl/penalConstant.js"></script>

	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>

</html>