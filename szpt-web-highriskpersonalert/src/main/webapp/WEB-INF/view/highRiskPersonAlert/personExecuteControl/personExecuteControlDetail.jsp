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
	<div class="m-ui-layer-box" >
		
<div class="m-ui-layer-content">
<div class="row row-mar">
<div class="col-xs-2"> <label class="control-label">单据编码：</label></div>
<div class="col-xs-2"> <input id="num" type="text" class="form-control input-sm form-val"readonly name="num"></div>
</div>
<div class="row row-mar">
<div class="col-xs-2"> <label class="control-label">身份证号：<span class="red-star">*</span></label></div>
<div class="col-xs-4 input-group"><input id="idcardNo"name="idCardNo" type="text" class="form-control input-sm  valiform-keyup form-val"datatype=" /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/" nullmsg="请填写身份证号">
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

<div class="row row-mar approveDiv" style="display:none">
<div class="col-xs-2"> <label class="control-label">审批意见：</label></div>
<div class="col-xs-4"><textarea id="approveContent"class="form-control input-sm valiform-keyup form-val" name="approveContent"rows="3" datatype="*0-500" vf-position="4"></textarea></div>
</div>

<!--操作历史-->
<div class="m-ui-title4"><h2>操作历史</h2></div>  
<div class="od-expand-btn" id="od-expand-btn-1" onClick="document.getElementById('od-expand-content-1').style.display='block';document.getElementById('od-expand-btn-1').style.display='none'">
<a href="###"><span>显示操作历史</span></a>
</div>
<div id="od-expand-content-1" style="display:none;">
<div class="m-ui-table m-ui-table-sm">
	<table id="table"class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="90%">
    </table>
</div>
<div class="od-expand-btn od-fold-btn" onClick="document.getElementById('od-expand-content-1').style.display='none';document.getElementById('od-expand-btn-1').style.display='block'"><a href="###"><span>隐藏操作历史</span></a></div>
</div>
<!--操作历史-->

<div style="display:none">
<p class="m-line"></p>
<p class="color-gray">最新修改人：<span id="lastModifyPerson"></span><span style="margin-left:60px">最新修改时间：<span id="lastModifyTime"></span></span></p>
</div>
</div>
	</div>
	
</body>
 <script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/personExecuteControl/personExecuteControlDetail.js"></script>

</html>