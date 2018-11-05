$.ptjc = $.ptjc || {} ;
$.ptjc.distributionRule = $.ptjc.distributionRule || {} ;
(function($){
	var table = null;
	"use strict";
	
	$(document).ready(function() {
		$.szpt.util.businessData.addToInitSuccessCallBack(init);
	});
	
	$(document).on("click", "#saveBtn", function(){
		$("#saveBtn").hide();
		$("#cancel").hide();
		$("#modify").show();
		saveChange();
	});
	$(document).on("click", "#search", function(){
		if($.szpt.util.businessData.getSelectByTree("jqlx") == ""){
			window.top.$.layerAlert.alert({msg:"请选择警情类型!",title:"提示"});
			return ;
		}else if($.select2.val("#tjdx") == ""){
			window.top.$.layerAlert.alert({msg:"请选择统计对象类型!",title:"提示"});
			return ; 
		}
		if(table == null){
			initTable();
			$("#modify").show();
		}else{
			table.draw();
			$("#saveBtn").hide();
			$("#cancel").hide();
			$("#modify").show();
		}
	});
	
	$(document).on("click", "#modify", function(){
		$("#table tbody tr").each(function(){
			var row = table.row(this);
			var tds = $(this).children();
			$(tds.eq(1).find("input")[0]).removeAttr("readonly");
			$(tds.eq(2).find("input")[0]).removeAttr("readonly");
			$(tds.eq(3).find("input")[0]).removeAttr("readonly");
			$(tds.eq(4).find("input")[0]).removeAttr("readonly");
			$(tds.eq(5).find("input")[0]).removeAttr("readonly");
		});
		$("#saveBtn").show();
		$("#cancel").show();
		$("#modify").hide();
	});
	
	$(document).on("click", "#cancel", function(){
		$("#table tbody tr").each(function(){
			var row = table.row(this);
			var full = row.data() ;
			var tds = $(this).children();
			$(tds.eq(1).find("input")[0]).attr("readonly", "readonly");
			$(tds.eq(2).find("input")[0]).attr("readonly", "readonly");
			$(tds.eq(3).find("input")[0]).attr("readonly", "readonly");
			$(tds.eq(4).find("input")[0]).attr("readonly", "readonly");
			$(tds.eq(5).find("input")[0]).attr("readonly", "readonly");
			$(tds.eq(1).find("input")[0]).val(full.blue);
			$(tds.eq(2).find("input")[0]).val(full.yellow);
			$(tds.eq(3).find("input")[0]).val(full.orange);
			$(tds.eq(4).find("input")[0]).val(full.red);
			$(tds.eq(5).find("input")[0]).val(full.advice);
		});
		$("#saveBtn").hide();
		$("#cancel").hide();
		$("#modify").show();
	});
	
	$(document).on("keyup", ".addEventB", function(){
		if($("#saveBtn").css("display") == "none"){
			return;
		}
		countSum(this, 1);
	});
	
	$(document).on("keyup", ".addEventY", function(){
		if($("#saveBtn").css("display") == "none"){
			return;
		}
		countSum(this, 2);
	});
	
	//点击放大镜弹出下拉选
	$(document).on("click", ".glyphicon-search", function(){
		$("#jqlx").click();
	});
	
	$(document).on("keyup", ".addEventO", function(){
		if($("#saveBtn").css("display") == "none"){
			return;
		}
		countSum(this, 3);
	});
	
	$(document).on("keyup", ".addEventR", function(){
		if($("#saveBtn").css("display") == "none"){
			return;
		}
		countSum(this, 4);
	});
	
	function init(){
		$.szpt.util.businessData.initJqlxTree("jqlx");
		var arr = [];
		for(var i in $.common.STATISTICS_TYPE){
			var obj = {	id : i,	name : $.common.STATISTICS_TYPE[i] }
			arr.push(obj);
		}
		$.select2.addByList("#tjdx", arr, "id", "name");
	}

	function initTable(){
		var tb = $.uiSettings.getOTableSettings();
			tb.ajax.url = context +'/gzgl/findAllDistributionRule.action',
			tb.columnDefs = [
				{
					"targets": 0,
         	    	"width": "10%",
         	    	"title": "统计对象",
         	    	"data": "targetName" ,
         	    	"render": function ( data, type, full, meta ) {
         	    			  return data;
         	    	}
				},
				{
					"targets" : 1,
					"width" : "15%",
					"title" : '<div class="warning-type"> <span class="btn sq-blue btn-xs" style="width:103px;"></span></div>',
					"data" : "blue",
					"render" : function(data, type, full, meta) {
						return '<input readonly style="width:120px" class="addEventB form-control input-sm" value="' + data + '">';
					}
				},
				{
					"targets" : 2,
					"width" : "15%",
					"title" : '<div class="warning-type"> <span class="btn sq-yellow btn-xs" style="width:103px;"></span></div>',
					"data" : "yellow",
					"render" : function(data, type, full, meta) {
						return '<input readonly style="width:120px" class="addEventY form-control input-sm" value="' + data + '">';
					}
				},{
					"targets" : 3,
					"width" : "15%",
					"title" : '<div class="warning-type"> <span class="btn sq-orange btn-xs" style="width:103px;"></span></div>',
					"data" : "orange",
					"render" : function(data, type, full, meta) {
						return '<input readonly style="width:120px" class="addEventO form-control input-sm" value="' + data + '">';
					}
				},{
					"targets" : 4,
					"width" : "15%",
					"title" : '<div class="warning-type"> <span class="btn sq-red btn-xs" style="width:103px;"></span></div>',
					"data" : "red",
					"render" : function(data, type, full, meta) {
						return '<input readonly style="width:120px" class="addEventR form-control input-sm" value="' + data + '">';
					}
				},
				{
					"targets" : 5,
					"width" : "",
					"title" : "警力部署建议",
					"data" : "advice",
					"render" : function(data, type, full, meta) {
						return '<input readonly class="form-control input-sm" value="' + data + '">';
					}
				}
			];
			tb.ordering = false ;
			tb.searching = false ;
			tb.lengthChange = false ;
			tb.autoFooter = false ;
			tb.autoWidth = false ;
			tb.paging = false;
			tb.info = false;
			tb.paramsReq = function(d, pagerReq){
				var data = { jqlx : $.szpt.util.businessData.getSelectByTree("jqlx"), tjdx : $.select2.val("#tjdx")}
				var queryStr = $.util.toJSONString(data);
				$.util.objToStrutsFormData(queryStr,"queryStr",d);
			};
			tb.paramsResp = function(json) {
				json.recordsTotal = json.resultMap.dataList.length;
				json.recordsFiltered = json.resultMap.dataList.length;
				json.data = json.resultMap.dataList;
			};
			table = $("#table").DataTable(tb);
	}
	
	function saveChange(){
		var arr = [];
		var reg = /^\d+$/;
		var rowIndex = 0;
		$("#table tbody tr").each(function(){
			var row = table.row(this);
			rowIndex ++;
			var full = row.data() ;
			var tds = $(this).children();
			full.blue = $(tds.eq(1).find("input")[0]).val();
			full.yellow = $(tds.eq(2).find("input")[0]).val();
			full.orange = $(tds.eq(3).find("input")[0]).val();
			full.red = $(tds.eq(4).find("input")[0]).val();
			full.advice = $(tds.eq(5).find("input")[0]).val();
			if(!reg.test(full.blue) || !reg.test(full.yellow) || !reg.test(full.orange) || !reg.test(full.red)){
				window.top.$.layerAlert.alert({msg:"第" + rowIndex + "行数据有误，填写数据必须为数字",title:"提示"});
				rowIndex = -1;
				return false;
				
			}
			if( Number(full.blue) >= Number(full.yellow) || Number(full.yellow) >= Number(full.orange) || Number(full.orange) >= Number(full.red)){
				window.top.$.layerAlert.alert({msg:"第" + rowIndex + "行数据有误，后一个数据必须大于等于前一个数据",title:"提示"});
				rowIndex = -1;
				return false;
			}
			if(full.advice.length > 80){
				window.top.$.layerAlert.alert({msg:"第" + rowIndex + "行建议过长（>80），请检查",title:"提示"});
				rowIndex = -1;
				return false;
			}
			arr.push(full);
		});
		if(rowIndex == -1){
			$("#saveBtn").show();
			$("#cancel").show();
			$("#modify").hide();
			return;
		}
		$.ajax({
			url:context +'/gzgl/saveAllDistributionRule.action',
			type:"post",
			contentType: "application/json; charset=utf-8",
			data:$.util.toJSONString(arr),
			dataType:"json",
			success:function(){
				window.top.$.layerAlert.alert({msg:"保存成功!",title:"提示"});
			}
		});
	}
	
	function countSum(input, index){
		var row = table.row($(input).parents("tr"));
		var full = row.data() ;
		if(full.targetName == "合计"){
			return;
		}
		var sum = 0;
		$("#table tbody tr").each(function(){
			var row = table.row(this);
			var fullData = row.data() ;
			var tds = $(this).children();
			if(fullData.targetName == "合计"){
				$(tds.eq(index).find("input")[0]).val(sum);
				return;
			}
			var num = $(tds.eq(index).find("input")[0]).val();
			var reg = /^\d+$/;
			if(reg.test(num)){
				sum += Number(num);
			}
		});
	}
})(jQuery);	

