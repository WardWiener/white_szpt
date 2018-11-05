(function($){
	"use strict";
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var id = initData.id;
	var isApprove = initData.isApprove;
	var table;
	$(document).ready(function() {	
		if(!$.util.isBlank(id)){
			initPageField();
			initTable();
		}
		if(isApprove){
			$(".approveDiv").show();
			$(".detailDiv").hide();
		}
	});
	/**
	 * 初始化页面字段信息
	 */
	function initPageField(){
		if(!$.util.isBlank(id)){//修改
			$.ajax({
				url:context +'/personExecuteControl/findPersonExecuteControlById.action',
				type:'post',
				dataType:'json',
				data:{id : id},
				async:false,
				success:function(successData){
					var personExecuteControlBean = successData.personExecuteControlBean;
					if(personExecuteControlBean!=null){
						$.validform.setFormVals("#validform",personExecuteControlBean);
						var startTime=$.date.timeToStr(personExecuteControlBean.startTime,"yyyy-MM-dd HH:mm");
						var endTime=$.date.timeToStr(personExecuteControlBean.endTime,"yyyy-MM-dd HH:mm");
						$.laydate.setRange(startTime,endTime,"#dateRange","info@yyyy-MM-dd HH:mm"); 
						var phones = successData.personMobileInfoBeanList;
						setPhone(phones);
						var files = successData.fileBeanList;
						setPic(files);
						$("#lastModifyPerson").text(personExecuteControlBean.lastModifyPerson);
						$("#lastModifyTime").text($.date.timeToStr(personExecuteControlBean.lastModifyTime));
						$("input").attr("readonly","readonly");
						$("#searchIdcardNo").hide();
						$("#dateRange").addClass("date-disabled");
						$("select").attr("disabled","disabled");
						$("textarea").attr("disabled","disabled");
						$("#approveContent").removeAttr("disabled")
					}
				},
				error:function(errorData){
					
				}
			});
		}
	}
	function setPhone(phones){
		var count=0;
		$(".phoneDiv").remove();
		$("#phoneDiv").html('<div class="col-xs-2"> <label class="control-label">手机：</label></div>');
		for(var i in phones){
			var phone;
			if(phones[i].number==null){
				phone="";
			}else{
				phone=phones[i].number;
			}
			var a='<div class="col-xs-2"><input type="text" datatype="/(^\\d{11}$)|(^$)/" class="form-control input-sm" value="'+phone+'"></div>'+
				'<div class="col-xs-3 mar-left"><input type="text" class="form-control input-sm " value="MAC:'+phones[i].mac+'"></div>';
			if(count==0){
				$("#phoneDiv").append(a);
				count++;
			}else{
				a='<div class="col-xs-2 phoneDiv"></div>'+a
				$("#phoneDiv").after(a);
			}
		}
	}
	function setPic(files){
		$("#picDiv").html('<div class="col-xs-2"> <label class="control-label">照片：</label></div>');
		for(var i in files){
			$("#picDiv").append('<div class="col-xs-2 mar-right"><p style="height:130px; overflow:hidden"><img src="'+context+'/personDetail/downloadFile.action?attachmentId='+files[i].id+'" width="100%"></p></div>');
		}
	}
	function initTable(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/personExecuteControl/findOperationRecord.action";
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"width": "12%",
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
					"data" : "result",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "15%",
					"title" : "审批意见",
					"data" : "content",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "12%",
					"title" : "操作单位",
					"data" : "operateUnitName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 4,
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
			table = $("#table").DataTable(tb);
	}
	function pass(){
		approve(true);
	}
	function noPass(){
		approve(false);
	}
	function approve(isPass){
		var demo = $.validform.getValidFormObjById("validform") ;
		var flag = $.validform.check(demo) ;
		if(!flag){
			return ;
		}
		var approveContent = $("#approveContent").val();
		$.ajax({
			url:context +'/personExecuteControl/approvePersonExecuteControl.action',
			type:'post',
			dataType:'json',
			data:{
				id : id,
				approveContent:approveContent,
				isPass:isPass
				},
			success:function(successData){
				window.top.$.common.refresh();
				window.top.$.layerAlert.closeWithLoading(pageIndex); 
			},
			error:function(errorData){
				
			}
		});
	}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
		pass:pass,
		noPass:noPass
	});	
})(jQuery);