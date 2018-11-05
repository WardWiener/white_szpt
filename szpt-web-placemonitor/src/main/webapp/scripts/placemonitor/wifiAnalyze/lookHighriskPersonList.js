$.lookHighriskPersonList = $.lookHighriskPersonList || {};

(function($){

	"use strict";
	
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var macList = initData.macList;
	
	var highriskPersonTable = null;
	
	$(document).ready(function() {
		if($.util.exist(macList)){
			var array = new Array();
			$.each(macList,function(i,val){
				array.push(val);
			});
			var obj = new Object();
			$.util.objToStrutsFormData(array,"macList",obj);
			$.ajax({
				url:context +'/wifiAnalyze/findHighriskPersonByMacList.action',
				data:obj,
				type:"post",
				dataType:"json",
			    customizedOpt:{
				    ajaxLoading:true,//设置是否loading
				},
				success:function(successData){
					var hpList = successData.resultMap.result;
					initHighriskPersonTable(hpList);
				}
			});
		}else{
			initHighriskPersonTable([]);
		}
	});
	
	
	/**
	 * 初始化高危人列表
	 */
	function initHighriskPersonTable(dataArray){
		if($.util.exist(highriskPersonTable)){
			highriskPersonTable.destroy();
			$("#highriskPersonTable").empty();
		}
		var st1 = $.uiSettings.getLocalOTableSettings();
		st1.data = dataArray;
		st1.columnDefs = [
			{
				"targets" : 0,
				"width" : "",
				"title" : "序号",
				"data" : "",
				"render" : function(data, type, full, meta) {
						
					return meta.row + 1;
				}
			}, 
			{
				"targets" : 1,
				"width" : "",
				"title" : "姓名",
				"data" : "name",
				"render" : function(data, type, full, meta) {
						
					return data;
				}
			}, 
			{
				"targets" : 2,
				"width" : "",
				"title" : "身份证号",
				"data" : "idcode",
				"render" : function(data, type, full, meta) {
						
					return data;
				}
			}, 
			{
				"targets" : 3,
				"width" : "",
				"title" : "预警类型",
				"data" : "warnTypeName",
				"render" : function(data, type, full, meta) {
						
					return data;
				}
			}, 
			{
				"targets" : 4,
				"width" : "",
				"title" : "人员类型",
				"data" : "peopleTypeName",
				"render" : function(data, type, full, meta) {
						
					return data;
				}
			}, 
			{
				"targets" : 5,
				"width" : "",
				"title" : "积分",
				"data" : "accumulatePoints",
				"render" : function(data, type, full, meta) {
						
					return data;
				}
			}
			];
			st1.ordering = false;
			st1.paging = true; // 是否分页
			st1.info = true; // 是否显示表格左下角分页信息
			st1.autoFoot = false;
			st1.dom = null;
			st1.searching = false;
			st1.lengthChange = false;
			st1.lengthMenu = [ 10 ];
			st1.rowCallback = function(row,data, index) {
						
			};		
			highriskPersonTable = $("#highriskPersonTable").DataTable(st1);
	}
	
	/**
	 * 暴露本js方法，让其它js可调用
	 */
	jQuery.extend($.lookHighriskPersonList, { 
		
	});	
})(jQuery);