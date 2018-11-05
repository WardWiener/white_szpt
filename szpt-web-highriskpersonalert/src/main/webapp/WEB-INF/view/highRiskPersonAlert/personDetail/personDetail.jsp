<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/highRiskPersonAlert/common/arcgisMapCommon.js"></script>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/highRiskPersonAlert/common/constant.jsp"%>
<!-- acrgis地图弹窗样式 -->
<style type="text/css">
.esriPopup .sizer {
	position: relative;
	width: 338px;
	z-index: 1;
}

.esriPopup .contentPane {
	position: relative;
	max-height: 500px;
	overflow: auto;
	padding: 0px;
	background-color: #F7F7F7;
	color: #333333;
}
</style>
</head>
<body id="validform" class="validform">
<input id="paraIdcode" type="hidden" value="<%=request.getParameter("idcode") %>">
<input id="paraPersonId" type="hidden" value="<%=request.getParameter("personId") %>">
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
					<li class="active"><a href="#">人员档案</a></li>
				</ol>
				<div id="c-center-right">
			<div class="m-ui-title1">
					<h1>
						<button id="backViewBtn" class="btn btn-primary"
							style="margin-right: 20px;">
							<span class="glyphicon glyphicon-menu-left"></span>返回
						</button>
						人员档案
					</h1>
				</div>
				<div class="row row-mar" style="width:400px; float:left; margin-top:-15px; margin-left:225px ;display:none">
				   <div class="col-xs-4"> <label class="control-label">身份证号：</label></div>
				    <div class="col-xs-5"><input id="inputIdCode" type="text" class="form-control input-sm valiform-keyup form-val" datatype="*18-18" errormsg="请正确填写身份证号"></div>
				    <div class="col-xs-3"id="search"><button id="search"class="btn btn-primary btn-sm">查询他人</button></div>
				</div> 
				<div id="c-center-right-content-block">
				<div id="c-center-right-content-bar" style="width:260px; margin-right:-280px; padding:0">
					<div class="panel panel-default  alert-info" style="min-height: 685px">
							 <div class="panel-heading" style="background-color:#1d98de; color:#fff;">人员信息
							  <p style="padding-top:10px;">
							  <button id="edit" class="btn btn-primary btn-sm" style="margin-left:0; border-color:#95deff;">打标</button>
							 </p>
							  </div>
								<table class="table table-sg table-sg-sm table-info" cellspacing="0" width="100%">
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
							<div class="m-ui-title1"><h1 class="pull-left">查控情况<!-- <span class="glyphicon glyphicon glyphicon-random micon-lg" style="color:#32e3f3; margin-left:5px; margin-right:15px"></span> --></h1>
								<button id="checkControl" class="btn btn-primary btn-sm" style="font-size: 12px;float: right;margin-top: 10px;">查控</button>
							</div>
							<div class="m-ui-table" style="margin-right: 10px">
								<table id="checkControlTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%"></table>
							</div>
							
							<div class="m-ui-title1"><h1 class="pull-left">轨迹信息<span class="glyphicon glyphicon glyphicon-random micon-lg" style="color:#32e3f3; margin-left:5px; margin-right:15px"></span></h1>
								<div id="macs"class="pull-left" style="margin-left:30px; padding-top:8px;"></div>
							</div>
							<!---地图--->
							<div class="row">
								<div class="col-xs-8">
									<div class="m-ui-table" style="margin-right: 10px">
										<table id="locusTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%"></table>
									</div>
								</div>
								<div class="col-xs-4">
									<div class="row row-mar mar-top">
										<button id="recentlyDay"class="btn btn-primary btn-xs ">近一天</button>
										<button id="recentlyWeek"class="btn btn-primary btn-xs ">近一周</button>
										<button id="recentlyMonth"class="btn btn-primary btn-xs ">近一月</button>
									</div>

									<div class="panel panel-primary">
										<div class="panel-heading">
											<h3 class="panel-title">该人员经过次数最多的场所</h3>
										</div>
										<div class="panel-body">
											<table id="placeFrequencyOrderTable" class="table table-bordered m-ui-table-no-paginate" cellspacing="0" width="100%">
												<tbody>
													
												</tbody>
											</table>
										</div>
									</div>
									<div class="panel panel-primary">
										<div class="panel-heading">
											<h3 class="panel-title">该人员驻留时间最长的场所</h3>
										</div>
										<div class="panel-body">
											<table id="maxTimeplaceOrderTable" class="table table-bordered m-ui-table-no-paginate" cellspacing="0" width="100%">
												<tbody>
													
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
								<!-----tabs-1end----->

								<div class="m-ui-title1"><h1>轨迹地图<span class="glyphicon glyphicon-map-marker micon-lg " style="color:#ff0000; margin-left:5px; margin-right:15px"></span></h1></div>
									<div id="locusMapConten" style="height: 595px;"></div>

							<div class="m-ui-title1"><h1>热力图<span class="glyphicon glyphicon-fire micon-lg" style="color:#ff0000; margin-left:5px; margin-right:15px"></span></h1></div>
										<div class="row row-mar mar-top">
										<div id="heatDateRange" class="dateRange ">
											<button id="recentlyDayHeat"class="btn btn-primary btn-xs heatTime" style="margin-left: 0">近一天</button>
											<button id="recentlyWeekHeat"class="btn btn-primary btn-xs heatTime" >近一周</button>
											<button id="recentlyMonthHeat"class="btn btn-primary btn-xs heatTime">近一月</button>
											<button id="recentlySixMonthHeat"class="btn btn-primary btn-xs heatTime">近半年</button>
										
											<input type="hidden" id="dtfmt" class="dateFmt" value="info@yyyy-MM-dd HH:mm:ss" />
											<div style="display:inline-block; width:166px; vertical-align:middle">
												<div class="input-group" style="margin-right:10px;">
													<input type="text" id="fixed_start" class="laydate-start form-control input-sm" readonly="readonly">
													<span class="laydate-start-span input-group-addon m-ui-addon">
														<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
													</span>
												</div>
											</div>
											<div style="display:inline-block;width:20px; text-align:center">-</div>
											<div style="display:inline-block; width:160px; vertical-align:middle">
												<div class="input-group" style="padding-left:5px;">
													<input type="text" id="fixed_end" class="laydate-end form-control input-sm" readonly="readonly">
													<span class="laydate-end-span input-group-addon m-ui-addon">
														<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
													</span>
												</div>
											</div>
												<button id="heatSearch" class="btn btn-primary btn-sm"
													style="font-size: 12px">查询</button>
												<button id="resetTime" class="btn btn-default btn-sm"
													style="font-size: 12px">重置</button>

									</div>
									<!---热力图查询时间--->
									<div id="locusHotMapConten" style="height: 595px;"></div>
							<!---地图-
							 <!-- 
							<div class="m-ui-title1 row-mar mar-top"><h1>前科信息</h1></div>
							  <div class="photo-list an-list an-list2 an-list3">  
							    <ul>
							                     <li>  
							                               <h2 class="title"><a class="criminalRecordMessage" caseId="09283710-e3df-4745-998c-7c3bf7a18c74" href="javascript:void(0)">6.15冈特路冈特小区抢劫案</a></h2>
							                               <p class="m-line"></p>
							                               <p><span class="color-gray">案件编号：</span>A3700021201501210001</p>
							                               <p><span class="color-gray">发案时间：</span>2015-01-12 &nbsp; &nbsp;18：00</p>
							                               <p><span class="color-gray">发案地点：</span>小河区</p>
							                               <p><span class="color-gray">案件类别：</span>抢劫案</p>
							                               
							                               <div class="row item-num">
							                                  <div class="col-xs-3"><p>嫌疑人</p>  <span class="num">1</span></div>
							                                   <div class="col-xs-3"><p>物证</p>   <span class="num">3</span></div>
							                                   <div class="col-xs-3"><p>卷宗</p>    <span class="num">10</span></div>
							                                   <div class="col-xs-3"><p>情报线索</p>  <span class="num">20</span></div>
							                              </div>
							                      </li>
							                     <li>  
							                               <h2 class="title"><a class="criminalRecordMessage" caseId="09283710-e3df-4745-998c-7c3bf7a18c74" href="javascript:void(0)">某某某盗窃案</a></h2>
							                               <p class="m-line"></p>
							                               <p><span class="color-gray">案件编号：</span>A3700021201501210001</p>
							                               <p><span class="color-gray">发案时间：</span>2015-01-12 &nbsp; &nbsp;18：00</p>
							                               <p><span class="color-gray">发案地点：</span>小河区</p>
							                               <p><span class="color-gray">案件类别：</span>盗窃案</p>
							                               
							                               <div class="row item-num">
							                                  <div class="col-xs-3"><p>嫌疑人</p>  <span class="num">1</span></div>
							                                   <div class="col-xs-3"><p>物证</p>   <span class="num">3</span></div>
							                                   <div class="col-xs-3"><p>卷宗</p>    <span class="num">10</span></div>
							                                   <div class="col-xs-3"><p>情报线索</p>  <span class="num">20</span></div>
							                              </div>
							                      </li>
							  </ul>
							  </div>-->
						</div>
					</div>
				
				</div>
			</div>
		</div>
	</div>
</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<script type="text/javascript">
		var url = "<%=request.getParameter("url")%>";
		var clickOrder = "<%=request.getParameter("clickOrder")%>";
</script>
<script type="text/javascript" src="<%=context%>/scripts/highRiskPersonAlert/personDetail/personDetail.js"></script>
</html>