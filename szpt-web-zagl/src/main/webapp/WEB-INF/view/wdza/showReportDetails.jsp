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
<%@include
	file="/common/library/iweboffice2000/iweboffice2000-import.jsp"%> 
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
<div class="m-ui-title4 mar-top"> <h2>报告内容</h2></div> 

<p class="row"><span class="col-xs-2 m-bold">报告名称：</span><span id="reportName"></span></p>
<p class="row"><span class="col-xs-2 m-bold">录入人：</span><span id="createPerson"></span></p>
<p class="row"><span class="col-xs-2 m-bold">录入时间：</span><span id="createdTime" ></span></p>
<p class="m-line"></p>

<div >
	<div class="row row-mar2" id="hasWordDiv">
			<div class="col-xs-2">
				<label class="control-label">报告附件：</label>
			</div>
			<div class="col-xs-8">
				<div id="wordDiv"></div>
			</div>
			<!-- <div class="col-xs-2">
				<button class="btn btn-primary btn-sm btn-reupload"
					style="margin-left: 5px;">重新上传</button>
			</div> -->
		</div>
		<div class="row row-mar2" id="reuploadDiv" style="display: none">
			<div class="col-xs-2">
				<label class="control-label">附件上传：</label>
			</div>
			<div class="col-xs-8">
				<div id="container" class="upload-control"></div>
			</div>
			<div class="col-xs-2">
				<button class="btn btn-primary btn-sm btn-cancelUpload"
					style="margin-left: 5px;">取消</button>
			</div>
		</div>
<div style="min-height:1300px; overflow-x:auto;">
  <div id="iwebOffice-container">
		<div class="webOffice-objDiv">
			<object class="webOffice-obj" width="100%" height="1200px"
				classid="clsid:<%=com.taiji.pubsec.complex.tools.weboffice.iweboffice2000.util.Constant.getClsid()%>"
				codebase="<%=com.taiji.pubsec.complex.tools.weboffice.iweboffice2000.util.Constant.getCodebase()%>"> </object>
		</div>
	</div>


<div class="m-ui-commitbox">
<button class="btn btn-default btn-lg" id="returnBtn">返回</button> 
<button class="btn btn-primary btn-lg">导出打印</button> 
</div>
</div>
</div>  
</div>
 <!-------右侧内容end--------> 
 
</div>
 </div>
</div>
 
       
<!--row-end-->        
<!--内容end-->


<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/common/library/iweboffice2000/js/iWebOffice2000.js"></script> --%>

	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
<script type="text/javascript"
		src="<%=context%>/script/wdza/showReportDetailst.js"></script>
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