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
			<h3 align="center">拘 留 证</h3>
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
			<p>拘留原因   <span class="valCell" valName="a14"></span></p>
			<br/>
			<p>批准人   <span class="valCell" valName="a15"></span></p>
			<br/>
			<p>批准时间   <span class="valCell" valName="a16"></span></p>
			<br/>
			<p>执行人   <span class="valCell" valName="a17"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a18"></span></p>
			<br/>
			<p>办案单位   <span class="valCell" valName="a19"></span></p>
			<br/>
			<p>填发时间   <span class="valCell" valName="a20"></span></p>
			<br/>
			<p>填发人   <span class="valCell" valName="a21"></span></p>
		</div>
		<div id="writ2" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="b1"></span></h3>
			<br/>
			<h3 align="center">拘 留 证</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="b2"></span><span class="valCell" valName="b3"></span><span class="valCell" valName="b4"></span>字[<span class="valCell" valName="b5"></span>]<span class="valCell" valName="b6"></span>号</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;根据《中华人民共和国刑事诉讼法》第<span class="valCell" valName="b7"></span>条之规定，兹决定对犯罪嫌疑人<span class="valCell" valName="b9"></span>(性别<span class="valCell" valName="c10"></span>，出生日期<span class="valCell" valName="b11"></span>，住址
				<span class="valCell" valName="b12"></span>)执行拘留，送<span class="valCell" valName="b13"></span>看守所羁押。</p>
			<br>
			<br>
			<p style="text-align: right;">(公安局印)</p>
			<p style="text-align: right;"><span class="valCell" valName="b14"></span></p>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<p>本证已于______年______月______日______时向我宣布。</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;被拘留人:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(捺指印)</p>
		    <br/>
		    <p>本证副本已收到，被逮捕人______已于______年______月______日______时送至我所。</p>
		    <br/>
		    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;接收民警:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(看守所印)</p>
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
			<h3 align="center"><span class="valCell" valName="c1"></span></h3>
			<br/>
			<h3 align="center">拘 留 证</h3>
			<br/>
			<h3 align="center">(副  本)</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="c2"></span><span class="valCell" valName="c3"></span><span class="valCell" valName="c4"></span>字[<span class="valCell" valName="c5"></span>]<span class="valCell" valName="c6"></span>号</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;根据《中华人民共和国刑事诉讼法》第<span class="valCell" valName="c7"></span>条之规定，兹决定对犯罪嫌疑人<span class="valCell" valName="c9"></span>(性别<span class="valCell" valName="c10"></span>，出生日期<span class="valCell" valName="c11"></span>，住址
				<span class="valCell" valName="c12"></span>)执行拘留，送<span class="valCell" valName="c13"></span>看守所羁押。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;执行拘留时间：<span class="valCell" valName="c16"></span></p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;涉嫌<span class="valCell" valName="c15"></span></p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;属于律师会见需经许可的案件：<span class="valCell" valName="c17"></span></p>
			<p style="text-align: right;">(公安局印)</p>
			<p style="text-align: right;"><span class="valCell" valName="c14"></span></p>
		</div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/showWrit/jlz.js"></script>
</html>