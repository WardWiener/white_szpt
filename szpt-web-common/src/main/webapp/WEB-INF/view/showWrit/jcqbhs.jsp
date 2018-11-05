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
		<button id="btn4" divId="writ4" class="btn btn-primary">第四联</button>
		<button id="btn5" divId="writ5" class="btn btn-primary">第五联</button>
	</div>
	<input type="hidden" id="writId"
				value=<%=request.getParameter("writId")%>>
	<div style="padding: 20px;" >
		<div id="writ1" style="width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			
			<h3 align="center"><span class="valCell" valName="a1"></span></h3>
			<br/>
			<div align="center">
			<table>
			<tr><td></td><td><h3 align="center">决&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;定</h3></td><td></td></tr>
			<tr><td><h3 align="center">解除取保候审&nbsp;&nbsp;&nbsp;</h3></td><td></td><td><h3 align="center">&nbsp;&nbsp;&nbsp;书</h3></td></tr>
			<tr><td></td><td><h3 align="center">通&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;知</h3></td><td></td></tr>
			</table>
			</div>
			<br/>
			<h3 align="center">(存  根)</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="a2"></span><span class="valCell" valName="a3"></span><span class="valCell" valName="a4"></span>字[<span class="valCell" valName="a5"></span>]<span class="valCell" valName="a6"></span>号</p>
			<br/>
			<p>案件名称   <span class="valCell" valName="a7"></span></p>
			<br/>
			<p>案件编号   <span class="valCell" valName="a8"></span></p>
			<br/>
			<p>被取保候审人   <span class="valCell" valName="a9"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a10"></span></p>
			<br/>
			<p>出生日期   <span class="valCell" valName="a11"></span></p>
			<br/>
			<p>住址   <span class="valCell" valName="a12"></span></p>
			<br/>
			<p>取保方式   <span class="valCell" valName="a13"></span></p>
			<br/>
			<p>执行机关   <span class="valCell" valName="a14"></span></p>
			<br/>
			<p>取保候审决定时间   <span class="valCell" valName="a15"></span></p>
			<br/>
			<p>解除原因   <span class="valCell" valName="a16"></span></p>
			<br/>
			<p>批准人   <span class="valCell" valName="a17"></span></p>
			<br/>
			<p>批准时间   <span class="valCell" valName="a18"></span></p>
			<br/>
			<p>办案人  <span class="valCell" valName="a19"></span>&nbsp;&nbsp;<span class="valCell" valName="a23"></span></p>
			<br/>
			<p>办案单位  <span class="valCell" valName="a20"></span></p>
			<br/>
			<p>填发时间   <span class="valCell" valName="a21"></span></p>
			<br/>
			<p>填发人   <span class="valCell" valName="a22"></span></p>
		</div>
		<div id="writ2" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="b1"></span></h3>
			<br/>
			<h3 align="center">解除取保候审决定书</h3>
			<br/>
			<h3 align="center">(副  本)</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="b2"></span><span class="valCell" valName="b3"></span><span class="valCell" valName="b4"></span>字[<span class="valCell" valName="b5"></span>]<span class="valCell" valName="b6"></span>号</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;被取保候审人<span class="valCell" valName="b7"></span>，性别<span class="valCell" valName="b12"></span>，出生日期<span class="valCell" valName="b13"></span>，
			住址<span class="valCell" valName="b14"></span>。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我局于<span class="valCell" valName="b8"></span>决定对其执行取保候审，现因<span class="valCell" valName="b10"></span>，根据《中华人民共和国刑事诉讼法》第七十七条第二款之规定，决定予以解除。</p>
			<br/><br/>
			<p style="text-align: right;">(公安局印)</p>
			<p style="text-align: right;"><span class="valCell" valName="b11"></span></p>
			<br/><br/><br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本决定书已收到。</p><br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;被取保候审人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(捺指印)</p><br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</p><br/>
			<br/><br/><br/>
			<p>此联附卷</p>
		</div>
		<div id="writ3" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="c1"></span></h3>
			<br/>
			<h3 align="center">解除取保候审决定书</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="c2"></span><span class="valCell" valName="c3"></span><span class="valCell" valName="c4"></span>字[<span class="valCell" valName="c5"></span>]<span class="valCell" valName="c6"></span>号</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;被取保候审人<span class="valCell" valName="c7"></span>，性别<span class="valCell" valName="c8"></span>，出生日期<span class="valCell" valName="c9"></span>，
			住址<span class="valCell" valName="c10"></span>。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我局于<span class="valCell" valName="c12"></span>决定对其执行取保候审，现因<span class="valCell" valName="c14"></span>，根据《中华人民共和国刑事诉讼法》第七十七条第二款之规定，决定予以解除。</p>
			<br/><br/>
			<p style="text-align: right;">(公安局印)</p>
			<p style="text-align: right;"><span class="valCell" valName="c15"></span></p>
			<br>
			<br>
			<br>
			<p>此联交被取保候审人</p>
		</div>
		<div id="writ4" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="d1"></span></h3>
			<br/>
			<h3 align="center">解除取保候审通知书</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="d2"></span><span class="valCell" valName="d3"></span><span class="valCell" valName="d4"></span>字[<span class="valCell" valName="d5"></span>]<span class="valCell" valName="d6"></span>号</p>
			<br/>
			<p><span class="valCell" valName="e7"></span>：</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我局于<span class="valCell" valName="d12"></span>决定对犯罪嫌疑人<span class="valCell" valName="d7"></span>（性别<span class="valCell" valName="d8"></span>，出生日期<span class="valCell" valName="d9"></span>，住址<span class="valCell" valName="d10"></span>）取保候审，现因<span class="valCell" valName="d14"></span>，根据《中华人民共和国刑事诉讼法》第七十七条第二款之规定，决定解除对其取保候审，并解除你的保证义务。
			<br/>
			<br/>
			<br/>
			<p style="text-align: right;">(公安局印)</p>
			<p style="text-align: right;"><span class="valCell" valName="d15"></span></p>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<p>此联交保证人</p>
		</div>
		<div id="writ5" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="e1"></span></h3>
			<br/>
			<h3 align="center">解除取保候审通知书</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="e2"></span><span class="valCell" valName="e3"></span><span class="valCell" valName="e4"></span>字[<span class="valCell" valName="e5"></span>]<span class="valCell" valName="e6"></span>号</p>
			<br/>
			<p><span class="valCell" valName="b7"></span>：</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我局于<span class="valCell" valName="e8"></span>决定对犯罪嫌疑人<span class="valCell" valName="e9"></span>（性别<span class="valCell" valName="e10"></span>，出生日期<span class="valCell" valName="e11"></span>，住址<span class="valCell" valName="e12"></span>）取保候审，现因<span class="valCell" valName="e13"></span>，根据《中华人民共和国刑事诉讼法》第七十七条第二款之规定，决定予以解除。
			<br/>
			<br/>
			<br/>
			<p style="text-align: right;">(公安局印)</p>
			<p style="text-align: right;"><span class="valCell" valName="e14"></span></p>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<p>此联交执行机关</p>
		</div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/showWrit/jcqbhs.js"></script>
</html>