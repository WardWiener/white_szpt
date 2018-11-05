<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/highRiskPersonAlert/common/constant.jsp"%>
</head>
<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/common/wifiCommon.js"></script>

<body id="validform" class="validform m-ui-layer-body">
	<div class="m-ui-layer-box" >
<div class="m-ui-layer-content">

<div class="row  mar-top row-mar alert-info approveDiv" style="padding:10px;display:none">
<strong>审批意见：</strong><span id="approveContent"></span>
<p>审批人：<span id="approvePeople"></span><span style="margin-left:40px;">审批时间：</span><span id="approveTime"></span></p>
</div>

 		
<div class="row row-mar">
<div class="col-xs-2"> <label class="control-label">单据编码：</label></div>
<div class="col-xs-2"> <input id="num" type="text" class="form-control input-sm form-val"readonly name="num"></div>
</div>
<div class="row row-mar">
<div class="col-xs-2"> <label class="control-label">身份证号：<span class="red-star">*</span></label></div>
<div class="col-xs-4 input-group"><input id="idcardNo"name="idCardNo" type="text" class="form-control input-sm valiform-keyup form-val" datatype="*" nullmsg="请填写身份证号">
<span class="input-group-addon"id="searchIdcardNo"><span class="glyphicon glyphicon-search"></span></span></div>
<div class="col-xs-2"> <label class="control-label">姓名：</label></div>
<div class="col-xs-2"> <input id="personName" name="personName"type="text" class="form-control input-sm form-val"readonly></div>
</div>
<div class="row row-mar">
<div class="col-xs-2"> <label class="control-label">户籍地址：</label></div>
<div class="col-xs-4 "><input id="residenceAddress" type="text" class="form-control input-sm form-val"name="residenceAddress"readonly></div>
<div class="col-xs-2"> <label class="control-label">性别：</label></div>
<div class="col-xs-2"> <input id="sex" type="text" class="form-control input-sm form-val"name="sex"readonly style="display:none"><input id="sexName" type="text" class="form-control input-sm form-val"name="sexName"readonly></div>
</div>
<div id="phoneDiv"class="row row-mar">
<div class="col-xs-2"> <label class="control-label">手机：</label></div>
</div>

<div id="picDiv"class="row row-mar">
<div class="col-xs-2"> <label class="control-label">照片：</label></div>
</div>

<div class="row row-mar">
<div class="col-xs-2"> <label class="control-label">布控时间范围：<span class="red-star">*</span></label></div>
<div id="dateRange" class=" dateRange">
<input type="hidden" id="dtfmt" class="dateFmt" value="info@yyyy-MM-dd HH:mm" />
  <div class="col-xs-3 input-group" >
  <div class="input-group" >
							<input type="text" id="fixed_start" class="laydate-start form-control input-sm" readonly="readonly">
							<span class="laydate-start-span input-group-addon m-ui-addon">
								<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
							</span>
						</div>
  </div><div class="col-xs-1 to">——</div>
     <div class="col-xs-3 input-group">
			<div class="input-group" >
							<input type="text" id="fixed_end" class="laydate-end form-control input-sm" readonly="readonly">
							<span class="laydate-end-span input-group-addon m-ui-addon">
								<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
							</span>
						</div>
	    </div>
</div>										
</div>

<div class="row row-mar">
<div class="col-xs-2"> <label class="control-label">状态：</label></div>
<div class="col-xs-2"><select id="status"class="form-control input-sm form-val"name="status">
<option value="1">启用</option><option value="0">停用</option></select ></div>
</div>

<div class="row row-mar">
<div class="col-xs-2"> <label class="control-label">布控原因：</label></div>
<div class="col-xs-4"><textarea id="note"class="form-control input-sm valiform-keyup form-val" name="note"rows="3" datatype="*0-500" vf-position="4"></textarea></div>
</div>

<p class="m-line"></p>
<p class="color-gray">最新修改人：<span id="lastModifyPerson"></span><span style="margin-left:60px">最新修改时间：<span id="lastModifyTime"></span></span></p>

</div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/personExecuteControl/newPersonExecuteControl.js"></script>

</html>