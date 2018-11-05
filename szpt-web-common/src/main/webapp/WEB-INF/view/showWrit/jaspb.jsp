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
			<h3 align="center">结案审批表</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="a101"></span><span class="valCell" valName="a102"></span><span class="valCell" valName="a103"></span>字[<span class="valCell" valName="a1"></span>]<span class="valCell" valName="a2"></span>号</p>
			<table border="2" style="width: 600px;text-align: center;">
				<tr style="text-align: center;">
					<td>案由</td>
					<td colspan="3" ><span class="valCell" valName="a3"></span></td>
					<td>发案时间</td>
					<td colspan="2"><span class="valCell" valName="a4"></span></td>
				</tr>
				<tr>
					<td>案件文号</td>
					<td colspan="6" ><span class="valCell" valName="a5"></span></td>
				</tr>
				<tr>
					<td rowspan="7">违法嫌疑人</td>
					<td>姓名</td>
					<td><span class="valCell" valName="a6"></span></td>
					<td>性别</td>
					<td><span class="valCell" valName="a7"></span></td>
					<td>民族</td>
					<td><span class="valCell" valName="a8"></span></td>
				</tr>
				<tr>
					<td>出生日期</td>
					<td colspan="2"><span class="valCell" valName="a9"></span></td>
					<td colspan="2">文化程度</td>
					<td><span class="valCell" valName="a10"></span></td>
				</tr>
				<tr>
					<td colspan="3">身份证件种类及号码</td>
					<td colspan="3"><span class="valCell" valName="a11"></span></td>
				</tr>
				<tr>
					<td colspan="2">现住址</td>
					<td colspan="4"><span class="valCell" valName="a12"></span></td>
				</tr>
				<tr>
					<td colspan="2">户籍所在地</td>
					<td colspan="4"><span class="valCell" valName="a13"></span></td>
				</tr>
				<tr>
					<td colspan="2">工作单位</td>
					<td colspan="4"><span class="valCell" valName="a14"></span></td>
				</tr>
				<tr>
					<td colspan="2">违法犯罪记录</td>
					<td colspan="4"><span class="valCell" valName="a15"></span></td>
				</tr>
				<tr>
					<td rowspan="2">违法嫌疑单位</td>
					<td>名称</td>
					<td colspan="2"><span class="valCell" valName="a16"></span></td>
					<td colspan="2">法定代表人</td>
					<td><span class="valCell" valName="a17"></span></td>
				</tr>
				<tr>
					<td>地址</td>
					<td colspan="5"><span class="valCell" valName="a18"></span></td>
				</tr>
				<tr>
					<td>同案其他人</td>
					<td colspan="6"><span class="valCell" valName="a19"></span></td>
				</tr>
				<tr>
					<td>违<br/>法<br/>事<br/>实<br/>及<br/>证<br/>据</td>
					<td colspan="6" style="width: 600px;text-align: left;"><span class="valCell" valName="a20"></span></td>
				</tr>
			</table>
		</div>
		<div id="writ2" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<br/>
			<table border="2" style="width: 600px;text-align: center;">
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;承&nbsp;&nbsp;&nbsp;&nbsp;<br/>办<br/>人<br/>意<br/>见</td>
					<td colspan="6" style="width: 600px;text-align: left;">
						<span class="valCell" valName="a21"></span>
						<br/>
						负责人：&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a22"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a33"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a23"></span>
					</td>
				</tr>
				<tr>
					<td>承<br/>办<br/>单<br/>位<br/>意<br/>见</td>
					<td colspan="6" style="width: 600px;text-align: left;">
						<span class="valCell" valName="a24"></span>
						<br/>
						负责人：&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a25"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a26"></span>
					</td>
				</tr>
				<tr>
					<td>审<br/>核<br/>部<br/>门<br/>意<br/>见</td>
					<td colspan="6" style="width: 600px;text-align: left;">
						<span class="valCell" valName="a27"></span>
						<br/>
						负责人：&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a28"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a34"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a29"></span>
					</td>
				</tr>
				<tr>
					<td>领<br/>导<br/>审<br/>批<br/>意<br/>见</td>
					<td colspan="6" style="width: 600px;text-align: left;">
						<span class="valCell" valName="a30"></span>
						<br/>
						负责人：&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a31"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a32"></span>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/showWrit/jaspb.js"></script>
</html>