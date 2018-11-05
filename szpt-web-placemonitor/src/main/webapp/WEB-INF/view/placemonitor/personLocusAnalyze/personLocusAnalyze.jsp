<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台–人员WIFI轨迹分析</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/szpt/util/searchTime.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/placemonitor/common/arcgisMapCommon.js"></script>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/placemonitor/common/constant.jsp"%>
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
		<%@include file="/WEB-INF/base/skin/leftPart-placemonitor.jsp"%>
		<div id="c-center-right">
			<div id="c-center-right-content">
				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">场所监控分析</a></li>
					<li><a href="#">人员WIFI轨迹分析</a></li>
				</ol>
				<div class="row row-mar">
					<div class="col-xs-3" style="width: 210px">
						<label class="control-label">身份证号&nbsp;/&nbsp;手机号&nbsp;/&nbsp;MAC地址：</label>
					</div>
					<div class="col-xs-3">
						<input id="searchText" type="text" class="form-control input-sm">
					</div>
					<div class="col-xs-5">
						<button id="search" class="btn btn-primary btn-sm">查询</button>
						<button id="reset" class="btn btn-default btn-sm"
							style="margin-left: 2px;">重置</button>
						<button id="createSnapshotBtn" class="btn btn-primary btn-sm" style="display:none;">生成分析快照</button>
					</div>
				</div>

				<div class="row row-mar" style="margin-bottom: 20px">
					<div class="col-xs-3 row-mar" style="width: 420px">
						<button id="recentlyHour"
							class="btn btn-primary btn-sm searchTime-btn searchTimeBtn"
							style="margin-left: 0" value="last_hour">近一小时</button>
						<button id="recentlyDay"
							class="btn btn-primary btn-sm searchTime-btn searchTimeBtn"
							value="day">近一天</button>
						<button id="recentlyWeek"
							class="btn btn-primary btn-sm searchTime-btn searchTimeBtn"
							value="week">近一周</button>
						<button id="recentlyMonth"
							class="btn btn-primary btn-danger btn-sm searchTime-btn btSelected searchTimeBtn"
							value="month">近一月</button>
						<button id="recentlySixMonth"
							class="btn btn-primary btn-sm searchTime-btn searchTimeBtn"
							value="half_year">近半年</button>
						<button id="dateCustomized"
							class="btn btn-primary btn-sm searchTime-btn" value="costum">自定义</button>
					</div>

					<div id="searchRange" class="dateRange col-xs-6"
						style="display: none;">
						<div class="col-xs-3">
							<label class="control-label">自定义时间：</label>
						</div>
						<input type="hidden" id="dtfmt" class="dateFmt"
							value="info@yyyy-MM-dd HH:mm:ss" />
						<div class="col-xs-4 input-group">
							<div class="input-group" style="margin-right: 10px;">
								<input type="text" id="fixed_start"
									class="laydate-start form-control input-sm" readonly="readonly">
								<span class="laydate-start-span input-group-addon m-ui-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
							</div>
						</div>
						<div class="col-xs-1" style="width: 20px; text-align: center">-</div>
						<div class="col-xs-4 input-group">
							<div class="input-group" style="padding-left: 5px;">
								<input type="text" id="fixed_end"
									class="laydate-end form-control input-sm" readonly="readonly">
								<span class="laydate-end-span input-group-addon m-ui-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
							</div>
						</div>
					</div>
				</div>
				<!---热力图查询时间--->
				<div id="searchResult" style="display:none;">
					<div class="m-ui-title1">
						<h1>
							轨迹信息<span class="glyphicon glyphicon glyphicon-random micon-lg"
								style="color: #32e3f3; margin-left: 5px; margin-right: 15px"></span>
						</h1>
					</div>
					<div class="row main-block" style="margin-top: 15px;">
						<div class="col-xs-8">
							<div class="m-ui-table" style="margin-right: 10px">
								<table id="locusTable"
									class="table table-bordered table-hover m-ui-table-whole"
									cellspacing="0" width="100%">

								</table>
							</div>
						</div>
						<div class="col-xs-4">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">该人员经过次数最多的场所</h3>
								</div>
								<div class="panel-body">
									<table id="placeFrequencyOrderTable"
										class="table table-bordered m-ui-table-no-paginate"
										cellspacing="0" width="100%">
										<tbody>

										</tbody>
									</table>
								</div>
							</div>
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">该人员驻留时间最长的场所</h3>
								</div>
								<div class="panel-body">
									<table id="maxTimeplaceOrderTable"
										class="table table-bordered m-ui-table-no-paginate"
										cellspacing="0" width="100%">
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="m-ui-title1">
						<h1>
							轨迹地图<span class="glyphicon glyphicon-map-marker micon-lg "
								style="color: #ff0000; margin-left: 5px; margin-right: 15px"></span>
						</h1>
					</div>
					<div class="row">
						<div id="locusMapConten" style="height: 600px;"></div>
					</div>
					<div class="m-ui-title1">
						<h1>
							热力图<span class="glyphicon glyphicon-fire micon-lg"
								style="color: #ff0000; margin-left: 5px; margin-right: 15px"></span>
						</h1>
					</div>
					<div class="row">
						<div id="locusHotMapConten" style="height: 510px;"></div>
					</div>
				</div>
				<div id="nullSearchResult" style="text-align:center;display:none;">
					<h3>无查询结果</h3>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/placemonitor/common/wifiCommon.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/placemonitor/personLocusAnalyze/personLocusAnalyze.js"></script>
</html>