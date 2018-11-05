<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.taiji.pubsec.complex.tools.comet.constant.Constants,
com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser,
com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil,
com.taiji.pubsec.common.tools.spring.SpringContextUtil,
java.util.Map,java.util.HashMap
"%>
<%
Map<String, Object> um = SessionUserDetailsUtil.getMySecureUser().getUserMap() ;
Map<String, String> mo = new HashMap<String, String>(0) ;
if(um.get("org")!=null){
	mo = (Map<String, String>)um.get("org");
}
Map<String, String> mp = new HashMap<String, String>(0) ;
if(um.get("person")!=null){
	mp = (Map<String, String>)um.get("person");
}
%>

<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/msghint/js/msghint.js"></script>
<script>
var currentUnitCodeForPush = '<%=mo.get("code")%>' + 'PC';
var currentAccountCodeForPush = '<%=mp.get("code")%>' + 'PC';
var COMET_OORT_URL = '<%=Constants.getOORT_URL()%>' ;
var COMET_OORT_CLOUD = '<%=Constants.getOORT_CLOUD()%>' ;
var COMET_OORT_LOCAL_URL = '<%=Constants.getOORT_LOCAL_URL()%>' ;
</script>

<script type="text/javascript" src="<%=request.getContextPath()%>/custom/szpt/js/comet.js"></script>

