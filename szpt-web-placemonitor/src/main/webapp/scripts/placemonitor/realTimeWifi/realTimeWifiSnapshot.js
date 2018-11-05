$.realTimeWifiSnapshot = $.realTimeWifiSnapshot || {};

(function($){

	"use strict";
	
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var mac = initData.mac;
	var wifiSnapshootObject = initData.wifiSnapshootObject;
	
	
	$(document).ready(function() {	
		initPageDate();
	});
	
	/**
	 * 初始化页面数据
	 * @returns
	 */
	function initPageDate(){
		var data = {
			"type" : $.common.DICT.DICT_ZLLX_YPZL //指令类型
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/realTimeWifi/findInstructionByUnitId.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    	customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var instructions = successData.resultMap.result;
				$.select2.addByList("#instruct", instructions,"id","content",true,true);
			}
		});
	}
	
	/***
	 * 保存快照
	 * @returns
	 */
	function saveSnapshot(){
		var demo = $.validform.getValidFormObjById("validform") ;
		var flag = $.validform.check(demo) ;
		if(!flag){
			return ;
		}
		var data = {
			"wifiSnapshootObject" : wifiSnapshootObject ,
			"snapshotInfo" : $("#snapshotInfo").val(),
			"mac" : mac ,
			"id" : $.util.isBlank($.select2.val("#instruct")) ? "" : $.select2.val("#instruct") // 指令接收主体id
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/realTimeWifi/generateRealTimeSnapshot.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    	customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				if(successData.resultMap.flag){
					$.util.topWindow().$.layerAlert.alert({icon:6, closeBtn:false, msg:"保存成功。", yes:function(){
						$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); //关闭弹窗
					}});
				}else{
					$.util.topWindow().$.layerAlert.alert({icon:5, closeBtn:false, msg:successData.resultMap.msg, yes:function(){
						$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); //关闭弹窗
					}});
				}
			}
		});
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.realTimeWifiSnapshot, { 
		saveSnapshot : saveSnapshot
	});	
})(jQuery);