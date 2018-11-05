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
	var mainCaseCode = "${resultMap.mainCaseCode}";
	var caseCodes = ${resultMap.caseCodes};
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
					<li><a href="#"> 串并案分析</a></li>
				</ol>

				<div class="m-ui-title1">
					<h1>
						<button id="backBut" class="btn btn-primary" style="margin-right: 20px;">
							<span class="glyphicon glyphicon-menu-left"></span>返回
						</button>
						分析比对结果
					</h1>
					<div class="m-ui-caozuobox" style="right: 0; left: auto; top: 5px; width: auto;">
						<button id="showHideBut" state="hide" class="btn btn-primary">隐藏不同项</button>
						<button id="highlightBut" class="btn btn-warning">高亮显示相同项</button>
						<button id="exportExcelBut" class="btn btn-primary">EXCEL导出</button>
						<button id="snapshotbut" class="btn btn-primary">快照</button>
						<button id="refreshBut" class="btn btn-primary">
							<span class="glyphicon glyphicon-refresh mar-right"></span>刷新
						</button>
						<button id="createReportBut" class="btn btn-danger">生成报告</button>
						<button class="btn btn-primary">生成情报线索</button>

					</div>
				</div>
				<!-----------------tabs--------------------->
				<div id="tabs" class="m-ui-tabs light-block-er"
					style="margin-top: 15px; padding: 0">
					<ul class="nav nav-tabs">
						<li><a href="#tabs-1">可能的串并案比对</a></li>
						<li><a href="#tabs-2">可能的嫌疑人</a></li>
						<li><a href="#tabs-3">嫌疑人的可能活动区域</a></li>
					</ul>
					<div id="tabs-1" class="tabxon">
						<div id="mainCaseInfo" class="light-block-er"
							style="padding: 15px; margin-bottom: 15px;">
							<div class="row row-mar">
								<div class="col-xs-4">
									<span class="color-gray2 mar-right">案件类别：</span>
									<span name="typeName"></span>
								</div>
								<div class="col-xs-8">
									<span class="color-gray2 mar-right">适用的评分模板：</span>
									<a href="javascript:void(0);" name="scoreTemplateName"></a>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-4">
									<span class="color-gray2 mar-right">案件性质：</span>
									<span name="firstSortName"></span>
								</div>
								<div class="col-xs-4">
									<span class="color-gray2 mar-right">二级案件性质：</span>
									<span name="secondSortName"></span>
								</div>
							</div>
						</div>
						<div style="overflow-y: hidden; width: 100%;">
							<!-- <div style="width: 3000px; overflow: hidden;"> -->
								<table id="cacTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
									
								</table>
							<!-- </div> -->
						</div>
						<p class="main-block" style="margin-bottom: 0">
							隐藏项说明：共隐藏了
							<strong id="hideItemNum" class="color-red2" style="margin: 0 5px;">0</strong>
							个不同项：
							<span id="hideItemName" class="color-yellow1">无</span>
						</p>
					</div>
					<!--可能的串并案比对-->
					<div id="tabs-2" class="tabxon">
						<div class="light-block-er"
							style="padding: 15px; margin-bottom: 15px;">

							<p class="color-gray2">
								1、本案件（<a href="#">AS000001</a>）的嫌疑人：张某一；李某二；
							</p>
							<p class="color-gray2">2、已知串并案的嫌疑人：张某一；李某二；</p>
							<p class="color-gray2">3、系统分析最可能的嫌疑人：张某某；李某；王某三；钱某四；丁某五；刘某六；</p>
							<p class="mar-top text-center">
								<input type="checkbox" class="icheckbox">&nbsp;&nbsp;仅显示同性案件前科人员
							</p>
						</div>
						<table class="table table-bordered table-hover m-ui-table-whole"
							cellspacing="0" width="auto">
							<thead>
								<tr>
									<th width="250"></th>
									<th width="150" style="vertical-align: top">串并案分值</th>
									<th width="100" style="vertical-align: top"><a href="#"
										target="_blank">张某某</a></th>
									<th width="100" style="vertical-align: top"><a href="#"
										target="_blank">李某</a>
										<p>
											<span class="color-yellow2 font12">同性质案件前科</span>
										</p></th>
									<th width="100" style="vertical-align: top"><a href="#"
										target="_blank">王某三</a></th>
									<th width="100" style="vertical-align: top"><a href="#"
										target="_blank">钱某四</a></th>
									<th width="100" style="vertical-align: top"><a href="#"
										target="_blank">丁某五</a>
										<p>
											<span class="color-yellow2 font12">同性质案件前科</span>
										</p></th>
									<th width="100" style="vertical-align: top"><a href="#"
										target="_blank">刘某六</a></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="state-blue2"></td>
									<td>嫌疑人可能性综合得分</td>
									<td><span class="font18 color-red2">83.66</span></td>
									<td><span class="font18 color-red2">80</span></td>
									<td><span class="font18 color-red2">70</span></td>
									<td><span class="font18 color-red2">60</span></td>
									<td><span class="font18 color-red2">60</span></td>
									<td><span class="font18 color-red2">60</span></td>
									<td></td>
								</tr>
								<tr>
									<td class="state-blue2"><p>AS0000001</p>
										<p>张某一、李某二盗窃案</p></td>
									<td>主案件（嫌疑人可能性比重50%）</td>
									<td><span class="font18">90</span></td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
								</tr>
								<tr>
									<td class="state-blue2"><p>AS0000002</p>
										<p>张某一、李某二盗窃案</p></td>
									<td><span class="font18">95</span></td>
									<td><span class="font18">90</span></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td class="state-blue2"><p>AS0000003</p>
										<p>张某一、李某二盗窃案</p></td>
									<td><span class="font18">95</span></td>
									<td><span class="font18">80</span></td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
								</tr>
								<tr>
									<td class="state-blue2"><p>AS0000004</p>
										<p>张某一、李某二盗窃案</p></td>
									<td><span class="font18">88</span></td>
									<td><span class="font18">87</span></td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
								</tr>
								<tr>
									<td class="state-blue2"><p>AS0000005</p>
										<p>张某一、李某二盗窃案</p></td>
									<td><span class="font18">75</span></td>
									<td><span class="font18">70</span></td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
								</tr>
								<tr>
									<td class="state-blue2"><p>AS0000006</p>
										<p>张某一、李某二盗窃案</p></td>
									<td><span class="font18">75</span></td>
									<td><span class="font18">68</span></td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
									<td>....</td>
								</tr>

							</tbody>
						</table>
					</div>
					<!--可能的嫌疑人-->
					<div id="tabs-3" class="tabxon">
						<div class="light-block-er"
							style="padding: 15px; margin-bottom: 15px;">
							<div class="row row-mar">
								<div class="col-xs-4">
									<span class="color-gray2 mar-right">主案件编号：</span><a href="#">AF00000032</a>
								</div>
								<div class="col-xs-8">
									<span class="color-gray2 mar-right">主案件名称：</span><a href="#">张某一、李某二盗窃案</a>
								</div>
							</div>
							<div class="row row-mar">
								<div class="col-xs-4">
									<span class="color-gray2 mar-right">办案民警：</span>张爱国；李国强
								</div>
								<div class="col-xs-4">
									<span class="color-gray2 mar-right">案发时间：</span>2015-01-21
									17:00
								</div>
							</div>
							<div class="row">
								<div class="col-xs-10">
									<span class="color-gray2 mar-right">案发地点：</span>XX区XX路123号
								</div>
							</div>
						</div>

						<div class="m-ui-title4"
							style="padding-left: 15px; border-bottom: none">
							<h2>可设置的参数条件：</h2>
						</div>

						<div style="padding-left: 15px;">
							<div class="row row-mar">
								<div class="col-xs-6">
									<span style="display: inline-block; width: 250px;">本案的嫌疑人及已知串并案的嫌疑人：</span>
									<a href="#" class="btn btn-primary btn-sm color-white font14">张某一</a>
									<a href="#" class="btn btn-primary btn-sm color-white font14">李某二</a>
								</div>
								<div class="col-xs-6">
									<span style="display: inline-block; width: 260px;">设置取最近活动记录的时间范围：</span><input
										class="form-control input-sm m-inline" value="5"
										style="width: 50px;">&nbsp;天
								</div>
							</div>
							<p class="m-line"></p>

							<div class="row row-mar">
								<div class="col-xs-6">
									<span style="display: inline-block; width: 250px;">可能的嫌疑人：</span>
									<a href="#" class="btn btn-bordered btn-sm font14">张默某</a> <a
										href="#" class="btn btn-bordered btn-sm font14">李某</a> <a
										href="#" class="btn btn-bordered btn-sm font14">赵某某</a> <a
										href="#" class="btn btn-bordered btn-sm font14">朱某某</a> <a
										href="#" class="btn btn-bordered btn-sm font14">胡某某</a> <a
										href="#" class="btn btn-bordered btn-sm font14">卓某某</a>
								</div>
								<div class="col-xs-6">
									<span style="display: inline-block; width: 260px;">设置取最近活动记录的时间范围：</span><input
										class="form-control input-sm m-inline" value="3"
										style="width: 50px;">&nbsp;天
								</div>
							</div>
							<p class="m-line"></p>
							<div class="row row-mar">
								<span style="display: inline-block; width: 260px;">显示辖区内活动记录最密集的区域数量：</span><input
									class="form-control input-sm m-inline" value="5"
									style="width: 50px;">&nbsp;个
								<div class="warning-type"
									style="display: inline-block; margin-left: 20px;">
									<a href="#" class="btn sq-red"></a> <a href="#"
										class="btn sq-orange"></a> <a href="#" class="btn sq-yellow"></a>
									<a href="#" class="btn sq-green"></a> <a href="#"
										class="btn sq-blue"></a>
								</div>

							</div>
							<div class="row text-right"
								style="padding-right: 15px; margin-top: -35px;">
								<button class="btn btn-primary">重新计算</button>
							</div>
						</div>
						<div class="row mar-top">
							<img src="../images/map/map-miji.png">
						</div>
					</div>
					<!--嫌疑人的可能活动区域-->
				</div>
				<!-----------------tabs--------------------->
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=context%>/scripts/score/caseAnalysisCompareCommon.js"></script>
	<script type="text/javascript"
		src="<%=context%>/scripts/score/caseAnalysisCompare.js">
		
	</script>
</body>
</html>