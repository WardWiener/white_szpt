<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<script type="text/javascript"
	src="<%=context%>/common/library/bootstrap/js/scrollspy.js"></script>
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<script type="text/javascript"
	src="<%=context%>/custom/arcgis/multiBaseMap.js"></script>
</head>

<body data-spy="scroll" data-target="#navbar-example" data-offset="90">
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<div id="c-center">
		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-dagl.jsp"%>
		</div>
		<input id="caseId" type="hidden" value="<%=request.getParameter("id")%>">
		<input id="location" type="hidden" value="${location}">
		<div id="c-center-right">
			<div id="c-center-right-content">
				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">档案管理</a></li>
					<li><a href="#">一案一档</a></li>
					<li><a href="#">案件档案详情</a></li>
				</ol>

				<div class="m-ui-title1">
					<h1>
						<button id="backViewBtn" class="btn btn-primary"
							style="margin-right: 20px;">
							<span class="glyphicon glyphicon-menu-left"></span>返回
						</button>
						案件档案详情
					</h1>
				</div>
				<div id="c-center-right-content-block">

					<div id="c-center-right-content-bar" style="position: fixed">
						<div class="history-content history-content2" id="navbar-example">
							<ul class="nav nav-tabs" role="tablist"
								style="border-bottom: none; height: 700px;">
								<li class="active">
									<span class="icon-red-dot"></span>
									<a href="#jcjInfo" class="con-box">
										<span class="arrow"></span>接处警信息
									</a>
								</li>
								<li>
									<span class="icon-red-dot"></span>
									<a href="#basicInfo" class="con-box">
										<span class="arrow"></span>基本信息
									</a>
								</li>
								<li>
									<span class="icon-red-dot"></span>
									<a href="#bs-scroll-2" class="con-box">
										<span class="arrow"></span>证据笔录
									</a>
								</li>
								<li><span class="icon-red-dot"></span><a
									href="#bs-scroll-4" class="con-box" id="caseInfoAnay"><span
										class="arrow"></span>案情分析</a></li>
								<li><span class="icon-red-dot"></span><a href="#bs-scroll-5"
									class="con-box"><span class="arrow"></span>指令反馈</a></li>
								<li><span class="icon-red-dot"></span><a
									href="#bs-scroll-6" class="con-box" id="involvedPerson"><span
										class="arrow"></span>涉案人员</a></li>
								<li><span class="icon-red-dot"></span><a
									href="#bs-scroll-7" class="con-box" id="involvedObject"><span
										class="arrow"></span>涉案物品</a></li>
								<li><span class="icon-red-dot"></span><a
									href="#bs-scroll-8" class="con-box"><span class="arrow"></span>卷宗</a></li>
								<li><span class="icon-red-dot"></span><a
									href="#bs-scroll-9" class="con-box"><span class="arrow"></span>音视频</a></li>
							</ul>


						</div>
					</div>

					<div id="c-center-right-content-con">
						<div class="right-inner">


							<div class="row main-block" id="bs-scroll-1">
								<div class="m-ui-title1 text-center" style="padding: 20px;">
									<h1>
										<span name="caseName"></span><span name="caseStateName"
											class="state state-red2 mar-left"></span>
									</h1>
								</div>

								<div class="light-block-er" style="padding: 15px">
									<div class="row row-mar">
										<div class="col-xs-4">
											<span class="color-gray2 mar-right">案件编号：</span><span
												name="caseCode"></span>
										</div>
										<div class="col-xs-4">
											<span class="color-gray2 mar-right">案件性质：</span><span
												name="caseKindName"></span>
										</div>
										<div class="col-xs-4">
											<span class="color-gray2 mar-right">案件类型：</span><span
												name="caseTypeName"></span>
										</div>
									</div>
									<div class="row row-mar">
										<div class="col-xs-4">
											<span class="color-gray2 mar-right">发案时间：</span><span
												name="caseTimeStart"></span>
										</div>
										<div class="col-xs-8">
											<span class="color-gray2 mar-right">发案地点：</span><span
												name="caseAddress"></span>
										</div>
									</div>
									<div class="row row-mar">
										<div class="col-xs-4">
											<span class="color-gray2 mar-right">主办人：&nbsp;&nbsp;&nbsp;</span>
											<span name="handlePolice"></span>
										</div>
										<div class="col-xs-8">
											<span class="color-gray2 mar-right">办理单位：</span><span
												name="handleUnit"></span>
										</div>

									</div>
								</div>
							</div>
							<!----------标题end------------>


							<div class="row main-block" id="jcjInfo">
								<div class="m-ui-title3">
									<h2>接处警信息</h2>
								</div>
								<div class="light-block-er" style="padding: 15px">
									<div class="row row-mar">
										<div class="col-xs-4">
											<span class="color-gray2 mar-right">接警时间：</span><span
												valName="reportTime" class="alarmInfo" id="reportTime"></span>
										</div>
										<div class="col-xs-4">
											<span class="color-gray2 mar-right">接警人：</span><span
												valName="receivePerson" class="alarmInfo" id="receivePerson"></span>
										</div>
										<div class="col-xs-4">
											<span class="color-gray2 mar-right">接警类别：</span><span
												valName="alarmType" class="alarmInfo" id="alarmType"></span>
										</div>
									</div>
									<div class="row row-mar">
										<div class="col-xs-4">
											<span class="color-gray2 mar-right">报警人：</span><span
												id="baojingren"></span>
										</div>
										<div class="col-xs-4">
											<span class="color-gray2 mar-right">发生地点：</span><span
												id="popedom" class="alarmInfo"></span>
										</div>
									</div>
									<div class="row row-mar">
										<div class="col-xs-2" style="width: 80px">
											<span class="color-gray2">警情概要：</span>
										</div>
										<div class="col-xs-8">
											<span id="reportDetails" class="alarmInfo"></span>
										</div>
									</div>
								</div>

								<div class="m-ui-title4 mar-top" style="display: none">
									<h2>反馈信息</h2>
								</div>

								<div class="light-block-er" style="padding: 15px; display: none">
									<div class="row row-mar">
										<div class="col-xs-4">
											<span class="color-gray2 mar-right">反馈类别：</span><span
												valName="" class="feedback" id="feedbackType"></span>
										</div>
										<div class="col-xs-4">
											<span class="color-gray2 mar-right">反馈人：</span><span
												valName="" class="feedback" id="feedbackPerson"></span>
										</div>
										<div class="col-xs-4">
											<span class="color-gray2 mar-right">反馈时间：</span><span
												valName="" class="feedback" id="feedbackTime"></span>
										</div>
									</div>

									<div class="row row-mar">
										<div class="col-xs-2" style="width: 80px">
											<span class="color-gray2">反馈内容：</span>
										</div>
										<div class="col-xs-8">
											<span valName="" class="feedback" id="feedbackContent"></span>
										</div>
									</div>
								</div>


								<div class="m-ui-title4 mar-top">
									<h2>警情处置记录</h2>
								</div>
								<div class="history-content history-content3"
									style="height: 400px; overflow: auto; padding-left: 20px">
									<ul id="policeDeal">
									</ul>
								</div>
							</div>

							<!----------接处警信息end------------>
							<div id="basicInfo"></div>
							<div class="row main-block">
								<div class="m-ui-title3">
									<h2>基本信息</h2>
								</div>
								<div class="alert" style="border: 3px solid #1d98de;">
									<table id="caseBasicInfo" class="table table-sg"
										cellspacing="0" width="100%">
										<tbody>
											<tr>
												<td class="td-left" width="20%">案件编号</td>
												<td name="caseCode"></td>
												<td class="td-left" width="20%">案件名称</td>
												<td name="caseName"></td>
											</tr>
											<tr>
												<td class="td-left">现场勘验编号</td>
												<td name="kno"></td>
												<td class="td-left">案件类别</td>
												<td name="caseSortName"></td>
											</tr>
											<tr>
												<td class="td-left">案件性质</td>
												<td name="caseKindName"></td>
												<td class="td-left">案件状态</td>
												<td name="caseStateName"></td>
											</tr>

											<tr>
												<td class="td-left">发现时间</td>
												<td><span name="discoverTimeStart"></span><br> 到<span
													name="discoverTimeEnd"></span></td>
												<td class="td-left">发案时间</td>
												<td><span name="caseTimeStart"></span><br> 到<span
													name="caseTimeEnd"></span></td>

											</tr>
											<tr>
												<td class="td-left">国家地区</td>
												<td name="country"></td>
												<td class="td-left">发案区划</td>
												<td name="district"></td>
											</tr>
											<tr>
												<td class="td-left">发案辖区</td>
												<td name="region"></td>
												<td class="td-left">发案社区</td>
												<td name="community"></td>
											</tr>
											<tr>
												<td class="td-left">发案地点</td>
												<td name="caseAddress" colspan="3"></td>
											</tr>
											<tr>
												<td class="td-left">简要案情</td>
												<td name="details" colspan="3"></td>
											</tr>
											<tr>
												<td class="td-left">受伤人数</td>
												<td><span name="injuredCount"></span>人</td>
												<td class="td-left">死亡人数</td>
												<td><span name="deadCount"></span>人</td>
											</tr>
											<tr>
												<td class="td-left">损失总价值</td>
												<td><span name="lossValue"></span>元</td>
												<td class="td-left">案情关键词</td>
												<td name="caseKeyword"></td>
											</tr>
										</tbody>
									</table>
								</div>

							</div>
							<!----------案件基本信息end------------>



							<div class="row main-block" id="bs-scroll-2">
								<div class="col-xs-5">
									<div style="margin-right: 20px;">
										<div class="m-ui-title3">
											<h2>证据笔录</h2>
										</div>
										<table id="caseNoteTable"
											class="table table-hover table-sg table-sg-sm"
											cellspacing="0" width="100%">

										</table>
									</div>
								</div>
								<div class="col-xs-7" id="blattachMent"></div>
							</div>
							<!---main-block---->

							<div class="row main-block" id="bs-scroll-3"
								style="display: none">
								<div class="col-xs-5">
									<div style="margin-right: 20px;">
										<div class="m-ui-title3">
											<h2>情报线索</h2>
											<button class="btn  btn-primary btn-sm pull-right"
												style="margin-top: -40px">新增</button>
										</div>
										<%-- 情报线索分析 --%>
										<table id="informationClue"
											class="table table-hover table-sg table-sg-sm"
											cellspacing="0" width="100%">
										</table>
									</div>
								</div>
								<div class="col-xs-7"></div>
							</div>


							<div class="row main-block" id="bs-scroll-4">
								<div class="m-ui-title3">
									<h2>案情分析</h2>
								</div>

								<div id="tabs" class="row m-ui-tabs"
									style="background: none; margin-top: 20px;">
									<ul class="nav nav-tabs">
										<li><a href="#tabs-1">分析打标</a></li>
										<li><a href="#tabs-2">一案一研判</a></li>
										<!-- <li><a href="#tabs-3">串并案分析</a></li> -->
										<li><a href="#tabs-4">嫌疑人MAC地址分析</a></li>
									</ul>

									<div id="tabs-1" class="tabxon">
										<%@include file="ajfx/fxdb.jsp"%>
									</div>
									<div id="tabs-2" class="tabxon">
										<%@include file="ajfx/yayyp.jsp"%>
									</div>
									<%-- <div id="tabs-3" class="tabxon">
										<%@include file="ajfx/cbafx.jsp"%>
									</div> --%>
									<div id="tabs-4" class="tabxon">
										<%@include file="ajfx/xyyMacAddressFx.jsp"%>
									</div>
								</div>
							</div>

							<div class="row main-block" id="bs-scroll-5">
								<div class="m-ui-title3">
									<h2>指令反馈</h2>
								</div>
								<div class="history-content history-content3"
									style="height: 400px; overflow: auto; padding-left: 20px">

									<ul id="instructive">
									</ul>
								</div>
							</div>
							<!---main-block---->
							<div class="row main-block" id="bs-scroll-6">
								<div class="col-xs-7">
									<div style="margin-right: 20px;">
										<div class="m-ui-title3">
											<h2>嫌疑人及团伙</h2>
										</div>

										<h3 class="row-mar font14 color-orange1" id="xianyirenNum"></h3>
										<div class="m-ui-table-sm light-block-er" style="padding: 0">
											<table id="xianyirenTable"
												class="table table-bordered table-hover m-ui-table-whole "
												cellspacing="0" width="100%">
											</table>
										</div>
										<h3 class="row-mar font14 color-orange1 mar-top" id="teamNum"></h3>
										<div class="m-ui-table-sm light-block-er" style="padding: 0">
											<table id="criminalCaseTeamTable"
												class="table table-bordered table-hover m-ui-table-whole "
												cellspacing="0" width="100%">
											</table>
										</div>


									</div>
								</div>
								<div class="col-xs-5">
									<div class="m-ui-title3">
										<h2>涉案人员</h2>
									</div>
									<h3 class="row-mar font14 color-orange1" id="barNum"></h3>
									<div class="m-ui-table-sm light-block-er" style="padding: 0">
										<table id="baPersonTable"
											class="table table-bordered table-hover m-ui-table-whole "
											cellspacing="0" width="100%">
										</table>
									</div>
									<h3 class="row-mar font14 color-orange1 mar-top" id="shrNum"></h3>
									<div class="m-ui-table-sm light-block-er" style="padding: 0">
										<table id="shPersonTable"
											class="table table-bordered table-hover m-ui-table-whole "
											cellspacing="0" width="100%">
										</table>
									</div>
									<h3 class="row-mar font14 color-orange1 mar-top" id="dsrNum"></h3>
									<div class="m-ui-table-sm light-block-er" style="padding: 0">
										<table id="dsPersonTable"
											class="table table-bordered table-hover m-ui-table-whole "
											cellspacing="0" width="100%">
										</table>
									</div>

								</div>
							</div>
							<!---main-block---->



							<div class="row main-block" id="bs-scroll-7">
								<div class="m-ui-title3">
									<h2>涉案物品</h2>
								</div>
								<div class="photo-list an-list an-list2 an-list3">
									<ul id="criminalObjectUl" class="objUl">

									</ul>
								</div>

							</div>
							<!---main-block---->

							<div class="main-block" id="bs-scroll-8">
								<div class="m-ui-title3">
									<h2>卷宗</h2>
								</div>
								<div class="row">
									<div class="col-xs-5">
										<div style="margin-right: 20px;">
											<ul id="archivedFileUl" class="list-news">

											</ul>
										</div>
									</div>
								</div>

							</div>
							<!---main-block---->
							<div class="main-block" id="bs-scroll-9">
								<div class="m-ui-title3">
									<h2>案件音视频</h2>
									<button class="btn btn-primary btn-lg pull-right showUpload"
										style="width: 200px; margin-top: -36px;">上传音视频</button>
								</div>
								<div class="m-ui-title4">
									<h2>接处警环节</h2>
								</div>
								<div class="row">
									<div class="col-xs-6">
										<h2 class="font14 row-mar color-gray">视频资料</h2>
										<div class="photo-list video-list">
											<ul class="list-news jcjsp openSp">
												
											</ul>
										</div>
									</div>
									<div class="col-xs-6">
										<div style="padding-left: 30px;">
											<h2 class="font14 row-mar color-gray">音频资料</h2>
											<ul class="list-news jcjyp openYp">
												
											</ul>
										</div>
									</div>
								</div>
								<div class="m-ui-title4">
									<h2>审讯环节</h2>
								</div>
								<div class="row">
									<div class="col-xs-6">
										<h2 class="font14 row-mar color-gray">视频资料</h2>
										<div class="photo-list video-list">
											<ul class="list-news">
												<li style="text-align:center;">无记录</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="m-ui-title4">
									<h2>其它上传</h2>
								</div>
								<div class="row">
									<div class="col-xs-6">
										<h2 class="font14 row-mar color-gray">视频资料</h2>
										<div class="photo-list video-list">
											<ul class="list-news qtsp openSp">
												
											</ul>
										</div>
									</div>
									<div class="col-xs-6">
										<div style="padding-left: 30px;">
											<h2 class="font14 row-mar color-gray">音频资料</h2>
											<ul class="list-news qtyp openYp">
												
											</ul>
										</div>
									</div>
								</div>
							</div>
							
							<!---main-block---->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 模版DIV -->
	<div style="display: none;">
		<!-- 涉案物品li模版 -->
		<li id="criminalObjectTemplate">
			<h2 class="title">
				<a href="javascript:void(0);"><span name="objectName"></span></a>
			</h2>
			<p class="m-line"></p>
			<p>
				<span class="color-gray">物品编号：</span><span name="objid"></span>
			</p>
			<p>
				<span class="color-gray">物品特征：</span><span name="objectTz"></span>
			</p> <!-- <p class="pull-right" style="margin-top:-70px;"><img src="" style="height:60px; width:60px;"></p> -->
			<p>
				<span class="color-gray">对应案件编号：</span><span name="caseCode"></span>
			</p>
			<p>
				<span class="color-gray">对应案件名称：</span><span name="caseName"></span>
			</p>
			<p>
				<span class="color-gray">所在物证保管区：</span><span name="savefield"></span>
			</p>
			<p>
				<span class="color-gray">所在储物柜（架）：</span><span name="saveCupboard"></span>
			</p>
			<p>
				<span class="color-gray">对应文书：</span><span name="wswh"></span>
			</p>
			<p>
				<span class="color-gray">所属人员姓名：</span><span name="suspectName"></span>
			</p>
		</li>
		<!-- 涉案团伙模版 -->
		<div id="criminalFordTemplate">
			<tr>
				<td>团伙名称：<span name="name"></span></td>
				<td>团伙人数：<span name="count"></span>人
				</td>
				<td rowspan="3" width="60"></td>
			</tr>
			<tr>
				<td>主犯名称：<span name="mainPeople"></span></td>
				<td>组合形式：<span name="form"></span></td>
			</tr>
			<tr>
				<td>作案手段：<span name="resort"></span></td>
				<td>作案特点：<span name="peculiarity"></span></td>
			</tr>
		</div>
	</div>
	<!-- 加载嫌疑人MAC地址分析快照 -->
	<script type="text/javascript"
		src="<%=context%>/scripts/yayd/ajfx/xyyMacAddressFx.js"></script>
	<!-- 加载嫌疑人MAC地址分析快照地图 -->
	<script type="text/javascript"
		src="<%=context%>/scripts/yayd/ajfx/xyyMacAddressFxMap.js"></script>

	<script type="text/javascript"
		src="<%=context%>/scripts/yayd/yaydDetail.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
	
</body>

</html>