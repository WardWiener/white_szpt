<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title>实战平台– 系统管理</title>
<style type="text/css">
.select2-selection--single{
background-color:red
}
</style>
 <%  
      String constantStauts_qy = com.taiji.pubsec.szpt.xtgl.Constant.STATUS_START;
      String constantStauts_ty = com.taiji.pubsec.szpt.xtgl.Constant.STATUS_STOP;
      String constantStauts = com.taiji.pubsec.szpt.xtgl.Constant.STATUS; 
      String constant_unitInit = com.taiji.pubsec.szpt.xtgl.Constant.ORGANIZATION_INIT;
 %>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<script>
var constantStauts_qy = '<%=constantStauts_qy%>';
var constantStauts_ty = '<%=constantStauts_ty%>';
var constantStauts = '<%=constantStauts%>';
var constantInit = '<%=constant_unitInit%>'
</script>
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
  <li>部门管理</li>
</ol>
<div id="btnUpDiv" class="m-ui-title1"><h1>部门管理</h1><div class="m-ui-caozuobox">
<button id="addBtn" class="btn btn-warning"><span class="glyphicon glyphicon-plus" aria-hidden="true" style="margin-right:10px;"></span>新增</button>
<button id="modifyBtn" class="changeBtn btn btn-primary"><span class="glyphicon glyphicon-edit" aria-hidden="true" style="margin-right:10px;"></span>修改</button>
<button id="disableBtn" style="display: none" class="changeBtn btn btn-danger"><span class="glyphicon glyphicon-ban-circle" aria-hidden="true" style="margin-right:10px;"></span>停用</button>
<button id="enableBtn" style="display: none" class="changeBtn btn btn-success"><span class="glyphicon glyphicon-wrench" aria-hidden="true" style="margin-right:10px;"></span>启用</button>
<button  id="deleteBtn" class="changeBtn btn btn-danger "><span class="glyphicon glyphicon-remove" aria-hidden="true" style="margin-right:10px;"></span>删除</button>
</div>
</div>
<div id="c-center-right-content-block">

<!--树形菜单start-->
<div id="c-center-right-content-bar" style="padding:0">
	<div class="left-tree">
		<div class="m-ztree-out"> 
			<input type="text" id="keyIn" style="margin-bottom:5px" class="form-control input-sm" />
			<ul id="ztree-inUnitTree" class="ztree" style="height:300px"></ul>
    		</div>
	</div>
</div>
<!--树形菜单end-->
<div id="c-center-right-content-con" >

<!--标题结束-->
<div class="right-inner">
<div class="m-ui-searchbox" >
<div class="m-ui-searchbox-con m-new-inbox">
<div class="row row-mar mar-top">
<div class="col-xs-4 ">
   <div class="col-xs-4"> <label class="control-label">组织机构代码：<span class="red-star red_star_canHide">*</span></label></div>
    <div class="col-xs-8">
    <input class="form-control valiform-keyup form-val" vf-position="2" datatype="*1-20" id="code">
	<input type="hidden" id="organizationId">
 </div>
</div>
<div class="col-xs-4">
   <div class="col-xs-4"> <label class="control-label">名称：<span class="red-star red_star_canHide">*</span></label></div>
    <div class="col-xs-8">
    <input class="form-control valiform-keyup form-val" vf-position="2" datatype="*1-50" id="name">
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
    <input class="form-control valiform-keyup form-val" vf-position="2" datatype="n0-15 | str0-15,halfByte" errormsg="请输入0-15位字符或数字" id="telephone">
</div>
</div>
<div class="col-xs-4">
   <div class="col-xs-4"> <label class="control-label">传真：</label></div>
    <div class="col-xs-8">
    <input class="form-control valiform-keyup form-val" vf-position="2" datatype="n0-15 | str0-15,halfByte"  errormsg="请输入0-15位字符或数字" id="portraiture">
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
<div class="col-xs-8">
  <div class="form-group">
   <div class="col-xs-2"> <label class="control-label">办公地址：</label></div>
    <div class="col-xs-10">
<input class="form-control valiform-keyup form-val" vf-position="2" datatype="*0-30" id="address">
 </div>
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
</div>
</div>
</div>
<!--表单输入结束-->
<div id="btnDownDiv" class="m-ui-commitbox" style="display: none;">
<!--根据阶段已配置的按钮,重点操作按钮-->
<button class="btn btn-success m-ui-btn1 w150 disableControl"  id="save">保存</button>
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

<div id="menuContent" class="ztree-MenuContent">
	<input type="text" id="keySelect" style="margin-bottom:5px;" class="form-control input-sm" />
	<ul id="ztree-unitSelect" class="ztree" style="width:200px; height: 300px;"></ul>
</div>

<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/xtgl/department/department.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/library/zTree/js/jquery.ztree.exhide-3.5.js"></script>

</html>