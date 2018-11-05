(function($){
	"use strict";
	
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var id = initData.id;

	var  trackmap1 = null;//轨迹地图1
	var  trackmap2 = null;//轨迹地图2
	var  hotmap =null;//热力地图
	
	var trackHotLayer = null;
	
	var table = null;
	$(document).ready(function() {	
		initMap();
		$.multiBaseMap.addToMapReadyExeList(mapReady);
	});
	function init(){
	
		var data = {
				"id":id,
		}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/yrydSnapshot/findSnapshotById.action',
			type:"post",
			data :dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				var data = successDate.result;
				var intro = successDate.intro;
				setTrackTimeShaft(data.personTrackInfoLst);
				setTrackCountTable("countTable1",data.hotelList,data.trainOutList);
				setTrackCountTable("countTable2", data.internetBarList,data.trainInList);
				setTrackCountTable("countTable3",data.monitoringGuardList,data.airPlaneOutList);
				setTrackCountTable("countTable4",data.wifiList,data.airPlaneInList);
				$.trackAnalyzeMap.newPlaceInfoPoint(trackmap1,data.personTrackInfoLst);
				$.trackAnalyzeMap.createHotMap(hotmap,trackHotLayer,data.personTrackInfoLst);
				
				$("#trackAnalyzeSnapshotInfo").text($.util.isBlank(intro) ? "" : intro);
			}
		});
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
				"class" : "color-yellow2",
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
	function initMap(){
		$.multiBaseMap.init(["locusPointMapConten","locusMapConten","locusHotMapConten"]);
	}
	
	var mapReady = function (){
		trackmap1 = $.multiBaseMap.getInstance("locusPointMapConten");
		trackmap2 = $.multiBaseMap.getInstance("locusMapConten");
		hotmap = $.multiBaseMap.getInstance("locusHotMapConten");
		trackHotLayer =	hotmap.createHeatLayer();
		trackmap1.setLoadCallback(function(e){
			init();
		});
	}

	
})(jQuery);