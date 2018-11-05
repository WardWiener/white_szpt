<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
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
<style type="text/css">
table.gridtable{
    font-family:verdana,arial,sans-serif;
    font-size:11px;
    color:#333333;
    border-width:1px;
    border-color:#666666;
    border-collapse: collapse;
  }
table.gridtable th{
    text-align:center;
    border-width:1px;
    padding:8px;
    border-style: solid;
    border-color:#666666;
    background-color: #dedede;
  }  
table.gridtable td{
    border-width:1px;
    padding:8px;
    border-style: solid;
    border-color:#666666;
    background-color: #ffffff;
  } 

</style>
<!-- 公用常量页面 -->

</head>

<body style="background: white;" align="center" >

<table id="table" class="gridtable" cellspacing="0" width="100%" height="100%">
  <tr>
        <th >类型</th><th>详情</th>
  </tr>
     </table>

	
</body>
 <script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/personExecuteControl/showPersonExecuteControlDetail.js"></script>

</html>