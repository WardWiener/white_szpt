<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<script type="text/javascript" src="<%=context%>/custom/arcgis/multiBaseMap.js"></script>
</head>
<body class="m-ui-layer-body">
<div class="m-ui-layer-box" style="width:500px;">

    <div class="row row-mar mar-top">
        <ul id="zTree" class="ztree z-ztree"></ul>
    </div>
    
</div>
	<script type="text/javascript" src="<%=context%>/scripts/loginHomePage/menuTree.js"></script>
</body>
</html>