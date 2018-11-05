<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript" src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
</head>

<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>

	<div id="c-center">

		<div class="fixed-a">
			<%@include file="/WEB-INF/custom/szpt/skin/left-ptjc.jsp"%>
		</div>
		<input id="paraId" type="hidden"
			value="<%=request.getParameter("id")%>">
		<div id="c-center-right">
			<div id="c-center-right-content">

				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">平台基础</a></li>
					<li><a href="#">警情分布规则设置</a></li>
				</ol>



				<!--整体查询板块--begin-->
					<div class="m-ui-searchbox">
						<div class="m-ui-searchbox-con">

							<div class="row row-mar">
								<div class="col-xs-3">
									<div class="col-xs-6">
										<label class="control-label">警情类别：</label>
									</div>
									<div class="col-xs-6 input-group">
										<input id="jqlx" type="text" class="form-control input-sm" readonly> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-search"></span></span>
									</div>
								</div>
								<div class="col-xs-3">
									<div class="col-xs-6">
										<label class="control-label">统计对象类型：</label>
									</div>
									<div class="col-xs-6">
										<select id="tjdx" class="form-control input-sm select2-noSearch">
										</select>
									</div>
								</div>
								<div class="col-xs-3">
									<button id="search" class="btn btn-primary btn-sm">生成规则表</button>
								</div>
							</div>
						</div>
					</div>
				<!--查询结束-->

				<div class="m-ui-title1">
					<h1>警情分布规则设置</h1>
				</div>

				<div id="myvf" class="validform m-ui-table">
					<table id="table" class="table table-bordered table-hover m-ui-table-whole"
						cellspacing="0" width="100%">
					</table>
				</div>
				<div class="m-ui-commitbox">
					<button style="display:none" id="modify" class="btn btn-primary  btn-lg">修改</button>
					<button style="display:none" id="saveBtn" class="btn btn-primary  btn-lg">提交</button>
					<button style="display:none" id="cancel" class="btn btn-default  btn-lg">取消</button>
				</div>
				<!--结束-->
			</div>
		</div>

	</div>
	<script type="text/javascript"
		src="<%=context%>/scripts/gzgl/distributionRule.js"></script>

	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>

</html>