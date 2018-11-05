var flag = true;
var dayErrorInfo = "";
var weekErrorInfo  = "";
var monthErrorInfo  = "";
var yearErrorInfo  = "";

$.ptjc = $.ptjc || {} ;
$.ptjc.penalConstant = $.ptjc.penalConstant || {} ;
(function($){
	var data_map;
	"use strict";
	
	var data;
	
	$(document).ready(function() {
		
		init();
		
		$(document).on("click", "#saveBtn", function(){
			window.top.$.layerAlert.confirm({
				msg:"确认保存？",
				title:"提示",	  //弹出框标题
				width:'300px',
				hight:'200px',
				shade: [0.5,'black'],  //遮罩
				icon:0,  //弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
				shift:1,  //弹出时的动画效果  有0-6种
				yes:function(index, layero){
					saveChange();
				}
			});
		});
		
		$(document).on("click", "#refresh", function(){
			window.top.$.layerAlert.confirm({
				msg:"确认重置？",
				title:"提示",	  //弹出框标题
				width:'300px',
				hight:'200px',
				shade: [0.5,'black'],  //遮罩
				icon:0,  //弹出框的图标：0:警告、1：对勾、2：叉、3：问号、4：锁、5：不高兴的脸、6：高兴的脸
				shift:1,  //弹出时的动画效果  有0-6种
				yes:function(index, layero){
					location.reload(); 
				}
			});
		});
		
	});
	
	function init(){
		$.ajax({
			url:context +'/gzgl/findAllPenalConstant.action',
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(successDate){
				data = successDate['resultMap'];
				$.ptjc.penalConstant.day.initTable(successDate['resultMap']);
				$.ptjc.penalConstant.week.initTable(successDate['resultMap']);
				$.ptjc.penalConstant.month.initTable(successDate['resultMap']);
				$.ptjc.penalConstant.year.initTable(successDate['resultMap']);
			}
		});
	}
	
	function saveChange(){
		
		var data = {
				"DAY":$.ptjc.penalConstant.day.saveChange(),
				"WEEK":$.ptjc.penalConstant.week.saveChange(),
				"MONTH":$.ptjc.penalConstant.month.saveChange(),
				"YEAR":$.ptjc.penalConstant.year.saveChange()
		}
		
		if(flag == false){
			window.top.$.layerAlert.alert({msg:dayErrorInfo + weekErrorInfo + monthErrorInfo +yearErrorInfo,title:"提示"});
			dayErrorInfo = "";
			weekErrorInfo = "";
			monthErrorInfo = "";
			yearErrorInfo = "";
			flag = true
			return;
		}
		
		$.ajax({
			url:context +'/gzgl/saveAllPenalConstant.action',
			type:"post",
			data:$.util.toJSONString(data),
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success:function(){
				window.top.$.layerAlert.alert({msg:"保存成功!",title:"提示"});
			}
		});
	}
	
})(jQuery);	


$.ptjc.penalConstant.day = $.ptjc.penalConstant.day || {} ;
(function($){

	$(document).ready(function() {
		
	})
		function initTable(map) {
		var dataSet = [];
		for ( var i in map) {
			if (i == "DAY") {
				dataSet = map[i];
			}
		}
		// Setting
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = dataSet;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "30%",
					"title" : "统计对象",
					"data" : "pcsName",
					"render" : function(data, type, full, meta) {
						
						
						return data +'<input type="hidden" name="field＿name" value="'+full.pcsCode+'">';
					}
				},
				{
					"targets" : 1,
					"width" : "15%",
					"title" : '<div class="warning-type"><span class="btn sq-blue btn-xs" style="width:30px;"></span></div>',
					"data" : "blue",
					"render" : function(data, type, full, meta) {
						return '<input class="form-control input-sm m-inline" value="'
								+ data + '" style="width: 50px">';
					}
				},
				{
					"targets" : 2,
					"width" : "15%",
					"title" : '<div class="warning-type"> <span class="btn sq-yellow btn-xs" style="width:30px;"></span></div>',
					"data" : "yellow",
					"render" : function(data, type, full, meta) {
						return '<input class="form-control input-sm m-inline" value="'
								+ data + '" style="width: 50px">';
					}
				},
				{
					"targets" : 3,
					"width" : "15%",
					"title" : '<div class="warning-type"> <span class="btn sq-orange btn-xs" style="width:30px;"></span></div>',
					"data" : "orange",
					"render" : function(data, type, full, meta) {
						return '<input class="form-control input-sm m-inline" value="'
								+ data + '" style="width: 50px" >';
					}
				},
				{
					"targets" : 4,
					"width" : "15%",
					"title" : '<div class="warning-type"> <span class="btn sq-red btn-xs" style="width:30px;"></span></div>',
					"data" : "red",
					"render" : function(data, type, full, meta) {
						return '<input class="form-control input-sm m-inline" value="'
								+ data + '" style="width: 50px">';
					}
				}
		];
		tb.ordering = false;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		$("#day").DataTable(tb);
		
	}
	
	function saveChange(){
		
		var arr = [];
		var reg = /^\d+$/;
		var rowIndex = 0;
		$("#day tbody tr").each(function(){
			rowIndex++;
			var full = new Object();
			var tds = $(this).children();
			full.pcsName = $(tds.eq(0)).text();
			full.pcsCode = $(tds.eq(0).find("input")[0]).val();
			full.blue = $(tds.eq(1).find("input")[0]).val();
			full.yellow = $(tds.eq(2).find("input")[0]).val();
			full.orange = $(tds.eq(3).find("input")[0]).val();
			full.red = $(tds.eq(4).find("input")[0]).val();
			if(!reg.test(full.blue) || !reg.test(full.yellow) || !reg.test(full.orange) || !reg.test(full.red)){
				dayErrorInfo = "日警情常量的第" + rowIndex + "行数据有误，填写数据必须为数字<br/>";
				rowIndex = -1;
				flag = false;
				return false;
			}
			if( Number(full.blue) >= Number(full.yellow) || Number(full.yellow) >= Number(full.orange) || Number(full.orange) >= Number(full.red)){
				dayErrorInfo = "日警情常量的第" + rowIndex + "行数据有误，后一个数据必须大于等于前一个数据<br/>";
				rowIndex = -1;
				flag = false;
				return false;
			}
			
			arr.push(full);
		});
		if(rowIndex == -1){
			return;
		}
		
		return arr;
	}
	
	
	jQuery.extend($.ptjc.penalConstant.day, { 
		initTable:initTable,
		saveChange:saveChange
		
	});	
	

})(jQuery);	


$.ptjc.penalConstant.week = $.ptjc.penalConstant.week || {} ;
(function($){
	
	$(document).ready(function() {
		
	})
		function initTable(map) {
		var dataSet = [];
		for ( var i in map) {
			if (i == "WEEK") {
				dataSet = map[i];
			}
		}
		// Setting
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = dataSet;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "30%",
					"title" : "统计对象",
					"data" : "pcsName",
					"render" : function(data, type, full, meta) {
						return data +'<input type="hidden" name="field＿name" value="'+full.pcsCode+'">';
					}
				},
				{
					"targets" : 1,
					"width" : "15%",
					"title" : '<div class="warning-type"><span class="btn sq-blue btn-xs" style="width:30px;"></span></div>',
					"data" : "blue",
					"render" : function(data, type, full, meta) {
						return '<input class="form-control input-sm m-inline" value="'
								+ data + '" style="width: 50px">';
					}
				},
				{
					"targets" : 2,
					"width" : "15%",
					"title" : '<div class="warning-type"> <span class="btn sq-yellow btn-xs" style="width:30px;"></span></div>',
					"data" : "yellow",
					"render" : function(data, type, full, meta) {
						return '<input class="form-control input-sm m-inline" value="'
								+ data + '" style="width: 50px">';
					}
				},
				{
					"targets" : 3,
					"width" : "15%",
					"title" : '<div class="warning-type"> <span class="btn sq-orange btn-xs" style="width:30px;"></span></div>',
					"data" : "orange",
					"render" : function(data, type, full, meta) {
						return '<input class="form-control input-sm m-inline" value="'
								+ data + '" style="width: 50px" >';
					}
				},
				{
					"targets" : 4,
					"width" : "15%",
					"title" : '<div class="warning-type"> <span class="btn sq-red btn-xs" style="width:30px;"></span></div>',
					"data" : "red",
					"render" : function(data, type, full, meta) {
						return '<input class="form-control input-sm m-inline" value="'
								+ data + '" style="width: 50px">';
					}
				}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		$("#week").DataTable(tb);
	}
	
	function saveChange(){
		var arr = [];
		var reg = /^\d+$/;
		var rowIndex = 0;
		$("#week tbody tr").each(function(){
			rowIndex++;
			var full = new Object();
			var tds = $(this).children();
			full.pcsName = $(tds.eq(0)).text();
			full.pcsCode = $(tds.eq(0).find("input")[0]).val();
			full.blue = $(tds.eq(1).find("input")[0]).val();
			full.yellow = $(tds.eq(2).find("input")[0]).val();
			full.orange = $(tds.eq(3).find("input")[0]).val();
			full.red = $(tds.eq(4).find("input")[0]).val();
			if(!reg.test(full.blue) || !reg.test(full.yellow) || !reg.test(full.orange) || !reg.test(full.red)
					|| Number(full.blue) >= Number(full.yellow) || Number(full.yellow) >= Number(full.orange) || Number(full.orange) >= Number(full.red)){
				weekErrorInfo = "周警情常量的第" + rowIndex + "行数据有误，请检查<br/>";
				rowIndex = -1;
				flag = false;
				return false;
			}
			
			arr.push(full);
		});
		if(rowIndex == -1){
			return;
		}
		
		return arr;
	}
	
	jQuery.extend($.ptjc.penalConstant.week , { 
		initTable:initTable,
		saveChange:saveChange
		
	});	
	
})(jQuery);	


$.ptjc.penalConstant.month = $.ptjc.penalConstant.month || {} ;
(function($){
	$(document).ready(function() {
		
	})
		function initTable(map) {
		var dataSet = [];
		for ( var i in map) {
			if (i == "MONTH") {
				dataSet = map[i];
			}
		}
		// Setting
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = dataSet;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "30%",
					"title" : "统计对象",
					"data" : "pcsName",
					"render" : function(data, type, full, meta) {
						return data +'<input type="hidden" name="field＿name" value="'+full.pcsCode+'">';
					}
				},
				{
					"targets" : 1,
					"width" : "15%",
					"title" : '<div class="warning-type"><span class="btn sq-blue btn-xs" style="width:30px;"></span></div>',
					"data" : "blue",
					"render" : function(data, type, full, meta) {
						return '<input class="form-control input-sm m-inline" value="'
								+ data + '" style="width: 50px">';
					}
				},
				{
					"targets" : 2,
					"width" : "15%",
					"title" : '<div class="warning-type"> <span class="btn sq-yellow btn-xs" style="width:30px;"></span></div>',
					"data" : "yellow",
					"render" : function(data, type, full, meta) {
						return '<input class="form-control input-sm m-inline" value="'
								+ data + '" style="width: 50px">';
					}
				},
				{
					"targets" : 3,
					"width" : "15%",
					"title" : '<div class="warning-type"> <span class="btn sq-orange btn-xs" style="width:30px;"></span></div>',
					"data" : "orange",
					"render" : function(data, type, full, meta) {
						return '<input class="form-control input-sm m-inline" value="'
								+ data + '" style="width: 50px" >';
					}
				},
				{
					"targets" : 4,
					"width" : "15%",
					"title" : '<div class="warning-type"> <span class="btn sq-red btn-xs" style="width:30px;"></span></div>',
					"data" : "red",
					"render" : function(data, type, full, meta) {
						return '<input class="form-control input-sm m-inline" value="'
								+ data + '" style="width: 50px">';
					}
				}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		$("#month").DataTable(tb);
	}
	
	function saveChange(){
		var arr = [];
		var reg = /^\d+$/;
		var rowIndex = 0;
		$("#month tbody tr").each(function(){
			rowIndex++;
			var full = new Object();
			var tds = $(this).children();
			full.pcsName = $(tds.eq(0)).text();
			full.pcsCode = $(tds.eq(0).find("input")[0]).val();
			full.blue = $(tds.eq(1).find("input")[0]).val();
			full.yellow = $(tds.eq(2).find("input")[0]).val();
			full.orange = $(tds.eq(3).find("input")[0]).val();
			full.red = $(tds.eq(4).find("input")[0]).val();
			if(!reg.test(full.blue) || !reg.test(full.yellow) || !reg.test(full.orange) || !reg.test(full.red)
					|| Number(full.blue) >= Number(full.yellow) || Number(full.yellow) >= Number(full.orange) || Number(full.orange) >= Number(full.red)){
				monthErrorInfo = "月警情常量的第" + rowIndex + "行数据有误，请检查<br/>";
				rowIndex = -1;
				flag = false;
				return false;
			}
			arr.push(full);
		});
		if(rowIndex == -1){
			return;
		}
		return arr;
	}
	
	jQuery.extend($.ptjc.penalConstant.month, { 
		initTable:initTable,
		saveChange:saveChange
		
	});	
	
})(jQuery);	


$.ptjc.penalConstant.year = $.ptjc.penalConstant.year || {} ;
(function($){
	
	$(document).ready(function() {
		
	})
		function initTable(map) {
		for ( var i in map) {
			if (i == "YEAR") {
				dataSet = map[i];
			}
		}
		// Setting
		var tb = $.uiSettings.getLocalOTableSettings();
		$.util.log(tb);
		tb.data = dataSet;
		tb.columnDefs = [
				{
					"targets" : 0,
					"width" : "30%",
					"title" : "统计对象",
					"data" : "pcsName",
					"render" : function(data, type, full, meta) {
						return data +'<input type="hidden" name="field＿name" value="'+full.pcsCode+'">';
					}
				},
				{
					"targets" : 1,
					"width" : "15%",
					"title" : '<div class="warning-type"><span class="btn sq-blue btn-xs" style="width:30px;"></span></div>',
					"data" : "blue",
					"render" : function(data, type, full, meta) {
						return '<input class="form-control input-sm m-inline" value="'
								+ data + '" style="width: 50px">';
					}
				},
				{
					"targets" : 2,
					"width" : "15%",
					"title" : '<div class="warning-type"> <span class="btn sq-yellow btn-xs" style="width:30px;"></span></div>',
					"data" : "yellow",
					"render" : function(data, type, full, meta) {
						return '<input class="form-control input-sm m-inline" value="'
								+ data + '" style="width: 50px">';
					}
				},
				{
					"targets" : 3,
					"width" : "15%",
					"title" : '<div class="warning-type"> <span class="btn sq-orange btn-xs" style="width:30px;"></span></div>',
					"data" : "orange",
					"render" : function(data, type, full, meta) {
						return '<input class="form-control input-sm m-inline" value="'
								+ data + '" style="width: 50px" >';
					}
				},
				{
					"targets" : 4,
					"width" : "15%",
					"title" : '<div class="warning-type"> <span class="btn sq-red btn-xs" style="width:30px;"></span></div>',
					"data" : "red",
					"render" : function(data, type, full, meta) {
						return '<input class="form-control input-sm m-inline" value="'
								+ data + '" style="width: 50px">';
					}
				}
		];
		tb.ordering = false;
		tb.paging = true;
		tb.hideHead = false;
		tb.dom = null;
		tb.searching = false;
		tb.lengthChange = false;
		tb.paging = false;
		tb.info = false;
		$("#year").DataTable(tb);
	}
		
		function saveChange(){
			
			var arr = [];
			var reg = /^\d+$/;
			var rowIndex = 0;
			$("#year tbody tr").each(function(){
				rowIndex++;
				var full = new Object();
				var tds = $(this).children();
				full.pcsName = $(tds.eq(0)).text();
				full.pcsCode = $(tds.eq(0).find("input")[0]).val();
				full.blue = $(tds.eq(1).find("input")[0]).val();
				full.yellow = $(tds.eq(2).find("input")[0]).val();
				full.orange = $(tds.eq(3).find("input")[0]).val();
				full.red = $(tds.eq(4).find("input")[0]).val();
				if(!reg.test(full.blue) || !reg.test(full.yellow) || !reg.test(full.orange) || !reg.test(full.red)
						|| Number(full.blue) >= Number(full.yellow) || Number(full.yellow) >= Number(full.orange) || Number(full.orange) >= Number(full.red)){
					yearErrorInfo = "年警情常量的第" + rowIndex + "行数据有误，请检查";
					rowIndex = -1;
					flag = false;
					return false;
				}
				arr.push(full);
			});
			if(rowIndex == -1){
				return;
			}
			return arr;
		}
	
	jQuery.extend($.ptjc.penalConstant.year, { 
		initTable:initTable,
		saveChange:saveChange
	});	
	
})(jQuery);	


