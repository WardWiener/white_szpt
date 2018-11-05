(function($){
	"use strict";
	
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var id = initData.id;
	
	var  trackmap = null;//wifi轨迹地图
	var  hotmap = null;//wifi热力地图
	
	var trackHotLayer = null;
	
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
				var data = successDate.result ;
				var intro = successDate.intro ;
				var wifiPlaceInAndOutInfoBeanList = data.wifiPlaceInAndOutInfoBeanList;
				//加载停留次数最多的场所
				setPlaceFrequencyOrderList(data.hitMaximumSumBeanList);
				//加载停留时间最长的场所
				setMaxTimePlaceOrderList(data.hitLongSumBeanList);
				//初始化查询信息
				initSearchInfo(data.searchInfo);
				//初始化轨迹table
				initLocusTable(wifiPlaceInAndOutInfoBeanList);
			
				$.wifiLocusMap.newPlaceInfoPoint(trackmap,wifiPlaceInAndOutInfoBeanList);
				$.wifiLocusMap.createHotMap(hotmap,trackHotLayer,wifiPlaceInAndOutInfoBeanList);
				
				//设置保存快照原因
				$("#intro").text($.util.isBlank(intro) ? "" : intro);
			}
		})
	}
	
	function initSearchInfo(baseInfo){
		$('#tel').attr("disabled",true); 
		$("#searchText").val(baseInfo.search);
		
		if(baseInfo.searchTimeBtn == "dateCustomized"){
			$("#zdySpan").show();
		}else{
			$.each($(".timeBtn"),function(e,m){
				if($(this).attr("id") == baseInfo.searchTimeBtn){
					$(this).attr("class","btn btn-primary btn-danger btn-sm searchTime-btn timeBtn")
				}else{
					$(this).attr("class","btn btn-primary btn-sm searchTime-btn timeBtn")
				}
			})
		}
		$("#wifiStartTime").val(baseInfo.fixed_start)
		$("#wifiEndTime").val(baseInfo.fixed_end)
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
	 * 设置该人员经过次数最多的场所列表
	 * @param hitSumBeanList 场所次数降序列表
	 */
	function setPlaceFrequencyOrderList(hitSumBeanList){
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
				"class" : "fi-ceng alert-info"
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
				"class" : "fi-ceng alert-info"
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
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
		
	});	
})(jQuery);