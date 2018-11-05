<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
 <%  
      String constantResourceType = com.taiji.pubsec.szpt.xtgl.Constant.RESOURCE_TYPE; 
 %>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<script>
var constantResourceType = '<%=constantResourceType%>';
</script></head>

<body class="m-ui-layer-body">
<div id="pa_newPage" class="validform m-ui-layer-box" style="width: 500px;">
	<div class="m-ui-layer-searchbox" style="padding-bottom: 0;">
       <div class="row row-mar mar-top">
			<div class="col-xs-12">
				<div class="form-group">
					<div class="col-xs-4">
						<label class="control-label">资源名称：<span class="red-star">*</span></label>
					</div>
					<div class="col-xs-7">
						<input id="pa_newPage_name" type="text" class="valiform-keyup form-val form-control" datatype="*1-60" nullmsg="名称不能为空"  value="">
					</div>
				</div>
			</div>
			
		</div>
		
		<div class="row row-mar mar-top">
		<div class="col-xs-12">
				<div class="form-group">
					<div class="col-xs-4">
						<label class="control-label">资源链接：<span class="red-star">*</span></label>
					</div>
					<div class="col-xs-7">
						<input id="pa_newPage_link" class="valiform-keyup form-val form-control" datatype="*1-200" nullmsg="链接不能为空"  value="" type="text" >
					</div>
				</div>
			</div>
		</div>
		<div class="row row-mar mar-top">
			<div class="col-xs-12">
					<div class="form-group">
					<div class="col-xs-4">
						<label class="control-label">资源类型：<span class="red-star">*</span></label>
					</div>
					<div class="col-xs-7">
						<select id="pa_newPage_type" class="form-control input-sm"></select>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	<!--弹出层列表内容end-->
	<div class="m-ui-commitbox">
		<button id="pa_newPage_save" class="btn btn-success m-ui-btn1">保存</button>
		<button id="pa_newPage_cancel" class="btn btn-default m-ui-btn2">取消</button>
	</div>
</div>

<!-- 自己写js -->
<script type="text/javascript" src="<%=context %>/scripts/xtgl/resource/addResource.js"></script>

</body>
</html>

