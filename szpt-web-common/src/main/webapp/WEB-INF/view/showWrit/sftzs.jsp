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
	</div>
	<input type="hidden" id="writId"
				value=<%=request.getParameter("writId")%>>
	<div style="padding: 20px;" >
		<div id="writ1" style="width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			
			<h3 align="center"><span class="valCell" valName="a1"></span></h3>
			<br/>
			<h3 align="center">释 放 通 知 书</h3>
			<br/>
			<h3 align="center">(存  根)</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="a2"></span><span class="valCell" valName="a3"></span><span class="valCell" valName="a24"></span>字[<span class="valCell" valName="a4"></span>]<span class="valCell" valName="a5"></span>号</p>
			<br/>
			<p>案件名称   <span class="valCell" valName="a6"></span></p>
			<br/>
			<p>案件编号   <span class="valCell" valName="a7"></span></p>
			<br/>
			<p>被释放人   <span class="valCell" valName="a8"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a9"></span></p>
			<br/>
			<p>出生日期   <span class="valCell" valName="a10"></span></p>
			<br/>
			<p>住址   <span class="valCell" valName="a11"></span></p>
			<br/>
			<p><span class="valCell" valName="a13"></span>时间   <span class="valCell" valName="a14"></span></p>
			<br/>
			<p><span class="valCell" valName="a15"></span>原因   <span class="valCell" valName="a16"></span></p>
			<br/>
			<p>释放原因  <span class="valCell" valName="a17"></span></p>
			<br/>
			<p>批准人   <span class="valCell" valName="a18"></span></p>
			<br/>
			<p>批准时间   <span class="valCell" valName="a19"></span></p>
			<br/>
			<p>办案人   <span class="valCell" valName="a20"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a25"></span></p>
			<br/>
			<p>办案单位   <span class="valCell" valName="a21"></span></p>
			<br/>
			<p>填发时间   <span class="valCell" valName="a22"></span></p>
			<br/>
			<p>填发人   <span class="valCell" valName="a23"></span></p>
		</div>
		<div id="writ2" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="b1"></span></h3>
			<br/>
			<h3 align="center">释 放 通 知 书</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="b2"></span><span class="valCell" valName="b3"></span><span class="valCell" valName="b17"></span>字[<span class="valCell" valName="b4"></span>]<span class="valCell" valName="b5"></span>号</p>
			<br/>
			<p><span class="valCell" valName="b6"></span>：</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="b7"></span>(性别<span class="valCell" valName="b8"></span>，出生日期<span class="valCell" valName="b9"></span>，
			住址<span class="valCell" valName="b10"></span>)，因<span class="valCell" valName="b11"></span>，于<span class="valCell" valName="b12"></span>被执行<span class="valCell" valName="b13"></span>
			，现因<span class="valCell" valName="b14"></span>，根据《中华人民共和国刑事诉讼法》第<span class="valCell" valName="b15"></span>条之规定，予以释放。</p>
			<br/>
			<br/>
			<p style="text-align: right;">(公安局印)</p>
			<p style="text-align: right;"><span class="valCell" valName="b16"></span></p>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<table style="width: 600px;">
			<tr><td style="width: 300px;">本通知书已收到</td><td>本通知书已收到</td></tr>
			<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;接收民警：</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;检察院收件人：</td></tr>
			<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;看守所(印)</td><td></td></tr>
			<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</td></tr>
			</table>
		</div>
		<div id="writ3" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="c1"></span></h3>
			<br/>
			<h3 align="center">释 放 通 知 书</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="c2"></span><span class="valCell" valName="c3"></span><span class="valCell" valName="c17"></span>字[<span class="valCell" valName="c4"></span>]<span class="valCell" valName="c5"></span>号</p>
			<br/>
			<p><span class="valCell" valName="c6"></span>看守所：</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="c7"></span>(性别<span class="valCell" valName="c8"></span>，出生日期<span class="valCell" valName="c9"></span>，
			住址<span class="valCell" valName="c10"></span>)，因<span class="valCell" valName="c11"></span>，于<span class="valCell" valName="c12"></span>被执行<span class="valCell" valName="c13"></span>
			，现因<span class="valCell" valName="c14"></span>，根据《中华人民共和国刑事诉讼法》第<span class="valCell" valName="c15"></span>条之规定，予以释放。</p>
			<br/>
			<br/>
			<p style="text-align: right;">(公安局印)</p>
			<p style="text-align: right;"><span class="valCell" valName="c16"></span></p>	
		</div>
		<div id="writ4" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="d1"></span></h3>
			<br/>
			<h3 align="center">释 放 通 知 书</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="d2"></span><span class="valCell" valName="d3"></span><span class="valCell" valName="d17"></span>字[<span class="valCell" valName="d4"></span>]<span class="valCell" valName="d5"></span>号</p>
			<br/>
			<p><span class="valCell" valName="d6"></span>人民检察院：</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你院于<span class="valCell" valName="d7"></span>以<span class="valCell" valName="d8"></span>批准逮捕的<span class="valCell" valName="d9"></span>(性别<span class="valCell" valName="d10"></span>，出生日期<span class="valCell" valName="d11"></span>，
			住址<span class="valCell" valName="d12"></span>)已于<span class="valCell" valName="d13"></span>被执行<span class="valCell" valName="d18"></span>，
			现因<span class="valCell" valName="d14"></span>，根据《中华人民共和国刑事诉讼法》第<span class="valCell" valName="d15"></span>条之规定，予以释放。</p>
			<br/>
			<br/>
			<p style="text-align: right;">(公安局印)</p>
			<p style="text-align: right;"><span class="valCell" valName="d16"></span></p>
		</div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/showWrit/sftzs.js"></script>
</html>