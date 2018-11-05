$.involvedPerson = $.involvedPerson || {};
(function($){
	"use strict";
	var i=1;
	var table;
	$(document).ready(function(){		
		//initInvolvedPersonTable();
		
	})	
		 
		 /**
		 * 修改涉案人员信息--弹窗按钮
		 */
		$(document).on('click',"#involvedPersonWHBtn",function(){
			showUpdataInvolvedPerson();
		});
	
	
	/**
	 * 我的专案--人员修改页面弹窗
	 */
	function showUpdataInvolvedPerson(){
		$.util.topWindow().$.layerAlert.dialog({
			content : context +  '/wdza/showUpdataInvolvedPerson.action',
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
				table.draw(true);
			}
		});
		//$.util.topWindow().location.href = context +  '/wdza/showUpdataInvolvedPerson.action?queryStr='+caseId;
	}
	
	
	/**
	 * 加载表格--涉案人员
	 */	
	function initInvolvedPersonTable(){
		if(table!=null){
			table.draw(true);
		}else{
			var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context + "/wdza/findInvolvedPersonTable.action";
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"title": "序号",
         	    	"width" : "8%",
         	    	"className":"table-checkbox",
         	    	"data": "id" ,
         	    	"render": function ( data, type, full, meta ) {
         	    			  return    (meta.row+1);
         	    	}
				},
				{
					"targets" :1,
					"width" : "15%",
					"title" : "姓名",
					"data" : "name",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 2,
					"width" : "10%",
					"title" : "绰号",
					"data" : "nick",
					"render" : function(data, type, full, meta) {
						return data;
					}
				},
				{
					"targets" : 3,
					"width" : "27%",
					"title" : "身份证号、手机号",
					"data" : "phone",
					"render" : function(data, type, full, meta) {
						var  str='<span class="color-gray">身份证:</span><p>'+full.idcard+'</p><span class="color-gray">手机号:</span><p>'+data+'<br></p></td>';
						return str;
					}
				},
				{
					"targets" : 4,
					"width" : "27%",
					"title" : "籍贯 、户籍",
					"data" : "householdRegister",
					"render" : function(data, type, full, meta) {
						var str='<span class="color-gray">籍贯:</span><p>'+data+'</p><span class="color-gray">户籍:</span><p>'+full.householdAddress+'</p>';
						return str;
					}
				},
				{
					"targets" : 5,
					"width" : "27%",
					"title" : "录入人员及时间",
					"data" : "createPerson",
					"render" : function(data, type, full, meta) {
						var str=data.name+'<br>'+$.date.timeToStr(full.createdTime, "yyyy-MM-dd HH:mm:ss");
						return str;
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
			tb.paging=true;
			
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
				json.recordsTotal = json.resultMap.totalNum;
				json.recordsFiltered = json.resultMap.totalNum;
				json.data = json.resultMap.pageList;
			};
			tb.rowCallback = function(row,data, index) {
				
			};
			table = $("#involvedPersonTable").DataTable(tb);
		}
		
	  }
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.involvedPerson, { 
		initInvolvedPersonTable : initInvolvedPersonTable
	});	
	
})(jQuery);