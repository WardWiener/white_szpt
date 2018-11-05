<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>

</head>
<script type="text/javascript">
	var caseCode = "${param.caseCode}";
</script>
<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	

	<div id="c-center">

		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-dagl.jsp"%>
		</div>
		<div id="c-center-right-content">

<ol class="breadcrumb m-ui-breadcrumb">
  <li><a href="#">档案管理</a></li>
  <li><a href="#">一案一档</a></li>
</ol>
<div class="row row-mar">
<div class="col-xs-2" style="width:200px"> <label class="control-label">案件名称或案件编号：</label></div>
<div class="col-xs-2"> <input type="text" id="searchText" class="form-control input-sm"></div>
<div class="col-xs-2">
<button id="searchBtn" class="btn btn-primary btn-sm">查询</button>
<button id="mycase"  class="btn btn-danger btn-sm">我的案件</button></div>
</div>  


<div class="m-ui-title1"><h1>一案一档</h1></div>

<div class="row main-block" style="padding-bottom:0; padding-left:25px;"> 
<p class="row row-mar"><span class=" font16 color-orange1">为您找到相关结果<span id="tableTotal"></span>个</span></p> 

  <div id="diyTable1" class="photo-list an-list an-list2">                 
  <ul id="diyTable1-tbody">
         
  </ul>
<div class="clear"></div>                      
</div>                                 
</div>

<!-----列表---end-->
</div>
		
	</div>
	<script type="text/javascript"
		src="<%=context%>/scripts/yayd/yaydList.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
	
	<div style="display:none">
	<ul>
         <li id="caseInfo" >  
                 <h2 class="title"><a href="javascript:void(0)" valName="caseName" class="valCell caseName "></a></h2><span valName="processName" class="valCell state state-red2"></span>
                 <p class="m-line"></p>
                 <p><span class="color-gray">案件编号：</span><span valName="caseCode" class="valCell caseCode"></span></p>
                 <p><span class="color-gray">发案时间：</span><span valName="caseTimeStartTime" class="valCell"></span></p>
                 <p><span class="color-gray">发案地点：</span><span valName="caseAddress" class="valCell"></span></p>
                 <p><span class="color-gray">案件类别：</span><span valName="caseSortName" class="valCell"></span></p>
                 
                 <div class="row item-num">
                     <div class="col-xs-4" id = "anaySnop"><p>分析快照</p>    <span valName="analSnopNum" class="valCell num"></span></div>
                     <div class="col-xs-4" id = "xianyiren"><p>嫌疑人</p>  <span valName="xyrNum" class="valCell num"></span></div>
                     <div class="col-xs-4" id = "sawp"><p>涉案物品</p>   <span valName="involvedObjectNum" class="valCell num"></span></div>
                </div>
        </li>
</ul>
</div>
</body>
<style>
.diyTable-page{
	text-align: right;
}
.laypage_main a, .laypage_main span {
	color:#fff;
}
.laypageskin_default a{
	border: 0px;
    background-color: transparent;
}
</style>
</html>