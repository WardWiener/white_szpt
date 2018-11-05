<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript" src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/cascadedDicItem.js"></script>

</head>
<body id="cbaGradeTemplate" class="validform m-ui-layer-body">
	<div class="m-ui-layer-box" style="width:800px;">
<div class="row" style=" margin:0;background:#f8f8f8;">

<!-------左侧-------->
<div class="col-xs-2" style="width:18%">
<div style="padding:10px; border-right:1px solid #ddd;">
<p class="row-mar"> 专案信息：</p>
 <div class="list-group">
          <a href="#" class="list-group-item">案件基本资料</a>
          <a href="#" class="list-group-item">专案涉案人员</a>
          <a href="#" class="list-group-item  list-group-item-info">涉案人员关系</a>
          <a href="#" class="list-group-item">专案进展报告</a>
          <a href="#" class="list-group-item">办案手段统计</a>
        </div> 
<p class="row-mar"> 专案资料：</p>  
<div class="list-group">
          <a href="#" class="list-group-item">音频资料</a>
          <a href="#" class="list-group-item">视频资料</a>
          <a href="#" class="list-group-item">图片资料</a>
          <a href="#" class="list-group-item">其他资料</a>
        </div>
              
</div>     
</div>
<!-------左侧-------->   
     
 <!-------右侧-------->       
<div class="col-xs-10" style="width:82%">
         <div style="padding:10px;border-left:1px solid #ddd; margin-left:-1px; background:#fff; min-height:500px;">
         
 <!-------右侧内容begin--------> 
<div class="m-ui-title4 mar-top"><button class="btn btn-info pull-right" style="margin-top:-6px;">维护涉案人员关系</button> <h2>涉案人员关系</h2></div> 

<div class="row row-mar">
<div class="col-xs-3">待维护关系人员关系：
</div>
<div class="col-xs-12">
              <div  class="person-list person-list-sm person-list-sheanrenyuan">
<ul>
<li><a href="#">周而建</a><p class="color-gray text-center">绰号：炜哥</p></li>
<li><a href="#">未知</a><p class="color-gray text-center">绰号：大头</p></li>
<li><a href="#">唐述素</a><p class="color-gray text-center">绰号：素素</p></li>
</ul>
</div>
</div>
</div>
<div style="height:400px; border:1px solid #888; background:#fcfcfc;">
 <!-------绘图区-------->
</div>
<div class="m-ui-commitbox">
<button class="btn btn-primary btn-lg">资料上传</button> 
<button class="btn btn-primary btn-lg">留言反馈</button> 
<button class="btn btn-default btn-lg">返回</button> 
</div>
</div>
</div>  
</div>      
<!--row-end-->        
<!--内容end-->


</div>

   <script type="text/javascript" src="<%=context%>/script/zawh/showInvolvedPersonRelation.js"></script>
</body>
</html>