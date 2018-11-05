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
<h1 class="nav-father">平台基础</h1> 
		<!-- 换肤  -->
		<div id="accordion" class="m-ui-accordion">
			<h3><span class="leftmenu-icon"></span><span class="moveSpan"><span class="leftH3Span">统计分析类规则管理</span></span></h3>
             <div>
                 <ul>
                  <li>
                  	<d:ss resource="/gzgl/*" type="a" request="${pageContext.request}"
					otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/gzgl/showPenalConstant.action"'
					value="刑事警情常量设置" />
                  </li>
                  <li>
                  	<d:ss resource="/gzgl/*" type="a" request="${pageContext.request}"
					otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/gzgl/showDistributionRule.action"'
					value="警情分布规则设置" />
                  </li>
                </ul>
             </div>
		</div>	
		<!-- 换肤  -->
	</div>
</div>


<script type="text/javascript"
	src="<%=request.getContextPath()%>/custom/szpt/js/left.js"></script>

