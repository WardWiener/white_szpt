(function($){
	"use strict";
	
	var  trackmap1 = null;//轨迹地图1
	var  hotmap =null;//热力地图
	
	var trackHotLayer = null;
	
	var table = null;
	
	var trackMapLayer = null;//轨迹地图图层
	var  trackLineLayer = null;//创建wifi轨迹线	
	
	
	$(document).ready(function() {	
		initMap();
		$.multiBaseMap.addToMapReadyExeList(mapReady);
		
		/**
		 * 小程序关闭按钮
		 */
		$(document).on("click", "#btn-close-window", function(){
			exitForm();//关闭方法 
		});
		
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
		
	});
	
	function init(){
		var data = {
				"id":snapshotId,
		}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/enterbaq/findSnapshotById.action',
			type:"post",
			data :dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				var data = successDate.result;
				setTrackTimeShaft(data.personTrackInfoLst);
				var monitoringGuardList = [];
				
				setTrackCountTable("countTable1",data.hotelList,data.trainOutList);
				setTrackCountTable("countTable2", data.internetBarList,data.trainInList);
				setTrackCountTable("countTable3",monitoringGuardList,data.airPlaneOutList);
				setTrackCountTable("countTable4",data.wifiList,data.airPlaneInList);
				newPlaceInfoPoint(trackmap1,data.personTrackInfoLst);
				createHotMap(hotmap,trackHotLayer,data.personTrackInfoLst);
				
				//设置高危人基本信息
				var highriskPersonBean = data.baseInfo;
				setHighriskPersonInfo(highriskPersonBean);
			}
		});
	}
	
	/**
	 * 设置高危人基本信息
	 * 
	 * @param highriskPersonBean 高危人信息Bean
	 * @returns
	 */
	function setHighriskPersonInfo(highriskPersonBean){
		if($.util.exist(highriskPersonBean)){
			if(!$.util.isBlank(highriskPersonBean.createdTime)){
				highriskPersonBean.createdTime = $.date.timeToStr(highriskPersonBean.createdTime, "yyyy-MM-dd HH:mm:ss");
			}
			$.validform.setFormTexts("#highriskPersonInfo", highriskPersonBean);
			//设置电话
			if($.util.exist(highriskPersonBean.phone)){
				var phone = "";
				$.each(highriskPersonBean.phone,function(i,val){
					phone += val + "<br>" ;
				});
				$("#phone").html(phone);
			}
			//设置mac
			if($.util.exist(highriskPersonBean.mac)){
				var mac = "";
				$.each(highriskPersonBean.mac,function(i,val){
					mac += val + "<br>" ;
				});
				$("#mac").html(mac);
			}
			//设置预警类型
			var warnTypeName = highriskPersonBean.warnTypeName;
			switch (highriskPersonBean.warnType){
				case YJLX_HONG_SE:
					var span = '<span class="btn sq-red" title="红">' + warnTypeName + '</span>';
					$("#warnType").html(span);
					$("#warnType").show();
					break;
				case YJLX_CHENG_SE:
					var span = '<span class="btn sq-orange" title="橙">' + warnTypeName + '</span>';
					$("#warnType").html(span);
					$("#warnType").show();
					break;
				case YJLX_HUANG_SE:
					var span = '<span class="btn sq-yellow" title="黄">' + warnTypeName + '</span>';
					$("#warnType").html(span);
					$("#warnType").show();
					break;
				case YJLX_BAI_SE:
					var span = '<span class="btn sq-white" title="白">' + warnTypeName + '</span>';
					$("#warnType").html(span);
					$("#warnType").show();
					break;
				case YJLX_LAN_SE:
					var span = '<span class="btn sq-blue" title="蓝">' + warnTypeName + '</span>';
					$("#warnType").html(span);
					$("#warnType").show();
					break;
				default:
					var span = '<span class="btn sq-other" title="其它">' + warnTypeName + '</span>';
					$("#warnType").html(span);
					$("#warnType").show();
					break;
			}
		}
	}
	
	/**
	 * 根据身份证号和时间段查询人员轨迹
	 */
	
	
	function loadTrackCasePoint(dataLst){
		for(var i in dataLst){
//			$.suspectMacAnalysisMap.addRecentlyKnownCasePoint(dataLst[i]);
		}
	}
	
	function rightTableRefresh(){
		$(".hotel-pannel .dataTr td").html("");
	}
	
	/**
	 * 设置轨迹统计表
	 * @param tableId table表id
	 * @param leftDataArr 左边统计项数据{name:,count,[详细数据数组]}
	 * @param rightDataArr 右边统计项数据
	 */
	function setTrackCountTable(tableId,leftDataArr,rightDataArr){
		var length = 3;//表默认最大行数
		
		//设置左边统计数据
		if(leftDataArr.length < 3){
			length = leftDataArr.length;
		}
		var leftClass="";
		var rightClass="";
		if(tableId=="countTable1"){
			leftClass="hotelCount";
			rightClass="trainOutCount";
		}else if(tableId=="countTable2"){
			leftClass="internetBarCount";
			rightClass="trainInCount";
		}else if(tableId=="countTable3"){
			leftClass="monitoringGuardCount";
			rightClass="trainInCount";
		}else if(tableId=="countTable4"){
			leftClass="wifiCount";
			rightClass="airPlaneInCount";
		}
		for(var i=0; i<length; i++){
			var tr = $("#" + tableId + " tbody tr")[i];
			$(tr).children('td').eq(0).text(leftDataArr[i].groupName);
			$(tr).children('td').eq(1).html('<a name="'+leftDataArr[i].groupName+'"class="'+leftClass+' count font16 color-red1 mar-right-sm">'+leftDataArr[i].totalInterval+'</a>次');
		}
		//设置右边统计数据
		if(rightDataArr.length < 3){
			length = rightDataArr.length;
		}
		for(var i=0; i<length; i++){
			var tr = $("#" + tableId + " tbody tr")[i];
			$(tr).children('td').eq(2).text(rightDataArr[i].groupName);
			$(tr).children('td').eq(3).html('<a name="'+rightDataArr[i].groupName+'"class="'+rightClass+' count font16 color-red1 mar-right-sm">'+rightDataArr[i].totalInterval+'</a>次');
		}
	}
	
	/**
	 * 设置轨迹时间轴
	 */
	function setTrackTimeShaft(trackArray){
		$("#trackTimeShaft").html("");
		if(!$.util.exist(trackArray) || trackArray.length < 1){
			return ;
		}
		$.each(trackArray,function(i,track){
			if(track.isWifi){
				return;
			}
			var className = "odd";
			if(i % 2 == 0){
				className = "even";
			}
			var li = $("<li />",{
				"class" : className
			});
			$("<span />",{
				"class" : "icon-red-dot"
			}).appendTo(li);
			var titleDiv = $("<div />",{
				"class" : "time-box"
			}).appendTo(li);
			$("<p />",{
				"class" : "pot",
				"text" : track.trackTypeDescription
			}).appendTo(titleDiv);
			var describeDiv = $("<div />",{
				"class" : "con-box",
				"text" : track.trackDescription
			}).appendTo(li);
			$("<span />",{
				"class" : "arrow"
			}).prependTo(describeDiv);
			$("#trackTimeShaft").append(li);
		});
		$("#trackTimeShaft").append($("<li />",{"class" : "clear"}));
	}
	
	function initLocasTable(dataLst){
		var st1 = $.uiSettings.getLocalOTableSettings();
		$.util.log(st1);
		st1.data = dataLst;
		st1.columnDefs = [ 
			{ 
				"targets": 0,
				"width": "20%",
				"title": '轨迹类型',
				"className":"",
				"data": "trackTypeDescription" ,
				"render": function ( data, type, full, meta ) {		
				      return data;
				}
			},
			{ 
				"targets": 1,
				"width": "20%",
				"title": "始发地",
				"className":"",
				"data": "originLocation" ,
				"render": function ( data, type, full, meta ) {		
				      return data;
				}
			},
			{ 
				"targets": 2,
				"width": "20%",
				"title": '目的地',
				"searchable" : true,
				"data": "destinationLocation" ,
				"render": function ( data, type, full, meta ) {
					return data;
				}
			},
			{ 
				"targets": 3,
				"width": "25%",
				"title": '时间',
				"searchable" : true,
				"data": "appearTime" ,
				"render": function ( data, type, full, meta ) {
					return data;
				}
			}
		];

		st1.ordering = false;
		st1.paging = false;
		st1.hideHead = false;
		st1.searching = false;
		st1.info = false ; 
		st1.myDrawCallback = function(settings){
		 };
		st1.myDrawCallback = function(settings){
		 };
		table = $("#trackTable").DataTable(st1);
	}
	
	function initMap(){
		$.multiBaseMap.init(["locusPointMapConten", "locusHotMapConten"]);
	}
	
	var mapReady = function (){
		trackmap1 = $.multiBaseMap.getInstance("locusPointMapConten");
		hotmap = $.multiBaseMap.getInstance("locusHotMapConten");
		trackHotLayer =	hotmap.createHeatLayer();
		trackmap1.setLoadCallback(function(e){
			init();
		});
	}
	
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
	jQuery.extend($.trackAnalyze, { 
		
	});	
})(jQuery);