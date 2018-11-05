<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<script type="text/javascript" src="<%=context%>/custom/arcgis/multiBaseMap.js"></script>
<script type="text/javascript"
	src="<%=context%>/common/library/bootstrap/js/scrollspy.js"></script>
<script type="text/javascript" src="<%=context%>/custom/szpt/js/thumbslider.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/thumbslider.css" />
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
					<li><a href="#">一人一档 普通人</a></li>
					<li><a href="#">详情</a></li>
				</ol>
				<div class="m-ui-title1">
					<h1>
						<button id="backViewBtn" class="btn btn-primary"
							style="margin-right: 20px;">
							<span class="glyphicon glyphicon-menu-left"></span>返回
						</button>
						 普通人 详情
					</h1>
				</div>
				<!-----------------------左侧内容开始-------------------------->
				<div style="margin-right: 120px;">
					<div class="main-block" id="bs-scroll-1">
						<div class="row ">
							<input type="hidden" id="yrydId" >
								<div id="personalInfo" class="col-info">
									<h2 id="name" valName="name" class="valCell"></h2>
									<span valName="sex" class="valCell" style="margin-right: 50px; "></span>
									<button id="concernBtn" class="btn btn-sm  btn-danger" style="display: none;">关注</button>
									<button id="cancelConcernBtn" class="btn btn-sm  btn-danger" style="display: none;">取消关注</button>
									<div class="row color-gray2">
										<p class="warning-type">
											<span class="btn sq-yellow"></span>
											<!---sq-red、 sq-orange、sq-yellow、sq-blue、sq-white、---->
										</p>
										人员类别：<span valName="persontype" class="valCell"></span>
									</div>
									<p class="m-line"></p>
									<p>
										<span class="color-gray">身份证号：</span> <span id="yrydIdNum" valName="idcard" class="valCell"></span>
									</p>
									<p>
										<span class="color-gray">联系电话：</span><span valName="phone" class="valCell"></span>
									</p>
									<p class="m-line"></p>
									<div class="row">
										<div class="col-xs-6">
											<p>
												<span class="color-gray">曾用名：</span></span><span valName="oldname" class="valCell"></span>
											</p>
											<p>
												<span class="color-gray">民族：</span></span><span valName="nation" class="valCell"></span>
											</p>
											<p>
												<span class="color-gray">出生日期：</span></span><span valName="birthday" class="valCell"></span>
											</p>
											<p>
												<span class="color-gray">文化程度：</span></span><span valName="culture" class="valCell"></span>
											</p>
										</div>
										<div class="col-xs-6">
											<p>
												<span class="color-gray">婚姻状况：</span></span><span valName="marry" class="valCell"></span>
											</p>
											<p>
												<span class="color-gray">职业：</span></span><span valName="occupation" class="valCell"></span>
											</p>
											<p>
												<span class="color-gray">户主姓名：</span></span><span valName="householder" class="valCell"></span>
											</p>
											<p>
												<span class="color-gray">与户主关系：</span></span><span valName="relation" class="valCell"></span>
											</p>
										</div>
									</div>
									<p class="m-line"></p>
									<p>
										<span class="color-gray">出生地：</span></span><span valName="birthaddress" class="valCell"></span>
									</p>
									<p>
										<span class="color-gray">户籍地址：</span></span><span valName="address" class="valCell"></span>
									</p>
									<p>
										<span class="color-gray">死亡日期：</span></span><span valName="dateOfDeath" class="valCell"></span>
									</p>
								</div>
							
						</div>
					</div>
					<div class="main-block" id="bs-scroll-2">
						<div class="m-ui-title3">
							<h2>
								<span class="glyphicon glyphicon-home"></span>户籍与暂住
							</h2>
						</div>
						<div class="main-block-content">
							<h5 class="row-mar font16 color-yellow1">户籍信息</h5>
							<div class="m-ui-table">
								<table id="censusInfo" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
								</table>
							</div>
							
							<h5 class="row-mar mar-top font16 color-yellow1">暂住信息</h5>
							
							<div class="m-ui-table">
								<table id="stayInfo" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
								</table>
							</div>
						</div>
					</div>

					<div class="main-block" id="bs-scroll-3">
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
								<table id="vehicleInfo" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
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
								<table id="cameraInfo" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
								</table>
							</div>
							<h5 class="row-mar font16 color-yellow1">手机</h5>
							
							<div class="m-ui-table">
								<table id="phoneInfo" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
								</table>
							</div>
							
						</div>
					</div>
					<div class="main-block" id="bs-scroll-4">
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
								<table id="trainGoOutInfo" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
								</table>
							</div>
							
							<h5 class="row-mar font16 color-yellow1 mar-top">飞机</h5>
							<div class="pull-right" style="margin-top: -30px;">
								<button class="btn btn-sm btn-danger">请求数据服务</button>
							</div>
							<div class="m-ui-table">
								<table id="planeGoOutInfo" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
								</table>
							</div>
						</div>
					</div>
					<div class="main-block" id="bs-scroll-5">
						<div class="m-ui-title3">
							<h2>
								<span class="glyphicon glyphicon-tree-deciduous"></span>旅馆住宿
							</h2>
						</div>
						<div class="main-block-content">
						
							<div class="m-ui-table">
								<table id="hotelInfo" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
								</table>
							</div>
						</div>
					</div>

					<div class="main-block" id="bs-scroll-6">
						<div class="m-ui-title3">
							<h2>
								<span class="glyphicon glyphicon-modal-window"></span>网吧上网
							</h2>
						</div>
						<div class="main-block-content">
						
							<div class="m-ui-table">
								<table id="cybercafeInfo" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
								</table>
							</div>
							
						</div>
					</div>
				</div>
				<!-----------------------左侧内容结束-------------------------->

				<!-----------------------左侧悬浮开始-------------------------->
				<div style="position: fixed; right: 20px; top: 235px;">
					<div class="history-content history-content-xs" id="navbar-example">
						<ul class="nav nav-tabs" role="tablist"
							style="border-bottom: none;">
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-0"
								class="con-box"><span class="arrow"></span>基本信息</a></li>
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-1"
								class="con-box"><span class="arrow"></span>户籍与暂住</a></li>
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-2"
								class="con-box"><span class="arrow"></span>物品信息</a></li>
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-3"
								class="con-box"><span class="arrow"></span>出行信息</a></li>
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-4"
								class="con-box"><span class="arrow"></span>旅馆住宿</a></li>
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-5"
								class="con-box"><span class="arrow"></span>网吧上网</a></li>
						</ul>

					</div>
				</div>
				<!-----------------------左侧悬浮结束-------------------------->

	
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=context%>/scripts/yryd/yrydOrdinaryPerson.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
	<script type="text/javascript">
		var idcard = "<%= request.getParameter("idcard")%>";
	</script>
</body>
</html>