<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<script type="text/javascript"
	src="<%=context%>/common/library/bootstrap/js/scrollspy.js"></script>
	<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
<script>
	$(document).ready(function() {
		$('body').scrollspy({
			target : '#navbar-example'
		});
	});
</script>
</head>

<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>

	<div id="c-center">

		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-dagl.jsp"%>
		</div>
		<input id="caseId" type="hidden"
			value="<%=request.getParameter("caseId")%>">
		<div id="c-center-right">
			<div id="c-center-right-content">

				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">档案管理</a></li>
					<li><a href="#">一人一档</a></li>
					<li class="active">我的关注</li>
				</ol>
					<div class="m-ui-searchbox">

						<div class="row row-mar">
							<div class="col-xs-2" style="width: 100px;">
								<label class="control-label">姓名：</label>
							</div>
							<div class="col-xs-1">
								<input id="name" type="text" class="form-control input-sm">
							</div>
							<div class="col-xs-2" style="width: 100px;">
								<label class="control-label">身份证：</label>
							</div>
							<div class="col-xs-2">
								<input id="idNum" type="text" class="form-control input-sm">
							</div>
							<div class="col-xs-4">
								<button id="search" class="btn btn-primary btn-sm" style="margin-left: 15px">查询</button>
								<button id="reset" class="btn btn-default btn-sm">重置</button>
								<!-- <button id="more" class="btn btn-link">更多</button> -->
								<input type="button" id="more" class="btn btn-link" value="更多">
							</div>
						</div>
						<div class="more row row-mar" style="margin-top: 15px; display:none">
							<div class="col-xs-2" style="width: 100px;">
								<label class="control-label">预警类型：</label>
							</div>
							<div class="col-xs-8" style="padding-top: 5px;">
								<div id="warning-type" class="warning-type" style="width: 700px;">
									<!-----------------------------------------开发注意此处样式修改 去掉<a>标签里的颜色名称------------------------------------------>
									 <a href="#" id="allYjlx" class="warnType btn sq-all" title="全部"></a>
										
								</div>
							</div>
						</div>
						<div id="rylbDiv" class="more row row-mar" style=" display:none">
							<div class="col-xs-2" style="width: 100px; padding-top: 10px">
								<label class="control-label">人员类型：</label>
							</div>
						<div class="col-xs-10">

							<div class="row m-icheck-group mar-top">

								<p id="allRylbP" class="col-xs-2" style="width: 139px">
									<input id="allRylb" type="checkbox" class="icheckbox">全部
								</p>
								<div id="xsqkType" class="col-xs-9"
									style="width: 750px; border: 1px dashed #6e97cb; padding: 5px 10px 8px 10px; margin-top: -8px">

								</div>
							</div>
							<div id="otherRylbType" class="row m-icheck-group mar-top">
							</div>
						</div>
						<div class="row row-mar">
							<div class="col-xs-2" style="width: 100px; padding-top: 10px">
								<label class="control-label">案件名称：</label>
							</div>
							<div class="col-xs-3" style="padding-top: 10px">
								<input id="caseName" type="text" class="form-control input-sm">
							</div>
							<div class="col-xs-2" style="width: 100px; padding-top: 10px">
								<label id="caseCode" class="control-label">案件编号：</label>
							</div>
							<div class="col-xs-2" style="padding-top: 10px">
								<input type="text" class="form-control input-sm">
							</div>
						</div>
					</div>
						

					</div>
				<div class="m-ui-title1">
					<h1>我的关注</h1>
				</div>
				<div class="m-ui-table">
					<table id="concernInfo" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
					</table>
				</div>




				<!--c-center-right-content-block结束-->


			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=context%>/scripts/yryd/wdgz.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>

</html>