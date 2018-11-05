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
<div class="m-ui-title2"><h2>专案详情</h2><div class="m-ui-closebox"><button id="closeAdd" class="btn m-ui-close"></button></div></div>
<div class="row" style=" margin:0;background:#f8f8f8;">

<!-------左侧-------->
<div class="col-xs-2" style="width:18%">
<div style="padding:10px; border-right:1px solid #ddd;">
<p class="row-mar"> 专案信息：</p>
 <div class="list-group">
          <a href="#" class="list-group-item">案件基本资料</a>
          <a href="#" class="list-group-item list-group-item-info">专案涉案人员</a>
          <a href="#" class="list-group-item">涉案人员关系</a>
          <a href="#" class="list-group-item">专案进展报告</a>
          <a href="#" class="list-group-item">办案手段统计</a>
        </div> 
<p class="row-mar"> 专案资料：</p>  
<div class="list-group">
          <a href="#" class="list-group-item">音频资料</a>
          <a href="#" class="list-group-item">视频资料</a>
          <a href="#" class="list-group-item">图片资料</a>
          <a href="#" class="list-group-item">其他资料</a>
        </div>
              
</div>     
</div>
<!-------左侧-------->   
     
 <!-------右侧-------->       
<div class="col-xs-10" style="width:82%">
         <div style="padding:10px;border-left:1px solid #ddd; margin-left:-1px; background:#fff; min-height:300px;">
         
 <!-------右侧内容begin--------> 
 <div class="m-ui-title4 mar-top"><button  id="involvedPersonWHBtn" class="btn btn-info pull-right" style="margin-top:-6px;">维护专案涉案人员信息</button> <h2>专案涉案人员信息</h2></div> 
<table id="table" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%" >
                      
                  </table>

<div class="m-ui-commitbox">
<button class="btn btn-primary btn-lg">资料上传</button> 
<button class="btn btn-primary btn-lg">留言反馈</button> 
<button class="btn btn-default btn-lg">返回</button> 
</div>


 <!-------右侧内容end--------> 
 

 
</div>
</div>
</div>  
       
<!--row-end-->        
<!--内容end-->


</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
<script type="text/javascript" src="<%=context%>/script/wdza/involvedPerson.js"></script>
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