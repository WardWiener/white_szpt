<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/highcharts/js/highcharts-more.js"></script>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript" src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/searchTime.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/analyzeDataInit.js"></script>

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
  <li><a href="#">高发警情分析</a></li>
</ol>
<div class="m-ui-searchbox">
<div class="m-ui-searchbox-con">

<div class="row">
<div class="col-xs-3">
   <div class="col-xs-4"> <label class="control-label">警情类别：</label></div>
    <div class="col-xs-8  input-group"><input id="gaofaJqlx" type="text" class="form-control input-sm" readonly> 
   <span id="gaofaJqlxSearch" class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span>
</div>
</div>
<div class="col-xs-3" >
<div class="col-xs-4"> <label class="control-label">派出所：</label></div>
	<div class="col-xs-8 input-group">
		<select id="pcsSelect" class="select2-noSearch" style="margin-top:5px">
		</select>	
	</div>		
</div>
<div class="col-xs-4">
    <div class="col-xs-2"> <label class="control-label">时间：</label></div>
    <div class="searchTime col-xs-10">
			<button class="statistics searchTime-btn btn btn-primary btn-sm btSelected" value="day">近1天</button>
			<button class="statistics searchTime-btn btn btn-bordered btn-sm" value="week">近一周</button>
			<button class="statistics searchTime-btn btn btn-bordered btn-sm" value="month">近一月</button>
  			<button id="dateCustomized"	class="searchTime-btn btn btn-bordered btn-sm " value="costum">自定义</button>
  			
   </div>

</div >
	<div id="searchRange" class="dateRange col-xs-6"
		style="display: none;margin-top: 6px;">
		<div class="col-xs-2">
			<label class="control-label">自定义时间：</label>
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
<div>
	<button id="statistics" class="btn btn-primary" style="width:50px;"><span class="glyphicon glyphicon-stats" aria-hidden="true" style="margin-right:10px;"></span>统计</button>
	<button id="reset" class="btn btn-default m-ui-btn3" style="margin-left:5px;">重置</button>
</div>
<!-- <div class="row m-ui-btn-box" style="text-align:center; padding-top:10px;">
<button class="btn btn-primary" style="width:100px;"><span class="glyphicon glyphicon-stats" aria-hidden="true" style="margin-right:10px;"></span>统计</button>
<button class="btn btn-default m-ui-btn3" style="margin-left:5px;">重置</button>
</div> -->

</div>


</div>
</div>


<div class="m-ui-title1"><h1>高发警情分析</h1></div>
    
 
 <div class="row" style="margin-bottom:20px;">
  <div class="col-xs-7">
     <div class="m-ui-title3 text-center"><h2>警情高发区和高发时段</h2></div>
  <div id="gaofa-jingqing" style="height:680px;"></div>
  </div>
 <div class="col-xs-5">
<button id="toUpPage" class="btn btn-primary  btn-sm mar-right">返回</button>
 <div id="gaofa-jingqing-lx" style="height:400px;"></div>
 <div class="row" style="min-height:280px; background:#1c2f4d;">
            <div class="m-ui-title3 text-center mar-top"><h2>总结</h2></div>
            <div class="para" style="height:auto">
            <h4 class="color-gray">警情高发地区为：</h4> 
                <p id="zongjie-jq"></p> 
                <p class="m-line"></p> 
                <h4 class="color-gray">警情高发时段为：</h4> 
                <p id="zongjie-sd"></p> 
                 <p class="m-line"></p> 
                <h4 class="color-gray">警情高发类型为： </h4> 
                <p id="zongjie-lx"></p> </div>
 </div>
 </div>
</div>
<!----row----> 
 <div class="row" style="margin-bottom:20px;">
  <div class="col-xs-7">

 <!-- <div id="echart-main2" style="height:680px;"></div>-->
  </div></div>


</div>
</div>

</div>


<script type="text/javascript" src="<%=context%>/scripts/zhzats/gaofa.js"></script>
<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>


</html>