$.newTrackAnalyzeSnapshot = $.newTrackAnalyzeSnapshot || {} ;
(function($){
	"use strict";
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	
	var idCode = initData.idCode ;
	var snapshotObject = initData.snapshotObject ;
	
	$(document).ready(function(){
		initPage();
	})
	
	/**
	 * 初始化页面数据
	 * 
	 * @returns
	 */
	function initPage(){
		var data = {
			"type" : $.common.DICT.DICT_ZLLX_YPZL //指令类型
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/trackAnalyze/findInstructionByUnitId.action',
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
			"snapshotObject" : snapshotObject ,
			"snapshotInfo" : $("#snapshotInfo").val(),
			"idCode" : idCode ,
			"id" : $.util.isBlank($.select2.val("#instruct")) ? "" : $.select2.val("#instruct") // 指令接收主体id
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/trackAnalyze/generateSnapshot.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    	customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				if(successData.resultMap.flag){
					$.util.topWindow().$.layerAlert.alert({icon:6, closeBtn:false, msg:"生成快照成功。", yes:function(){
						$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); //关闭弹窗
					}});
				}else{
					$.util.topWindow().$.layerAlert.alert({icon:5, closeBtn:false, msg:"生成快照失败。", yes:function(){
						$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); //关闭弹窗
					}});
				}
			}
		});
	}
	jQuery.extend($.newTrackAnalyzeSnapshot, { 
		saveSnapshot : saveSnapshot
	});	
})(jQuery);