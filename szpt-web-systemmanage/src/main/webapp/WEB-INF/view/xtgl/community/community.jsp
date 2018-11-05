<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>系统管理-社区信息维护</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<script>
var serverName = '<%=request.getServerName()%>';
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
							<li>基础数据管理</li>
							<li>社区信息维护</li>
						</ol>
						<div class="m-ui-searchbox">
							<div class="m-ui-searchbox-con">
								<div class="row" style="margin-bottom: 10px">
									<div class="col-xs-5">
										<div class="form-group">
											<div class="col-xs-4">
												<label class="control-label">社区名称：</label>
											</div>
											<div class="col-xs-8">
												<input type="text" id="communityName" class="form-control input-sm">
											</div>
										</div>
									</div>

									<div class="col-xs-5">
										<div class="form-group">
											<div class="col-xs-4">
												<label class="control-label">所属单位：</label>
											</div>
											<div class="col-xs-7">
												<div class="input-group ">
													<input class="form-control unit " id="unitName" readonly> 
													<input type="hidden" id="unitId"> 
													<span class="input-group-addon m-ui-addon unit"> 
													<span class="glyphicon glyphicon-search" aria-hidden="true" style="font-size: 16px;"> </span>
													</span>
												</div>
											</div>
										</div>
									</div>
									<div class="col-xs-2">
										<button id="query" class="btn btn-primary btn-sm"
											style="margin-left: 50px;">查询</button>
										<button id="reset" class="btn btn-default btn-sm"
											style="margin-left: 10px;">重置</button>
									</div>
								</div>

							</div>
						</div>
						<div class="m-ui-title1">
							<h1>社区管理</h1>
							<div class="m-ui-caozuobox">
								<button class="btn btn-primary" id="addCommunity">
									<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
								</button>
							</div>
						</div>

						<div class="m-ui-table" style="margin-top: 35px">
							<table id="communityTable"
								class="table table-bordered table-hover m-ui-table-whole"
								cellspacing="0" width="100%">

							</table>
						</div>
						<!--数据列表结束-->
					</div>
				</div>
			</div>

		</div>
	<div id="menuContent" class="ztree-MenuContent">
		<input type="text" id="keySelect" style="margin-bottom: 5px;" class="form-control input-sm" />
		<ul id="ztree-unitSelect" class="ztree" style="width: 230px; height: 300px;"></ul>
	</div>

	</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
	<script type="text/javascript"
		src="<%=context%>/scripts/xtgl/community/community.js"></script>
</body>


</html>