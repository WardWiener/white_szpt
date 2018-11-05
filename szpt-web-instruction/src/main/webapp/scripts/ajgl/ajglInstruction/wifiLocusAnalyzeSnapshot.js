(function($){
	"use strict";
	
	var  trackmap = null;//wifi轨迹地图
	var  hotmap = null;//wifi热力地图
	
	var trackHotLayer = null;
	
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
				"id":snapshotId
		}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/enterbaq/findSnapshotById.action',
			type:"post",
			data : dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				var data = successDate.result ;
				var wifiPlaceInAndOutInfoBeanList = data.wifiPlaceInAndOutInfoBeanList;
				//加载停留次数最多的场所
				setPlaceFrequencyOrderList(data.hitMaximumSumBeanList);
				//加载停留时间最长的场所
				setMaxTimePlaceOrderList(data.hitLongSumBeanList);
				//初始化查询信息
				initSearchInfo(data.searchInfo);
				//初始化轨迹table
				initLocusTable(wifiPlaceInAndOutInfoBeanList);
				newPlaceInfoPoint(trackmap,wifiPlaceInAndOutInfoBeanList);
				createHotMap(hotmap,trackHotLayer,wifiPlaceInAndOutInfoBeanList);
			}
		})
	}
	
	function initSearchInfo(baseInfo){
		$('#tel').attr("disabled",true); 
		$("#searchText").val(baseInfo.search);
		
		$.each($(".timeBtn"),function(e,m){
			if($(this).attr("id") != baseInfo.searchTimeBtn){
				$(this).attr("class","btn btn-primary btn-sm timeBtn")
			}else{
				$(this).attr("class","btn btn- btn-sm timeBtn")
			}
		})
		if(baseInfo.searchTimeBtn == "diyTime"){
			$("#diyDateRange").show();
			$("#wifiStartTime").val(baseInfo.fixed_start)
			$("#wifiEndTime").val(baseInfo.fixed_end)
		}
		
	}
	
	/**
	 * 初始化轨迹信息表格
	 */
	function initLocusTable(tableLst){
		var locusTable = null;
		var tb = $.uiSettings.getLocalOTableSettings();
		tb.data = tableLst;
		tb.columnDefs = [
			{
				"targets": 0,
     	    	"width": "50px",
     	    	"title": "序号",
     	    	"className":"table-checkbox",
     	    	"data": "" ,
     	    	"render": function ( data, type, full, meta ) {
     	    			  return meta.row+1+'&nbsp &nbsp';
     	    	}
			},
			{
				"targets" : 1,
				"width" : "150px",
				"title" : "场所名称",
				"data" : "placeName",
				"render" : function(data, type, full, meta) {
					return data;
				}
			},
			{
				"targets" : 2,
				"width" : "150px",
				"title" : "进入时间",
				"data" : "enterTime",
				"render" : function(data, type, full, meta) {
					var data = $.date.timeToStr(data, "yyyy-MM-dd HH:mm:ss");
					return data;
				}
			},
			{
				"targets" : 3,
				"width" : "150px",
				"title" : "离开时间",
				"data" : "leaveTime",
				"render" : function(data, type, full, meta) {
					var data = $.date.timeToStr(data, "yyyy-MM-dd HH:mm:ss");
					return data;
				}
			}
		];
		//是否排序
		tb.ordering = false ;
		//每页条数
		tb.lengthMenu = [ 10 ];
		//默认搜索框
		tb.searching = false ;
		//能否改变lengthMenu
		tb.lengthChange = false ;
		//自动TFoot
		tb.autoFooter = false ;
		//自动列宽
		tb.autoWidth = false ;
		//
		locusTable = $("#locusTable").DataTable(tb);
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
	
	/**
	 * 设置该人员经过次数最多的场所列表
	 * @param hitSumBeanList 场所次数降序列表
	 */
	function setPlaceFrequencyOrderList(hitSumBeanList){
		console.log(hitSumBeanList);
		$("#placeFrequencyOrderTable tbody").html("");
		$.each(hitSumBeanList,function(h,hitSumBean){
			if(h >= 3){
				return false;
			}
			var tr = $("<tr>",{});
			$("<td>",{
				"width" : "70%",
				"text" : hitSumBean.placeName
			}).appendTo(tr);
			var td = $("<td>",{}).appendTo(tr);
			var tdDiv = $("<div>",{
				"class" : "fi-ceng-out"
			}).appendTo(td);
			//总数button
			$("<button>",{
				"class" : "btn btn-xs btn-bordered fi-ceng-out-but",
				"text" : hitSumBean.count + " 次"
			}).appendTo(tdDiv);
			//详细时间表div
			var timeInfoDiv = $("<div>",{
				"class" : "fi-ceng alert-info",
				"style" : "width:300px;left:-300px;"
			}).appendTo(tdDiv);
			//标题
			$("<h4>",{
				"class" : "row-mar",
				"text" : hitSumBean.placeName
			}).appendTo(timeInfoDiv);
			//时间表ul
			var timeUl = $("<ul>",{
				"class" : "list-group"
			}).appendTo(timeInfoDiv);
			
			$.each(hitSumBean.timeIntervalList,function(t,timeInterval){
				//时间表li
				var timeLi = $("<li>",{
					"class" : "list-group-item"
				}).appendTo(timeUl);
				//时间表信息
				$("<span>",{
					"class" : "badge alert-warning",
					"text" : t + 1
				}).appendTo(timeLi);
				$("<span>",{
					"text" : "mac地址：" + timeInterval.macAddress
				}).appendTo(timeLi);
				$("<br>").appendTo(timeLi);
				$("<span>",{
					"text" : "进入时间：" + $.date.timeToStr(timeInterval.enterTime,"yyyy-MM-dd HH:mm:ss")
				}).appendTo(timeLi);
				$("<br>").appendTo(timeLi);
				$("<span>",{
					"text" : "离开时间：" + $.date.timeToStr(timeInterval.leaveTime,"yyyy-MM-dd HH:mm:ss")
				}).appendTo(timeLi);
			});
			$("#placeFrequencyOrderTable tbody").append(tr);
		});
	}
	
	/**
	 * 设置该人员驻留时间最长的场所列表
	 * @param wifiPlaceInAndOutInfoBeanList wifi围栏命中信息Bean集合
	 */
	function setMaxTimePlaceOrderList(hitSumBeanList){
		console.log(hitSumBeanList);
		$("#maxTimeplaceOrderTable tbody").html("");
		if(!$.util.isArray(hitSumBeanList) || hitSumBeanList.length < 1){
			return ;
		}
		//按照间距排序
		hitSumBeanList.sort(function(a,b){
	        return b.stayInterval-a.stayInterval
	    });
		
		$.each(hitSumBeanList,function(w,hitSumBean){
			if(w >= 3){
				return false;
			}
			var tr = $("<tr>",{});
			$("<td>",{
				"width" : "70%",
				"text" : hitSumBean.placeName
			}).appendTo(tr);
			var td = $("<td>",{}).appendTo(tr);
			var tdDiv = $("<div>",{
				"class" : "fi-ceng-out"
			}).appendTo(td);
			//总时间button
			$("<button>",{
				"class" : "btn btn-xs btn-bordered fi-ceng-out-but",
				"text" : millisecondToDate(hitSumBean.stayInterval)
			}).appendTo(tdDiv);
			//详细时间表div
			var timeInfoDiv = $("<div>",{
				"class" : "fi-ceng alert-info",
				"style" : "width:300px;left:-250px;"
			}).appendTo(tdDiv);
			//标题
			$("<h4>",{
				"class" : "row-mar",
				"text" : hitSumBean.placeName
			}).appendTo(timeInfoDiv);
			//时间表ul
			var timeUl = $("<ul>",{
				"class" : "list-group"
			}).appendTo(timeInfoDiv);
			
			$.each(hitSumBean.timeIntervalList,function(t,timeInterval){
				//时间表li
				var timeLi = $("<li>",{
					"class" : "list-group-item"
				}).appendTo(timeUl);
				//时间表信息
				$("<span>",{
					"class" : "badge alert-warning",
					"text" : t + 1
				}).appendTo(timeLi);
				$("<span>",{
					"text" : "mac地址：" + timeInterval.macAddress
				}).appendTo(timeLi);
				$("<br>").appendTo(timeLi);
				$("<span>",{
					"text" : "进入时间：" + $.date.timeToStr(timeInterval.enterTime,"yyyy-MM-dd HH:mm:ss")
				}).appendTo(timeLi);
				$("<br>").appendTo(timeLi);
				$("<span>",{
					"text" : "离开时间：" + $.date.timeToStr(timeInterval.leaveTime,"yyyy-MM-dd HH:mm:ss")
				}).appendTo(timeLi);
			});
			$("#maxTimeplaceOrderTable tbody").append(tr);
		});
	}
	
	function initMap(){
		$.multiBaseMap.init(["wifiLocusMapConten","wifiLocusHotMapConten"]);
	}
	
	var mapReady = function (){
		trackmap = $.multiBaseMap.getInstance("wifiLocusMapConten");
		hotmap = $.multiBaseMap.getInstance("wifiLocusHotMapConten");
		trackHotLayer =	hotmap.createHeatLayer();
		trackmap.setLoadCallback(function(e){
			init();
		});
	}
	
	//创建建轨迹地图
	function newPlaceInfoPoint(map,wifiInfoBeanList){
		
		if(!$.util.exist(wifiInfoBeanList)){
			return;
		}
		var placeInfoPointTemplate = map.createInfoTemplate("人员轨迹监测点详细信息", placeInfoPointLayerContent);
		if($.util.exist(trackMapLayer)){
			trackMapLayer.clear();
		}
		var trackLineLst = [];
		$.each(wifiInfoBeanList,function(p,trackInfo){  //每个点的定义
			if(trackInfo.longitude && trackInfo.latitude){
				var lonlat = {
						longitude : trackInfo.longitude ,
						latitude : trackInfo.latitude
					}
					var graphic = map.createPoint("trackMapLayer", lonlat, null, trackInfo, placeInfoPointTemplate);
					if(!$.util.exist(trackMapLayer) && $.util.exist(graphic)){
						trackMapLayer = graphic.getLayer();
					}
				trackLineLst.push(lonlat);
			}
		});
		//* @param style 线样式，例如：{r:颜色值,g:颜色值,b:颜色值,width:线宽}
		var style = {r:255,g:0,b:0,width:3}
		map.createLine("trackLineLayer", trackLineLst, style , null);//创建线
	}

	var placeInfoPointLayerContent = function(graphic){
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
    	//场所名称
    	if(!$.util.isBlank(wifiFenceHitBean.placeName)){
    		$("<div />", {
        		"class":"row",
    			"style":"margin-top:5px",
    			"text":"场所名称："+wifiFenceHitBean.placeName
        	}).appendTo(baseDiv_contentDiv);
    	}
    	//进入时间
    	if(!$.util.isBlank(wifiFenceHitBean.enterTime)){
    		$("<div />", {
        		"class":"row",
    			"style":"margin-top:5px",
    			"text":"进入时间："+$.date.timeToStr(wifiFenceHitBean.enterTime, "yyyy-MM-dd HH:mm:ss")
        	}).appendTo(baseDiv_contentDiv);
    	}
	    //离开时间
    	if(!$.util.isBlank(wifiFenceHitBean.leaveTime)){
    		$("<div />", {
        		"class":"row",
        		"style":"margin-top:5px",
    			"text":"离开时间："+$.date.timeToStr(wifiFenceHitBean.leaveTime, "yyyy-MM-dd HH:mm:ss")
        	}).appendTo(baseDiv_contentDiv);
    	}
		return baseDiv[0];
	}
	
	//创建热力地图
	function createHotMap(map,hotLayer,initLst){
		map.showHeatLayer(hotLayer,initLst);
	}
	
	function timeToDate(time){
		var str = parseInt(time / 86400.0) + "天 ";
		time -= parseInt(time / 86400.0) * 86400;
		return str + timeToHour(time);
	}
	
	function timeToHour(time){
		var str = parseInt(time / 3600.0) + "小时 ";
		time -= parseInt(time / 3600.0) * 3600;
		return str + timeToMinute(time);
	}
	
	function timeToMinute(time){
		var str = parseInt(time / 60.0) + "分钟 ";
		time -= parseInt(time / 60.0) * 60;
		return str + timeToSecond(time);
	}
	
	function timeToSecond(time){
		return time + "秒";
	}
	
	function millisecondToDate(msd) {
	    var time = parseFloat(msd) / 1000;
	    if(time > 86400){
	    	return timeToDate(time);
	    }else if(time > 3600){
	    	return timeToHour(time);
	    }else if(time > 60){
	    	return timeToMinute(time);
	    }else{
	    	return timeToSecond(time);
	    }
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
	jQuery.extend($.common, { 
		
	});	
})(jQuery);