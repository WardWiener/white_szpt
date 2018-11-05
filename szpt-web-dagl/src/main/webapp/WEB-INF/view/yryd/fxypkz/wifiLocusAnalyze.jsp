<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div id="nullWifiLocusSnapshotDiv" style="margin: 10px 10px;text-align: center;display:none;">
	<h2>wifi轨迹分析没有保存过快照</h2>
</div>

<div id="wifiLocusAnalyzeLst" style="display : none">
	<table id="wifiLocusAnalyzeTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
			
	</table>
</div> 
<div id="wifiLocusAnalyzeInfo" style="display : none">

<p class="mar-top font12 color-gray2">
	保存快照原因：<span id="intro"></span>
</p>

	<div class="row row-mar" style="margin-top: 20px">
		<div class="col-xs-3" style="width: 225px">
			<label class="control-label">身份证号&nbsp;/&nbsp;手机号&nbsp;/&nbsp;MAC地址：</label>
		</div>
		<div class="col-xs-2" style="width: 200px; margin-right: 10px;">
			<input id="searchText" type="text" class="form-control input-sm">
		</div>
		<div class="col-xs-3 row-mar" style="width: 350px">			
			<button id="recentlyHour" class="btn btn-primary btn-sm searchTime-btn timeBtn" style="margin-left:0">近一小时</button>
			<button id="recentlyDay" class="btn btn-primary btn-sm searchTime-btn timeBtn">近一天</button>
			<button id="recentlyWeek" class="btn btn-primary btn-sm searchTime-btn timeBtn">近一周</button>
			<button id="recentlyMonth" class="btn btn-primary btn-sm searchTime-btn timeBtn">近一月</button>
			<button id="recentlySixMonth" class="btn btn-primary btn-sm searchTime-btn timeBtn">近半年</button>
		</div>
		<div  class="col-xs-10" style="width: 470px; ">
			<div class="col-xs-3 input-group">
				&nbsp&nbsp <label><span id="zdySpan" style="display:none;">自定义</span>时间：</label>
			</div>
			<input type="hidden" id="dtfmt" class="dateFmt"
				value="info@yyyy-MM-dd HH:mm:ss" />
			<div class="col-xs-4 input-group">
				<div class="input-group" style="margin-right: 10px;">
					<input type="text" id="wifiStartTime"
						class="laydate-start form-control input-sm" readonly="readonly">
				</div>
			</div>
			<div class="col-xs-1" style="width: 20px; text-align: center">-</div>
			<div class="col-xs-4 input-group">
				<div class="input-group" style="padding-left: 5px;">
					<input type="text" id="wifiEndTime"
						class="laydate-end form-control input-sm" readonly="readonly">
					
				</div>
			</div>
		</div>
	</div>

<div class="row">
	<div class="col-xs-8">
		<div class="m-ui-table" style="margin-right: 30px">
			<table id="locusTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
			</table>
		</div>
	</div>
	<div class="col-xs-4">
		<div class="panel panel-primary m-panel" style="margin-bottom: 0">
			<div class="panel-heading">
				<h3 class="panel-title">该人员经过次数最多的场所</h3>
			</div>
			<div class="panel-body" style="padding: 0">
				<table id="placeFrequencyOrderTable"
						class="table table-bordered m-ui-table-no-paginate"
						cellspacing="0" width="100%">
						<tbody>

						</tbody>
					</table>
			</div>
		</div>
		<div class="panel panel-primary m-panel" style="margin-bottom: 0">
			<div class="panel-heading">
				<h3 class="panel-title">该人员驻留时间最长的场所</h3>
			</div>
			<div class="panel-body" style="padding: 0">
				<table id="maxTimeplaceOrderTable"
					class="table table-bordered m-ui-table-no-paginate" cellspacing="0"
					width="100%">
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="row mar-top">
	<div class="col-xs-6">
		<div class="m-ui-title4">
			<h2>地图轨迹</h2>
		</div>
		<div id="wifiLocusMapConten" style="margin-right: 1px; height: 200px; overflow: hidden;">
		</div>
	</div>
	<div class="col-xs-6">
		<div class="m-ui-title4">
			<h2>轨迹热点</h2>
		</div>
		<div id="wifiLocusHotMapConten" style="height: 200px; overflow: hidden;">
		</div>
	</div>
</div>
</div>