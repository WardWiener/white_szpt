<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/map.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/highcharts/js/highcharts-more.js"></script>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%> 
<%@include file="/custom/arcgis/arcgis-import.jsp"%>
<%@include file="/WEB-INF/view/instruction/common/constant.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript" src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/instruction/instructionList.js"></script>
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
    <li><a href="#">刑事警情研判</a></li>
</ol>


<div class="m-ui-title1"><h1>刑事警情研判</h1></div>

<div class="main-block">
<div class="row row-mar">
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">关键字：</label></div>
    <div class="col-xs-6"><input type="text" id="keyword" class="form-control input-sm"></div>
</div>
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">警情类别：</label></div>
   <div class="col-xs-6  input-group"><input id="jqlx" type="text" class="form-control input-sm" readonly> 
   <span id="findjqlx"  class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span>
</div>
</div>
<div class="col-xs-6">
   <div class="col-xs-3"> <label class="control-label">处警单位：</label></div>
    <div class="col-xs-9">
		<div class="col-xs-3">
				<select id="firstSelect" class="form-control select2-noSearch allowClear">
				<option></option>
				<option>派出所</option>
				<option>网格</option>
				</select>
		</div>
		<div class="col-xs-3">
				<select id="secondSelect" class="form-control select2-multiple   allowClear">
				</select>
		</div>
		<div class="col-xs-3 thidlySelect" style="display:none;" >
				<select id="thidlySelect" class="form-control select2-multiple  allowClear" >
				</select>
			</div>
    </div>
</div>
</div>
<div class="row row-mar">
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">发生地点：</label></div>
    <div class="col-xs-6"><input type="text" id="occurPlace" class="form-control input-sm"></div>
</div>
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">所属村居：</label></div>
    <div class="col-xs-6"><select id="cunjuSelect"class="form-control input-sm select2 allowClear">
    </select></div>
</div>
<div id="dateRange" class="col-xs-6 dateRange">
   <div class="col-xs-3"> <label class="control-label">发生时间：</label></div>
<input type="hidden" id="dtfmt" class="dateFmt" value="yyyy-MM-dd" />
  <div class="col-xs-3 input-group">
  <div class="input-group" >
							<input type="text" id="fixed_start" class="laydate-start form-control input-sm" readonly="readonly">
							<span class="laydate-start-span input-group-addon m-ui-addon">
								<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
							</span>
						</div>
  </div>
     <div class="col-xs-1 to">——</div>
     <div class="col-xs-3 input-group">
			<div class="input-group" >
							<input type="text" id="fixed_end" class="laydate-end form-control input-sm" readonly="readonly">
							<span class="laydate-end-span input-group-addon m-ui-addon">
								<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
							</span>
						</div>
	    </div>
</div>
</div>


<div class="row">
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">研判状态：</label></div>
    <div class="col-xs-6"><select id="yanpanState" class="form-control input-sm" ><option value="">全部</option><option value="0">未研判</option><option value="1">已研判</option></select></div>
</div>
<div class="col-xs-6 text-center">
<button class="btn query btn-primary btn-sm" style="margin-left:60px">查询</button>
<button class="btn reset btn-default btn-sm" style="margin-left:5px;">重置</button>
</div></div>
<!----row----> 




<div class="row">
<button id="showJqDetail" class="btn btn-primary">查看详情</button>
</div>
<table id="xsjqTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
                 
         </table>
<p class="color-gray" style="margin-top:20px;">图标说明：
<span class="glyphicon glyphicon-book font16 color-green1 mar-right"></span>已经过研判的警情&nbsp;&nbsp;&nbsp;</p>

</div>

<%@include file="/WEB-INF/view/zhzats/jqxxAndFkqk.jsp"%>

<div id="zlxf" class="main-block" style="display:none;">
<div class="m-ui-title3"><h2>指令下发</h2></div>
<div class="row row-mar"><!-- <button class="btn btn-danger">指令下发</button> btn-primary--><button id="newAddInstruction" class="btn btn-danger">指令下发</button></div>
       <table id ="instructionByYanpanTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
                     
       </table>
 </div>


<div id="ypjgfk" class="main-block" style="display:none;">
<div class="m-ui-title3"><h2>研判结果反馈</h2></div>
  <table id="yanpanResultReturnTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
        
        </table>
</div>
</div>
</div>
</div>

<script type="text/javascript" src="<%=context%>/scripts/zhzats/yanpan.js"></script>
<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>

</body></html>