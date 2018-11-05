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
	</div>
	<input type="hidden" id="writId"
				value=<%=request.getParameter("writId")%>>
	<div style="padding: 20px;" >
		<div id="writ1" style="width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			
			<h3 align="center"><span class="valCell" valName="a1"></span></h3>
			<br/>
			<h3 align="center">扣 押 决 定 书</h3>
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
			<p>被扣押单位   <span class="valCell" valName="a12"></span></p>
			<br/>
			<p>扣押原因   <span class="valCell" valName="a13"></span></p>
			<br/>
			<p>批准人   <span class="valCell" valName="a14"></span></p>
			<br/>
			<p>批准时间   <span class="valCell" valName="a15"></span></p>
			<br/>
			<p>办案人   <span class="valCell" valName="a16"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a17"></span></p>
			<br/>
			<p>办案单位   <span class="valCell" valName="a18"></span></p>
			<br/>
			<p>填发时间   <span class="valCell" valName="a19"></span></p>
			<br/>
			<p>填发人   <span class="valCell" valName="a20"></span></p>
		</div>
		<div id="writ2" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="b1"></span></h3>
			<br/>
			<h3 align="center">扣 押 决 定 书</h3>
			<br/>
			<h3 align="center">(副  本)</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="b2"></span><span class="valCell" valName="b3"></span><span class="valCell" valName="b4"></span>字[<span class="valCell" valName="b5"></span>]<span class="valCell" valName="b6"></span>号</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名<span class="valCell" valName="b7"></span>，性别<span class="valCell" valName="b8"></span>，出生日期<span class="valCell" valName="b9"></span>，
			身份证件种类及号码<span class="valCell" valName="b10"></span>，住址<span class="valCell" valName="b11"></span>，单位名称<span class="valCell" valName="b12"></span>，法人代表<span class="valCell" valName="b13"></span>，
			单位地址及联系方式<span class="valCell" valName="b14"></span>。
			</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我局在侦查<span class="valCell" valName="b15"></span>案件中发现你(单位)持有的下列财物、文件可用以证明犯罪嫌疑人有罪或无罪，根据《中华人民共和国刑事诉讼法》第一百三十九条之规定，现决定扣押：</p>
			<table border="2"style="width: 600px;text-align: center;">
			<tr style="text-align: center;"><td>编号</td><td>名称</td><td>数量</td><td>特征</td></tr>
			<tr>
			<td><span class="valCell" valName="b16_1"></span>&nbsp;</td>
			<td><span class="valCell" valName="b17_1"></span>&nbsp;</td>
			<td><span class="valCell" valName="b18_1"></span>&nbsp;</td>
			<td><span class="valCell" valName="b19_1"></span>&nbsp;</td>
			</tr>
			<tr>
			<td><span class="valCell" valName="b16_2"></span>&nbsp;</td>
			<td><span class="valCell" valName="b17_2"></span>&nbsp;</td>
			<td><span class="valCell" valName="b18_2"></span>&nbsp;</td>
			<td><span class="valCell" valName="b19_2"></span>&nbsp;</td>
			</tr>
			<tr>
			<td><span class="valCell" valName="b16_3"></span>&nbsp;</td>
			<td><span class="valCell" valName="b17_3"></span>&nbsp;</td>
			<td><span class="valCell" valName="b18_3"></span>&nbsp;</td>
			<td><span class="valCell" valName="b19_3"></span>&nbsp;</td>
			</tr>
			<tr>
			<td><span class="valCell" valName="b16_4"></span>&nbsp;</td>
			<td><span class="valCell" valName="b17_4"></span>&nbsp;</td>
			<td><span class="valCell" valName="b18_4"></span>&nbsp;</td>
			<td><span class="valCell" valName="b19_4"></span>&nbsp;</td>
			</tr>
			<tr>
			<td><span class="valCell" valName="b16_5"></span>&nbsp;</td>
			<td><span class="valCell" valName="b17_5"></span>&nbsp;</td>
			<td><span class="valCell" valName="b18_5"></span>&nbsp;</td>
			<td><span class="valCell" valName="b19_5"></span>&nbsp;</td>
			</tr>
			</table>
			<table style="width: 600px;text-align: right;">
			<tr><td style="width: 200px;">持有人：</td><td style="width: 200px;">见证人：</td><td style="width: 200px;">公安局(印)</td></tr>
			<tr><td><span class="valCell" valName="b20"></span></td><td><span class="valCell" valName="b21"></span></td><td><span class="valCell" valName="b22"></span></td></tr>
			</table>
		</div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/showWrit/kyjds.js"></script>
</html>