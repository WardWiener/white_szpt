<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台–实时WIFI设备监控</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/placemonitor/common/arcgisMapCommon.js"></script>
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
<body id="fcplValidform" class="validform">
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<div id="c-center">
		<%@include file="/WEB-INF/base/skin/leftPart-placemonitor.jsp"%>
		<div id="c-center-right">
			<div id="c-center-right-content">
				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">场所监控分析</a></li>
					<li><a href="#">实时WIFI设备监控</a></li>
				</ol>
					<div class="row row-mar">
					<div class="col-xs-2"></div>
						<div class="col-xs-2">
							<label class="control-label">&nbsp;MAC地址：</label>
						</div>
						<div class="col-xs-4">
							<input id='searchText' type="text" class="form-control input-sm">
						</div>
						<div class="col-xs-3">
							<button id="search" class="btn btn-primary btn-sm">查询</button>
							<button id="reset" class="btn btn-default btn-sm">重置</button>
							<button id="createSnapshot" class="btn btn-primary btn-sm">生成快照</button>
						</div>
					</div>
					<div>
					&nbsp;
					</div>
					<!-- 时间 
					<div class="row row-mar" style="margin-bottom: 20px">
					<div class="col-xs-3 row-mar" style="width: 420px">
						<button id="recentlyHour" class="btn btn-primary btn-sm selectTime" style="margin-left: 0">近一小时</button>
						<button id="recentlyDay" class="btn btn-primary btn-sm selectTime">近一天</button>
						<button id="recentlyWeek" class="btn btn-primary btn-sm selectTime">近一周</button>
						<button id="recentlyMonth" class="btn btn-danger btn-sm selectTime">近一月</button>
						<button id="recentlySixMonth" class="btn btn-primary btn-sm selectTime">近半年</button>
						<button id="diyTime" class="btn btn-primary btn-sm selectTime">自定义</button>
					</div>

					<div id="diyDateRange" class="dateRange col-xs-6" style="display:none;">
						<div class="col-xs-3">
							<label class="control-label">自定义时间：</label>
						</div>
						<input type="hidden" id="dtfmt" class="dateFmt" value="info@yyyy-MM-dd HH:mm:ss" />
						<div class="col-xs-4 input-group">
							<div class="input-group" style="margin-right:10px;">
								<input type="text" id="fixed_start" class="laydate-start form-control input-sm" readonly="readonly">
								<span class="laydate-start-span input-group-addon m-ui-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
							</div>
						</div>
						<div class="col-xs-1" style="width: 20px; text-align: center">-</div>
						<div class="col-xs-4 input-group">
							<div class="input-group" style="padding-left:5px;">
								<input type="text" id="fixed_end" class="laydate-end form-control input-sm" readonly="readonly">
								<span class="laydate-end-span input-group-addon m-ui-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
							</div>
						</div>
					</div>
				</div> -->
					
				<div id="c-center-right-content-block">

					<div id="c-center-right-content-bar" style="padding-top: 0">

						<div class="panel panel-default" style="min-height: 700px">
							<div class="panel-heading" style="background-color: #1d98de; color: #fff;">实时WIFI热点</div>
							<ul id="wifiPlaceUl" class="list-group" style="overflow-y: scroll; height: 660px;">
								
							</ul>
						</div>
					</div>
					<div id="c-center-right-content-con">
						<div class="right-inner">
							<div class="row alert-info" style="padding: 10px;">
								共监测WIFI围栏点 <strong id="placeSum"></strong> 个；共监测终端设备 <strong id="deviceSum"></strong> 个。
								<!-- <input checked type="checkbox" class="icheckbox" id="placeCheck"><label for="monitor-pot" style="margin-right:30px; color:#ff0000;">&nbsp;显示监测点</label>
								<input checked type="checkbox" class="icheckbox" id="personCheck"><label for="monitor-sb" style="color:#ff0000;">&nbsp;显示监测设备</label> -->
							</div>
							<div id="realTimeWifiMapConten" style="height: 660px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/placemonitor/common/wifiCommon.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/placemonitor/realTimeWifi/lookRealTimeWifi.js"></script>

</html>