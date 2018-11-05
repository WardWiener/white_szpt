(function($){
	"use strict";
	var locusMap ;//轨迹地图实例对象
	var locusBaseMapServiceLayer ;//轨迹地图基础图层
	
	var layer_placeInfoPoint ;// WIFI场所监控点图层
	var layer_personLocusPoint ;// 重点人轨迹点图层
	
	var baseBeanList = []; //所有WIFI监控点列表
	
	var deviceaSum;//检测终端设备数
	var placeSum;//检测wifi围栏点数
	var searchText="";//查询条件
	var wifiPlaceInAndOutInfoBeanList = [];//重点人的活动场所
	
	var placeBasicInfoBeanList = null;//场所对象Bean集合
	var flag = false;
	
//	var startTime = getRecentlyMonth().startTime;//默认最近一月
//	var endTime = getRecentlyMonth().endTime;//默认最近一月	
	var table;
	$(document).ready(function(){
		//initTable(1,"ltb1");
		updatePageField();
		setInterval(function(){
			updatePageField();
		}, 15000); 
		//初始化轨迹地图
		initLocusMap();
		
		/**
		 * 查询按钮事件
		 */
		$(document).on("click","#search",function(){
			searchPersonLocus();
		});
		
		/**
		 * 生成快照
		 */
		$(document).on("click","#createSnapshot",function(){
			if(flag){
				createSnapshotClick();
			}else{
				$.util.topWindow().$.layerAlert.alert({icon:0, msg:"您没有查询到任何信息。"}) ;
			}
		});
		

		
		/**
		 * 重置按钮事件
		 */
		$(document).on("click","#reset",function(){
			$("#searchText").val("");
			searchPersonLocus();
			flag = false;
		});
		
		/**
		 * 搜索按钮点击事件
		 *//*
		$(document).on("click","#search",function(){
			var searchText = $("#searchText").val();
			if($.common.wifiCommon.macValid(searchText)){//输入的是mac地址
				searchFiveColorPersonLocusByMac(searchText);
			}else if(searchText==""){
				searchFiveColorPersonLocusByMac("");
			}else{
				$.layerAlert.tips({
					msg:'请填写正确的MAC地址',
					selector:"#searchText",
					color:'#FF0000',
					position:2,
					closeBtn:2,
					time:2000,
					shift:1
				});
				return false;
			}
//			else{
//				$.ajax({
//					url:context +'/fiveColorPerson/searchFiveColorPersonByMobilePhone.action',
//					type:'post',
//					dataType:'json',
//					data:{mobilePhone : searchText},
//					success:function(successData){
//						var fcpBean = successData.fiveColorPersonBean;
//						if($.util.exist(fcpBean)){
//							searchFiveColorPersonLocusByMac(fcpBean.terminalDeviceInfo);
//						}else{
//							searchFiveColorPersonLocusByMac(" ");
//						}
//					},
//					error:function(errorData){
//						
//					}
//				});
//			}
		});*/
		
		/**
		 * 实时wifi场所列表li点击事件
		 */
		$(document).on("click",".placeLi",function(){
			$.each( $(".placeLi"), function(e,m){
				$(m).removeAttr("style")
			})
			$(this).attr("style","background-color: #663;")
			
			var placeName = $($(this).find("div")).text();
			if(!$.util.exist(placeBasicInfoBeanList) || placeBasicInfoBeanList.length < 0){
				return ;
			}
			$.each(placeBasicInfoBeanList,function(i,val){
				if( val.internetServicePlaceName == placeName){
					$.common.arcgisMapCommon.setMapCentreAtByLonlat(val.longitude, val.latitude, locusMap);
				}
			});
			var graphics = layer_placeInfoPoint.graphics;
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
//		$(document).on("ifChanged","#personCheck",function(){
//			if(this.checked){
//				layer_personLocusPoint.show();
//			}else{
//				layer_personLocusPoint.hide();
//			}
//		});
//		$(document).on("ifChanged","#placeCheck",function(){
//			if(this.checked){
//				layer_placeInfoPoint.show();
//			}else{
//				layer_placeInfoPoint.hide();
//			}
//		});
	});
	
	/**
	 * 查询实时WiFi的方法
	 */
	function searchPersonLocus(){
		searchText = $("#searchText").val();
		if($.common.wifiCommon.macValid(searchText)){//输入的是mac地址
			searchFiveColorPersonLocusByMac(searchText);
		}else if(searchText==""){
			searchFiveColorPersonLocusByMac("");
		}else{
			$.layerAlert.tips({
				msg:'请填写正确的MAC地址',
				selector:"#searchText",
				color:'#FF0000',
				position:2,
				closeBtn:2,
				time:2000,
				shift:1
			});
			return false;
		}
//		else{
//			$.ajax({
//				url:context +'/fiveColorPerson/searchFiveColorPersonByMobilePhone.action',
//				type:'post',
//				dataType:'json',
//				data:{mobilePhone : searchText},
//				success:function(successData){
//					var fcpBean = successData.fiveColorPersonBean;
//					if($.util.exist(fcpBean)){
//						searchFiveColorPersonLocusByMac(fcpBean.terminalDeviceInfo);
//					}else{
//						searchFiveColorPersonLocusByMac(" ");
//					}
//				},
//				error:function(errorData){
//					
//				}
//			});
//		}
	}
	
		
	/**
	 * 设置时间范围快捷按钮样式
	 * @param button 需要设置为选中样式的按钮
	 */
	function setTimeButStyle(button){
		$(".selectTime").each(function(b,but){
			$(but).removeClass("btn-danger");
			$(but).addClass("btn-primary");
		});
		$(button).addClass("btn-danger");
		$(button).removeClass("btn-primary");
		$.laydate.reset("#diyDateRange");
	}
	
	
//	/**
//	 * 获取最近一小时时间段
//	 * @retrun {startTime: 开始时间(毫秒),endTime: 结束时间(毫秒)}
//	 */
//	function getRecentlyHour(){
//		var endTime = new Date().getTime();
//		var startTime = endTime - 1000*60*60;
//		return {startTime : startTime , endTime : endTime};
//	}
//	
//	/**
//	 * 获取最近一天时间段
//	 * @retrun {startTime: 开始时间(毫秒),endTime: 结束时间(毫秒)}
//	 */
//	function getRecentlyDay(){
//		var endTime = new Date().getTime();
//		var startTime = endTime - 1000*60*60*24;
//		return {startTime : startTime , endTime : endTime};
//	}
//	
//	/**
//	 * 获取最近一周时间段
//	 * @retrun {startTime: 开始时间(毫秒),endTime: 结束时间(毫秒)}
//	 */
//	function getRecentlyWeek(){
//		var endTime = new Date().getTime();
//		var startTime = endTime - 1000*60*60*24*7;
//		return {startTime : startTime , endTime : endTime};
//	}
//	
//	/**
//	 * 获取最近一月时间段
//	 * @retrun {startTime: 开始时间(毫秒),endTime: 结束时间(毫秒)}
//	 */
//	function getRecentlyMonth(){
//		var endTime = new Date().getTime();
//		var startTime = endTime - 1000*60*60*24*31;
//		return {startTime : startTime , endTime : endTime};
//	}
//	
//	/**
//	 * 获取最近半年时间段
//	 * @retrun {startTime: 开始时间(毫秒),endTime: 结束时间(毫秒)}
//	 */
//	function getRecentlySixMonth(){
//		var endTime = new Date().getTime();
//		var startTime = endTime - 1000*60*60*24*31*6;
//		return {startTime : startTime , endTime : endTime};
//	}
//	
//	/**
//	 * 获取最近1年时间段
//	 * @retrun {startTime: 开始时间(毫秒),endTime: 结束时间(毫秒)}
//	 */
//	function getRecentlyYear(){
//		var endTime = new Date().getTime();
//		var startTime = endTime - 1000*60*60*24*365;
//		return {startTime : startTime , endTime : endTime};
//	}
	
	/**
	 * 更新页面动态值
	 */
	function updatePageField(){
		findAllPlaceList();
		findPlaceSum();
		findDeviceaSum();
	}
	
	/**
	 * 根据手机号或者mac查询重点人活动的场所（设置重点人地图标点）
	 * @param amc mac地址
	 */
	function searchFiveColorPersonLocusByMac(mac){
		var macList = new Array();
		macList.push(mac);
		var gData = new Object();
		$.util.objToStrutsFormData(macList, "macList", gData);
		$.ajax({
			url:context +'/realTimeWifi/findAllLocusByMacs.action',
			type:'post',
			dataType:'json',
			data:gData,
			success:function(successData){
				wifiPlaceInAndOutInfoBeanList = successData.wifiPlaceInAndOutInfoBeanList;
				if($.util.isBlank(mac)){
					flag = false;
				}else{
					flag = true;
					newPersonLocusPoint(wifiPlaceInAndOutInfoBeanList); 
				}
			},
			error:function(errorData){
				
			}
		});
	}
	
	/**
	 * 查询所有WIFI监控点列表
	 */
	function findAllPlaceList(){  //添加时间参数 页面选着的时间
		var gData = new Object();
//		$.util.objToStrutsFormData(startTime, "startTime", gData);//新增  
//		$.util.objToStrutsFormData(endTime, "endTime", gData);  //新增
		$.ajax({
			url:context +'/realTimeWifi/findAllPlaceList.action',
			type:'post',
			dataType:'json',
			data:{}, //{}
			success:function(successData){
				baseBeanList = successData.baseBeanList;
				updateWifiPlaceUl(baseBeanList);
				//地图上绘制监控点
				placeBasicInfoBeanList = successData.placeBasicInfoBeanList;
				newPlaceInfoPoint(placeBasicInfoBeanList);
			},
			error:function(errorData){
				
			}
		});
	}
	
	/**
	 * 查询wifi围栏监控点总数
	 */
	function findPlaceSum(){
		$.ajax({
			url:context +'/realTimeWifi/findPlaceSum.action',
			type:'post',
			dataType:'json',
			data:{},
			success:function(successData){
				placeSum = successData.placeSum;
				updatePlaceSum(placeSum);
			},
			error:function(errorData){
			}
		});
	}
	
	/**
	 * 查询终端设备总数
	 */
	function findDeviceaSum(){
		$.ajax({
			url:context +'/realTimeWifi/findDeviceaSum.action',
			type:'post',
			dataType:'json',
			data:{},
			success:function(successData){
				deviceaSum = successData.deviceaSum;
				updateDeviceSum(deviceaSum);
			},
			error:function(errorData){
			}
		});
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
			var li = '<li class="list-group-item placeLi"><span class="badge m-badge">' + baseBean.deviceSum + '</span><div class="t-over" style="width:115px;" tooltipPos="top" my="center bottom+60" at="center top" title="' + baseBean.placeName + '">' + baseBean.placeName + '</div></li>';
			if(b == 0){
				li = '<li class="list-group-item list-group-item-danger placeLi"><span class="badge m-badge">' + baseBean.deviceSum + '</span><div class="t-over" style="width:115px;" tooltipPos="top" my="center bottom+60" at="center top" title="' + baseBean.placeName + '">' + baseBean.placeName + '</div></li>';
			}else if(b == 1){
				li = '<li class="list-group-item list-group-item-warning placeLi" style="background-color: #ffe4ce; color: #e26500;"><span class="badge m-badge">' + baseBean.deviceSum + '</span><div class="t-over" style="width:115px;" tooltipPos="top" my="center bottom+60" at="center top" title="' + baseBean.placeName + '">' + baseBean.placeName + '</div></li>';
			}else if(b == 2){
				li = '<li class="list-group-item list-group-item-warning placeLi"><span class="badge m-badge">' + baseBean.deviceSum + '</span><div class="t-over" style="width:115px;" tooltipPos="top" my="center bottom+60" at="center top" title="' + baseBean.placeName + '">' + baseBean.placeName + '</div></li>';
			}
			$("#wifiPlaceUl").append(li);
		});
	}
	
	/**
	 * 设置搜索的重点人位置信息
	 * @param placeNameList 场所名称集合
	 * @returns
	 */
//	function setFiveColorPersonPoint(wifiPlaceInAndOutInfoBeanList){
//		var locationArray = new Array();
//		$.each(wifiPlaceInAndOutInfoBeanList,function(p,wifiFenceHitBean){
//			if($.util.isBlank(wifiFenceHitBean.longitude) || $.util.isBlank(wifiFenceHitBean.latitude)){
//				return true;
//			}
//			var longitude = parseFloat(wifiFenceHitBean.longitude);
//			var latitude = parseFloat(wifiFenceHitBean.latitude);
//			locationArray.push({longitude : longitude, latitude : latitude});
//		});
//		newPersonLocusPoint(wifiPlaceInAndOutInfoBeanList); // 创建人员轨迹点
//	}
	
	/**
	 * 更新监控点总数量
	 * @sum 总数
	 */
	function updatePlaceSum(sum){
		$("#placeSum").text($.util.isBlank(sum)||sum=="null"?0:sum);
	}
	
	/**
	 * 更新被监控对象总数量
	 * @sum 总数
	 */
	function updateDeviceSum(sum){
		$("#deviceSum").text($.util.isBlank(sum)||sum=="null"?0:sum);
	}
	
	/**
	 * 初始化轨迹地图
	 */
	function initLocusMap(){
		var callback = function(arcgisEsri){
	        //初始化map
			locusMap = initBaseMap("realTimeWifiMapConten");
	        //map事件
	        locusMap.on("click", function(e){
	        	$.common.arcgisMapCommon.consoleLogCoordinate(e);
	        });
		    
	        locusMap.on("load", function(e){
		    	if($.util.exist(placeBasicInfoBeanList)){
		    		newPlaceInfoPoint(placeBasicInfoBeanList);
		    		if($.util.isBlank(placeBasicInfoBeanList[0].longitude) || $.util.isBlank(placeBasicInfoBeanList[0].latitude)){
		    			return ;
		    		}
		    		$.common.arcgisMapCommon.setMapCentreAtByLonlat(placeBasicInfoBeanList[0].longitude, placeBasicInfoBeanList[0].latitude, locusMap);
		    		return;
		    	}
		    	var mapPoint = arcgisEsri.Point(11876079.465427278, 3062348.6424484807, locusMap.spatialReference);
		    	locusMap.centerAt(mapPoint);
		    }); 
	        
	        //地图底图
	        locusBaseMapServiceLayer = initBaseMapLayer();
	        locusMap.addLayer(locusBaseMapServiceLayer);
	        
	        //WIFI场所监控点infoTemplate
		    var placeInfoPointTemplate = new arcgisEsri.InfoTemplate({
	        	title:"WIFI监测点详细信息",
	        	content:function(arg0){
		        	var placeBasicInfoBean = arg0.data ;
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
		    });
		    
		    //显示wifi轨迹
		    var wifiFenceHitBeanTemplate = new arcgisEsri.InfoTemplate({
	        	title:"WIFI轨迹",
	        	content:function(arg0){
		        	var wifiFenceHitBean = arg0.data ;
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
		    });
	        
		    //WIFI场所监控点图层
	        layer_placeInfoPoint = new arcgisEsri.GraphicsLayer({ 
		    	id: "layer_placeInfoPoint",
		    	infoTemplate:placeInfoPointTemplate
		    });
	        locusMap.addLayer(layer_placeInfoPoint);

	        //重点人轨迹图层
	        layer_personLocusPoint = new arcgisEsri.GraphicsLayer({ 
		    	id: "layer_personLocusPoint",
		    	infoTemplate:wifiFenceHitBeanTemplate
		    });
	        locusMap.addLayer(layer_personLocusPoint);
		}
		$.common.arcgisMapCommon.newArcgisMap(callback);
	}
	
	/**
	 * 加载设备表格 data1
	 */	
	function initTable(data1,data2 ){
		var obj=new Array();
		var b=new Object();
		b.mac="1";
		b.enterTime="2";
		b.leaveTime="3";
		b.stayInterval="4";
		obj.push(b);
		if(table!=null){
			table=[];
		}
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = data1;
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"title": "序号",
         	    	"className":"table-checkbox",
         	    	"data": "" ,
         	    	"render": function ( data, type, full, meta ) {
         	    			  return    (meta.row+1);
         	    	}
				},
				{
					"targets" :1,
					"title" : "MAC地址",
					"data" : "mac",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"title" : "进入时间",
					"data" : "enterTime",
					"render" : function(data, type, full, meta) {
					
						return data;
					}
				},
				{
					"targets" : 3,
					"title" : "离开时间",
					"data" : "leaveTime",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
					"title" : "停留时间",
					"data" : "stayInterval",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}
			];
			tb.ordering = false;
			tb.paging = true;
			tb.hideHead = false;
			tb.dom = null;
			tb.searching = false;
			tb.lengthChange = false;
			tb.lengthMenu = [ 3, 5, 10 ];
			tb.rowCallback = function(row,data, index) {
				
			};
			table = $(data2).DataTable(tb);
	}
	
	/**
	 * 创建WIFI场所监控点
	 * @param placeBasicInfoBeanList wifi场所信息对象集合
	 */
	function newPlaceInfoPoint(placeBasicInfoBeanList){ //15秒刷新一下
		if(!$.util.exist(placeBasicInfoBeanList) || !$.util.exist(layer_placeInfoPoint)){
			return ;
		}
		layer_placeInfoPoint.clear();
		$.each(placeBasicInfoBeanList,function(p,placeBasicInfoBean){  //每个点的定义
			var longitude = placeBasicInfoBean.longitude;
			var latitude = placeBasicInfoBean.latitude;
			var layer = layer_placeInfoPoint;
			var symbolObj = {url:context+"/images/map/map-icon-wifi.png", width:22, height:35};
			var data = placeBasicInfoBean;
			$.common.arcgisMapCommon.newPointByLonlat(longitude, latitude, layer, locusMap, symbolObj, data);
		});
	}
	
	/**
	 * 创建人员轨迹点
	 * @param locationArray 人员经纬度
	 */
	function newPersonLocusPoint(wifiPlaceInAndOutInfoBeanList){
		if(!$.util.exist(wifiPlaceInAndOutInfoBeanList) || !$.util.exist(layer_personLocusPoint)){
			return ;
		}
		layer_personLocusPoint.clear();
		$.each(wifiPlaceInAndOutInfoBeanList,function(l,wifiFenceHitBean){
			var longitude = wifiFenceHitBean.longitude;
			var latitude = wifiFenceHitBean.latitude;
			var layer = layer_personLocusPoint;
			var symbolObj = {url:context+"/images/map/map-icon-trail.png", width:22, height:35};
			var data = wifiFenceHitBean;
			$.common.arcgisMapCommon.newPointByLonlat(longitude, latitude, layer, locusMap, symbolObj, data);
			$.common.arcgisMapCommon.setMapCentreAtByLonlat(longitude, latitude, locusMap)
		});
	}
	
	//生成快照
	function createSnapshotClick(){
		var baseInfo = {
			"deviceaSum":deviceaSum,//检测终端设备数
			"placeSum":placeSum, //检测wifi围栏点
			"searchText":searchText, //查询条件
		}
		var data = {
			"baseInfo":baseInfo,
			"baseBeanList":baseBeanList,//所有wifi监控点
			"wifiPlaceInAndOutInfoBeanList":wifiPlaceInAndOutInfoBeanList, //重点人的活动场所
			"placeBasicInfoBeanList":placeBasicInfoBeanList
		}
		var wifiSnapshootObject = new Object();
		wifiSnapshootObject["baseInfo"] = baseInfo ;
		wifiSnapshootObject["data"] = data ;
		
		var wifiSnapshootJson =  JSON.stringify(wifiSnapshootObject);
		
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/realTimeWifi/showRealTimeWifiSnapshotPage.action',
			pageLoading : true,
			title:"生成分析快照",
			width : "500px",
			height : "400px",
			btn:["保存","取消"],
			callBacks:{
				btn1:function(index, layero){
					var cm = $.util.topWindow().frames["layui-layer-iframe"+index].$.realTimeWifiSnapshot ;
					cm.saveSnapshot();
				},
				btn2:function(index, layero){
					$.util.topWindow().$.layerAlert.closeWithLoading(index); //关闭弹窗
				}
			},
			shadeClose : false,
			success:function(layero, index){
				
			},
			initData:{
				wifiSnapshootObject : wifiSnapshootJson ,
				mac : searchText
			},
			end:function(){
				
			}
		});
		
	}
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
		
	});	
})(jQuery);