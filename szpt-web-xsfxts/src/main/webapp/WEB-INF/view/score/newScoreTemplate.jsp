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
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/cascadedDicItem.js"></script>
<%@include file="/WEB-INF/view/score/scoreTemplateCommon.jsp"%>
</head>
<body id="cbaGradeTemplate" class="validform m-ui-layer-body">
	<div class="m-ui-layer-box" style="width:800px;">
		<div id="cbaGradeTemplateData" class="m-ui-layer-content">
			<div class="row row-mar">
				<div class="col-xs-2">
					<label class="control-label">模板编码：<span class="red-star">*</span></label>
				</div>
				<div class="col-xs-3">
					<input name="code" class="form-control input-sm valiform-keyup form-val" datatype="*1-80" errormsg="不可超过80个字符">
				</div>
				<div class="col-xs-2">
					<label class="control-label">模板名称：<span class="red-star">*</span></label>
				</div>
				<div class="col-xs-3">
					<input name="name" class="form-control input-sm valiform-keyup form-val" datatype="*1-80" errormsg="不可超过80个字符">
				</div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-2">
					<label class="control-label">案件类别：<span class="red-star">*</span></label>
				</div>
				<div class="col-xs-3">
					<select id="type" name="type" class="form-control input-sm select2-noSearch allowClear form-val">
						
					</select>
				</div>
				<div class="col-xs-2">
					<label class="control-label">案件性质：<span class="red-star">*</span></label>
				</div>
				<div class="col-xs-3">
					<select id="firstSort" name="firstSort" class="form-control input-sm select2-noSearch allowClear form-val">
						
					</select>
				</div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-2">
					<label class="control-label">二级案件性质：<span id="twoNatureSpan" class="red-star" style="display:none;">*</span></label>
				</div>
				<div class="col-xs-3">
					<select id="secondSort" name="secondSort" class="form-control input-sm select2-noSearch allowClear form-val">
						
					</select>
				</div>
				<div class="col-xs-2">
					<label class="control-label">状态：</label>
				</div>
				<div class="col-xs-3">
					<select id="state" name="state" class="form-control input-sm select2-noSearch form-val">
						
					</select>
				</div>
			</div>
			
			<div class="row row-mar">
				<div class="col-xs-2">
					<label class="control-label">备注：</label>
				</div>
				<div class="col-xs-5">
					<textarea name="remarks" class="form-control valiform-keyup form-val" datatype="*0-80" errormsg="不可超过80个字符"> </textarea>
				</div>
			</div>

			<p class="row-mar">
				串并案默认显示最低分数值设置&nbsp;<span class="red-star">*</span><input name="minScore" class="form-control input-sm m-inline valiform-keyup form-val" datatype="/^[1-9]$|^[1-9][0-9]$|^[1][0][0]$/" errormsg="请输入1-100的整数"
					style="width: 50px;">&nbsp;分
			</p>
			<table id="gradeItemScoreTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0">
				<thead>
					<tr>
						<th width="25%" rowspan="2">评分项\得分分值（100分）</th>
						<th width="15%" rowspan="2">权重（10）<span class="red-star">*</span></th>
						<th colspan="4">匹配度区间（100分）</th>
					</tr>
					<tr>
						<th>0~25%<span class="red-star">*</span></th>
						<th>25%~50%<span class="red-star">*</span></th>
						<th>50%~75%<span class="red-star">*</span></th>
						<th>75%~100%<span class="red-star">*</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>作案特点（手段）相同</td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[0-9](.[0-9]{1,2})?$|^[1][0]$/" errormsg="请输入&lt;=10的小数或整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[1-9]$|^[1][0-9]$|^[2][0-5]$/" errormsg="请输入&gt;0&lt;=25的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[2][6-9]$|^[3-4][0-9]$|^[5][0]$/" errormsg="请输入&gt;25&lt;=50的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[5][1-9]$|^[6][0-9]$|^[7][0-5]$/" errormsg="请输入&gt;50&lt;=75的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[7][6-9]$|^[8-9][0-9]$|^[1][0][0]$/" errormsg="请输入&gt;75&lt;=100的正整数" value=""></td>
					</tr>
					<tr>
						<td>选择处所相同或相似</td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[0-9](.[0-9]{1,2})?$|^[1][0]$/" errormsg="请输入&lt;=10的小数或整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[1-9]$|^[1][0-9]$|^[2][0-5]$/" errormsg="请输入&gt;0&lt;=25的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[2][6-9]$|^[3-4][0-9]$|^[5][0]$/" errormsg="请输入&gt;25&lt;=50的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[5][1-9]$|^[6][0-9]$|^[7][0-5]$/" errormsg="请输入&gt;50&lt;=75的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[7][6-9]$|^[8-9][0-9]$|^[1][0][0]$/" errormsg="请输入&gt;75&lt;=100的正整数" value=""></td>
					</tr>
					<tr>
						<td>作案时段相同或相似</td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[0-9](.[0-9]{1,2})?$|^[1][0]$/" errormsg="请输入&lt;=10的小数或整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[1-9]$|^[1][0-9]$|^[2][0-5]$/" errormsg="请输入&gt;0&lt;=25的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[2][6-9]$|^[3-4][0-9]$|^[5][0]$/" errormsg="请输入&gt;25&lt;=50的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[5][1-9]$|^[6][0-9]$|^[7][0-5]$/" errormsg="请输入&gt;50&lt;=75的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[7][6-9]$|^[8-9][0-9]$|^[1][0][0]$/" errormsg="请输入&gt;75&lt;=100的正整数" value=""></td>
					</tr>
					<tr>
						<td>作案人数</td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[0-9](.[0-9]{1,2})?$|^[1][0]$/" errormsg="请输入&lt;=10的小数或整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[1-9]$|^[1][0-9]$|^[2][0-5]$/" errormsg="请输入&gt;0&lt;=25的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[2][6-9]$|^[3-4][0-9]$|^[5][0]$/" errormsg="请输入&gt;25&lt;=50的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[5][1-9]$|^[6][0-9]$|^[7][0-5]$/" errormsg="请输入&gt;50&lt;=75的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[7][6-9]$|^[8-9][0-9]$|^[1][0][0]$/" errormsg="请输入&gt;75&lt;=100的正整数" value=""></td>
					</tr>
					<tr>
						<td>作案进口</td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[0-9](.[0-9]{1,2})?$|^[1][0]$/" errormsg="请输入&lt;=10的小数或整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[1-9]$|^[1][0-9]$|^[2][0-5]$/" errormsg="请输入&gt;0&lt;=25的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[2][6-9]$|^[3-4][0-9]$|^[5][0]$/" errormsg="请输入&gt;25&lt;=50的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[5][1-9]$|^[6][0-9]$|^[7][0-5]$/" errormsg="请输入&gt;50&lt;=75的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[7][6-9]$|^[8-9][0-9]$|^[1][0][0]$/" errormsg="请输入&gt;75&lt;=100的正整数" value=""></td>
					</tr>
					<tr>
						<td>作案出口</td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[0-9](.[0-9]{1,2})?$|^[1][0]$/" errormsg="请输入&lt;=10的小数或整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[1-9]$|^[1][0-9]$|^[2][0-5]$/" errormsg="请输入&gt;0&lt;=25的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[2][6-9]$|^[3-4][0-9]$|^[5][0]$/" errormsg="请输入&gt;25&lt;=50的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[5][1-9]$|^[6][0-9]$|^[7][0-5]$/" errormsg="请输入&gt;50&lt;=75的正整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[7][6-9]$|^[8-9][0-9]$|^[1][0][0]$/" errormsg="请输入&gt;75&lt;=100的正整数" value=""></td>
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
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[0-9](.[0-9]{1,2})?$|^[1][0]$/" errormsg="请输入&lt;=10的小数或整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[1-9]$|^[1-9][0-9]$|^[1][0][0]$/" errormsg="请输入1-100的整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[1-9]$|^[1-9][0-9]$|^[1][0][0]$/" errormsg="请输入1-100的整数" value=""></td>
						<td><input class="form-control input-sm m-inline valiform-keyup" datatype="/^[1-9]$|^[1-9][0-9]$|^[1][0][0]$/" errormsg="请输入1-100的整数" value=""></td>
						<td></td>
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
	src="<%=context%>/scripts/score/newScoreTemplate.js"></script>
</html>