<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title>全文检索 – 警情</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/chosen.css" />
<link rel="stylesheet"
	href="<%=context%>/custom/szpt/style/zTreeMenu.css" />
<%@include file="/WEB-INF/base/basePart.jsp"%>
<script type="text/javascript"
	src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/szpt/util/noDataInit.js">
	
</script>

</head>
<body class="search-bg">
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
		<div class="search-result-box" style="margin-top:65px;">
			<ol class="breadcrumb m-ui-breadcrumb">
  				<li id="back"><a href="###" >搜索首页</a></li>
  				<li>警情&nbsp;&nbsp;<span class="color-red2" id="inputResult"></span>&nbsp;&nbsp;的搜索结果</li>
			</ol>
			<div class="m-ui-searchbox" >
					<div class="m-ui-searchbox-con">
						<div class="row row-mar">
							<div class="col-xs-3">
									<div class="col-xs-6"> <label class="control-label">关键字：</label></div>
									<div class="col-xs-6"><input id="key" type="text" class="form-control input-sm"></div>
							</div>
							<div class="col-xs-3">
								<div class="col-xs-6"> <label class="control-label">警情类别：</label></div>
						        <div class="col-xs-6">
									<input id="dimJqlxId" readonly type="text" class="ztree-menu-input form-control input-sm">
								</div>  
							</div>
							<!-- <div class="col-xs-8">
								<div id="alertsituationDate" class="col-xs-6 dateRange">
								<div class="col-xs-3">
									<label class="control-label" style="line-height:0px">接警时间：</label>
								</div>
								<input type="hidden" id="dtfmt1" class="dateFmt"
											value="info@yyyy-MM-dd HH:mm:ss" />
						      	<div class="col-xs-4 input-group">
								<input type="text" id="alertTime_start" date-pos="bottom"  
										class="laydate-start form-control input-sm valiform-keyup form-val" datatype="*1-50" nullmsg="请选择开始时间"
										readonly="readonly"> <span
										class="laydate-start-span input-group-addon m-ui-addon">
										<span class="glyphicon glyphicon-calendar"
										aria-hidden="true"></span>
									</span>
								  </div>
							      <div class="col-xs-1"><label class="control-label" style="text-align: center;line-height:0px">至</label></div>
							      <div class="col-xs-4 input-group">
									 <input type="text" id="alertTime_end" 
											class="laydate-end form-control input-sm valiform-keyup form-val" datatype="*1-50" nullmsg="请选择开始时间"
											readonly="readonly"> <span
											class="laydate-end-span input-group-addon m-ui-addon">
											<span class="glyphicon glyphicon-calendar"
											aria-hidden="true"></span>
										</span>
								 </div>
							</div> 
							</div> -->
						</div> 
						<br>
						<div class="row">
							<div id="messageDate" class="col-xs-6 dateRange">
								<div class="col-xs-3">
									<label class="control-label" style="line-height:0px">发生时间：</label>
								</div>
								<div class="col-xs-2">
									<div style="margin-right: 10px">
										<select class="form-control input-sm" id="time">
											<option value="today">今日</option>
											<option value="week">本周</option>
											<option value="month">本月</option>
											<option value="zdy">自定义</option>
										</select>
									</div>
								</div>
								<input type="hidden" id="dtfmt" class="dateFmt"
											value="info@yyyy-MM-dd HH:mm:ss" />
						      	<div class="col-xs-3 input-group">
								<input type="text" id="time_start" date-pos="bottom"
										class="laydate-start form-control input-sm valiform-keyup form-val" datatype="*1-50" nullmsg="请选择开始时间"
										readonly="readonly"> <span
										class="laydate-start-span input-group-addon m-ui-addon">
										<span class="glyphicon glyphicon-calendar"
										aria-hidden="true"></span>
									</span>
								  </div>
							      <div class="col-xs-1"><label class="control-label" style="text-align: center;line-height:0px">至</label></div>
							      <div class="col-xs-3 input-group">
									 <input type="text" id="time_end"
											class="laydate-end form-control input-sm valiform-keyup form-val" datatype="*1-50" nullmsg="请选择开始时间"
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
				<table id="eventTable"
					class="table table-bordered table-hover m-ui-table-whole"
					cellspacing="0" width="100%">
					 
				</table>
			</div>
			</div>
	
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<script type="text/javascript">
	var title = "${param.title}";
	var startTime = "${param.startTime}";
	var endTime = "${param.endTime}";
</script>
<script type="text/javascript"
	src="<%=context%>/scripts/eventMore/eventMore.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/dateOperation.js"></script>
</html>
