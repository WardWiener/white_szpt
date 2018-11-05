$.showBbjlDetail = $.showBbjlDetail || {};
(function($){
	
	"use strict"
	
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var dataStr = initData.bbjlData;
	
	var bbjlDetailTable;
	$(document).ready(function(){
		$.ajax({
			url: context + '/fenbu/findBbjlDetail.action',
			data:dataStr,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(data){
				initBbjlDetailTable(data.resultMap.list);
			}
		});
	});
	
	//报备警力
	function initBbjlDetailTable(formData) {
		if($.util.exist(bbjlDetailTable)){
			bbjlDetailTable.destroy();
			$("#showBbjlDetail").empty();
		}
		var st1 = $.uiSettings.getLocalOTableSettings();
		st1.data = formData;
		st1.columnDefs = [{
			"targets" : 0,
			"width" : "",
			"title" : "警号",
			"data" : "personJh",
			"render" : function(data, type, full, meta) {
				
				return data;
			}
		}, {
			"targets" : 1,
			"width" : "",
			"title" : "姓名",
			"data" : "personName",
			"render" : function(data, type, full, meta) {
				return data;
			}
		}, {
			"targets" : 2,
			"width" : "",
			"title" : "单位名称",
			"data" : "orderCellName",
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
		
		bbjlDetailTable = $("#showBbjlDetail").DataTable(st1);

	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.showBbjlDetail, { 
		
	});	
})(jQuery);