<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<script>
(function($){
	"use strict";
})(jQuery);	
	
</script>
</head>

<body class="m-ui-layer-body validform" id="validformId" style="color:#333 !important">
	
		<div class="m-ui-layer-searchbox">

			<div class="row row-mar mar-top">
				<div class="col-xs-6">
					<div class="form-group">
						<div class="col-xs-4">
							<label class="control-label">权限名称：<span class="red-star">*</span></label>
						</div>
						<div class="col-xs-7">
							<input type="text" id="powerName" style="color:#333 !important" class="form-control input-sm valiform-keyup form-val" vf-position="2" datatype="*1-20">
						</div>
					</div>
				</div>
				<div class="col-xs-6">
					<div class="form-group" style="padding-left:60px">
						<div class="col-xs-4">
							<label class="control-label">权限编码：<span class="red-star">*</span></label>
						</div>
						<div class="col-xs-7">
							<input id="powerCategory" style="color:#333 !important" class="form-control input-sm valiform-keyup form-val" vf-position="2" datatype="*1-20">
						</div>
					</div>
				</div>
			</div>
			<div class="row row-mar mar-top">
			   <div class="col-xs-12">
			        <div class="form-group">
			      <div class="col-xs-2"> <label class="control-label">资源信息：<span class="red-star" style="visibility:hidden">*</span></label></div>
			      <div class="col-xs-10">
			      <div id="roleSelecter" style="width:600px;">
				<div class="col-xs-5" >
				    
					   <div class="m-ui-title-sm2" style="line-height:30px;">可选资源：</div>
					   <div class="m-ztree-out" style="background:none"> 
				            			<input type="text" id="keyLeft" style="margin-bottom:5px;color:#333 !important" class="form-control input-sm" />
				               			 <ul id="ztree-roleLeft" class="ztree" style="height:300px"></ul>
				             		</div>
					   <!--  <select size="16" style="padding: 5px; width: 80%;" multiple="multiple" id="availableResource"></select>-->
			      
				</div>
				<div class="col-xs-2" style="padding-top: 20%;">
						<div style="padding-left:15%">
					     	<button id="toRight" class="btn btn-success btn-sm" style="float:center;width:50%;"><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></button>
						    <button id="remove" class="btn btn-danger btn-sm" style="float:center;width:50%;margin-top:10%"><span class="glyphicon  glyphicon-menu-left m-ui-nogap" aria-hidden="true"></span></button>
						</div>
			     </div>
			     <!-- 
				<div class="col-xs-2" style="padding-top: 20%;">
					<div style="padding-left: 10px;">
						<button type="button" class="btn btn-default"
							style="margin-bottom: 10px;" id="rightshift">
							<span class="glyphicon glyphicon-menu-right m-ui-nogap"
								aria-hidden="true"></span>
						</button>
						<button type="button" class="btn btn-default" 
							style="margin-bottom: 10px;" id="leftshift">
							<span class="glyphicon glyphicon-menu-left m-ui-nogap"
								aria-hidden="true"></span>
						</button>
					</div>
				</div> -->
				<div class="col-xs-5">
				    
					<div class="m-ui-title-sm2" style="line-height:30px;">已选资源：</div>
					<div class="m-ztree-out" style="background:none"> 
								 <input type="text" id="keyRight" style="margin-bottom:5px;color:#333 !important" class="form-control input-sm" />
							     <ul id="ztree-roleRight" class="ztree" style="height:300px"></ul>
							</div>
					<!--  <select size="16" style="padding: 5px; width: 90%;" id="possessResource" multiple="multiple"></select>-->
				
				</div>
				</div>
				</div>
				</div>
			</div>
			</div>		
		</div>
		<!--弹出层列表内容end-->
		<div class="m-ui-commitbox">
			<button class="btn btn-success" id="savePower">保存</button>
			<button class="btn btn-success" style="display: none;" id="modify">修改</button>
			<button class="btn btn-default" id="cancel">取消</button>
		</div>
	


</body>
<script type="text/javascript"
			src="<%=context%>/scripts/xtgl/authority/addAuthority.js"></script>

</html>