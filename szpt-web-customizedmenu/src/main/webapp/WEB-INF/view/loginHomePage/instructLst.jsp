<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/instruction/common/constant.jsp"%>
<script type="text/javascript" src="<%=context%>/custom/arcgis/multiBaseMap.js"></script>
</head>
<body style="background: #1E2B42;">
		
<!--悬浮操作层-->

	<div class="m-ui-table" style="margin-top:20px;">
                  <table id="instructionTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="10" width="80%">
                  </table>
      </div>
<!--结束-->
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
	</body>
	<script type="text/javascript" src="<%=context%>/scripts/loginHomePage/instructLst.js"></script>
</html>