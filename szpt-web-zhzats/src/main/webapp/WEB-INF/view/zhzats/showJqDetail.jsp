<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title>查看警情详情</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
</head>

<body class="m-ui-layer-body">

<div class="m-ui-layer-box" style="width:600px;">

<div id="showJqDetail" class="m-ui-layer-content">
<div class="m-ui-title4"><h2>接警信息</h2></div>
<div class="row row-mar">
<div class="col-xs-2 m-label-left"> 接警时间：</div>
<div class="col-xs-3"><span name="answerAlarmDate"></span></div>
<div class="col-xs-2 m-label-left"> 接警人：</div>
<div class="col-xs-2"><span name="jjr"></span></div>
</div> 
<div class="row row-mar">
<div class="col-xs-2 m-label-left"> 接警类别：</div>
<div class="col-xs-3"><span name="jqlxName"></span></div>
<div class="col-xs-2 m-label-left"> 警情来源：</div>
<div class="col-xs-2"><span name="jqSource"></span></div>
</div> 
<div class="row row-mar">
<div class="col-xs-2 m-label-left"> 报警人：</div>
<div class="col-xs-3"><span name="callingPerson"></span></div>
</div> 
<div class="row row-mar">
<div class="col-xs-2 m-label-left"> 发生地点：</div>
<div class="col-xs-8"><span name="addr"></span></div>
</div> 
<div class="row row-mar">
<div class="col-xs-2 m-label-left"> 警情概要：</div>
<div class="col-xs-8"><span name="jqSummary"></span></div>
</div> 



<div class="m-ui-title4"><h2>反馈信息</h2></div>
<div class="row row-mar">
<div class="col-xs-2 m-label-left"> 反馈类别：</div>
<div class="col-xs-3"><span name="jqSummary"></span> </div>
<div class="col-xs-2 m-label-left"> 反馈人：</div>
<div class="col-xs-2"><span name="jqSummary"></span></div>
</div>
<div class="row row-mar">
<div class="col-xs-2 m-label-left"> 反馈时间：</div>
<div class="col-xs-3"><span name="jqSummary"></span></div>
</div>
<div class="row row-mar">
<div class="col-xs-2 m-label-left"> 反馈内容：</div>
<div class="col-xs-8"><span name="jqSummary"></span></div>
</div>
</div>

<div class="m-ui-title4"><h2>警情处置记录</h2></div>
<div class="history-content history-content3 m-overflow-box" style="background:#f8f8f8; height:400px; overflow:auto; padding-left:20px">

<ul id="recordAppend" style="border-color:#aaa;">
<!-- <li class="odd"><span class="icon-red-dot"></span>
        <div class="time-box"><p>09:02 &nbsp;&nbsp;2016年4月14日</p></div>
        <div class="con-box"><span class="arrow"></span>主格JA01  接警</div>
</li>
<li class="even"><span class="icon-red-dot"></span>
        <div class="time-box"><p>09:11 &nbsp;&nbsp;2016年4月14日</p></div>
        <div class="con-box"><span class="arrow"></span>主格JA01  反馈：到达现场</div>
</li> -->

<li class="clear"></li>
</ul>
</div>

</div>

</body>
<script type="text/javascript"
	src="<%=context%>/scripts/zhzats/showJqDetail.js"></script>
</html>