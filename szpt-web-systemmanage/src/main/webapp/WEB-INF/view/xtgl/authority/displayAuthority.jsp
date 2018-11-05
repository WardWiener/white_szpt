<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<script>
	(function($) {
		"use strict";
	})(jQuery);
</script>
<style type="text/css">

</style>
</head>

<body class="m-ui-layer-body" style="color:#333">
			<div id="c-center-right-content-con" style="margin-left: 0">
				<div class="m-ui-layer-searchbox" style="padding-bottom: 0;">
					<input id="powerId" type="hidden" value="">
					<div class="row row-mar mar-left mar-right"style="margin-left:60px;margin-right:60px;">
						<table class="table table-sg" cellspacing="0" width="100%"
							style="margin-bottom: 0">
							<tbody>
								<tr>
									<td class="td-left" width="20%" style="color:#333">权限名称</td>
									<td class="word-break" width="20%" id="powerName" style="background:none;color:#333"></td>
									<td class="td-left" width="20%" style="color:#333">权限编码</td>
									<td width="20%" id="powerCategory" style="background:none;color:#333"></td>
								</tr>
							</tbody>
						</table>
						<div class="m-ui-title3  mar-top">
							<h2>资源详情</h2>
						</div>
						<div class="m-ui-table">
						  <table id="powerTable" class="table table-bordered table-hover m-ui-table-whole"
							cellspacing="0" width="100%"  >
						  </table>
						</div>
					</div>
					<!--基本信息end-->
				</div>
				<!--弹出层列表内容end-->
			</div>
	
	<script type="text/javascript"
		src="<%=context%>/scripts/xtgl/authority/displayAuthority.js"></script>
</body>


</html>