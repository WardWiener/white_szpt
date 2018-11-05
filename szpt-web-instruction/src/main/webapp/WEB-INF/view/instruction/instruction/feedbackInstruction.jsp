<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/instruction/common/constant.jsp"%>
</head>
<body id="validform" class="validform m-ui-layer-body">

<div class="m-ui-layer-box" style="width:600px;">

<div class="m-ui-layer-content">
<div class="m-ui-title4 mar-top"><h2>指令反馈</h2></div>  
<div class="row row-mar">
<div class="col-xs-2 m-label-left"> 指令内容：</div>
<div class="col-xs-8" id="content"></div>
</div> 
<div class="row row-mar">
<div class="col-xs-2 m-label-left"> 关联内容：</div>
<div class="col-xs-8" id="relateObjectContent"></div>
</div> 
<div class="row row-mar">
<div class="col-xs-2 m-label-left"> 要求反馈时间：</div>
<div class="col-xs-8 " id="requireFeedbackTime"></div>
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
<div class="col-xs-8" id="receiveTime"></div>
</div> 
<div class="m-ui-title4 mar-top"><h2>已反馈内容</h2></div>  

<table id="table"class="table table-bordered table-hover m-ui-table-whole" cellspacing="0">
    </table>
<!--内容end-->
<div id="feedbackDiv">
<div class="row row-mar mar-top">
<div class="col-xs-2"> <label class="control-label">反馈内容：<span class="red-star">*</span></label></div>
<div class="col-xs-8"><textarea class="form-control input-sm valiform-keyup" rows="4" datatype="*1-500" nullmsg="请填写反馈内容" vf-position="4"id="feedbackContent"></textarea></div>
</div> 

<div class="row row-mar"id="checkDiv"style="display:none">
<div class="col-xs-3"> <label class="control-label">情报线索属实：<span class="red-star">*</span></label></div>
<div class="col-xs-4"><div class="row m-icheck-group" style="margin-bottom:10px;">
                      <p class="col-xs-4"><input type="radio" class="icheckradio" id="radio1"value="1"name="radio"checked>是</p>
                      <p class="col-xs-4"><input type="radio" class="icheckradio" id="radio0"value="0"name="radio">否</p>
                     </div></div>
</div>

<div class="row row-mar">
<div class="col-xs-2"> <label class="control-label">反馈人：</label></div>
<div class="col-xs-3"><input type="text" class="form-control input-sm"id="feedbackPeopleName" readonly="true" ></div>
<div class="col-xs-2"> <label class="control-label">反馈时间：</label></div>
<div class="col-xs-3"><input type="text" class="form-control input-sm"id="feedbackTime" readonly="true"></div>
</div> 
</div> 





<!--操作历史-->
<div class="m-ui-title4"><h2>操作历史</h2></div>  
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
	src="<%=context%>/scripts/instruction/feedbackInstruction.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/instruction/typeContentCommon.js"></script>
</html>