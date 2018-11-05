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
	
	 if(currentUnitCode != "520199310000"){
		$("#personApproveListH3").hide();
	} 
</script>
<h1 id="analyzeAlert" class="nav-father">高危人群分析</h1> 
		<!-- 换肤  -->
		<div id="accordion" class="m-ui-accordion">
			<d:ss resource="${pageContext.request.contextPath}/highriskPerson/showHomePage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/highriskPerson/showHomePage.action"'
				value='<span class="leftmenu-icon gw-icon-0" style="margin-right:0px;"></span><span class="moveSpan"><a><span class="leftH3Span">高危人群分析</span></a></span>' />
				
			<!--一般警员登陆界面 -->
			<d:ss resource="${pageContext.request.contextPath}/highriskPerson/showPersonListPage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/highriskPerson/showPersonListPage.action"'
				value='<span class="leftmenu-icon gw-icon-1" style="margin-right:0px;"></span><span class="moveSpan"><a><span class="leftH3Span">高危人管理</span></a></span>' />
				
			<!--领导查看页面-->
			<d:ss resource="${pageContext.request.contextPath}/highriskPerson/showPersonApproveListPage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/highriskPerson/showPersonApproveListPage.action"'
				value='<span class="leftmenu-icon gw-icon-1" style="margin-right:0px;"></span><span class="moveSpan"><a><span class="leftH3Span">高危人管理</span></a></span>' />
				
			<%-- <d:ss resource="${pageContext.request.contextPath}/personDetail/showPersonDetailPage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/personDetail/showPersonDetailPage.action"'
				value='<span class="leftmenu-icon gw-icon-2" style="margin-right:0px;"></span><span class="moveSpan"><a><span class="leftH3Span">人员档案</span></a></span>' /> --%>
				
			<d:ss resource="${pageContext.request.contextPath}/personAttention/showPersonAttentionPage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/personAttention/showPersonAttentionPage.action"'
				value='<span class="leftmenu-icon gw-icon-3" style="margin-right:0px;"></span><span class="moveSpan"><a><span class="leftH3Span">高危人预警</span></a></span>' />
				
			<%-- <d:ss resource="${pageContext.request.contextPath}/heatAnalyze/showHeatAnalyzePage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/heatAnalyze/showHeatAnalyzePage.action"'
				value='<span class="leftmenu-icon gw-icon-4" style="margin-right:0px;"></span><span class="moveSpan"><a><span class="leftH3Span">人案时空分析</span></a></span>' /> --%>
				
			<%-- <d:ss resource="${pageContext.request.contextPath}/trackAnalyze/showTrackAnalyzePage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/trackAnalyze/showTrackAnalyzePage.action"'
				value='<span class="leftmenu-icon gw-icon-5" style="margin-right:0px;"></span><span class="moveSpan"><a><span class="leftH3Span">轨迹分析</span></a></span>' /> --%>
				
			<d:ss resource="${pageContext.request.contextPath}/integralModel/showIntegralModelPage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/integralModel/showIntegralModelPage.action"'
				value='<span class="leftmenu-icon gw-icon-4" style="margin-right:0px;"></span><span class="moveSpan"><a><span class="leftH3Span">人员积分模型设置</span></a></span>' />
				
			<d:ss resource="${pageContext.request.contextPath}/personExecuteControl/showPersonExecuteControlListPage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/personExecuteControl/showPersonExecuteControlListPage.action"'
				value='<span class="leftmenu-icon gw-icon-0" style="margin-right:0px;"></span><span class="moveSpan"><a><span class="leftH3Span">人员布控</span></a></span>' />
				
			<d:ss resource="${pageContext.request.contextPath}/personExecuteControl/showPersonExecuteControlApproveListPage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/personExecuteControl/showPersonExecuteControlApproveListPage.action"'
				value='<span class="leftmenu-icon gw-icon-0" style="margin-right:0px;"></span><span class="moveSpan"><a><span class="leftH3Span">人员布控</span></a></span>' />
				
		
		
        <!-- <h3>
        	<span class="leftmenu-icon wf-icon-6"></span>
        	<span class="moveSpan">
        		<d:ss resource="/realTimeWifi/*" type="a" request="${pageContext.request}"
					otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}//highriskPerson/showHomePage.action"'
					value="关联分析" />
        	</span>
        </h3>
        <div></div>
        <h3>
        	<span class="leftmenu-icon wf-icon-7"></span>
        	<span class="moveSpan">
        		<d:ss resource="/personAttention/*" type="a" request="${pageContext.request}"
					otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}//highriskPerson/showHomePage.action"'
					value="通联分析" />
        	</span>
        </h3>
		<div></div> -->
  		<!--   <h3>
  		 	<span class="leftmenu-icon gw-icon-7"></span>
  		 	<span class="moveSpan">
  		 		<d:ss resource="/geographicalZoneAnalyze/showGeographicalZoneAnalyzePage.action" type="a" request="${pageContext.request}"
					otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/geographicalZoneAnalyze/showGeographicalZoneAnalyzePage.action"'
					value="地源性人员分析" />
  		 	</span>
  		 </h3>
		 <div></div> -->  
		<%--  <h3>
        	<span class="leftmenu-icon gw-icon-4"></span>
        	<span class="moveSpan">
        		<d:ss resource="/integralModel/*" type="a" request="${pageContext.request}"
					otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/integralModel/showIntegralModelPage.action"'
					value="人员积分模型设置" />
        	</span>
        </h3>
        <div></div>
         <h3>
			<span class="leftmenu-icon gw-icon-0"></span>
			<span class="moveSpan">
				<d:ss resource="/personExecuteControl/showPersonExecuteControlListPage.action" type="a" request="${pageContext.request}"
					otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/personExecuteControl/showPersonExecuteControlListPage.action"'
					value="人员布控" />
			</span>
		</h3>
		<div></div>
		<h3>
			<span class="leftmenu-icon gw-icon-0"></span>
			<span class="moveSpan">
				<d:ss resource="/personExecuteControl/showPersonExecuteControlApproveListPage.action" type="a" request="${pageContext.request}"
					otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/personExecuteControl/showPersonExecuteControlApproveListPage.action"'
					value="人员布控(领导审批页)"/><!-- 领导审批 -->
			</span>
		</h3>
		<div></div>
		</div>	 --%>
		<!-- 换肤  -->
	</div>
</div>
<script type="text/javascript">
	$(document).on("click","#analyzeAlert",function(){
		window.top.location.href = context + "/highriskPerson/showHomePage.action" ;
	});
</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/custom/szpt/js/left.js"></script>

