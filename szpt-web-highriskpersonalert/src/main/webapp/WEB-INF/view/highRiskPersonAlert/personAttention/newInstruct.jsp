<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台–WIFI分析</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript" src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
</head>

<body class="m-ui-layer-body">
	<div class="m-ui-layer-box" style="width: 600px;">
		<div class="m-ui-layer-content">
			<div class="row row-mar">
				<div class="col-xs-3">
					<label class="control-label">预警人员：</label>
				</div>
				<div class="col-xs-9">
					<div id="personCheckboxDiv" class="m-icheck-group">
						
					</div>
				</div>
			</div>
			<div id="tabs1" class="m-ui-tabs2">
				<ul id="tabsUl" class="nav nav-tabs">
					
				</ul>
				<div id="tabsDiv">
					
				</div>
			</div>
		</div>
	</div>
	<div style="display:none;">
		<div id="instructTemplate" class="tabxon">
			<div class="row row-mar mar-top">
				<div class="col-xs-3">
					<label class="control-label">指令类型：<span class="red-star">*</span></label>
				</div>
				<div class="col-xs-3">
					<select name="instructTypeFirst" class="form-control input-sm select2-noSearch allowClear">
						
					</select>
				</div>
				<div class="col-xs-3" style="display:none;">
					<div style="padding: 0 10px;">
						<select name="instructTypeSecond" class="form-control input-sm select2-noSearch">
							
						</select>
					</div>
				</div>
			</div>
			<div name="personInfo" class="alert alert-info" style="display:none;">
				<div class="row row-mar">
					<div class="col-xs-2">
						<label class="control-label">姓名：</label>
					</div>
					<div class="col-xs-3">
						<input name="personName" type="text" class="form-control input-sm">
					</div>
					<div class="col-xs-2">
						<label class="control-label">身份证号：</label>
					</div>
					<div class="col-xs-4">
						<input name="idCode" type="text" class="form-control input-sm">
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<label class="control-label">盘查地点：</label>
					</div>
					<div class="col-xs-9">
						<input name="personQuestionAddress" type="text" class="form-control input-sm">
					</div>
				</div>
			</div>
			<div name="carInfo" class="alert alert-info" style="display:none;">
				<div class="row row-mar">
					<div class="col-xs-2">
						<label class="control-label">车牌号：</label>
					</div>
					<div class="col-xs-4">
						<input name="carNumber" type="text" class="form-control input-sm">
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<label class="control-label">盘查地点：</label>
					</div>
					<div class="col-xs-9">
						<input name="carQuestionAddress" type="text" class="form-control input-sm">
					</div>
				</div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-3">
					<label class="control-label">指令内容：<span class="red-star">*</span></label>
				</div>
				<div class="col-xs-8">
					<textarea name="content" class="form-control input-sm" rows="6"></textarea>
				</div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-3">
					<label class="control-label">指令附件：</label>
				</div>
				<div class="col-xs-7">
					<div name="pcrUpload" class="upload-control"></div>
					<div name="showAttach"></div>
				</div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-3">
					<label class="control-label">要求反馈时间：<span
						class="red-star">*</span></label>
				</div>
				<div name="feedbackTime" class="col-xs-4 input-group laydate">
					<input type="hidden" class="laydate-fmt dateFmt" value="info@yyyy-MM-dd HH:mm" />
					<input type="text" class="laydate-value form-control input-sm" readonly="readonly"> 
					<span class="laydate-value-span input-group-addon m-ui-addon">
						<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
					</span>
				</div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-3">
					<label class="control-label">接收单位：<span class="red-star">*</span></label>
				</div>
				<div class="col-xs-4 input-group">
					<input name="unit" type="text" class="form-control input-sm" readonly>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-3">
					<label class="control-label">通知本单位负责人：<span
						class="red-star">*</span></label>
				</div>
				<div class="col-xs-4">
					<div class="row m-icheck-group" style="margin-bottom: 10px;">
						<p class="col-xs-4">
							<input type="radio" name="tongzhi" value="1">是
						</p>
						<p class="col-xs-4">
							<input type="radio" name="tongzhi" value="0">否
						</p>
					</div>
				</div>
			</div>
		</div> 
	</div>
</body>

<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/personAttention/newInstruct.js"></script>
<style>
</style>
</html>