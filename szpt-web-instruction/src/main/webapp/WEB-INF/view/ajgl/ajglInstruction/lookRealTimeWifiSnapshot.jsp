<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=8,IE=Edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>案管客户端</title>
<script>

</script>
<!-- 换肤  -->
<script>
var ContextPath = '<%=request.getContextPath()%>'; 
var context = '<%=request.getContextPath()%>'; 
var serverName = '<%=request.getServerName()%>' ;

var clickOrder = '<%=request.getParameter("clickOrder")%>'; 
var clickListOrder = '<%=request.getParameter("clickListOrder")%>';

	if (clickOrder == "null") {
		clickOrder = "0";
	};
	if (clickListOrder == "null") {
		clickListOrder = "0";
	};
</script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/custom/szpt/style/layer.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/common/library/bootstrap/dist/css/bootstrap.custom.css">

<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/library/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/library/jquery/jquery-ui-fix-focus.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/library/layer/layer.fixbug.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/library/dataTables/js/jquery.dataTables.custom.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/base/js/basePart.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/library/jcade-master/jcade.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/base/js/layerAlert.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/custom/szpt/js/util.js"></script>
<!--关闭按钮旋转-->
<script type="text/javascript" src="<%=request.getContextPath()%>/ajgl/custom/ajgl/default/js/jQueryRotate.2.2.js"></script>

<link rel="stylesheet"
	href="<%=context%>/ajgl/custom/ajgl/default/style/base.css">
<link rel="stylesheet"
	href="<%=context%>/ajgl/custom/ajgl/default/style/frame.css">
<link rel="stylesheet"
	href="<%=context%>/ajgl/custom/ajgl/default/style/cs.css">
	
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<script type="text/javascript" src="<%=context%>/custom/arcgis/multiBaseMap.js"></script>
</head>

<body class="cs-bg">
	<div class="cs-content">
		<div class="cs-content-top" id="divMouseMove">
			<h2>研判结果查看</h2>
			<a id="btn-close-window" href="####" style="transform: rotate(0deg);"></a>
			<div class="row row-mar">保存快照原因：<span id="wifiSnapshotInfo"></span></div>
			<div class="wifi-tt"></div>
		</div>
		<div class="cs-content-main" style="overflow: hidden;">

			<div class="row">
				<div class="col-xs-3">
					<div class="panel panel-default" style="margin-right: 10px;margin-bottom: 0px;">
						<div class="panel-heading" style="background-color: #65a5ed; color: #fff;">实时WIFI热点</div>
						<ul id="wifiPlaceUl" class="list-group list-group-noborder" style="overflow: auto;height: 460px;">

						</ul>
					</div>
				</div>

				<div class="col-xs-9">
					<div style="position: relative;">
						<!-- <div
							style="position: absolute; left: 26%; top: 25%; z-index: 100;">
							<img src="../images/map/icon-site.png">
							<p
								style="background: #fff; padding: 1px 8px; color: #1d98de; border: 1px solid #1d98de; border-radius: 4px;">
								经开分局 <strong>50</strong>
							</p>
						</div>

						<div
							style="position: absolute; left: 30%; top: 20%; z-index: 100;">
							<img src="../images/map/icon-site.png">
							<p
								style="background: #fff; padding: 1px 8px; color: #1d98de; border: 1px solid #1d98de; border-radius: 4px;">
								小河路口 <strong>80</strong>
							</p>
						</div>
						<div
							style="position: absolute; left: 40%; top: 30%; z-index: 100;">
							<img src="../images/map/map-icon-wifi.png">
							<p
								style="background: #fff; padding: 1px 8px; color: #ff4646; border: 1px solid #ff4646; border-radius: 4px;">15882937456</p>
						</div>
						<div
							style="position: absolute; left: 50%; top: 25%; z-index: 100;">
							<img src="../images/map/map-icon-wifi.png">
							<p
								style="background: #fff; padding: 1px 8px; color: #ff4646; border: 1px solid #ff4646; border-radius: 4px;">15882937456</p>
						</div>

						<div
							style="position: absolute; left: 10%; top: 25%; z-index: 100;">
							<img src="../images/map/map-icon-wifi.png">
							<p
								style="background: #fff; padding: 1px 8px; color: #ff4646; border: 1px solid #ff4646; border-radius: 4px;">15882937456</p>
						</div>

						<div
							style="position: absolute; left: 10%; top: 35%; z-index: 100;">
							<img src="../images/map/map-icon-wifi.png">
							<p
								style="background: #fff; padding: 1px 8px; color: #ff4646; border: 1px solid #ff4646; border-radius: 4px;">15882937456</p>
						</div> -->
						<div id="realTimeWifiMapConten" style="height: 500px;"></div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/ajgl/ajglInstruction/lookRealTimeWifiSnapshot.js"></script>
<script>
	var snapshotId = "${param.snapshotId}";
</script>
</html>