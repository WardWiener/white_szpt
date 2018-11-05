$.wifiLocusMap = $.wifiLocusMap || {};
(function($){
	"use strict";
	
	var trackMapLayer = null;//轨迹地图图层
	var  trackLineLayer = null;//创建wifi轨迹线

	/**
	 * 创建WIFI场所监控点
	 * @param placeBasicInfoBeanList wifi场所信息对象集合
	 */
	
	//创建建轨迹地图
	function newPlaceInfoPoint(map,wifiInfoBeanList){
		
		if(!$.util.exist(wifiInfoBeanList)){
			return;
		}
		var placeInfoPointTemplate = map.createInfoTemplate("人员轨迹监测点详细信息", placeInfoPointLayerContent);
		if($.util.exist(trackMapLayer)){
			trackMapLayer.clear();
		}
		var trackLineLst = [];
		$.each(wifiInfoBeanList,function(p,trackInfo){  //每个点的定义
			if(trackInfo.longitude && trackInfo.latitude){
				var lonlat = {
						longitude : trackInfo.longitude ,
						latitude : trackInfo.latitude
					}
					var graphic = map.createPoint("trackMapLayer", lonlat, null, trackInfo, placeInfoPointTemplate);
					if(!$.util.exist(trackMapLayer) && $.util.exist(graphic)){
						trackMapLayer = graphic.getLayer();
					}
				trackLineLst.push(lonlat);
			}
		});
		//* @param style 线样式，例如：{r:颜色值,g:颜色值,b:颜色值,width:线宽}
		var style = {r:255,g:0,b:0,width:3}
		map.createLine("trackLineLayer", trackLineLst, style , null);//创建线
	}

	var placeInfoPointLayerContent = function(graphic){
    	var wifiFenceHitBean = graphic.data ;
		//显示详细信息面板
    	var baseDiv = $("<div />", {
    		"class":"layer-police",
    		"style":"width:300px; padding:10px;",
    	});
    	var baseDiv_contentDiv = $("<div />",{
    		"class":"content"
    	}).appendTo(baseDiv);
    	
    	if(!$.util.exist(wifiFenceHitBean)){
    		return baseDiv[0] ;
    	}
    	//场所名称
    	if(!$.util.isBlank(wifiFenceHitBean.placeName)){
    		$("<div />", {
        		"class":"row",
    			"style":"margin-top:5px",
    			"text":"场所名称："+wifiFenceHitBean.placeName
        	}).appendTo(baseDiv_contentDiv);
    	}
    	//进入时间
    	if(!$.util.isBlank(wifiFenceHitBean.enterTime)){
    		$("<div />", {
        		"class":"row",
    			"style":"margin-top:5px",
    			"text":"进入时间："+$.date.timeToStr(wifiFenceHitBean.enterTime, "yyyy-MM-dd HH:mm:ss")
        	}).appendTo(baseDiv_contentDiv);
    	}
	    //离开时间
    	if(!$.util.isBlank(wifiFenceHitBean.leaveTime)){
    		$("<div />", {
        		"class":"row",
        		"style":"margin-top:5px",
    			"text":"离开时间："+$.date.timeToStr(wifiFenceHitBean.leaveTime, "yyyy-MM-dd HH:mm:ss")
        	}).appendTo(baseDiv_contentDiv);
    	}
		return baseDiv[0];
	}
	
	//创建热力地图
	function createHotMap(map,hotLayer,initLst){
		map.showHeatLayer(hotLayer,initLst);
	}
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.wifiLocusMap, { 
		newPlaceInfoPoint : newPlaceInfoPoint,
		createHotMap : createHotMap
	});	
})(jQuery);