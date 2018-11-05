$.lookSswfjkDetail = $.lookSswfjkDetail || {};

(function($){

	"use strict";
	
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var rowData = initData.rowData;
	
	var realTimeWifiMapConten = null;
	
	$(document).ready(function() {	
		//初始化地图
		$.multiBaseMap.init(["realTimeWifiMapConten"]);
		//添加初始化map完成后执行的函数
		$.multiBaseMap.addToMapReadyExeList(mapReady) ;
	});
	
	/**
	 * map初始化之后执行的方法
	 */
	var mapReady = function(){
		//获取map实例
		realTimeWifiMapConten = $.multiBaseMap.getInstance("realTimeWifiMapConten");
		$.lookSswfjkSnapshot.setMapObject(realTimeWifiMapConten);
		
		realTimeWifiMapConten.setLoadCallback(function(e){
			setSnapshotPage(rowData);
		});
	}
	
	/**
	 * 设置快照页面
	 * @returns
	 */
	function setSnapshotPage(snapshot){
		var snapshotObject = $.parseJSON(snapshot.snapshot);
		$("#wifiSnapshotInfo").text(snapshot.intro);
		
		$.lookSswfjkSnapshot.updateWifiPlaceUl(snapshotObject.data.baseBeanList);
		$.lookSswfjkSnapshot.newPlaceInfoPoint(snapshotObject.data.placeBasicInfoBeanList);
		$.lookSswfjkSnapshot.newPersonLocusPoint(snapshotObject.data.wifiPlaceInAndOutInfoBeanList);
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.lookSswfjkDetail, { 
		
	});	
})(jQuery);