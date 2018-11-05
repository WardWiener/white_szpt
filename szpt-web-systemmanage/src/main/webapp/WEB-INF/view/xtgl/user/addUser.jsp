<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body class="m-ui-layer-body" >
<div id="myvf" class="m-ui-layer-box validform" style="width:800px;">
<input type="hidden" id="id">
<input type="hidden" id="unitId">
<input type="hidden" id="personNo">
<div class="m-ui-layer-searchbox">
<!--基本信息-->
<div class="row row-mar mar-top">
	<div class="col-xs-6">
    	<div class="form-group">
        	<div class="col-xs-4"> <label class="control-label">用户名：<span class="red-star">*</span></label></div>
            <div class="col-xs-7">
            	<input type="text" id="username" class="valiform-keyup form-val" datatype="str1-30" errormsg="请输入长度为1-30的任意字符"  style="color:#333"></div>
		</div>
	</div>
    <div class="col-xs-6">
    	<div class="form-group">
        	<div class="col-xs-4"> <label class="control-label">密码：<span class="red-star">*</span></label></div>
            <div class="col-xs-7">
            	<input id="password" type="text" class="valiform-keyup form-val" datatype="str6-40,halfByte" 
            		errormsg="请输入大于6位小于40位的字母或数字" style="color:#333">
            </div>
		</div>
	</div>
</div>
<div class="row row-mar">   
<div id="unitDev" class="col-xs-6" >
		<div class="form-group">
        	<div class="col-xs-4"> <label class="control-label">人员所属单位：<span class="red-star">*</span></label></div>
            <div class="col-xs-7 input-group">
            	<input type="text" id="personBelongUnitName" class=" form-control input-sm " datatype="*"
            		 disabled="disabled">
            	<span class="input-group-addon m-ui-addon" id="personBelongUnit">
            		<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
            	</span>
            	<input id="pa_newPage_unitId" type="hidden" value="">
            </div>
 		</div>
	</div>  
 <div id="personDev" class="col-xs-6" >
		<div class="form-group"  >
        	<div class="col-xs-4"> <label class="control-label">人员：<span class="red-star">*</span></label></div>
            <div class="col-xs-7 input-group">
            	<input type="text" id="upPerson" class="form-control input-sm valiform-keyup form-val" datatype="*" disabled="disabled">
            	<input id="upPersonId" type="hidden" value="">
            	<span class="input-group-addon m-ui-addon" id="renyuan">
            		<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
            	</span>
            </div>
 		</div>
	</div>
</div>
<div class="row row-mar"> 
	<div class="col-xs-6">
    	<div class="form-group">
        	<div class="col-xs-4"> <label class="control-label">状态：</label></div>
            <div class="col-xs-7">
            	<select id="selecttest1" class="form-control select2-noSearch">
                 
                </select>
            </div>
		</div>
	</div>
</div>
<div id="dateRangeId" class="row row-mar dateRange">
	<div class="col-xs-8">
		<div class="form-group">
			<div class="col-xs-3"> <label class="control-label">有效期限：</label></div>
            <div class="col-xs-4">
	            <div class="input-group" style="margin-right:10px;">
	            	<input type="hidden" id="dtfmt" class="dateFmt" value="info@yyyy-MM-dd HH:mm:ss" />
	            	<input type="text" id="start" class="laydate-start form-control input-sm valiform-keyup form-val" 
	            		 datatype="*" vf-position="3" errormsg="请填写开始日期" readonly="readonly">
	            	<span class="input-group-addon m-ui-addon">
	            		<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
	            	</span>
	            </div>
            </div>
            <div class="col-xs-4"><span style="float:left; margin-top:4px; margin-left:-2px;">--</span>
	             <div class="input-group" style="padding-left:5px;">
	             	<input type="text" id="end" class="laydate-end form-control input-sm valiform-keyup form-val" 
	             		datatype="*" vf-position="3" errormsg="请填写结束日期" readonly="readonly">
	             	<span class="input-group-addon m-ui-addon">
	             		<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
	             	</span>
	             </div>
			</div>
		</div>
	</div>
   	    
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="form-group">
			<div class="col-xs-2"> <label class="control-label">备注：</label></div>
            <div class="col-xs-9">
            	<textarea rows="3" id="intro" class="form-control valiform-keyup form-val" type="text" datatype="*0-50" ></textarea>
            </div>
		</div>
	</div>
</div>
<div id="menuCellContent" class="ztree-MenuContent" style="z-index:90">
	<input type="text" id="keySelect" style="margin-bottom:5px;" class="form-control input-sm" />
	<ul id="ztree-cellSelect" class="ztree" style="width:200px; height: 300px;"></ul>
</div>
<div id="menuUnitContent" class="ztree-MenuContent" style="z-index:100">
	<input type="text" id="keyUnitSelect" style="margin-bottom:5px;display:none" class="form-control input-sm" />
	<ul id="ztree-unitSelect" class="ztree" style="width:200px; height: 300px;"></ul>
</div>
<div id="menuContentPerson" class="ztree-MenuContent" style="z-index:101">
		<input type="text" id="keySelectPerson" style="margin-bottom: 5px;"
			class="form-control input-sm" />
		<ul id="ztree-personSelect" class="ztree"
			style="width: 200px; height: 300px;"></ul>
		<div align="right" style="font-size: 15pt">
			<a href="###" id="personLeft">&lt;&nbsp;</a>
			<span id="personNumber">1</span>
			<span>&frasl;</span>
			<span id="personTotal">1</span>
			<a href="###" id="personRight">&nbsp;&gt;</a>
		</div>
	</div>
<!--基本信息end-->
<div class="m-ui-title5" style="margin-top:30px;">
	<h4>
		<span class="glyphicon glyphicon-user" aria-hidden="true" style="margin-right:10px; font-size:16px;"></span>
		本用户的角色权限信息
	</h4>
</div>
</br>
</br>
<div class="row row-mar">
	<div class="col-xs-6" >
	<div style="padding-left:15px">
	      所有角色权限：
        	<div style="margin-top:-25px;width:310px;text-align: right;">
				<button class="btn btn-success btn-sm" style="margin-right:5px;" id="toRight">添加</button>
			</div>
        	<div class="m-ztree-out" style="width:310px; background:none">
				<input type="text" id="keyLeft" style="margin-bottom:5px;" class="form-control input-sm" />
				<ul id="ztree-leftTree" class="ztree" style="width:300px;height:200px;overflow:auto"></ul>
			</div>
	</div>
	 
	</div>
    <div class="col-xs-6">
    	已选角色权限：
    		<div style="text-align:right; margin-top:-25px;width:310px;">
				<button class="btn btn-danger btn-sm" id="remove">删除</button>
			</div>
        	<div class="m-ztree-out" style="width:310px;background:none">
				<input type="text" id="keyRight" style="margin-bottom:5px" class="form-control input-sm" />
				<ul id="ztree-rightTree" class="ztree" style="width:300px;height:200px;overflow:auto"></ul>
			</div>
	</div>
</div>

</div>
<!--弹出层列表内容end-->
</div>
</body>
<script type="text/javascript" src="<%=context%>/scripts/xtgl/user/addUser.js"></script>

</html>