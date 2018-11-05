<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
 <%  
      String constantStauts_qy = com.taiji.pubsec.szpt.xtgl.Constant.STATUS_START;
      String constantStauts_ty = com.taiji.pubsec.szpt.xtgl.Constant.STATUS_STOP;
      String constantStauts = com.taiji.pubsec.szpt.xtgl.Constant.STATUS;
 %>
 
<html>
<head>
<title>实战平台 – 系统管理</title>

<%@include file="/WEB-INF/base/basePart.jsp"%>
<script>
var constantStauts_qy = '<%=constantStauts_qy%>';
var constantStauts_ty = '<%=constantStauts_ty%>';
var constantStauts = '<%=constantStauts%>';
</script>
</head>

<body>
<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<div id="c-center">
		<%@include file="/WEB-INF/base/skin/leftPart-xtgl.jsp"%>
		<div id="c-center-right">
				<div id="c-center-right-content">
					<div id="c-center-right-content-block">


            <div id="c-center-right-content-con" class="alt">
							<ol class="breadcrumb m-ui-breadcrumb">
								<li>系统管理</li>
								<li>角色权限管理</li>
								<li>角色管理</li>
							</ol>
							<div class="m-ui-searchbox">
								<div class="m-ui-searchbox-con">
									<div class="row mar-top row-mar">
										<div class="col-xs-5">
											<div class="form-group">
												<div class="col-xs-4">
													<label class="control-label">角色名称：</label>
												</div>
												<div class="col-xs-8">
													<input type="text" id="roleName" class="form-control input-sm" maxlength="10">
												</div>
											</div>
										</div>
										<div class="col-xs-5">
											<div class="form-group">
												<div class="col-xs-4">
													<label class="control-label">状态：</label>
												</div>
												<div class="col-xs-8">
													<select id="statu" class="select2-noSearch">
														<option value="all">全部</option>
														
													</select>
												</div>
											</div>
										</div>
										<div class="col-xs-2">
											<div class="m-ui-btn-box text-right" style = "padding-right:15px">
												<button class="btn btn-primary " id="queryRole">
													<span class="glyphicon glyphicon-search mar-right" aria-hidden="true"></span>查询
												</button>
												<button class="btn btn-default" id="resetRole">重置
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="m-ui-title1 ">
								<h1>角色管理</h1>
								<div class="m-ui-caozuobox">
									<!--  <button class="btn btn-primary" id="copyRole"><span class="glyphicon glyphicon-duplicate" aria-hidden="true"></span>复制</button>-->
									<button class="btn btn-warning" id="addRole"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增</button>
									<button class="btn btn-primary" id="modifyRole"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span>修改</button>
									<button class="btn btn-success" id="startRole"><span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>启用</button>
									<button class="btn btn-danger" id="stopRole"><span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>停用</button>
									<button class="btn btn-danger" id="deleteRole"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</button>
								</div>
							</div>
								<div class="m-ui-table " style="margin: auto 10px auto 10px">
									<table id="roleTabulation"
										class="table table-bordered table-hover m-ui-table-whole"
										cellspacing="0" width="100%">
									</table>
								</div>
						</div>
					</div>
				</div>
				</div>
				</div>
</body>
<script type="text/javascript"
			src="<%=context%>/scripts/xtgl/role/role.js"></script>

</html>