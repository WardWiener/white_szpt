<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/highRiskPersonAlert/common/constant.jsp"%>
</head>
<body id="validform" class="validform m-ui-layer-body">

	<div class="m-ui-layer-box" style="width: 600px;">
		<div class="m-ui-layer-content">
			<div class="row  row-mar row-mar alert-info" style="padding: 10px 0">
				<div class="col-xs-2">
					<label class="control-label">身份证号：</label>
				</div>
				<div class="col-xs-6 m-label-right" name="idcode"></div>
			</div>

			<div class="row row-mar  alert-info" style="padding: 5px 0">
				<div class="col-xs-2 m-label-left">预警颜色：</div>
				<div class="col-xs-1">
					<div class="warning-type" style="padding-left: 10px;">
						<span class="btn sq-red" title="红色预警人员" style="display:none;"></span>
						<span class="btn sq-orange" title="橙色预警人员" style="display:none;"></span>
						<span class="btn sq-yellow" title="黄色预警人员" style="display:none;"></span>
						<span class="btn sq-white" title="白色预警人员" style="display:none;"></span>
						<span class="btn sq-blue" title="蓝色预警人员" style="display:none;"></span>
						<span class="btn sq-other" title="其它预警人员" style="display:none;"></span>
					</div>
				</div>
				<div class="col-xs-2 m-label-left">人员类型：</div>
				<div class="col-xs-5" name="peopleTypeName"></div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-4">
					姓名：<strong class="font16" name="name"></strong>
				</div>
				<div class="col-xs-4">
					曾用名：<span name="usedName"></span>
				</div>
				<div class="col-xs-4">
					绰号：<span name="nickname"></span>
				</div>
			</div>
			<p class="m-line"></p>
			<div class="row row-mar">
				<div class="col-xs-4">
					性别：<span name="sexName"></span>
				</div>
				<div class="col-xs-4">
					民族：<span name="ethnicGroup"></span>
				</div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-4">
					出生日期：<span id="birthday" name="birthday"></span>
				</div>
				<div class="col-xs-4">
					文化程度：<span name="educationName"></span>
				</div>
			</div>

			<p class="m-line"></p>
			<div class="row row-mar">
				户籍地址：<span name="registerAddress"></span>
			</div>
			<div class="row row-mar">
				现住地址：<span name="liveAddress"></span>
			</div>

			<p class="m-line"></p>
			<div class="row row-mar">
				<div class="col-xs-4">
					婚姻情况：<span name="marriageStatusName"></span>
				</div>
				<div class="col-xs-4">
					就业情况：<span name="employmentStatusName"></span>
				</div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-4">
					职业：<span name="professionName"></span>
				</div>
				<div class="col-xs-4">
					收入：<span name="income"></span>元
				</div>
			</div>
			<p class="m-line"></p>
			<div class="row row-mar">
				现工作地：<span name="workAddress"></span>
			</div>
			<p class="m-line"></p>
			<div class="row  mar-top row-mar">
				<div class="col-xs-2">照片：</div>
				<div id="picDiv" class="col-xs-6"></div>
			</div>






			<div
				style="background: #f8f8f8; border-top: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; padding: 10px 0;">
				<div class="row row-mar">
					<div class="col-xs-2"></div>
					<div class="col-xs-3">手机号</div>
					<div class="col-xs-3">IMEI号</div>
					<div class="col-xs-3">WIFI MAC</div>
				</div>
				<div class="row row-mar" id="phoneDiv"></div>
			</div>


			<div class="row row-mar mar-top">
				<div class="col-xs-2">
					<label class="control-label">新增原因：</label>
				</div>
				<div class="col-xs-6  m-label-right" ><span name="addReason"></span></div>
			</div>

			<div class="row row-mar mar-top">
				<div class="col-xs-2">
					<label class="control-label">在控类型：</label>
				</div>
				<div class="col-xs-6  m-label-right" name="personInControlTypeName"></div>
			</div>


			<div class="row  row-mar row-mar alert-info" style="padding: 10px 0">
				<div class="col-xs-2">
					<label class="control-label">审批意见：</label>
				</div>
				<div class="col-xs-8">
					<textarea rows="5" style="width: 100%; border-color: #036;"
						class="valiform-keyup form-val" datatype="*1-200"
						name="approveContent" id="approveContent"></textarea>
				</div>
			</div>



			<!--内容end-->


			<!--操作历史-->

			<div class="od-expand-btn" id="od-expand-btn-7"
				onClick="document.getElementById('od-expand-content-7').style.display='block';document.getElementById('od-expand-btn-7').style.display='none'">
				<a href="###"><span>操作历史</span></a>
			</div>
			<div id="od-expand-content-7" style="display: none;">
				<div class="m-ui-table m-ui-table-sm">
					<table id="table"
						class="table table-bordered table-hover m-ui-table-no-paginate"
						cellspacing="0" width="100%">
					</table>
				</div>
				<div class="od-expand-btn od-fold-btn"
					onClick="document.getElementById('od-expand-content-7').style.display='none';document.getElementById('od-expand-btn-7').style.display='block'">
					<a href="###"><span>隐藏操作历史</span></a>
				</div>
			</div>
			<!--操作历史-->


		</div>
	</div>

</body>
<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/personList/approvePerson.js"></script>

</html>