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
	</div>
	<input type="hidden" id="writId"
		value=<%=request.getParameter("writId")%>>
	<div style="padding: 20px;" >
		<div id="writ1" style="width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center">受案登记表</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="a33"></span><span class="valCell" valName="a34"></span><span class="valCell" valName="a35"></span>字[<span class="valCell" valName="a36"></span>]<span class="valCell" valName="a37"></span>号</p>
			<br/>
			（受案单位名称和印章）
			<table border="2" style="width: 600px;text-align: center;">
				<tr style="text-align: center;">
					<td colspan="2">案件来源</td>
					<td colspan="5" style="text-align: left;">
						<div class="a9Check check1 icheckbox_square-grey"></div>&nbsp;&nbsp;110指令
						<div class="a9Check check2 icheckbox_square-grey"></div>&nbsp;&nbsp;工作中发现
						<div class="a9Check check3 icheckbox_square-grey"></div>&nbsp;&nbsp;报案
						<div class="a9Check check4 icheckbox_square-grey"></div>&nbsp;&nbsp;投案
						<div class="a9Check check5 icheckbox_square-grey"></div>&nbsp;&nbsp;移送
						<div class="a9Check check6 icheckbox_square-grey"></div>&nbsp;&nbsp;扭送
						<div class="a9Check check7 icheckbox_square-grey"></div>&nbsp;&nbsp;其他
					</td>
				</tr>
				<tr>
					<td rowspan="4">报<br/>案<br/>人</td>
					<td>姓名</td>
					<td colspan="2"><span class="valCell" valName="a2"></span>&nbsp;</td>
					<td>性别<span class="valCell" valName="a3"></span>&nbsp;</td>
					<td colspan="2">出生日期<span class="valCell" valName="a4"></span>&nbsp;</td>
				</tr>
				<tr>
					<td>身份证件种类</td>
					<td colspan="2"><span class="valCell" valName="a40"></span>&nbsp;</td>
					<td>证件号码</td>
					<td colspan="2"><span class="valCell" valName="a6"></span>&nbsp;</td>
				</tr>
				<tr>
					<td>工作单位</td>
					<td colspan="2"><span class="valCell" valName="a7"></span>&nbsp;</td>
					<td>联系方式</td>
					<td colspan="2"><span class="valCell" valName="a5"></span>&nbsp;</td>
				</tr>
				<tr>
					<td>现住址</td>
					<td colspan="5"><span class="valCell" valName="a8"></span>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">移送单位</td>
					<td><span class="valCell" valName="a30"></span>&nbsp;</td>
					<td>移送人</td>
					<td><span class="valCell" valName="a31"></span>&nbsp;</td>
					<td>联系方式</td>
					<td><span class="valCell" valName="a32"></span>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">接报民警</td>
					<td><span class="valCell" valName="a10"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a11"></span></td>
					<td>接报时间</td>
					<td><span class="valCell" valName="a12"></span>&nbsp;</td>
					<td>接报地点</td>
					<td><span class="valCell" valName="a13"></span>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="7" style="text-align: left;">
						简要案情或者报案记录（发案时间、地点、简要过程、涉案人基本情况、受害情况等）以及是否接收证据：
						<br/>
						<span class="valCell" valName="a16"></span>&nbsp;
						<br/>
						<span class="valCell" valName="a17"></span>&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2">受案意见</td>
					<td colspan="5" style="text-align: left;">
						<div class="a18Check check1 icheckbox_square-grey"></div>&nbsp;&nbsp;属本单位管辖的行政案件，建议及时调查处理
						<br/>
						<div class="a18Check check2 icheckbox_square-grey"></div>&nbsp;&nbsp;属本单位管辖的刑事案件，建议及时立案侦查
						<br/>
						<div class="a18Check check3 icheckbox_square-grey"></div>&nbsp;&nbsp;不属于本单位管辖，建议移送<span class="valCell" valName="a38"></span>处理
						<br/>
						<div class="a18Check check4 icheckbox_square-grey"></div>&nbsp;&nbsp;不属于公安机关职责范围，不予调查处理并当场书面告知当事人
						<br/>
						<div class="a18Check check5 icheckbox_square-grey"></div>&nbsp;&nbsp;其他<span class="valCell" valName="a39"></span>
						<br/>
						<span>受案民警：<span class="valCell" valName="a19"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a20"></span></span><span style="text-align: right;" class="valCell" valName=""></span>
					</td>
				</tr>
				<tr>
					<td colspan="2">受案审批</td>
					<td colspan="5" style="text-align: left;">
						<span class="valCell" valName="a27"></span><br/><br/><br/>
						<span>受案部门负责人：<span class="valCell" valName="a46"></span></span><span style="text-align: right;" class="valCell" valName="a47"></span>
					</td>
				</tr>
			</table>
			<p>一式两份，一份附卷，一份存根</p>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/showWrit/sadjb.js"></script>
</html>