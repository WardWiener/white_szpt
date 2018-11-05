<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/common/library/font-awesome/css/font-awesome.css" />
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<script type="text/javascript"
	src="<%=context%>/custom/arcgis/multiBaseMap.js"></script>
<script type="text/javascript"
	src="<%=context%>/common/library/bootstrap/js/scrollspy.js"></script>
<script type="text/javascript"
	src="<%=context%>/custom/szpt/js/thumbslider.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/custom/szpt/style/thumbslider.css" />
<script>
	$(document).ready(function() {
		$('body').scrollspy({
			target : '#navbar-example'
		});
	});
</script>
</head>

<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>

	<div id="c-center">

		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-dagl.jsp"%>
		</div>
		<input id="caseId" type="hidden"
			value="<%=request.getParameter("caseId")%>">
		<div id="c-center-right">
			<div id="c-center-right-content">

				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">档案管理</a></li>
					<li><a href="#">一人一档 高危人/嫌疑人</a></li>
					<li><a href="#">详情</a></li>
				</ol>
				<div class="m-ui-title1">
					<h1>
						<button id="backViewBtn" class="btn btn-primary"
							style="margin-right: 20px;">
							<span class="glyphicon glyphicon-menu-left"></span>返回
						</button>
						 高危人/嫌疑人详情
					</h1>
				</div>
				<!-----------------------左侧内容开始-------------------------->
				<div style="margin-right: 120px;">
					<div class="main-block" id="bs-scroll-0">
						<div class="row person-block">

							<div class="col-xs-3 left">
								<!-------图片轮播-------->
								<div id="topthumbslider" class="thumbslider">
									<div class="thumbslider-prev">
										<a href="javascript:void(0)"></a>
									</div>
									<div class="thumbslider-show" style="width:340px;height:300px"></div>

									<div class="thumbslider-next">
										<a href="javascript:void(0)"></a>
									</div>

									<div class="thumbslider-circle">
										<ul class="thumbslider-circle-ul">

										</ul>
									</div>
								</div>
							</div>

							<!-------图片轮播-------->
							<div id="personalInfo" class="col-xs-9 right">
								<input type="hidden" id="yrydId">
								<div id="personalInfo" class="col-info">

									<h2 id="name" valName="name" class="valCell"></h2>
									<span class="icon-xyren mar-left-sm xyr" title="嫌疑人" style="display:none"></span> <span
										valName="sex" class="valCell" style="margin-right: 50px;"></span>
									<button id="concernBtn" class="btn btn-sm  btn-danger" style="display: none;">关注</button>
									<button id="cancelConcernBtn" class="btn btn-sm  btn-danger"
										style="display: none;">取消关注</button>
									<div class="row color-gray2">
										<p id="warning-type" class="warning-type">
											<!---sq-red、 sq-orange、sq-yellow、sq-blue、sq-white、---->
										</p>
										人员类别：<span valName="persontype" class="valCell"></span>
									</div>
									<p class="m-line"></p>
									<p>
										<span class="color-gray">身份证号：</span> <span id="yrydIdNum"
											valName="idcard" class="valCell"></span>
									</p>
									<p>
										<span class="color-gray">联系电话：</span><span valName="phone"
											class="valCell"></span>
									</p>
									<p class="m-line"></p>
									<div class="row">
										<div class="col-xs-6">
											<p>
												<span class="color-gray">曾用名：</span></span><span valName="oldname"
													class="valCell"></span>
											</p>
											<p>
												<span class="color-gray">民族：</span></span><span valName="nation"
													class="valCell"></span>
											</p>
											<p>
												<span class="color-gray">出生日期：</span></span><span
													valName="birthday" class="valCell"></span>
											</p>
										</div>
										<div class="col-xs-6">
											<p>
												<span class="color-gray">婚姻状况：</span></span><span valName="marry"
													class="valCell"></span>
											</p>
											<p>
												<span class="color-gray">职业：</span></span><span
													valName="occupation" class="valCell"></span>
											</p>
											<p>
												<span class="color-gray">文化程度：</span></span><span valName="culture"
													class="valCell"></span>
											</p>

										</div>
									</div>
									<p class="m-line"></p>
									<p>
										<span class="color-gray">户籍地址：</span></span><span
											valName="birthaddress" class="valCell"></span>
									</p>
									<p>
										<span class="color-gray">现住地址：</span></span><span valName="localAddress"
											class="valCell"></span>
									</p>
								</div>
							</div>
						</div>
					</div>
					<div class="main-block" id="bs-scroll-1">
						<div class="m-ui-title3">
							<h2>
								<span class="icon-facetime-video title1-icon"></span>办案区采集信息
							</h2>
							<div class="pull-right" style="margin-top: -60px;">
								<button id="addInstruct" class="btn btn-primary">新增指令</button>
							</div>
						</div>
						<div class="pull-left"
							style="margin-top: -43px; width: 400px; margin-left: 250px;">
							<div class="row">
								<div class="col-xs-4">
									指纹信息：<span id="zw"></span>
								</div>
								<div class="col-xs-4">
									DNA信息：<span id="dna"></span>
								</div>
								<div class="col-xs-4">
									尿液：<span id="ny"></span>
								</div>
							</div>
						</div>
						<!--------------------手机QQ邮箱-------------------->
						<div class="row">
							<div class="col-xs-4">
								<div class="alert alert-p light-block-er show-hide-group"
									style="min-height: 100px; margin-right: 10px;">
									<h3 class="color-blue1 text-center font14">
										手机<a href="###" class="show-hide-btn mar-left"><span
											class="icon-angle-down font18"></span></a>
									</h3>
									<div class="row">
										<div class="pull-left" style="width: 60px;">
											<img src="../images/icon/phone.png">
										</div>
										<div class="pull-left">
											<div id="phoneInfoOneDiv">
												
											</div>
											<div id="phoneInfoMoreDiv" class="show-hide-content">
												
											</div>
										</div>
									</div>
								</div>
							</div>


							<div class="col-xs-4">
								<div class="alert alert-p light-block-er show-hide-group"
									style="min-height: 100px; margin-right: 5px; margin-left: 5px;">
									<h3 class="color-blue1 text-center font14">
										QQ<a href="###" class="show-hide-btn mar-left"><span
											class="icon-angle-down font18"></span></a>
									</h3>

									<div class="row">
										<div class="pull-left" style="width: 60px;">
											<img src="../images/icon/qq.png">
										</div>
										<div class="pull-left">
											<div id="qqInfoOneDiv">
											
											</div>
											<div id="qqInfoMoreDiv" class="show-hide-content">
												
											</div>
										</div>
									</div>
								</div>
							</div>


							<div class="col-xs-4">
								<div class="alert alert-p light-block-er show-hide-group"
									style="min-height: 100px; margin-left: 10px;">
									<h3 class="color-blue1 text-center font14">
										微信<a href="###" class="show-hide-btn mar-left"><span
											class="icon-angle-down font18"></span></a>
									</h3>

									<div class="row">
										<div class="pull-left" style="width: 60px;">
											<img src="../images/icon/weixin.png">
										</div>
										<div class="pull-left">
											<div id="weiXinInfoOneDiv">
												
											</div>
											<div id="weiXinInfoMoreDiv" class="show-hide-content">
												
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!--------------------手机QQ邮箱-------------------->
						<div class="text-center color-gray" style="display: none">尚未采集该嫌疑人的相关数据</div>
					</div>
					<div class="main-block" id="bs-scroll-2">
						<div class="m-ui-title3">
							<h2>
								<span class="glyphicon glyphicon-info-sign"></span>前科信息
							</h2>
						</div>
						<div class="m-ui-table">
							<table id="qkxxTable"
								class="table table-bordered table-hover m-ui-table-whole"
								cellspacing="0" width="100%">
							</table>
						</div>
					</div>
					<!-- <div class="main-block" id="bs-scroll-4" style="display:none;"> -->
					<div style="display:none;">
						<div class="m-ui-title3">
							<h2>
								<span class="glyphicon glyphicon-home"></span>户籍与暂住
							</h2>
						</div>
						<div class="main-block-content">
							<h5 class="row-mar font16 color-yellow1">户籍信息</h5>
							<div class="m-ui-table">
								<table id="censusInfo"
									class="table table-bordered table-hover m-ui-table-whole"
									cellspacing="0" width="100%">
								</table>
							</div>
							<h5 class="row-mar mar-top font16 color-yellow1">暂住信息</h5>
							<div class="m-ui-table">
								<table id="stayInfo"
									class="table table-bordered table-hover m-ui-table-whole"
									cellspacing="0" width="100%">
								</table>
							</div>
						</div>
					</div>
					<!--户籍与暂住end-->
					<div class="main-block" id="bs-scroll-3" style="min-height: 40px;">
						<div class="m-ui-title3">
							<h2>
								<span class="glyphicon glyphicon-modal-window"></span>指令反馈
							</h2>
						</div>
						<div id="zlfkDiv" class="main-block-content"
							style="max-height: 400px; overflow: auto; margin-top: -65px;">
							<div  class="history-content history-content3">
								<ul id="instructUl" style="border-color: #aaa;">
									<li id="odd" class="odd instruct" valBq="li"
										style="display: none;"><span class="icon-red-dot"></span>
										<div class="time-box">
											<p>
												<span valName="createTime" class="valCell">09:02</span>
												&nbsp;&nbsp;<span valName="createDate" class="valCell">2016年4月14日</span>
											</p>
										</div>
										<div class="con-box">
											<span class="arrow"></span><span valName="feedbackDepartment"
												class="valCell"></span> 反馈
											<p>
												反馈内容：<span valName="feedbackContent" class="valCell"></span>
											</p>
											<span style="display: none;" valName="id" class="valCell"></span>
										</div></li>
									<li id="even" class="even instruct" valBq="li"
										style="display: none;"><span class="icon-red-dot"></span>
										<div class="time-box">
											<p>
												<span valName="createTime" class="valCell">09:11</span>
												&nbsp;&nbsp;<span valName="createDate" class="valCell">2016年4月14日</span>
											</p>
										</div>
										<div class="con-box">
											<span class="arrow"></span><span valName="createDepartment"
												class="valCell"></span> 下发指令给 <span
												valName="acceptDepartments" class="valCell"></span>
											<p>
												指令内容：<span valName="content" class="valCell"></span>
											</p>
											<span style="display: none;" valName="id" class="valCell"></span>
										</div></li>
									<li id="instructClear" class="clear"></li>
								</ul>
							</div>
						</div>
					</div>
					<!--指令反馈end-->

					<div class="main-block light-block-er" id="bs-scroll-4">
						<div class="m-ui-title3">
							<h2>
								<span class="glyphicon glyphicon-random"></span>分析研判快照
							</h2>
						</div>
						<div id="tabs" class="m-ui-tabs" style="background: none;">
							<ul class="nav nav-tabs">
								<li id="trackA"><a href="#tabs-1">轨迹分析</a></li>
								<li><a href="#tabs-2">实时WIFI设备监控</a></li>
								<li><a href="#tabs-3" style="display:none;">关联分析</a></li>
								<li id="wifiLocusA"><a href="#tabs-4">WIFI轨迹分析</a></li>
								<li id="controlLi"><a href="#tabs-5">布控结果查看</a></li>
							</ul>
							<!--tabs-1轨迹分析-->
							<div id="tabs-1">
								<%@include file="fxypkz/trackAnalyze.jsp"%>
							</div>
							<!--tabs-1轨迹分析-->

							<!--tabs-2实时WIFI设备监控-->
							<div id="tabs-2">
								<%@include file="fxypkz/sswfjk.jsp"%>
							</div>
							<!--tabs-2实时WIFI设备监控-->

							<!--tabs-3关联分析-->
							<div id="tabs-3">
								<p class="mar-top font12 color-gray2">
									保存快照原因：XXXXX。<span class="color-green1">已推送办案民警</span><span
										class="color-orange1">办案民警未查看</span>
								</p>
								<p style="line-height: 40px;">
									分析时间：2016-01-03 到 2016-02-03 <span style="margin-left: 30px;">关联类型：同住关系，飞机同行</span>
								</p>
								<div class="row" style="height: 500px; overflow: hidden;">
									<img src="../images/map/map-guanlian.png"height:"100%">
								</div>
							</div>
							<!--tabs-3关联分析-->

							<!--tabs-4WIFI轨迹分析-->
							<div id="tabs-4">
								<%@include file="fxypkz/wifiLocusAnalyze.jsp"%>
							</div>
							<!--tabs-4WIFI轨迹分析-->

							<!--tabs-5布控结果查看-->
							<div id="tabs-5">
								<%@include file="fxypkz/executeControlResult.jsp"%>
							</div>
							<!--tabs-5布控结果查看-->
						</div>
					</div>
					<!--分析研判快照end-->

					<div class="main-block" id="bs-scroll-7" style="display:none;">
						<div class="m-ui-title3">
							<h2>
								<span class="icon-lightbulb title1-icon"></span>情报线索
							</h2>
						</div>
						<div class="row">
							<div class="col-xs-6">
								<table id="" class="table table-hover table-sg table-sg-sm"
									cellspacing="0" width="100%">
									<tbody>
										<tr>
											<td><a href="#">XXX案件情报线索1</a></td>
											<td width="120">2016-04-02 10:11</td>
										</tr>
										<tr class="info">
											<td><a href="#">XXX案件情报线索2</a></td>
											<td>2016-04-02 10:11</td>
										</tr>
										<tr>
											<td><a href="#">XXX案件情报线索3</a></td>
											<td>2016-04-02 10:11</td>
										</tr>
										<tr>
											<td><a href="#">XXX案件情报线索4</a></td>
											<td>2016-04-02 10:11</td>
										</tr>
										<tr>
											<td><a href="#">XXX案件情报线索5</a></td>
											<td>2016-04-02 10:11</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="col-xs-6">
								<div class="light-block-er-er" style="padding: 10px;">
									<span class="color-green1"><span
										class="glyphicon glyphicon-ok mar-right"></span>已采用</span>
									<div class="row row-mar-sm">
										<p class="col-xs-3 m-label-left color-gray2">线索来源：</p>
										<p class="col-xs-8">日常工作</p>
									</div>
									<div class="row row-mar-sm">
										<p class="col-xs-3 m-label-left color-gray2">线索内容：</p>
										<p class="col-xs-8">群众李四举报王五贩毒，地点在中曹司桥下。</p>
									</div>
									<div class="row row-mar-sm">
										<p class="col-xs-3 m-label-left color-gray2">发生时间：</p>
										<p class="col-xs-8">2016-10-19 10:00:00</p>
									</div>
									<div class="row row-mar-sm">
										<p class="col-xs-3 m-label-left color-gray2">发生地点：</p>
										<p class="col-xs-8">中曹司桥下</p>
									</div>
									<div class="row row-mar-sm">
										<p class="col-xs-3 m-label-left color-gray2">举报人：</p>
										<p class="col-xs-8">
											赵六 <span class=" mar-left color-gray2"> 举报人联系电话：</span>184171792
										</p>
									</div>
									<div class="row row-mar-sm">
										<p class="col-xs-3 m-label-left color-gray2">采集人：</p>
										<p class="col-xs-8">
											张三 <span class=" mar-left color-gray2"> 采集时间：</span>2016-10-19
											12:00:00
										</p>
									</div>
								</div>

							</div>
						</div>
					</div>
					<div  style="display:none;">
						<div class="m-ui-title3">
							<h2>
								<span class="glyphicon glyphicon-th"></span>物品信息
							</h2>
						</div>
						<div class="main-block-content">
							<h5 class="row-mar font16 color-yellow1">车辆</h5>
							<div class="pull-right" style="margin-top: -40px;">
								<button class="btn btn-sm btn-danger">请求数据服务</button>
							</div>

							<div class="m-ui-table">
								<table id="vehicleInfo"
									class="table table-bordered table-hover m-ui-table-whole"
									cellspacing="0" width="100%">
								</table>
							</div>

							<h5 class="row-mar mar-top font16 color-yellow1">卡口记录</h5>
							<div class="row row-mar">
								<div class="col-xs-4">
									<div class="col-xs-3">
										<label class="control-label">时间：</label>
									</div>
									<div class="col-xs-3 input-group">
										<input type="text" class="form-control input-sm"
											placeholder="开始时间"> <span class="input-group-addon"><span
											class="glyphicon glyphicon-calendar"></span></span>
									</div>
									<div class="col-xs-3 to">――</div>
									<div class="col-xs-3 input-group">
										<input type="text" class="form-control input-sm"
											placeholder="开始时间"> <span class="input-group-addon"><span
											class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div>
								<div class="col-xs-3">
									<button class="btn btn-primary btn-sm">搜索</button>
								</div>
							</div>

							<div class="m-ui-table">
								<table id="cameraInfo"
									class="table table-bordered table-hover m-ui-table-whole"
									cellspacing="0" width="100%">
								</table>
							</div>

							<h5 class="row-mar font16 color-yellow1">手机</h5>

							<div class="m-ui-table">
								<table id="phoneInfo"
									class="table table-bordered table-hover m-ui-table-whole"
									cellspacing="0" width="100%">
								</table>
							</div>
						</div>
					</div>

					<div class="main-block" id="bs-scroll-5">
						<div class="m-ui-title3">
							<h2>
								<span class="glyphicon glyphicon-random"></span>轨迹信息
							</h2>
						</div>
						<div id="personTrankMap"
							style="background: #eee; height: 320px; overflow: hidden;">
						</div>
					</div>

					<div class="main-block" id="bs-scroll-6">
						<div class="m-ui-title3">
							<h2>
								<span class="glyphicon glyphicon-plane"></span>出行信息
							</h2>
						</div>
						<div class="main-block-content">
							<h5 class="row-mar font16 color-yellow1">火车</h5>
							<div class="pull-right" style="margin-top: -40px;">
								<button class="btn btn-sm btn-danger">请求数据服务</button>
							</div>
							<div class="m-ui-table">
								<table id="trainGoOutInfo"
									class="table table-bordered table-hover m-ui-table-whole"
									cellspacing="0" width="100%">
								</table>
							</div>
							<h5 class="row-mar font16 color-yellow1 mar-top">飞机</h5>
							<div class="pull-right" style="margin-top: -30px;">
								<button class="btn btn-sm btn-danger">请求数据服务</button>
							</div>
							<div class="m-ui-table">
								<table id="planeGoOutInfo"
									class="table table-bordered table-hover m-ui-table-whole"
									cellspacing="0" width="100%">
								</table>
							</div>
						</div>
					</div>
					<div class="main-block" id="bs-scroll-7">
						<div class="m-ui-title3">
							<h2>
								<span class="glyphicon glyphicon-home"></span>旅馆住宿
							</h2>
						</div>
						<div class="m-ui-table">
							<table id="hotelInfo"
								class="table table-bordered table-hover m-ui-table-whole"
								cellspacing="0" width="100%">
							</table>
						</div>
					</div>
					<div class="main-block" id="bs-scroll-8">
						<div class="m-ui-title3">
							<h2>
								<span class="glyphicon glyphicon-modal-window"></span>网吧上网
							</h2>
						</div>
						<div class="m-ui-table">
							<table id="cybercafeInfo"
								class="table table-bordered table-hover m-ui-table-whole"
								cellspacing="0" width="100%">
							</table>
						</div>
					</div>

				</div>
				<!-----------------------左侧内容结束-------------------------->
				<!-----------------------左侧悬浮开始-------------------------->
				<div style="position: fixed; right: 20px; top: 175px;">
					<div class="history-content history-content-xs" id="navbar-example">
						<ul class="nav nav-tabs" role="tablist"
							style="border-bottom: none;">
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-0"
								class="con-box"><span class="arrow"></span>基本信息</a></li>
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-1"
								class="con-box"><span class="arrow"></span>办案区采集信息</a></li>
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-2"
								class="con-box"><span class="arrow"></span>前科信息</a></li>
							<!-- <li><span class="icon-red-dot"></span><a href="#bs-scroll-3"
								class="con-box"><span class="arrow"></span>户籍与暂住</a></li> -->
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-3"
								class="con-box"><span class="arrow"></span>指令反馈</a></li>
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-4"
								class="con-box"><span class="arrow"></span>分析研判快照</a></li>
							<!-- <li><span class="icon-red-dot"></span><a href="#bs-scroll-6"
								class="con-box"><span class="arrow"></span>情报线索</a></li> -->
							<!-- <li><span class="icon-red-dot"></span><a href="#bs-scroll-7"
								class="con-box"><span class="arrow"></span>物品信息</a></li> -->
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-5"
								class="con-box"><span id="trackClick" class="arrow"></span>轨迹信息</a></li>
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-6"
								class="con-box"><span class="arrow"></span>出行信息</a></li>
							<li><span class="icon-red-dot"></span><a
								href="#bs-scroll-7" class="con-box"><span class="arrow"></span>旅馆住宿</a></li>
							<li><span class="icon-red-dot"></span><a
								href="#bs-scroll-8" class="con-box"><span class="arrow"></span>网吧上网</a></li>

						</ul>
					</div>
				</div>
				<!-----------------------左侧悬浮结束-------------------------->
			</div>
		</div>
	</div>
	
	<div id="phoneInfoTemplate" style="display:none;">
		<p class="m-line"></p>
		<p>
			<span class="color-gray m-inline" style="width: 70px">手机号：</span><span name="phone"></span>
		</p>
		<p>
			<span class="color-gray m-inline" style="width: 70px">IMEI：</span><span name="imei"></span>
		</p>
		<p>
			<span class="color-gray m-inline" style="width: 70px">MAC地址：</span><span name="mac"></span>
			&nbsp;<button class="btn btn-success btn-sm trackDetails">轨迹详情</button>
		</p>
	</div>
	
	<div id="qqInfoTemplate" style="display:none;">
		<p>
			<span class="color-gray m-inline" style="width: 70px">QQ号：</span><span></span>
		</p>
	</div>
	<div id="weiXinInfoTemplate" style="display:none;">
		<p>
			<span class="color-gray m-inline" style="width: 70px">微信号：</span><span></span>
		</p>
	</div>

	<!-- 一人一档多有地图初始化js -->
	<script type="text/javascript"
		src="<%=context%>/scripts/yryd/initYrydMap.js"></script>

	<script type="text/javascript"
		src="<%=context%>/scripts/yryd/yrydHighriskPersonAlert.js"></script>
	<!-- 加载分析分析研判快照--轨迹分析js -->
	<script type="text/javascript"
		src="<%=context%>/scripts/yryd/fxypkz/trackAnalyzeMap.js"></script>
	<script type="text/javascript"
		src="<%=context%>/scripts/yryd/fxypkz/trackAnalyzeSnapshotLst.js"></script>
	<!-- 加载分析分析研判快照--实时wifi设备监控js -->
	<script type="text/javascript"
		src="<%=context%>/scripts/yryd/fxypkz/sswfjk.js"></script>
	<script type="text/javascript"
		src="<%=context%>/scripts/yryd/fxypkz/lookSswfjkSnapshot.js"></script>
	<!-- 加载分析分析研判快照--wifi轨迹分析js -->
	<script type="text/javascript"
		src="<%=context%>/scripts/yryd/fxypkz/wifiLocusMap.js"></script>
	<script type="text/javascript"
		src="<%=context%>/scripts/yryd/fxypkz/wifiLocusAnalyzeSnapshotLst.js"></script>
	<!-- 加载分析分析研判快照--实时查看布控结果 -->
	<script type="text/javascript"
		src="<%=context%>/scripts/yryd/fxypkz/executeControlResult.js"></script>

	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
	<script type="text/javascript">
		var idcard = "<%=request.getParameter("idcard")%>";
	</script>

</body>
</html>