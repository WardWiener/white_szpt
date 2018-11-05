<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<%@include
	file="/common/library/iweboffice2000/iweboffice2000-import.jsp"%> 

</head>
<body class="m-ui-layer-body">
<div class="row" style="margin-top: 20px">
	<div id="wordDiv"></div>

	<div class="m-ui-layer-box" style="width: 800px;">
	<div id="iwebOffice-container">
	<div class="webOffice-objDiv">
		<object class="webOffice-obj" width="100%" height="1200px"
			classid="clsid:<%=com.taiji.pubsec.complex.tools.weboffice.iweboffice2000.util.Constant.getClsid()%>"
			codebase="<%=com.taiji.pubsec.complex.tools.weboffice.iweboffice2000.util.Constant.getCodebase()%>"> </object>
	</div>
	</div>
	</div>
	</div>

	<script type="text/javascript"
		src="<%=context%>/script/zazl/lookData.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<style>
		.diyTable-page{
			text-align: right;
		}
		.laypage_main a, .laypage_main span {
			color:#fff;
		}
		.laypageskin_default a{
			border: 0px;
		    background-color: transparent;
		}
</style>
</html>

