<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div id="nullWifiSnapshotTableDiv" style="margin: 10px 10px;text-align: center;display:none;">
	<h2>实时WIFI设备监控没有保存过快照</h2>
</div>
<div id="wifiSnapshotTableDiv" style="display:none;">
	<table id="wifiSnapshotTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" width="100%">
         
    </table>
</div>
<div id="wifiSnapshotInfoDiv" style="display:none;">
	<p class="mar-top font12 color-gray2">
		保存快照原因：<span  id="wifiSnapshotInfo"></span><!-- <span class="color-green1">已推送办案民警</span><span class="color-orange1">办案民警未查看</span> -->
	</p>
	<div class="row mar-top">
		<div
			style="width: 250px; margin-right: -250px; float: left; position: relative">
			<div class="panel panel-default"
				style="min-height: 500px; border: none; max-height: 500px; overflow: auto;">
				<div class="panel-heading">实时WIFI热点</div>
				<ul id="wifiPlaceUl" class="list-group list-group-noborder">
				
				</ul>
			</div>
		</div>
	
		<div style="width: 100%; float: left;">
			<div class="right-inner" style="margin-left: 260px;">
				<div id="realTimeWifiMapConten" style="height: 500px;"></div>
			</div>
		</div>
	</div>
</div>
