(function($){
	"use strict";
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var id = initData.id;
	var table;
	var table2;
	$(document).ready(function() {	
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
				url:context +'/instructionManage/findInstruction.action',
				type:'post',
				dataType:'json',
				data:{id : id},
				async:false,
				success:function(successData){
					var instructionBean = successData.instructionBean;
					if(instructionBean!=null){
						$("#content").text(instructionBean.content);
						$("#relateObjectContent").html(instructionBean.relateObjectContent);
						$("#type").text(instructionBean.typeName);
						$("#createTime").text($.date.timeToStr(instructionBean.createTime, "yyyy-MM-dd HH:mm"));
					}
					$.common.typeContentCommon.setTypeContentText(instructionBean);
				},
				error:function(errorData){
					
				}
			});
		}
		$(document).on("click",".personDetail",function(){
			var personId = $(this).attr("personId");
			$.util.topWindow().$.common.toPersonDetail(personId);
			$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); 
		});
	}
	function initTable(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/instructionManage/findReceiveSubjectBeanList.action";
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"width": "6%",
         	    	"title": "序号",
         	    	"className":"table-checkbox",
         	    	"data": "" ,
         	    	"render": function ( data, type, full, meta ) {
         	    		var a=meta.row+1;
         	    		if(full.isOverTime==1&&full.feedbackBeanList.length==0){
         	    			a+='<span class="glyphicon glyphicon-exclamation-sign font16 color-red2 pull-right"></span>';
         	    		}	
         	    		return a;
         	    	}
				},
				{
					"targets" : 1,
					"width" : "12%",
					"title" : "接收单位",
					"data" : "receiveSubjectName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "15%",
					"title" : "实际签收时间",
					"data" : "signTime",
					"render" : function(data, type, full, meta) {
						return data==null?"":$.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				},
				{
					"targets" : 3,
					"width" : "15%",
					"title" : "要求反馈时间",
					"data" : "instructionBean.requireFeedbackTime",
					"render" : function(data, type, full, meta) {
						return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				},
				{
					"targets" : 4,
					"width" : "15%",
					"title" : "实际反馈时间",
					"data" : "feedbackTime",
					"render" : function(data, type, full, meta) {
						return data==null?"":$.date.timeToStr(data, "yyyy-MM-dd HH:mm");
					}
				},
				{
					"targets" : 5,
					"width" : "20%",
					"title" : "反馈内容",
					"data" : "feedbackBeanList",
					"render" : function(data, type, full, meta) {
//						var a='';
//						a+='<div class="fi-ceng-out"><span class="mar-right">';
//						if(data.length==0){
//							return "";
//						}else if(data.length==1){
//							if(data[0].feedbackContent.length<=20){
//								return data[0].feedbackContent;
//							}else{
//								a+=data[0].feedbackContent.substring(0,20)+'...';
//								a+='</span><div class="fi-ceng row-ceng">';
//								a+='<p>'+data[0].feedbackContent+'</p>';
//								a+='</div></div>';
//								return a;
//							}
//						}else{
//							a+=data[0].feedbackContent.substring(0,20)+'...';
//							a+='</span><div class="fi-ceng row-ceng">';
//							$.each(data, function(i,item){
//								a+='<p>'+$.date.timeToStr(item.feedbackTime, "yyyy-MM-dd HH:mm")+':'+item.feedbackContent+'</p>';
//							 }); 
//							a+='</div></div>';
//							return a;
//						}
						var a='';
						var result='';
						if(data.length==0){
							return "";
						}else if(data.length==1){
							if(data[0].feedbackContent.length<=20){
								result = data[0].feedbackContent==null?"":data[0].feedbackContent;
							}else{
								a+='<span title="'+data[0].feedbackContent+'">';
								a+=data[0].feedbackContent.substring(0,20)+'...';
								a+='</span>';
								result = a;
							}
						}else{
							var b='';
							$.each(data, function(i,item){
								b+=$.date.timeToStr(item.feedbackTime, "yyyy-MM-dd HH:mm")+':'+item.feedbackContent+'</br>';
							 }); 
							a+='<span title="'+b+'">';
							a+=data[0].feedbackContent.substring(0,20)+'...';
							a+='</span>';
							result = a;
						}
						if(!$.util.isBlank(data[0].relateObjectId)&&!$.util.isBlank(data[0].relateObjectType)){
							if(data[0].relateObjectType==$.common.Constant.CZDXLX_GWRY()){
//								result='<a href="'+context +'/personDetail/showPersonDetailPage.action?clickOrder=2&&clickListOrder=0&&idcode=' + full.relateObjectId+'>'+result+'</a>';
								result='<p class="personDetail" personId="'+data[0].relateObjectId+'">'+result+'</p>';
							}
						}
						return result;
					}
				},
				{
					"targets" : 6,
					"width" : "10%",
					"title" : "签收人",
					"data" : "signPeopleName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 7,
					"width" : "10%",
					"title" : "状态",
					"data" : "statusName",
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
				json.data = json.receiveSubjectBeanList;
				json.recordsTotal = json.receiveSubjectBeanList.length;
				json.recordsFiltered = json.receiveSubjectBeanList.length;
			};
			tb.rowCallback = function(row,data, index) {
				
			};
			tb.fnDrawCallback = function(json){
				$(".personDetail").css("text-decoration","underline");
				$(".personDetail").css("color","blue");				
	 		};
			table = $("#table").DataTable(tb);
	}
	function initTable2(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/instructionManage/findOperationRecordBeanListByInstructionId.action";
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"width": "17%",
         	    	"title": "操作时间",
         	    	"className":"table-checkbox",
         	    	"data": "operateTime" ,
         	    	"render": function ( data, type, full, meta ) {
         	    		return $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
         	    	}
				},
				{
					"targets" : 1,
					"width" : "11%",
					"title" : "操作内容",
					"data" : "content",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "11%",
					"title" : "操作单位",
					"data" : "operateUnitName",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "11%",
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
			table2 = $("#table2").DataTable(tb);
	}
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.common, { 
	});	
})(jQuery);