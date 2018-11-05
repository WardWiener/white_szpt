(function($){
	"use strict";
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var list = initData.list;
	var type = initData.type;
	var table;
	$(document).ready(function() {	
		if(type==1){
			initTable();
		}else if(type=="wifi"){
			initWifiTable();
		}else if(type=="wifiCount"){
			initWifiTable();
		}else if(type=="monitoringGuard"){
			initMonitoringGuardTable();
		}else if(type=="monitoringGuardCount"){
			initMonitoringGuardTable();
		}
	});
	function initTable(){
		if(!list || list.length<=0){
			var a='<tr><td align="center">'+'无数据'+'</td></tr>';
			$("#table tbody").append(a);
		}else{
			for(var i in list){
				var a='<tr><td>'+list[i].trackDescription+'</td></tr>';
				$("#table tbody").append(a);
			}
		}
	}
	function initWifiTable(){
		$("#table thead").append('<tr><td>地点</td><td>进入时间</td><td>离开时间</td></tr>');
		if(!list || list.length<=0){
			var a='<tr><td colspan="3" align="center">'+'无数据'+'</td></tr>';
			$("#table tbody").append(a);
		}else{
			for(var i in list){
				var a='<tr><td>'+list[i].placeName+'</td><td>'+$.date.timeToStr(list[i].appearTime, "yyyy-MM-dd HH:mm:ss")+'</td><td>'+$.date.timeToStr(list[i].leaveTime, "yyyy-MM-dd HH:mm:ss")+'</td></tr>';
				$("#table tbody").append(a);
			}
		}
	}
	function initMonitoringGuardTable(){
		$("#table thead").append('<tr><td>地点</td><td>时间</td></tr>');
		console.log(list);
		if( !list || list.length <= 0 ){
			var a='<tr><td colspan="2" align="center">'+'无数据'+'</td></tr>';
			$("#table tbody").append(a);
		}else{
			for(var i in list){
				var a='<tr><td>'+list[i].place +'</td><td>'+list[i].monitoredTime.replace("T"," ")+'</td></tr>';
				$("#table tbody").append(a);
			}
		}
	}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
	});	
})(jQuery);