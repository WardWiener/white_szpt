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
			<h3 align="center"><span class="valCell" valName="b1"></span></h3>
			<br/>
			<h3 align="center">行政处罚决定书</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="b2"></span><span class="valCell" valName="b7"></span><span class="valCell" valName="b3"></span>字[<span class="valCell" valName="b4"></span>]<span class="valCell" valName="b5"></span>号</p>
			<br/>
			<p>决定书正文载明一下内容：</p>
			<br/>
			<p>1.违法行为人的基本情况；</p>
			<br/>
			<p>2.违法事实和证据以及从重、从轻等情节；</p>
			<br/>
			<p>3.法律依据；</p>
			<br/>
			<p>4.触发种类及幅度；</p>
			<br/>
			<p>5.执行方式及期限；</p>
			<br/>
			<p>6.对涉案财物的处理情况及对被处罚人的其他处理情况；</p>
			<br/>
			<p>7.不副本决定的救济途径；</p>
			<br/>
			<p>8.附没收违法所得、非法财物清单及收缴/追缴物品清单。</p>
			<br/><br/><br/>
			<p style="text-align: right;">公安机关名称、印章及决定日期</p>
			<p style="text-align: right;"><span class="valCell" valName="b10"></span></p>
			<br/><br/><br/>
			<p>一式三份，被处罚人和执行单位各一份，一份附卷。治安案件有被害人的，复印送达被侵害人</p>
		</div>
		<div id="writ2" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="a1"></span></h3>
			<br/>
			<h3 align="center">行政处罚决定书</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="a2"></span><span class="valCell" valName="a7"></span><span class="valCell" valName="a3"></span>字[<span class="valCell" valName="a4"></span>]<span class="valCell" valName="a5"></span>号</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;违法行为人<span class="valCell" valName="a6"></span></p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;现查明<span class="valCell" valName="a9"></span>，以上事实有<span class="valCell" valName="a11"></span>等证据证实。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;根据<span class="valCell" valName="a14"></span>之规定，现决定<span class="valCell" valName="a17">。</span></p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;执行方式和期限<span class="valCell" valName="a18"></span>。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;逾期不缴纳罚款的，每日按罚款数额的百分之三加处罚款，加处罚款的数额不超过罚款本数。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如不服本决定，可以在收到本决定书之日起六十日内向<span class="valCell" valName="a19"></span>申请行政复议或者在六个月内依法向<span class="valCell" valName="a20">人民法院提起行政诉讼。</span></p>
			<br/>
			<p>附：<span class="valCell" valName="a21"></span>清单共<span class="valCell" valName="a22"></span>份。</p>
			<br/><br/>
			<p style="text-align: right;">公安机关（印）</p>
			<p style="text-align: right;"><span class="valCell" valName="a23"></span></p>
			<br/>
			<br/>
			<p>行政处罚决定书已向我宣告并送达。</p>
			<p>被处罚人</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</p>
			<br/>
			<br/>
			<p>此联附卷</p>
		</div>
		<div id="writ3" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<br/>
			<h2 align="center">没收违法所得、非法财物清单</h2>
			<br/>
			<table border="2"style="width: 600px;text-align: center;">
				<tr style="text-align: center;">
					<td>编号</td>
					<td>名称</td>
					<td>数量</td>
					<td>特征</td>
					<td>备注</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="c1_1">></span>&nbsp;</td>
					<td><span class="valCell" valName="c2_1">></span>&nbsp;</td>
					<td><span class="valCell" valName="c3_1">></span>&nbsp;</td>
					<td><span class="valCell" valName="c4_1">></span>&nbsp;</td>
					<td><span class="valCell" valName="c5_1">></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="c1_2">></span>&nbsp;</td>
					<td><span class="valCell" valName="c2_2">></span>&nbsp;</td>
					<td><span class="valCell" valName="c3_2">></span>&nbsp;</td>
					<td><span class="valCell" valName="c4_2">></span>&nbsp;</td>
					<td><span class="valCell" valName="c5_2">></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="c1_3">></span>&nbsp;</td>
					<td><span class="valCell" valName="c2_3">></span>&nbsp;</td>
					<td><span class="valCell" valName="c3_3">></span>&nbsp;</td>
					<td><span class="valCell" valName="c4_3">></span>&nbsp;</td>
					<td><span class="valCell" valName="c5_3">></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="c1_4">></span>&nbsp;</td>
					<td><span class="valCell" valName="c2_4">></span>&nbsp;</td>
					<td><span class="valCell" valName="c3_4">></span>&nbsp;</td>
					<td><span class="valCell" valName="c4_4">></span>&nbsp;</td>
					<td><span class="valCell" valName="c5_4">></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="c1_5">></span>&nbsp;</td>
					<td><span class="valCell" valName="c2_5">></span>&nbsp;</td>
					<td><span class="valCell" valName="c3_5">></span>&nbsp;</td>
					<td><span class="valCell" valName="c4_5">></span>&nbsp;</td>
					<td><span class="valCell" valName="c5_5">></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="c1_6">></span>&nbsp;</td>
					<td><span class="valCell" valName="c2_6">></span>&nbsp;</td>
					<td><span class="valCell" valName="c3_6">></span>&nbsp;</td>
					<td><span class="valCell" valName="c4_6">></span>&nbsp;</td>
					<td><span class="valCell" valName="c5_6">></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="c1_7">></span>&nbsp;</td>
					<td><span class="valCell" valName="c2_7">></span>&nbsp;</td>
					<td><span class="valCell" valName="c3_7">></span>&nbsp;</td>
					<td><span class="valCell" valName="c4_7">></span>&nbsp;</td>
					<td><span class="valCell" valName="c5_7">></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="c1_8">></span>&nbsp;</td>
					<td><span class="valCell" valName="c2_8">></span>&nbsp;</td>
					<td><span class="valCell" valName="c3_8">></span>&nbsp;</td>
					<td><span class="valCell" valName="c4_8">></span>&nbsp;</td>
					<td><span class="valCell" valName="c5_8">></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="c1_9">></span>&nbsp;</td>
					<td><span class="valCell" valName="c2_9">></span>&nbsp;</td>
					<td><span class="valCell" valName="c3_9">></span>&nbsp;</td>
					<td><span class="valCell" valName="c4_9">></span>&nbsp;</td>
					<td><span class="valCell" valName="c5_9">></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="c1_10">></span>&nbsp;</td>
					<td><span class="valCell" valName="c2_10">></span>&nbsp;</td>
					<td><span class="valCell" valName="c3_10">></span>&nbsp;</td>
					<td><span class="valCell" valName="c4_10">></span>&nbsp;</td>
					<td><span class="valCell" valName="c5_10">></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="c1_11">></span>&nbsp;</td>
					<td><span class="valCell" valName="c2_11">></span>&nbsp;</td>
					<td><span class="valCell" valName="c3_11">></span>&nbsp;</td>
					<td><span class="valCell" valName="c4_11">></span>&nbsp;</td>
					<td><span class="valCell" valName="c5_11">></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="c1_12">></span>&nbsp;</td>
					<td><span class="valCell" valName="c2_12">></span>&nbsp;</td>
					<td><span class="valCell" valName="c3_12">></span>&nbsp;</td>
					<td><span class="valCell" valName="c4_12">></span>&nbsp;</td>
					<td><span class="valCell" valName="c5_12">></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="c1_13">></span>&nbsp;</td>
					<td><span class="valCell" valName="c2_13">></span>&nbsp;</td>
					<td><span class="valCell" valName="c3_13">></span>&nbsp;</td>
					<td><span class="valCell" valName="c4_13">></span>&nbsp;</td>
					<td><span class="valCell" valName="c5_13">></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="c1_14">></span>&nbsp;</td>
					<td><span class="valCell" valName="c2_14">></span>&nbsp;</td>
					<td><span class="valCell" valName="c3_14">></span>&nbsp;</td>
					<td><span class="valCell" valName="c4_14">></span>&nbsp;</td>
					<td><span class="valCell" valName="c5_14">></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="c1_15">></span>&nbsp;</td>
					<td><span class="valCell" valName="c2_15">></span>&nbsp;</td>
					<td><span class="valCell" valName="c3_15">></span>&nbsp;</td>
					<td><span class="valCell" valName="c4_15">></span>&nbsp;</td>
					<td><span class="valCell" valName="c5_15">></span>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">
						被处罚人&nbsp;&nbsp;<span class="valCell" valName="c6_1"></span>
						<br/>
						<span class="valCell" valName="c6_2"></span>
					</td>
					<td colspan="2">
						保管人&nbsp;&nbsp;<span class="valCell" valName="c7_1"></span>
						<br/>
						<span class="valCell" valName="c7_2"></span>
					</td>
					<td>
						办案民警&nbsp;&nbsp;<span class="valCell" valName="c7_1"></span>
						<br/>
						<span class="valCell" valName="c7_2"></span>
					</td>
				</tr>
			</table>
			<p>一式三份，一份交被处罚人，一份交保管人，一份附卷。</p>
		</div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/showWrit/xzcfjds.js"></script>
</html>