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
<h1 class="nav-father">实战平台</h1> 
		<!-- 换肤  -->
		<div id="accordion" class="m-ui-accordion">
			
			<!--
			<d:ss resource="${pageContext.request.contextPath}/zagl/zaDataMaintenance.action" type="h3" request="${pageContext.request}"
				otherAttribute='myHref="${pageContext.request.contextPath}/zagl/zaDataMaintenance.action"'
				value='<span class="leftmenu-icon"></span><span class="moveSpan"><a><span class="leftH3Span">刑事案件分析</span></a></span>' />
 		  	<div id="accordion" class="m-ui-accordion">
 		  	-->
			<h3><span class="leftmenu-icon"></span><span class="moveSpan"><span class="leftH3Span">专案管理</span></span></h3>
             <div id="zaglleftDIV">
                 <ul>
                  <li>
                  	<d:ss resource="/zagl/*" type="a" request="${pageContext.request}"
					otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/zagl/showProjectManagement.action"'
					value="专案角色维护" />
                  </li>
                  <li>
                  	<d:ss resource="/zllxgl/*" type="a" request="${pageContext.request}"
					otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/zllxgl/showZaDataMaintenance.action"'
					value="专案资料分类维护" />
                  </li>
                  <li>
                  	<d:ss resource="/zawh/*" type="a" request="${pageContext.request}"
					otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/zawh/showProjectsMaintenance.action"'
					value="专案维护" />
                  </li>
                  <li>
                  	<d:ss resource="/wdza/*" type="a" request="${pageContext.request}"
					otherAttribute='href="javascript:void(0);" myHref="${pageContext.request.contextPath}/wdza/showMyCase.action"'
					value="我的专案" />
                  </li>
                </ul>
             </div>
		</div>			  		  	  		  		  	  
		</div>	
		<!-- 换肤  -->
	</div>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/custom/szpt/js/left.js"></script>

