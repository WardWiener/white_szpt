<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<script>
	$(document).ready(function() {

	});
</script>
</head>

<body id="validformId" class="m-ui-layer-body validform">
	<div class="m-ui-table m-ui-table-sm">
		<table id="people" class="table table-hover m-ui-table-no-paginate table-condensed"
			cellspacing="0" width="100%">
			<thead>
				<tr>
					<th align="center" colspan="3"><h4 align="center" id="name"></h4></th>
				</tr>
			</thead>

			<tbody>
			</tbody>
		</table>

	</div>

	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
	<script type="text/javascript"
		src="<%=context%>/scripts/homePage/positionInfo.js"></script>
</body>
</html>