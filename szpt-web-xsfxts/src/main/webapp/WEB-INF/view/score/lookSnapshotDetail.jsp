<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
</head>
<script type="text/javascript">
	var sanpshotId = "${param.sanpshotId}";
</script>
<body style="background: #132643;">
	<div id="c-center-right">
		<div id="c-center-right-content" style="margin-left:0px;">
			<ol class="breadcrumb m-ui-breadcrumb">
				<li><a href="#">首页</a></li>
				<li><a href="#">查看快照</a></li>
				<li><a href="#">快照详情</a></li>
			</ol>
			<div class="m-ui-title1">
				<h1>
					<!-- <button class="btn btn-primary" style="margin-right: 20px;">
						<span class="glyphicon glyphicon-menu-left"></span>返回
					</button> -->
					快照详情
				</h1>
			</div>
			<div class="main-block" style="margin-top: 30px;">
				<div id="snapshotInfo" class="row light-block-er" style="padding-left: 5px">
					<div class="col-xs-1" style="width: 160px">
						<button class="btn btn-danger" style="width: 100px">生成报告</button>
					</div>
					<div class="col-xs-2 m-label-right">
						<span class="color-gray2 mar-right">快照序号：</span>
						<span>${param.no}</span>
					</div>
					<div class="col-xs-3 m-label-right">
						<span class="color-gray2 mar-right">生成时间：</span>
						<span name="createdDate"></span>
					</div>
					<div class="col-xs-3 m-label-right">
						<span class="color-gray2 mar-right">创建人：</span>
						<span name="createPerson"></span>
					</div>
				</div>


				<div class="row  light-block-er">

					<!-----------------tabs--------------------->
					<div id="tabs" class="m-ui-tabs" style="background: none;">
						<ul class="nav nav-tabs">
							<li><a href="#tabs-1">可能的串并案比对</a></li>
							<li><a href="#tabs-2">可能的嫌疑人</a></li>
							<li><a href="#tabs-3">嫌疑人的可能活动区域</a></li>
						</ul>
						<div id="tabs-1" class="tabxon">
							<div id="mainCaseInfo" class="light-block-er" style="padding: 15px; margin-bottom: 15px;">
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
							<div style="overflow-x: auto; overflow-y: hidden; width: 100%;">
								<!-- <div style="width: 3000px; overflow: hidden;"> -->
									<table id="cacTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
										
									</table>
								<!-- </div> -->
							</div>
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
							</div>
							<table class="table table-bordered table-hover m-ui-table-whole"
								cellspacing="0" width="auto">
								<thead>
									<tr>
										<th width="250"></th>
										<th width="150">串并案分值</th>
										<th width="100">张某某</th>
										<th width="100">李某</th>
										<th width="100">王某三</th>
										<th width="100">钱某四</th>
										<th width="100">丁某五</th>
										<th width="100">刘某六</th>
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

							<div class="light-block-er"
								style="padding: 15px; margin-bottom: 15px;">
								<div class="row row-mar">
									<span class="color-gray2"
										style="display: inline-block; width: 250px;">本案的嫌疑人及已知串并案的嫌疑人：</span>
									<a href="#" class="btn btn-primary color-white">张某一</a> <a
										href="#" class="btn btn-primary color-white">李某二</a>
								</div>
								<div class="row row-mar">
									<span class="color-gray2"
										style="display: inline-block; width: 260px;">设置取最近活动记录的时间范围：</span>5天
								</div>
								<div class="row row-mar">
									<span class="color-gray2"
										style="display: inline-block; width: 250px;">可能的嫌疑人：</span> <a
										href="#" class="btn btn-bordered">张默某</a> <a href="#"
										class="btn btn-bordered">李某</a> <a href="#"
										class="btn btn-bordered">赵某某</a> <a href="#"
										class="btn btn-bordered">朱某某</a> <a href="#"
										class="btn btn-bordered">胡某某</a> <a href="#"
										class="btn btn-bordered">卓某某</a>
								</div>
								<div class="row row-mar">
									<span class="color-gray2"
										style="display: inline-block; width: 260px;">设置取最近活动记录的时间范围：</span>3天
								</div>
								<div class="row">
									<span class="color-gray2"
										style="display: inline-block; width: 260px;">显示辖区内活动记录最密集的区域数量：</span>5个
									<div class="warning-type"
										style="display: inline-block; margin-left: 20px;">
										<a href="#" class="btn sq-red"></a> <a href="#"
											class="btn sq-orange"></a> <a href="#" class="btn sq-yellow"></a>
										<a href="#" class="btn sq-green"></a> <a href="#"
											class="btn sq-blue"></a>
									</div>

								</div>
							</div>
							<div class="row">
								<img src="../images/map/map-miji.png">
							</div>
						</div>
						<!--嫌疑人的可能活动区域-->
					</div>
					<!-----------------tabs--------------------->
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/score/caseAnalysisCompareCommon.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/score/lookSnapshotDetail.js"></script>
</html>