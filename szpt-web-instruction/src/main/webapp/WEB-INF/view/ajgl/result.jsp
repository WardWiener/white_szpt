<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=8,IE=Edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>案管客户端</title>
<script>

</script>
<!-- 换肤  -->
<script>
var ContextPath = '<%=request.getContextPath()%>'; 
var context = '<%=request.getContextPath()%>'; 
var serverName = '<%=request.getServerName()%>' ;

var clickOrder = '<%=request.getParameter("clickOrder")%>'; 
var clickListOrder = '<%=request.getParameter("clickListOrder")%>'; 

var identy = '<%=request.getParameter("identy")%>'; 

if(clickOrder=="null"){
	clickOrder = "0" ;
} ;
if(clickListOrder=="null"){
	clickListOrder = "0" ;
} ;

</script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/layer.css" />


<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/jquery/jquery-ui-fix-focus.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/layer/layer.fixbug.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/dataTables/js/jquery.dataTables.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/base/js/basePart.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/jcade-master/jcade.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/base/js/layerAlert.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/custom/szpt/js/util.js"></script>
<!--关闭按钮旋转-->
<script type="text/javascript" src="<%=request.getContextPath()%>/ajgl/custom/ajgl/default/js/jQueryRotate.2.2.js"></script>

<link rel="stylesheet" href="<%=context%>/ajgl/custom/ajgl/default/style/base.css">
<link rel="stylesheet" href="<%=context%>/ajgl/custom/ajgl/default/style/frame.css">
<link rel="stylesheet" href="<%=context%>/ajgl/custom/ajgl/default/style/cs.css">

<script type="text/javascript">
$.common = $.common || {} ;
(function($){
	
	"use strict";
	
	jQuery.extend($.common, { 
		SZPT_HOST_PORT:'<%=com.taiji.pubsec.szpt.util.Constant.SZPT_HOST_PORT()%>'
	});
})(jQuery);	
</script>

</head>

<body class="cs-bg">
<div class="cs-content">
<div id="divMouseMove" class="cs-content-top">
<h2>研判结果查看</h2>
<a id="btn-close-window" href="####" style="transform: rotate(0deg);"></a>
</div>
<div class="cs-content-main">
<div class="m-ui-table">
    <table id="resConTable" class="table table-hover m-ui-table-whole" cellspacing="0" width="100%">
    
    </table>
</div>
</div>
 </div>
</body>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/ajgl/result.js"></script> 
</html>