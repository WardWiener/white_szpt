<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/instruction/common/constant.jsp"%>
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<script type="text/javascript" src="<%=context%>/custom/arcgis/multiBaseMap.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript" src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
</head>
<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<div id="c-center">
		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-xsfxts.jsp"%>
		</div>
		<div id="c-center-right">

			<div id="c-center-right-content">

				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">刑事案件分析</a></li>
					<li><a href="#">嫌疑人MAC地址</a></li>
				</ol>


				<div class="m-ui-title1">
					<h1>基于串并案分析嫌疑人MAC地址</h1>
				</div>

				<div class="main-block">
					<div class="row row-mar">
						<div class="col-xs-3">
							<div class="col-xs-6">
								<label class="control-label">案件编号：</label>
							</div>
							<div class="col-xs-6">
								<input id="caseCode" class="form-control input-sm">
							</div>
						</div>
						<div class="col-xs-3">
							<div class="col-xs-6">
								<label class="control-label">案件名称：</label>
							</div>
							<div class="col-xs-6">
								<input id="caseName" class="form-control input-sm">
							</div>
						</div>
						<div class="col-xs-3">
							<button id="queryBut" class="btn btn-primary btn-sm" style="margin-left: 10px">查询</button>
							<button id="resetBut" class="btn btn-default btn-sm">重置</button>
						</div>
						
					</div>

					<div class="row">
						<button id="caseDetailBut" class="btn btn-primary lookYayd" style="margin-left:0px;">查看详情</button>
					</div>
					<table id="caseTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
						
					</table>
				</div>
				<!----main-block---->

				
				<div class="row">
					<button id="caseSnapshootBut" class="btn btn-primary" style="margin-left:0px;">生成分析快照</button>
				</div>
				<div id="knownCaseDiv" class="row main-block  light-block-er" style="display:none;">
					<!-----------------串并案---------------------->
					<div id="knownCaseValidform" class="col-xs-8 validform" style="width: 100%; float: left;">
						<div style="padding-right: 350px">
							<div class="m-ui-title4">
								<h2>串并案案件</h2>
							</div>
							<table id="knownCaseTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" style="margin-bottom: 0">
								
							</table>
							<div class="row" style="position: relative; height: 550px; overflow: hidden;">
								<div class="light-block" style="position: absolute; right: 5px; top: 5px; padding: 5px 10px; border-radius: 6px;z-index: 1;">
									分析半径<input id="scope" class="form-control input-sm m-inline valiform-keyup form-val" value="500"
										style="width: 50px; margin-left: 8px; margin-right: 8px;" datatype="n1-100" errormsg="请填写正整数">米，
									计算时间<input id="hour" class="form-control input-sm m-inline valiform-keyup form-val" value="3"
										style="width: 50px; margin-left: 8px; margin-right: 8px;" datatype="n1-100" errormsg="请填写正整数">小时，
									<button id="analysisBut" class="btn btn-sm btn-primary ">分析</button>
								</div>
								<!-- 地图容器 -->
								<div id="mapContent" style="height: 100%;">
								
								</div>
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
						<ul id="allMatchingTemplate" style="display:none;" class="list-group">
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
						<ul id="portionMatchingTemplate" style="display:none;" class="list-group">
							<li class="list-group-item">
								<p>
									<span class="color-gray2">匹配案件：</span><span name="matchingCaseNameStr"></span>
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
					<!-----------------串并案--------------------->
				</div>
				<div id="knownCaseExistDiv" style="display:none;">
					<h3 style="text-align: center;padding: 10px;">该案件暂无串并案，请先通过V3系统添加该案件的串并案，再进行分析。</h3>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=context%>/scripts/suspect/suspectMacAnalysis.js"></script>
	<script type="text/javascript" src="<%=context%>/scripts/suspect/suspectMacAnalysisMap.js"></script>
	<script type="text/javascript" src="<%=context%>/scripts/instruction/instructionList.js"></script>
</body>
</html>