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
			<h3 align="center"><span class="valCell" valName="b1"></span></h3>
			<br/>
			<h3 align="center">强制隔离戒毒决定书</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="b2"></span>禁<span class="valCell" valName="b3"></span>字[<span class="valCell" valName="b4"></span>]<span class="valCell" valName="b5"></span>号</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;违法行为人<span class="valCell" valName="b6"></span>性别<span class="valCell" valName="b7"></span>出生日期<span class="valCell" valName="b8"></span>身份证件种类及号码<span class="valCell" valName="b9"></span>现住址<span class="valCell" valName="b10"></span>工作单位<span class="valCell" valName="b11"></span>户籍所在地<span class="valCell" valName="b29"></span></p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;现查明<span class="valCell" valName="b12"></span></p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以上事实有<span class="valCell" valName="b13"></span>等证据证实。</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;根据</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div id="b16Check" class="icheckbox_square-grey"></div>&nbsp;&nbsp;《中华人民共和国禁毒法》第三十八条第<span class="valCell" valName="b16"></span>款第<span class="valCell" valName="b17"></span>项、第四十七条第一款之规定，决定对违法行为人强制隔离戒毒二年（自<span class="valCell" valName="b18"></span>至<span class="valCell" valName="b30"></span>）。</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如不服本决定，可以在接到本决定书之日起六十日内向<span class="valCell" valName="b31"></span>申请行政复议或者在三个月内依法向<span class="valCell" valName="b32">人民法院提起行政诉讼。</span></p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;强制隔离戒毒所名称及地址<span class="valCell" valName="b33"></span></p>
			<br/><br/><br/>
			<p style="text-align: right;">公安机关（印）</p>
			<p style="text-align: right;"><span class="valCell" valName="b20"></span></p>
			<br/><br/><br/>
			被强制隔离戒毒人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;接收人员<span class="valCell" valName="b25"></span><br/>
			<span class="valCell" valName="b21"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;接收单位（印）
			<br/><br/><br/>
			<p>一式两份，一份交被处罚人，一份交所属公安机关备案。治安案件有被侵害人的，复印送达被侵害人。</p>
		</div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/showWrit/qzgljd.js"></script>
</html>