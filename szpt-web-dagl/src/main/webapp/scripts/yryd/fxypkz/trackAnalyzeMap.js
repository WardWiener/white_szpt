$.trackAnalyzeMap = $.trackAnalyzeMap || {};
(function($){
	"use strict";
	
	var trackMapLayer = null;//轨迹地图图层
	var  trackLineLayer = null;//创建wifi轨迹线	
	
	/**
	 * 创建轨迹监控点
	 * @param placeBasicInfoBeanList wifi场所信息对象集合
	 */
	function newPlaceInfoPoint(map,personTrackInfoLst){
		
		if(!$.util.exist(personTrackInfoLst)){
			return ;
		}
		var placeInfoPointTemplate = map.createInfoTemplate("人员轨迹监测点详细信息", placeInfoPointLayerContent);
		if($.util.exist(trackMapLayer)){
			trackMapLayer.clear();
		}
		var trackLineLst = [];
		$.each(personTrackInfoLst,function(p,trackInfo){  //每个点的定义
			if(trackInfo.longitude && trackInfo.latitude){
				var lonlat = {
						longitude : trackInfo.longitude ,
						latitude : trackInfo.latitude
					}
//					var symbolObj = {url:context+"/images/map/map-icon-wifi.png", width:22, height:35};
					var graphic = map.createPoint("trackMapLayer", lonlat, null, trackInfo, placeInfoPointTemplate);
					if(!$.util.exist(trackMapLayer) && $.util.exist(graphic)){
						trackMapLayer = graphic.getLayer();
					}
					trackLineLst.push(trackInfo);
			}
		});
		
		map.createLine("trackLineLayer", trackLineLst, null , null);//创建线
		
	}

	var placeInfoPointLayerContent = function(graphic){
    	var personTrackInfoBean = graphic.data ;
		//显示详细信息面板
		var baseDiv = $("<div />", {
			"class" : "layer-police",
			"style" : "width:200px; padding:10px;",
		});
		var baseDiv_contentDiv = $("<div />", {
			"class" : "content"
		}).appendTo(baseDiv);

		if (!$.util.exist(personTrackInfoBean)) {
			return baseDiv[0];
		}
		//场所名称
		if (!$.util.isBlank(personTrackInfoBean.placeName)) {
			$("<div />", {
				"class" : "row",
				"style" : "margin-top:5px",
				"text" : "地点名称：" + personTrackInfoBean.placeName
			}).appendTo(baseDiv_contentDiv);
		}
		
		if (!$.util.isBlank(personTrackInfoBean.appearTime)) {
			$(
					"<div />",
					{
						"class" : "row",
						"style" : "margin-top:5px",
						"text" : "进入时间："
								+ $.date.timeToStr(
										personTrackInfoBean.appearTime,
										"yyyy-MM-dd HH:mm")
					}).appendTo(baseDiv_contentDiv);
		}
		//离开时间
		if (!$.util.isBlank(personTrackInfoBean.leaveTime)) {
			$(
					"<div />",
					{
						"class" : "row",
						"style" : "margin-top:5px",
						"text" : "离开时间："
								+ $.date.timeToStr(
										personTrackInfoBean.leaveTime,
										"yyyy-MM-dd HH:mm")
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
	jQuery.extend($.trackAnalyzeMap, { 
		newPlaceInfoPoint : newPlaceInfoPoint,
		createHotMap : createHotMap
	});	
})(jQuery);