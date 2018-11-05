<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
</head>
<body class="m-ui-layer-body">
</body>
<script type="text/javascript">
(function($){
	"use strict";
	$(document).ready(function() {
		var frameData = window.top.$.layerAlert.getFrameInitData(window);
		window.location.href = context + "/showWrit/checkWrit.action?archivedFileId=" + frameData.initData.archivedFileId;
	});
})(jQuery);
</script>
</html>