<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div id="nullTrackAnalyzeSnapshotDiv" style="margin: 10px 10px;text-align: center;display:none;">
	<h2>轨迹分析没有保存过快照</h2>
</div>

<div id="trackAnalyzeLst" style="display : none">
	<table id="trackAnalyzeTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
			
	</table>
</div> 
<div id="trackAnalyzeinfo" style="display : none">
<p class="mar-top font12 color-gray2">
	保存快照原因：<span id="trackAnalyzeSnapshotInfo"></span>
</p>
<!--轨迹信息-->
<div class="m-ui-title4 mar-top">
	<h2>联合轨迹</h2>
</div>
<div class="row">
	<div class="col-xs-8">
		<div class="history-content" style="max-height: 800px; overflow: auto">
			<ul id="trackTimeShaft">

				<li class="clear"></li>
			</ul>
		</div>
	</div>


	<div class="col-xs-4">
		<div class="row row-mar hotel-pannel">
			<table id="countTable1"
				class="table table-bordered table-hover m-ui-table-whole"
				cellspacing="0" width="100%">
				<thead>
					<tr>
						<th colspan="2" width="50%">旅馆酒店</th>
						<th colspan="2">从贵阳乘火车 - 目的地</th>
					</tr>
				</thead>
				<tbody>
					<tr class="dataTr">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr class="dataTr">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr class="dataTr">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2"><a data="旅馆酒店" id="hotelAll"
							class="a-link mar-right-sm all">全部</a></td>
						<td colspan="2"><a data="从贵阳乘火车 - 目的地" id="trainOutAll"
							class="a-link mar-right-sm all">全部</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row row-mar hotel-pannel">
			<table id="countTable2"
				class="table table-bordered table-hover m-ui-table-whole"
				cellspacing="0" width="100%">
				<thead>
					<tr>
						<th colspan="2" width="50%">网吧</th>
						<th colspan="2">乘火车到贵阳 - 起始地</th>
					</tr>
				</thead>

				<tbody>
					<tr class="dataTr">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr class="dataTr">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr class="dataTr">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2"><a data="网吧" id="internetBarAll"
							class="a-link mar-right-sm all">全部</a></td>
						<td colspan="2"><a data="乘火车到贵阳 - 起始地" id="trainInAll"
							class="a-link mar-right-sm all">全部</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row row-mar hotel-pannel">
			<table id="countTable3"
				class="table table-bordered table-hover m-ui-table-whole"
				cellspacing="0" width="100%">
				<thead>
					<tr>
						<th colspan="2" width="50%">监所</th>
						<th colspan="2">从贵阳乘飞机- 目的地</th>
					</tr>
				</thead>

				<tbody>
					<tr class="dataTr">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr class="dataTr">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr class="dataTr">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2"><a data="监所" id="monitoringGuardAll"
							class="a-link mar-right-sm">全部</a></td>
						<td colspan="2"><a data="从贵阳乘飞机- 目的地" id="airPlaneInAll"
							class="a-link mar-right-sm all">全部</a></td>
					</tr>
				</tbody>
			</table>

		</div>

		<div class="row row-mar hotel-pannel">
			<table id="countTable4"
				class="table table-bordered table-hover m-ui-table-whole"
				cellspacing="0" width="100%">
				<thead>
					<tr>
						<th colspan="2" width="50%">WIFI</th>
						<th colspan="2">乘飞机到贵阳 - 起始地</th>
					</tr>
				</thead>

				<tbody>
					<tr class="dataTr">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr class="dataTr">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr class="dataTr">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2"><a data="WIFI" id="wifiAll"
							class="a-link mar-right-sm">全部</a></td>
						<td colspan="2"><a data="乘飞机到贵阳 - 起始地" id="airPlaneOutAll"
							class="a-link mar-right-sm all">全部</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

<!--轨迹信息-->
<!--轨迹地图-->

<div class="row mar-top">
	<div class="col-xs-8">
		<div class="m-ui-title4">
			<h2>地图轨迹</h2>
		</div>
		<div class="col-xs-6">
			<div id="locusPointMapConten" style="margin-right: 1px; height: 200px; overflow: hidden;">
			</div>
		</div>
		<div class="col-xs-6">
			<div id="locusMapConten" style="margin-right: 1px; height: 200px; overflow: hidden;">
			</div>
		</div>
	</div>
	<div class="col-xs-4">
		<div class="m-ui-title4">
			<h2>轨迹热点</h2>
		</div>
		<div id="locusHotMapConten" style="height: 200px; overflow: hidden;">
		</div>
	</div>
</div>
</div>
</div>