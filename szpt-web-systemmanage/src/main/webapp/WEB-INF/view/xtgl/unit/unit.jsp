<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>

<title>实战平台 – 系统管理</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
 <%  
      String constantStauts_qy = com.taiji.pubsec.szpt.xtgl.Constant.STATUS_START;
      String constantStauts_ty = com.taiji.pubsec.szpt.xtgl.Constant.STATUS_STOP;
      String constantStauts = com.taiji.pubsec.szpt.xtgl.Constant.STATUS; 
      String constant_unitInit = com.taiji.pubsec.szpt.xtgl.Constant.ORGANIZATION_INIT;
      String constant_rootUint = com.taiji.pubsec.szpt.xtgl.Constant.UNITCODE_SUBSTATION;
 %>
<script>
var constantStauts_qy = '<%=constantStauts_qy%>';
var constantStauts_ty = '<%=constantStauts_ty%>';
var constantStauts = '<%=constantStauts%>';
var constantInit = '<%=constant_unitInit%>';
var constantRootUnit = '<%=constant_rootUint%>';
</script>
<style type="text/css">

</style>
</head>

<body class="validform" id="validformId">
<%@include file="/WEB-INF/base/skin/topPart.jsp"%>

<div id="c-center">
<%@include file="/WEB-INF/base/skin/leftPart-xtgl.jsp"%>




<div id="c-center-right">

<div id="c-center-right-content">
	<ol class="breadcrumb m-ui-breadcrumb">
		<li>系统管理</li>
		<li>组织机构管理</li>
	  	<li>单位管理</li>
	</ol>
	<div class="fixed-a">
		<div id="btnUpDiv" class="m-ui-title1">
			<h1>单位管理</h1>
			<div class="m-ui-caozuobox">
				<button id="synchronousBtn" class="btn btn-warning"><span class="glyphicon glyphicon-refresh" aria-hidden="true" style="margin-right:10px;"></span>数据同步</button>
				<button id="addBtn" class="btn btn-warning"><span class="glyphicon glyphicon-plus" aria-hidden="true" style="margin-right:10px;"></span>新增</button>
				<button id="modifyBtn" class="changeBtn btn btn-primary"><span class="glyphicon glyphicon-edit" aria-hidden="true" style="margin-right:10px;"></span>修改</button>
				<button id="disableBtn" style="display: none" class="changeBtn btn btn-danger "><span class="glyphicon glyphicon-ban-circle" aria-hidden="true" style="margin-right:10px;"></span>停用</button>
				<button id="enableBtn" style="display: none" class="changeBtn btn btn-primary "><span class="glyphicon glyphicon-wrench" aria-hidden="true" style="margin-right:10px;"></span>启用</button>
				<button  id="deleteBtn" class="changeBtn btn btn-danger " ><span class="glyphicon glyphicon-remove" aria-hidden="true" style="margin-right:10px; display:none"></span>删除</button>
			</div>
		</div>
		<!--标题结束-->
	</div>
<div id="c-center-right-content-block">
<!--树形菜单start-->
<div id="c-center-right-content-bar" style="padding:0">
   <input type="hidden" id="curSelectUnit" />
	<div class="left-tree">
		<div class="m-ztree-out"> 
			<input type="text" id="keyIn" style="margin-bottom:5px" class="form-control input-sm" />
			<ul id="ztree-inUnitTree" class="ztree" style="height:300px"></ul>
    	</div>
	</div>
</div>
<!--树形菜单end-->
<div id="c-center-right-content-con" >

<div class="right-inner">
<div class="m-ui-searchbox" >
<div class="m-ui-searchbox-con m-new-inbox">
<div class="row row-mar mar-top">
<div class="col-xs-4">
   <div class="col-xs-4"> <label class="control-label">组织机构代码：<span class="red-star red_star_canHide">*</span></label></div>
    <div class="col-xs-8">
    <input class="form-control valiform-keyup form-val" vf-position="2" datatype="*1-20" id="code">
	<input type="hidden" id="organizationId">
 </div>
</div>
<div class="col-xs-4">
   <div class="col-xs-4"> <label class="control-label">名称：<span class="red-star red_star_canHide">*</span></label></div>
    <div class="col-xs-8">
    <input class="form-control valiform-keyup form-val" vf-position="2" datatype="*1-40" id="name">
 </div>
</div>
<div class="col-xs-4">
   <div class="col-xs-4"> <label class="control-label">简称：</label></div>
    <div class="col-xs-8">
    <input class="form-control valiform-keyup form-val" vf-position="2" datatype="*0-10" id="shortName">
</div>
</div>
</div>


<div class="row row-mar mar-top">


<div class="col-xs-4">
   <div class="col-xs-4"> <label class="control-label">联系电话：</label></div>
    <div class="col-xs-8">
    <input class="form-control valiform-keyup form-val" vf-position="2" datatype="n0-15 | str0-15,halfByte" errormsg="请输入0-15数字或字符" id="telephone">
</div>
</div>
<div class="col-xs-4">
   <div class="col-xs-4"> <label class="control-label">传真：</label></div>
    <div class="col-xs-8">
    <input class="form-control valiform-keyup form-val" vf-position="2" datatype="n0-15 | str0-15,halfByte"  errormsg="请输入0-15数字或字符" id="portraiture">
</div>
</div>
<div class="col-xs-4">
   <div class="col-xs-4"> <label class="control-label">状态：</label></div>
    <div class="col-xs-8">
   <select class="form-control select2-noSearch" id="state" ></select>
</div>
</div>
</div>
<div class="row row-mar mar-top">
<div class="col-xs-8">
  <div class="form-group">
   <div class="col-xs-2"> <label class="control-label">办公地址：</label></div>
    <div class="col-xs-10">
      <input class="form-control valiform-keyup form-val" vf-position="2" datatype="*0-30" id="address">
    </div>
  </div>
</div>
<div class="col-xs-4">
   <div class="col-xs-4"> <label class="control-label">上级机构：<span class="red-star red_star_canHide">*</span></label></div>
    <div class="col-xs-8">
            <div class="input-group">
            <input class="form-control upUnit"  id="upUnit" readonly> 
            <input type="hidden" id="upUnitCode">
            <input type="hidden" id="upUnitId">
            <input type="hidden" id="upUnitType">
            <input type="hidden" id="ifInUnit">
            <span class="input-group-addon m-ui-addon upUnit"><span class="glyphicon glyphicon-search" aria-hidden="true" style="font-size:16px;"></span></span></div>
    </div>
</div>
</div>

<div class="row row-mar mar-top">
<div class="col-xs-8">
  <div class="form-group">
   <div class="col-xs-2"> <label class="control-label">备注：</label></div>
    <div class="col-xs-10">
     <textarea cols="" rows="5" class="form-control valiform-keyup form-val" vf-position="2" datatype="*0-500" id="remarks"></textarea>
 </div>
  </div>
</div>
  <div class="col-xs-4">
   <div class="col-xs-4"> <label class="control-label">单位属性：<span class="red-star red_star_canHide">*</span></label></div>
    <div class="col-xs-8">
   <select class="form-control select2-noSearch" id="type"></select>
</div>
</div>
</div>
</div>
</div>
</div>
<!--表单输入结束-->
<div id="btnDownDiv" class="m-ui-commitbox" style="display: none;">
<!--根据阶段已配置的按钮,重点操作按钮-->
<button class="btn btn-primary m-ui-btn1 w150 disableControl"  id="save">保存</button>
<button class="btn btn-default m-ui-btn2 " id="cancel">取消</button>
<input type="hidden" id="operateType" value="lookOver">
<input type="hidden" id="oldCode">
<input type="hidden" id="oldDep">
</div>
</div>
</div>
<!--c-center-right-content-block结束-->


</div>
<!--右侧内容结束-->
</div>
</div>
</div>

<div id="menuContent" class="ztree-MenuContent">
	<ul id="ztree-unitSelect" class="ztree" style="width:200px; height: 300px;"></ul>
</div>
<div id="menuCellContent" class="ztree-MenuContent">
	<input type="text" id="keyCellSelect" style="margin-bottom:5px;" class="form-control input-sm" />
	<ul id="ztree-CellSelect" class="ztree" style="width:200px; height: 300px;"></ul>
</div>
<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<script type="text/javascript" src="<%=context%>/scripts/xtgl/common/alertLoading.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/xtgl/unit/unit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/zTree/js/jquery.ztree.exhide-3.5.js"></script>
</html>