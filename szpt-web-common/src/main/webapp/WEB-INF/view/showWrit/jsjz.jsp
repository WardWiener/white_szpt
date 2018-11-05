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
			<div align="center">
			<table>
			<tr><td></td><td><h3 align="center">决&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;定</h3></td><td></td></tr>
			<tr><td><h3 align="center">监视居住&nbsp;&nbsp;&nbsp;</h3></td><td></td><td><h3 align="center">&nbsp;&nbsp;&nbsp;书</h3></td></tr>
			<tr><td></td><td><h3 align="center">执行通知</h3></td><td></td></tr>
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
			<p>被监视居住人   <span class="valCell" valName="a9"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="valCell" valName="a10"></span></p>
			<br/>
			<p>出生日期   <span class="valCell" valName="a11"></span></p>
			<br/>
			<p>住址   <span class="valCell" valName="a12"></span></p>
			<br/>
			<p>监视居住原因   <span class="valCell" valName="a14"></span></p>
			<br/>
			<p>监视居住地点   <span class="valCell" valName="a15"></span></p>
			<br/>
			<p>指定居所   <span class="valCell" valName="a16"></span></p>
			<br/>
			<p>起算时间   <span class="valCell" valName="a17"></span></p>
			<br/>
			<p>执行机关   <span class="valCell" valName="a18"></span></p>
			<br/>
			<p>批准人   <span class="valCell" valName="a19"></span></p>
			<br/>
			<p>批准时间   <span class="valCell" valName="a20"></span></p>
			<br/>
			<p>办案人  <span class="valCell" valName="a21"></span>&nbsp;&nbsp;  <span class="valCell" valName="a25"></span></p>
			<br/>
			<p>填发人   <span class="valCell" valName="a23"></span></p>
			<br/>
			<p>填发时间   <span class="valCell" valName="a24"></span></p>
		</div>
		<div id="writ2" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="b1"></span></h3>
			<br/>
			<h3 align="center">监视居住决定书</h3>
			<br/>
			<h3 align="center">(副  本)</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="b2"></span><span class="valCell" valName="b3"></span><span class="valCell" valName="b4"></span>字[<span class="valCell" valName="b5"></span>]<span class="valCell" valName="b6"></span>号</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;犯罪嫌疑人<span class="valCell" valName="b7"></span>，性别<span class="valCell" valName="b8"></span>，出生日期<span class="valCell" valName="b9"></span>，
			住址<span class="valCell" valName="b10"></span>。
			</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我局正在侦查<span class="valCell" valName="b17"></span>案，因<span class="valCell" valName="b12"></span>，根据《中华人民共和国刑事诉讼法》第<span class="valCell" valName="b13"></span>条之规定，决定在<span class="valCell" valName="b14"></span>对犯罪嫌疑人<span class="valCell" valName="b18"></span>，由<span class="valCell" valName="b19"></span>负责执行，监视居住期限从<span class="valCell" valName="b15"></span>起算</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在监视居住期间，被监视居住人应当遵守一下规定：</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一、未经执行机关批准不得离开执行监视居住的处所；二、未经执行机关批准不得会见他人或者通信；三、在传讯的时候及时到案；四、不得以任何刑事干扰证人作证；五、不得毁灭、伪造证据或者串供；六、将护照等出入境证件、身份证、驾驶证件教执行机关保存。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果被监视居住人违反以上规定，情节严重的，可以予以逮捕；需要予以逮捕的，可以先行拘留。</p>
			<p style="text-align: right;">(公安局印)</p>
			<p style="text-align: right;"><span class="valCell" valName="b16"></span></p>
			<br/><br/><br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本决定书已收到。</p><br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;被监视居住人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(捺指印)</p><br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</p><br/>
			<br/><br/><br/>
			<p>此联附卷</p>
		</div>
		<div id="writ3" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="c1"></span></h3>
			<br/>
			<h3 align="center">监视居住决定书</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="c2"></span><span class="valCell" valName="c3"></span><span class="valCell" valName="c4"></span>字[<span class="valCell" valName="c5"></span>]<span class="valCell" valName="c6"></span>号</p>
			<br/>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;犯罪嫌疑人<span class="valCell" valName="c7"></span>，性别<span class="valCell" valName="c8"></span>，出生日期<span class="valCell" valName="c9"></span>，
			住址<span class="valCell" valName="c10"></span>。
			</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我局正在侦查<span class="valCell" valName="c12"></span>案，因<span class="valCell" valName="c13"></span>，根据《中华人民共和国刑事诉讼法》第<span class="valCell" valName="c14"></span>条之规定，决定在<span class="valCell" valName="c15"></span>对犯罪嫌疑人<span class="valCell" valName="c19"></span>，由<span class="valCell" valName="c16"></span>负责执行，监视居住期限从<span class="valCell" valName="c17"></span>起算。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在监视居住期间，被监视居住人应当遵守以下规定：</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一、未经执行机关批准不得离开执行监视居住得处所；</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;二、未经执行机关批准不得会见他人或者通信；</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;三、在传讯得时候及时到案；</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;四、不得以任何形式干扰证人作证；</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;五、不得毁灭、伪造证据或者串供；</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;六、将护照等出入境证件、身份证件、驾驶证件交执行机关保存。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果被监视居住人违反以上规定，情节严重的，可以予以逮捕；需要予以逮捕的，可以先行拘留。</p>
			<br>
			<br>
			<p style="text-align: right;">(公安局印)</p>
			<p style="text-align: right;"><span class="valCell" valName="c18"></span></p>
			<br>
			<br>
			<br>
			<p>此联交被监视居住人</p>
		</div>
		<div id="writ4" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center"><span class="valCell" valName="d1"></span></h3>
			<br/>
			<h3 align="center">监视居住执行通知书</h3>
			<br/>
			<p style="text-align: right;"><span class="valCell" valName="d2"></span><span class="valCell" valName="d3"></span><span class="valCell" valName="d4"></span>字[<span class="valCell" valName="d5"></span>]<span class="valCell" valName="d6"></span>号</p>
			<br/>
			<p><span class="valCell" valName="d7"></span>：</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;因<span class="valCell" valName="d8"></span>，我局决定在<span class="valCell" valName="d9"></span>对犯罪嫌疑人<span class="valCell" valName="d10"></span>（性别<span class="valCell" valName="d11"></span>，出生日期<span class="valCell" valName="d12"></span>，住址<span class="valCell" valName="d13"></span>）
			<span class="valCell" valName="d19"></span>，交由你单位执行，监视居住期限从<span class="valCell" valName="d14"></span>起算。
			</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在监视居住期间，被监视居住人应当遵守以下规定：</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一、未经执行机关批准不得离开执行监视居住得处所；</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;二、未经执行机关批准不得会见他人或者通信；</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;三、在传讯得时候及时到案；</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;四、不得以任何形式干扰证人作证；</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;五、不得毁灭、伪造证据或者串供；</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;六、将护照等出入境证件、身份证件、驾驶证件交执行机关保存。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果被监视居住人违反以上规定，情节严重的，可以予以逮捕；需要予以逮捕的，可以先行拘留。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;属于律师会见需经许可的案件：<span class="valCell" valName="d17"></span></p>
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
			<p>此联交执行机关</p>
		</div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/showWrit/jsjz.js"></script>
</html>