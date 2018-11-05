<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
 <%  
      String constantStauts_qy = com.taiji.pubsec.szpt.xtgl.Constant.STATUS_START;
      String constantStauts_ty = com.taiji.pubsec.szpt.xtgl.Constant.STATUS_STOP;
      String constantStauts = com.taiji.pubsec.szpt.xtgl.Constant.STATUS; 
      String constant_unitInit = com.taiji.pubsec.szpt.xtgl.Constant.ORGANIZATION_INIT;
 %>
<script>
var constantStauts_qy = '<%=constantStauts_qy%>';
var constantStauts_ty = '<%=constantStauts_ty%>';
var constantStauts = '<%=constantStauts%>';
var constantInit = '<%=constant_unitInit%>'
</script>

</head>

<body  class="m-ui-layer-body ">
	<div  id="validformId" class="validform  m-ui-layer-box " style="width: 75%; border: 0px;">
		<div class="m-ui-layer-searchbox">
			<div class="row row-mar mar-top">
				<div class="col-xs-3">
					<label class="control-label">名称：<span class="red-star">*</span></label>
				</div>
				<div class="col-xs-7">
					<input type="text" id="itemName" class="form-control input-sm valiform-keyup form-val" nullmsg="数据字典项名称不能为空" vf-position="2" datatype="*1-20">
				</div>
			</div>
			<div class="row row-mar mar-top">
				<div class="col-xs-3">
					<label class="control-label">编码：<span class="red-star">*</span></label>
				</div>
				<div class="col-xs-7">
					<input type="text" id="itemCode" class="form-control input-sm valiform-keyup form-val"  nullmsg="数据字典项编码不能为空" vf-position="2" datatype="*1-20">
				</div>
			</div>
			<div class="row row-mar mar-top">
					<div class="col-xs-3">
						<label class="control-label">状态：<span class="red-star">*</span></label>
					</div>
					<div class="col-xs-7">
						<select id="itemStatus" class="form-control input-sm"></select>
					</div>
			</div>
			<div class="row row-mar mar-top">
				<div class="col-xs-3">
					<label class="control-label">备注：<span class="red-star" style="visibility:hidden">*</span></label>
				</div>
				<div class="col-xs-7">
					<input type="text" id="itemdescription" class="valiform-keyup form-control input-sm  form-val" datatype="*0-500">
				</div>
			</div>
		</div>
		<!--内容end-->
	</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
	<script type="text/javascript" src="<%=context%>/scripts/dictionary/addItem.js"></script>
</body>
</html>





