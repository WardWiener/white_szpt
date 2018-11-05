(function($){
	"use strict";
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var id = initData.id;
	var currentUserName = initData.currentUserName;
	var isDetail = initData.isDetail;
	var table;
	var table2;
	var isQBHSZL=false;
	$(document).ready(function() {	
		if(isDetail){
			$("#feedbackDiv").hide();
		}else{
			$("#feedbackPeopleName").val(currentUserName);
			var time = new Date().getTime();
			$("#feedbackTime").val($.date.timeToStr(time,"yyyy-MM-dd HH:mm"));
		}
		if(!$.util.isBlank(id)){
			initPageField();
			initTable();
			initTable2();
		}
	});
	/**
	 * 初始化页面字段信息
	 */
	function initPageField(){
		if(!$.util.isBlank(id)){//修改
			$.ajax({
				url:context +'/instructionReceive/findInstructionReceiveSubject.action',
				type:'post',
				dataType:'json',
				data:{id : id},
				async:false,
				success:function(successData){
					var receiveSubjectBean = successData.receiveSubjectBean;
					if(receiveSubjectBean!=null){
						$("#content").text(receiveSubjectBean.instructionBean.content);
						$("#relateObjectContent").html(receiveSubjectBean.instructionBean.relateObjectContent);
						$("#type").text(receiveSubjectBean.instructionBean.typeName);
						$("#requireFeedbackTime").text($.date.timeToStr(receiveSubjectBean.instructionBean.requireFeedbackTime, "yyyy-MM-dd HH:mm"));
						$("#receiveTime").text($.date.timeToStr(receiveSubjectBean.receiveTime, "yyyy-MM-dd HH:mm"));
						if(receiveSubjectBean.isOverTime==1){
							$("#requireFeedbackTime").addClass("color-red1");
						}
						if(receiveSubjectBean.instructionBean.type==$.common.Constant.ZLLX_QBHSZL()){
							$("#checkDiv").show();
							isQBHSZL = true;
						}
						$.common.typeContentCommon.setTypeContentText(receiveSubjectBean.instructionBean);
					}
				},
				error:function(errorData){
					
				}
			});
		}
	}
	function initTable(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/instructionReceive/findFeedbackBeanList.action";
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"width": "15%",
         	    	"title": "反馈时间",
         	    	"className":"table-checkbox",
         	    	"data": "feedbackTime" ,
         	    	"render": function ( data, type, full, meta ) {
         	    		return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
         	    	}
				},
				{
					"targets" : 1,
					"width" : "12%",
					"title" : "反馈人",
					"data" : "feedbackPeopleName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "20%",
					"title" : "反馈内容",
					"data" : "feedbackContent",
					"render" : function(data, type, full, meta) {
						var a=data;
						if(!$.util.isBlank(full.otherFeedbackContent)){
							a+='('+full.otherFeedbackContent+')';
						}
						return a;
					}
				}
			];
			//是否排序
			tb.ordering = false ;
			//每页条数
			tb.paging = false;
			//默认搜索框
			tb.searching = false ;
			//能否改变lengthMenu
			tb.lengthChange = false ;
			//自动TFoot
			tb.autoFooter = false ;
			//自动列宽
			tb.autoWidth = false ;
			tb.info = false ;
			//请求参数
			tb.paramsReq = function(d, pagerReq){
				d["id"] = id;
			};
			tb.paramsResp = function(json) {
				json.data = json.feedbackBeanList;
				json.recordsTotal = json.feedbackBeanList.length;
				json.recordsFiltered = json.feedbackBeanList.length;
			};
			tb.rowCallback = function(row,data, index) {
				
			};
			table = $("#table").DataTable(tb);
	}
	function initTable2(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/instructionReceive/findOperationRecordBeanListByReceiveSubjectId.action";
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"width": "20%",
         	    	"title": "操作时间",
         	    	"className":"table-checkbox",
         	    	"data": "operateTime" ,
         	    	"render": function ( data, type, full, meta ) {
         	    		return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
         	    	}
				},
				{
					"targets" : 1,
					"width" : "12%",
					"title" : "操作内容",
					"data" : "content",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "12%",
					"title" : "操作单位",
					"data" : "operateUnitName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "12%",
					"title" : "操作人",
					"data" : "operatorName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				}
			];
			//是否排序
			tb.ordering = false ;
			//每页条数
			tb.paging = false;
			//默认搜索框
			tb.searching = false ;
			//能否改变lengthMenu
			tb.lengthChange = false ;
			//自动TFoot
			tb.autoFooter = false ;
			//自动列宽
			tb.autoWidth = false ;
			tb.info = false ;
			//请求参数
			tb.paramsReq = function(d, pagerReq){
				d["id"] = id;
			};
			tb.paramsResp = function(json) {
				json.data = json.operationRecordBeanList;
				json.recordsTotal = json.operationRecordBeanList.length;
				json.recordsFiltered = json.operationRecordBeanList.length;
			};
			tb.rowCallback = function(row,data, index) {
				
			};
			table = $("#table2").DataTable(tb);
	}
	function save(){
		var demo = $.validform.getValidFormObjById("validform") ;
		var flag = $.validform.check(demo) ;
		if(!flag){
			return ;
		}
		var otherFeedbackContent = null;
		if(isQBHSZL){
			otherFeedbackContent = $.icheck.val("radio")==1?"情报线索属实：是":"情报线索属实：否";
		}
		$.ajax({
			url:context +'/instructionReceive/feedbackInstruction.action',
			type:'post',
			dataType:'json',
			data:{
				id:id,
				feedbackContent:$("#feedbackContent").val(),
				otherFeedbackContent:otherFeedbackContent
			},
			success:function(successData){
				window.top.$.layerAlert.alert({msg:"反馈成功!",
					title:"提示",
					icon:1,
					yes:function(index, layero){
						$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex);  
					}
				});
			},
			error:function(errorData){
				
			}
		});
	}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
		save : save
	});	
})(jQuery);