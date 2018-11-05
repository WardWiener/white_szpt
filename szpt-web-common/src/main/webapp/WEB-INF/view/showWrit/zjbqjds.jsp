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
			<h3 align="center">证据保全决定书</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="a2"></span><span class="valCell" valName="a3"></span><span class="valCell" valName="a4"></span>字[<span class="valCell" valName="a5"></span>]<span class="valCell" valName="a6"></span>号</p>
			<br/>
			<p>当事人姓名及其身份证件种类，号码，或者单位名称及其法定代表人姓名<span class="valCell" valName="a7"></span></p>
			<br/>
			<p>现住址及联系方式<span class="valCell" valName="a8"></span></p>
			<br/>
			<p>因调查<span class="valCell" valName="a9"></span>一案，</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;<div id="a14Check" class="icheckbox_square-grey"></div>&nbsp;&nbsp;根据《<span class="valCell" valName="a10"></span>》第<span class="valCell" valName="a11"></span>条第<span class="valCell" valName="a12"></span>款第<span class="valCell" valName="a13"></span>项之规定，</p>
			<br/>
			<p><div id="a15Check" class="icheckbox_square-grey"></div>&nbsp;&nbsp;决定对证据保全清单中的物品进行扣押、扣留/延长扣押、扣留<span class="valCell" valName="a16"></span>日（自<span class="valCell" valName="a17"></span>至<span class="valCell" valName="a18"></span>）。</p>
			<br/>
			<p><div id="a32Check" class="icheckbox_square-grey"></div>&nbsp;&nbsp;决定对证据保全清单中的场所、设施、财物予以查封/延长查封<span class="valCell" valName="a19"></span>日,（自<span class="valCell" valName="a20"></span>至<span class="valCell" valName="a21"></span>）。</p>
			<br/>
			<p><div id="a22Check" class="icheckbox_square-grey"></div>&nbsp;&nbsp;根据《中华人民共和国刑事处罚法》第三十七条第二款之规定，</p>
			<br/>
			<p><div id="a23Check" class="icheckbox_square-grey"></div>&nbsp;&nbsp;决定对证据保全清单中的物品予以先行登记保存<span class="valCell" valName="a24"></span>日，（自<span class="valCell" valName="a25"></span>至<span class="valCell" valName="a26"></span>），保存地点为<span class="valCell" valName="a27"></span>，在此期间，当事人或者有关人员不得销毁或者转移证据。</p>
			<br/>
			<p><div id="a33Check" class="icheckbox_square-grey"></div>&nbsp;&nbsp;决定对下列物品进行抽样取证。</p>
			<br/>
			<p><div id="a28Check" class="icheckbox_square-grey"></div>&nbsp;&nbsp;根据《中华人民共和国治安管理处罚法》第八十九条第一款之规定，决定对证据保全清单中的物品予以登记。</p>
			<br/>
			<p>如不服本决定，可以在收到本决定书之日起六十日内向<span class="valCell" valName="a29"></span>申请行政复议或者在六个月内依法向<span class="valCell" valName="a30"></span>人民法院提起行政诉讼。</p>
			<br/>
			<p>附：证据保全清单</p>
			<br/><br/>
			<p style="text-align: right;">公安机关（印）</p>
			<p style="text-align: right;"><span class="valCell" valName="a31"></span></p>
			<br/>
			<br/>
			<p>行政处罚决定书已向我宣告并送达。</p>
			<p>被处罚人</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</p>
			<br/>
			<br/>
			<p>当事人</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</p>
			<br/>
			<br/>
			<br/>
			<p>一式两份，一份交当事人，一份附卷</p>
		</div>
		<div id="writ2" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<br/>
			<h2 align="center">证据保全清单</h2>
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
					<td><span class="valCell" valName="b1"></span>&nbsp;</td>
					<td><span class="valCell" valName="b16"></span>&nbsp;</td>
					<td><span class="valCell" valName="b31"></span>&nbsp;</td>
					<td><span class="valCell" valName="b46"></span>&nbsp;</td>
					<td><span class="valCell" valName="b61"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b2"></span>&nbsp;</td>
					<td><span class="valCell" valName="b17"></span>&nbsp;</td>
					<td><span class="valCell" valName="b32"></span>&nbsp;</td>
					<td><span class="valCell" valName="b47"></span>&nbsp;</td>
					<td><span class="valCell" valName="b62"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b3"></span>&nbsp;</td>
					<td><span class="valCell" valName="b18"></span>&nbsp;</td>
					<td><span class="valCell" valName="b33"></span>&nbsp;</td>
					<td><span class="valCell" valName="b48"></span>&nbsp;</td>
					<td><span class="valCell" valName="b63"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b4"></span>&nbsp;</td>
					<td><span class="valCell" valName="b19"></span>&nbsp;</td>
					<td><span class="valCell" valName="b34"></span>&nbsp;</td>
					<td><span class="valCell" valName="b49"></span>&nbsp;</td>
					<td><span class="valCell" valName="b64"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b5"></span>&nbsp;</td>
					<td><span class="valCell" valName="b20"></span>&nbsp;</td>
					<td><span class="valCell" valName="b35"></span>&nbsp;</td>
					<td><span class="valCell" valName="b50"></span>&nbsp;</td>
					<td><span class="valCell" valName="b65"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b6"></span>&nbsp;</td>
					<td><span class="valCell" valName="b21"></span>&nbsp;</td>
					<td><span class="valCell" valName="b36"></span>&nbsp;</td>
					<td><span class="valCell" valName="b51"></span>&nbsp;</td>
					<td><span class="valCell" valName="b66"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b7"></span>&nbsp;</td>
					<td><span class="valCell" valName="b22"></span>&nbsp;</td>
					<td><span class="valCell" valName="b37"></span>&nbsp;</td>
					<td><span class="valCell" valName="b52"></span>&nbsp;</td>
					<td><span class="valCell" valName="b67"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b8"></span>&nbsp;</td>
					<td><span class="valCell" valName="b23"></span>&nbsp;</td>
					<td><span class="valCell" valName="b38"></span>&nbsp;</td>
					<td><span class="valCell" valName="b53"></span>&nbsp;</td>
					<td><span class="valCell" valName="b68"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b9"></span>&nbsp;</td>
					<td><span class="valCell" valName="b24"></span>&nbsp;</td>
					<td><span class="valCell" valName="b39"></span>&nbsp;</td>
					<td><span class="valCell" valName="b54"></span>&nbsp;</td>
					<td><span class="valCell" valName="b69"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b10"></span>&nbsp;</td>
					<td><span class="valCell" valName="b25"></span>&nbsp;</td>
					<td><span class="valCell" valName="b40"></span>&nbsp;</td>
					<td><span class="valCell" valName="b55"></span>&nbsp;</td>
					<td><span class="valCell" valName="b70"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b11"></span>&nbsp;</td>
					<td><span class="valCell" valName="b26"></span>&nbsp;</td>
					<td><span class="valCell" valName="b41"></span>&nbsp;</td>
					<td><span class="valCell" valName="b56"></span>&nbsp;</td>
					<td><span class="valCell" valName="b71"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b12"></span>&nbsp;</td>
					<td><span class="valCell" valName="b27"></span>&nbsp;</td>
					<td><span class="valCell" valName="b42"></span>&nbsp;</td>
					<td><span class="valCell" valName="b57"></span>&nbsp;</td>
					<td><span class="valCell" valName="b72"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b13"></span>&nbsp;</td>
					<td><span class="valCell" valName="b28"></span>&nbsp;</td>
					<td><span class="valCell" valName="b43"></span>&nbsp;</td>
					<td><span class="valCell" valName="b58"></span>&nbsp;</td>
					<td><span class="valCell" valName="b73"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b14"></span>&nbsp;</td>
					<td><span class="valCell" valName="b29"></span>&nbsp;</td>
					<td><span class="valCell" valName="b44"></span>&nbsp;</td>
					<td><span class="valCell" valName="b59"></span>&nbsp;</td>
					<td><span class="valCell" valName="b74"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b15"></span>&nbsp;</td>
					<td><span class="valCell" valName="b30"></span>&nbsp;</td>
					<td><span class="valCell" valName="b45"></span>&nbsp;</td>
					<td><span class="valCell" valName="b60"></span>&nbsp;</td>
					<td><span class="valCell" valName="b75"></span>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">证据持有人：<span class="valCell" valName="b76"></span><br/><span class="valCell" valName="b77"></span></td>
					<td colspan="2">保管人：<span class="valCell" valName="b78"></span><br/><span class="valCell" valName="b79"></span></td>
					<td>办案民警：<span class="valCell" valName="b80"></span><br/>办案单位（印）<br/><span class="valCell" valName="b81"></span></td>
				</tr>
			</table>
			<p>一式三份，一份交被处罚人，一份交保管人，一份附卷。</p>
		</div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/showWrit/zjbqjds.js"></script>
</html>