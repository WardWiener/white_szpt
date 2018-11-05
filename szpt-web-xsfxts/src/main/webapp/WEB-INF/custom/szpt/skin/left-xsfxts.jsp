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
<h1 class="nav-father">刑事案件分析</h1> 
		<div id="accordion" class="m-ui-accordion">
			
			<d:ss resource="${pageContext.request.contextPath}/xsfxts/showXsfxtsWelcome.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/xsfxts/showXsfxtsWelcome.action"'
				value='<span class="leftmenu-icon" style="margin-right:0px;"></span><span class="moveSpan"><a><span class="leftH3Span">刑事案件分析</span></a></span>' />
				
			<d:ss resource="${pageContext.request.contextPath}/caseJudge/showCaseJudgePage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/caseJudge/showCaseJudgePage.action"'
				value='<span class="leftmenu-icon" style="margin-right:0px;"></span><span class="moveSpan"><a><span class="leftH3Span">刑事案件一案一研判</span></a></span>' />
				
			<d:ss resource="${pageContext.request.contextPath}/caseTag/showCaseTagListPage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/caseTag/showCaseTagListPage.action"'
				value='<span class="leftmenu-icon" style="margin-right:0px;"></span><span class="moveSpan"><a><span class="leftH3Span">刑事案件分析打标</span></a></span>' />
			
			<d:ss resource="${pageContext.request.contextPath}/suspectMacAnalysis/showSuspectMacAnalysisPage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/suspectMacAnalysis/showSuspectMacAnalysisPage.action"'
				value='<span class="leftmenu-icon" style="margin-right:0px;"></span><span class="moveSpan"><a><span class="leftH3Span">嫌疑人MAC地址分析</span></a></span>' />
				
			<h3>
				<span class="leftmenu-icon" style="margin-right:0px;"></span>
				<span class="moveSpan">
					<span class="leftH3Span">两抢一盗案件智能<br>&emsp;&emsp;&nbsp;串并案分析</span>
				</span>
			</h3>
			<div>
				<ul>
					<li>
						<d:ss resource="/scoreTemplate/showScoreTemplateList.action" type="a" request="${pageContext.request}"
						otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/scoreTemplate/showScoreTemplateList.action"'
						value="案件串并案评分模板" />
					</li>
					<li>
						<d:ss resource="/caseAnalysis/showLookCaseTagListPage.action" type="a" request="${pageContext.request}"
						otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/caseAnalysis/showLookCaseTagListPage.action"'
						value="两抢一盗案件串并案分析结果查询" />
					</li>
				</ul>
			</div>
		</div>	
		<!-- 换肤  -->
	</div>
</div>


<script type="text/javascript"
	src="<%=request.getContextPath()%>/custom/szpt/js/left.js"></script>

