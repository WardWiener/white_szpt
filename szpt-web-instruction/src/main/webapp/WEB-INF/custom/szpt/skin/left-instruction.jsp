<%@ page language="java" import="java.util.*,com.taiji.pubsec.szpt.util.Constant" pageEncoding="utf-8"%>
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
<h1 class="nav-father">指令管理</h1> 

		<div id="accordion" class="m-ui-accordion">
		
		<d:ss resource="${pageContext.request.contextPath}/instructionManage/showInstructionListPage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/instructionManage/showInstructionListPage.action"'
				value='<span class="leftmenu-icon" style="margin-right:0px;"></span><span class="moveSpan"><a><span class="leftH3Span">指令管理</span></a></span>' />
				
        <d:ss resource="${pageContext.request.contextPath}/instructionReceive/showInstructionReceiveListPage.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/instructionReceive/showInstructionReceiveListPage.action"'
				value='<span class="leftmenu-icon" style="margin-right:0px;"></span><span class="moveSpan"><a><span class="leftH3Span">接收指令</span></a></span>' />
		</div>	
	</div>
</div>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/custom/szpt/js/left.js"></script>

