<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台–指令管理</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/instruction/common/constant.jsp"%>
<%-- <%@include file="/common/library/echarts/echarts-base-import.jsp"%> --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript" src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
</head>
<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<div id="c-center">
		<%@include file="/WEB-INF/base/skin/leftPart-instruction.jsp"%>
		
<div id="c-center-right">

<div id="c-center-right-content">

<ol class="breadcrumb m-ui-breadcrumb">
  <li><a href="#">首页</a></li>
  <li><a href="#">指令管理</a></li>
</ol>





<!--整体查询板块--begin-->
<div class="basic-query-out">
<div class="basic-query"><input id="content2"type="text" class="form-control input-sm" value="内容模糊查询" onBlur="if(!value){value=defaultValue;this.style.color='#b1b8c2'}"  onFocus="if(value==defaultValue){value='';this.style.color='#fff'}" style="color:#b1b8c2;"><button class="btn btn-primary btn-sm search">查询</button><button class="advanced-btn">展开高级查询</button></div>
</div>


<div class="m-ui-searchbox  advanced-query">
<div class="m-ui-searchbox-con">

<div class="row row-mar">
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">指令内容：</label></div>
    <div class="col-xs-6"><input id="content" type="text" class="form-control input-sm"></div>
</div>


<div id="createTime" class="col-xs-6 dateRange">
									<input type="hidden" id="dtfmt" class="dateFmt"
										value="yyyy-MM-dd HH:ss" />
									<div class="col-xs-3">
										<label class="control-label">创建时间：</label>
									</div>
									<div class="col-xs-3 input-group">
										<input type="text" id="fixed_start" date-pos="bottom"
												class="laydate-start form-control input-sm"
												readonly="readonly"> <span
												class="laydate-start-span input-group-addon m-ui-addon">
												<span class="glyphicon glyphicon-calendar"
												aria-hidden="true"></span>
											</span>
									</div>
									<div class="col-xs-3 to">――</div>
									<div class="col-xs-3 input-group">
										<input type="text" id="fixed_end"
												class="laydate-end form-control input-sm"
												readonly="readonly"> <span
												class="laydate-end-span input-group-addon m-ui-addon">
												<span class="glyphicon glyphicon-calendar"
												aria-hidden="true"></span>
											</span>
									</div>
								</div>


<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">指令类型：</label></div>
    <div class="col-xs-6"><select id="type"class="form-control input-sm select2-noSearch allowClear">
    <option value="">全部</option>
    </select></div>
</div>
</div>
<div class="row row-mar">
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">接收单位：</label></div>
   <div class="col-xs-6 input-group">
		<input type="text" id="unitTree" class="form-control input-sm " readonly>
	</div>
</div>
<div id="backTime" class="col-xs-6 dateRange">
									<input type="hidden" id="dtfmt" class="dateFmt"
										value="yyyy-MM-dd HH:ss" />
									<div class="col-xs-3">
										<label class="control-label">要求反馈时间：</label>
									</div>
									<div class="col-xs-3 input-group">
										<input type="text" id="fixed_start" date-pos="bottom"
												class="laydate-start form-control input-sm"
												readonly="readonly"> <span
												class="laydate-start-span input-group-addon m-ui-addon">
												<span class="glyphicon glyphicon-calendar"
												aria-hidden="true"></span>
											</span>
									</div>
									<div class="col-xs-3 to">――</div>
									<div class="col-xs-3 input-group">
										<input type="text" id="fixed_end"
												class="laydate-end form-control input-sm"
												readonly="readonly"> <span
												class="laydate-end-span input-group-addon m-ui-addon">
												<span class="glyphicon glyphicon-calendar"
												aria-hidden="true"></span>
											</span>
									</div>
								</div>
<div class="col-xs-3">  
<button class="btn btn-primary btn-sm search">查询</button>
<button class="btn btn-default btn-sm" id="reset">重置</button>    
</div>
</div>




</div>
</div>
<!--查询结束-->


<div class="advanced-btn-box"><button class="advanced-btn-up">收起高级查询</button></div>

<!--整体查询板块--end-->





<!--悬浮操作层-->
<div class="fixed-a">
<div class="m-ui-title1"><h1>指令管理</h1><div class="m-ui-caozuobox"><button id="add"class="btn btn-primary" style="width:100px">新增指令</button></div>
</div>
</div>
<!--悬浮操作层-->

<div class="m-ui-table" style="margin-top:40px;">
                  <table id="instructionTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
                  </table>
                  
                  
              </div>



<!--结束-->


</div>
</div>
	</div>
	<div id="menuContent" class="ztree-MenuContent">
		<input type="text" id="keySelect" style="margin-bottom:5px;display:none;" class="form-control input-sm" />
		<ul id="ztree-unitSelect" class="ztree" style="width:200px; height: 150px;"></ul>
	</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/instruction/instructionList.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/util/util.js"></script>
</html>