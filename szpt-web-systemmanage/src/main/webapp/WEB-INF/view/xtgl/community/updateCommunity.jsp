<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<script>
	$(document).ready(function() {

	});
</script>
</head>

<body id="validformId" class="m-ui-layer-body validform">
	<div class="m-ui-layer-box" style="width: 75%; border: 0px">
		<div class="m-ui-layer-searchbox">
			<div class="row row-mar2" style="margin-top: 20px">
				<div class="form-group">
					<div class="col-xs-3">
						<label class="control-label">所属单位：</label>
					</div>
					<div class="col-xs-7">
						<div class="input-group ">
							<input class="form-control unit valiform-keyup form-val"
								nullmsg="单位不能为空" vf-position="2" datatype="*1-80" id="unitName"
								readonly> <input type="hidden" id="unitId"> <span
								class="input-group-addon m-ui-addon unit"> <span
								class="glyphicon glyphicon-search" aria-hidden="true"
								style="font-size: 16px;"> </span>
							</span>
						</div>
					</div>
					<span class="red-star">*</span>
				</div>
			</div>
			<div class="row row-mar2" style="margin-top: 20px">
				<div class="form-group">
					<div class="col-xs-3">
						<label class="control-label">所属大社区：</label>
					</div>
					<div class="col-xs-7">
						<select class="form-control select2-noSearch allowClear" id="bigCommunity">
						
						</select>
					</div>
				</div>
			</div>
			<div class="row row-mar2" style="margin-top: 20px">
				<div class="form-group">
					<div class="col-xs-3">
						<label class="control-label">社区名称：</label>
					</div>
					<div class="col-xs-7">
						<input type="text" id="communityName"
							class="form-control input-sm valiform-keyup form-val"
							vf-position="2" nullmsg="社区名称不能为空" datatype="*1-80">
					</div>
					<span class="red-star">*</span>
				</div>
			</div>
			<div class="row row-mar2" style="margin-top: 20px">
				<div class="form-group">
					<div class="col-xs-3">
						<label class="control-label">社区编号：</label>
					</div>
					<div class="col-xs-7">
						<input type="text" id="communityCode"
							class="form-control input-sm valiform-keyup form-val"
							nullmsg="社区编号不能为空" vf-position="2" datatype="*1-80">
					</div>
					<span class="red-star">*</span>
				</div>
			</div>
		</div>
	</div>
	<div id="menuContent" class="ztree-MenuContent">
		<input type="text" id="keySelect" style="margin-bottom: 5px;"
			class="form-control input-sm" />
		<ul id="ztree-unitSelect" class="ztree"
			style="width: 230px; height: 300px;"></ul>
	</div>

	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
	<script type="text/javascript"
		src="<%=context%>/scripts/xtgl/community/updateCommunity.js"></script>
</body>
</html>