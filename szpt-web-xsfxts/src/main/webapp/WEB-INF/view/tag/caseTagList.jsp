<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript"
	src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/szpt/util/noDataInit.js"></script>
</head>

<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	
	<div id="c-center">

		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-xsfxts.jsp"%>
		</div>

		<div id="c-center-right">

			<div id="c-center-right-content">

				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">刑事案件分析</a></li>
					<li><a href="#">刑事案件分析打标</a></li>
				</ol>

				<!--整体查询板块--begin-->
				<div class="basic-query-out">
					<div class="basic-query">
						<input id="mohu" type="text" class="form-control input-sm"
							value="案件名称模糊查询"
							onBlur="if(!value){value=defaultValue;this.style.color='#b1b8c2'}"
							onFocus="if(value==defaultValue){value='';this.style.color='#fff'}"
							style="color: #b1b8c2;">
						<button class="search btn btn-primary btn-sm">查询</button>
						<button class="advanced-btn">展开高级查询</button>
					</div>
				</div>


				<div class="m-ui-searchbox  advanced-query">
					<div class="m-ui-searchbox-con">

						<div class="row row-mar">
							<div class="col-xs-3">
								<div class="col-xs-4">
									<label class="control-label">案件编号：</label>
								</div>
								<div class="col-xs-8">
									<input type="text" id="code" class="form-control input-sm">
								</div>
							</div>
							<div class="col-xs-3">
								<div class="col-xs-4">
									<label class="control-label">案件名称：</label>
								</div>
								<div class="col-xs-8">
									<input type="text" id="name" class="form-control input-sm">
								</div>
							</div>
							<div class="col-xs-3">
								<div class="col-xs-4">
									<label class="control-label">发案地点：</label>
								</div>
								<div class="col-xs-8">
									<input type="text" id="address" class="form-control input-sm">
								</div>
							</div>
							<div class="col-xs-3">
								<div class="col-xs-4">
									<label class="control-label">案件类别：</label>
								</div>
								<div class="col-xs-8">
									<input type="text" id="ajlbTree" class="form-control input-sm" readonly>
								</div>
							</div>
						</div>


						<div class="row row-mar">

							<div class="col-xs-3">
								<div class="col-xs-4">
									<label class="control-label">案件性质：</label>
								</div>
								<div class="col-xs-8">
									<input type="text" id="ajxzTree" class="form-control input-sm" readonly>
								</div>
							</div>
							<div class="col-xs-3">
								<div class="col-xs-4">
									<label class="control-label">发案辖区：</label>
								</div>
								<div class="col-xs-8">
									<select id="pcsSelect"
										class="form-control input-sm select2-noSearch allowClear">
									</select>
								</div>
							</div>
							<div class="col-xs-3">
								<div class="col-xs-4">
									<label class="control-label">发案社区：</label>
								</div>
								<div class="col-xs-8">
									<select id="countrySelect" class="form-control input-sm select2 allowClear">
									
									</select>
								</div>
							</div>
							<div class="col-xs-3">
								<div class="col-xs-4">
									<label class="control-label">案件状态：</label>
								</div>
								<div class="col-xs-8">
									<select id="caseState" class="form-control input-sm select2-noSearch allowClear">
										
									</select>
								</div>
							</div>
						</div>

						<div class="row row-mar">
							<div id="dateRange" class="col-xs-6 dateRange">
								<div class="col-xs-2">
									<label class="control-label">发现时间：</label>
								</div>
								<input type="hidden" id="dtfmt" class="dateFmt"
									value="yyyy-MM-dd" />
								<div class="col-xs-4 input-group">
									<div class="input-group">
										<input type="text" id="fixed_start"
											class="laydate-start form-control input-sm"
											readonly="readonly"> <span
											class="laydate-start-span input-group-addon m-ui-addon">
											<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
										</span>
									</div>
								</div>
								<div class="col-xs-2 to"">——</div>
								<div class="col-xs-4 input-group">
									<div class="input-group">
										<input type="text" id="fixed_end"
											class="laydate-end form-control input-sm" readonly="readonly">
										<span class="laydate-end-span input-group-addon m-ui-addon">
											<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
										</span>
									</div>
								</div>
							</div>
							<div class="col-xs-3">
								<div class="col-xs-4">
									<label class="control-label">打标状态：</label>
								</div>
								<div class="col-xs-8">
									<select id="tagState" class="form-control input-sm select2-noSearch allowClear">
										
									</select>
								</div>
							</div>
							<div class="col-xs-3">
								<button class="search btn btn-primary btn-sm">查询</button>
								<button id="reset" class="btn btn-default btn-sm">重置</button>
							</div>
						</div>


					</div>
				</div>
				<!--查询结束-->


				<div class="advanced-btn-box">
					<button class="advanced-btn-up">收起高级查询</button>
				</div>

				<!--整体查询板块--end-->
				<!--悬浮操作层-->
				<div class="fixed-a">
					<div class="m-ui-title1">
						<h1>刑事案件分析打标</h1>
					</div>
				</div>
				<!--悬浮操作层-->

				<div class="m-ui-table">
					<table id="table"
						class="table table-bordered table-hover m-ui-table-whole"
						cellspacing="0" width="100%">
					</table>

				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="<%=context%>/scripts/tag/caseTagList.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>

</html>