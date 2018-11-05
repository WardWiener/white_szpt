<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/map.css" />
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

<div id="map-container" style=" width:auto; left:200px; right:0; overflow:hidden;">

<div class="map-tools-box">
<div style="padding-top:10px; padding-left:15px;">
<ol class="breadcrumb m-ui-breadcrumb">
  <li><a href="#">首页</a></li>
  <li><a href="#">综合治安态势</a></li>
  <li class="active">警情分布分析</li>
</ol>
</div>
</div>
<!-- 图上指挥工具栏 end -->
<div class="map-search-box" id="map-search-box">
<!--展开折叠按钮-->


<div class="row map-title1"><h2>警情分布分析</h2></div>

<div class="search-result hot-result">

<div class="row mar-top color-gray" style="border-bottom:1px dotted #527aac; padding-bottom:10px;">&nbsp;&nbsp;单位名称/巡组名称 <span style="margin-left:25px; margin-right:5px;">&nbsp;&nbsp;&nbsp;警情数量&nbsp;</span>报备警力&nbsp;&nbsp;<span >实际警力</span></div>
<ul id="litable" class="route-list-route" style="margin-top:0">

</ul>    
</div> 


</div>
<!-- 搜索板块 end -->


<!-- map start -->
<div class="map map-right-default" id="mapContent"></div>

<!-- 时间段-->
<div class="map-date" style="width:720px;">

<div class="row row-mar" style="margin-top:-25px;">
            <div class="col-xs-4" style="width:220px;border-left:5px;">
                    <div class="col-xs-4"> <label class="control-label" style="padding-top: 5px;">分析基础：</label></div>
                    <div id="analyzeBase" class="col-xs-8"><select id="fxjc" class="form-control input-sm"><option>派出所</option><option>主格</option></select></div>
            </div>
          <div class="col-xs-5">
                    <div class="col-xs-4"> <label class="control-label">警情类别：</label></div>
                    <div class="col-xs-6  input-group"><input id="fenbuJqlx" type="text" class="form-control input-sm" readonly> 
					   <span class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span>
					</div>
		</div>
		<div class="col-xs-2" style="width:110px;">
				<button id="fenbuSelect" class="btn btn-primary btn-sm">统计</button>
				<button id="reset" class="btn btn-default btn-sm" style="margin-left:2px;">重置</button>
		</div>
</div>


<div class="searchTime row">
  <!-- <div class="col-xs-5" style="display:none"> -->
  <div class="col-xs-7" >
			<button class="search searchTime-btn btn btn-primary btn-sm btSelected" value="week">近一周</button>
			<button class="search searchTime-btn btn btn-bordered btn-sm" value="day">近1天</button>
		    <button class="search searchTime-btn btn btn-bordered btn-sm" value="day3">近3天</button>
			<button class="search searchTime-btn btn btn-bordered btn-sm" value="month">近一月</button>
			<button id="dateCustomized"	class="searchTime-btn btn btn-bordered btn-sm " value="costum">自定义</button>
   </div>
   
   <!-- 自定义时间 -->
						<div id="searchRange" class="dateRange row"
							style="display: none; margin-top: 10px; padding-top: 10px;">
							<div class="col-xs-4" style="width:220px;">
							<div class="col-xs-5">
								<label class="control-label">自定义时间：</label>
							</div>
							<input type="hidden" id="dtfmt" class="dateFmt"
								value="info@yyyy-MM-dd HH:mm:ss" />
							<div class="col-xs-7 input-group">
								<div class="input-group" style="margin-right: 10px;">
									<input type="text" id="fixed_start"
										class="laydate-start form-control input-sm"
										readonly="readonly"> <span
										class="laydate-start-span input-group-addon m-ui-addon">
										<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
									</span>
								</div>
							</div>
							</div>
							<div class="col-xs-1" style="width: 20px; text-align: center">-</div>
							<div class="col-xs-4">
							<div class="col-xs-7 input-group">
								<div class="input-group" style="padding-left: 5px;">
									<input type="text" id="fixed_end"
										class="laydate-end form-control input-sm" readonly="readonly">
									<span class="laydate-end-span input-group-addon m-ui-addon">
										<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
									</span>
								</div>
							</div>
							</div>
						</div>
						<!-- 自定义时间 -->
    
  </div>
  </div>
<!-- 时间段-->

  
<div class="hot-result  indication-hot"><h5>警力热力图例</h5> <p class="sq-group"><span class="sq-blue">一般</span><span class="sq-yellow">较热</span><span class="sq-orange">热</span><span class="sq-red">最热</span></p></div>
<!-- map end -->


</div> 



</div>
</div>

</div>

<div style="display:none">

<ul>

           <li class="tableLiTemplate">
             <div class="row">
             <div class="col-xs-5 left"><p class="name"></p>
             <p class="sq-group"><span class="jingqing-color"></span><span class="jingqing-color"></span><span class="jingqing-color"></span><span class="jingqing-color"></span></p> 
             </div> 
             <div class="col-xs-7 right"><a href="#" class="showJq hot-num"><span class="jingqing"></span>起</a>  <a href="#" class="showBb hot-num"><span class="baobei">20</span>人</a>  <a href="#" class="showSj hot-num"><span class="shiji">20</span>人</a> 
                             <button class="btn btn-bordered btn-xs bushu">部署建议</button>    
             </div>
             </div>
           </li>

</ul>

</div>

<script type="text/javascript" src="<%=context%>/scripts/zhzats/fenbu.js"></script>
<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>


</html>