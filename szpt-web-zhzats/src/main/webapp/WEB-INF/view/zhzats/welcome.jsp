<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/highcharts/js/highcharts-more.js"></script>
<%@include file="/WEB-INF/view/instruction/common/constant.jsp"%>
<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript" src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript" src="<%=context%>/custom/arcgis/baseMap.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/searchTime.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/analyzeDataInit.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/instruction/instructionList.js"></script>
<script>
$(document).ready(function() {	
	
	

	

});
</script>
</head>

<style>
.select2-container{

}
</style>

<body>
<%@include file="/WEB-INF/base/skin/topPart.jsp"%>


<div id="c-center">

<div class="fixed-a">
<%@include file="/WEB-INF/base/skin/leftPart-zhzats.jsp"%>
</div>

<div id="c-center-right">

<div id="c-center-right-content">

	<ol class="breadcrumb m-ui-breadcrumb">
	  <li><a href="#">首页</a></li>
	  <li><a href="#">综合治安态势</a></li>
	  <li><a href="#">综合治安态势</a></li>
	</ol>

				<div class="m-ui-searchbox">
					<div class="m-ui-searchbox-con">
						<div class="row">
							<div class="col-xs-2">
								<select id="pcsSelect" class="select2-noSearch"
									style="margin-top: 5px">
								</select>
							</div>
							<div class="searchTime col-xs-3">
								<button
									class="search searchTime-btn btn btn-primary btn-sm btSelected"
									value="day">近1天</button>
								<button class="search searchTime-btn btn btn-bordered btn-sm"
									value="week">近一周</button>
								<button class="search searchTime-btn btn btn-bordered btn-sm"
									value="month">近一月</button>
  								<button id="dateCustomized"	class="searchTime-btn btn btn-bordered btn-sm " value="costum">自定义</button>
								
							</div>
							<!-- 自定义时间 -->
						<div id="searchRange" class="dateRange col-xs-6"
							style="display: none; margin-top: 0px;">
							<div class="col-xs-2">
								<label class="control-label" style=" margin: 0 auto;">自定义时间：</label>
							</div>
							<input type="hidden" id="dtfmt" class="dateFmt"
								value="info@yyyy-MM-dd HH:mm:ss" />
							<div class="col-xs-4 input-group">
								<div class="input-group" style="margin-right: 10px;">
									<input type="text" id="fixed_start"
										class="laydate-start form-control input-sm"
										readonly="readonly"> <span
										class="laydate-start-span input-group-addon m-ui-addon">
										<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
									</span>
								</div>
							</div>
							<div class="col-xs-1" style="width: 20px; text-align: center">-</div>
							<div class="col-xs-4 input-group">
								<div class="input-group" style="padding-left: 5px;">
									<input type="text" id="fixed_end"
										class="laydate-end form-control input-sm" readonly="readonly">
									<span class="laydate-end-span input-group-addon m-ui-addon">
										<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
									</span>
								</div>
							</div>
						</div>
						<!-- 自定义时间 -->
						<div class="col-xs-1">
							<button id="search" class="btn btn-primary btn-sm">查询</button>
						</div>
						</div>
						
					</div>
				</div>

				<div class="m-ui-title1"><h1 class="pull-left">综合治安态势</h1>
	<div class="pull-left mar-left mar-top">
	<div id="oDiv" style="height:20px; overflow:hidden">
	<ul id="gundong_ul">
	<li> <h4 class="font16 color-gray m-inline"><span id="gundongJqTitle">今日警情</span>： </h4><p id="gundongJqData" class="m-inline"></p> </li>
	<li> <h4 class="font16  color-gray m-inline"><span id="gundongPanChaTitle">今日盘查人员、车辆</span>： </h4><p id="gundongPanChaData"  class="m-inline">XXX派出所辖区、XXX派出所辖区、XXX派出所辖区、XXX派出所辖区、XXX派出所辖区</p> </li>
	<li> <h4 class="font16  color-gray m-inline"><span id="gundongLiuDongTitle">今日流动人口增长</span>： </h4>  <p id="gundongLiuDongData"  class="m-inline">XXX派出所辖区、XXX派出所辖区、XXX派出所辖区、XXX派出所辖区、XXX派出所辖区</p></li>
	</ul>
	</div>
	</div>
	</div>

<div class="row" style="background:#15253c;padding-top:10px; "> 
           
               <div class="col-xs-4">
               <div  class="today-jq" style="padding:10px 10px 10px 10px;">
                      <h2 class="font18" style="height:30px;"><button style="display:none" id="toUpPage" class="btn btn-primary  btn-sm mar-right">返回</button><span id="todayJqData">今日警情</span><span class="font16 color-yellow2 pull-right"><span id="jinri-total"></span>起</span></h2>
                      <table id="jiri_table" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%" style="border-top:none;">

					  </table></div></div>
                      
               <div class="col-xs-8">
            <div class="m-ui-title3"  style="text-align:center;"><h2>常量预警</h2></div>
            <div class="fi-ceng-out" style="text-align:center;height:311px;">
            	<div id="changliang_chart" style="height:311px;">
            	</div>
<!--             <div class="fi-ceng light-block" style="left:30%; top:60px; margin-left:0; width:300px; padding:20px; text-align:center;">
            <p>黄河所已达到预警值，请及时安排并部署警力！并请确认是否发送相关指令？</p>
            <p style="padding-top:20px;"><button class="btn btn-primary brn-sm">是</button><button class="btn btn-default brn-sm">否</button></p>
            </div> -->
            </div>
            </div>          
</div>  

<div class="row dark-block mar-top" style="background:#031428;height:35px;padding-top:10px; padding-bottom:10px;">
<div class="m-icheck-group">
                         <p class="col-xs-2" style="width:110px;"><input type="checkbox" value="jq" class="map-pos icheckbox">警情态势</p>
                         <p class="col-xs-2"  style="width:110px;"><input type="checkbox" value="aj"  class="map-pos icheckbox">案件态势</p>
                         <p class="col-xs-2"  style="width:120px;"><input type="checkbox" value="xcll" class="map-pos icheckbox"> 巡处力量</p>
                         <p class="col-xs-1" style="width:50px;">图层：</p>
                         <p class="col-xs-2"  style="width:90px;"><input type="checkbox" value="pcs" class="map-tuceng icheckbox">派出所</p>
                         <p class="col-xs-2" style="width:80px;"><input type="checkbox" value="wg" class="map-tuceng icheckbox">网格</p>
                       	 <p class="col-xs-2"  style="width:80px;"><input type="checkbox" value="sq" class="map-tuceng icheckbox">社区</p>
</div>
</div>

<div class="row">
<div id="mapContent" style="height:400px;overflow:hidden;background-color: #f7f6ef;"></div> 
</div>

<div class="row dark-block" style="margin-bottom:20px; padding-bottom:15px; padding-top:15px;">
<div class="col-xs-6">
<div class="m-ui-title3 text-center"><button id="faanchusuo_hc-toUpPage" style="display:none" class="btn btn-primary  btn-sm pull-left">返回</button><h2>发案处所统计</h2></div>
<div id="faanchusuochart" style="width:100%; max-width:830px;height:262px"></div>            
</div>            
<div class="col-xs-6">
<div class="m-ui-title3 text-center"><button id="faanshiduan_hc-toUpPage" style="display:none" class="btn btn-primary  btn-sm pull-left">返回</button><h2>发案时段统计</h2></div>
<div id="faanshiduanchart" style="width:100%; max-width:830px;height:262px"></div> 
</div>
            

</div>


<div class="row dark-block" style="margin-bottom:20px; padding-bottom:15px;">
<div class="col-xs-4">
	<div id="wifiweilan" style="height:400px;">
	</div>

</div>
<div class="col-xs-4">
	<div id="gaoweichart" style="height:400px">
	</div>
</div>
<div class="col-xs-4"><div id="liudongchart" style="height:400px"></div></div>
</div>

<div class="row dark-block" style="margin-bottom:20px; height:400px">
<div class="col-xs-4"><div id="container-pancha" style="height:400px"></div></div>

<div class="col-xs-8"> 
<div style="padding:0 15px;">
<div class="m-ui-title3 mar-top text-center"><h2>打防管控分析建议</h2></div>
<div style="float:right; margin-top:-25px;">
<button id="dafang-export" class="btn btn-primary btn-sm" style="display:none;"><span class="glyphicon glyphicon-share-alt mar-right"></span>导出报告</button>
<button id="sendInstruction" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-send mar-right"></span>发送指令</button>
</div>
<div id="suggestContent" class="row-mar color-gray" style="text-indent:2em">

								<p>
									<span id="dafang-startDay"></span>到<span id="dafang-endDay"></span>，<span id="suggestUnit"></span>共接各类警情<span
										id="dafang-jqTotal"></span>环比<span id="dafang-jqTotalHB"></span><span id="dafang-jqTotalPercent"></span>其中刑事类警情共计<span
										id="dafang-xingshiJq"></span>环比<span id="dafang-xingshiJqHB"></span><span id="dafang-xingshiJqPercent"></span>其中治安类警情<span id="dafang-zhianJq"></span>环比<span id="dafang-zhianJqHB"></span><span id="dafang-zhianJqPercent"></span> 其他警情<span id="dafang-qitaJq"></span>环比<span id="dafang-qitaJqHB"></span><span id="dafang-qitaJqPercent"></span>
								</p>

								<p>街面盘查情况： 新增盘查人员<span id="dafang-pancha_ren"></span>环比<span id="dafang-pancha_renHB"></span><span id="dafang-pancha_renPercent"></span>新增盘查客运车辆<span id="dafang-pancha_che"></span>环比<span id="dafang-pancha_cheHB"></span><span id="dafang-pancha_chePercent"></span>非客运车辆<span id="dafang-pancha_fche"></span>环比<span id="dafang-pancha_fcheHB"></span><span id="dafang-pancha_fchePercent"></span></p> 
 </div>
 <p id="jqHight">警情高发区域为<span class="color-yellow2">“<span id="dafang-gaofa_pcs"></span>”</span>，高发警情类别为<span class="color-yellow2">“<span id="dafang-gaofa_jqlx"></span>”</span>，警情高发时段为<span class="color-yellow2">“<span id="dafang-gaofa_shiduan"></span>点”</span></p>
               <div id="dafangChart" style="height:250px;"></div>

                <p class="mar-top row-mar"><span class="color-red2">建议：</span><span id="dafang-panchalidu"></span>，加大街面盘查力度。</p>
</div>
</div>   
   
</div>

</div>
</div>

</div>
<script type="text/javascript" src="<%=context%>/scripts/zhzats/welcome.js"></script>
<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>


</html>