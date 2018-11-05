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
 %>
<script>
var constantStauts_qy = '<%=constantStauts_qy%>';
var constantStauts_ty = '<%=constantStauts_ty%>';
var constantStauts = '<%=constantStauts%>';
</script>
</head>

<body>
<%@include file="/WEB-INF/base/skin/topPart.jsp"%>

<div id="c-center">
<%@include file="/WEB-INF/base/skin/leftPart-xtgl.jsp"%>



<div id="c-center-right">

<div id="c-center-right-content">
	<ol class="breadcrumb m-ui-breadcrumb">
	  <li>系统管理</li>
	  <li>角色权限管理</li>
	  <li>用户管理</li>
	</ol>
	<div class="fixed-a">
		<div class="m-ui-title1"><h1>用户管理</h1>
			<div class="m-ui-caozuobox">
				<button class="btn btn-warning" id="add"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增</button>
				<button class="btn btn-primary" id="edit" disabled=true><span class="glyphicon glyphicon-edit" aria-hidden="true"></span>修改</button>
				<button class="btn btn-danger" id="delete" disabled=true><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</button>
				<button class="btn btn-danger" id="stop" disabled=true><span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>停用</button>
				<button class="btn btn-primary" id="start2" style="display:none;"><span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>启用</button>
				<button class="btn btn-primary" id="reset" disabled=true><span class="glyphicon glyphicon-repeat" aria-hidden="true"></span>重置密码</button>
			</div>
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



<div id="c-center-right-content-con" class="alt">
<div class="right-inner">


<div class="m-ui-searchbox">
<div class="m-ui-searchbox-con">
<div class="row row-mar mar-top">
<div class="col-xs-3">
  <div class="form-group">
   <div class="col-xs-4"> <label class="control-label" >用户名：</label></div>
    <div class="col-xs-8">
      <input type="text" class="form-control input-sm" id="sname">
    </div>
  </div>
</div>
<input type="text" class="form-control input-sm" id="scode" value="" style="display:none;">
<input type="text" class="form-control input-sm" id="scell" value="" style="display:none;">

<div class="col-xs-2">
  <div class="form-group">
   <div class="col-xs-4"> <label class="control-label">状态：</label></div>
    <div class="col-xs-8">
   <select class="form-control select2-noSearch" id="state">
   			<option value="">全部</option>
   </select>
 </div>
  </div>
</div>
<div class="col-xs-4">
  <div id="dateRangeId" class="form-group dateRange">
   <div class="col-xs-4"> <label class="control-label">有效期限：</label></div>
   <input type="hidden" id="dtfmt" class="dateFmt"value="info@yyyy-MM-dd" />
    <div class="col-xs-4">
    <div class="input-group" style="margin-right:10px;"><input type="text" id="start"class="laydate-start form-control input-sm" readonly="readonly" ><span class="laydate-start-span input-group-addon m-ui-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span></div>
    </div>
     <div class="col-xs-4"><span style="float:left; margin-top:4px; margin-left:-2px;">--</span>
     <div class="input-group" style="padding-left:5px;"><input type="text" id="end" class="laydate-end form-control input-sm" readonly="readonly"><span class="laydate-end-span input-group-addon m-ui-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span></div>
    </div>
  </div>
</div>
<div class="col-xs-3">
<div class="m-ui-btn-box text-right" >
<button class="btn btn-primary" id="search"><span class="glyphicon glyphicon-search" aria-hidden="true" style="margin-right:10px;"></span>查询</button>
<button class="btn btn-default m-ui-btn3" id="chongzhi" style="margin-left:5px;">重置</button>
</div>
</div>
</div>
</div>
</div>
<!--查询结束-->
<div class="m-ui-table">
	<table id="datatable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">

    </table>
</div>
</div>
<!--数据列表结束-->
</div>
</div>
<!--c-center-right-content-block结束-->
</div>
</div>
</div>
<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>

<script type="text/javascript" src="<%=context%>/scripts/xtgl/user/userManage.js"></script>

</html>