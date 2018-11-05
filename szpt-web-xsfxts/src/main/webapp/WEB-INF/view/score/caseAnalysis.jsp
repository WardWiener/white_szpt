<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript"
	src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>

</head>
<script type="text/javascript">
	var caseCode = "${param.caseCode}";
</script>
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
					<li><a href="#">两抢一盗案件智能串并案分析</a></li>
				</ol>
				<div class="m-ui-title1">
					<h1>
						<button id="back" class="btn btn-primary" style="margin-right: 20px;">
							<span class="glyphicon glyphicon-menu-left"></span>返回
						</button>
						串并案分析
					</h1>
					<div class="m-ui-caozuobox">
						<button id="analysisCompareBut" class="btn btn-danger">分析比对</button>
						<button id="exportExcelBut" class="btn btn-primary">EXCEL导出</button>
						<button class="btn btn-primary query">
							<span class="glyphicon glyphicon-refresh mar-right"></span>刷新
						</button>
						<button id="lookSnapshotBut" class="btn btn-primary">查看快照</button>
					</div>
				</div>
				<div id='mainCaseInfo' class="light-block-er" style="padding: 15px; margin-top: 30px;">
					<div class="row row-mar">
						<div class="col-xs-4">
							<span class="color-gray2 mar-right">主案件编号：</span><span name="caseCode"></span>
						</div>
						<div class="col-xs-4">
							<span class="color-gray2 mar-right">主案件名称：</span><span name="caseName"></span>
						</div>
						<div class="col-xs-4">
							<span class="color-gray2 mar-right">适用的评分模板：</span><span name="scoreTemplateName"></span>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-4">
							<span class="color-gray2 mar-right">办案民警：</span><span name="handlePolice"></span>
						</div>
						<div class="col-xs-4">
							<span class="color-gray2 mar-right">案发时间：</span><span name="caseTimeStart"></span>
						</div>
					</div>
				</div>
				<!-----------------可能串并案过滤条件--------------------->
				<div id='queryCondition' class="light-block-er" style="padding: 15px; margin-top: 20px">
					<div class="m-ui-title4">
						<h2>可能串并案过滤条件：</h2>
						<div class="row pull-left" style="margin-left: 200px; margin-top: -20px">
							<input id="showHide" type="checkbox" name="showHide" class="icheckbox form-val">
							&nbsp;
							<label for="showHide">是否显示已忽略案件</label>
							<span style="margin-left: 50px">
								<input id="caseState" type="checkbox" name="caseState" class="icheckbox form-val"> 
								&nbsp;
								<label for="caseState">是否显示已破案件</label>
							</span>
						</div>
					</div>
					<div class="row row-mar" style="padding-top: 5px;">
						<div class="col-xs-3">
							<div class="col-xs-6">
								<label class="control-label">最低分值：</label>
							</div>
							<div class="col-xs-6">
								<input id="minScore" type="text" name="minScore" class="form-control input-sm form-val">
							</div>
						</div>

						<div class="col-xs-3">
							<div class="col-xs-6">
								<label class="control-label">作案特点：</label>
							</div>
							<div class="col-xs-6">
								<select id="feature" name="feature" class="form-control input-sm select2 allowClear form-val">
								
								</select>
							</div>
						</div>
						<div class="col-xs-3">
							<div class="col-xs-6">
								<label class="control-label">选择处所：</label>
							</div>
							<div class="col-xs-6">
								<select id="occurPlace" name="occurPlace" class="form-control input-sm select2 allowClear form-val">
									
								</select>
							</div>
						</div>
						<div class="col-xs-3">
							<div class="col-xs-6">
								<label class="control-label">发案社区：</label>
							</div>
							<div class="col-xs-6 input-group">
								<input type="text" id="community" name="community" class="form-control input-sm form-val" readonly>
							</div>
						</div>
					</div>
					<div class="row row-mar">
						<div class="col-xs-3">
							<div class="col-xs-6">
								<label class="control-label">作案时段：</label>
							</div>
							<div class="col-xs-6">
								<select id="period" name="period" class="form-control input-sm select2 allowClear form-val">
									
								</select>
							</div>
						</div>
						<div class="col-xs-3">
							<div class="col-xs-6">
								<label class="control-label">作案人数：</label>
							</div>
							<div class="col-xs-6">
								<select id="peopleNum" name="peopleNum" class="form-control input-sm select2 allowClear form-val">
									
								</select>
							</div>
						</div>
						<div class="col-xs-3">
							<div class="col-xs-6">
								<label class="control-label">作案进口：</label>
							</div>
							<div class="col-xs-6">
								<select id="entrance" name="entrance" class="form-control input-sm select2 allowClear form-val">
									
								</select>
							</div>
						</div>
						<div class="col-xs-3">
							<div class="col-xs-6">
								<label class="control-label">作案出口：</label>
							</div>
							<div class="col-xs-6">
								<select id="exit" name="exit" class="form-control input-sm select2 allowClear form-val">
									
								</select>
							</div>
						</div>
					</div>
					<div class="row text-center">
						<button class="btn btn-primary btn-sm query">查询</button>
						<button id="reset" class="btn btn-default btn-sm">重置</button>
					</div>
				</div>
				<!-----------------可能串并案过滤条件--------------------->
				<!-----------------tabs--------------------->
				<div id="tabs" class="m-ui-tabs"
					style="background: none; margin-top: 20px;">
					<ul class="nav nav-tabs">
						<li><a id="possibleCaseTabs" href="#tabs-1">可能的串并案</a></li>
						<li><a id="knownCaseTabs" href="#tabs-2">已知串并案</a></li>
					</ul>
					<div id="tabs-1" class="tabxon">
						<!-- <div style="overflow-x: auto; overflow-y: hidden; width: 100%;">
							<div style="width: 2000px; overflow: hidden;"> -->
								<table id="possibleCaseTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
									
								</table>
							<!-- </div>
						</div> -->
					</div>
					<!--可能的串并案-->
					<div id="tabs-2" class="tabxon">
						<!-- <div style="overflow-x: auto; overflow-y: hidden; width: 100%;">
							<div style="width: 2000px; overflow: hidden;"> -->
								<table id="knownCaseTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
									
								</table>
							<!-- </div>
						</div> -->
					</div>
					<!--已知串并案-->
				</div>
				<!-----------------tabs--------------------->
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=context%>/scripts/score/caseAnalysis.js">
		
	</script>
</body>
</html>