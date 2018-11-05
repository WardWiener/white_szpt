$.wifiDeployControlDetails = $.wifiDeployControlDetails || {} ;
(function($){
	"use strict";
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var id = initData.id;
	var map = null;
	var wifiPlacePointLayer = null;// wifi场所点图层
	var wifiPlaceNewPointLayer = null; //wifi场所新位置点图层
	var newLongitude = null;// 新选的经度
	var newLatitude = null;// 新选的纬度
	var wifiPlaceObj = null;// 场所Bean
	
	$(document).ready(function() {	
		//初始化地图
		$.multiBaseMap.init(["mapContent"]);
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
			setWifiNewPoint(lonlat.longitude, lonlat.latitude);
			newLongitude = lonlat.longitude ;
			newLatitude = lonlat.latitude ;
		});
		map.initPcsBoundaryLayer(true);
		map.initPcsBoundaryNameLayer(true);
		map.setLoadCallback(function(e){
			init();
		});
		
		wifiPlacePointLayer = map.createLayer("graphics", "wifiPlacePointLayer");
		wifiPlaceNewPointLayer = map.createLayer("graphics", "wifiPlaceNewPointLayer");
	}
	
	/**
	 * 设置新的wifi场所位置点
	 * 
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @returns
	 */
	function setWifiNewPoint(longitude, latitude){
		if($.util.isBlank(longitude) || $.util.isBlank(latitude)){
			return ;
		}
		if($.util.exist(wifiPlaceNewPointLayer)){
			wifiPlaceNewPointLayer.clear();
		}
		var lonlat = {
			longitude : longitude ,
			latitude : latitude
		}
		var image = {
			url : context+"/images/map/map-mark2.png" ,
			width : 30 , 
			height : 30
		}
		map.createPoint("wifiPlaceNewPointLayer", lonlat, image);
	}
	
	function commit(){
		$.layerAlert.confirm({
    		msg:"你确定要修改吗?",
    		title:"确定修改框",		//弹出框标题
    		width:'300px',
			hight:'250px',
			shade: [0.5,'black'],  //遮罩
			icon:3,				//弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
			shift:1,			//弹出时的动画效果  有0-6种
			btn:['确定','取消'],
    		yes:function(index, layero){//点击确定按钮
    			var islive;
    			var data = {
					"id":id ,
					"pcsId": $("#pcsSelect").val() ,
					"isLive":  $("#islive").val() ,
					"newLongitude" : $.util.isBlank(newLongitude) ? wifiPlaceObj.longitude : newLongitude ,
					"newLatitude" : $.util.isBlank(newLatitude) ? wifiPlaceObj.latitude : newLatitude
				}
				var queryStr = $.util.toJSONString(data);
				$.ajax({
					url:context +'/deployControl/updateOrderCell.action',
					type:"post",
					data : queryStr,
					contentType : "application/json; charset=utf-8",
					dataType:"json",
					success:function(){
						$.util.topWindow().$.layerAlert.alert({msg:"修改成功!",title:"提示",end:function(){
							$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex);
						}});
					}
				})
    		},
    		cancel:function(){
    			return;
			}
    	});
		
	}
	//初始化方法
	function init(){
		var data = {
			"id":id	
		}
		var queryStr = $.util.toJSONString(data);
		$.ajax({
			url:context +'/deployControl/querySurveillanceById.action',
			type:"post",
			data : queryStr,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				wifiPlaceObj = successDate.resultMap.result;
				parameter(wifiPlaceObj);
				initPcs(successDate.resultMap.pcsLst,successDate.resultMap.result.areaDepartmentId);
				initIsLive(successDate.resultMap.result.isLive);
				//设置地图上场所点
				setWifiPlacePoint(wifiPlaceObj.longitude, wifiPlaceObj.latitude);
			}
		})
	}
	/**
	 * 设置场所位置
	 * 
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @returns
	 */
	function setWifiPlacePoint(longitude, latitude){
		if($.util.isBlank(longitude) || $.util.isBlank(latitude)){
			return ;
		}
		var lonlat = {
			longitude : longitude ,
			latitude : latitude
		}
		var image = {
			url : context+"/images/map/map-icon-wifi.png" ,
			width : 22 , 
			height : 35
		}
		map.createPoint("wifiPlacePointLayer", lonlat, image);
		map.setMapCenterAt(lonlat);
	}
	
	function parameter(data){
		$.each($(".valCell"),function(){
			$(this).val(data[$(this).attr("valName")]);
		})
	}
	function initPcs(departmentLst,id){
		$("#pcsSelect").empty();
		$.select2.addByList("#pcsSelect", departmentLst,"id","shortName",true,true);
		if(null != id && id.length > 0 ){
			$("#pcsSelect").select2("val", id);
		}
	}
	function initIsLive(isLive){
		var isLiveLst = [{
			id:"0",
			text:"否"
		},{
			id:"1",
			text:"是"
		}]
		$("#islive").empty();
		$.select2.addByList("#islive", isLiveLst,"id","text",true,true);
		$("#islive").select2("val", isLive);
	}
	
	jQuery.extend($.wifiDeployControlDetails, { 
		commit:commit
	});	
	
})(jQuery);