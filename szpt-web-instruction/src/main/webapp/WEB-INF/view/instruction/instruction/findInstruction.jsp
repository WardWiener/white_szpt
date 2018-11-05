<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/instruction/common/constant.jsp"%>
</head>
<body class="m-ui-layer-body validform" id="validform">

<div class="m-ui-layer-box" style="width:850px;">

<div class="m-ui-layer-content">
<div class="row row-mar">
<div class="col-xs-2 m-label-left"> 指令内容：</div>
<div class="col-xs-9" id="content" style="word-break:break-all"></div>
</div> 
<div class="row row-mar">
<div class="col-xs-2 m-label-left"> 关联内容：</div>
<div class="col-xs-9" id="relateObjectContent"></div>
</div> 
<div class="row row-mar">
<div class="col-xs-2 m-label-left"> 指令类型：</div>
<div class="col-xs-3" id="type"></div>
</div> 
<div class="row row-mar personDiv" style="display:none">
<div class="col-xs-2 m-label-left"> 姓名：</div>
<div class="col-xs-3 typeContent" id="name"name="name"></div>
</div>
<div class="row row-mar personDiv" style="display:none">
<div class="col-xs-2 m-label-left"> 身份证号：</div>
<div class="col-xs-3 typeContent" id="idcode"name="idcode"></div>
</div>
<div class="row row-mar personDiv" style="display:none">
<div class="col-xs-2 m-label-left"> 盘查地点：</div>
<div class="col-xs-3 typeContent" id="personLocation"name="personLocation"></div>
</div>

<div class="row row-mar carDiv" style="display:none">
<div class="col-xs-2 m-label-left"> 车牌号：</div>
<div class="col-xs-3 typeContent" id="carNum"name="carNum"></div>
</div>
<div class="row row-mar carDiv" style="display:none">
<div class="col-xs-2 m-label-left"> 盘查地点：</div>
<div class="col-xs-3 typeContent" id="carLocation"name="carLocation"></div>
</div>

<div class="row row-mar">
<div class="col-xs-2 m-label-left"> 指令发送时间：</div>
<div class="col-xs-8" id="createTime"></div>
</div> 
 

<table id="table"class="table table-bordered table-hover m-ui-table-whole" cellspacing="0">
    </table>
<!--内容end-->

<p class="color-gray">温馨说明：
<span class="glyphicon glyphicon-exclamation-sign font16 color-red2 mar-right"></span>表示反馈超时
</p>






<!--操作历史-->
<div class="m-ui-title4 mar-top"><h2>操作历史</h2></div>  
<div class="od-expand-btn" id="od-expand-btn-1" onClick="document.getElementById('od-expand-content-1').style.display='block';document.getElementById('od-expand-btn-1').style.display='none'">
<a href="###"><span>显示操作历史</span></a>
</div>
<div id="od-expand-content-1" style="display:none;">
<div class="m-ui-table m-ui-table-sm">
	<table id="table2"class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="90%">
    </table>
</div>
<div class="od-expand-btn od-fold-btn" onClick="document.getElementById('od-expand-content-1').style.display='none';document.getElementById('od-expand-btn-1').style.display='block'"><a href="###"><span>隐藏操作历史</span></a></div>
</div>
<!--操作历史-->

</div>
</div>

</body>
<script type="text/javascript"
	src="<%=context%>/scripts/instruction/findInstruction.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/instruction/typeContentCommon.js"></script>
</html>