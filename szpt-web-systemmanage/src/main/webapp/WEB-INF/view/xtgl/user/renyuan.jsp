<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
</head>
<body class="m-ui-layer-body">
<div class="m-ui-layer-box" style="width:600px;">
<div class="m-ui-layer-searchbox" style="padding-bottom:0;">
<div class="row">
	<div class="col-xs-2"><label class="control-label">人员名称：</label></div>
	<div class="col-xs-8">
		<input type="text" id="perName" class="form-control input-sm" value="请输入" 
			onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
			onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color:#999">
	</div>
    <div class="col-xs-1" style="margin-left:-2px;">
    	<button class="btn btn-default btn-sm" id="searchPerson">
    		<span class="glyphicon glyphicon-search m-ui-nogap" aria-hidden="true"></span>
    	</button>
    </div>
</div>
<div class="m-ui-table m-ui-table-sm" style="max-height:250px; overflow-y:auto; overflow-x:hidden; margin-top:15px;">
	<table id="datatable" class="table table-bordered table-hover m-ui-table-no-paginate" cellspacing="0" width="100%">
    </table>
</div>
</div>
<!--弹出层列表内容end-->
</div>
</body>
<script type="text/javascript" src="<%=context%>/scripts/xtgl/user/renyuan.js"></script>

</html>