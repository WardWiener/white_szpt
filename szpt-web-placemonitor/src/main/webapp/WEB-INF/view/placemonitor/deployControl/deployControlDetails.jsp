<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台–WIFI分析</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<script type="text/javascript" src="<%=context%>/custom/arcgis/multiBaseMap.js"></script>
</head>
<style>
	.fj1 {
		color: #000;
		background: #fff;
		z-index: 1;
	}
	
	div {
		padding: 5px;
		top: 5px
	}
	#mapContent_zoom_slider{
		display:none;
	}
</style>

<script>
	
</script>
<body class="m-ui-layer-body">
	<div class="row map-search-box" id="map-search-box">
		<div class="col-xs-3 fj1">
			<div class="search-result hot-result">
				<div class="row">
					<div class="col-xs-4">
						<label class="control-label">名称:</label>
					</div>
					<div class="col-xs-8">
						<input type="text" valName="internetServicePlaceName" style="color: #000;" class="form-control input-sm valCell">
					</div>
				</div>
			</div>
			<div>
				<div class="row">
					<div class="col-xs-4">
						<label class="control-label">编码:</label>
					</div>
					<div class="col-xs-8">
						<input type="text" valName="internetServicePlaceCode"
							style="color: #000;" class="form-control input-sm valCell">
					</div>
				</div>
			</div>
			<div>
				<div class="row" style="display: text-align: center">
					<div class="col-xs-4">
						<label class="control-label">地址:</label>
					</div>
					<div class="col-xs-8">
						<input type="text" valName="detailedAddress" style="color: #000;"
							class="form-control input-sm valCell">
					</div>
				</div>
			</div>
			<div>
				<div class="row">
					<div class="col-xs-4">
						<label class="control-label">派出所:</label>
					</div>
					<div class="col-xs-8">

						<select id="pcsSelect" class="select2-noSearch allowClear ">
						</select>

					</div>
				</div>
			</div>
			<div>
				<div class="row">
					<div class="col-xs-4">
						<label class="control-label">是否小区:</label>
					</div>
					<div class="col-xs-8">
						<select id="islive" class="select2-noSearch allowClear ">
						</select>
					</div>
				</div>
			</div>
		</div>
		<!-- 地图div -->
		<div class="col-xs-9" id="mapContent" style="height:480px;"></div>
	</div>
</body>

<script type="text/javascript"
	src="<%=context%>/scripts/placemonitor/deployControl/deployControlDetails.js"></script>
<style>
</style>
</html>