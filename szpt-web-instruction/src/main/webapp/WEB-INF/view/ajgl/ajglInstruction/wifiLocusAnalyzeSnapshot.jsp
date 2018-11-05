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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/common/library/bootstrap/dist/css/bootstrap.custom.css">

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
<script type="text/javascript"
	src="<%=context%>/custom/arcgis/multiBaseMap.js"></script>
<script type="text/javascript">
	var snapshotId = "<%=request.getParameter("snapshotId")%>";
</script>
</head>
<body class="cs-bg">
	<div class="cs-content">
		<div class="cs-content-top" id="divMouseMove">
			<h2>WIFI轨迹分析</h2>
			<a id="btn-close-window" href="####" style="transform: rotate(0deg);"></a>
		</div>
		<div class="cs-content-main">

			<div class="m-ui-title3">
				<h2>
					<span class="glyphicon glyphicon-random"
						style="color: #5894e7; margin-right: 10px; vertical-align: middle;"></span>轨迹信息
				</h2>
			</div>
			<div class="row light-block">
				<div class="col-xs-8">
					<div class="m-ui-table" style="margin-right: 20px">
						<table id="locusTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
						
						</table>
					</div>
				</div>


				<div class="col-xs-4">
					<div class="panel panel-info m-panel" style="margin-bottom: 10px">
						<div class="panel-heading">
							<h3 class="panel-title" style="font-size: 14px;">该人员经过次数最多的场所</h3>
						</div>
						<div class="row">
							<table id="placeFrequencyOrderTable" class="table table-bordered m-ui-table-no-paginate" cellspacing="0" width="100%">
								<tbody>
			
								</tbody>
							</table>
						</div>
					</div>
					<div class="panel panel-info m-panel" style="margin-bottom: 0">
						<div class="panel-heading">
							<h3 class="panel-title" style="font-size: 14px;">该人员驻留时间最长的场所</h3>
						</div>
						<div class="row">
							<table id="maxTimeplaceOrderTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%" style="border-top: none;">
								<tbody>
									<tr>
										<td width="70%">小河路口</td>
										<td>
											<div class="fi-ceng-out">
												<button class="btn btn-xs btn-bordered">30 &nbsp;分钟</button>
												<div class="fi-ceng alert-info cs-ceng">
													<h4 class="cs-ceng-t">小河路口</h4>
													MAC地址：76:93:ac:13:45:25<br> 进入时间：2016.6.21 12:00:01<br>
													离开时间：2016.6.21 12:30:01
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td>经开分局门口</td>
										<td>
											<div class="fi-ceng-out">
												<button class="btn btn-xs btn-bordered">15 &nbsp;分钟</button>
												<div class="fi-ceng alert-info cs-ceng">
													<h4 class="cs-ceng-t">经开分局门口</h4>
													MAC地址：76:93:ac:13:45:25<br> 进入时间：2016.6.21 12:00:01<br>
													离开时间：2016.6.21 12:30:01
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td>XXX局门口</td>
										<td>
											<div class="fi-ceng-out">
												<button class="btn btn-xs btn-bordered">25 &nbsp;分钟</button>
												<div class="fi-ceng alert-info cs-ceng">
													<h4 class="cs-ceng-t">XXX局门口</h4>
													MAC地址：76:93:ac:13:45:25<br> 进入时间：2016.6.21 12:00:01<br>
													离开时间：2016.6.21 12:30:01
												</div>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>


				</div>
			</div>

			<div class="m-ui-title3">
				<h2>
					<span class="glyphicon glyphicon-map-marker "
						style="color: #ff5454; margin-right: 5px; vertical-align: middle;"></span>轨迹地图
				</h2>
			</div>
			<div class="row">
				<div id="wifiLocusMapConten" style="margin-right: 1px; height: 450px; overflow: hidden;">
			</div>

			<div class="m-ui-title3 mar-top">
				<h2>
					<span class="glyphicon glyphicon-fire"
						style="color: #ff5454; margin-right: 5px; vertical-align: middle;"></span>热力图
				</h2>
			</div>

			<div class="row">
				<div id="wifiLocusHotMapConten" style="height: 450px; overflow: hidden;">
			</div>

		</div>
	</div>
	</div>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/ajgl/ajglInstruction/wifiLocusAnalyzeSnapshot.js"></script>
</body>
</html>