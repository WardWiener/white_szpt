<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=8,IE=Edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>案管客户端</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/jquery/jquery-1.11.3.min.js"></script>

<!-- 换肤  -->
<script>
var ContextPath = '<%=request.getContextPath()%>'; 
var context = '<%=request.getContextPath()%>'; 
var serverName = '<%=request.getServerName()%>' ;

var clickOrder = '<%=request.getParameter("clickOrder")%>'; 
var clickListOrder = '<%=request.getParameter("clickListOrder")%>'; 

var INFO_SNAPSHOT_MODULE = JSON.parse('<%=com.taiji.pubsec.szpt.util.Constant.INFO_SNAPSHOT_MODULE.toJSONString()%>');

var snapshotId = '<%=request.getParameter("snapshotId")%>'; 
var snapshotCode = '<%=request.getParameter("snapshotCode")%>'; 

if(clickOrder=="null"){
	clickOrder = "0" ;
} ;
if(clickListOrder=="null"){
	clickListOrder = "0" ;
} ;

if(INFO_SNAPSHOT_MODULE.CSJKFX_SSWIFI==snapshotCode){
	window.top.location.href = context + "/enterbaq/showLookRealTimeWifiSnapshotPage.action?snapshotId="+snapshotId ;
}

if(INFO_SNAPSHOT_MODULE.CSJKFX_WIFIGJ==snapshotCode){
	window.top.location.href = context + "/enterbaq/showWifiLocusAnalyzeSnapshot.action?snapshotId="+snapshotId ;
}

if(INFO_SNAPSHOT_MODULE.GWR_GJFX==snapshotCode){
	window.top.location.href = context + "/enterbaq/showTrackAnalyzeSnapshot.action?snapshotId="+snapshotId ;
}

</script>
</head>

<body>

</body>
</html>