
$.trackAnalyzeSnapshotLst = $.trackAnalyzeSnapshotLst || {};
(function($){
	"use strict";
	
	var table = null;
	$(document).ready(function() {	
		
	});
	
	$(document).on("click","#trackAnalyzeTable tr",function(){
		var rowData = $(this).data("data");
		lookTrackSnapshotDetail(rowData.id);
	})
	
	function init(){
		var data = {
				"idCode":idcard,
		}
		var dataMap = $.util.toJSONString(data);
		$.ajax({
			url:context +'/yrydSnapshot/findTrackSnapshotLst.action',
			type:"post",
			data :dataMap,
			contentType : "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				if(successDate.resultMap.result.length > 1){
					$("#trackAnalyzeLst").show();
					$.dagl.yryd.yrydHighriskPersonAlert.createTrackAnalyzeLstTable("trackAnalyzeTable",successDate.resultMap.result)
				}else if(successDate.resultMap.result.length == 1){
					$("#trackAnalyzeinfo").show();
					initTrackAnalyzeInfo(successDate.resultMap.result[0].id)
				}else{
					$("#nullTrackAnalyzeSnapshotDiv").show();
				}
			}
		});
	}
	
	function initTrackAnalyzeInfo(id){
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
				$.trackAnalyzeMap.newPlaceInfoPoint(locusPointMapConten,data.personTrackInfoLst);
				$.trackAnalyzeMap.createHotMap(locusHotMapConten,trackHotLayer,data.personTrackInfoLst);
				$("#trackAnalyzeSnapshotInfo").text($.util.isBlank(intro) ? "" : intro);
			}
		});
	}
	
	function lookTrackSnapshotDetail(id){
		
		window.top.$.layerAlert.dialog({
			content : context +  '/yrydSnapshot/showtrackAnalyzeDetail.action',
			pageLoading : true,
			title:"轨迹分析快照详情",
			width : "1000px",
			height : "650px",
			btn:["关闭"],
			callBacks:{
				btn1:function(index, layero){
					window.top.$.layerAlert.closeWithLoading(index); 
				}
			},
			shadeClose : false,
			success:function(layero, index){
			},
			initData:{
				id : id
			},
			end:function(){
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
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.trackAnalyzeSnapshotLst, { 
		trackSnapshotLstInit:init
	});	
})(jQuery);