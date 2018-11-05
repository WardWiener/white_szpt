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
			<h3 align="center">变更羁押期限通知书</h3>
			<br/>
			<h3 align="center">（存根）</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="a2"></span><span class="valCell" valName="a3"></span><span class="valCell" valName="a4"></span>字[<span class="valCell" valName="a5"></span>]<span class="valCell" valName="a6"></span>号</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;犯罪嫌疑人（被告人）<span class="valCell" valName="a7"></span>，性别<span class="valCell" valName="a8"></span>，<span class="valCell" valName="a9"></span>生,涉嫌<span class="valCell" valName="a10"></span>(同案人<span class="valCell" valName="a11"></span>)被采取<span class="valCell" valName="a12"></span>强制措施</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;原羁押期限自<span class="valCell" valName="a13"></span>至<span class="valCell" valName="a14"></span>,羁押期限变更原因<span class="valCell" valName="a15"></span>。现羁押期限自<span class="valCell" valName="a16"></span>至<span class="valCell" valName="a17"></span>送达单位<span class="valCell" valName="a18"></span>看守所</p>
			<br/>
			<table>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;办案人：</td>
					<td><span class="valCell" valName="a19"></span></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;办案单位：</td>
					<td><span class="valCell" valName="a20"></span></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;填发人：</td>
					<td><span class="valCell" valName="a21"></span></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;填发时间：</td>
					<td><span class="valCell" valName="a22"></span></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批准人：</td>
					<td><span class="valCell" valName="a23"></span></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批准时间：</td>
					<td><span class="valCell" valName="a24"></span></td>
				</tr>
			</table>
		</div>
		<div id="writ2" style="display:none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="b1"></span></h3>
			<br/>
			<h3 align="center">变更羁押期限通知书</h3>
			<br/>
			<h3 align="center">（第一联 办案机关附卷）</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="b2"></span><span class="valCell" valName="b3"></span><span class="valCell" valName="b4"></span>字[<span class="valCell" valName="b5"></span>]<span class="valCell" valName="b6"></span>号</p>
			<br/>
			<p><span class="valCell" valName="b7"></span>看守所：</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我局/<span style="text-decoration:line-through;">院</span>正在办理的<span class="valCell" valName="b8"></span>案件，涉案犯罪嫌疑人（被告人）<span class="valCell" valName="b9"></span>(性别<span class="valCell" valName="b10"></span>，<span class="valCell" valName="b11"></span>出生），因<span class="valCell" valName="b12"></span>根据《中华人民共和国刑事诉讼法》第<span class="valCell" valName="b13"></span>条之规定，经<span class="valCell" valName="b14"></span>批准（决定），<span class="valCell" valName="b15"></span>其羁押期限。</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;现羁押期限自<span class="valCell" valName="b16"></span>至<span class="valCell" valName="b17"></span>。</p>
			<br/>
			<br/>
			<p style="text-align: right;">办案机关(印)</p>
			<p style="text-align: right;"><span class="valCell" valName="b18"></span></p>
		</div>
		<div id="writ3" style="display:none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="c1"></span></h3>
			<br/>
			<h3 align="center">变更羁押期限通知书</h3>
			<br/>
			<h3 align="center">（第二 看守所附卷）</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="c2"></span><span class="valCell" valName="c3"></span><span class="valCell" valName="c4"></span>字[<span class="valCell" valName="c5"></span>]<span class="valCell" valName="c6"></span>号</p>
			<br/>
			<p><span class="valCell" valName="c7"></span>看守所：</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我局/<span style="text-decoration:line-through;">院</span>正在办理的<span class="valCell" valName="c8"></span>案件，涉案犯罪嫌疑人（被告人）<span class="valCell" valName="c9"></span>(性别<span class="valCell" valName="c10"></span>，<span class="valCell" valName="c11"></span>出生），因<span class="valCell" valName="c12"></span>根据《中华人民共和国刑事诉讼法》第<span class="valCell" valName="c13"></span>条之规定，经<span class="valCell" valName="c14"></span>批准（决定），<span class="valCell" valName="c15"></span>其羁押期限。</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;现羁押期限自<span class="valCell" valName="c16"></span>至<span class="valCell" valName="c17"></span>。</p>
			<br/>
			<br/>
			<p style="text-align: right;">办案机关(印)</p>
			<p style="text-align: right;"><span class="valCell" valName="c18"></span></p>
		</div>
		<div id="writ4" style="display:none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="d1"></span></h3>
			<br/>
			<h3 align="center">变更羁押期限通知书</h3>
			<br/>
			<h3 align="center">（第二联 在押人员留存）</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="d2"></span><span class="valCell" valName="d3"></span><span class="valCell" valName="d4"></span>字[<span class="valCell" valName="d5"></span>]<span class="valCell" valName="d6"></span>号</p>
			<br/>
			<p><span class="valCell" valName="d7"></span>看守所：</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我局/<span style="text-decoration:line-through;">院</span>正在办理的<span class="valCell" valName="d8"></span>案件，涉案犯罪嫌疑人（被告人）<span class="valCell" valName="d9"></span>(性别<span class="valCell" valName="d10"></span>，<span class="valCell" valName="d11"></span>出生），因<span class="valCell" valName="d12"></span>根据《中华人民共和国刑事诉讼法》第<span class="valCell" valName="d13"></span>条之规定，经<span class="valCell" valName="d14"></span>批准（决定），<span class="valCell" valName="d15"></span>其羁押期限。</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;现羁押期限自<span class="valCell" valName="d16"></span>至<span class="valCell" valName="d17"></span>。</p>
			<br/>
			<br/>
			<p style="text-align: right;">办案机关(印)</p>
			<p style="text-align: right;"><span class="valCell" valName="d18"></span></p>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/showWrit/bgjyqx.js"></script>
</html>