<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/instruction/common/constant.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript" src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
</head>
<body id="validform" class="validform m-ui-layer-body">
	<div class="m-ui-layer-box" >
<div class="m-ui-layer-content">
<div class="row row-mar">
<div class="col-xs-4"> <label class="control-label">指令类型：<span class="red-star">*</span></label></div>
<div class="col-xs-3"><select id="type"class="form-control input-sm select2-noSearch allowClear valiform-keyup" datatype="*" nullmsg="请选择类型"vf-position="4">
<option value=""></option>
    </select></div>
    <div class="col-xs-3" id="type2Div"style="display:none"><select id="type2"class="form-control input-sm select2-noSearch allowClear " >
<option value=""></option>
    </select></div>
</div> 
<div id="personDiv"class="row row-mar" style="display:none">
<div class="row row-mar">
<div class="col-xs-4"> <label class="control-label">姓名：<span class="red-star">*</span></label></div>
<div class="col-xs-6">
<input id="name"class="form-control input-sm typeContent"name="name">
</div></div>
<div class="row row-mar">
<div class="col-xs-4"> <label class="control-label">身份证号：<span class="red-star">*</span></label></div>
<div class="col-xs-6">
<input id="idcode"class="form-control input-sm typeContent"name="idcode">
</div></div>
<div class="row row-mar">
<div class="col-xs-4"> <label class="control-label">盘查地点：<span class="red-star">*</span></label></div>
<div class="col-xs-6">
<input id="personLocation"class="form-control input-sm typeContent"name="personLocation">
</div></div>
</div>
<div id="carDiv"class="row row-mar" style="display:none">
<div class="row row-mar">
<div class="col-xs-4"> <label class="control-label">车牌号：<span class="red-star">*</span></label></div>
<div class="col-xs-6">
<input id="carNum"class="form-control input-sm typeContent"name="carNum">
</div></div>
<div class="row row-mar">
<div class="col-xs-4"> <label class="control-label">盘查地点：<span class="red-star">*</span></label></div>
<div class="col-xs-6">
<input id="carLocation"class="form-control input-sm typeContent"name="carLocation">
</div></div>
</div> 
<div class="row row-mar">
<div class="col-xs-4"> <label class="control-label">接收单位：<span class="red-star">*</span></label></div>
<div class="col-xs-6 input-group">
	<input type="text" id="dwTree" class="form-control input-sm" readonly>
	</div>
</div> 
<div class="row row-mar">
<div class="col-xs-4"> <label class="control-label">指令内容：<span class="red-star">*</span></label></div>
<div class="col-xs-6"><textarea id="content"class="form-control input-sm valiform-keyup" rows="6" datatype="*1-500" nullmsg="请填写内容" vf-position="4"></textarea></div>
</div> 
<div class="row row-mar">
<div class="col-xs-4"> <label class="control-label">要求反馈时间：<span class="red-star">*</span></label></div>

<div id="backTime" class="laydate input-group" style="margin-right:10px;width:200px;margin-bottom:10px;">
	<input type="hidden" class="laydate-fmt dateFmt" value="info@yyyy-MM-dd HH:mm" />
	<input type="text" date-pos="top"  class="laydate-value form-control input-sm" readonly="readonly">
	<span class="laydate-value-span input-group-addon m-ui-addon">
		<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
	</span>
</div>
									
</div> 
<div class="row row-mar">
<div class="col-xs-4"> <label class="control-label">通知本单位负责人：<span class="red-star">*</span></label></div>
<div class="col-xs-6"><div class="row m-icheck-group" style="margin-bottom:10px;">
                      <p class="col-xs-4"><input value="1"type="radio" class="icheckradio" name="tongzhi" id="tongzhi_1" style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);">是</p>
                       <p class="col-xs-4"><input value="0"type="radio" class="icheckradio" name="tongzhi" id="tongzhi_0" style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);">否</p>
                     </div></div>
</div> 
</div>
	</div>
	<div id="menuContent" class="ztree-MenuContent">
		<!--  
		<input type="text" id="keySelect" style="margin-bottom:5px;" class="form-control input-sm" />
		-->
		<ul id="ztree-criminalRecord" class="ztree" style="width:200px; height: 150px;"></ul>
	</div>
</body>

<script type="text/javascript" src="<%=context%>/scripts/util/zTreeMenu.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/yryd/newInstruction/newInstruction.js"></script>
</html>