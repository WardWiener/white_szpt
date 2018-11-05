<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
</head>
<body class="">
	<div class="text-center" style="padding: 20px">
		<button id="btn1" divId="data1" class="btn btn-danger">基本信息</button>
		<button id="btn2" divId="data2" class="btn btn-primary">案件关联信息</button>
	</div>
	<input type="hidden" id="dataId"
		value=<%=request.getParameter("dataId")%>>
	<div style="padding: 20px;">
		<div id="data1"
			style="width: 1000px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;"
			class="dataDiv m-ui-layer-content">
			<table class="table table-sg personInfo" cellspacing="0" width="100%">
				<tbody>
					<tr>
						<td class="td-left" width="15%">人员编号</td>
						<td width="30%" valName="personId" class="valCell"></td>
						<td class="td-left">性别</td>
						<td valName="sex" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">姓名</td>
						<td>
							<h2 valName="name" class="valCell font24">张玉侠</h2>
						</td>
						<td class="td-left">别名</td>
						<td valName="aliasName" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">曾用名</td>
						<td valName="usedName" class="valCell"></td>
						<td class="td-left">绰号</td>
						<td valName="nickName" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">身份证号</td>
						<td valName="idcardNo" class="valCell"></td>
						<td class="td-left">婚否</td>
						<td valName="ifMarry" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">出生日期起</td>
						<td valName="birthdayStart" class="valCell"></td>
						<td class="td-left">出生日期止</td>
						<td valName="birthdayEnd" class="valCell"></td>

					</tr>

					<tr>
						<td class="td-left">QQ号码</td>
						<td valName="qqNo" class="valCell"></td>
						<td class="td-left">电子邮箱 <span
							class="micon-lg glyphicon glyphicon-envelope pull-right"></span>
						</td>
						<td valName="email" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">联系电话<span
							class="micon-lg glyphicon glyphicon-phone pull-right"></span>
						</td>
						<td valName="telephone" class="valCell"></td>
						<td class="td-left">籍贯国籍</td>
						<td valName="nativePlace" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">工作单位</td>
						<td valName="employUnit" class="valCell"></td>
						<td class="td-left">工作单位地址</td>
						<td valName="employAdress" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">现住址</td>
						<td valName="address" class="valCell"></td>
						<td class="td-left">现住地详细地址</td>
						<td valName="addressDetail" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">出生地</td>
						<td valName="birthCode" class="valCell"></td>
						<td class="td-left">出生地详细住址</td>
						<td valName="birthDetail" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">户籍地</td>
						<td valName="door" class="valCell"></td>
						<td class="td-left">户籍地详细地址</td>
						<td valName="doorDetail" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">民族</td>
						<td valName="nation" class="valCell"></td>
						<td class="td-left">口音</td>
						<td valName="tone" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">文化程度</td>
						<td valName="culture" class="valCell"></td>
						<td class="td-left">政治面貌</td>
						<td valName="politics" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">宗教信仰</td>
						<td valName="faith" class="valCell"></td>
						<td class="td-left">其他专长</td>
						<td valName="otherspecialty" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">情况说明</td>
						<td colspan="3">
							<div class="alert alert-info">
								<span valName="thingExplain" class="valCell"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td class="td-left">职业</td>
						<td valName="job" class="valCell"></td>
						<td class="td-left">特殊身份</td>
						<td valName="specialIdentity" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">身高</td>
						<td valName="staturest" class="valCell"></td>
						<td class="td-left">体型</td>
						<td valName="bodilyForm" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">体重</td>
						<td valName="avoirdupois" class="valCell"></td>
						<td class="td-left">足长</td>
						<td valName="footSize" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">鞋号</td>
						<td valName="shoesSize" class="valCell"></td>
						<td class="td-left">血型</td>
						<td valName="bloodType" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">是否会驾驶</td>
						<td valName="ifDrive" class="valCell"></td>
						<td class="td-left">是否经常上网</td>
						<td valName="ifOftenNet" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">嗜好</td>
						<td valName="addiction" class="valCell"></td>
						<td class="td-left">职务</td>
						<td valName="headShip" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">脸型</td>
						<td valName="faceForm" class="valCell"></td>
						<td class="td-left">人员状态</td>
						<td valName="personState" class="valCell"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="data2"
			style="display: none; width: 1000px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;"
			class="dataDiv m-ui-layer-content">
			<table class="table table-sg bjrTable" cellspacing="0" width="100%">
				<tbody>
					<tr>
						<td class="td-left" width="15%">涉案类型</td>
						<td width="30%" valName="relation" class="valCell"></td>
						<td class="td-left" width="15%">受害程度</td>
						<td valName="receivePSN" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">受害形式</td>
						<td valName="reportMode" class="valCell"></td>
						<td class="td-left">受害形式2</td>
						<td valName="reportMode2" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">受侵害时间上限</td>
						<td valName="aggrievedTimeLimit" class="valCell"></td>
						<td class="td-left">受侵害时间下限</td>
						<td valName="aggrievedTimeLower" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">损伤及病理特征</td>
						<td valName="damageFeature" class="valCell"></td>
						<td class="td-left">与受害人关系</td>
						<td valName="repnexusdis" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">致死伤工具</td>
						<td valName="injuryTools" class="valCell"></td>
						<td class="td-left">致死伤原因</td>
						<td valName="injuryCause" class="valCell"></td>
					</tr>

				</tbody>
			</table>
		</div>
	</div>

</body>
<script type="text/javascript"
	src="<%=context%>/scripts/yayd/scrInfo.js"></script>
</html>