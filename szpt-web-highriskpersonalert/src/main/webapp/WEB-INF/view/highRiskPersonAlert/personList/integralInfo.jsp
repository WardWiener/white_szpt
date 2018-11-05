<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
</head>
<body id="validform" class="validform m-ui-layer-body">
	<div class="m-ui-layer-box" style="width: 98%;">

		<div class="m-ui-layer-content">

			<div class="row row-mar mar-top  mar-left">
				<div class="col-xs-2 m-label-right">姓名：</div>
				<div class="col-xs-2">
					<p id="peopleName"></p>
				</div>
				<div class="col-xs-2 m-label-right">身份证：</div>
				<div class="col-xs-2">
					<p id="peopleIdcode"></p>
				</div>
			</div>
			<div class="row row-mar  mar-left">
				<div class="col-xs-2 m-label-right">人员类型：</div>
				<div class="col-xs-9 m-label-right" id="peopleType"></div>
			</div>

			<div
				style="background: #f8f8f8; border-top: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; padding-top: 5px; height: 240px; overflow: hidden;">
				<div class="row row-mar mar-left">
					<div class="col-xs-2">当前分数为：</div>
					<div class="col-xs-8" style="margin-top: -20px;">
						<div id="echart-container" style="height: 320px;"></div>
					</div>
				</div>
			</div>
			<p class="m-line"></p>
			<p class="color-gray" id="record">
				
			</p>

			<p class="color-gray row-mar mar-top">评分标准</p>
			<div style="overflow-x: auto;">
				<div style="width: 100%;">
					<table id="example1" class="table table-bordered integral-table"
						cellspacing="0" width="100%">
						<thead>
							<tr>
								<th width="6%">评分项类别</th>
								<th width="6%">评分项权重（100）</th>
								<th width="6%">权重封顶</th>
								<th colspan="9">评分项类别的值（100分）</th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<td class="info">在控类型</td>
								<td class="warning"><input
									class="form-control input-sm valCell" readonly="readonly"  valName="在控类型-评分项权重"></td>
								<td class="warning"><input
									class="form-control input-sm valCell" readonly="readonly"  valName="在控类型-权重封顶"></td>
								<td><p class="color-gray titleName" titalName="在控类型-高危">高危</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly"  readonly="readonly" valName="在控类型-高危">%
									</p></td>
								<td><p class="color-gray titleName" titalName="在控类型-关注">关注</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly"  valName="在控类型-关注">%
									</p></td>
								<td><p class="color-gray titleName" titalName="在控类型-一般">
										一般
									</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly"  valName="在控类型-一般">%
									</p></td>
								<td><p class="color-gray titleName" titalName="在控类型-无">无</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly"  valName="在控类型-无">%
									</p></td>
								<td colspan="5"></td>
							</tr>
							<tr>
								<td rowspan="4" class="info">人员类型</td>
								<td rowspan="4" class="warning"><input
									class="form-control input-sm valCell" readonly="readonly"  valName="人员类型-评分项权重"></td>
								<td rowspan="4" class="warning"><input
									class="form-control input-sm valCell" readonly="readonly"  valName="人员类型-权重封顶"></td>
								<td rowspan="2"><p class="color-gray titleName" titalName="人员类型-在逃人员">在逃人员</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-在逃人员">%
									</p></td>

								<td rowspan="2"><p class="color-gray valCell titleName" titalName="人员类型-涉稳人员">涉稳人员</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-涉稳人员">%
									</p></td>
								<td rowspan="2"><p class="color-gray valCell titleName" titalName="人员类型-涉恐人员">涉恐人员</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-涉恐人员">%
									</p></td>
								<td colspan="2" style="text-align: center"><p
										class="color-gray">涉毒人员</p></td>
								<td rowspan="2"><p class="color-gray valCell titleName" titalName="人员类型-肇事肇祸精神病人">肇事肇祸精神病人
									</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-肇事肇祸精神病人">%
									</p></td>
								<td rowspan="2"><p class="color-gray valCell titleName" titalName="人员类型-重点上访人员">重点上访人员</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-重点上访人员">%
									</p></td>
								<td rowspan="2"><p class="color-gray valCell titleName" titalName="人员类型-违法犯罪青少年">违法犯罪青少年</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-违法犯罪青少年">%
									</p></td>
								<td rowspan="2"><p class="color-gray valCell titleName" titalName="人员类型-艾滋病人">艾滋病人</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-艾滋病人">%
									</p></td>
							</tr>
							<tr>

								<td><p class="color-gray titleName" titalName="人员类型-涉毒人员@吸毒人员">吸毒人员</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-涉毒人员@吸毒人员">%
									</p></td>
								<td><p class="color-gray titleName" titalName="人员类型-涉毒人员@制贩毒人员">制贩毒人员</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-涉毒人员@制贩毒人员">%
									</p></td>
							</tr>
							<tr>
								<td colspan="9" style="text-align: center"><p
										class="color-gray">刑事前科</p></td>
							</tr>
							<tr>
								<td><p class="color-gray titleName" titalName="人员类型-刑事前科@危害国家安全案">危害国家安全案</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-刑事前科@危害国家安全案">%
									</p></td>
								<td><p class="color-gray titleName" titalName="人员类型-刑事前科@危害公共安全案">危害公共安全案</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-刑事前科@危害公共安全案">%
									</p></td>
								<td><p class="color-gray titleName" titalName="人员类型-刑事前科@破坏社会主义市场经济秩序案">
										破坏社会主义<br>市场经济秩序案
									</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-刑事前科@破坏社会主义市场经济秩序案">%
									</p></td>
								<td><p class="color-gray titleName" titalName="人员类型-刑事前科@侵犯公民人身权利、民主权利案">
										侵犯公民人身权利、<br>民主权利案
									</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-刑事前科@侵犯公民人身权利、民主权利案">%
									</p></td>
								<td><p class="color-gray titleName" titalName="人员类型-刑事前科@侵犯财产案">侵犯财产案</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-刑事前科@侵犯财产案">%
									</p></td>
								<td><p class="color-gray titleName" titalName="人员类型-刑事前科@妨害社会管理案">妨害社会管理案</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-刑事前科@妨害社会管理案">%
									</p></td>
								<td><p class="color-gray titleName" titalName="人员类型-刑事前科@危害国防利益案">危害国防利益案</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-刑事前科@危害国防利益案">%
									</p></td>
								<td><p class="color-gray titleName" titalName="人员类型-渎职案">渎职案</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="人员类型-渎职案">%
									</p></td>
								<td></td>
							</tr>
							<tr>
								<td class="info">就业情况</td>
								<td class="warning"><input
									class="form-control input-sm valCell" readonly="readonly"  valName="就业情况-评分项权重"></td>
								<td class="warning"><input
									class="form-control input-sm valCell" readonly="readonly"  valName="就业情况-权重封顶"></td>
								<td><p class="color-gray titleName" titalName="就业情况-无业">无业</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly"  valName="就业情况-无业">%
									</p></td>
								<td><p class="color-gray titleName" titalName="就业情况-待业">待业</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly"  valName="就业情况-待业">%
									</p></td>
								<td><p class="color-gray titleName" titalName="就业情况-失业">失业</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly"  valName="就业情况-失业">%
									</p></td>
								<td><p class="color-gray titleName" titalName="就业情况-就业">就业</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly"  valName="就业情况-就业">%
									</p></td>
								<td colspan="5"></td>
							</tr>
							<tr>
								<td class="info">婚姻情况</td>
								<td class="warning"><input
									class="form-control input-sm valCell" readonly="readonly"  valName="就业情况-评分项权重"></td>
								<td class="warning"><input
									class="form-control input-sm valCell" readonly="readonly"  valName="就业情况-权重封顶"></td>
								<td><p class="color-gray titleName" titalName="婚姻情况-已婚">已婚</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly"  valName="婚姻情况-已婚">%
									</p></td>
								<td><p class="color-gray titleName" titalName="婚姻情况-再婚">再婚</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly"  valName="婚姻情况-再婚">%
									</p></td>
								<td><p class="color-gray titleName" titalName="婚姻情况-丧偶">丧偶</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly"  valName="婚姻情况-丧偶">%
									</p></td>
								<td><p class="color-gray titleName" titalName="婚姻情况-未婚">未婚</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly"  valName="婚姻情况-未婚">%
									</p></td>
								<td><p class="color-gray titleName" titalName="婚姻情况-离婚">离婚</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly"  valName="婚姻情况-离婚">%
									</p></td>
								<td><p class="color-gray titleName" titalName="婚姻情况-未知">未知</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly"  valName="婚姻情况-未知">%
									</p></td>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td class="info">经济收入（月）</td>
								<td class="warning"><input
									class="form-control input-sm valCell" readonly="readonly"  valName="经济收入（月）-评分项权重"></td>
								<td class="warning"><input
									class="form-control input-sm valCell" readonly="readonly"  valName="就业情况-权重封顶"></td>
								<td><p class="color-gray titleName" titalName="经济收入（月）-少于1000元">少于1000元</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="经济收入（月）-少于1000元">%
									</p></td>
								<td><p class="color-gray titleName" titalName="经济收入（月）-1000~2000">1000~2000</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="经济收入（月）-1000~2000">%
									</p></td>
								<td><p class="color-gray titleName" titalName="经济收入（月）-2000~5000">2000~5000</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="经济收入（月）-2000~5000">%
									</p></td>
								<td><p class="color-gray titleName" titalName="经济收入（月）-5000以上">5000以上</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="经济收入（月）-5000以上">%
									</p></td>
								<td><p class="color-gray titleName" titalName="经济收入（月）-空">空</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="经济收入（月）-空">%
									</p></td>
								<td colspan="4"></td>
							</tr>
							<tr>
								<td class="info">近一月出行次数</td>
								<td class="warning"><input
									class="form-control input-sm valCell" readonly="readonly"  valName="近一月出行次数-评分项权重"></td>
								<td class="warning"><input
									class="form-control input-sm valCell" readonly="readonly"  valName="近一月出行次数-权重封顶"></td>
								<td><p class="color-gray titleName" titalName="近一月出行次数-=6次">=6次</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="近一月出行次数-=6次">%
									</p></td>
								<td><p class="color-gray titleName" titalName="近一月出行次数-=4次">=5次</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="近一月出行次数-=5次">%
									</p></td>
								<td><p class="color-gray titleName" titalName="近一月出行次数-=4次">=4次</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="近一月出行次数-=4次">%
									</p></td>
								<td><p class="color-gray titleName" titalName="近一月出行次数-=3次">=3次</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="近一月出行次数-=3次">%
									</p></td>
								<td><p class="color-gray titleName" titalName="近一月出行次数-=2次">=2次</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="近一月出行次数-=2次">%
									</p></td>
								<td><p class="color-gray titleName" titalName="近一月出行次数-<=1次"><=1次</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="近一月出行次数-<=1次">%
									</p></td>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td rowspan="2" class="info">场所属性</td>
								<td rowspan="2" class="warning"><input
									class="form-control input-sm valCell" readonly="readonly"  valName="场所属性-评分项权重"></td>
								<td rowspan="2" class="warning"><input
									class="form-control input-sm valCell" readonly="readonly"  valName="场所属性-权重封顶"></td>
								<td colspan="3"><p class="color-gray">娱乐场所权重（近一年）</p></td>
								<td colspan="3" style="text-align: center"><p
										class="color-gray">网吧权重（近一年）</p></td>
								<td colspan="3" style="text-align: center"><p
										class="color-gray">酒店宾馆权重（近一年）</p></td>
							</tr>
							<tr>
								<td><p class="color-gray titleName" titalName="场所属性-娱乐场所权重（近一年）@>=20次">>=20次</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="场所属性-娱乐场所权重（近一年）@>=20次">%
									</p></td>
								<td><p class="color-gray titleName" titalName="场所属性-娱乐场所权重（近一年）@>2次，且<20次">>2次，且<20次</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="场所属性-娱乐场所权重（近一年）@>2次，且<20次">%
									</p></td>
								<td><p class="color-gray titleName" titalName="场所属性-娱乐场所权重（近一年）@<=2次"><=2次</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="场所属性-娱乐场所权重（近一年）@<=2次">%
									</p></td>
								<td><p class="color-gray titleName" titalName="场所属性-网吧权重（近一年）@@>=240小时">>=240小时</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="场所属性-网吧权重（近一年）@@>=240小时">%
									</p></td>
								<td><p class="color-gray titleName" titalName="场所属性-网吧权重（近一年）@>56小时，且<240小">>56小时，且<240小</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="场所属性-网吧权重（近一年）@>56小时，且<240小">%
									</p></td>
								<td><p class="color-gray titleName" titalName="场所属性-网吧权重（近一年）@<=56小时"><=56小时</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="场所属性-网吧权重（近一年）@<=56小时">%
									</p></td>
								<td><p class="color-gray titleName" titalName="场所属性-酒店宾馆权重（近一年）@>=20天">>=20天</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="场所属性-酒店宾馆权重（近一年）@>=20天">%
									</p></td>
								<td><p class="color-gray titleName" titalName="场所属性-酒店宾馆权重（近一年）@>2天，且<20天">>2天，且<20天</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="场所属性-酒店宾馆权重（近一年）@>2天，且<20天">%
									</p></td>
								<td><p class="color-gray titleName" titalName="场所属性-酒店宾馆权重（近一年）@<=2天"><=2天</p>
									<p>
										<input class="form-control input-sm valCell" readonly="readonly" 
											valName="场所属性-酒店宾馆权重（近一年）@<=2天">%
									</p></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>

</body>
<script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/personList/integralInfo.js"></script>
</html>