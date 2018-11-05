<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台 – 系统管理</title>

<%@include file="/WEB-INF/base/basePart.jsp"%>
 <%  
      String constantResourceType = com.taiji.pubsec.szpt.xtgl.Constant.RESOURCE_TYPE; 
 %>
 <script>
var constantResourceType = '<%=constantResourceType%>';
</script>
<script>
$(document).ready(function() {	
	
	
});
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
								<li>资源管理</li>
							</ol>
							<div class="m-ui-searchbox">
								<div class="m-ui-searchbox-con">
									<div class="row row-mar mar-top">
										<div class="col-xs-5">
											<div class="form-group">
												<div class="col-xs-4">
													<label class="control-label">资源名称：</label>
												</div>
												<div class="col-xs-8">
													<input type="text" id="roleName" class="form-control input-sm" maxlength="10">
												</div>
											</div>
										</div>
										<div class="col-xs-5">
					                        <div class="form-group">
					                           <div class="col-xs-4">
					                              	<label class="control-label">资源类型：<span class="red-star">*</span></label>
					                           </div>
					                      <div class="col-xs-8">
						                    <select id="pa_newPage_type" class="form-control select2-noSearch">
						                      <option value="all">全部</option>
						                    </select>
					                      </div>
				                        </div>
			                          </div>
										<div class="col-xs-2 ">
											<div class="m-ui-btn-box text-right ">
												<button class="btn btn-primary" id="queryRole">
													<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
												</button>
												<button class="btn btn-default" id="resetRole">重置
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="m-ui-title1">
								<h1>资源管理</h1>
								<div class="m-ui-caozuobox">
									<button class="btn btn-warning" id="addRole"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增</button>
									<button class="btn btn-primary" id="modifyRole"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span>修改</button>
									<button class="btn btn-danger" id="deleteRole"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</button>
								</div>
							</div>
								<div class="m-ui-table" style="margin: auto 10px auto 10px">
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
			src="<%=context%>/scripts/xtgl/resource/resource.js"></script>

</html>