<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>


</head>
<body class="m-ui-layer-body">

	<div class="m-ui-layer-box" style="width: 800px;">
		<div class="m-ui-layer-content">
			<h2 class="font16 text-center" id="titleH2" style="padding: 10px"></h2>
			<hr class="m-line">
			<div class="row row-mar">
				<div class="col-xs-3">
					<label class="control-label">专案组成员所在部门：</label>
				</div>
				<div class="col-xs-3">
					<select id="zaDepartmentSel" class="select2-noSearch allowClear " myPlaceHolder="全部" >
						
					</select>
				</div>
			</div>

			<div class="row row-mar">
				<div class="col-xs-3">
					<label class="control-label">留言日期：</label>
				</div>
				<div id="dateRangeId" class="dateRange form-group"
					style="height: 30px;">
					<input type="hidden" id="dtfmt" class="dateFmt"
						value="info@yyyy-MM-dd" />
					<div class="col-xs-8">
				
					<div class="col-xs-5">
						<div class="input-group" style="margin-right: 10px;">
							<input type="text" id="fixed_start"
								class="laydate-start form-control input-sm" readonly="readonly">
							<span class="laydate-start-span input-group-addon m-ui-addon">
								<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
							</span>
						</div>
						
					</div>
					<div class="col-xs-5">
						<span style="float: left; margin-top: 4px; margin-left: -2px;">--</span>
						<div class="input-group" style="padding-left: 5px;">
							<input type="text" id="fixed_end"
								class="laydate-end form-control input-sm" readonly="readonly">
							<span class="laydate-end-span input-group-addon m-ui-addon">
								<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
							</span>
						</div>
					</div>
				</div>
				</div>
				<div class="col-xs-2">
					<button  id="chooseLiuYanBtn" class="btn btn-sm btn-default" style="background: #eee">留言筛选</button>
				</div>
			</div>

			<div class="m-ui-title4" style="margin-top: 20px">
				<h2>留言</h2>
			</div>
			<div class="row row-mar">
				<div class="col-xs-1" style="width: 50px;">
					<ul class="nav nav-pills name-group pull-left">
						<li class="active"><a href="#">全部</a></li>
					</ul>
				</div>
				<div id="postAndPersonDiv" class="col-xs-11">
			
				</div>
			</div>

			<div class="row text-right">
				<button id="addLiuYanBtn" class="btn btn-info btn-sm" style="width: 150px">我要留言</button>
				<button id="topBtn" class="btn btn-primary btn-sm">置顶 </button>
				<button id="untopBtn" class="btn btn-primary btn-sm">取消置顶</button>
				<button id="deleteLiuYanBtn" class="btn btn-danger btn-sm">删除</button>
				<button id="refreshBtn" class="btn btn-primary btn-sm">
					<span class="glyphicon glyphicon-refresh mar-right"></span>刷新
				</button>
			</div>
			<div id= "topAndPersonAndRoleTableDiv">
			<table id="topAndPersonAndRoleTable" class="table table-bordered table-hover m-ui-table-whole"
				cellspacing="0">
			</table>
			</div>
			<div id="tableIdDiv">
			<table id="tableId" class="table table-bordered table-hover m-ui-table-whole"
				cellspacing="0">
			</table>
			</div>
		</div>
		<!--内容end-->
	</div>

	<script type="text/javascript"
		src="<%=context%>/script/zalyfk/zaFeedbackUserLayer.js"></script>
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