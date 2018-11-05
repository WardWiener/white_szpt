<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<script type="text/javascript" src="<%=context%>/custom/arcgis/multiBaseMap.js"></script>
</head>

<style>
body {
  color: #333;
}
input[type="text"]{
  color: #333;
}
</style>

<body id="tagMap" class="m-ui-layer-body validform">
<div class="row" style="margin-top:10px">
<div class="col-xs-12">
  <div class="col-xs-2"><label class="control-label">搜索：</label></div>
  <div class="col-xs-6"><input type="text" id="searchText" class="form-control input-sm"></div>
  <div class="col-xs-4">
  	<button id="search" class="btn btn-primary btn-sm">查询</button>
  	<button id="clearSearch" class="btn btn-primary btn-sm">清空</button>
  	<!-- <button id="beginMark" class="btn btn-primary btn-sm">开始标点</button> -->
  </div>
</div>
</div>
<div class="row" style="margin-top:10px">
	<div class="col-xs-2"><label class="control-label">地点：</label></div>
	<div class="col-xs-2"><input type="text" id="resultName" class="form-control input-sm valiform-keyup form-val" datatype="*1-500" errormsg="地址不可大于500个字符" nullmsg="请选择地址"></div>
	<div class="col-xs-1"><label class="control-label">经度：</label></div>
	<div class="col-xs-1"><input type="text" id="resultX" class="form-control input-sm valiform-keyup form-val" readonly datatype="*1-50" nullmsg="请标点获取经度"></div>
	<div class="col-xs-1"><label class="control-label">纬度：</label></div>
	<div class="col-xs-1"><input type="text" id="resultY" class="form-control input-sm valiform-keyup form-val" readonly datatype="*1-50" nullmsg="请标点获取纬度"></div>
</div>
<div class="row" style="margin-top:10px">
	<div class="col-xs-12" id="mapContent" style="height: 600px;"></div>
</div>

<script type="text/javascript" src="<%=context%>/scripts/tag/caseTagMap.js"></script>
<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>


</html>