<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>


</head>

<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>

	<div id="c-center">

		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-zagl.jsp"%>
		</div>
		<div id="c-center-right-content">
			<ol class="breadcrumb m-ui-breadcrumb">
  <li><a href="#">首页</a></li>
  <li><a href="#">专案资料管理</a></li>
  <li><a href="#">专案维护</a></li>
</ol>

<!--整体查询板块--begin-->
<div class="basic-query-out">
<div class="basic-query"><input type="text" id="nrmhcx" class="form-control input-sm" value="内容模糊查询" onBlur="if(!value){value=defaultValue;this.style.color='#b1b8c2'}"  onFocus="if(value==defaultValue){value='';this.style.color='#fff'}" style="color:#b1b8c2;"><button class="btn btn-primary btn-sm" id="findBtn">查询</button><button class="advanced-btn">展开高级查询</button></div>
</div>


<div class="m-ui-searchbox  advanced-query">
<div class="m-ui-searchbox-con">

<div class="row row-mar">
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">专案编号：</label></div>
    <div class="col-xs-6"><input type="text" id="zabh" value="" class="form-control input-sm"></div>
</div>
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">专案名称：</label></div>
    <div class="col-xs-6"><input type="text" value="" id="ajmc" class="form-control input-sm"></div>
</div>
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">专案性质：</label></div>
     <div class="col-xs-6"> <select id="ajxz" class="form-control input-sm">
    </select></div>
</div>
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">涉及子案件：</label></div>
     <div class="col-xs-6 input-group"><input id="sjzaj" sonCaseCode="" value="" type="text"  readonly="readonly" class="form-control input-sm"> <span class="input-group-addon" id="sonProject"><span class="glyphicon glyphicon-search"></span></span></div>
</div>
</div>


<div class="row row-mar">
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">简要案情：</label></div>
     <div class="col-xs-6 input-group"><input type="text" id="jyaq" value="" class="form-control input-sm"> </div>
</div>
<div class="col-xs-3">
   <div class="col-xs-6"> <label class="control-label">专案组成员：</label></div>
     <div class="col-xs-6 input-group"><input type="text" personId="" id="zazcy" readonly="readonly" value="" class="form-control input-sm"> <span class="input-group-addon" id="findPerson" ><span class="glyphicon glyphicon-search"></span></span></div>
</div>
<div class="col-xs-3">  
<button class="btn btn-primary btn-sm " id="findjq">查询</button>
<button class="btn btn-default btn-sm" id="res">重置</button>    
</div>

</div>
</div>
</div>

<!--查询结束-->


<div class="advanced-btn-box"><button class="advanced-btn-up">收起高级查询</button></div>

<!--整体查询板块--end-->
<!--悬浮操作层-->
<div class="fixed-a">
<div class="m-ui-title1"><h1>专案维护</h1><div class="m-ui-caozuobox">
                        <button class="btn btn-primary" id="addBtn">新增</button>
                        <button class="btn btn-success" id="updateBtn">修改</button>
                        <button class="btn btn-danger" id="delBtn">删除</button>
                        <button class="btn btn-primary" id="allotUserBtn" ><span class="glyphicon glyphicon-user"></span>分配用户</button>
                        <button class="btn btn-primary" id="fu"><span class="glyphicon glyphicon-refresh"></span>刷新</button>
                        </div></div>
</div>
<!--悬浮操作层-->
<br><br>
<div class="m-ui-table">
                  <table id="table" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%" >
                      
                  </table>
                  
 
                  
              </div>


			        

			<!-----列表---end-->
		</div>
		
	</div>
	
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
<script type="text/javascript" src="<%=context%>/script/zawh/zawh.js"></script>
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