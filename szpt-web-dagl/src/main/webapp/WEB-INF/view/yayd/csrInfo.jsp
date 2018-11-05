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
			<table class="table table-sg personForXYR" cellspacing="0"
				width="100%">
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
			<table class="table table-sg xyrTable" cellspacing="0" width="100%">
				<tbody>
					<tr>
						<td class="td-left" width="15%">嫌疑人类型</td>
						<td width="18%" valName="suspectType" class="valCell"></td>
						<td class="td-left" width="15%">案件角色</td>
						<td colspan="3" valName="crimeRole" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">嫌疑依据</td>
						<td valName="suspiciongist" class="valCell"></td>
						<td class="td-left">处理方式</td>
						<td width="18%" valName="approach" class="valCell"></td>
						<td class="td-left">人员状态</td>
						<td width="18%" valName="personState" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">抓获日期</td>
						<td valName="dateofCapture" class="valCell"></td>
						<td class="td-left">抓获经过</td>
						<td colspan="3" valName="captureProcess" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">案件处理种类</td>
						<td valName="caseTreatmentType" class="valCell"></td>
						<td class="td-left">特殊人群类型</td>
						<td colspan="3" valName="specialGroup" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left" width="15%">违法事实及依据</td>
						<td colspan="5" valName="criRecord" class="valCell"></td>
					</tr>
					<tr>
						<td colspan="6" class="alert-info row-mar0">批准逮捕</td>
					</tr>
					<tr>
						<td class="td-left">报捕时间</td>
						<td valName="requestarrestTime" class="valCell"></td>
						<td class="td-left">批准逮捕时间</td>
						<td valName="arrestApprovalTime" class="valCell"></td>
						<td class="td-left">执行逮捕时间</td>
						<td valName="arrestTime" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">是否批准逮捕</td>
						<td valName="isArrestApproval" class="valCell"></td>
						<td class="td-left">不批捕原因</td>
						<td colspan="3" valName="arrestRefuse" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">批准提请逮捕复议时间</td>
						<td valName="drewReviewTime" class="valCell"></td>
						<td class="td-left">批准提请逮捕复议人</td>
						<td valName="drewReviewer" class="valCell"></td>
						<td class="td-left">是否批准提请逮捕复议</td>
						<td valName="isfyApproval" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">提请逮捕复议时间</td>
						<td valName="arrestviewerTime" class="valCell"></td>
						<td class="td-left">提请逮捕复核时间</td>
						<td valName="arrestreviewTime" class="valCell"></td>
						<td class="td-left">批准逮捕提请复核人</td>
						<td valName="arrestReviewer" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">是否批准提请逮捕复核</td>
						<td valName="isfhApproval" class="valCell"></td>
						<td class="td-left">提请批捕时间</td>
						<td valName="" class="valCell"></td>
						<td class="td-left">是否提请批捕</td>
						<td valName="" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">逮捕复核时间</td>
						<td valName="toArrestTime" class="valCell"></td>
						<td class="td-left">复议后是否批准逮捕</td>
						<td valName="isfypzApproval" class="valCell"></td>
						<td class="td-left">复核后是否批捕</td>
						<td valName="isfhpzApproval" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">不批捕办理人</td>
						<td valName="arrestRefTransactor" class="valCell"></td>
						<td class="td-left">批捕复核办理人</td>
						<td valName="arreviewTransactor" class="valCell"></td>
						<td class="td-left">不批捕审核人</td>
						<td valName="arrestRefreviewed" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">批准逮捕审核人</td>
						<td valName="arrestReviewed" class="valCell"></td>
						<td class="td-left">批准逮捕批准人</td>
						<td valName="arrestApprover" class="valCell"></td>
						<td class="td-left">批准逮捕办理人</td>
						<td valName="arrestTransactor" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">不批捕批准人</td>
						<td valName="arrestRefApprover" class="valCell"></td>
						<td class="td-left">提请逮捕复议办理人</td>
						<td valName="arrvTransactor" class="valCell"></td>
					</tr>
					<tr>
						<td colspan="6" class="alert-info row-mar0">移送起诉</td>
					</tr>
					<tr>
						<td class="td-left">是否移送起诉</td>
						<td valName="isysqsApproval" class="valCell"></td>
						<td class="td-left">移送起诉时间</td>
						<td valName="prosecutedTime" class="valCell"></td>
						<td class="td-left">移送起诉罪名</td>
						<td valName="prosecutedCharge" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">不起诉原因</td>
						<td valName="notprosecuteReason" class="valCell"></td>
						<td class="td-left">移送起诉审核人</td>
						<td valName="prosecutReviewed" class="valCell"></td>
						<td class="td-left">移送起诉办理人</td>
						<td valName="prosecutTransactor" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">移送起诉批准人</td>
						<td colspan="5" valName="prosecutApproval" class="valCell"></td>
					</tr>
					<tr>
						<td colspan="6" class="alert-info row-mar0">刑拘拘留</td>
					</tr>
					<tr>
						<td class="td-left">刑拘批准时间</td>
						<td valName="xjApprovalTime" class="valCell"></td>
						<td class="td-left">刑拘批准人</td>
						<td valName="xjApprover" class="valCell"></td>
						<td class="td-left">是否批准刑拘</td>
						<td valName="isxjApproval" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">刑拘时间</td>
						<td valName="detentionTime" class="valCell"></td>
						<td class="td-left">拘留时限</td>
						<td valName="detentionLength" class="valCell"></td>
						<td class="td-left">拘留地点</td>
						<td valName="detentionAddress" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">延长拘留期限批准时间</td>
						<td valName="ycjlApprovalTime" class="valCell"></td>
						<td class="td-left">延长拘留期限批准人</td>
						<td valName="ycjlApprover" class="valCell"></td>
						<td class="td-left">是否批准延长拘留期限</td>
						<td valName="isycjlApproval" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">延长拘留期限时间</td>
						<td valName="ycDetentiomTime" class="valCell"></td>
						<td class="td-left">刑拘审核人</td>
						<td valName="xjReviewed" class="valCell"></td>
						<td class="td-left">刑拘办理人</td>
						<td valName="xjTransactor" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">延长拘留办理人</td>
						<td valName="ycjlTransactor" class="valCell"></td>
						<td class="td-left">延长拘留审核人</td>
						<td valName="ycjlReviewed" class="valCell"></td>
					</tr>

					<tr valTr="isqbhsApproval">
						<td colspan="6" class="alert-info row-mar0 valShow"
							valName="isqbhsApproval">取保候审</td>
					</tr>
					<tr valTr="isqbhsApproval">
						<td class="td-left">取保候审批准时间</td>
						<td valName="qbhsApprovalTime" class="valCell"></td>
						<td class="td-left">取保候审批准人</td>
						<td valName="qbhsApprover" class="valCell"></td>
						<td class="td-left">是否批准取保候审</td>
						<td valName="isqbhsApproval" class="valCell"></td>
					</tr>
					<tr valTr="isqbhsApproval">
						<td class="td-left">取保候审执行日期</td>
						<td valName="bailTime" class="valCell"></td>
						<td class="td-left">取保候审办理人</td>
						<td valName="qbhsTransactor" class="valCell"></td>
						<td class="td-left">取保候审审核人</td>
						<td valName="qbhsreviewed" class="valCell"></td>
					</tr>
					<tr valTr="isjsjzApproval">
						<td colspan="6" class="alert-info row-mar0 valShow"
							valName="isjsjzApproval">监视居住</td>
					</tr>
					<tr valTr="isjsjzApproval">
						<td class="td-left">监视居住批准时间</td>
						<td valName="jsjzApprovalTime" class="valCell"></td>
						<td class="td-left">监视居住批准人</td>
						<td valName="jsjzApprover" class="valCell"></td>
						<td class="td-left">监视居住执行日期</td>
						<td valName="surveillanceTime" class="valCell"></td>
					</tr>
					<tr valTr="isjsjzApproval">
						<td class="td-left">是否批准监视居住</td>
						<td valName="isjsjzApproval" class="valCell"></td>
						<td class="td-left">监视居住办理人</td>
						<td valName="jsjzTransactor" class="valCell"></td>
						<td class="td-left">监视居住审核人</td>
						<td valName="jsjzReviewed" class="valCell"></td>
					</tr>
					<tr>
						<td colspan="6" class="alert-info row-mar0">行政处罚</td>
					</tr>
					<tr>
						<td class="td-left">行政处罚批准时间</td>
						<td valName="adminPenaltyApptime" class="valCell"></td>
						<td class="td-left">行政处罚批准人</td>
						<td valName="adminPenaltyApper" class="valCell"></td>
						<td class="td-left">是否批准行政处罚</td>
						<td valName="isxzcfApproval" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">是否行政拘留</td>
						<td valName="isadminDetention" class="valCell"></td>
						<td class="td-left">是否行政拘留并罚款</td>
						<td valName="isFinesDetention" class="valCell"></td>
						<td class="td-left">行政拘留日期</td>
						<td valName="adminDetentionTime" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">是否罚款</td>
						<td valName="isFines" class="valCell"></td>
						<td class="td-left">罚款金额</td>
						<td valName="finesNo" class="valCell"></td>
						<td class="td-left">是否没收违法所得及违法财物</td>
						<td valName="isConfiscation" class="valCell"></td>

					</tr>
					<tr>
						<td class="td-left">是否责令停产停业</td>
						<td valName="isorderedcase" class="valCell"></td>
						<td class="td-left">是否暂扣吊销证照</td>
						<td valName="isWithheldLicenses" class="valCell"></td>
						<td class="td-left">是否警告(行政处罚)</td>
						<td valName="iswarningPunish" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">行政处罚办理人</td>
						<td valName="adminTransactor" class="valCell"></td>
						<td class="td-left">行政处罚审核人</td>
						<td valName="adminPenalReviewed" class="valCell"></td>
						<td class="td-left">行政处罚执行日期</td>
						<td valName="adminPenaltyTime" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">其他</td>
						<td valName="other" colspan="5" class="valCell"></td>
					</tr>

					<tr valTr="ifsqjd">
						<td colspan="6" class="alert-info row-mar0 valShow"
							valName="ifsqjd">责令社区戒毒</td>
					</tr>
					<tr valTr="ifsqjd">
						<td class="td-left">社区戒毒办理人</td>
						<td valName="sqjdRansactor" class="valCell"></td>
						<td class="td-left">社区戒毒审核人</td>
						<td valName="sqjdReviewer" class="valCell"></td>
						<td class="td-left">社区戒毒批准人</td>
						<td valName="sqjdApproved" class="valCell"></td>

					</tr>
					<tr valTr="ifsqjd">
						<td class="td-left">社区戒毒批准时间</td>
						<td valName="sqjdApprovalTime" class="valCell"></td>
						<td class="td-left">是否社区戒毒</td>
						<td valName="ifsqjd" class="valCell"></td>
						<td class="td-left">社区戒毒审核人</td>
						<td valName="sqjdReviewer" class="valCell"></td>
					</tr>
					<tr valTr="iszyDetoxif">
						<td colspan="6" class="alert-info row-mar0 valShow"
							valName="iszyDetoxif">强制隔离戒毒</td>
					</tr>
					<tr valTr="iszyDetoxif">
						<td class="td-left">是否自愿接受强制隔离戒毒</td>
						<td valName="iszyDetoxif" class="valCell"></td>
						<td class="td-left">强制隔离戒毒批准人</td>
						<td valName="detoxifApprover" class="valCell"></td>
						<td class="td-left">强制隔离戒毒办理人</td>
						<td valName="detoxifTransactor" class="valCell"></td>
					</tr>
					<tr valTr="iszyDetoxif">
						<td class="td-left">强制隔离戒毒审核人</td>
						<td colspan="5" valName="detoxifReviewed" class="valCell"></td>
					</tr>
					<tr>
						<td colspan="6" class="alert-info row-mar0">社区康复</td>
					</tr>
					<tr>
						<td class="td-left">社区康复办理人</td>
						<td valName="sqkfActor" class="valCell"></td>
						<td class="td-left">社区康复审核人</td>
						<td valName="sqkfReviewer" class="valCell"></td>
						<td class="td-left">社区康复批准人</td>
						<td valName="sqkfApproval" class="valCell"></td>
					</tr>
					<tr>
						<td class="td-left">社区康复批准时间</td>
						<td valName="sqkfApprovalTime" class="valCell"></td>
						<td class="td-left">是否社区康复</td>
						<td colspan="3" valName="issqkf" class="valCell"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

</body>
<script type="text/javascript"
	src="<%=context%>/scripts/yayd/csrInfo.js"></script>
</html>