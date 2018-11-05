<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=8,IE=Edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style id="my-jcade-create-htc">
</style>

<script>
var ContextPath = '<%=request.getContextPath()%>'; 
var context = '<%=request.getContextPath()%>'; 
var serverName = '<%=request.getServerName()%>' ;

var clickOrder = '<%=request.getParameter("clickOrder")%>'; 
var clickListOrder = '<%=request.getParameter("clickListOrder")%>'; 

if(clickOrder=="null"){
	clickOrder = "0" ;
} ;
if(clickListOrder=="null"){
	clickListOrder = "0" ;
} ;



</script>

<!-- <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" /> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/common/library/bootstrap/dist/css/bootstrap.custom.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/common/library/bootstrap/jquery-ui-bootstrap/css/custom-theme/jquery-ui-1.10.0.custom.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/common/library/dataTables/css/jquery.dataTables.custom.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/common/library/zTree/css/zTreeStyle/zTreeStyle.css" />
<!-- icheck -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/common/library/icheck/skins/all.css?v=1.0.2" />

<link rel="stylesheet" href="<%=request.getContextPath()%>/common/library/chosen/css/chosen.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/common/library/select2/css/select2.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/common/library/font-awesome/css/font-awesome.css" /><!---------新增font-awesome图标库------------>
<link rel="stylesheet" href="<%=request.getContextPath()%>/common/base/style/base.css" />



<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/jquery/jquery-ui-fix-focus.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/jquery/jquery.md5.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/layer/layer.fixbug.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/laydate/laydate.dev.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/layPage/laypage.dev.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/laytpl/laytpl.dev.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/dataTables/js/jquery.dataTables.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/highcharts/js/highcharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/highcharts/js/highcharts-3d.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/zTree/js/jquery.ztree.all-3.5-bugfix.js"></script>
<!-- icheck -->
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/icheck/js/icheck.custom.js?v=1.0.2"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/icheck/js/custom.bugfix.js?v=1.0.2"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/select2/js/select2.full.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/chosen/js/chosen.jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/validform/validform.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/jcade-master/jcade.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/plupload/js/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/plupload/js/jquery.plupload.queue/jquery.plupload.queue.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/plupload/js/i18n/zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/base/js/plupload.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/common/base/js/basePart.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/base/js/layerAlert.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/base64/jquery.base64-zh.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/uuid/Math.uuid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/audioPlayer/js/audioPlayer.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/common/base/js/diyTable.js"></script>

<script>
$(document).ready(function() {	
	

});
</script>

<%@include file="/WEB-INF/base/skin/utilPart.jsp"%>
