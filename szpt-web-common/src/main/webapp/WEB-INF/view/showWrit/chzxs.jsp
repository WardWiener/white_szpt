<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
</head>
<body class="m-ui-layer-body">
	<div class="text-center" style="padding: 20px" >
		<button id="btn1" divId="writ1" class="btn btn-danger">第一联</button>
		<button id="btn2" divId="writ2" class="btn btn-primary">第二联</button>
		<button id="btn3" divId="writ3" class="btn btn-primary">第三联</button>
	</div>
	<input type="hidden" id="writId"
		value=<%=request.getParameter("writId")%>>
	<div style="padding: 20px;" >
		<div id="writ1" style="width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="a1"></span></h3>
			<br/>
			<h3 align="center">传 唤 证</h3>
			<br/>
			<h3 align="center">(存  根)</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="a2"></span><span class="valCell" valName="a3"></span><span class="valCell" valName="a4"></span>字[<span class="valCell" valName="a5"></span>]<span class="valCell" valName="a6"></span>号</p>
			<br/>
			<p>案件名称   <span class="valCell" valName="a7"></span></p>
			<br/>
			<p>案件编号   <span class="valCell" valName="a8"></span></p>
			<br/>
			<p>犯罪嫌疑人   <span class="valCell" valName="a9"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a10"></span></p>
			<br/>
			<p>出生日期   <span class="valCell" valName="a11"></span></p>
			<br/>
			<p>住址   <span class="valCell" valName="a12"></span></p>
			<br/>
			<p>单位及职业 <span class="valCell" valName="a13"></span></p>
			<br/>
			<p>传唤原因 <span class="valCell" valName="a14"></span></p>
			<br/>
			<p>指定时间  <span class="valCell" valName="a15"></span></p>
			<br/>
			<p>指定地点   <span class="valCell" valName="a16"></span></p>
			<br/>
			<p>批准人   <span class="valCell" valName="a17"></span></p>
			<br/>
			<p>批准时间   <span class="valCell" valName="a18"></span></p>
			<br/>
			<p>办案人   <span class="valCell" valName="a19"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a20"></span></p>
			<br/>
			<p>办案单位   <span class="valCell" valName="a21"></span></p>
			<br/>
			<p>填发时间  <span class="valCell" valName="a22"></span></p>
			<br/>
			<p>填发人   <span class="valCell" valName="a23"></span></p>
		</div>
		<div id="writ2" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h1 align="center"><span class="valCell" valName="b1"></span></h1>
			<br/>
			<h2 align="center">传 唤 证</h2>
			<br/>
			<h2 align="center">(副本)</h2>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="b2"></span><span class="valCell" valName="b3"></span><span class="valCell" valName="b4"></span>字[<span class="valCell" valName="b5"></span>]<span class="valCell" valName="b6"></span>号</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;根据《中华人民共和国刑事诉讼法》第一百一十七条之规定，兹传唤涉嫌<span class="valCell" valName="b7"></span>罪的犯罪嫌疑人<span class="valCell" valName="b8"></span>(性别<span class="valCell" valName="b9"></span>，出生日期 <span class="valCell" valName="b10"></span>，住址<span class="valCell" valName="b11"></span>)于 <span class="valCell" valName="b12"></span>到 <span class="valCell" valName="b13"></span>接受讯问。无正当理由拒不接受传唤的，可以依法拘传。</p>
			<br>
			<br>
			<p style="text-align: right;">(公安局印)</p>
			<p style="text-align: right;"> <span class="valCell" valName="b14"></span></p>
			<br/>
			<br/>
			<br/>
			<br/>
			<p>本证已于______年______月______日收到。</p>
			<br/>
			<p style="text-align: right;">被传唤人：____________(捺指印)</p>	
			<br/>
			<p>被传唤人到达时间______年______月______日______时。</p>
			<br/>
			<p style="text-align: right;">被传唤人：____________(捺指印)</p>		    
			<br/>
			<p>传唤结束时间______年______月______日______时。</p>
			<br/>
			<p style="text-align: right;">被传唤人：____________(捺指印)</p>		    
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<p>此联附卷</p>
		</div>
		<div id="writ3" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h1 align="center"><span class="valCell" valName="c1"></span></h1>
			<br/>
			<h2 align="center">传 唤 证</h2>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="c2"></span><span class="valCell" valName="c3"></span><span class="valCell" valName="c4"></span>字[<span class="valCell" valName="c5"></span>]<span class="valCell" valName="c6"></span>号</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;根据《中华人民共和国刑事诉讼法》第一百一十七条之规定，兹传唤涉嫌<span class="valCell" valName="c7"></span>罪的犯罪嫌疑人<span class="valCell" valName="c8"></span>(性别<span class="valCell" valName="c9"></span>，出生日期 <span class="valCell" valName="c10"></span>，住址 <span class="valCell" valName="c11"></span>)于 <span class="valCell" valName="c12"></span>到 <span class="valCell" valName="c13"></span>接受讯问。无正当理由拒不接受传唤的，可以依法拘传。</p>
			<br>
			<br>
			<p style="text-align: right;">(公安局印)</p>
			<p style="text-align: right;"> <span class="valCell" valName="c14"></span></p>
		</div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/showWrit/chzxs.js"></script>
</html>