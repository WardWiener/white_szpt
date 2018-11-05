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
			    <li><a href="#">刑事案件分析</a></li>
			  <li><a href="#">专案资料管理</a></li>
			</ol> 
			
			
			<!--悬浮操作层-->
			<div class="alert alert-info">
				<p style="margin-top:10px; margin-left:20px;">提示：
				<span class="icon-dot icon-dot-qy"  style="margin-left:10px"></span> 表示“启用”
				<span class="icon-dot icon-dot-ty"  style="margin-left:30px"></span> 表示“停用”
				</p>
			
			
		<div class="pull-right" style="margin-top:-40px;" id="allBtn">

			                        <button class="btn btn-primary" id="newBut">新增</button>
			                        <button class="btn btn-success" id="modifyBut">修改</button>
			                        <button class="btn btn-danger" id="deleteBut">删除</button>
			                        <button class="btn btn-primary" id="enabledBut">启用</button>
			                        <button class="btn btn-primary" id="disableBut">停用</button>
			                        <button class="btn btn-primary" id="assigningRolesBut">领导角色分配用户</button>
			                        </div>
			                        <div class="pull-right" style="margin-top:-40px;display:none" id="addBtn">
			                        <button class="btn btn-primary" id="saveBut">保存</button>
			                        <button class="btn btn-success" id="resetBut">取消 </button>
			                        </div>
			                        <div class="pull-right" style="margin-top:-40px;display:none" id="updateBtn">
			                        <button class="btn btn-primary" id="saveUpdatBut">保存</button>
			                        <button class="btn btn-success" id="resetUpdateBut">取消 </button>
			                        </div>
			
			
		<div class="person-list validform" id="yanzheng" >
			<ul id="perList">
			</ul>
			<div class="clear"></div>
		 </div>
			        

			<!-----列表---end-->
		</div>
		
	</div>
	</div>
	<script type="text/javascript" src="<%=context%>/script/zajs/jswh.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>

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