<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
</head>
<body id="validform" class="validform m-ui-layer-body">
	<div class="m-ui-layer-box" style="width: 98%;">

		<div class="m-ui-layer-content">

			<div class="row row-mar">
				<div class="col-xs-2">
					<label class="control-label">模型编号：<span class="red-star">*</span></label>
				</div>
				<div class="col-xs-2">
					<input type="text" id="num" readonly="readonly" class="form-control input-sm valiform-keyup form-val" vf-position="2" datatype="*1-20" value="">
				</div>
				<div class="col-xs-2">
					<label class="control-label">模型名称：<span class="red-star">*</span></label>
				</div>
				<div class="col-xs-4">
					<input type="text" id="name" class="form-control input-sm valiform-keyup form-val" readonly="readonly" vf-position="2" datatype="*1-20"
						value="">
				</div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-2">
					<label class="control-label">状态：<span class="red-star">*</span></label>
				</div>
				<div class="col-xs-2">
					<input type="text" id="state" class="form-control input-sm valiform-keyup form-val" readonly="readonly" vf-position="2" datatype="*1-20"
						value="">
				</div>
				<div class="col-xs-4">
					<label class="control-label">达到预警的最低分数值设置：<span class="red-star">*</span></label>
				</div>
				<div class="col-xs-2 input-group">
					<input type="text" id="alertPoint" class="form-control input-sm" readonly="readonly"><span
						class="input-group-addon">分</span>
				</div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-2">
					<label class="control-label">备注：</label>
				</div>
				<div class="col-xs-4">
					<textarea class="form-control input-sm valiform-keyup form-val" readonly="readonly" vf-position="2" datatype="*0-80" id="remark" rows="3" style="margin: 0px; width: 992px;"></textarea>
				</div>
			</div>
			<p class="m-line"></p>
			<p class="color-gray" id="record">
				最新修改人：<span id="modifyPeople"></span><span style="margin-left: 60px">最新修改时间：<span id="modifyTime"></span></span>
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
								<td class="info">在控类型<span class="red-star">*</span></td>
								<td class="warning"><input class="form-control input-sm valCell" valName="在控类型-评分项权重"></td>
								<td class="warning"><input class="form-control input-sm valCell" valName="在控类型-权重封顶"></td>
								<td><p class="color-gray">高危<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="在控类型-高危">%
									</p></td>
								<td><p class="color-gray">关注<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="在控类型-关注">%
									</p></td>
								<td><p class="color-gray">一般<span class= valCell"red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="在控类型-一般">%
									</p></td>
								<td><p class="color-gray">无<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="在控类型-无">%
									</p></td>
								<td colspan="5"></td>
							</tr>
							<tr>
								<td rowspan="4" class="info">人员类别<span class="red-star">*</span></td>
								<td rowspan="4" class="warning"><input class="form-control input-sm valCell" valName="人员类别-评分项权重"></td>
								<td rowspan="4" class="warning"><input class="form-control input-sm valCell" valName="人员类别-权重封顶"></td>
								<td rowspan="2"><p class="color-gray">在逃人员<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-在逃人员">%
									</p></td>
								
								<td rowspan="2"><p class="color-gray valCell">涉稳人员<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-涉稳人员">%
									</p></td>
								<td rowspan="2"><p class="color-gray valCell">涉恐人员<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-涉恐人员">%
									</p></td>
								<td colspan="2" style="text-align: center"><p
										class="color-gray">涉毒人员</p></td>
								<td rowspan="2"><p class="color-gray valCell">肇事肇祸精神病人<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-肇事肇祸精神病人">%
									</p></td>
								<td rowspan="2"><p class="color-gray valCell">重点上访人员<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-重点上访人员">%
									</p></td>
								<td rowspan="2"><p class="color-gray valCell">违法犯罪青少年<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-违法犯罪青少年">%
									</p></td>
								<td rowspan="2"><p class="color-gray valCell">艾滋病人<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-艾滋病人">%
									</p></td>
							</tr>
							<tr>
								
								<td><p class="color-gray">吸毒人员<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-涉毒人员@吸毒人员">%
									</p></td>
								<td><p class="color-gray">制贩毒人员<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-涉毒人员@制贩毒人员">%
									</p></td>
							</tr>
							<tr>
								<td colspan="9" style="text-align: center"><p
										class="color-gray">刑事前科</p></td>
							</tr>
							<tr>
							<td><p class="color-gray">危害国家安全案<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-刑事前科@危害国家安全案">%
									</p></td>
								<td><p class="color-gray">危害公共安全案<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-刑事前科@危害公共安全案">%
									</p></td>
								<td><p class="color-gray">破坏社会主义<br>市场经济秩序案<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-刑事前科@破坏社会主义市场经济秩序案">%
									</p></td>
								<td><p class="color-gray">侵犯公民人身权利<br>民主权利案<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-刑事前科@侵犯公民人身权利、民主权利案">%
									</p></td>
								<td><p class="color-gray">侵犯财产案<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-刑事前科@侵犯财产案">%
									</p></td>
								<td><p class="color-gray">妨害社会管理案<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-刑事前科@妨害社会管理案">%
									</p></td>
								<td><p class="color-gray">危害国防利益案<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-刑事前科@危害国防利益案">%
									</p></td>
								<td><p class="color-gray">渎职案<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="人员类别-渎职案">%
									</p></td>
								<td></td>
							</tr>
							<tr>
								<td class="info">就业情况<span class="red-star">*</span></td>
								<td class="warning"><input class="form-control input-sm valCell" valName="就业情况-评分项权重"></td>
								<td class="warning"><input class="form-control input-sm valCell" valName="就业情况-权重封顶"></td>
								<td><p class="color-gray">无业<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="就业情况-无业">%
									</p></td>
								<td><p class="color-gray">待业<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="就业情况-待业">%
									</p></td>
								<td><p class="color-gray">失业<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="就业情况-失业">%
									</p></td>
								<td><p class="color-gray">就业<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="就业情况-就业">%
									</p></td>
								<td colspan="5"></td>
							</tr>
							<tr>
								<td class="info">婚姻情况<span class="red-star">*</span></td>
								<td class="warning"><input  class="form-control input-sm valCell" valName="就业情况-评分项权重"></td>
								<td class="warning"><input  class="form-control input-sm valCell" valName="就业情况-权重封顶"></td>
								<td><p class="color-gray">已婚<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="婚姻情况-已婚">%
									</p></td>
								<td><p class="color-gray">再婚<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="婚姻情况-再婚">%
									</p></td>
								<td><p class="color-gray">丧偶<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="婚姻情况-丧偶">%
									</p></td>
								<td><p class="color-gray">未婚<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="婚姻情况-未婚">%
									</p></td>
								<td><p class="color-gray">离婚<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="婚姻情况-离婚">%
									</p></td>
								<td><p class="color-gray">未知<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell"  valName="婚姻情况-未知">%
									</p></td>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td class="info">经济收入（月）<span class="red-star">*</span></td>
								<td class="warning"><input  class="form-control input-sm valCell" valName="经济收入（月）-评分项权重"></td>
								<td class="warning"><input  class="form-control input-sm valCell" valName="就业情况-权重封顶"></td>
								<td><p class="color-gray">少于1000元<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="经济收入（月）-少于1000元">%
									</p></td>
								<td><p class="color-gray">1000~2000<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="经济收入（月）-1000~2000">%
									</p></td>
								<td><p class="color-gray">2000~5000<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="经济收入（月）-2000~5000">%
									</p></td>
								<td><p class="color-gray">5000以上<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="经济收入（月）-5000以上">%
									</p></td>
								<td><p class="color-gray">空<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="经济收入（月）-空">%
									</p></td>
								<td colspan="4"></td>
							</tr>
							<tr>
								<td class="info">近一月出行次数<span class="red-star">*</span></td>
								<td class="warning"><input  class="form-control input-sm valCell" valName="近一月出行次数-评分项权重"></td>
								<td class="warning"><input  class="form-control input-sm valCell" valName="近一月出行次数-权重封顶"></td>
								<td><p class="color-gray">=6次<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="近一月出行次数-=6次">%
									</p></td>
								<td><p class="color-gray">=5次<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="近一月出行次数-=5次">%
									</p></td>
								<td><p class="color-gray">=4次<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="近一月出行次数-=4次">%
									</p></td>
								<td><p class="color-gray">=3次<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="近一月出行次数-=3次">%
									</p></td>
								<td><p class="color-gray">=2次<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="近一月出行次数-=2次">%
									</p></td>
								<td><p class="color-gray"><=1次<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="近一月出行次数-<=1次">%
									</p></td>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td rowspan="2" class="info">场所属性<span class="red-star">*</span></td>
								<td rowspan="2" class="warning"><input  class="form-control input-sm valCell" valName="场所属性-评分项权重"></td>
								<td rowspan="2" class="warning"><input  class="form-control input-sm valCell" valName="场所属性-权重封顶"></td>
								<td colspan="3"><p class="color-gray">娱乐场所权重（近一年）</p></td>
								<td colspan="3" style="text-align: center"><p
										class="color-gray">网吧权重（近一年）</p></td>
								<td colspan="3" style="text-align: center"><p
										class="color-gray">酒店宾馆权重（近一年）</p></td>
							</tr>
							<tr>
								<td><p class="color-gray">>=20次<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="场所属性-娱乐场所权重（近一年）@>=20次">%
									</p></td>
								<td><p class="color-gray">>2次，且<20次<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="场所属性-娱乐场所权重（近一年）@>2次，且<20次">%
									</p></td>
								<td><p class="color-gray"><=2次<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="场所属性-娱乐场所权重（近一年）@<=2次">%
									</p></td>
								<td><p class="color-gray">>=240小时<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="场所属性-网吧权重（近一年）@@>=240小时">%
									</p></td>
								<td><p class="color-gray">>56小时，且<240小<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="场所属性-网吧权重（近一年）@>56小时，且<240小">%
									</p></td>
								<td><p class="color-gray"><=56小时<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="场所属性-网吧权重（近一年）@<=56小时">%
									</p></td>
								<td><p class="color-gray">>=20天<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="场所属性-酒店宾馆权重（近一年）@>=20天">%
									</p></td>
								<td><p class="color-gray">>2天，且<20天<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="场所属性-酒店宾馆权重（近一年）@>2天，且<20天">%
									</p></td>
								<td><p class="color-gray"><=2天<span class="red-star">*</span></p>
									<p>
										<input class="form-control input-sm valCell" valName="场所属性-酒店宾馆权重（近一年）@<=2天" >%
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
	src="<%=context%>/scripts/highRiskPersonAlert/integralModel/lookIntegralModel.js"></script>
</html>