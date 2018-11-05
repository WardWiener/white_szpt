<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title>实战平台</title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/highRiskPersonAlert/common/constant.jsp"%>
</head>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
<body>
<input id="isApprove" style="display:none" value="1">
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>
	<div id="c-center">
		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-highRiskPersonAlert.jsp"%>
		</div>
		<div id="c-center-right">
			<div id="c-center-right-content">
				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">高危人群分析</a></li>
					<li class="active"><a href="#">人员查询</a></li>
				</ol>
				<div id="c-center-right-content-block">
					<div id="c-center-right-content-bar">
						<div class="panel panel-default">
							<div class="panel-heading"
								style="background-color: #1d98de; color: #fff;">人员情况</div>
							<ul id="yjlxRyqkUl" class="list-group">
							</ul>
						</div>

						<div class="panel panel-default">
							<div class="panel-heading" style="background-color:#1d98de; color:#fff;">新增人员</div>
							<ul id="newAddPersonUl" class="list-group"></ul>
						</div>
					</div>
					<div id="c-center-right-content-con">
						<div class="right-inner">
							<div class="m-ui-searchbox">

								<div class="row row-mar">
									<div class="col-xs-2" style="width:100px;">
										<label class="control-label">姓名：</label>
									</div>
									<div class="col-xs-2">
										<input id="name" type="text" class="form-control input-sm">
									</div>
									<div class="col-xs-2" style="width:100px;">
										<label class="control-label" >身份证：</label>
									</div>
									<div class="col-xs-2">
										<input id="idCode" type="text" class="form-control input-sm">
									</div>
									<div class="col-xs-2">
									       <button id="seach"class="btn btn-primary btn-sm" style="margin-left:15px">查询</button>
									       <button id="reset"class="btn btn-default btn-sm">重置</button>
									</div>
									<div class="col-xs-2 text-right" style="display:none"><button id="add" class="btn btn-primary" style="width:120px"><span class="glyphicon glyphicon-plus mar-right"></span>新增高危人</button></div>
									<div class="col-xs-2 text-right" style="display:none"><button id="importExcel" class="btn btn-primary" style="width:120px"><span class="glyphicon glyphicon-plus mar-right"></span>导入高危人</button></div>
									
								</div>
								<div class="more row row-mar" style="margin-top: 15px;">
									<div class="col-xs-2" style="width: 100px;">
										<label class="control-label">预警类型：</label>
									</div>
									<div class="col-xs-8" style="padding-top: 5px;">
										<div id="warning-type" class="warning-type" style="width: 700px;">
											<!-----------------------------------------开发注意此处样式修改 去掉<a>标签里的颜色名称------------------------------------------>
											 <a href="#" id="allYjlx" class="warnType btn sq-all" title="全部"></a>
										
										</div>
									</div>
								</div>
								<div id="rylbDiv" class="row">
							      <div class="col-xs-2" style="width:100px;"> <label class="control-label">人员类型：</label></div>
							      <div class="col-xs-10">
							      <div class="row m-icheck-group">
							      		<div class="col-xs-2">
											<p id="allRylbP" class="col-xs-2" style="width: 139px">
												<input id="allRylb"  type="checkbox" class="icheckbox">全部
											</p>
										</div>
										<div id="xsqkType" class="col-xs-10"
											style="width: 550px; border: 1px dashed #6e97cb; padding: 5px 10px 8px 10px; margin-top: -8px">
										</div>							        
									 </div>
							            <!-- <div class="row m-icheck-group mar-top">
							            	 
										 </div>  -->   
							            <div id="otherRylbType" class="row m-icheck-group mar-top">
										</div>
								</div>
							</div>
							
							<div class="row">
							<div class="col-xs-2" style="width:100px;"> <label class="control-label">审批状态：</label></div>
							<div class="col-xs-10">
							<div class="row m-icheck-group">
							            <p class="col-xs-1" style="width:100px"><input id="allCzzt" value=""type="checkbox" class="icheckbox">全部</p>
							            <p class="col-xs-1" style="width:100px"><input value="<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.CZZT_XZ%>"type="checkbox"  class="icheckbox czztCheck"name="czzt">新增</p>
							            <p class="col-xs-1" style="width:100px"><input id="<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.CZZT_DSP%>"value="<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.CZZT_DSP%>"type="checkbox"  class="icheckbox czztCheck"name="czzt">待审批</p>
							            <p class="col-xs-1" style="width:100px"><input value="<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.CZZT_SPTG%>"type="checkbox"  class="icheckbox czztCheck"name="czzt">审批通过</p>
							            <p class="col-xs-1" style="width:100px"><input value="<%= com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant.CZZT_SPBH%>"type="checkbox"  class="icheckbox czztCheck"name="czzt">审批驳回</p>
							</div>
							</div>
							</div>
							</div>

							<div class="m-ui-title1">
								<h1>人员查询</h1>
								<!-- <div class="m-ui-caozuobox">
									<button id="newFiveColorPerson" class="btn btn-primary">新增</button>
									<button id="updateFiveColorPerson" class="btn btn-success">修改</button>
								</div> -->
							</div>
							<div class="m-ui-table">
								<table id="table" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%"></table>
							</div>
						</div>
					</div>
				</div>
				<!--c-center-right-content-block结束-->
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/personList/personApproveList.js"></script>
</html>