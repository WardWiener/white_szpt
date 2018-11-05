$.basicMessage = $.basicMessage || {};
(function($){
	"use strict";
	var table; 
//	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window) ;
//	var initData = frameData.initData ;
//	var caseId = initData.lockerId;
	$(document).ready(function(){
		
//		$.each( $(".subViewDiv"), function(e,m){
//			$(m).hide();
//		} ); 
//		$("#basicDiv").show();
//		  onloadBasicData();
	
		 /**
		 * 维护专案信息修改页面
		 */
		$(document).on('click',"#updataBasicMessageBtn",function(){
			showUpdatabasicMessage();
		});
	  
	  
	})
		  
	/**
	 * 维护专案基本信息页面--修改页面弹窗
	 */
	function showUpdatabasicMessage(){
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/wdza/showUpdataBasicProject.action',
			pageLoading : true,
			title:"我的专案",
			width : "60%",
			height : "80%",
			shadeClose : false,
			success:function(layero, index){
				
			},
			initData:{
				lockerId : $.zaxq.getCaseId()  //专案id
				
			},
			end:function(){
				onloadBasicData();
			}
		});
		//$.util.topWindow().location.href = context +  '/wdza/showUpdataInvolvedPerson.action?queryStr='+caseId;
	}
	
	/**
	 * 维护案件基本信息--加载项
	 */
	function onloadBasicData(){
		var data={
				caseId:$.zaxq.getCaseId()
             }
	var queryStr = $.util.toJSONString(data);
		
		$.ajax({
			url:context +'/wdza/findMyUpdataProject.action',
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			data:queryStr,
			success:function(successData){
				var specialCase=successData.resultMap.specialCase;
				$('#basicName').text(specialCase.name); //专案名称
				$('#basicCode').text(specialCase.code);  //专案编号
				$('#basicNature').text(specialCase.nature); //专案性质
				$('#basicJYAQ').text(specialCase.content); //简要案情
				if(specialCase.progress==null){
					$('#basicJZ').text("");
				}else{
					$('#basicJZ').text(specialCase.progress);  //工作进展
				}
				if(specialCase.problem==null){
					$('#basicWT').text("");
				}else{
					$('#basicWT').text(specialCase.problem);  //工作进展
				}
				if(specialCase.plan==null){
					$('#basicJH').text("");
				}else{
					$('#basicJH').text(specialCase.plan);  //工作进展
				}
				var data=specialCase.sonList;
				if(table!=null){
					table.draw(true);
				}else{
					initTable();
				}
			}
			
		
		});
	}
	
	/**
	 * 加载表格
	 */	
	function initTable(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/wdza/findBasicSonProjectTable.action";
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"title": "子案件编号",
         	    	"className":"table-checkbox",
         	    	"data": "caseCode" ,
         	    	"render": function ( data, type, full, meta ) {
         	    			  return  '<a class="sonCaseCode">'+data+'</a>';
         	    	}
				},{
					"targets": 1,
         	    	"title": "子案件名称",
         	    	"className":"table-checkbox",
         	    	"data": "caseName" ,
         	    	"render": function ( data, type, full, meta ) {
       	    			return data;
         	    	}
				},{
					"targets": 2,
         	    	"title": "子案件办案民警",
         	    	"className":"table-checkbox",
         	    	"data": "workers" ,
         	    	"render": function ( data, type, full, meta ) {
         	    			  return    data;
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
			//是否分页
			tb.paging=false;
			
			//请求参数
			tb.paramsReq = function(d, pagerReq){					
					var data = {
						"caseId":$.zaxq.getCaseId(),
						"start":d.start,
						"length":d.length
					};
					var queryStr = $.util.toJSONString(data);
					$.util.objToStrutsFormData(queryStr,"queryStr",d);
			};
			tb.paramsResp = function(json) {
				json.recordsTotal = json.resultMap.sonCase.length;
				json.recordsFiltered = json.resultMap.sonCase.length;
				json.data = json.resultMap.sonCase;
			};
			tb.rowCallback = function(row,data, index) {
				
			};
			table = $("#basicSonProjectTable").DataTable(tb);
	}
	
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.basicMessage, { 
		onloadBasicData : onloadBasicData
	});	
	
})(jQuery);