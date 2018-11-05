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
			<%@include file="/WEB-INF/base/skin/leftPart-xsfxts.jsp"%>
		</div>
		<div id="c-center-right">

			<div id="c-center-right-content">

				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">刑事案件分析</a></li>
					<li><a href="#">两抢一盗案件智能串并案分析</a></li>
					<li><a href="#">两抢一盗案件串并案分析结果查询</a></li>
				</ol>

				<!--整体查询板块--begin-->
				<div class="basic-query-out">
					<div class="basic-query">
						<input id="fuzzyQuery" type="text" class="form-control input-sm" value="案件编号模糊查询"
							onBlur="if(!value){value=defaultValue;this.style.color='#b1b8c2'}"
							onFocus="if(value==defaultValue){value='';this.style.color='#fff'}"
							style="color: #b1b8c2;">
						<button class="query btn btn-primary btn-sm">查询</button>
						<button class="advanced-btn">展开高级查询</button>
					</div>
				</div>


					<div class="m-ui-searchbox  advanced-query">
						<div class="m-ui-searchbox-con">

							<div class="row row-mar">
								<div class="col-xs-3">
									<div class="col-xs-6">
										<label class="control-label">案件编号：</label>
									</div>
									<div class="col-xs-6">
										<input type="text" id="ajcode" class="form-control input-sm">
									</div>
								</div>
								<div class="col-xs-3">
									<div class="col-xs-6">
										<label class="control-label">案件类别：</label>
									</div>
									<div class="col-xs-6">
										<select id="ajlbSelect"
											class="form-control input-sm select2-noSearch allowClear">
										</select>
									</div>
								</div>
								<div class="col-xs-3">
									<div class="col-xs-6">
										<label class="control-label">案件性质：</label>
									</div>
									<div class="col-xs-6 ">
										<select id="caseNature" name="caseNature" multiple="multiple"
											class="form-control input-sm select2-multiple   allowClear form-val">

										</select>
									</div>
								</div>
								<div class="col-xs-3">
									<div class="col-xs-6">
										<label class="control-label">二级案件性质：</label>
									</div>
									<div class="col-xs-6 ">
										<select id="ejcaseNature" name="ejcaseNature" multiple="multiple"
											class="form-control input-sm select2-multiple  allowClear form-val">
										</select>
									</div>
								</div>


							</div>


							<div class="row row-mar">

								<div class="col-xs-3">
									<div class="col-xs-6">
										<label class="control-label">作案特点：</label>
									</div>
									<div class="col-xs-6 ">
									<select id="zatdSelect" name="zatedian" multiple="multiple"
											class="form-control input-sm select2-multiple  allowClear form-val">
										</select>
									</div>
								</div>

								<div class="col-xs-3">
									<div class="col-xs-6">
										<label class="control-label">选择处所：</label>
									</div>
									<div class="col-xs-6 ">
										<select id="choosecs"
											class="form-control input-sm select2-noSearch allowClear">
										</select>
									</div>
								</div>
								<div class="col-xs-3">
									<div class="col-xs-6">
										<label class="control-label">作案时段：</label>
									</div>
									<div class="col-xs-6">
											<select id="zasdSelect"
											class="form-control input-sm select2-noSearch allowClear">
										</select>
									</div>
								</div>
								<div class="col-xs-3">
									<div class="col-xs-6">
										<label class="control-label">作案人数：</label>
									</div>
									<div class="col-xs-6">
										<select id="zarsSelect"
											class="form-control input-sm select2-noSearch allowClear">
										</select>
									</div>
								</div>
							</div>

							<div class="row row-mar">
								<div class="col-xs-3">
									<div class="col-xs-6">
										<label class="control-label">作案进口：</label>
									</div>
									<div class="col-xs-6">
										<select id="zajkSelect"
											class="form-control input-sm select2-noSearch allowClear">
										</select>
									</div>
								</div>
								<div class="col-xs-3">
									<div class="col-xs-6">
										<label class="control-label">作案出口：</label>
									</div>
									<div class="col-xs-6">
										<select id="zackSelect"
											class="form-control input-sm select2-noSearch allowClear">
										</select>
									</div>
								</div>
								<div class="col-xs-3">
									<div class="col-xs-6">
										<label class="control-label">发案社区：</label>
									</div>
									<div class="col-xs-6">
										<input type="text" id="countryTree" class="form-control input-sm" readonly>
									</div>
								</div>
								<div class="col-xs-3">
									<button class="query btn btn-primary btn-sm">查询</button>
									<button class="search btn btn-default btn-sm">重置</button>
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
						<h1>两抢一盗案件串并案分析结果查询</h1>
						<div class="m-ui-caozuobox" >
							<button id="comparison" class="btn btn-primary">比对</button>
							<button id="cbaAnalyse" class="btn btn-success">串并案分析</button>
							<button  id="fxjgExcel" class="btn btn-primary">EXCEL导出</button>
							<button class="query btn btn-primary">
								<span class=" glyphicon glyphicon-refresh"></span>刷新
							</button>
						</div>
					</div>
				</div>
				<!--悬浮操作层-->
				<div style="width: 100%; overflow: auto;">
					<!-- <div class="m-ui-table" style="width: 2200px"> -->
						<table id="analyseResult" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
						
						</table>
					<!-- </div> -->
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=context%>/scripts/score/lookCaseTagList.js">
	</script>
</body>
</html>