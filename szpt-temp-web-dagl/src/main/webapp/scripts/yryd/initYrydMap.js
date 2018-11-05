
var executeControlMap = null;
var realTimeWifiMapConten = null;
var locusPointMapConten = null;
var locusMapConten = null;
var locusHotMapConten = null;
var wifiLocusMapConten = null;
var wifiLocusHotMapConten = null;
var personTrankMapConten = null; 

var trackHotLayer = null;
var  wifiTrackHotLayer = null;
$(document).ready(function() {	
	//初始化地图
	$.multiBaseMap.init(["personTrankMap","executeControlMap","realTimeWifiMapConten","locusPointMapConten","locusMapConten","locusHotMapConten","wifiLocusMapConten","wifiLocusHotMapConten"]);
	//添加初始化map完成后执行的函数
	$.multiBaseMap.addToMapReadyExeList(mapReady) ;
});

/**
 * map初始化之后执行的方法
 */
var mapReady = function(){
	//获取map实例
	executeControlMap = $.multiBaseMap.getInstance("executeControlMap");
	realTimeWifiMapConten = $.multiBaseMap.getInstance("realTimeWifiMapConten");
	locusPointMapConten = $.multiBaseMap.getInstance("locusPointMapConten");
	locusMapConten = $.multiBaseMap.getInstance("locusMapConten");
	locusHotMapConten = $.multiBaseMap.getInstance("locusHotMapConten");
	wifiLocusMapConten = $.multiBaseMap.getInstance("wifiLocusMapConten");
	wifiLocusHotMapConten = $.multiBaseMap.getInstance("wifiLocusHotMapConten");
	personTrankMapConten = $.multiBaseMap.getInstance("personTrankMap");
	
	wifiTrackHotLayer =	wifiLocusHotMapConten.createHeatLayer();
	trackHotLayer = locusHotMapConten.createHeatLayer();
	
	realTimeWifiMapConten.setLoadCallback(function(e){
		//查询wifi快照
		$.sswijk.findWifiSnapshotByIdCode();
	});
	
	locusPointMapConten.setLoadCallback(function(e){
		$.trackAnalyzeSnapshotLst.trackSnapshotLstInit();
	});
	
	
	personTrankMapConten.setLoadCallback(function(e){
		$.dagl.yryd .yrydHighriskPersonAlert.initPersonTrankInfo();
	});
	
	wifiLocusMapConten.setLoadCallback(function(e){
		//查询wifi快照
		$.wifiLocusAnalyzeSnapshotLst.wifiLocusLstInit();
	})
	
}