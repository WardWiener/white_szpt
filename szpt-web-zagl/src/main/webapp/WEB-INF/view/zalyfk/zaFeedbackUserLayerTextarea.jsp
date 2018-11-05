<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>

</head>
<body class="m-ui-layer-body">
	<div class="m-ui-layer-box" style="width: 600px;">
		<div class="m-ui-layer-content">
			<h2 class="font16 text-center" id="titleH2" style="padding: 10px"></h2>
			<div class="row alert"
				style="background: #f8f8f8; padding: 15px; margin-top: 10px; padding-left: 0; padding-right: 0;">
				<div class="col-xs-2">
					<label class="control-label">留言内容：</label>
				</div>
				<div id="contentDiv" class="col-xs-6 validform" >
					<textarea id="contentText" class="form-control valiform-keyup form-val" datatype="*1-80" errormsg="请输入1-80个字符" rows="7"></textarea>
				</div>
				<div class="col-xs-4">
					<button id="publishBtn" class="btn btn-info btn-sm" style="width: 80px">发表留言</button>
					<button id="cancelBtn" class="btn btn-default btn-sm">取消</button>
				</div>
			</div>
			<div class="text-right">
				<span class="pull-left font16">我的留言</span>
				<button id="deleteLiuYanBtn" class="btn btn-danger btn-sm">删除</button>
				<button id="refreshBtn" class="btn btn-primary btn-sm">
					<span class="glyphicon glyphicon-refresh mar-right"></span>刷新
				</button>
			</div>
			
			
			<table id="tavleId" class="table table-bordered table-hover m-ui-table-whole"
				cellspacing="0">

			</table>
		</div>
		
	</div>
	<script>
		//var zaId = "${param.id}"
		var zaId = "1";
	</script>

	<script type="text/javascript"
		src="<%=context%>/script/zalyfk/zaFeedbackUserLayerTextarea.js"></script>
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