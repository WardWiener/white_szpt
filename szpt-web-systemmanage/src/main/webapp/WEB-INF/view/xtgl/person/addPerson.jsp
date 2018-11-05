<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<script>

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body class="m-ui-layer-body" id="validformId" >
<div id="pa_newPage" class="validform m-ui-layer-box" style="width: 800px;">
	
	<div class="m-ui-layer-searchbox" style="padding-bottom: 0;">
      <div class="row row-mar mar-top">
			<div class="col-xs-6">
				<div class="form-group">
					<div class="col-xs-4">
						<label class="control-label">姓名：<span class="red-star">*</span></label>
					</div>
					<div class="col-xs-7"><!-- /*datatype="zh2-10" errormsg="请输入2-10个汉字"*/ -->
						<input id="pa_newPage_personId" type="hidden" value="">
						<input id="pa_newPage_personCode" type="hidden" value="">
						<input id="pa_newPage_name" type="text" class="valiform-keyup form-val form-control input-sm" style="background-color:#eee" datatype="zh2-10" nullmsg="姓名不能为空" errormsg="请输入2-10个汉字" value="">
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<div class="col-xs-4">
						<label class="control-label">性别：<span class="red-star">*</span></label>
					</div>
					<div id="sex" class="col-xs-7">
						<select  id="pa_newPage_sex" class="form-control input-sm"></select>
					</div>
				</div>
			</div>
		</div>
		<div class="row row-mar mar-top">
			<div class="col-xs-6">
				<div class="form-group">
					<div class="col-xs-4">
						<label class="control-label">所属单位：<span class="red-star">*</span></label>
					</div>
					<div class="col-xs-7">
						<div class="input-group">
							<input id="pa_newPage_unit" class="form-control"  value="">
					    	<span id="pa_newPage_unitFind" class="input-group-addon m-ui-addon">
								<span class="glyphicon glyphicon-search" aria-hidden="true" style="font-size: 16px;"></span>
							</span>
							<input id="pa_newPage_unitId" type="hidden" value="">
							<input id="pa_newPage_unitCode" type="hidden" value="">
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<div class="col-xs-4">
						<label class="control-label">警号：<span class="red-star">*</span></label>
					</div>
					<div class="col-xs-7">
						<input id="pa_newPage_policeNo" type="text" class="valiform-keyup form-val form-control input-sm"  value="" datatype="*1-20">
					</div>
				</div>
			</div>
		</div>
		<div class="row row-mar mar-top">
			<div class="col-xs-6">
				<div class="form-group">
					<div class="col-xs-4">
						<label class="control-label">身份证号：<span class="red-star" style="visibility:hidden" >*</span></label>
					</div>
					<div class="col-xs-7">
						<input id="pa_newPage_statusCardNo" type="text" class="valiform-keyup form-val form-control input-sm" value="" datatype="n0-18 | str0-18" errormsg="请输入0-18个数字或字符">
					</div>
				</div>
			</div>

			<div class="col-xs-6">
				<div class="form-group">
					<div class="col-xs-4">
						<label class="control-label">职务：<span class="red-star" >*</span></label>
					</div>
					<div id="job" class="col-xs-7">
						<select id="pa_newPage_job" class="form-control select2-noSearch"></select>
					</div>
				</div>
			</div>
		</div>
		<div class="row row-mar mar-top">
			<div class="col-xs-6">
				<div class="form-group">
					<div class="col-xs-4">
						<label class="control-label">民族：<span class="red-star" style="visibility:hidden">*</span></label>
					</div>
					<div id="nationality" class="col-xs-7">
						<select id="pa_newPage_nationality" class="form-control select2-noSearch"></select>
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<div class="col-xs-4">
						<label class="control-label">政治面貌：<span class="red-star" style="visibility:hidden">*</span></label>
					</div>
					<div id="politicsType" class="col-xs-7">
						<select id="pa_newPage_politicsType" class="form-control select2-noSearch"></select>
					</div>
				</div>
			</div>
		</div>
		<div class="row row-mar mar-top">
			<div class="col-xs-6">
				<div class="form-group">
					<div class="col-xs-4">
						<label class="control-label">学历：<span class="red-star" style="visibility:hidden">*</span></label>
					</div>
					<div id="diploma" class="col-xs-7">
						<select id="pa_newPage_diploma" class="form-control select2-noSearch"></select>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row row-mar mar-top">
			<div class="col-xs-6">
				<div class="form-group">
					<div class="col-xs-4">
						<label class="control-label">移动电话：<span class="red-star" style="visibility:hidden">*</span></label>
					</div>
					<div class="col-xs-7">
						<input id="pa_newPage_movePhone" type="text" class="valiform-keyup form-val form-control input-sm"  datatype="n0-15 | str0-15,includeNum,halfByte" exclude="true" errormsg="请输入0-15位数字或字符" value="">
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<div class="col-xs-4">
						<label class="control-label">办公电话：<span class="red-star" style="visibility:hidden">*</span></label>
					</div>
					<div class="col-xs-7">
						<input id="pa_newPage_officePhone" type="text" class="valiform-keyup form-control input-sm" datatype="*0-20">
					</div>
				</div>
			</div>
		</div>
		<div class="row row-mar mar-top">
			<div class="col-xs-6">
				<div class="form-group">
					<div class="col-xs-4">
						<label class="control-label">状态：<span class="red-star">*</span></label>
					</div>
					<div id="status" class="col-xs-7">
						<select id="pa_newPage_status" class="form-control select2-noSearch"></select>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--弹出层列表内容end-->
	<div class="m-ui-commitbox">
		<button id="pa_newPage_configUser" class="btn btn-warning m-ui-btn1" style="display:none;">配置用户</button>
		<button id="pa_newPage_save" class="btn btn-success m-ui-btn1">保存</button>
		<button id="pa_newPage_cancel" class="btn btn-default m-ui-btn2">取消</button>
	</div>
</div>
<div id="menuContent" class="ztree-MenuContent">
	<input type="text" id="keySelect" style="margin-bottom:5px; display:none" class="form-control input-sm" />
	<ul id="ztree-unitSelect" class="ztree" style="width:200px; height: 300px;"></ul>
</div>
<!-- 自己写js -->
<script type="text/javascript" src="<%=context %>/scripts/xtgl/person/addPerson.js"></script>

</body>
</html>

