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
<h1 class="nav-father">综合治安态势</h1> 
		<!-- 换肤  -->
		<div id="accordion" class="m-ui-accordion">
			
			<d:ss resource="${pageContext.request.contextPath}/zhzats/showZhzatsWelcome.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/zhzats/showZhzatsWelcome.action"'
				value='<span class="leftmenu-icon"></span><span class="moveSpan"><a><span class="leftH3Span">综合治安态势</span></a></span>' />

	        <d:ss resource="${pageContext.request.contextPath}/fenbu/showZhzatsFenbu.action" type="h3" request="${pageContext.request}"
				otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/fenbu/showZhzatsFenbu.action"'
				value="<span class='leftmenu-icon icon-1'></span><span class='moveSpan'><a><span class='leftH3Span'>警情分布分析</span></a></span>" />	        
	        
	        <d:ss resource="${pageContext.request.contextPath}/gaofa/showZhzatsGaofa.action" type="h3" request="${pageContext.request}"
				otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/gaofa/showZhzatsGaofa.action"'
				value="<span class='leftmenu-icon icon-1'></span><span class='moveSpan'><a><span class='leftH3Span'>高发警情分析</span></a></span>" />		  		  		  		  		  	  		  		  	  
	       
	        <d:ss resource="${pageContext.request.contextPath}/yanpan/showZhzatsYanpan.action" type="h3" request="${pageContext.request}"
				otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/yanpan/showZhzatsYanpan.action"'
				value="<span class='leftmenu-icon icon-1'></span><span class='moveSpan'><a><span class='leftH3Span'>刑事警情研判</span></a></span>" />		  		  		  		  		  	  		  		  	  
		</div>	
		<!-- 换肤  -->
	</div>
</div>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/custom/szpt/js/left.js"></script>

