	
	
$.lookSswfjkSnapshot = $.lookSswfjkSnapshot || {};
(function($){

	"use strict";
	
	var map = null;
	var pbibs = [];
	var placeInfoPointLayer = null;// 场所监控点图层
	var personLocusPointLayer = null;// 人员轨迹图层
	
	$(document).ready(function() {	
		/**
		 * 实时wifi场所列表li点击事件
		 */
		$(document).on("click",".placeLi",function(){
			$.each( $(".placeLi"), function(e,m){
				$(m).removeAttr("style")
			})
			$(this).attr("style","background-color: #663;")
			
			var placeName = $($(this).find("div")).text();
			if($.util.isBlank(pbibs[0].longitude) || $.util.isBlank(pbibs[0].latitude)){
    			return ;
    		}
			$.each(pbibs,function(i,val){
				if( val.internetServicePlaceName == placeName){
					var lonlat = {
						longitude : val.longitude ,
						latitude : val.latitude
					}
					map.setMapCenterAt(lonlat);
				}
			});
			var graphics = placeInfoPointLayer.graphics;
			for(var i in graphics){
				if(graphics[i].data.internetServicePlaceName==placeName){
					graphics[i].symbol.url=context+"/images/map/map-mark2.png";
					graphics[i].symbol.width=60;
					graphics[i].symbol.height=60;
					graphics[i].draw();
				}else{
					graphics[i].symbol.url=context+"/images/map/map-icon-wifi.png";
					graphics[i].symbol.width=22;
					graphics[i].symbol.height=35;
					graphics[i].draw();
				}
			}
		});
	});
	
	/**
	 * 创建人员轨迹点
	 * @param locationArray 人员经纬度
	 */
	function newPersonLocusPoint(wifiPlaceInAndOutInfoBeanList){
		if(!$.util.exist(wifiPlaceInAndOutInfoBeanList)){
			return ;
		}
		
		var personLocusPointTemplate = map.createInfoTemplate("WIFI轨迹", personLocusPointLayerContent);
		if($.util.exist(personLocusPointLayer)){
			personLocusPointLayer.clear();
		}
		
		$.each(wifiPlaceInAndOutInfoBeanList,function(l,wifiFenceHitBean){
			var lonlat = {
				longitude : wifiFenceHitBean.longitude ,
				latitude : wifiFenceHitBean.latitude
			}
			var symbolObj = {url:context+"/images/map/map-icon-trail.png", width:22, height:35};
			
			var graphic = map.createPoint("personLocusPointLayer", lonlat, symbolObj, wifiFenceHitBean, personLocusPointTemplate);
			if(!$.util.exist(personLocusPointLayer) && $.util.exist(graphic)){
				personLocusPointLayer = graphic.getLayer();
			}
		});
	}
	
	/**
	 * 创建WIFI场所监控点
	 * @param placeBasicInfoBeanList wifi场所信息对象集合
	 */
	function newPlaceInfoPoint(placeBasicInfoBeanList){
		pbibs = placeBasicInfoBeanList;
		
		if(!$.util.exist(placeBasicInfoBeanList)){
			return ;
		}
		var placeInfoPointTemplate = map.createInfoTemplate("WIFI监测点详细信息", placeInfoPointLayerContent);
		if($.util.exist(placeInfoPointLayer)){
			placeInfoPointLayer.clear();
		}
		$.each(placeBasicInfoBeanList,function(p,placeBasicInfoBean){  //每个点的定义
			var lonlat = {
				longitude : placeBasicInfoBean.longitude ,
				latitude : placeBasicInfoBean.latitude
			}
			var symbolObj = {url:context+"/images/map/map-icon-wifi.png", width:22, height:35};
			
			var graphic = map.createPoint("placeInfoPointLayer", lonlat, symbolObj, placeBasicInfoBean, placeInfoPointTemplate);
			if(!$.util.exist(placeInfoPointLayer) && $.util.exist(graphic)){
				placeInfoPointLayer = graphic.getLayer();
			}
		});
	}
	
	/**
	 * 人员轨迹点详细信息模版
	 */
	var personLocusPointLayerContent = function(graphic){
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
    	//姓名
    	if(!$.util.isBlank(wifiFenceHitBean.personName)){
    		$("<div />", {
        		"class":"row",
    			"style":"margin-top:5px",
    			"text":"姓名："+wifiFenceHitBean.personName
        	}).appendTo(baseDiv_contentDiv);
    	}
    	//场所名称
    	if(!$.util.isBlank(wifiFenceHitBean.place)){
    		$("<div />", {
        		"class":"row",
    			"style":"margin-top:5px",
    			"text":"场所名称："+wifiFenceHitBean.place
        	}).appendTo(baseDiv_contentDiv);
    	}
    	//进入时间
    	if(!$.util.isBlank(wifiFenceHitBean.enterTime)){
    		$("<div />", {
        		"class":"row",
    			"style":"margin-top:5px",
    			"text":"进入场所时间："+$.date.timeToStr(wifiFenceHitBean.enterTime, "yyyy-MM-dd HH:mm:ss")
        	}).appendTo(baseDiv_contentDiv);
    	}
    	//离开场所时间
    	if(!$.util.isBlank(wifiFenceHitBean.leaveTime)){
    		$("<div />", {
        		"class":"row",
    			"style":"margin-top:5px",
    			"text":"离开场所时间："+$.date.timeToStr(wifiFenceHitBean.leaveTime, "yyyy-MM-dd HH:mm:ss")
        	}).appendTo(baseDiv_contentDiv);
    	}
    	//停留时间
    	if(!$.util.isBlank(wifiFenceHitBean.stayInterval)){
    		$("<div />", {
        		"class":"row",
    			"style":"margin-top:5px",
    			"text":"停留时间（秒）："+wifiFenceHitBean.stayInterval
        	}).appendTo(baseDiv_contentDiv);
    	}
    	
    	return baseDiv[0] ;
	}
	
	/**
	 * 场所点详细信息模版
	 */
	var placeInfoPointLayerContent = function(graphic){
    	var placeBasicInfoBean = graphic.data ;
    	//显示详细信息面板
    	var baseDiv = $("<div />", {
    		"class":"layer-police",
    		"style":"width:300px; padding:10px;",
    	});
    	var baseDiv_contentDiv = $("<div />",{
    		"class":"content"
    	}).appendTo(baseDiv);
    	
    	if(!$.util.exist(placeBasicInfoBean)){
    		return baseDiv[0] ;
    	}
    	//场所名称
    	if(!$.util.isBlank(placeBasicInfoBean.internetServicePlaceName)){
    		$("<div />", {
        		"class":"row",
    			"style":"margin-top:5px",
    			"text":"场所名称："+placeBasicInfoBean.internetServicePlaceName
        	}).appendTo(baseDiv_contentDiv);
    	}
    	//场所监控人数
    	if(!$.util.isBlank(placeBasicInfoBean.placeMonitorNum)){
    		$("<div />", {
        		"class":"row",
    			"style":"margin-top:5px",
    			"text":"场所监控人数："+placeBasicInfoBean.placeMonitorNum
        	}).appendTo(baseDiv_contentDiv);
    	}
    	//场所详细地址
    	if(!$.util.isBlank(placeBasicInfoBean.detailedAddress)){
    		$("<div />", {
        		"class":"row",
    			"style":"margin-top:5px",
    			"text":"详细地址："+placeBasicInfoBean.detailedAddress
        	}).appendTo(baseDiv_contentDiv);
    	}
    	//营业开始时间
    	if(!$.util.isBlank(placeBasicInfoBean.businessStartTime)){
    		$("<div />", {
        		"class":"row",
    			"style":"margin-top:5px",
    			"text":"营业开始时间："+$.date.timeToStr(placeBasicInfoBean.businessStartTime, "HH:mm")
        	}).appendTo(baseDiv_contentDiv);
    	}
	    //营业结束时间
    	if(!$.util.isBlank(placeBasicInfoBean.businessEndTime)){
    		$("<div />", {
        		"class":"row",
        		"style":"margin-top:5px",
        		"text":"营业结束时间："+$.date.timeToStr(placeBasicInfoBean.businessEndTime, "HH:mm")
        	}).appendTo(baseDiv_contentDiv);
    	}
    	//设备table**********
    	if(!$.util.isBlank(placeBasicInfoBean.wifiPlaceInAndOutInfoBeanList)){
    		$("<div />", {
        		"class":"m-ui-table",
    			"style":"",
    			"html":'<table id="'+placeBasicInfoBean.id+'" class="table table-bordered table-hover m-ui-table-no-paginate" cellspacing="0" width="100%"></table>',
    			onload:function(){
    				initTable(wifiPlaceInAndOutInfoBeanList,$(this).find('#'+placeBasicInfoBean.id));
    				$(this).find("tr").css("background","#132643");
    			}
        	}).appendTo(baseDiv_contentDiv);
    	}
    	return baseDiv[0] ;
	}
	
	/**
	 * 更新wifi热点列表
	 * @param baseBeanList 列表集合
	 */
	function updateWifiPlaceUl(baseBeanList){
		$("#wifiPlaceUl").html("");
		if(!$.util.exist(baseBeanList)){
			return ;
		}
		$.each(baseBeanList,function(b,baseBean){
			var li = '<li class="list-group-item placeLi"><span class="badge m-badge">' + baseBean.deviceSum + '</span><div class="t-over" style="width:180px;" tooltipPos="top" my="center bottom+60" at="center top" title="' + baseBean.placeName + '">' + baseBean.placeName + '</div></li>';
			if(b == 0){
				li = '<li class="list-group-item list-group-item-danger placeLi"><span class="badge m-badge">' + baseBean.deviceSum + '</span><div class="t-over" style="width:180px;" tooltipPos="top" my="center bottom+60" at="center top" title="' + baseBean.placeName + '">' + baseBean.placeName + '</div></li>';
			}else if(b == 1){
				li = '<li class="list-group-item list-group-item-warning placeLi" style="background-color: #ffe4ce; color: #e26500;"><span class="badge m-badge">' + baseBean.deviceSum + '</span><div class="t-over" style="width:180px;" tooltipPos="top" my="center bottom+60" at="center top" title="' + baseBean.placeName + '">' + baseBean.placeName + '</div></li>';
			}else if(b == 2){
				li = '<li class="list-group-item list-group-item-warning placeLi"><span class="badge m-badge">' + baseBean.deviceSum + '</span><div class="t-over" style="width:180px;" tooltipPos="top" my="center bottom+60" at="center top" title="' + baseBean.placeName + '">' + baseBean.placeName + '</div></li>';
			}
			$("#wifiPlaceUl").append(li);
		});
	}
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.lookSswfjkSnapshot, { 
		setMapObject : function(obj){
			map = obj;
		},
		updateWifiPlaceUl : updateWifiPlaceUl ,
		newPlaceInfoPoint : newPlaceInfoPoint ,
		newPersonLocusPoint : newPersonLocusPoint
	});	
})(jQuery);