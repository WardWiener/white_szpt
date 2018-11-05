(function($){
	"use strict";
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var idcode = initData.idcode;
	var personBean=null;
	var table;
	$(document).ready(function() {	
		initPageData();
		initTable();
	});
	function initPageData(){
		if(idcode==null){
			return;
		}
		$.ajax({
			url:context +'/personDetail/findPersonTypeAndMobile.action',
			type:'post',
			dataType:'json',
			data:{code:idcode},
			async:false,
			success:function(successData){
				personBean = successData.personBean;
				console.log(personBean);
				//设置人员预警
				var warnType = personBean.warnType;
				$(".warning-type").find("span").hide();
				if(warnType == $.common.DICT.DICT_YJLX_RED){//红色
					$(".sq-red").show();
				}else if(warnType == $.common.DICT.DICT_YJLX_ORANGE){//橙色
					$(".sq-orange").show();
				}else if(warnType == $.common.DICT.DICT_YJLX_YELLOW){//黄色
					$(".sq-yellow").show();
				}else if(warnType == $.common.DICT.DICT_YJLX_WHITE){//白色
					$(".sq-white").show();
				}else if(warnType == $.common.DICT.DICT_YJLX_BLUE){//蓝色
					$(".sq-blue").show();
				}else if(warnType == $.common.DICT.DICT_YJLX_OTHER){//其它
					$(".sq-other").show();
				}
				
				$.validform.setFormTexts("#validform",personBean);
				if(personBean.birthday!=null){
					$("#birthday").text($.date.timeToStr(personBean.birthday, "yyyy-MM-dd"));
				}
				var phones = successData.mobilePhoneInfoBeanList;
				for(var i in phones){
					var a='<div class="row row-mar">';
					a+='<div class="col-xs-2"> <label class="control-label">手机号'+i+1+'：</label></div>';
					a+='<div class="col-xs-3  m-label-right"><p class="mar-right">'+phones[i].number+'</p></div>';
					a+='<div class="col-xs-3  m-label-right"><p class="mar-right">'+phones[i].imei+'</p></div>';
					a+='<div class="col-xs-3  m-label-right">'+phones[i].mac+'</div></div>';
					$("#phoneDiv").append(a);
				}
				var files = successData.fileBeanList;
				for(var i in files){
					$("#picDiv").append('<div class="col-xs-3 mar-right"><p style="height:100px; overflow:hidden">'+
							'<img id="'+files[i].id+'" class="picDownload" width="100%" src="'+context+'/personDetail/downloadFile.action?attachmentId='+files[i].id+'"></div>');
				}
			},
			error:function(errorData){
				
			}
		});
	}
	
	
	
	function initTable(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/highriskPerson/findOperationRecord.action";
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
				d["idcode"] = idcode;
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
			url:context +'/highriskPerson/approvePerson.action',
			type:'post',
			dataType:'json',
			data:{
				idcode : idcode,
				approveContent:approveContent,
				isPass:isPass
				},
			success:function(successData){
				if(isPass){
					$.util.topWindow().$.layerAlert.alert({msg:"审批通过!",title:"提示",icon:1});
				}else{
					$.util.topWindow().$.layerAlert.alert({msg:"审批驳回成功!",title:"提示",icon:1});
				}
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