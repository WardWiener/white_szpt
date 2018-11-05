$.suspectMacAnalysisMap = $.suspectMacAnalysisMap || {};

(function($){

	"use strict";
	
	var map = null;
	var knownCasePointLayer = null;// 已知串并案案件点图层
	var wifiPointLayer = null;// wifi点图层
	var caseCircleLayer = null;// 案件范围图层（圆圈）//TODO 测试用--完后删除
	
	$(document).ready(function() {	
		//初始化地图
		$.multiBaseMap.init(["mapContent"]);
		//添加初始化map之前执行的函数
//		$.multiBaseMap.addToMapBeforeReadyExeList(setMapHeight) ;
		//添加初始化map完成后执行的函数
		$.multiBaseMap.addToMapReadyExeList(mapReady) ;
	});
	
	/**
	 * 地图初始化完毕后执行的函数
	 */
	var mapReady = function(){
		//获取map实例
		map = $.multiBaseMap.getInstance("mapContent");
		//设置地图点击事件回调
		map.setClickCallback(function(lonlat,e){
			
			
		});
		map.setLoadCallback(function(e){
			
		});
		
	}
	
	/**
	 * 创建已知串并案案件地点
	 * 
	 * @param data 包含经纬度的业务对象
	 */
	function addKnownCasePoint(data){
		if($.util.isBlank(data.longitude) || $.util.isBlank(data.latitude)){
			return ;
		}
		var lonlat = new Object();
		lonlat.longitude = data.longitude ;
		lonlat.latitude = data.latitude ;
		
		var knownCaseInfoTemplate = map.createInfoTemplate("串并案详情", knownCasePointLayerContent);
		var graphic = map.createPoint("knownCasePointLayer", lonlat, null, data, knownCaseInfoTemplate);
		if($.util.exist(graphic)){
			knownCasePointLayer = graphic.getLayer();
		}
	}
	
	/**
	 * 已知串并案点详情面板回调
	 * 
	 * @param graphic 警情点对象
	 */
	var knownCasePointLayerContent = function(graphic){
		var data = graphic.data;
		var baseDiv = $("<div />", {
			"class":"layer-police",
			"style":"width:300px; padding:10px;",
    	});
		var div = $("<div />", {
			"class":"content"
		}).appendTo(baseDiv);
		if(!$.util.isBlank(data.caseName)){
			$('<div />',{
        		"class":"t-over",
        		"style":"margin-top:5px;width:270px;",
        		"tooltipPos":"bottom",
        		"mouseTrack":"true",
        		"my":"center bottom-20",
        		"at":"center top",
        		"title":data.caseName,
        		"text":"案件名称："+data.caseName
        	}).appendTo(div);
	   	}
	   	if(!$.util.isBlank(data.caseCode)){
		   	$("<div />", {
			   "style":"margin-top:5px",
			   "text":"案件编号："+data.caseCode
		   	}).appendTo(div);
	   	}
	   	if(!$.util.isBlank(data.caseStateName)){
		   	$("<div />", {
			   "style":"margin-top:5px",
			   "text":"案件状态："+data.caseStateName
		   	}).appendTo(div);
	   	}
	   	if(!$.util.isBlank(data.caseAddress)){
		   	$("<div />", {
			   "style":"margin-top:5px",
			   "text":"案件地点："+data.caseAddress
		   	}).appendTo(div);
	   	}
	   	if(!$.util.isBlank(data.longitude) && !$.util.isBlank(data.latitude)){
	   		$("<div />", {
			   "style":"margin-top:5px",
			   "text":"案件经纬度：" + data.longitude + ',' + data.latitude
	   		}).appendTo(div);
	   	}
	   	return baseDiv[0] ;
	}
	
	/**
	 * 创建wifi点
	 * 
	 * @param data 包含经纬度的业务对象
	 */
	function addWifiPoint(data){
		if($.util.isBlank(data.longitude) || $.util.isBlank(data.latitude)){
			return ;
		}
		var lonlat = new Object();
		lonlat.longitude = data.longitude ;
		lonlat.latitude = data.latitude ;
		
		var image = new Object();
		image.url = context+"/images/map/map-mark2.png" ;
		image.width = 40;
		image.height = 40;
		
		var wifiInfoTemplate = map.createInfoTemplate("WIFI监控点详情", wifiPointLayerContent);
		var graphic = map.createPoint("wifiPointLayer", lonlat, image, data, wifiInfoTemplate);
		if($.util.exist(graphic)){
			wifiPointLayer = graphic.getLayer();
		}
	}
	
	/**
	 * wifi监控点详情面板回调
	 * 
	 * @param graphic 警情点对象
	 */
	var wifiPointLayerContent = function(graphic){
		var data = graphic.data;
		var baseDiv = $("<div />", {
			"class":"layer-police",
			"style":"width:300px; padding:10px;",
    	});
		var div = $("<div />", {
			"class":"content"
		}).appendTo(baseDiv);
		if(!$.util.isBlank(data.name)){
			$('<div />',{
        		"class":"t-over",
        		"style":"margin-top:5px;width:270px;",
        		"tooltipPos":"bottom",
        		"mouseTrack":"true",
        		"my":"center bottom-20",
        		"at":"center top",
        		"title":data.name,
        		"text":"监控点名称："+data.name
        	}).appendTo(div);
		}
		if(!$.util.isBlank(data.code)){
			$("<div />", {
			   "style":"margin-top:5px",
			   "text":"监控点编号："+data.code
			}).appendTo(div);
		}
		if(!$.util.isBlank(data.longitude)){
			$("<div />", {
			   "style":"margin-top:5px",
			   "text":"监控点经度："+data.longitude
			}).appendTo(div);
		}
		if(!$.util.isBlank(data.latitude)){
			$("<div />", {
			   "style":"margin-top:5px",
			   "text":"监控点纬度："+data.latitude
			}).appendTo(div);
		}
		return baseDiv[0] ;
	}
	
	/**
	 * 根据半径创建以案件为中心的圆圈
	 * @returns
	 */
	function createCaseCircle(lonlatArray, scope){//TODO 测试用--完后删除
		if($.util.exist(caseCircleLayer)){
			caseCircleLayer.clear();
		}
		$.each(lonlatArray, function(l, lonlat){
			if($.util.isBlank(lonlat.longitude) || $.util.isBlank(lonlat.latitude)){
				return ;
			}
			map.createCircle("caseCircleLayer", lonlat, scope);
		});
		caseCircleLayer = map.getLayerById("caseCircleLayer");
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.suspectMacAnalysisMap, { 
		addKnownCasePoint : addKnownCasePoint ,
		clearKnownCasePointLayer : function(){
			if($.util.exist(knownCasePointLayer)){
				knownCasePointLayer.clear();
			}
		},
		addWifiPoint : addWifiPoint ,
		clearWifiPointLayer : function(){
			if($.util.exist(wifiPointLayer)){
				wifiPointLayer.clear();
			}
		},
		setMapCenterAt : function(longitude, latitude){
			var lonlat = new Object();
			lonlat.longitude = longitude ;
			lonlat.latitude = latitude ;
			map.setMapCenterAt(lonlat);
		} ,
		createCaseCircle : createCaseCircle//TODO 测试用--完后删除
	});	
})(jQuery);