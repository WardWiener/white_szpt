$.caseTagMap = $.caseTagMap || {};

(function($){
	
	"use strict";
	
	var map = null;// map实例
	var addressPointLayer = null;// 地址点图层
	
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	
	var isBeginMark = false ;
	
	$(document).ready(function(){
		//初始化地图
		$.multiBaseMap.init(["mapContent"]);
		//添加初始化map之前执行的函数
		$.multiBaseMap.addToMapBeforeReadyExeList(setMapHeight) ;
		//添加初始化map完成后执行的函数
		$.multiBaseMap.addToMapReadyExeList(mapReady) ;
		
		/**
		 * 清空标点及信息
		 */
		$(document).on("click", "#clearSearch", function(){
			$("#searchText").val("") ;
			$("#resultName").val("") ;
			$("#resultX").val("") ;
			$("#resultY").val("") ;
			if($.util.exist(addressPointLayer)){
				addressPointLayer.clear();
			}
		});
		
		/**
		 * 搜索框输入
		 */
//		$(document).on("keyup change","#searchText",function(){
//			var keyword = $(this).val();
//			console.log(keyword);
//			if($.util.exist(map)){
//				map.queryLocationByKeyword(keyword, showQueryResultList);
//			}
//		});
		
		/**
		 * 查询
		 */
		$(document).on("click","#search",function(){
			var keyword = $("#searchText").val();
			if($.util.exist(map)){
				map.queryLocationByKeyword(keyword, showQueryResultList);
			}
		});
		
	});
	
	/**
	 * 地图初始化完毕后执行的函数
	 */
	var mapReady = function(){
		//获取map实例
		map = $.multiBaseMap.getInstance("mapContent");
		//设置地图点击事件回调
		map.setClickCallback(function(lonlat,e){
			//创建标点
			addSingtonPoint(lonlat);
			map.getAddressByLonlat(lonlat, setPageAddress);
			
		});
		map.setLoadCallback(function(e){
			//设置默认值
			if(!$.util.isBlank(initData.longitude) && !$.util.isBlank(initData.latitude)){
				setPageAddress(initData.address);
				setPageLonlat(initData.longitude,initData.latitude);
				var lonlat = {longitude:initData.longitude,latitude:initData.latitude};
				addSingtonPoint(lonlat);
				map.setMapCenterAt(lonlat);
			}
		});
		
	}
	
	/**
	 * 设置map地图容器的高度
	 */
	var setMapHeight = function(){
		var height = $(window).height() - 90;
		$("#mapContent").css("height", height+"px") ;
	}
	
	/**
	 * 创建单例标点
	 * @param lonlat 经纬度坐标
	 */
	function addSingtonPoint(lonlat){
		if(!$.util.exist(lonlat)){
			return ;
		}
		if($.util.exist(addressPointLayer)){
			addressPointLayer.clear();
		}
		var graphic = map.createPoint("addressPointLayer", lonlat);
		if($.util.exist(graphic)){
			addressPointLayer = graphic.getLayer();
		}
		setPageLonlat(lonlat.longitude, lonlat.latitude);
	}
	
	/**
	 * 设置页面经纬度值
	 * 
	 * @param lon 经度
	 * @param lat 纬度
	 */
	function setPageLonlat(lon, lat){
		$("#resultX").val(lon);
		$("#resultY").val(lat);
	}
	
	/**
	 * 设置页面地点
	 * 
	 * @param address 地址字符串
	 */
	var setPageAddress = function(address){
		$("#resultName").val(address);
	}
	
	/**
	 * 显示查询结果
	 */
	var showQueryResultList = function(resultArray){
		map.showMenuContent(resultArray,"#searchText",setPointByResult);
	}
	
	/**
	 * 根据搜索结果设置位置
	 */
	var setPointByResult = function (obj){
		setPageLonlat(obj.longitude, obj.latitude);
		setPageAddress(obj.name + obj.address);
		var lonlat = {longitude:obj.longitude,latitude:obj.latitude};
		addSingtonPoint(lonlat);
		map.setMapCenterAt(lonlat);
	}

	/**
	 * 获取页面信息
	 */
	function getInfo(){
		var demo = $.validform.getValidFormObjById("tagMap") ;
		var flag = $.validform.check(demo) ;
		if(!flag){
			return ;
		}
		
		var name = $.trim($("#resultName").val()) ;
		var longitude = $.trim($("#resultX").val()) ;
		var latitude = $.trim($("#resultY").val()) ;
		
		return {name : name, longitude : longitude, latitude : latitude, map : map};
	}

	jQuery.extend($.caseTagMap, {	
		getInfo:getInfo
	});
	
})(jQuery);	