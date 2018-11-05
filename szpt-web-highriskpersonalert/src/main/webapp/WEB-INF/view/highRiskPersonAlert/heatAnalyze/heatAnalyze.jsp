<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/highRiskPersonAlert/common/arcgisMapCommon.js"></script>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/highRiskPersonAlert/common/constant.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<div id="c-center">
		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-highRiskPersonAlert.jsp"%>
		</div>
		<div id="c-center-right">
			<div id="c-center-right-content">
				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">高危人群分析</a></li>
					<li class="active"><a href="#">热点分析</a></li>
				</ol>
				<div id="dateRange" class="row row-mar dateRange">
					<div class="col-xs-2">
						<label class="control-label">起止时间：</label>
					</div>
					<input type="hidden" id="dtfmt" class="dateFmt" value="info@yyyy-MM-dd HH:mm:ss" />
					<div class="col-xs-2 input-group">
						<div class="input-group" style="margin-right:10px;">
							<input type="text" id="fixed_start" class="laydate-start form-control input-sm" readonly="readonly">
							<span class="laydate-start-span input-group-addon m-ui-addon">
								<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
							</span>
						</div>
					</div>
					<div class="col-xs-1" style="width: 20px; text-align: center">-</div>
					<div class="col-xs-2 input-group">
						<div class="input-group" style="padding-left:5px;">
							<input type="text" id="fixed_end" class="laydate-end form-control input-sm" readonly="readonly">
							<span class="laydate-end-span input-group-addon m-ui-addon">
								<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
							</span>
						</div>
					</div>

					<div class="col-xs-2">
						<label class="control-label">人员类型：</label>
					</div>
					<div class="col-xs-2">
						<input id="criminalRecordName" readonly type="text" class="form-control input-sm">
					</div>
				</div>
				<div class="row">
			         <div  class="col-xs-2 text-right" style="padding-top:5px;"><input type="checkbox" class=" icheckbox" id="Jqcheck"name="Jqcheck">&nbsp;&nbsp;警情类型：&nbsp;&nbsp;</div>
			          <div class="col-xs-2">
						<input id="dimJqlxId" readonly type="text" class="ztree-menu-input form-control input-sm">
					</div>  
					<div class="col-xs-4" style="display:none;">
					<div  class="col-xs-6 text-right" style="padding-top:5px; width:128px;"><input type="checkbox" class=" icheckbox" id="Ajcheck"name="Ajcheck">&nbsp;&nbsp;案件类别：&nbsp;&nbsp;</div>
			          <div class="col-xs-6">
						<input id="ajlb" readonly type="text" class="ztree-menu-input form-control input-sm">
					</div> 
					</div>
					<div class="col-xs-1"></div>
			          <div class="col-xs-2">
			            <button id="search"class="btn btn-primary btn-sm">查询</button>
			            <button id="reset"class="btn btn-default btn-sm" style="margin-left:2px;">重置</button>
			          </div>     
			</div>
				<div class="m-ui-title1">
					<h1>
						<button id="backViewBtn" class="btn btn-primary"
							style="margin-right: 20px;">
							<span class="glyphicon glyphicon-menu-left"></span>返回
						</button>
						热点分析
					</h1>
				</div>
				<div class="row"><div id="locusHotMapConten" style="height: 510px;"></div></div>
			</div>
		</div>
	</div>
	<div id="menuContent" class="ztree-MenuContent">
		<ul id="ztree-criminalRecord" class="ztree" style="width:200px; height: 150px;"></ul>
	</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>

<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/heatAnalyze/heatAnalyze.js"></script>
<script type="text/javascript" 
	src="<%=context%>/scripts/highRiskPersonAlert/heatAnalyze/zTreeMenu.js"></script>
<script type="text/javascript">
	var personType = "<%=request.getAttribute("personType")%>";
	var url = "<%=request.getParameter("url")%>";
	var personTypeName = "<%=request.getAttribute("propleTypeName")%>";
</script>
</html>