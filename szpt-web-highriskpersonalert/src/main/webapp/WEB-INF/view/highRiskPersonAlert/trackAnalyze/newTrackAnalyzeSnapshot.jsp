<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>

</head>
<body id="validform" class="validform m-ui-layer-body">
	<div class="m-ui-layer-box">
		<div class="m-ui-layer-content">
			<div class="row row-mar">
				<div class="col-xs-3">
					<label class="control-label">关联指令：</label>
				</div>
				<div class="col-xs-7">
					<select id="instruct" class="form-control input-sm select2-noSearch allowClear">
						
					</select>
				</div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-3">
					<label class="control-label">保存快照原因：<span class="red-star">*</span></label>
				</div>
				<div class="col-xs-7">
					<textarea id="snapshotInfo" class="form-control input-sm valiform-keyup" rows="6" datatype="*1-80" nullmsg="请填写保存快照原因" vf-position="1"></textarea>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=context%>/scripts/highRiskPersonAlert/trackAnalyze/newTrackAnalyzeSnapshot.js"></script>
</html>