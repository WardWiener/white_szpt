<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript" src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/cascadedDicItem.js"></script>
 
</head>
<body id="cbaGradeTemplate" class="validform m-ui-layer-body">
	<div class="m-ui-layer-box validform" id="yanzheng" style="width:800px;">
<p class="row row-mar mar-top"><span class="col-xs-2 m-bold" style="padding-top:5px;">报告类别：</span>
<select class="form-control input-sm m-inline" style="width:200px" id="first">
       </select>
       <select id="bglx" class="form-control input-sm m-inline" style="width:200px">
       <option>全部</option>
       </select>
</p>
<p class="row row-mar"><span class="col-xs-2 m-bold"  style="padding-top:5px;">报告名称：<span class="red-star">*</span></span><input id="bgmc" datatype="*1-80" class="form-control input-sm m-inline" style="width:400px"></p>

		<div class="m-ui-layer-searchbox">
		<div class="row row-mar2">
			<div class="col-xs-2">
				<label class="control-label">资料上传：<span class="red-star">*</span></label>
			</div>
			<div class="col-xs-9">
				<div id="container" class="upload-control"></div>
				<span>（请上传一个word文档！）</span>
			</div>
		</div>
		</div>
	<!-- 	<div class="row" style="margin-top: 20px">
			<div class="col-xs-3">
				<label class="control-label">资料上传：</label>
			</div>
			<div class="col-xs-3">
				<div class="input-group">
					<div class="col-xs-8 upload-control" id="container">
					</div>
				</div>
			</div>			 
		</div>	 -->	
<div class="m-ui-commitbox">
<button class="btn btn-primary btn-lg" id="uploadBtn" >保存</button>
<button class="btn btn-default btn-lg" id="resBtn">返回</button>

</div>
      
<!--内容end-->

</div>
<script type="text/javascript"src="<%=context%>/script/wdza/zaUploadLayer.js"></script>
</body>
</html>