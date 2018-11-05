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
					<li><a href="#">一人一档</a></li>
				</ol>

				<div class="m-ui-title1">
					<h1>一人一档</h1>
				</div>

				<div class="main-block text-center">
					<span class="mar-right color-gray">身份证号:</span><input
						class="form-control" style="width: 400px; vertical-align: middle;">
					<button id="searchBtn" class="btn btn-info">查询</button>
					<button class="attention btn btn-danger">我的关注</button>
				</div>
				<!-----------------------左侧内容开始-------------------------->
				<div style="margin-right: 120px;">

					<div class="main-block" id="bs-scroll-1">
						<div class="row person-block">

							<div class="col-xs-3 left">
								<!-------图片轮播-------->
								<div id="topthumbslider" class="thumbslider">
									<div class="thumbslider-prev prev">
										<a href="javascript:void(0)"></a>
									</div>
									<div class="thumbslider-show"></div>
			
									<div class="thumbslider-next next">
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
								<input type="hidden" id="yrydId" >
								<div id="personalInfo" class="col-info">
									<h2  valName="name" class="valCell"></h2>
									<span valName="sex" class="valCell" style="margin-right: 50px; "></span>
									<button id="concernBtn" class="btn btn-sm  btn-danger" >关注</button>
									<div class="row color-gray2">
										<p class="warning-type">
											<span class="btn sq-yellow"></span>
											<!---sq-red、 sq-orange、sq-yellow、sq-blue、sq-white、---->
										</p>
										人员类别：<span valName="type" class="valCell"></span>
									</div>
									<p class="m-line"></p>
									<p>
										<span class="color-gray">身份证号：</span> <span id="yrydIdNum" valName="idNum" class="valCell"></span>
									</p>
									<p>
										<span class="color-gray">联系电话：</span><span valName="phone" class="valCell"></span>
									</p>
									<p class="m-line"></p>
									<div class="row">
										<div class="col-xs-6">
											<p>
												<span class="color-gray">曾用名：</span></span><span valName="usedName" class="valCell"></span>
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
												<span class="color-gray">婚姻状况：</span></span><span valName="married" class="valCell"></span>
											</p>
											<p>
												<span class="color-gray">职业：</span></span><span valName="culture" class="valCell"></span>
											</p>
											<p>
												<span class="color-gray">户主姓名：</span></span><span valName="householder" class="valCell"></span>
											</p>
											<p>
												<span class="color-gray">与户主关系：</span></span><span valName="relationWithHouseholder" class="valCell"></span>
											</p>

										</div>
									</div>
									<p class="m-line"></p>
									<p>
										<span class="color-gray">出生地：</span><span valName="birthplace" class="valCell"></span>
									</p>
									<p>
										<span class="color-gray">户籍地址：</span><span valName="censusAddress" class="valCell"></span>
									</p>
									<p>
										<span class="color-gray">死亡日期：</span><span valName="dateOfDeath" class="valCell"></span>
									</p>

								</div>
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
								<span class="glyphicon glyphicon-random"></span>轨迹信息
							</h2>
						</div>	
						
						<div class="row">
							<div class="col-xs-6">
								<div style="padding: 0 20px;">
									<div id="mapContent" style="background: #eee; height: 320px; overflow: hidden;">
									</div>
									<p class="mar-top">最近出现地点：浙江省宁波市江东区桑田路111号</p>
								</div>
							</div>
							<div class="col-xs-6">
								<div style="padding: 0 20px;">
									<div id="mapMaxTime" style="background: #eee; height: 320px; overflow: hidden;">
									</div>
									<p class="mar-top">最长出现地点：浙江省宁波市江东区桑田路111号；XXX；YYYY；ZZZZ；UUUU；PPPP</p>
								</div>
							</div>
						</div>
					</div>



					<div class="main-block" id="bs-scroll-4">
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
					<div class="main-block" id="bs-scroll-5">
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
					<div class="main-block" id="bs-scroll-6">
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

					<div class="main-block" id="bs-scroll-7">
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

					<!------------------前科信息------------------>
					<div class="main-block" id="bs-scroll-8">
						<div class="m-ui-title3">
							<h2>
								<span class="glyphicon glyphicon-certificate"></span>前科信息
							</h2>
						</div>
						<div class="photo-list an-list an-list2 an-list3"
							style="margin-left: 0;">
							<ul>
								<li style="display: none;" id="recordInfo">
									<h2 class="title">
										<a href="#" class=""><span valName="name" class="valCell"></span></a>
									</h2>
									<p class="m-line"></p>
									<p>
										<span class="color-gray">案件编号：</span><span valName="num" class="valCell"></span>
									</p>
									<p>
										<span class="color-gray">发案时间：</span><span valName="startTime" class="valCell"></span> &nbsp;
										&nbsp;18：00
									</p>
									<p>
										<span class="color-gray">发案地点：</span><span valName="place" class="valCell"></span>
									</p>
									<p>
										<span class="color-gray">案件类别：</span><span valName="sort" class="valCell"></span>
									</p>

									<div class="row item-num">
										<div class="col-xs-3">
											<p>嫌疑人</p>
											<span valName="suspect" class="valCell num"></span>
										</div>
										<div class="col-xs-3">
											<p>物证</p>
											<span valName="wuZheng" class="valCell num"></span>
										</div>
										<div class="col-xs-3">
											<p>卷宗</p>
											<span valName="fileNum" class="valCell num"></span>
										</div>
										<div class="col-xs-3">
											<p>情报线索</p>
											<span valName="clue" class="valCell num"></span>
										</div>
									</div>
								</li>
								
							</ul>
						</div>
						<div class="clear"></div>
					</div>
					<!------------------前科信息------------------>
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
								class="con-box"><span class="arrow"></span>轨迹信息</a></li>
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-3"
								class="con-box"><span class="arrow"></span>物品信息</a></li>
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-4"
								class="con-box"><span class="arrow"></span>出行信息</a></li>
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-5"
								class="con-box"><span class="arrow"></span>旅馆住宿</a></li>
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-6"
								class="con-box"><span class="arrow"></span>网吧上网</a></li>
							<li><span class="icon-red-dot"></span><a href="#bs-scroll-7"
								class="con-box"><span class="arrow"></span>前科信息</a></li>
						</ul>

					</div>
				</div>
				<!-----------------------左侧悬浮结束-------------------------->

	
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=context%>/scripts/yryd/yryd.js"></script>
	<script type="text/javascript" src="<%=context%>/scripts/yryd/yrydMap.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>

</html>