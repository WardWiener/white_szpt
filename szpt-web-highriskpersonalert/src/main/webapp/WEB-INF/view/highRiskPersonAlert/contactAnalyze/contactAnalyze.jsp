<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/highRiskPersonAlert/common/constant.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<div id="c-center">
		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-highRiskPersonAlert.jsp"%>
		</div>
		<div id="c-center-right">
			<div id="c-center-right-content">
			
			<ol class="breadcrumb m-ui-breadcrumb">
			  <li><a href="#">首页</a></li>
				<li><a href="#">高危人群分析</a></li>
			  <li><a href="#">通联分析</a></li>
			</ol>
			
			<div class="m-ui-title1"><h1>通联分析</h1></div>
			<div class="row row-mar" style="margin-top:-40px; margin-left:220px;">
			<button id="yidong"class="btn btn-primary">移动话单导入</button>
			<button id="liantong"class="btn btn-primary">联通话单导入</button>
			<button id="dianxin"class="btn btn-primary">电信话单导入</button>
			<button id="analyze"class="btn btn-success">分析</button>
			<button id="all"class="btn btn-success">全部话单分析</button>
			</div> 
			
			<div id="c-center-right-content-block">
			<div id="c-center-right-content-bar">
			
			<div class="panel panel-info">
			  <div class="panel-heading" style="background-color:#1d98de; color:#fff;">待分析人员
			  <button id="add"class="btn btn-danger btn-sm" style="float:right; margin-top:-5px; margin-right:-5px;"><span class="glyphicon glyphicon-plus"></span></button></div>
			<ul class="list-group">
			  <a href="#"  class="list-group-item"><span class="badge m-badge">18993454321</span>周璐 </a>
			  <a href="#"  class="list-group-item"><span class="badge m-badge">18992345677</span>孙果果</a>
			  <a href="#" class="list-group-item"><span class="badge m-badge">19834568344</span>李柳</a>
			  <a href="#" class="list-group-item"><span class="badge m-badge">19834568365</span>萧邦</a>
			  <a href="#"  class="list-group-item"><span class="badge m-badge">19834568373</span>洛宜路</a>
			</ul>
			</div>
			</div>
			
			<div id="c-center-right-content-con">
			<div class="right-inner">
			<div class="row light-block" style="padding:20px; text-align:center">
			<img src="../images/map/map-huadan.png" height="550">
			</div>
			</div>
			</div>
			
			</div>
			</div>
			</div>
	</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/contactAnalyze/contactAnalyze.js"></script>
</html>