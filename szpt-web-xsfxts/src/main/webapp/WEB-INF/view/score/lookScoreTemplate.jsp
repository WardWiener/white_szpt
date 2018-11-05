<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript" src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
<%@include file="/WEB-INF/view/score/scoreTemplateCommon.jsp"%>
</head>
<body id="cbaGradeTemplate" class="validform m-ui-layer-body">
	<div class="m-ui-layer-box" style="width:800px;">
		<div id="cbaGradeTemplateData" class="m-ui-layer-content">
			<div class="row row-mar">
				<div class="col-xs-2">
					<label class="control-label">模板编码：</label>
				</div>
				<div class="col-xs-3">
					<div name="code" class="m-label-right"></div>
				</div>
				<div class="col-xs-2">
					<label class="control-label">模板名称：</label>
				</div>
				<div class="col-xs-3">
					<div name="name" class="m-label-right"></div>
				</div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-2">
					<label class="control-label">案件类别：</label>
				</div>
				<div class="col-xs-3">
					<div name="typeName" class="m-label-right"></div>
				</div>
				<div class="col-xs-2">
					<label class="control-label">案件性质：</label>
				</div>
				<div class="col-xs-3">
					<div name="firstSortName" class="m-label-right"></div>
				</div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-2">
					<label class="control-label">二级案件性质：</label>
				</div>
				<div class="col-xs-3">
					<div name="secondSortName" class="m-label-right"></div>
				</div>
				<div class="col-xs-2">
					<label class="control-label">状态：</label>
				</div>
				<div class="col-xs-3">
					<div name="stateName" class="m-label-right"></div>
				</div>
			</div>
			
			<div class="row row-mar">
				<div class="col-xs-2">
					<label class="control-label">备注：</label>
				</div>
				<div class="col-xs-5">
					<div name="remarks" class="m-label-right"></div>
				</div>
			</div>

			<p class="row-mar">
				串并案默认显示最低分数值设置&nbsp;<span name="minScore"></span>&nbsp;分
			</p>
			<table id="gradeItemScoreTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0">
				<thead>
					<tr>
						<th width="25%" rowspan="2">评分项\得分分值（100分）</th>
						<th width="15%" rowspan="2">权重（10）</th>
						<th colspan="4">匹配度区间（100分）</th>
					</tr>
					<tr>
						<th>0~25%</th>
						<th>25%~50%</th>
						<th>50%~75%</th>
						<th>75%~100%</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>作案特点（手段）相同</td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
					</tr>
					<tr>
						<td>选择处所相同或相似</td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
					</tr>
					<tr>
						<td>作案时段相同或相似</td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
					</tr>
					<tr>
						<td>作案人数</td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
					</tr>
					<tr>
						<td>作案进口</td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
					</tr>
					<tr>
						<td>作案出口</td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td>不相邻社区</td>
						<td>相邻社区</td>
						<td>相同社区</td>
						<td>--</td>
					</tr>
					<tr>
						<td>发案社区</td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
						<td><span></span></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--内容end-->
	</div>
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/score/scoreTemplateCommon.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/score/lookScoreTemplate.js"></script>
</html>