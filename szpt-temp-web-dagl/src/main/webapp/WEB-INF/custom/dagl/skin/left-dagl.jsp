<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="d" uri="/WEB-INF/tag/securityresource.tld"%>
<div id="c-center-left">
<div id="c-center-left-menuHeight">
    
<div class="user-box">
	<div class="user-icon">
		<img src="<%=request.getContextPath()%>/images/police.png">
	</div>
	<div class="user-name"></div>
</div>
<script type="text/javascript">
	$(".user-name").html(currentUserName + "<br>" + currentUnitName);
</script>
<h1 class="nav-father">档案管理</h1> 
		<!-- 换肤  -->
		<div id="accordion" class="m-ui-accordion">
			
			<d:ss resource="${pageContext.request.contextPath}/yayd/showYaydListPage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/yayd/showYaydListPage.action"'
				value='<span class="leftmenu-icon"></span><span class="moveSpan"><a><span class="leftH3Span">一案一档</span></a></span>' />
<%-- 			<d:ss resource="${pageContext.request.contextPath}/yryd/showYrydPage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/yryd/showYrydPage.action"'
				value='<span class="leftmenu-icon"></span><span class="moveSpan"><a><span class="leftH3Span">一人一档</span></a></span>' />
 --%>			
 				<d:ss resource="${pageContext.request.contextPath}/yryd/showYrydHighriskPersonAlertPage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/yryd/showYrydLstPage.action"'
				value='<span class="leftmenu-icon"></span><span class="moveSpan"><a><span class="leftH3Span">一人一档</span></a></span>' />
		
		</div>	
		<!-- 换肤  -->
	</div>
</div>


<script type="text/javascript"
	src="<%=request.getContextPath()%>/custom/szpt/js/left.js"></script>

