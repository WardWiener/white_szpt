<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title>全文检索 – 指令</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript" src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
</head>
<body class="search-bg">
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
		<div class="search-result-box" style="margin-top:65px;">
			<ol class="breadcrumb m-ui-breadcrumb">
  				<li id="back"><a href="###" >搜索首页</a></li>
  				<li>指令&nbsp;&nbsp;<span class="color-red2" id="inputResult"></span>&nbsp;&nbsp;的搜索结果</li>
			</ol>

				<div class="m-ui-searchbox">
					<div class="m-ui-searchbox-con">

						<div class="row row-mar">
							<div class="col-xs-3">
								<div class="col-xs-6">
									<label class="control-label" style="line-height:0px">指令内容：</label>
								</div>
								<div class="col-xs-6" >
									<input type="text" class="form-control input-sm" id="content">
								</div>
							</div>
							<div id="createDate" class="col-xs-5 dateRange">
								<div class="col-xs-3">
									<label class="control-label" style="line-height:0px">创建时间：</label>
								</div>
								<input type="hidden" id="create_dtfmt" class="dateFmt" name="startTime"
											value="info@yyyy-MM-dd HH:mm:ss" />
						      	<div class="col-xs-3 input-group">
								<input type="text" id="create_start" date-pos="bottom" name="endTime"
										class="laydate-start form-control input-sm" 
										readonly="readonly"> <span
										class="laydate-start-span input-group-addon m-ui-addon">
										<span class="glyphicon glyphicon-calendar"
										aria-hidden="true"></span>
									</span>
								  </div>
							      <div class="col-xs-1"><label class="control-label" style="text-align: center;line-height:0px">至</label></div>
							      <div class="col-xs-3 input-group">
									 <input type="text" id="create_end"
											class="laydate-end form-control input-sm" 
											readonly="readonly"> <span
											class="laydate-end-span input-group-addon m-ui-addon">
											<span class="glyphicon glyphicon-calendar"
											aria-hidden="true"></span>
										</span>
								 </div>
							</div>

							<div class="col-xs-4">
								<div class="col-xs-4">
									<label class="control-label" style="line-height:0px">指令类型：</label>
								</div>
								<div class="col-xs-4">
									<select class="form-control input-sm" id="commandType">
										<option value="">全部</option>
										<option selected>打击指令</option>
										<option>防控指令</option>
										<option>管控指令</option>
										<option>研判指令</option>
										<option>落地指令</option>
										<option>情报核实指令</option>
										<option>研判结果推送指令</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row row-mar">
							<div class="col-xs-3">
							<!-- 
								<div class="col-xs-6">
									<label class="control-label" style="line-height:0px">接收单位：</label>
								</div>
								<div class="col-xs-6 input-group">
									<input type="text" class="form-control input-sm" id="unit"> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-search"></span></span>
								</div>
								-->
								<div class="col-xs-6"> <label class="control-label">接收单位：</label></div>
							    <div class="col-xs-6 input-group">
									<input type="text" id="unitTree" class="form-control input-sm " readonly>
								</div>
							</div>
							<div id="requireDate" class="col-xs-5 dateRange">
								<div class="col-xs-3">
									<label class="control-label" style="line-height:0px">要求反馈时间：</label>
								</div>
								<input type="hidden" id="dtfmt" class="dateFmt"
											value="info@yyyy-MM-dd HH:mm:ss" />
						      	<div class="col-xs-3 input-group">
								<input type="text" id="time_start" date-pos="bottom"
										class="laydate-start form-control input-sm" 
										readonly="readonly"> <span
										class="laydate-start-span input-group-addon m-ui-addon">
										<span class="glyphicon glyphicon-calendar"
										aria-hidden="true"></span>
									</span>
								  </div>
							      <div class="col-xs-1"><label class="control-label" style="text-align: center;line-height:0px">至</label></div>
							      <div class="col-xs-3 input-group">
									 <input type="text" id="time_end"
											class="laydate-end form-control input-sm" 
											readonly="readonly"> <span
											class="laydate-end-span input-group-addon m-ui-addon">
											<span class="glyphicon glyphicon-calendar"
											aria-hidden="true"></span>
										</span>
								 </div>
							</div>
							<div class="col-xs-2 text-right">
								<button class="btn btn-primary btn-sm" id="query">查询</button>
								<button class="btn btn-default btn-sm" id="reset">重置</button>
							</div>
						</div>
						



					</div>
				</div>
				<div class="row row-mar" style="margin-top: 30px">
					<table id="commandTable"
						class="table table-bordered table-hover m-ui-table-whole"
						cellspacing="0" width="100%">
						 
					</table>
				</div>
			</div>
	<div id="menuContent" class="ztree-MenuContent">
		<input type="text" id="keySelect" style="margin-bottom:5px;display:none;" class="form-control input-sm" />
		<ul id="ztree-unitSelect" class="ztree" style="width:200px; height: 150px;"></ul>
	</div>
</body>
<script type="text/javascript">
	var title = "${param.title}";
	var startTime = "${param.startTime}";
	var endTime = "${param.endTime}";
</script>
<script type="text/javascript"
	src="<%=context%>/scripts/commandMore/commandMore.js"></script>
	<script type="text/javascript"
	src="<%=context%>/scripts/util/util.js"></script>
</html>
