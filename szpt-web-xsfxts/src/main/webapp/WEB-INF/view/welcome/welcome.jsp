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
<script type="text/javascript"
	src="<%=context%>/scripts/szpt/util/cascadedDicItem.js"></script>

</head>
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
				</ol>

				<div class="m-ui-title1">
					<h1>刑事案件分析</h1>
				</div>

				<!--整体查询板块--begin-->
				<div class="main-block" style="margin-bottom: 15px;">

					<div class="row row-mar">
						<div id="welcomeDateRange" class="col-xs-7 dateRange" style="width: 66%">
							<input type="hidden" id="dtfmt" class="dateFmt" value="info@yyyy-MM-dd HH" />
							<div class="col-xs-2">
								<label class="control-label">时间：</label>
							</div>		   
							<div class="col-xs-4">
								<div class="input-group" style="margin-right:10px;">
									<input type="text" id="fixed_start" class="laydate-start form-control input-sm" readonly="readonly">
									<span class="laydate-start-span input-group-addon m-ui-addon">
										<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
									</span>
								</div>
							</div>
							<div class="col-xs-1" style="width: 20px; text-align: center">-</div>
							<div class="col-xs-4">
								<div class="input-group" style="padding-left:5px;">
									<input type="text" id="fixed_end" class="laydate-end form-control input-sm" readonly="readonly">
									<span class="laydate-end-span input-group-addon m-ui-addon">
										<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
									</span>
								</div>
							</div>
						</div>
						<div class="col-xs-3">
							<div class="col-xs-3">
								<label class="control-label">辖区：</label>
							</div>
							<div class="col-xs-8">
								<select id="region" class="form-control input-sm select2-noSearch allowClear form-val">
									<option value="all">分局</option>
								</select>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-xs-4">
							<div class="col-xs-4">
								<label class="control-label">案件类别：</label>
							</div>
							<div class="col-xs-8">
								<select id="type" class="form-control input-sm select2-noSearch allowClear form-val">
								
								</select>
							</div>
						</div>
						<div class="col-xs-7">
							<div class="col-xs-2">
								<label class="control-label">案件性质：</label>
							</div>
							<div class="col-xs-4">
								<select id="firstSort" class="form-control input-sm select2-noSearch allowClear form-val">
								
								</select>
							</div>
							<div class="col-xs-4" style="margin-left: 10px;">
								<select id="secondSort" class="form-control input-sm select2-noSearch allowClear form-val">
								
								</select>
							</div>
							<div class="col-xs-1">
								<button id="analysis" class="btn btn-primary btn-sm" style="margin-left: 10px">分析</button>
							</div>
						</div>
					</div>

				</div>
				<!--整体查询板块--end-->
				<div class="row" style="background: #15253c; height: 300px;">
					<div class="col-xs-4">
						<div style="margin-right: 15px; height: 265px; background-color: #273d62; padding: 10px;">
							<h2 class="font20" style="height: 30px;">发案处所</h2>
							<table id="occurPlaceTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
								<tbody>
									
								</tbody>
							</table>
						</div>
					</div>
					<div class="col-xs-4">
						<div
							style="margin-right: 15px; height: 265px; background-color: #273d62; padding: 10px;">
							<h2 class="font20" style="height: 30px;">作案特点</h2>
							<table id="caseFeatureTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
								<tbody>
									
								</tbody>
							</table>
						</div>
					</div>


					<div class="col-xs-4">
						<div style="margin-right: 15px; height: 265px; background-color: #273d62; padding: 10px;">
							<h2 class="font20" style="height: 30px;">作案时段</h2>
							<table id="casePeriodTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
								<tbody>
									
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="row" style="margin-bottom: 30px;">
					<div class="col-xs-6">
						<h2 class="font20 text-center dark-block" style="padding: 10px">盗窃案件发案情况 
							<span id="larcenyCaseCount" class="font30 color-yellow2 mar-left"style="line-height: 14px;">0起</span>
						</h2>
						<div id="highchartLarcenyCase" style="height: 400px;"></div>
					</div>

					<div class="col-xs-6">
						<h2 class="font20 text-center dark-block" style="padding: 10px">抢劫、抢夺案件发案情况 
						<span id="robCaseCount" class="font30 color-yellow2 mar-left" style="line-height: 14px;">0起</span>
						</h2>
						<div id="highchartRobCase" style="height: 400px;"></div>
					</div>
				</div>
				<!----row---->
			</div>
		</div>
	</div>
	<!-----------------主题代码---end---------------------->

	<script type="text/javascript"
		src="<%=context%>/scripts/welcome/welcome.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>


</html>