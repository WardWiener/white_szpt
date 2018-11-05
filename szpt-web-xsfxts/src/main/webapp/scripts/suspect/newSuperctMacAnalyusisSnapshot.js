$.newSuperctMacAnalyusisSnapshot = $.newSuperctMacAnalyusisSnapshot || {};

(function($){

	"use strict";
	
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var caseSnapshootObject = initData.caseSnapshootObject;
	var mainCaseCode = initData.mainCaseCode;
	
	$(document).ready(function() {	
		initPageDate();
	});
	
	/**
	 * 初始化页面数据
	 * @returns
	 */
	function initPageDate(){
		//查询当前案件发送的指令类型是研判指令的指令列表
		var data = {
			"relatedObjectId" : mainCaseCode ,
			"type" : $.common.DICT.DICT_ZLLX_YPZL
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/suspectMacAnalysisSnapshot/findInstructionListByRelatedObjectIdAndType.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    	customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				var instructions = successData.resultMap.instructions;
				$.select2.addByList("#instruct", instructions,"id","content",true,true);
			}
		});
	}
	
	/**
	 * 保存快照
	 */
	function saveSnapshot(){
		var demo = $.validform.getValidFormObjById("validform") ;
		var flag = $.validform.check(demo) ;
		if(!flag){
			return ;
		}
		
		var data = {
			"caseSnapshootObject" : caseSnapshootObject ,
			"mainCaseCode" : mainCaseCode ,
			"snapshotInfo" : $("#snapshotInfo").val()
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/suspectMacAnalysisSnapshot/createSnapshot.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    	customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				if(successData.resultMap.flag){
//					var instructionId = $.select2.val("#instruct");
//					instructionFeedback(instructionId);
					$.util.topWindow().$.layerAlert.alert({icon:6, closeBtn:false, msg:"生成分析快照成功。", yes:function(){
						$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); //关闭弹窗
					}});
				}
			}
		});
	}
	
	/**
	 * 指令反馈
	 * 
	 * @returns
	 */
	function instructionFeedback(receiveSubjectId){
		if($.util.isBlank(receiveSubjectId)){
			return ;
		}
		var data = {
			"receiveSubjectId" : receiveSubjectId ,
			"snapshotName" :  "基于串并案分析嫌疑人MAC地址" ,
			"mainCaseCode" : mainCaseCode
		};
		var dataStr = $.util.toJSONString(data) ;
		$.ajax({
			url:context +'/suspectMacAnalysisSnapshot/feedbackInstruction.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
		    	customizedOpt:{
			    ajaxLoading:true,//设置是否loading
			},
			success:function(successData){
				
			}
		});
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.newSuperctMacAnalyusisSnapshot, { 
		saveSnapshot : saveSnapshot
	});	
})(jQuery);




