$.xyyMacAddressMap = $.xyyMacAddressMap || {};
(function($){
	"use strict";
	
	var xyyMacAddressMapLayer = null;//轨迹地图图层
	
	/**
	 * 创建轨迹监控点
	 * @param placeBasicInfoBeanList wifi场所信息对象集合
	 */
	function newPlaceInfoPoint(map,personTrackInfoLst){
		
		if(!$.util.exist(personTrackInfoLst)){
			return ;
		}
		var placeInfoPointTemplate = map.createInfoTemplate("串并案详情", knownCasePointLayerContent);
		if($.util.exist(xyyMacAddressMapLayer)){
			xyyMacAddressMapLayer.clear();
		}
		var trackLineLst = [];
		$.each(personTrackInfoLst,function(p,trackInfo){  //每个点的定义
			if(trackInfo.longitude && trackInfo.latitude){
				var lonlat = {
						longitude : trackInfo.longitude ,
						latitude : trackInfo.latitude
					}
//					var symbolObj = {url:context+"/images/map/map-icon-wifi.png", width:22, height:35};
					var graphic = map.createPoint("xyyMacAddressMapLayer", lonlat, null, trackInfo, placeInfoPointTemplate);
					if(!$.util.exist(xyyMacAddressMapLayer) && $.util.exist(graphic)){
						xyyMacAddressMapLayer = graphic.getLayer();
					}
					trackLineLst.push(trackInfo);
			}
		});
	}

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
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.xyyMacAddressMap, { 
		newPlaceInfoPoint : newPlaceInfoPoint,
	});	
})(jQuery);