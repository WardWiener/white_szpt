<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<!-- 公用常量页面 -->
<%@include file="/WEB-INF/view/instruction/common/constant.jsp"%>
<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript"
	src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/szpt/util/noDataInit.js"></script>
</head>

<body>
	<%@include file="/WEB-INF/base/skin/topPart.jsp"%>

	<div id="c-center">
		<div class="fixed-a">
			<%@include file="/WEB-INF/base/skin/leftPart-xsfxts.jsp"%>
		</div>
		<div id="c-center-right">

			<div id="c-center-right-content">

				<ol class="breadcrumb m-ui-breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">刑事案件分析</a></li>
					<li><a href="#">刑事案件一案一研判</a></li>
				</ol>
				<div class="m-ui-title1">
					<h1>刑事案件一案一研判</h1>
				</div>

				<div class="main-block">
					<div class="row row-mar">
						<div class="col-xs-3">
							<div class="col-xs-6">
								<label class="control-label">案件编号：</label>
							</div>
							<div class="col-xs-6">
								<input id="caseCode" class="form-control input-sm">
							</div>
						</div>
						<div class="col-xs-3">
							<div class="col-xs-6">
								<label class="control-label">案件名称：</label>
							</div>
							<div class="col-xs-6">
								<input id="caseName" class="form-control input-sm">
							</div>
						</div>
						<div class="col-xs-3">
							<div class="col-xs-6">
								<label class="control-label">打标状态：</label>
							</div>
							<div class="col-xs-6">
								<select id="ajdbztSelect" class="form-control input-sm select2-noSearch allowClear">
								
								</select>
							</div>
						</div>
					</div>
					<div class="row row-mar">
						<div class="col-xs-3">
							<div class="col-xs-6">
								<label class="control-label">案件类别：</label>
							</div>
							<div class="col-xs-6">
								<input type="text" id="ajlbTree" class="form-control input-sm" readonly>
							</div>
						</div>
						<div class="col-xs-3">
							<div class="col-xs-6">
								<label class="control-label">案件性质：</label>
							</div>
							<div class="col-xs-6">
								<input type="text" id="ajxzTree" class="form-control input-sm" readonly>
							</div>
						</div>
						<div class="col-xs-3">
							<button id="query" class="btn btn-primary btn-sm" style="margin-left: 30px">查询</button>
							<button id="reset" class="btn btn-default btn-sm">重置</button>
						</div>
					</div>

					<div class="row">
						<button id="lookYayd" class="btn btn-primary lookYayd">查看详情</button>
					</div>
					<table id="caseTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
						
					</table>
					<p class="color-gray" style="margin-top: 10px;">
						图标说明： 
						<span class="glyphicon glyphicon-star font16 color-red2 mar-right"></span>已打标的案件&nbsp;&nbsp;&nbsp;
					</p>
				</div>

				<div id="caseInfoDiv" class="row main-block  light-block-er" style="display:none;">
					<!-----------------tabs--------------------->
					<div id="tabs" class="m-ui-tabs" style="background: none;">
						<ul class="nav nav-tabs">
							<li><a href="#tabs-1">警情基本信息</a></li>
							<li><a href="#tabs-2">警情研判结果</a></li>
							<li><a href="#tabs-3">案件基本信息</a></li>
							<li><a href="#tabs-4">问讯笔录</a></li>

						</ul>
						<div id="tabs-1">
							<h3 id="alarmNullInfo" style="text-align: center;padding: 10px;">无此信息</h3>
							<table id="alarmInfoTable" class="table table-sg" cellspacing="0" width="100%">
								<tbody>
									<tr>
										<td class="td-left" width="100">警情名称</td>
										<td colspan="3" name="name"></td>
									</tr>
									<tr>
										<td class="td-left">警情内容</td>
										<td colspan="3" name="jqSummary"></td>
									</tr>
									<tr>
									<!-- <tr>
										<td class="td-left">追加内容</td>
										<td colspan="3">无追加内容</td>
									</tr> -->
									<tr>
										<td class="td-left">发生地点</td>
										<td colspan="3" name="addr"></td>
									</tr>
									<tr>
										<td class="td-left">警情编码</td>
										<td name="code"></td>
										<td class="td-left" width="100">发生时间</td>
										<td name="occurrenceTime"></td>
									</tr>
									<tr>
										<td class="td-left">接警单位</td>
										<td name="pcsName"></td>
										<td class="td-left">社区</td>
										<td colspan="3" name="countryName"></td>
									</tr>
									<tr>
										<td class="td-left">接警时间</td>
										<td name="answerAlarmDate"></td>
										<td class="td-left">警情来源</td>
										<td name="jqSource"></td>
									</tr>
									<tr>
										<td class="td-left">报警人</td>
										<td name="callingPerson"></td>
										<td class="td-left">联系电话</td>
										<td name="callingPersonPhone"></td>
									</tr>
									<tr>
										<td class="td-left">紧急程度</td>
										<td name="urgencyLevel"></td>
										<td class="td-left">警情类别</td>
										<td name="jqlxName"></td>
									</tr>
									<!-- <tr>
										<td class="td-left">附件</td>
										<td colspan="3"></td>
									</tr> -->
								</tbody>
							</table>
						</div>
						<!--tabs-1-->
						<div id="tabs-2">
							<h3 id="judgeResultNullInfo" style="text-align: center;padding: 10px;">无此信息</h3>
							<div id="judgeResultInfo">
								<div class="col-xs-8" style="width: 100%; float: left;">
									<div style="padding-right: 470px">
										<div class="m-ui-title4" style="margin-top: 10px">
											<h2 class="color-yellow1">刑侦接警反馈记录</h2>
										</div>
										<table id="alarmAndFeedbackInfo" class="table table-sg table-sg-sm" cellspacing="0">
											<tbody>
												<tr>
													<td class="td-left" width="100">反馈警情类型</td>
													<td width="30%" name="jqlxName" class="tabs-2-jq"></td>
													<td class="td-left" width="100">警情地址</td>
													<td name="addr" class="tabs-2-jq"></td>
												</tr>
												<tr>
													<td class="td-left">所属村居</td>
													<td name="pcsNameAndcountryName" class="tabs-2-jq"></td>
													<td class="td-left">反馈内容</td>
													<td id="feedbackContent" class="tabs-2-jq"></td>
												</tr>
											</tbody>
										</table>
										
										<!----------更多反馈弹出层------------->
										<div id="con-gengduoFeedback" style="display: none; width: 600px; height: 300px; margin-bottom: 0;">
											<h4 class="alert alert-info font16 text-center"
												style="padding: 10px;">更多反馈详情</h4>
											<div class="row row-mar">
												<table id="gdFeedbackTable" class="table table-bordered table-hover m-ui-table-no-paginate" cellspacing="0">
													<thead>
														<tr>
															<td width="10%">序号</td>
															<td width="10%">反馈人</td>
															<td width="20%">反馈时间</td>
															<td width="15%">反馈单位</td>
															<td width="65%">反馈内容</td>
														</tr>
													</thead>
													<tbody>
														
													</tbody>
												</table>
											</div>
										</div>
										<!----------更多反馈弹出层------------->
	
										<h3 class="row-mar font14 color-yellow2">现场情况</h3>
										<table id="" class="table table-sg table-sg-sm" cellspacing="0">
											<tbody>
												<tr>
													<td class="td-left" width="20%">周边摄像头</td>
													<td width="30%" name="cameras" class="tabs-2"></td>
													<td class="td-left"><p class="lineh">
															<span class="mar-right">现场无人员伤亡情况</span>、 <span
																class="mar-right">不跨区域</span>、 <span class="mar-right">不涉及敏感人物</span>、
															<span class="mar-right">非重大财产损失</span>
														</p></td>
												</tr>
											</tbody>
										</table>
	
										<h3 class="row-mar font14 color-yellow2">
											受害人情况<a href="###" id="btn-shouhairen" class="mar-left">更多</a>
										</h3>
										<table id="" class="table table-sg table-sg-sm" cellspacing="0">
											<tbody>
												<tr>
													<td class="td-left" width="20%">受侵害方式</td>
													<td width="30%" name="harmedWay" class="tabs-2"></td>
													<td class="td-left" width="20%">受害物品简要特征</td>
													<td name="materialChara" class="tabs-2"></td>
												</tr>
											</tbody>
										</table>
	
										<!----------受害人弹出层------------->
										<div id="con-shouhairen" style="display:none;width: 300px; height: 200px; margin-bottom: 0;">
											<h4 class="alert alert-info font16 text-center"
												style="padding: 10px;">受害人情况</h4>
											<div class="row row-mar">
												<div class="col-xs-4 m-label-left">姓名：</div>
												<div class="col-xs-7 tabs-2" name="victimName"></div>
											</div>
											<div class="row row-mar">
												<div class="col-xs-4 m-label-left">身份证号：</div>
												<div class="col-xs-7 tabs-2" name="victimId"></div>
											</div>
										</div>
										<!----------受害人弹出层------------->
										<script>
											$('#btn-shouhairen')
													.on(
															'click',
															function() {
																layer
																		.open({
																			type : 1,
																			title : false,
																			closeBtn : 1,
																			anim : 2,
																			shadeClose : true,
																			skin : 'layui-layer-nobg', //没有背景色
																			content : $('#con-shouhairen')
																		});
															});
										</script>
	
	
										<h3 class="row-mar font14 color-yellow2">
											嫌疑人情况 <a href="###" id="btn-xianyiren" class="mar-left">更多</a>
										</h3>
										<table id="" class="table table-sg table-sg-sm" cellspacing="0">
											<tbody>
												<tr>
													<td class="td-left" width="20%">逃窜方式</td>
													<td width="30%" name="runWay" class="tabs-2"></td>
													<td class="td-left" width="20%">逃窜方向</td>
													<td name="runDirect" class="tabs-2"></td>
												</tr>
												<tr>
													<td class="td-left">年龄段</td>
													<td width="30%" name="suspectAge" class="tabs-2"></td>
													<td class="td-left">性别</td>
													<td name="suspectSex" class="tabs-2"></td>
												</tr>
												<tr>
													<td class="td-left">体貌特征</td>
													<td colspan="3">
														<div class="row">
															<div class="col-xs-4">身高：<span name="suspectHeight" class="tabs-2"></span></div>
															<div class="col-xs-3">发型：<span name="suspectHair" class="tabs-2"></span></div>
															<div class="col-xs-3">体型：<span name="suspectBody" class="tabs-2"></span></div>
															<div class="col-xs-2">肤色：<span name="suspectSkin" class="tabs-2"></span></div>
														</div>
													</td>
												</tr>
												<tr>
													<td class="td-left">衣着特征</td>
													<td name="suspectclothChara" class="tabs-2"></td>
													<td class="td-left">随身物品特征</td>
													<td name="suspectCarryItemChara" class="tabs-2"></td>
												</tr>
											</tbody>
										</table>
										<!----------嫌疑人弹出层------------->
										<div id="con-xianyiren" style="display: none; width: 300px; height: 200px; margin-bottom: 0;">
											<h4 class="alert alert-info font16 text-center"
												style="padding: 10px;">嫌疑人情况</h4>
											<div class="row row-mar">
												<div class="col-xs-5 m-label-left">是否驾乘车辆：</div>
												<div class="col-xs-6 tabs-2" name="isDriving"></div>
											</div>
											<div class="row row-mar">
												<div class="col-xs-5 m-label-left">其他明显特征：</div>
												<div class="col-xs-6 tabs-2" name="suspectOtherChara"></div>
											</div>
										</div>
										<!----------嫌疑人弹出层------------->
										<script>
											$('#btn-xianyiren')
													.on(
															'click',
															function() {
																layer
																		.open({
																			type : 1,
																			title : false,
																			closeBtn : 1,
																			anim : 2,
																			shadeClose : true,
																			skin : 'layui-layer-nobg', //没有背景色
																			content : $('#con-xianyiren')
																		});
															});
										</script>
	
										<h3 class="row-mar font14 color-yellow2">现场音视频</h3>
										<div class="row">
											<div class="col-xs-7">
												<div class="photo-list video-list" style="margin-left: -7px">
													<ul class="list-news jcjsp openSp">
														
													</ul>
												</div>
											</div>
											<div class="col-xs-5">
												<div style="padding-left: 30px;">
													<ul class="list-news jcjyp openYp">
														
													</ul>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-xs-4"
									style="width: 430px; margin-left: -430px; float: left;">
									<div class="m-ui-title4" style="margin-top: 10px">
										<h2 class="color-yellow1">各单位警情研判结果</h2>
									</div>
									<table id="feedbackTable" class="table table-sg" cellspacing="0" width="100%">
										
									</table>
								</div>
							</div>
						</div>
						<!--tabs-2-->
						<div id="tabs-3">
							<div class="row">
								<div class="col-xs-8" style="width: 100%; float: left;">
									<div style="padding-right: 470px">
										<table class="table table-sg" cellspacing="0" width="100%">
											<tbody>
												<tr>
													<td class="td-left" width="100">案件编号</td>
													<td name="caseCode"></td>
													<td class="td-left" width="100">案件名称</td>
													<td name="caseName"></td>
												</tr>
												<tr>
													<td class="td-left">现场勘验编号</td>
													<td name="kno"></td>
													<td class="td-left">案件类别</td>
													<td name="caseSortName"></td>
												</tr>
												<tr>
													<td class="td-left">案件性质</td>
													<td name="caseKindName"></td>
													<td class="td-left">案件状态</td>
													<td name="caseStateName"></td>
												</tr>
												<tr>
													<td class="td-left">发现时间</td>
													<td>
														起：
														<span name="discoverTimeStart"></span> 
														<br>止：
														<span name="discoverTimeEnd"></span>
													</td>
													<td class="td-left">发案时间</td>
													<td>
														起：
														<span name="caseTimeStart"></span>
														<br>止：
														<span name="caseTimeEnd"></span>
													</td>
												</tr>
												<tr>
													<td class="td-left">国家地区</td>
													<td name="country"></td>
													<td class="td-left">发案区划</td>
													<td name="district"></td>
												</tr>
												<tr>
													<td class="td-left">发案辖区</td>
													<td name="region"></td>
													<td class="td-left">发案社区</td>
													<td name="community"></td>
												</tr>
												<tr>
													<td class="td-left">发案地点</td>
													<td colspan="3" name="caseAddress"></td>
												</tr>
												<tr>
													<td class="td-left">简要案情</td>
													<td colspan="3" name="details"></td>
												</tr>
												<tr>
													<td class="td-left">受伤人数</td>
													<td><span name="injuredCount"></span>人</td>
													<td class="td-left">死亡人数</td>
													<td><span name="deadCount"></span>人</td>
												</tr>
												<tr>
													<td class="td-left">损失总价值</td>
													<td><span name="lossValue"></span>元</td>
													<td class="td-left">案情关键词</td>
													<td name="caseKeyword"></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<div class="col-xs-4" style="width: 430px; margin-left: -430px; float: left;">
									<div id="suspectDetailsDiv">
										<div class="m-ui-title4" style="height:25px;">
											<h2 style="margin-top:5px;">嫌疑人</h2>
											<input id="suspectPageNum" type="hidden" value="0">
										</div>
										<div class="pull-right" style="margin-top: -45px; margin-right: 50px;">
											<button id="suspectLeft" class="btn btn-sm btn-primary" style="display:none;" title="上一页">
												<span class="glyphicon glyphicon-menu-left"></span>
											</button>
										</div>
										<div class="pull-right" style="margin-top: -45px; margin-right: 10px;" style="display:none;" title="下一页">
											<button id="suspectRight" class="btn btn-sm btn-primary">
												<span class="glyphicon glyphicon-menu-right"></span>
											</button>
										</div>
	
										<p>
											<span class="color-gray2">姓名：</span>
											<span name="name" class="suspectDetailsDiv"></span>
											<span style="margin-left: 30px;">
												<span class="color-gray2">性别：</span>
												<span name="sex" class="suspectDetailsDiv"></span>
											</span>
										</p>
										<p>
											<span class="color-gray2">曾用名：</span>
											<span name="aliasName" class="suspectDetailsDiv"></span>
										</p>
										<p class="pull-right" style="margin-top: -45px; margin-right: 10px;">
											<!-- <img src="../images/photo/photo-2.png" height="70" width="70"> -->
										</p>
										<p>
											<span class="color-gray2">身份证号：</span>
											<span name="idcardNo" class="suspectDetailsDiv"></span>
										</p>
										<p class="row-mar">
											<a id="suspectDetailsBut" href="javascript:void(0);">详情</a>
										</p>
									</div>
									<p style="background: #283e68; text-align: center">前科案件</p>
									<table class="table table-sg table-sg-sm" cellspacing="0" style="margin-bottom: 0">
										<thead>
											<tr style="background: #273d62">
												<th width="30%">案件编号</th>
												<th>案件名称</th>
												<th width="20%">案件状态</th>
												<th width="25%">发案时间</th>
											</tr>
										</thead>
									</table>
									<div style="max-height: 210px; overflow-x: hidden; overflow-y: auto;">
										<table id="suspectQkCaseTable" class="table table-sg table-sg-sm" cellspacing="0">
											<tbody>
												
											</tbody>
										</table>
									</div>

									<div class="m-ui-title4" style="margin-top: 20px">
										<h2>涉案物品</h2>
										<input id="objectPageNum" type="hidden" value="0">
									</div>
									<div class="pull-right" style="margin-top: -45px; margin-right: 50px;">
										<button id="objectLeft" class="btn btn-sm btn-primary" style="display:none;" title="上一页">
											<span class="glyphicon glyphicon-menu-left"></span>
										</button>
									</div>
									<div class="pull-right" style="margin-top: -45px; margin-right: 10px;" style="display:none;" title="下一页">
										<button id="objectRight" class="btn btn-sm btn-primary">
											<span class="glyphicon glyphicon-menu-right"></span>
										</button>
									</div>
									<div id="objectDetailsDiv">
										<p>
											<span class="color-gray2">物品编号：</span>
											<span name="objid"></span>
										</p>
										<p>
											<span class="color-gray2">物品特征：</span>
											<span name="feature"></span>
										</p>
										<p>
											<span class="color-gray2">物品名称：</span>
											<span name="objName"></span>
										</p>
										<p class="pull-right" style="margin-top: -45px; margin-right: 10px;">
											<!-- <img src="../images/photo/photo-2.png" height="70" width="70"> -->
										</p>
										<p>
											<span class="color-gray2">所在物证保管区：</span>
											<span name="area"></span>
										</p>
										<p>
											<span class="color-gray2">所在储物柜（架）：</span>
											<span name="locker"></span>
										</p>
										<p>
											<span class="color-gray2">对应文书：</span>
											<span name="paperCode"></span>
										</p>
										<p>
											<span class="color-gray2">所属人员姓名：</span>
											<span name="suspectName"></span>
										</p>
									</div>
								</div>
							</div>
						</div>
						<!--tabs-3-->
						<div id="tabs-4">
							<h3 id="noteNullInfo" style="text-align: center;padding: 10px;">无此信息</h3>
							<ul id="crimialCaseNotes" class="list-news">
			       
			   				</ul>
						</div>
						<!--tabs-4-->
					</div>
					<!-----------------tabs--------------------->


				</div>
				<!---------------------------------------->
				<div id="instructInfoDiv" class="main-block" style="display:none;">
					<div class="m-ui-title3">
						<h2>指令下发</h2>
					</div>
					<div class="row row-mar">
						<button id="addInstructionBut" class="btn btn-danger">指令下发</button>
						<!-- <button class="btn btn-primary">新增</button> -->
					</div>
					<table id="instructTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
						
					</table>
				</div>


				<div id="ypResultDiv" class="main-block" style="display:none;">
					<div class="m-ui-title3">
						<h2>研判结果反馈</h2>
					</div>
					<table id="judgeFeedbackTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
						
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="<%=context%>/scripts/instruction/instructionList.js"></script>
	<script type="text/javascript" src="<%=context%>/scripts/judge/caseJudge.js"></script>
	<%@include file="/WEB-INF/base/skin/footPart.jsp"%>
</body>

</html>