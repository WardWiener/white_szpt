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
<%-- <%@include
	file="/common/library/iweboffice2000/iweboffice2000-import.jsp"%> --%>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/custom/szpt/js/top.js"></script>
<script type="text/javascript">
var currentUserName = '<%=mPerson.get("name")%>';
var currentUserCode = '<%=mPerson.get("code")%>';
var currentUserId = '<%=mPerson.get("id")%>';
var currentUnitName = '<%=mOrg.get("shortName")%>';
var currentUnitCode = '<%=mOrg.get("code")%>';
var personCode = '<%=mPerson.get("code")%>';


/* var iWebOffice2000_clsid = '23739A7E-2000-4D1C-88D5-D50B18F7C347' ;
var iWebOffice2000_codebase = 'iWebOffice2000.ocx#version=7,2,6,0' ;
var iWebOffice2000_progID = 'iWebOffice2000.iWebOffice' ;
var iWebOffice2000_webUrl = '/iweboffice2000/IWebOffice2000Servlet' ;
var iWebOffice2000_templateDocUrl = '/common/library/iweboffice2000/template/blank/' ;
var iWebOffice2000_createTemplateFileUrl = '/iweboffice2000/createTemplateFile.action' ; */

</script>

</head>

<body class="m-ui-layer-body">
		<div class="m-ui-layer-box" style="width:800px;">
<div class="row" style=" margin:0;background:#f8f8f8;">

<!-------左侧-------->
<div class="col-xs-2" style="width:18%">
<div style="padding:10px; border-right:1px solid #ddd;">
<p class="row-mar"> 专案信息：</p>
 <div id="zaInfo" class="list-group">
          <a href="#" id="basicA" class="list-group-item list-group-item-inf">案件基本资料</a>
          <a href="#" id="personA" class="list-group-item">专案涉案人员</a>
          <a href="#" id="relationshipA" class="list-group-item">涉案人员关系</a>
          <a href="#" id="reportA" class="list-group-item">专案进展报告</a>
          <a href="#" id="methodA" class="list-group-item">办案手段统计</a>
        </div> 
<p class="row-mar"> 专案资料：</p>  
		<div id="zazllxDiv" class="list-group">
        </div>
              
</div>     
</div>
<!-------左侧-------->   
     
 <!-------右侧-------->       
<div class="col-xs-10" style="width:82%">
<div >
         <div style="padding:10px;border-left:1px solid #ddd; margin-left:-1px; background:#fff; min-height:300px;">
         
 <!-------右侧内容begin--------> 

 <div id="dataDiv" class="subViewDiv">
  <!-- 专案资料模块 -->
 <%@include file="subView/zaListDetailDataLayer.jsp"%>
 </div>
 
 <div id="basicDiv" class="subViewDiv">
  <!-- 案件基本资料 -->
     <%@include file="subView/basicMessageLayer.jsp"%>
 </div>
 
  <div id="personDiv" class="subViewDiv">
  <!-- 专案涉案人员 -->
    <%@include file="subView/involvedPersonLayer.jsp"%>
 </div>
 
  <div id="relationshipDiv" class="subViewDiv">
  <!-- 涉案人员关系 -->
  <%@include file="subView/involvedPersonConcernLayer.jsp"%>
 </div>

 
  <div id="reportDiv" class="subViewDiv">
  <!-- 专案进展报告 -->
    <%@include file="subView/projectReportLayer.jsp"%>
 </div>
 
  <div id="methodDiv" class="subViewDiv">
  <!-- 办案手段统计 -->
  <%@include file="subView/zaListDetailMethodLayer.jsp"%>
 </div>
 </div>
 <!-------右侧内容end--------> 
 
<div id="bottomDiv" class="m-ui-commitbox zlsc">
<button id="fileUploadBtn" class="btn btn-primary btn-lg">资料上传</button> 
<button id="feedbackBtn" class="btn btn-primary btn-lg">留言反馈</button> 
<button id="goBackBtn" class="btn btn-default btn-lg">返回</button> 
</div>
 
</div>
</div>
</div> 

</div>


	<form action="<%=context%>/zagl/saveSpecialCaseRole.action"
		method="post" id="savejs">
		<input style="display: none" id="addjs" name="name" value="">
	</form>
	<form action="<%=context%>/zagl/updateSpecialCaseRole.action"
		method="post" id="updatejs">
		<input style="display: none" id="changejs" name="name" value="">
		<input style="display: none" id="code" name="code" value="">
	</form>

	<script type="text/javascript"
		src="<%=context%>/script/zaxx/zaListDetailLayer.js"></script>
	
	<!-- 专案资料模块 -->	
	<script type="text/javascript"
	src="<%=context%>/script/zaxx/subView/zaListDetailDataLayer.js"></script>
	
	<!-- 案件基本资料 -->
	<script type="text/javascript" src="<%=context%>/script/zaxx/subView/basicMessageLayer.js"></script>
	<!-- 专案涉案人员 -->
	<script type="text/javascript" src="<%=context%>/script/zaxx/subView/involvedPersonLayer.js"></script>
	<!-- 涉案人员关系 -->
	<script type="text/javascript" src="<%=context%>/script/zaxx/subView/involvedPersonConcernLayer.js"></script>
	<!-- 专案进展报告 -->
	<script type="text/javascript" src="<%=context%>/script/zaxx/subView/projectReportLayer.js"></script>
	<!-- 办案手段统计 -->
	<script type="text/javascript"
		src="<%=context%>/script/zaxx/subView/zaListDetailMethodLayer.js"></script>
	
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>

</body>

<script>
	//var zaId = "${param.id}"
</script>
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