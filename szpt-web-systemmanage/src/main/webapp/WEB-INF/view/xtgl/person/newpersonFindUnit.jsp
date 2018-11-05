<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<script>

</script>
</head>
<body class="m-ui-layer-body">
	<div id="pa_findUnitPage" class="m-ui-layer-box" style="width:600px;">
	<div class="m-ui-title2">
		<h2>选择单位</h2>
		<div class="m-ui-caozuobox" style="text-align: right;">
			<button id="pa_findUnitPage_close" class="btn m-ui-close"></button>
		</div>
	</div>
	<div class="m-ui-layer-searchbox" style="padding-bottom: 0;">
		<div class="row">
			<div class="col-xs-2">
				<label class="control-label">单位名称：</label>
			</div>
			<div class="col-xs-8">
				<input id="pa_findUnitPage_searchText" type="text" class="form-control input-sm" value="请输入" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color: #999">
			</div>
			<div class="col-xs-1" style="margin-left: -2px;">
				<button id="pa_findUnitPage_search" class="btn btn-default btn-sm">
					<span class="glyphicon glyphicon-search m-ui-nogap" aria-hidden="true"></span>
				</button>
			</div>
		</div>
		<div class="m-ui-table m-ui-table-sm" style="max-height: 250px; overflow-y: auto; overflow-x: hidden; margin-top: 15px;">
			<table id="pa_findUnitPage_unitTable" class="table table-bordered table-hover m-ui-table-no-paginate" cellspacing="0" width="100%"></table>
		</div>
	</div>
	<!--弹出层列表内容end-->
	<div class="m-ui-commitbox">
		<button id="pa_findUnitPage_save" class="btn btn-success m-ui-btn1">确认</button>
		<button id="pa_findUnitPage_cancel" class="btn btn-default m-ui-btn2">取消</button>
	</div>
</div>

<!-- 自己写js -->
<script type="text/javascript" src="<%=context %>/scripts/xtgl/person/newPersonFindUnit.js"></script>

</body>
</html>
