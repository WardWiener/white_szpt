<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="d" uri="/WEB-INF/tag/securityresource.tld"%>
<%@ page import="com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil,
com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser,
java.util.List,java.util.Map,java.util.HashMap,
com.taiji.pubsec.common.tools.spring.SpringContextUtil
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

<script>
var currentUserName = '<%=mPerson.get("name")%>';
var currentUserCode = '<%=mPerson.get("code")%>';
var currentUnitName = '<%=mOrg.get("shortName")%>';
var currentUnitCode = '<%=mOrg.get("code")%>';
var personCode = '<%=mPerson.get("code")%>';

$(document).on("click", ".exit", function(){
	$.layerAlert.confirm({
		msg : "确认退出吗？",
		yes : function(index,layero) {
			window.top.location.href = context + "/logout" ;
		}
	});	
});

</script>

		<!-- 换肤  -->
		<div class="c-top-content">
			<div class="logo">
				<h1>
					<a href="<%=request.getContextPath()%>/loginHomePage/showLoginPage.action" target="_self" title="返回首页">贵阳市局经开分局警务实战平台</a>
				</h1>
				<h2>高危人群分析预警</h2>
			</div>
			<div class="c-top-right">
				<div class="home-search-block">
					<div class="home-search">
						<input id="indexInput" class="form-control input-sm" value="警情、事件、案件、高危人、场所……"
							onBlur="if(!value){value=defaultValue;this.style.color='#b8d3f4'}"
							onFocus="if(value==defaultValue){value='';this.style.color='#fff'}"
							style="color: #b8d3f4;">
						<button id="search" class="btn">
							<span class="icon icon-search"></span>
						</button>
					</div>
				</div>
				<ul>
					<li><a href="<%=request.getContextPath()%>/loginHomePage/showLoginPage.action" target="_self"><span
							class="top-icon link-index-icon"></span><i>首页</i></a></li>
					<li><a href="<%=request.getContextPath()%>/zhzats/showZhzatsWelcome.action" target="_self"><span
							class="top-icon link-za-icon"></span><i>治安态势</i></a></li>
					<li><a href="<%=request.getContextPath()%>/xsfxts/showXsfxtsWelcome.action" target="_self"><span
							class="top-icon link-xs-icon"></span><i>刑事案件</i></a></li>
					<li><a href="<%=request.getContextPath()%>/highriskPerson/showHomePage.action" target="_self"><span
							class="top-icon link-gw-icon"></span><i>高危人群</i></a></li>
					<li><a href="<%=request.getContextPath()%>/realTimeWifi/showLookRealTimeWifiPage.action" target="_self"><span
							class="top-icon link-cs-icon"></span><i>场所分析</i></a></li>
					<li><div class="nav-ceng-out">
							<a href="###"><span class="top-icon link-menu-icon"></span><i>系统导航</i></a>
							<div class="nav-ceng nav-systems">
								<ul class="nav">
									<li><a href="<%=request.getContextPath()%>/yayd/showYaydListPage.action" target="_self"><span
											class="icon sz-icon-1"></span>档案管理</a></li>
									<li><a href="<%=request.getContextPath()%>/instructionManage/showInstructionListPage.action" target="_self"><span
											class="icon sz-icon-2"></span>指令管理</a></li>
									<li><a href="###" ><span
											class="icon sz-icon-3"></span>情报线索</a></li>
									<li><a href="<%=request.getContextPath()%>/gzgl/showPenalConstant.action" target="_self"><span
											class="icon sz-icon-4"></span>基础设置</a></li>
									<li><a href="<%=request.getContextPath()%>/homePage/showHomePageJsp.action" target="_self"><span
											class="icon sz-icon-5"></span>系统管理</a></li>
								</ul>
							</div>
						</div></li>
					<li><a href="###" class="exit">退出</a></li>
				</ul>
			</div>
		</div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/custom/szpt/js/top.js"></script>
		<%@include file="/WEB-INF/custom/szpt/skin/comet.jsp"%>
		<!-- 换肤  -->
