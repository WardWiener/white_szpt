<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台– 系统管理</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<script>
$(document).ready(function() {	
	
});
</script>
</head>

<body id="validformId">
<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<div id="c-center">
		<%@include file="/WEB-INF/base/skin/leftPart-xtgl.jsp"%>
		<div id="c-center-right">
				<div id="c-center-right-content">
					<div id="c-center-right-content-block">
						<!--树形菜单start-->
						<div id="c-center-right-content-bar" style="display:none">
							<div class="m-ui-title5">
								
								<input type="text" id="keyIn" style="margin-bottom: 5px"
									class="form-control input-sm" />
								<ul id="ztree-inUnitTree" class="ztree"></ul>
							</div>
						</div>
						<!--树形菜单end-->
						<div id="c-center-right-content-con" style="margin-left:0">
							<ol class="breadcrumb m-ui-breadcrumb">
								<li>系统管理</li>
								<li>角色权限管理</li>
								<li>权限管理</li>
							</ol>
							<div class="m-ui-searchbox">
								<div class="m-ui-searchbox-con">
									<div class="row row-mar mar-top">
										<div class="col-xs-4">
											<div class="form-group">
												<div class="col-xs-4">
													<label class="control-label">权限名称：</label>
												</div>
												<div class="col-xs-8">
													<input type="text" id="powerName" class="form-control input-sm" maxlength="10">
													<input type="hidden" id="powerId" class="form-control input-sm">
													<input type="hidden" id="type" class="form-control input-sm">
												</div>
											</div>
										</div>
										<div class="col-xs-4">
											<div class="form-group">
												<div class="col-xs-4">
													<label class="control-label">权限编码：</label>
												</div>
												<div class="col-xs-8">
													<input type="text" id="authorityCode" class="form-control input-sm" maxlength="10">

												</div>
											</div>
										</div>
										<div class="col-xs-4 text-right">
											<div class="m-ui-btn-box">
												<button class="btn btn-primary" id="queryP">
													<span class="glyphicon glyphicon-search" aria-hidden="true"
														style="margin-right: 10px;"></span>查询
												</button>
												<button class="btn btn-default m-ui-btn3"
													style="margin-left: 5px;" id="resetting">重置</button>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!--查询结束-->
							<div class="m-ui-title1">
								<h1>权限管理</h1>
								<div class="m-ui-caozuobox">
									<button class="btn btn-warning" id="addPower">
										<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
									</button>
									<button class="btn btn-success" id="modifyPower">
										<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>修改
									</button>
									<button class="btn btn-danger" id="deletePower">
										<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
									</button>
								</div>
							</div>
							<div class="m-ui-table">
								<table id="powerTable"
									class="table table-bordered table-hover m-ui-table-whole"
									cellspacing="0" width="100%">
								</table>
							</div>
							<!--数据列表结束-->
						</div>
					</div>
					
				</div>
			</div>
		</div>
</body>
<script type="text/javascript"
						src="<%=context%>/scripts/xtgl/authority/authority.js"></script>
</html>