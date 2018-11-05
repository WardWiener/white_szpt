<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<script type="text/javascript"
	src="<%=context%>/custom/arcgis/multiBaseMap.js"></script>
</head>
<body style="background: #1E2B42;">
	<div id="knownCaseDiv" class="row main-block  light-block-er">
		<!-----------------串并案---------------------->
		<div id="knownCaseValidform" class="col-xs-8 validform"
			style="width: 100%; float: left;">
			<div style="padding-right: 350px">
				<div class="m-ui-title4">
					<h2>串并案案件</h2>
				</div>
				<div class="m-ui-table" style="margin-right: 30px">
					<table id="knownCaseTable"
						class="table table-bordered table-hover m-ui-table-whol"
						cellspacing="0" style="margin-bottom: 0">
					</table>
				</div>
				<div class="row"
					style="position: relative; height: 550px; overflow: hidden;">
					<div class="light-block"
						style="position: absolute; right: 5px; top: 5px; padding: 5px 10px; border-radius: 6px; z-index: 1;">
						分析半径<input id="scope"
							class="form-control input-sm m-inline valiform-keyup form-val"
							value="500"
							style="width: 50px; margin-left: 8px; margin-right: 8px;"
							datatype="n1-100" errormsg="请填写正整数">米， 计算时间<input
							id="hour"
							class="form-control input-sm m-inline valiform-keyup form-val"
							value="3"
							style="width: 50px; margin-left: 8px; margin-right: 8px;"
							datatype="n1-100" errormsg="请填写正整数">小时，
						<button id="analysisBut" class="btn btn-sm btn-primary ">分析</button>
					</div>
					<!-- 地图容器 -->
					<div id="mapContent" style="height: 100%;"></div>
				</div>
			</div>
		</div>
		<div class="col-xs-4"
			style="width: 330px; margin-left: -330px; float: left;">
			<div class="m-ui-title4">
				<h2>分析结果</h2>
			</div>
			<h3 class="row-mar color-yellow1 font16">所有案件完全匹配</h3>
			<ul id="allMatching" class="list-group">

			</ul>
			<!-- 模版 -->
			<ul id="allMatchingTemplate" style="display: none;"
				class="list-group">
				<li class="list-group-item">
					<p>
						<span class="color-gray2">相同MAC地址：</span><span name="mac"></span>
					</p>
					<p>
						<span class="color-gray2">MAC地址对应人员：</span><span name="name"></span>
						<button class="btn btn-sm btn-primary pull-right sendInstruct">下发指令</button>
					</p>
				</li>
			</ul>
			<h3 class="row-mar color-yellow1 font16">部分案件匹配</h3>
			<ul id="portionMatching" class="list-group">

			</ul>
			<ul id="portionMatchingTemplate" style="display: none;"
				class="list-group">
				<li class="list-group-item">
					<p>
						<span class="color-gray2">匹配案件：</span><span
							name="matchingCaseNameStr"></span>
					</p>
					<p>
						<span class="color-gray2">相同MAC地址：</span><span name="mac"></span>
					</p>
					<p>
						<span class="color-gray2">MAC地址对应人员：</span><span name="name"></span>
						<button class="btn btn-sm btn-primary pull-right sendInstruct">下发指令</button>
					</p>
				</li>
			</ul>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/yayd/ajfx/xyyMacAddressFxDetail.js"></script>
<script type="text/javascript"
		src="<%=context%>/scripts/yayd/ajfx/xyyMacAddressFxMap.js"></script>
</html>