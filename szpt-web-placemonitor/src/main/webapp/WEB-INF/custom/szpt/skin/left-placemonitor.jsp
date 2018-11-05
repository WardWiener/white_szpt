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
<h1 id="placemonitor" class="nav-father">场所监控分析</h1> 
		<!-- 换肤  -->
		<div id="accordion" class="m-ui-accordion">
			<d:ss resource="${pageContext.request.contextPath}/realTimeWifi/showLookRealTimeWifiPage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/realTimeWifi/showLookRealTimeWifiPage.action"'
				value='<span class="leftmenu-icon wf-icon-1"></span><span class="moveSpan"><a><span class="leftH3Span">实时WIFI设备监控</span></a></span>' />
		
			<d:ss resource="${pageContext.request.contextPath}/personLocusAnalyze/showPersonLocusAnalyzePage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/personLocusAnalyze/showPersonLocusAnalyzePage.action"'
				value='<span class="leftmenu-icon wf-icon-2"></span><span class="moveSpan"><a><span class="leftH3Span">人员WIFI轨迹分析</span></a></span>' />
		
			<d:ss resource="${pageContext.request.contextPath}/wifiAnalyze/showWifiAnalyzePage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/wifiAnalyze/showWifiAnalyzePage.action"'
				value='<span class="leftmenu-icon wf-icon-3"></span><span class="moveSpan"><a><span class="leftH3Span">高危人WIFI监测</span></a></span>' />
		
			<d:ss resource="${pageContext.request.contextPath}/deployControl/showDeployControlPreserve.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/deployControl/showDeployControlPreserve.action"'
				value='<span class="leftmenu-icon wf-icon-3"></span><span class="moveSpan"><a><span class="leftH3Span">WIFI围栏监控点</span></a></span>' />
		
		</div>	
		<!-- 换肤  -->
	</div>
</div>
<script type="text/javascript">
	$(document).on("click","#analyzeAlert",function(){
	});
</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/custom/szpt/js/left.js"></script>

