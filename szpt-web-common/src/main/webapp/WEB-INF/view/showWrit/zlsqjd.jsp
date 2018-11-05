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
			<h3 align="center">责令社区戒毒决定书</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="b2"></span><span class="valCell" valName="b17"></span><span class="valCell" valName="b3"></span>字[<span class="valCell" valName="b4"></span>]<span class="valCell" valName="b5"></span>号</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;违法行为人  <span class="valCell" valName="b6"></span>  性别  <span class="valCell" valName="b7"></span>  年龄   <span class="valCell" valName="b30"></span>  出生日期   <span class="valCell" valName="b8"></span>  身份证种类及号码   <span class="valCell" valName="b9"></span>  工作单位   <span class="valCell" valName="b11"></span>  户籍所在地   <span class="valCell" valName="b22"></span>  现住址  <span class="valCell" valName="26"></span></p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;现查明<span class="valCell" valName="b10"></span></p>
			<br/>
			<p>以上事实有  <span class="valCell" valName="b13"></span>  等证据证实。</p>
			<br/>
			<p>根据</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div class="checkDiv icheckbox_square-grey" valName="b18"></div>&nbsp;&nbsp;《中华人民共和国禁毒法》第三十三条之规定，决定责令违法行为人接受社区戒毒  <span class="valCell" valName="b28"></span>  年（自  <span class="valCell" valName="b15"></span>  至  <span class="valCell" valName="b16"></span>  止）。</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;自收到本决定书之日起十五日内持本决定书到社区戒毒执行地报到，否则视为拒绝接收社区戒毒。被责令接受社区戒毒人员在社区戒毒过程中应当根据公安机关的要求，定期接受检测。</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如不服本决定，可以在接到本决定书之日起六十日内向   <span class="valCell" valName="b31"></span>  申请行政复议或者在三个月内依法向   <span class="valCell" valName="b29"></span>  人民法院提起行政诉讼。</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;执行地社区名称及地址  <span class="valCell" valName="b23"></span>
			<br/>
			<br/>
			<br/>
			<p style="text-align: right;">(公安局印)</p>
			<p style="text-align: right;"> <span class="valCell" valName="b20"></span></p>
			<br/>
			<br/>
			<br/>
			<p>社区戒毒人</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</p>
		</div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/showWrit/zlsqjd.js"></script>
</html>