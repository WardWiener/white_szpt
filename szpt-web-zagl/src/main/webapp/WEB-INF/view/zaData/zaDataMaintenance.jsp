<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>

<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<div id="c-center">
		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-zagl.jsp"%>
		</div>
		<input id="paraId" type="hidden"
			value="<%=request.getParameter("id")%>">
		<div id="c-center-right">

			<div id="c-center-right-content">

				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">刑事案件分析</a></li>
					<li><a href="#">专案资料管理</a></li>
				</ol>
				
				<div class="fixed-a">
					<div class="m-ui-title1"> 
						<h1>专案资料分类维护</h1>
					</div>
				</div>
				<div class="alert alert-info">
				<!--悬浮操作层-->
				<p  style="margin-left:20px;margin-top:10px; ">提示：
					<span class="icon-dot icon-dot-qy"  style="margin-left:10px"></span> 表示“启用”
					<span class="icon-dot icon-dot-ty"  style="margin-left:30px"></span> 表示“停用”
				</p>
				<div id="crudDiv" class="pull-right" style="margin-top:-40px;">
                     <button id="addZazl" class="btn btn-primary">新增</button>
					<button id="updateZazl" class="btn btn-success">修改</button>
					<button id="deleteZazl" class="btn btn-danger">删除</button>
					<button id="startUseZazl" class="btn btn-primary">启用</button>
					<button id="stopUseZazl" class="btn btn-primary">停用</button>
				</div>
				<div id="addZazlDiv"  class="pull-right" style="margin-top:-40px;">
					<button id="addCommit" class="comfigBtn btn btn-primary">保存</button>
					<button id="addCancel" class="btn btn-success">取消</button>
				</div>
				<div id="updateZazlDiv"  class="pull-right" style="margin-top:-40px;">
					<button id="updateCommit" class="comfigBtn btn btn-primary">保存</button>
					<button id="updateCancel" class="btn btn-success">取消</button>
				</div>
				<div id="contentDiv" class="person-list zl-list validform">
					<ul id="zazlType"> 
					</ul>
					<ul id="newAddLi">
						<li ><span class="zl-icon zl-icon-other"></span> <span
							class=""></span>
							<h2 class="name">
								<input id="zazllbName" class="form-control valiform-keyup form-val input-sm" datatype="*1-20" errormsg="请输入1-20个字符" placeholder=""><br>
							</h2>
							<p >
							</p></li>
					</ul>
					<div class="clear"></div>
				</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=context%>/script/zaData/zaDataMaintenance.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
</html>