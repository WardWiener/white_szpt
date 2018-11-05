<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.taiji.pubsec.szpt.util.Constant"%> 
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

var snapshotId = '<%=request.getParameter("snapshotId")%>'; 

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
	href="<%=context%>/ajgl/custom/ajgl/default/style/branch.css">
<link rel="stylesheet"
	href="<%=context%>/ajgl/custom/ajgl/default/style/cs.css">
	
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<script type="text/javascript" src="<%=context%>/custom/arcgis/multiBaseMap.js"></script>
<script type="text/javascript">
	var YJLX_HONG_SE = "<%= Constant.DICT.DICT_YJLX_RED.getValue() %>";
	var YJLX_CHENG_SE = "<%= Constant.DICT.DICT_YJLX_ORANGE.getValue() %>";
	var YJLX_HUANG_SE = "<%= Constant.DICT.DICT_YJLX_YELLOW.getValue() %>";
	var YJLX_BAI_SE = "<%= Constant.DICT.DICT_YJLX_WHITE.getValue() %>";
	var YJLX_LAN_SE = "<%= Constant.DICT.DICT_YJLX_BLUE.getValue() %>";
	var YJLX_OTHER = "<%= Constant.DICT.DICT_YJLX_OTHER.getValue() %>";
</script>
</head>
<body class="cs-bg">
	<div class="cs-content">
		<div class="cs-content-top" id="divMouseMove">
			<h2>轨迹分析</h2>
			<a id="btn-close-window" href="####" style="transform: rotate(0deg);"></a>
		</div>
		<div id="highriskPersonInfo" class="cs-content-main">

			<div class="m-ui-title3 row">
				<h2 class="pull-left">
					<span class="glyphicon glyphicon-user"
						style="color: #5894e7; margin-right: 5px; vertical-align: middle;"></span>人员信息
				</h2>
				<h4 name="name" class="pull-left m-bold" style="margin-left: 20px"></h4>
			</div>
			<table class="table table-sg table-sg-sm table-info" cellspacing="0"
				width="100%">
				<tbody>
					<tr>
						<td class="td-left">身份证号：</td>
						<td name="idcode"></td>
						<td class="td-left">性别：</td>
						<td name="sexName"></td>

					</tr>
					<tr>
						<td class="td-left">预警类型：</td>
						<td>
							<div id="warnType" class="warning-type warning-type-dd">
								
							</div>
						</td>
						<td class="td-left">人员类别：</td>
						<td colspan="3" name="peopleTypeName"></td>
					</tr>
					<tr>
						<td class="td-left">前科类型：</td>
						<td name="criminalRecordName"></td>
						<td class="td-left">有无异常：</td>
						<td name="statusName"></td>
					</tr>
					<tr>
						<td class="td-left">现住地址：</td>
						<td name="liveAddress"></td>
						<td class="td-left">户籍地址：</td>
						<td name="registerAddress"></td>
					</tr>
					<tr>
						<td class="td-left">职业：</td>
						<td name="professionName"></td>
						<td class="td-left">收入：</td>
						<td name="income"></td>
					</tr>
					<tr>
						<td class="td-left">尿检结果：</td>
						<td name="urineTest"></td>
						<td class="td-left">采集时间：</td>
						<td name="createdTime"></td>
					</tr>
					<tr>
						<td class="td-left">联系电话：</td>
						<td id="phone"></td>
						<td class="td-left">MAC地址：</td>
						<td id="mac"></td>
					</tr>
					<tr>

					</tr>
				</tbody>
			</table>
			<div class="m-ui-title3 row">
				<h2 class="pull-left">
					<span class="glyphicon glyphicon-random"
						style="color: #5894e7; margin-right: 10px; vertical-align: middle;"></span>联合轨迹
				</h2>
			</div>
			<div class="row light-block">
				<div class="col-xs-8">
					<div class="history-content-cs" style="max-height: 600px;overflow: auto;margin-right: 5px;">
						<ul id="trackTimeShaft">
							<li class="clear"></li>
						</ul>
					</div>
				</div>

				<div class="col-xs-4">
					<div style="max-height: 600px; overflow: auto;">
						<div class="row row-mar hotel-pannel">
							<table id="countTable1"
								class="table table-bordered table-hover m-ui-table-whole"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th colspan="2" width="50%">旅馆入住</th>
										<th colspan="2">从贵阳乘火车 - 目的地</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td colspan="2"><a id="hotelAll" class="a-link mar-right-sm">全部</a></td>
										<td colspan="2"><a id="trainOutAll" class="a-link mar-right-sm">全部</a></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="row row-mar hotel-pannel">
							<table id="countTable2"
								class="table table-bordered table-hover m-ui-table-whole"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th colspan="2" width="50%">网吧</th>
										<th colspan="2">乘火车到贵阳 - 起始地</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td colspan="2"><a id="internetBarAll" class="a-link mar-right-sm">全部</a></td>
										<td colspan="2"><a id="trainInAll" class="a-link mar-right-sm">全部</a></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="row row-mar hotel-pannel">
							<table id="countTable3"
								class="table table-bordered table-hover m-ui-table-whole"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th colspan="2" width="50%">监所</th>
										<th colspan="2">乘飞机到贵阳 - 起始地</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td colspan="2"><a id="monitoringGuardAll" class="a-link mar-right-sm">全部</a></td>
										<td colspan="2"><a id="airPlaneInAll" class="a-link mar-right-sm">全部</a></td>
									</tr>
								</tbody>
							</table>

						</div>

						<div class="row row-mar hotel-pannel">
							<table id="countTable4"
								class="table table-bordered table-hover m-ui-table-whole"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th colspan="2" width="50%">WIFI</th>
										<th colspan="2">从贵阳乘飞机- 目的地</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td colspan="2"><a id="wifiAll" class="a-link mar-right-sm">全部</a></td>
										<td colspan="2"><a id="airPlaneOutAll" class="a-link mar-right-sm">全部</a></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="m-ui-title3 row">
				<h2 class="pull-left">
					<span class="glyphicon glyphicon-map-marker"
						style="color: #ff5454; margin-right: 5px; vertical-align: middle;"></span>轨迹地图
				</h2>
			</div>
			<div class="row text-center row-mar">
				<div id="locusPointMapConten" style="margin-right: 1px; height: 450px; overflow: hidden;">
				
				</div>
			</div>
			<div class="m-ui-title3 row mar-top">
				<h2 class="pull-left">
					<span class="glyphicon glyphicon-fire" style="color: #ff5454; margin-right: 5px; vertical-align: middle;"></span>轨迹热点
				</h2>
			</div>
			<div class="row  text-center">
				<div id="locusHotMapConten" style="height: 450px; overflow: hidden;">
				
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/ajgl/ajglInstruction/trackAnalyzeSnapshot.js"></script>
</body>
</html>