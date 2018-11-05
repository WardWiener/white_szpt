$.lookRealTimeWifiSnapshot = $.lookRealTimeWifiSnapshot || {};

(function($){

	"use strict";
	
	var pbibs = null;
	var realTimeWifiMapConten = null;
	var placeInfoPointLayer = null;
	var personLocusPointLayer = null;
	
	$(document).ready(function() {	
		/**
		 * 关闭按钮旋转
		 */
		var value = 0
    	$("#btn-close-window").rotate({ 
    	   bind: 
    	     { 
    	        mouseover: function(){
    	            value +=90;
    	            $(this).rotate({ animateTo:value, duration: 500})
    	        },
    	        mouseleave: function(){
    	            value +=-90;
    	            $(this).rotate({ animateTo:value})
    	      }
    	     } 
    	});
		
		/**
		 * 小程序关闭按钮
		 */
		$(document).on("click", "#btn-close-window", function(){
			exitForm();//关闭方法 
		});
		
		//初始化地图
		$.multiBaseMap.init(["realTimeWifiMapConten"]);
		//添加初始化map完成后执行的函数
		$.multiBaseMap.addToMapReadyExeList(mapReady) ;
		
		/**
		 * 实时wifi场所列表li点击事件
		 */
		$(document).on("click",".placeLi",function(){
			$.each( $(".placeLi"), function(e,m){
				$(m).removeAttr("style")
			})
			$(this).attr("style","background-color: #C5E4FF;")
			
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
					realTimeWifiMapConten.setMapCenterAt(lonlat);
				}
			});
			var graphics = placeInfoPointLayer.graphics;
			for(var i in graphics){
				if(graphics[i].data.internetServicePlaceName==placeName){
					graphics[i].symbol.url=context+"/images/map/icon-site.png";
					graphics[i].symbol.width=25;
					graphics[i].symbol.height=35;
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
	 * map初始化之后执行的方法
	 */
	var mapReady = function(){
		//获取map实例
		realTimeWifiMapConten = $.multiBaseMap.getInstance("realTimeWifiMapConten");
		
		realTimeWifiMapConten.setLoadCallback(function(e){
			findWifiSnapshotById();
		});
	}
	
	/**
	 * 根据id查询wifi快照
	 * 
	 * @returns
	 */
	function findWifiSnapshotById(){
		var data = {
			"id" : snapshotId
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/enterbaq/findSnapshotById.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    	customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var snapshotObject = successData.result;
				var intro = successData.intro;
				$("#wifiSnapshotInfo").text($.util.isBlank(intro) ? "" : intro);
				if($.util.exist(snapshotObject)){
					setSnapshotPage(snapshotObject);
				}
			}
		});
	}
	
	/**
	 * 设置快照页面
	 * @returns
	 */
	function setSnapshotPage(snapshotObject){
		updateWifiPlaceUl(snapshotObject.data.baseBeanList);
		newPlaceInfoPoint(snapshotObject.data.placeBasicInfoBeanList);
		newPersonLocusPoint(snapshotObject.data.wifiPlaceInAndOutInfoBeanList);
	}
	
	/**
	 * 创建人员轨迹点
	 * @param locationArray 人员经纬度
	 */
	function newPersonLocusPoint(wifiPlaceInAndOutInfoBeanList){
		if(!$.util.exist(wifiPlaceInAndOutInfoBeanList)){
			return ;
		}
		
		var personLocusPointTemplate = realTimeWifiMapConten.createInfoTemplate("WIFI轨迹", personLocusPointLayerContent);
		if($.util.exist(personLocusPointLayer)){
			personLocusPointLayer.clear();
		}
		
		$.each(wifiPlaceInAndOutInfoBeanList,function(l,wifiFenceHitBean){
			var lonlat = {
				longitude : wifiFenceHitBean.longitude ,
				latitude : wifiFenceHitBean.latitude
			}
			var symbolObj = {url:context+"/images/map/map-icon-trail.png", width:22, height:35};
			
			var graphic = realTimeWifiMapConten.createPoint("personLocusPointLayer", lonlat, symbolObj, wifiFenceHitBean, personLocusPointTemplate);
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
		var placeInfoPointTemplate = realTimeWifiMapConten.createInfoTemplate("WIFI监测点详细信息", placeInfoPointLayerContent);
		if($.util.exist(placeInfoPointLayer)){
			placeInfoPointLayer.clear();
		}
		$.each(placeBasicInfoBeanList,function(p,placeBasicInfoBean){  //每个点的定义
			var lonlat = {
				longitude : placeBasicInfoBean.longitude ,
				latitude : placeBasicInfoBean.latitude
			}
			var symbolObj = {url:context+"/images/map/map-icon-wifi.png", width:22, height:35};
			
			var graphic = realTimeWifiMapConten.createPoint("placeInfoPointLayer", lonlat, symbolObj, placeBasicInfoBean, placeInfoPointTemplate);
			if(!$.util.exist(placeInfoPointLayer) && $.util.exist(graphic)){
				placeInfoPointLayer = graphic.getLayer();
			}
		});
	}
	
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
			var li = '<li class="list-group-item placeLi"><span class="badge m-badge">' + baseBean.deviceSum + '</span><div class="t-over" style="width:100px;" tooltipPos="top" my="center bottom+60" at="center top" title="' + baseBean.placeName + '">' + baseBean.placeName + '</div></li>';
			if(b == 0){
				li = '<li class="list-group-item list-group-item-danger placeLi"><span class="badge m-badge">' + baseBean.deviceSum + '</span><div class="t-over" style="width:100px;" tooltipPos="top" my="center bottom+60" at="center top" title="' + baseBean.placeName + '">' + baseBean.placeName + '</div></li>';
			}else if(b == 1){
				li = '<li class="list-group-item list-group-item-warning placeLi" style="background-color: #ffe4ce; color: #e26500;"><span class="badge m-badge">' + baseBean.deviceSum + '</span><div class="t-over" style="width:100px;" tooltipPos="top" my="center bottom+60" at="center top" title="' + baseBean.placeName + '">' + baseBean.placeName + '</div></li>';
			}else if(b == 2){
				li = '<li class="list-group-item list-group-item-warning placeLi"><span class="badge m-badge">' + baseBean.deviceSum + '</span><div class="t-over" style="width:100px;" tooltipPos="top" my="center bottom+60" at="center top" title="' + baseBean.placeName + '">' + baseBean.placeName + '</div></li>';
			}
			$("#wifiPlaceUl").append(li);
		});
	}
	
	
	/*****************************小程序加载部分************************************/
	  //拖动处理
	var oldX = 0;
	var oldY = 0;
	var addPressEvent = 0;
	document.getElementById("divMouseMove").onmousedown = function() {
		oldX = event.screenX;
		oldY = event.screenY;
		if (addPressEvent == 0) {
			addPressEvent = 1;
			if (document.attachEvent) {
				document.detachEvent('onmousemove', moveOnMousePress);
				document.detachEvent('onmouseup', moveOnMouseUP);
				document.attachEvent('onmousemove', moveOnMousePress, false);
				document.attachEvent('onmouseup', moveOnMouseUP, false);
			} else {
				document.removeEventListener('mousemove', moveOnMousePress);
				document.removeEventListener('mouseup', moveOnMouseUP);
				document.addEventListener('mousemove', moveOnMousePress, false);
				document.addEventListener('mouseup', moveOnMouseUP, false);
			}
		}
	}
	var rushCount = 0;
	function moveOnMousePress() {
		rushCount++;//降低频率
		if (rushCount > 7) {
			rushCount = 0;
			moveForm(event.screenX - oldX, event.screenY - oldY);
			oldX = event.screenX;
			oldY = event.screenY;
		}
	}
	function moveOnMouseUP() {
		addPressEvent = 0;
		if (document.attachEvent) {
			document.detachEvent('onmousemove', moveOnMousePress);
			document.detachEvent('onmouseup', moveOnMouseUP);
		} else {
			document.removeEventListener('mousemove', moveOnMousePress);
			document.removeEventListener('mouseup', moveOnMouseUP);
		}
		//松开最后执行一次
		moveForm(event.screenX - oldX, event.screenY - oldY);
		oldX = event.screenX;
		oldY = event.screenY;
	}
	//--拖动处理完

	  function getRoomNum() {
	    roomNum = getConfig("Basic", "RoomNumber");
	  return roomNum;
	} 
  
//读取配置文件
  function getConfig(node,key) {
      return window.external.getConfig(node,key); 
  }
  //读取配置文件
  function getConfigAll(node) {
      return window.external.getConfigAllString(node); 
  }
//设置小图标右键菜单大小
  function setMenuWindowSize(x, y) {
  	window.external.setWindowSize(x, y);
  }

   //打开menu
  function openMenuForm(Url, formWidth, formHeight)
  {

      window.external.openUrlByMenuForm(Url, formWidth, formHeight);
  }
  //windows方法
  //IE打开url
  function openUrlExplorer(Url) {
      window.external.openUrlExplorer(Url);
  }
  function moveForm(addX,addY) {
      if(addX==0&&addY==0)return;
      window.external.moveForm(addX,addY);
  }
//结束程序
  function exitProgram() {
      window.external.exitProgram();
  }

  function hideForm() {
      window.external.hideForm();
  }
  
//关闭窗口
  function exitForm() {
  	window.external.exitForm();
  }
	
	/**
	 * 开启窗口
	 */
	function openWebForm(formId,formName,Url, formWidth, formHeight)
	{
	    window.external.openUrlByWinForm(formId,formName,Url, formWidth, formHeight);
	}
	
	/*************************小程序end******************************************/
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.lookRealTimeWifiSnapshot, { 
		
	});	
})(jQuery);