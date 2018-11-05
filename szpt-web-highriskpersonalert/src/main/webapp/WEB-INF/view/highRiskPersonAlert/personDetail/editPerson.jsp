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
		<div class="row  mar-top row-mar alert-info " id="approveDiv"
			style="padding: 10px; display: none">
			<strong>审批意见：</strong><span id="approveContent"></span>
			<p>
				审批人：<span id="approvePeople"></span><span style="margin-left: 40px;">审批时间：</span><span
					id="approveTime"></span>
			</p>
		</div>
		<div id="topDiv" class="row row-mar mar-top">
			<div class="col-xs-3 m-label-right " id="nameDiv">
				姓名：<span id="name"></span>
			</div>
			<div class="col-xs-5 m-label-right" id="idcodeDiv">
				身份证：<span id="idcode"></span>
			</div>
			<div class="col-xs-2">
				<label class="control-label">绰号：</label>
			</div>
			<div class="col-xs-2">
				<input id="nickname" class="form-control input-sm valiform-keyup"
					datatype="*0-20">
			</div>
		</div>

		<div class="row row-mar  alert-info " id="newPageWarnDiv"
			style="padding: 5px 0; display: none">
			<div class="col-xs-2 m-label-left" style="display: none">预警颜色：</div>
			<div class="col-xs-1" style="display: none">
				<div class="warning-type" style="padding-left: 10px;"
					id="warnTypeDiv">
					<span class="btn sq-red" title="红色预警人员"
						id="warnType<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YJLX_HONG_SE%>"
						style="display: none"></span> <span class="btn sq-orange"
						title="橙色预警人员"
						id="warnType<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YJLX_CHENG_SE%>"
						style="display: none"></span> <span class="btn sq-yellow"
						title="黄色预警人员"
						id="warnType<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YJLX_HUANG_SE%>"
						style="display: none"></span> <span class="btn sq-white"
						title="白色预警人员"
						id="warnType<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YJLX_BAI_SE%>"
						style="display: none"></span> <span class="btn sq-blue"
						title="蓝色预警人员"
						id="warnType<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YJLX_LAN_SE%>"
						style="display: none"></span> <span class="btn sq-other"
						title="其他预警人员"
						id="warnType<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.YJLX_QI_TA%>"
						style="display: none"></span>
				</div>
			</div>
			<div class="col-xs-2 m-label-left">人员类型：</div>
			<div class="col-xs-5" id="peopleType"></div>
		</div>


		<div id="topDiv2" class="row row-mar newPageDiv" style="display: none">
			<div class="row row-mar">
				<div class="col-xs-2">
					<label class="control-label">姓名：<span class="red-star">*</span></label>
				</div>
				<div class="col-xs-2">
					<input id="name"
						class="form-control input-sm valiform-keyup form-val"
						datatype="*1-10" name="name">
				</div>
				<div class="col-xs-2">
					<label class="control-label">曾用名：</label>
				</div>
				<div class="col-xs-2">
					<input id="usedName"
						class="form-control input-sm valiform-keyup form-val"
						datatype="*0-10" name="usedName">
				</div>
				<div class="col-xs-2">
					<label class="control-label">绰号：</label>
				</div>
				<div class="col-xs-2">
					<input id="nickname" class="form-control input-sm valiform-keyup"
						datatype="*0-20" name="nickname">
				</div>
			</div>

		</div>
		<div class="row row-mar newPageDiv" style="display: none">
			<div class="col-xs-2">
				<label class="control-label">性别：<span class="red-star">*</span></label>
			</div>
			<div class="col-xs-4" style="width: 28%">
				<div id="sexDiv" class="row m-icheck-group"></div>
			</div>
			<div class="col-xs-2" style="width: 13.5%">
				<label class="control-label">民族：</label>
			</div>
			<div class="col-xs-3">
				<select id="ethnicGroup" name="ethnicGroup"
					class="form-control input-sm select2-noSearch allowClear form-val"><option
						value=""></option>
				</select>
			</div>
		</div>
		<div class="row row-mar newPageDiv" style="display: none">
			<div class="col-xs-2">
				<label class="control-label">出生日期：</label>
			</div>
			<div id="birthday" class="input-group laydate col-xs-3">
				<input type="hidden" class="laydate-fmt dateFmt" value="yyyy-MM-dd" />
				<input id="gatherTime_input"
					class="form-control laydate-value input-sm" readonly="readonly">
				<span class="input-group-addon m-ui-addon laydate-value-span">
					<span class="glyphicon glyphicon-calendar" aria-hidden="true"
					style="font-size: 16px;"></span>
				</span>
			</div>
			<div class="col-xs-2">
				<label class="control-label">文化程度：</label>
			</div>
			<div class="col-xs-3">
				<select id="education" name="education"
					class="form-control input-sm select2-noSearch allowClear form-val"><option
						value=""></option>
				</select>
			</div>
		</div>
		<div class="row row-mar newPageDiv" style="display: none">
			<div class="col-xs-2">
				<label class="control-label">预警类型：<span class="red-star">*</span></label>
			</div>
			<div class="col-xs-6 m-label-right" id="">
				<select id="warnType" name="warnType"
					class="form-control input-sm select2-noSearch allowClear form-val"><option
						value=""></option>
				</select>
			</div>
		</div>
		<div class="row row-mar" style="display: none">
			<div class="col-xs-2">
				<label class="control-label">预警类型：<span class="red-star">*</span></label>
			</div>
			<div class="col-xs-6">
				<div id="warnTypeDiv2" class="row m-icheck-group"></div>
			</div>
		</div>
		<div id="phoneDiv"
			style="background: #f8f8f8; border-top: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; padding: 10px 0;">
			<div class="row row-mar">
				<div class="col-xs-2"></div>
				<div class="col-xs-3">手机号</div>
				<div class="col-xs-3">IMEI号</div>
				<div class="col-xs-3">WIFI MAC</div>
			</div>
			
		</div>

		<!----------新增内容2016-10-11----------->
		<div class="row row-mar mar-top" id="personInControlTypeDiv2">
			<div class="col-xs-2">
				<label class="control-label">在控类型：<span class="red-star">*</span></label>
			</div>
			<div class="col-xs-6">
				<div class="row m-icheck-group" id="personInControlTypeDiv"></div>
			</div>
		</div>
		<div class="row row-mar">
			<div class="col-xs-2">
				<label class="control-label">户籍地址：</label>
			</div>
			<div class="col-xs-6 m-label-right" id="registerAddressDiv">
				<span id="registerAddress" name="registerAddress"></span>
			</div>
		</div>
		<div class="row row-mar">
			<div class="col-xs-2">
				<label class="control-label">现住地址：</label>
			</div>
			<div class="col-xs-6">
				<input id="liveAddress"
					class="form-control input-sm valiform-keyup form-val"
					datatype="*0-50" name="liveAddress">
			</div>
		</div>
		<div class="row row-mar">
			<div class="col-xs-2">
				<label class="control-label">婚姻情况：</label>
			</div>
			<div class="col-xs-2">
				<select id="marriageStatus"
					class="form-control input-sm select2-noSearch allowClear form-val"
					name="marriageStatus">
					<option value=""></option>
				</select>
			</div>
			<div class="col-xs-2">
				<label class="control-label">就业情况：</label>
			</div>
			<div class="col-xs-2">
				<select id="employmentStatus"
					class="form-control input-sm select2-noSearch allowClear form-val"
					name="employmentStatus">
					<option value=""></option>
				</select>
			</div>
		</div>
		<div class="row row-mar">
			<div class="col-xs-2">
				<label class="control-label">职业：</label>
			</div>
			<div class="col-xs-2">
				<select id="profession"
					class="form-control input-sm select2-noSearch allowClear form-val"
					name="profession">
					<option value=""></option>
				</select>
			</div>
			<div class="col-xs-2">
				<label class="control-label">收入：</label>
			</div>
			<div class="col-xs-2 input-group">
				<input id="income"
					class="form-control input-sm valiform-keyup form-val"
					datatype="*0-10" name="income"><span
					class="input-group-addon">元</span>
			</div>
		</div>

		<div class="row  mar-top row-mar">
			<div class="col-xs-2">
				<label class="control-label">现工作地：</label>
			</div>
			<div class="col-xs-6">
				<input id="workAddress"
					class="form-control input-sm valiform-keyup form-val"
					datatype="*0-50" name="workAddress">
			</div>
		</div>
		<div class="row  mar-top row-mar">
			<div class="col-xs-2">
				<label class="control-label">照片：<span class="red-star"
					id="containerStar"></span></label>
			</div>
			<div class="col-xs-6 upload-control" id="container"></div>
		</div>
		<div class="row" style="margin-left: 17%" id="picDiv"></div>
		<div class="row row-mar mar-top newPageDiv" id="bottomDiv"
			style="display: none">
			<div class="col-xs-2">
				<label class="control-label">新增原因：<span class="red-star">*</span></label>
			</div>
			<div class="col-xs-6">
				<textarea class="form-control valiform-keyup form-val" rows="5"
					name="addReason" id="addReason" datatype="*0-80"></textarea>
			</div>
		</div>

		<!----------新增内容2016-10-11----------->



		<div class="m-ui-title4 mar-top">
			<h2>人员类型</h2>
		</div>

		<div class="row row-mar">
			<div class="col-xs-3 m-label-right checkDiv">
				<input type="checkbox" class="icheckbox  type1"
					id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_XSQK%>"
					name="type1"
					value="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_XSQK%>"><label
					for="type_2" class="mar-left">刑事前科</label>
			</div>
			<div
				id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_XSQK%>Div"
				style="display: none" class="col-xs-4">
				<input
					id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_XSQK%>Tree"
					class="form-control input-sm ui-readonly-select" readonly>
			</div>

			<div id="xsqkDiv" style="display: none"
				class="col-xs-7 col-xs-offset-3 mar-top"
				style="padding:15px; background:#eff7ff;">
				<!-- <div id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_QJ%>Div"class="row row-mar"style="display:none">
                              <div class="col-xs-5">
                              <input class="form-control input-sm" value="抢劫案  →" readonly></div>
                              <div class="col-xs-7"><p class="mar-left">
                              <input id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_QJ%>Tree" class="form-control input-sm ui-readonly-select" readonly></p></div>
                              </div> -->

			</div>

		</div>
		<div class="row row-mar">
			<div class="col-xs-3 m-label-right checkDiv">
				<input type="checkbox" class="icheckbox type1"
					id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_SW%>"
					name="type1"
					value="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_SW%>"><label
					for="type_2" class="mar-left">涉稳</label>
			</div>
			<div
				id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_SW%>Div"
				style="display: none" class="col-xs-4">
				<input
					id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_SW%>Tree"
					class="form-control input-sm ui-readonly-select" value="" readonly>
			</div>
		</div>
		<div class="row row-mar">
			<div class="col-xs-3 m-label-right checkDiv">
				<input type="checkbox" class="icheckbox type1"
					id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_SK%>"
					name="type1"
					value="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_SK%>"><label
					for="type_3" class="mar-left">涉恐</label>
			</div>
			<div
				id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_SK%>Div"
				style="display: none" class="col-xs-4">
				<input
					id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_SK%>Tree"
					class="form-control input-sm ui-readonly-select" value="" readonly>
			</div>
		</div>
		<div class="row row-mar">
			<div class="col-xs-3 m-label-right checkDiv">
				<input type="checkbox" class="icheckbox type1"
					id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_SD%>"
					name="type1"
					value="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_SD%>"><label
					for="type_4" class="mar-left">涉毒</label>
			</div>
			<div
				id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_SD%>Div"
				style="display: none" class="col-xs-4">
				<input
					id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_SD%>Tree"
					class="form-control input-sm ui-readonly-select" value="" readonly>
			</div>
		</div>
		<div class="row row-mar">
			<div class="col-xs-3 m-label-right checkDiv">
				<input type="checkbox" class="icheckbox type1"
					id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_JSB%>"
					name="type1"
					value="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_JSB%>"><label
					for="type_5" class="mar-left">肇事肇祸精神病</label>
			</div>
			<div
				id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_JSB%>Div"
				style="display: none" class="col-xs-4">
				<input
					id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_JSB%>Tree"
					class="form-control input-sm ui-readonly-select" value="" readonly>
			</div>
		</div>
		<div class="row row-mar">
			<div class="col-xs-3 m-label-right checkDiv">
				<input type="checkbox" class="icheckbox type1"
					id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_ZDSF%>"
					name="type1"
					value="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_ZDSF%>"><label
					for="type_6" class="mar-left">重点上访</label>
			</div>
			<div
				id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_ZDSF%>Div"
				style="display: none" class="col-xs-4">
				<input
					id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_ZDSF%>Tree"
					class="form-control input-sm ui-readonly-select" value="" readonly>
			</div>
		</div>
		<div class="row row-mar">
			<div class="col-xs-3 m-label-right checkDiv">
				<input type="checkbox" class="icheckbox type1"
					id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_ZT%>"
					name="type1"
					value="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_ZT%>"><label
					for="type_7" class="mar-left">在逃</label>
			</div>
		</div>
		<div class="row row-mar">
			<div class="col-xs-3 m-label-right checkDiv">
				<input type="checkbox" class="icheckbox type1"
					id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_QSN%>"
					name="type1"
					value="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_QSN%>"><label
					for="type_8" class="mar-left">违法犯罪青少年</label>
			</div>
		</div>
		<div class="row row-mar">
			<div class="col-xs-3 m-label-right checkDiv">
				<input type="checkbox" class="icheckbox type1"
					id="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_AZBR%>"
					name="type1"
					value="<%=com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.RYLX_AZBR%>"><label
					for="type_9" class="mar-left">艾滋病人</label>
			</div>
		</div>

	</div>

	<!-- 模版 -->
	<div id="templateDiv" style="display:none;">
		<div id="phoneGroupDiv" class="row row-mar">
			<div class="col-xs-2">
				<label class="control-label"></label>
			</div>
			<div class="col-xs-3">
				<p class="mar-right">
					<input class="form-control input-sm phone" datatype="null|/^\d{11}$/">
				</p>
			</div>
			<div class="col-xs-3">
				<p class="mar-right">
					<input class="form-control input-sm imei" datatype="null|/^\d{15}$/">
				</p>
			</div>
			<div class="col-xs-3">
				<input class="form-control input-sm mac" datatype="null|/[A-F\d]{2}-[A-F\d]{2}-[A-F\d]{2}-[A-F\d]{2}-[A-F\d]{2}-[A-F\d]{2}/">
			</div>
		</div>
		<button id="addPhoneGroup" class="btn btn-info btn-sm" title="增加手机号">
			<span class="glyphicon glyphicon-plus"></span>
		</button>
		<button id="deletePhoneGroup" class="btn btn-info btn-sm deletePhone"  title="删除手机号">	
			<span class="glyphicon glyphicon-minus"></span>
		</button>
	</div>

</body>
<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/heatAnalyze/zTreeMenu.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/personDetail/editPerson.js"></script>

</html>