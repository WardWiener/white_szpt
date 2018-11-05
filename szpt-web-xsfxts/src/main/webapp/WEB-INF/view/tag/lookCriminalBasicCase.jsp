<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="col-xs-8" style="width: 100%; float: left;">
	<div style="padding-right: 370px">
		<div id="tabs" class="m-ui-tabs" style="background: none;">
			<ul class="nav nav-tabs">
				<li id="detailLi"><a href="#tabs-1">基本信息</a></li>
				<li id="detailAlm"><a href="#tabs-2">接处警</a></li>
				<li id="detailXYR"><a href="#tabs-3">嫌疑人</a></li>
				<li id="detailJZ"><a href="#tabs-4">问询笔录</a></li>
				<li id="detailObj"><a href="#tabs-5">涉案物品</a></li>
				<li id="detailJZ"><a href="#tabs-6">卷宗</a></li>
			</ul>
			<!-- 案件基本信息 -->
			<div id="tabs-1">
				<br />
				<table class="table table-sg caseTable" cellspacing="0" width="100%">
					<tbody>
						<tr>
							<td class="td-left" width="15%">案件编号</td>
							<td width="30%" name="caseCode" class="form-val"></td>
							<td class="td-left" width="15%">案件名称</td>
							<td width="30%" name="caseName" class="form-val"></td>
						</tr>
						<tr>
							<td class="td-left">案件文号</td>
							<td name=""></td>
							<td class="td-left">案件类型</td>
							<td name="caseTypeName"></td>
						</tr>
						<tr>
							<td class="td-left">案件性质</td>
							<td name="caseKindName"></td>
							<td class="td-left">案件状态</td>
							<td name="caseStateName"></td>
						</tr>
						<tr>
							<td class="td-left">案由</td>
							<td name=""></td>
							<td class="td-left">发案地点 <span
								class="micon-lg glyphicon glyphicon-map-marker pull-right"></span>
							</td>
							<td name="caseAddress"></td>
						</tr>
						<tr>
							<td class="td-left">发现时间 <span
								class="micon-lg glyphicon glyphicon-time pull-right"></span>
							</td>
							<td>起：
								<span name="discoverTimeStart"></span> 
								<br>止：
								<span name="discoverTimeEnd"></span>
							</td>
							<td class="td-left">发案时间 <span
								class="micon-lg glyphicon glyphicon-time pull-right"></span>
							</td>
							<td>起：
								<span name="caseTimeStart"></span>
								<br>止：
								<span name="caseTimeEnd"></span>
							</td>

						</tr>
						<tr>
							<td class="td-left">涉及国家地区</td>
							<td name="country"></td>
							<td class="td-left">发案地行政区划</td>
							<td name="district"></td>
						</tr>
						<tr>
							<td class="td-left">发案社区</td>
							<td name="community"></td>
							<td class="td-left">案情关键词</td>
							<td name="caseKeyword"></td>
						</tr>
						<tr>
							<td class="td-left">简要案情</td>
							<td colspan="3">
								<div class="alert td-left alert-info">
									<span name="details"></span>
								</div>
							</td>
						</tr>
						<tr>
							<td class="td-left">是否涉密案件</td>
							<td name=""></td>
							<td class="td-left">当前办理单位</td>
							<td name="handleUnit"></td>
						</tr>
						<tr>
							<td class="td-left">涉案总价值经侦专用</td>
							<td name="lossValue"></td>
							<td class="td-left">现勘编号</td>
							<td name="kno"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- 接处警信息 -->
			<div id="tabs-2">
				<br />
				<table class="table alarmTable table-sg" cellspacing="0" width="100%">
					<tbody>
						<tr>
							<td class="td-left" width="15%">报警电话 <span
								class="micon-lg glyphicon glyphicon-earphone pull-right"></span>
							</td>
							<td width="30%" name="callingPersonPhone"></td>
							<td width="15%" class="td-left">警情类别</td>
							<td width="30%" name="jqlxName"></td>
						</tr>
						<tr>
							<td class="td-left" width="15%">接警单位</td>
							<td width="30%" name="pcsName"></td>
							<td class="td-left">接警时间 <span
								class="micon-lg glyphicon glyphicon-time pull-right"></span>
							</td>
							<td name="answerAlarmDate"></td>
						</tr>
						<!-- <tr>
							<td class="td-left">出动船支（次）</td>
							<td valName="boatNum" class="valCell"></td>
							<td class="td-left">出动机动车（次）</td>
							<td valName="voitureNum" class="valCell"></td>
						</tr>
						<tr>
							<td class="td-left">出动警力数</td>
							<td valName="policeNum" class="valCell"></td>
							<td class="td-left">出动协勤数</td>
							<td valName="policeAssNum" class="valCell"></td>
						</tr>
						<tr>
							<td class="td-left">处警单位</td>
							<td valName="disposeUnit" class="valCell"></td>
							<td class="td-left">处警结果</td>
							<td valName="disposeResult" class="valCell"></td>
						</tr>
						<tr>
							<td class="td-left">处警简要情况</td>
							<td colspan="3">
								<div class="alert td-left alert-info">
									<span valName="disposeDetails" class="valCell"></span>
								</div>
							</td>
						</tr>
						<tr>
							<td class="td-left">处警人1</td>
							<td valName="disposePerson" class="valCell"></td>
							<td class="td-left">处警人2</td>
							<td valName="disposePerson2" class="valCell"></td>
						</tr>
						<tr>
							<td class="td-left">处警意见</td>
							<td valName="disposeOpinion" class="valCell"></td>
							<td class="td-left">到达现场时间 <span
								class="micon-lg glyphicon glyphicon-time pull-right"></span>
							</td>
							<td valName="arriveTime" class="valCell"></td>

						</tr>
						<tr>
							<td class="td-left">解救被拐卖儿童数</td>
							<td valName="rescueyNum" class="valCell"></td>
							<td class="td-left">解救被拐卖妇女数</td>
							<td valName="rescuewNum" class="valCell"></td>
						</tr>
						<tr>
							<td class="td-left">解救人质数</td>
							<td valName="rescurhNum" class="valCell"></td>
							<td class="td-left">救助群众</td>
							<td valName="salvationPeople" class="valCell"></td>
						</tr>
						<tr>
							<td class="td-left">救助伤员</td>
							<td colspan="3" valName="salvationWou" class="valCell"></td>
						</tr>
						<tr>
							<td class="td-left">留置审查人数</td>
							<td valName="censorNum" class="valCell"></td>
							<td class="td-left">是否现场处罚</td>
							<td valName="ifSpotfine" class="valCell"></td>
						</tr>
						<tr>
							<td class="td-left">是否现场调解</td>
							<td valName="ifSpotReconcile" class="valCell"></td>
							<td class="td-left">辖区</td>
							<td valName="popedom" class="valCell"></td>
						</tr> -->
					</tbody>
				</table>
			</div>
			<!-- 嫌疑人信息 -->
			<div id="tabs-3">
				<div id="criminalPersonNames" style="padding:15px;">
					
				</div>
				<table class="table table-sg" cellspacing="0" width="100%">
					<tbody>
						<tr>
							<td class="td-left" width="15%">人员编号</td>
							<td width="30%" name="personId"></td>
							<td class="td-left" width="15%">性别</td>
							<td width="30%" name="sex"></td>
						</tr>
						<tr>
							<td class="td-left">姓名</td>
							<td>
								<h2 name="name" class="font24"></h2>
							</td>
							<td class="td-left">别名</td>
							<td name="aliasName"></td>
						</tr>
						<tr>
							<td class="td-left">曾用名</td>
							<td name=""></td>
							<td class="td-left">绰号</td>
							<td name="nickName"></td>
						</tr>
						<tr>
							<td class="td-left">身份证号</td>
							<td name="idcardNo"></td>
							<td class="td-left">婚否</td>
							<td name="ifMarry"></td>
						</tr>
						<tr>
							<td class="td-left">出生日期起</td>
							<td name="birthday"></td>
							<td class="td-left">出生日期止</td>
							<td name=""></td>
						</tr>
						<tr>
							<td class="td-left">QQ号码</td>
							<td name="qqNo"></td>
							<td class="td-left">电子邮箱 <span
								class="micon-lg glyphicon glyphicon-envelope pull-right"></span>
							</td>
							<td name="email"></td>
						</tr>
						<tr>
							<td class="td-left">联系电话<span
								class="micon-lg glyphicon glyphicon-phone pull-right"></span>
							</td>
							<td name="telephone"></td>
							<td class="td-left">籍贯国籍</td>
							<td name="nativePlace"></td>
						</tr>
						<tr>
							<td class="td-left">工作单位</td>
							<td name="employUnit"></td>
							<td class="td-left">工作单位地址</td>
							<td name="employAdress"></td>
						</tr>
						<tr>
							<td class="td-left">现住址</td>
							<td name="address"></td>
							<td class="td-left">现住地详细地址</td>
							<td name="addressDetail"></td>
						</tr>
						<tr>
							<td class="td-left">出生地</td>
							<td name="birthDistrict"></td>
							<td class="td-left">出生地详细住址</td>
							<td name="birthDetail"></td>
						</tr>
						<tr>
							<td class="td-left">户籍地</td>
							<td name="door"></td>
							<td class="td-left">户籍地详细地址</td>
							<td name="doorDetail"></td>
						</tr>
						<tr>
							<td class="td-left">民族</td>
							<td name="nation"></td>
							<td class="td-left">特殊身份</td>
							<td name="specialIdentity"></td>
						</tr>
						<tr>
							<td class="td-left">文化程度</td>
							<td name="culture"></td>
							<td class="td-left">政治面貌</td>
							<td name="politics"></td>
						</tr>
						<tr>
							<td class="td-left">宗教信仰</td>
							<td name="faith"></td>
							<td class="td-left">其他专长</td>
							<td name=""></td>
						</tr>
						<tr>
							<td class="td-left">情况说明</td>
							<td colspan="3">
								<div class="alert td-left alert-info">
									<span name=""></span>
								</div>
							</td>
						</tr>
						<tr>
							<td class="td-left">职业</td>
							<td name="job"></td>
							<td class="td-left">职务</td>
							<td name="headShip"></td>
						</tr>
						<tr>
							<td class="td-left">脸型</td>
							<td name="faceform"></td>
							<td class="td-left">人员状态</td>
							<td name=""></td>
						</tr>
						<tr>
							<td colspan="6" class="td-left alert-info row-mar0">体貌特征</td>
						</tr>
						<tr>
							<td class="td-left">身高</td>
							<td name="staturest"></td>
							<td class="td-left">体重</td>
							<td name="avoirdupois"></td>
						</tr>
						<tr>
							<td class="td-left">体型</td>
							<td name="bodilyForm"></td>
							<td class="td-left">血型</td>
							<td name="bloodType"></td>
						</tr>
						<tr>
							<td class="td-left">鞋号</td>
							<td name="shoesSize"></td>
							<td class="td-left">足长</td>
							<td name="footSize"></td>
						</tr>
						<tr>
							<td class="td-left">口音</td>
							<td name="tone"></td>
							<td class="td-left">嗜好</td>
							<td name="addiction"></td>
						</tr>
						<tr>
							<td class="td-left">是否会驾驶</td>
							<td name=""></td>
							<td class="td-left">是否经常上网</td>
							<td name=""></td>
						</tr>
					</tbody>
				</table>

			</div>
			<!-- 问询笔录信息 -->
			<div id="tabs-4">
				<ul id="crimialCaseNotes" class="list-news">
			       
			    </ul>
			</div>
			<!-- 涉案物品信息 -->
			<div id="tabs-5">
				<div id="criminalObjectNames" style="padding:15px;">
					
				</div>
				<table class="table table-sg" cellspacing="0" width="100%">
					<tbody>
						<tr>
							<td class="td-left" width="15%">物品名称</td>
							<td width="30%" name="name"></td>
							<td class="td-left" width="15%">物品编号</td>
							<td width="30%" name="objid"></td>
						</tr>
						<tr>
							<td class="td-left">物品数量</td>
							<td name="amounts"></td>
							<td class="td-left">数量单位</td>
							<td name="amountUnit"></td>
						</tr>
						<tr>
							<td class="td-left">重量</td>
							<td name="weight"></td>
							<td class="td-left">重量单位</td>
							<td name="weightUnit"></td>
						</tr>
						<tr>
							<td class="td-left">价值</td>
							<td name="value"></td>
							<td class="td-left">型号</td>
							<td name="model"></td>
						</tr>
						<tr>
							<td class="td-left">物品状态</td>
							<td name="itemStatus"></td>
							<td class="td-left">品牌</td>
							<td name="tradeMark"></td>
						</tr>

						<tr>
						<tr>
							<td class="td-left">产地</td>
							<td name="producingArea"></td>
							<td class="td-left">购买地址</td>
							<td name="purchaseAddress"></td>
						</tr>
						<tr>
							<td class="td-left">购买日期</td>
							<td name="purchaseDate"></td>
							<td class="td-left">号码(车牌号)</td>
							<td name="serialNumber"></td>
						</tr>
						<tr>
							<td class="td-left">特征描述及备注</td>
							<td name="annex"></td>
							<td class="td-left">其他特征</td>
							<td name="otherFeature"></td>
						</tr>
						<tr>
							<td class="td-left">物品类型</td>
							<td name="type"></td>
							<td class="td-left">物品成色</td>
							<td name="quality"></td>
						</tr>
						<tr>
							<td class="td-left">颜色1</td>
							<td name="color1"></td>
							<td class="td-left">颜色2</td>
							<td name="color2"></td>
						</tr>
						<tr>
							<td class="td-left">颜色3</td>
							<td colspan="3" name="color3"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- 卷宗信息 -->
			<div id="tabs-6">
				<ul id="archivedFiles" class="list-news">
			       
			    </ul>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/tag/lookCriminalBasicCase.js"></script>