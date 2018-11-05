<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/home.css">

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<body>
	
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	
	<div class="home-main-block">
		<div class="home-main">
			<ul class="main-menu">
				<li valUrl="/yayd/showYaydListPage.action"  class="modul menu-1 animated bounceIn"><a href="###"
					 class="modul"><b></b><span class="txt">一案一档</span></a></li>
				<li valUrl="/xsfxts/showXsfxtsWelcome.action" class="modul menu-2 animated bounceIn"><a href="###"
					 ><b></b><span class="txt">刑事案件<br />分析
					</span></a></li>
				<li valUrl="/zhzats/showZhzatsWelcome.action" class="modul menu-3 animated bounceIn"><a href="###"
					><b></b><span class="txt">综合治安<br />态势
					</span></a></li>
				<li valUrl="/highriskPerson/showHomePage.action" class="modul menu-4 animated bounceIn"><a href="###"
					 ><b></b><span class="txt">高危人群<br />分析
					</span></a></li>
				<li valUrl="/yryd/showYrydLstPage.action?clickOrder=1&&clickListOrder=0" class="modul menu-5 animated bounceIn"><a href="###"
					 ><b></b><span class="txt">一人一档</span></a></li>
				<!-- <li valUrl="" class="modul menu-6 animated bounceIn"><a href="###"
					><b></b><span class="txt">情报线索<br />管理
					</span></a></li> -->
				<li valUrl="/instructionManage/showInstructionListPage.action" class="modul menu-7 animated bounceIn"><a href="#"
					><b></b><span class="txt">指令管理</span></a></li>
				<li id="addOtherMenu" class="menu-more animated bounceIn"><a href="###"
					><b></b></a></li>
			</ul>
			<div class="info-box left-box">
				<div class="tt">
					<h2>指令</h2>
					<a href="###" id="moreInstruct" class="more"></a>
					<button class="btn btn-send" title="指令">
						<span class="icon-fighter-jet"></span>
					</button>
				</div>
				<ul id="instructLst" class="info-list">
				</ul>
				<ul></ul>
			</div>
			<div class="info-box right-box" style="display: none">
				<div class="tt">
					<h2>情报线索</h2>
					<a href="#" target="_blank" class="more"></a>
					<button class="btn btn-send" title="情报线索">
						<span class="icon-lightbulb"></span>
					</button>
				</div>
				<ul class="info-list">
					<li><a href=""><apan class="context">针对XXX案件嫌疑人张三开展研判...</apan><span class="time">2017-01-11
								09:41</span></a></li>
					<li><a href="">针对XXX案件嫌疑人张三开展研判...<span class="time">2017-01-11
								09:41</span></a></li>
					<li><a href="">针对XXX案件嫌疑人张三开展研判...<span class="time">2017-01-11
								09:41</span></a></li>
				</ul>
				<ul></ul>
			</div>
		</div>
		<!-------------预留--------新增的功能菜单----------->
		<div class="more-menu-group" >
			<ul id="templateMenuLst" class="main-menu">
				
			</ul>
		</div>
		<!-------------预留--------新增的功能菜单----------->
	</div>
	<ul class="info-list" style="display: none">
					<li id="oneInstruct" valId="" class="showInstructDetails"><a href="###"><span class="content">针对XXX案件嫌疑人张三开展研判...</span>&nbsp&nbsp&nbsp<span class="time">2017-01-11
								09:41</span></a></li>
	</ul>
	
	<ul id="templateMenuUl" class="main-menu" style="display: none">
		<li id="templateMenu" class="modul animated bounceIn"><a href="###" ><span
						class="txt name">功能菜单</span></a></li>
	</ul>
		<script type="text/javascript" src="<%=context%>/scripts/loginHomePage/loginPage.js"></script>

	<script>
		$(function() {

			var IEV = IEVersion();
			//如果版本是9及以下，b标签不显示
			if (IEV == "IE7" || IEV == "IE8" || IEV == "IE9") {
				$(".main-menu li").find('b').remove();
			}

			//7个导航的移入移除事件
			$('.main-menu li a').mouseenter(function() {
				$(this).find('b').css("opacity", "0.2").addClass('scale-b');
				$(this).parent().addClass('lg');
			})
			$('.main-menu li a').mouseleave(function() {
				$(this).find('b').css("opacity", 0).removeClass('scale-b');
				$(this).parent().removeClass('lg');
			})

			//    判断IE浏览器版本
			function IEVersion() {
				var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
				var isIE = userAgent.indexOf("compatible") > -1
						&& userAgent.indexOf("MSIE") > -1; //判断是否IE浏览器
				var isEdge = userAgent.indexOf("Windows NT 6.1; Trident/7.0;") > -1
						&& !isIE; //判断是否IE的Edge浏览器
				if (isIE) {
					var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
					reIE.test(userAgent);
					var fIEVersion = parseFloat(RegExp["$1"]);
					if (fIEVersion == 7) {
						return "IE7";
					} else if (fIEVersion == 8) {
						return "IE8";
					} else if (fIEVersion == 9) {
						return "IE9";
					} else if (fIEVersion == 10) {
						return "IE10";
					} else if (fIEVersion == 11) {
						return "IE11";
					} else {
						return "0"
					}//IE版本过低
				} else if (isEdge) {
					return "Edge";
				} else {
					return "-1";//非IE
				}
			}
		});
	</script>
		<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
</html>