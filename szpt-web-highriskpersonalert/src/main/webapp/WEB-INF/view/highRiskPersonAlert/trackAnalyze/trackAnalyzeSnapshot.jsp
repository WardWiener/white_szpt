<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/highRiskPersonAlert/common/constant.jsp"%>
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/highRiskPersonAlert/common/arcgisMapCommon.js"></script>
</head>
<body>
<input id="paraIdcode" type="hidden" value="<%=request.getParameter("idcode") %>">
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<div id="c-center">
		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-highRiskPersonAlert.jsp"%>
		</div>
		<div id="c-center-right">
			<div id="c-center-right-content">
				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">高危人群分析</a></li>
					<li class="active"><a href="#">轨迹分析</a></li>
				</ol>
				<div class="row row-mar"
					style="width: 900px; float: right; margin-top: -10px;">
					<div class="col-xs-1">
						<label class="control-label">身份证：</label>
					</div>
					<div class="col-xs-2" style="width: 150px; margin-right: 10px">
						<input id="idcode" type="text" class="form-control input-sm">
					</div>

					<div id="trackDateRange" class="col-xs-3 dateRange" style="width: 270px">
						<input type="hidden" id="dtfmt" class="dateFmt" value="info@yyyy-MM-dd HH:mm:ss" />
						<div
							style="display: inline-block; width: 120px; vertical-align: middle">
							<div class="input-group">
								<input type="text" id="fixed_start" class="laydate-start form-control input-sm" readonly="readonly">
								<span class="laydate-start-span input-group-addon m-ui-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
							</div>
						</div>
						<div
							style="display: inline-block; width: 20px; text-align: center">-</div>
						<div
							style="display: inline-block; width: 120px; vertical-align: middle">
							<div class="input-group">
								<input type="text" id="fixed_end" class="laydate-end form-control input-sm" readonly="readonly">
								<span class="laydate-end-span input-group-addon m-ui-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
							</div>
						</div>
					</div>
					<div class="col-xs-3">
						<button id="search" class="btn btn-primary btn-sm">查询</button>
						<button id="snapshot" class="btn btn-primary btn-sm">生成分析快照</button>
						<button id="reset"class="btn btn-default btn-sm">重置</button>
					</div>

					<!--<div class="col-xs-1">
						<label class="control-label">身份证：</label>
					</div>
					<div class="col-xs-2" style="width: 150px; margin-right: 10px">
						<input id="idCodeOther" type="text" class="form-control input-sm">
					</div>
					 <div class="col-xs-1">
						<button id="searchOther" class="btn btn-primary btn-sm">查询他人</button>
					</div> -->
				</div>

				<div id="c-center-right-content-block">

					<div id="c-center-right-content-bar"
						style="width: 260px; margin-right: -280px; padding: 0">
						<div class="panel panel-default  alert-info"
							style="min-height: 685px">
							<div class="panel-heading"
								style="background-color: #1d98de; color: #fff;">人员信息</div>
							<table class="table table-sg table-sg-sm table-info"
								cellspacing="0" width="100%">
								<tbody>
									<tr>
										<td width="75">姓名：</td>
										<td><h3 id="name"></h3></td>
									</tr>
									<tr>
										<td>预警类型：</td>
										<td>
											<div class="warning-type warning-type-dd"style="display:none;">
												<span id="warnType" class="btn sq-red selected"></span>
											</div>
										</td>
									</tr>
									<tr>
										<td>身份证号：</td>
										<td id="idCode"></td>
									</tr>
									<tr>
										<td>性别：</td>
										<td id="sex"></td>
									</tr>
									<tr>
										<td>前科类型：</td>
										<td id="criminalRecord"></td>
									</tr>
									<tr>
										<td>有无异常：</td>
										<td id="status"></td>
									</tr>
									<tr>
										<td>现住地址：</td>
										<td id="liveAddress"></td>
									</tr>
									<tr>
										<td>户籍地址：</td>
										<td id="registerAddress"></td>
									</tr>
									<tr>
										<td>职业：</td>
										<td id="profession"></td>
									</tr>
									<tr>
										<td>收入：</td>
										<td id="income"></td>
									</tr>
									<tr>
										<td>联系电话：</td>
										<td id="phone"></td>
									</tr>
									<tr>
										<td>采集时间：</td>
										<td id="createdTime"></td>
									</tr>
									<tr>
										<td>尿检结果：</td>
										<td id="urineTest"></td>
									</tr>
									<tr>
										<td>MAC地址：</td>
										<td id="mac"></td>
									</tr>
									<tr>
										<td>人员类型：</td>
										<td id="peopleType"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

					<div id="c-center-right-content-con">
						<div class="right-inner" style="margin-left: 280px;">

							<!------------------轨迹信息------------------>
							<div class="m-ui-title1">
								<h1>
									联合轨迹<span class="glyphicon glyphicon glyphicon-random micon-lg"
										style="color: #32e3f3; margin-left: 5px; margin-right: 15px"></span>
								</h1>
							</div>

							<div class="row light-block"
								style="padding: 10px 0; border: 1px solid #434874;">
								<div class="col-xs-8">
									<div class="history-content">
										<ul id="trackTimeShaft">
											
											<li class="clear"></li>
										</ul>
									</div>
								</div>
								<div class="col-xs-4">
									<div class="row row-mar hotel-pannel">
										<table id="countTable1"
											class="table table-bordered table-hover m-ui-table-whole"
											cellspacing="0" width="100%">
											<thead>
												<tr>
													<th colspan="2" width="50%">旅馆酒店</th>
													<th colspan="2">从贵阳乘火车 - 目的地</th>
												</tr>
											</thead>
											<tbody>
												<tr class="dataTr">
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
												<tr class="dataTr">
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
												<tr class="dataTr">
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
												<tr>
													<td colspan="2"><a data="旅馆酒店"id="hotelAll"class="a-link mar-right-sm all">全部</a></td>
													<td colspan="2"><a data="从贵阳乘火车 - 目的地"id="trainOutAll"class="a-link mar-right-sm all">全部</a></td>
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
												<tr class="dataTr">
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
												<tr class="dataTr">
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
												<tr class="dataTr">
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
												<tr>
													<td colspan="2"><a data="网吧"id="internetBarAll"class="a-link mar-right-sm all">全部</a></td>
													<td colspan="2"><a data="乘火车到贵阳 - 起始地"id="trainInAll"class="a-link mar-right-sm all">全部</a></td>
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
													<th colspan="2">从贵阳乘飞机- 目的地</th>
												</tr>
											</thead>

											<tbody>
												<tr class="dataTr">
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
												<tr class="dataTr">
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
												<tr class="dataTr">
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
												<tr>
													<td colspan="2"><a data="监所"id="monitoringGuardAll"class="a-link mar-right-sm">全部</a></td>
													<td colspan="2"><a data="从贵阳乘飞机- 目的地"id="airPlaneInAll"class="a-link mar-right-sm all">全部</a></td>
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
													<th colspan="2">乘飞机到贵阳 - 起始地</th>
												</tr>
											</thead>

											<tbody>
												<tr class="dataTr">
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
												<tr class="dataTr">
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
												<tr class="dataTr">
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
												<tr>
													<td colspan="2"><a data="WIFI"id="wifiAll"class="a-link mar-right-sm">全部</a></td>
													<td colspan="2"><a data="乘飞机到贵阳 - 起始地"id="airPlaneOutAll"class="a-link mar-right-sm all">全部</a></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>

							</div>
							<!------------------轨迹信息------------------>

							

							<!------------------轨迹地图------------------>
							<div class="m-ui-title1">
								<h1>
									轨迹地图<span class="glyphicon glyphicon-map-marker micon-lg "
										style="color: #ff0000; margin-left: 5px; margin-right: 15px"></span>
								</h1>
							</div>
							<div class="row"><div class="m-ui-table">
								<table id="table" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%"></table>
							</div></div>
							<div class="row"><div id="locusMapConten" style="height: 600px;margin-top:20px"></div></div>
							<!------------------轨迹地图------------------>

							<!------------------热力图------------------>
							<div class="m-ui-title1">
								<h1>
									轨迹热点<span class="glyphicon glyphicon-fire micon-lg"
										style="color: #ff0000; margin-left: 5px; margin-right: 15px"></span>
								</h1>
							</div>


							<!---热力图查询时间--->
							<div class="row"><div id="locusHotMapConten" style="height: 510px;"></div></div>
							<!------------------热力图------------------>

						</div>
					</div>
				</div>
				<!--c-center-right-content-block结束-->


			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/trackAnalyze/trackAnalyze.js"></script>
</html>