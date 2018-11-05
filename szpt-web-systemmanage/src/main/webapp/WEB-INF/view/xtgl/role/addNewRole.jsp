<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
 <%  
      String constantStauts_qy = com.taiji.pubsec.szpt.xtgl.Constant.STATUS_START;
      String constantStauts_ty = com.taiji.pubsec.szpt.xtgl.Constant.STATUS_STOP;
      String constantStauts = com.taiji.pubsec.szpt.xtgl.Constant.STATUS;
 %>
 <script>
var constantStauts_qy = '<%=constantStauts_qy%>';
var constantStauts_ty = '<%=constantStauts_ty%>';
var constantStauts = '<%=constantStauts%>';
</script>
</head>

<body class="m-ui-layer-body validform" id="validformId" style="color:#333">

<div class="m-ui-layer-searchbox">
		<div class="m-ui-layer-searchbox" style="padding-bottom: 0;">
			<div class="row row-mar mar-top">
				<div class="col-xs-6">
					<div class="form-group">
						<div class="col-xs-4">
							<label class="control-label">角色名称：<span class="red-star">*</span></label>
						</div>
						<div class="col-xs-8">
							<input type="text" id="roleName" class="form-control input-sm valiform-keyup form-val" vf-position="2" datatype="*1-10" style="color:#333">
						</div>
					</div>
				</div>
				<div class="col-xs-5">
					<div class="form-group">
						<div class="col-xs-4">
							<label class="control-label">状态：</label>
						</div>
						<div class="col-xs-8">
								<select id="status" class="select2-noSearch">
								</select>
							</div>
					</div>
				</div>
			</div>
			<div class="row row-mar mar-top">
				<div class="col-xs-6">
					<div class="form-group">
						<div class="col-xs-4">
							<label class="control-label">角色编码：<span class="red-star">*</span></label>
						</div>
						<div class="col-xs-8">
							<input type="text" id="roleCode"  maxlength="50" vf-position="2"  style="color:#333"
							 errormsg="长度为1至50的数字、字母！" class="form-control input-sm valiform-keyup form-val"   datatype="n1-50 | str1-50,halfByte" >
						</div>
					</div>
				</div>
			</div>
			<div class="row row-mar mar-top">
				<div class="col-xs-12">
				  <div class="form-group">
				   <div class="col-xs-2"> <label class="control-label">权限信息：<span class="red-star" style="visibility:hidden">*</span></label></div>
				    <div class="col-xs-10">
					<div id="roleSelecter" style="width:600px;">
						<div class="col-xs-5">
							 <div class="m-ui-title-sm2" style="line-height:30px;">
							 	所有权限：
							 	
							 </div>
				               		<div class="m-ztree-out" style="background:none"> 
				            			<input type="text" id="keyLeft" style="margin-bottom:5px;color:#333" class="form-control input-sm" />
				               			 <ul id="ztree-roleLeft" class="ztree" style="height:300px"></ul>
				             		</div>
						</div>
						<div class="col-xs-2" style="padding-top: 20%;">
						<div style="padding-left:15%">
					     	<button id="toRight" class="btn btn-success btn-sm" style="float:center;width:50%;"><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></button>
						    <button id="remove" class="btn btn-danger btn-sm" style="float:center;width:50%;margin-top:10%"><span class="glyphicon  glyphicon-menu-left m-ui-nogap" aria-hidden="true"></span></button>
						</div>
						 </div>
						<div class="col-xs-5" >
						        <div class="m-ui-title-sm2" style="line-height:30px;">
						     	   	已选权限：
						        </div>
							<div class="m-ztree-out" style="background:none"> 
								 <input type="text" id="keyRight" style="margin-bottom:5px;color:#333" class="form-control input-sm" />
							     <ul id="ztree-roleRight" class="ztree" style="height:300px"></ul>
							</div>
						</div>
					 </div>
				     </div>
				  </div>
				</div>
		 	</div>
			<div class="m-ui-commitbox">
					<button class="btn btn-success" id="saveRole">保存</button>
					<button class="btn btn-success" style="display: none;" id="modify">修改</button>
					<button class="btn btn-primary" id="cancel">取消</button>
			</div>
		</div>
	</div>
	





</body>
<script type="text/javascript"
			src="<%=context%>/scripts/xtgl/role/addNewRole.js"></script>

</html>