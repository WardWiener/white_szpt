<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
</head>
<body class="">
	<input type="hidden" id="dataId"
				value=<%=request.getParameter("dataId")%>>
	<div style="padding: 20px;" >
		<div style="width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="dataDiv m-ui-layer-content">
			<table class="table table-sg criminalObjectTable" cellspacing="0" width="100%">
				<tbody>
					<tr>
						<td class="td-left" width="15%">物品名称</td>
						<td width="30%" valName="objectName" class="valCell"></td>
						<td class="td-left" width="15%">物品编号</td>
						<td valName="objid" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">物品数量</td>
						<td valName="amounts" class="valCell"></td>
						<td class="td-left">数量单位</td>
						<td valName="amountUnit" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">重量</td>
						<td valName="weight" class="valCell"></td>
						<td class="td-left">重量单位</td>
						<td valName="weightUnit" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">价值</td>
						<td valName="value" class="valCell"></td>
						<td class="td-left">型号</td>
						<td valName="model" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">物品状态</td>
						<td valName="itemStatus" class="valCell"></td>
						<td class="td-left">品牌</td>
						<td valName="tradeMark" class="valCell"></td>
					</tr>

					<tr>
					<tr>
						<td class="td-left">产地</td>
						<td valName="producingArea" class="valCell"></td>
						<td class="td-left">购买地址</td>
						<td valName="purchaseAdd" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">购买日期</td>
						<td valName="purchaseString" class="valCell"></td>
						<td class="td-left">号码(车牌号)</td>
						<td valName="serialNumber" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">特征描述及备注</td>
						<td valName="annex" class="valCell"></td>
						<td class="td-left">其他特征</td>
						<td valName="otherFeature" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">物品类型</td>
						<td valName="objectType" class="valCell"></td>
						<td class="td-left">物品成色</td>
						<td valName="quality" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">颜色1</td>
						<td valName="color1" class="valCell"></td>
						<td class="td-left">颜色2</td>
						<td valName="color2" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">颜色3</td>
						<td colspan="3" valName="color3" class="valCell"></td>
					</tr>

				</tbody>
			</table>
		</div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/yayd/coInfo.js"></script>
</html>