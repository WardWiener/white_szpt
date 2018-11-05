<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
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
<div class="row" style=" margin:0;background:#f8f8f8;">

<!-------左侧-------->

<!-------左侧-------->   
     
 <!-------右侧-------->       
<div class="col-xs-10" style="width:100%">
         <div style="padding:10px;border-left:1px solid #ddd; margin-left:-1px; background:#fff; min-height:300px;">
         
 <!-------右侧内容begin--------> 
<div class="m-ui-title4 mar-top"> <h2>维护专案涉案人员信息</h2></div>
<div class="text-right">
<button class="btn btn-xs btn-primary" id="addBtn">增行</button>
<button class="btn btn-xs btn-danger" id="remBtn">删除</button>
</div>
<div class="validform" id="involvedYZ">
<table id="table" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0">
        
    </table>
</div>
<div class="m-ui-commitbox">
<button class="btn btn-primary btn-lg" id="saveBtn">保存</button>
<button class="btn btn-default btn-lg" id="resBtn" >取消</button> 
</div>

 <!-------右侧内容end--------> 
 

 
</div>
</div>
</div>  
       
<!--row-end-->        
<!--内容end-->


</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
<script type="text/javascript" src="<%=context%>/script/wdza/UpdataInvolvedPerson.js"></script>
</body>
<style>
		.diyTable-page{
			text-align: right;
		}
		.laypage_main a, .laypage_main span {
			color:#fff;
		}
		.laypageskin_default a{
			border: 0px;
		    background-color: transparent;
		}
</style>
</html>