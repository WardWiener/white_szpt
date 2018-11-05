$.showJqslDetail = $.showJqslDetail || {};
(function($){
	
	"use strict"
	
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var dataStr = initData.dataBase;
	
	var jqslDetailTable;
	$(document).ready(function(){
		$.ajax({
			url: context + '/fenbu/findJqslDetail.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(data){
				initjqslDetailTable(data.resultMap.list);
			}
		});
	});
	
	//警情数量
	function initjqslDetailTable(formData) {
		if($.util.exist(jqslDetailTable)){
			jqslDetailTable.destroy();
			$("#showJqslDetail").empty();
		}
		var st1 = $.uiSettings.getLocalOTableSettings();
		st1.data = formData;
		st1.columnDefs = [{
			"targets" : 0,
			"width" : "",
			"title" : "名称",
			"data" : "name",
			"render" : function(data, type, full, meta) {
				
				return data;
			}
		}, {
			"targets" : 1,
			"width" : "",
			"title" : "类型名称",
			"data" : "jqlxName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}, {
			"targets" : 2,
			"width" : "",
			"title" : "来源",
			"data" : "jqSource",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}, {
			"targets" : 3,
			"width" : "",
			"title" : "概要",
			"data" : "jqSummary",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 4,
			"width" : "",
			"title" : "发生时间",
			"data" : "occurrenceTime",
			"render" : function(data, type, full, meta) {
				var td = $.date.timeToStr(data, "yyyy-MM-dd HH:mm");
				return td;
			}
		},
		{
			"targets" : 5,
			"width" : "",
			"title" : "地址",
			"data" : "addr",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 6,
			"width" : "",
			"title" : "紧急程度",
			"data" : "urgencyLevel",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 7,
			"width" : "",
			"title" : "派出所",
			"data" : "pcsName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		},
		{
			"targets" : 8,
			"width" : "",
			"title" : "村居",
			"data" : "countryName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}];
		
		st1.ordering = false;
		st1.paging = false; // 是否分页
        st1.info = false; // 是否显示表格左下角分页信息
		st1.autoFoot = false;
		st1.dom = null;
		st1.searching = false;
		st1.lengthChange = false;
		st1.lengthMenu = [ formData.length ];
		
		jqslDetailTable = $("#showJqslDetail").DataTable(st1);

	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.showJqslDetail, { 
		
	});	
})(jQuery);