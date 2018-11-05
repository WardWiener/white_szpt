<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript" src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
<%@ page import="com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser,
com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil,
java.util.List,java.util.Map,java.util.HashMap
"%> 
<%
MySecureUser user = SessionUserDetailsUtil.getMySecureUser() ;
Map<String, Object> userMap = user.getUserMap() ;
Map<String, String> mPerson = new HashMap<String, String>(0) ;
Map<String, String> mOrg = new HashMap<String, String>(0) ;
if(userMap.get("person")!=null){
	mPerson = (Map<String, String>)userMap.get("person");
}
if(userMap.get("org")!=null){
	mOrg = (Map<String, String>)userMap.get("org");
}
%>
<script type="text/javascript" src="<%=request.getContextPath()%>/custom/szpt/js/top.js"></script>
<script type="text/javascript">
var currentUserName = '<%=mPerson.get("name")%>';
var currentUserCode = '<%=mPerson.get("code")%>';
var currentUserId = '<%=mPerson.get("id")%>';
var currentUnitName = '<%=mOrg.get("shortName")%>';
var currentUnitCode = '<%=mOrg.get("code")%>';
var personCode = '<%=mPerson.get("code")%>';
	var caseId = "${param.queryStr}"; 
</script>
</head>

<body class="m-ui-layer-body">
	<div class="m-ui-layer-box" style="width:800px;">

<div class="m-ui-layer-content">

<div style="padding:10px;">
<div class="row row-mar">
<div class="col-xs-4">
   <div class="col-xs-4"> <label class="control-label">案件编号：</label></div>
    <div class="col-xs-7 input-group"><input type="text" id="ajbh" class="form-control input-sm"></div>
</div>
<div class="col-xs-4">
   <div class="col-xs-4"> <label class="control-label">案件名称：</label></div>
    <div class="col-xs-7"><input type="text" id="ajmc" class="form-control input-sm"></div>
</div>
<div class="col-xs-4">
   <div class="col-xs-4"> <label class="control-label">办案单位：</label></div>
	    <div class="col-xs-6 input-group">
			<input type="text" id="dwTree" class="form-control input-sm " readonly>
		</div>
	</div>
</div>

<div class="row row-mar">
<div class="col-xs-4">
   <div class="col-xs-4"> <label class="control-label">案件类别：</label></div>
    <div class="col-xs-7"><select id="aylb" class="form-control input-sm">
           
</select></div>
</div>
<div class="col-xs-4">
   <div class="col-xs-4"> <label class="control-label">办案民警：</label></div>
    <div class="col-xs-7"><input type="text" id="bamj" class="form-control input-sm"></div>
</div>
</div>


<div class="row row-mar">
<div id="dateRangeId_2" class="col-xs-8 dateRange">
      <div class="col-xs-2"> <label class="control-label">发案日期：</label></div>
      <input type="hidden" id="dtfmt" class="dateFmt" value="info@yyyy-MM-dd" />
		<div class="col-xs-4">
			<div class="input-group"
				style="margin-left: 10px; margin-right: 10px;">
				<input type="text" id="start" date-pos="top" class="laydate-start form-control input-sm" readonly="readonly">
					<span class="laydate-start-span input-group-addon m-ui-addon"><span
					class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
			</div>
		</div>
      <div class="col-xs-2 to">——</div>
      <div class="col-xs-4">
            <div class="input-group" style="padding-left: 5px;">
				<input type="text" id="end"	class="laydate-end form-control input-sm" readonly="readonly"><span
					class="laydate-end-span input-group-addon m-ui-addon"><span
					class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
			</div>
	</div>
</div>
<div class="col-xs-4 m-ui-btn-box text-center">
<button class="btn btn-primary btn-sm" id="findBtn">查询</button>
<button class="btn btn-default btn-sm" id="resBtn">重置</button>
</div>
</div>

</div>




	<table id="sonProjectTable" class="table table-bordered table-hover m-ui-table-no-paginate" cellspacing="0" width="100%">
        
    </table>


</div>
<!--内容end-->
<div class="m-ui-commitbox">
<button class="btn btn-primary btn-lg" id="saveSonProjectBtn">保存</button>
<button class="btn btn-default btn-lg" id="returnBtn">返回</button> 
</div>

</div>
	
	<script type="text/javascript"
		src="<%=context%>/script/wdza/findSonProject.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>

</body>
<style>
.diyTable-page {
	text-align: right;
}

.laypage_main a, .laypage_main span {
	color: #fff;
}

.laypageskin_default a {
	border: 0px;
	background-color: transparent;
}
</style>
</html>